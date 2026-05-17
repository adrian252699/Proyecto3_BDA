/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.salas.AsientoDTO;
import dtos.boletos.BoletoDTO;
import dtos.boletos.PagoDTO;
import entidades.Boleto;
import enums.EstadoBoleto;
import enums.EstadoPago;
import excepciones.daos.DaoException;
import excepciones.negocio.NegocioException;
import interfaces.IBoletoBO;
import interfaces.IBoletoDAO;
import interfaces.IPagoDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import mappers.BoletoMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class BoletoBO implements IBoletoBO{
    
    private final IBoletoDAO boletoDAO;
    private final IPagoDAO pagoDAO;

    public BoletoBO(IBoletoDAO boletoDAO, IPagoDAO pagoDAO) {
        this.boletoDAO = boletoDAO;
        this.pagoDAO = pagoDAO;
    }

    @Override
    public BoletoDTO reservarAsiento(String usuarioId, String funcionId, AsientoDTO asiento) throws NegocioException {
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setUsuarioId(usuarioId);
        boletoDTO.setFuncionId(funcionId);
        boletoDTO.setAsiento(asiento);
        boletoDTO.setEstadoBoleto(EstadoBoleto.RESERVADO);
        boletoDTO.setFechaCompra(LocalDateTime.now());
        asiento.setDisponible(false);
        boletoDTO.setPrecio(75.00);
        
        try {
            Boleto boletoEntidad = boletoDAO.crearBoleto(BoletoMapper.toEntity(boletoDTO));
            
            return BoletoMapper.toDTO(boletoEntidad);
        } catch (DaoException e) {
            throw new NegocioException("El asiento ya está ocupado");
        }
    }

    @Override
    public void confirmarPago(String boletoId, PagoDTO pagoDTO) throws NegocioException {
        try {
            pagoDTO.setBoletoId(boletoId);
            pagoDTO.setFechaPago(LocalDate.now());

            pagoDAO.crearPago(BoletoMapper.toPagoEntity(pagoDTO));

            if (pagoDTO.getEstadoPago() == EstadoPago.PAGADO) {
                boletoDAO.actualizarEstado(new ObjectId(boletoId), EstadoBoleto.VENDIDO);
            } else {
                boletoDAO.actualizarEstado(new ObjectId(boletoId), EstadoBoleto.CANCELADO);
            }

        } catch (DaoException e) {
            throw new NegocioException("Error al procesar el pago");
        }
    }
    
}
