/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.boletos.BoletoDTO;
import dtos.boletos.PagoDTO;
import entidades.Boleto;
import entidades.Pago;
import static mappers.SalaMapper.toAsientoDTOList;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class BoletoMapper {
    /**
     * Convierte una entidad Boleto a BoletoDTO
     * @param boleto
     * @param sala
     * @return 
     */
    public static BoletoDTO toDTO( Boleto boleto ) {

        if (boleto == null) {
            return null;
        }

        BoletoDTO dto = new BoletoDTO();

        dto.setId(boleto.getId().toHexString());
        dto.setUsuarioId(boleto.getUsuarioId().toHexString());
        dto.setFuncionId(boleto.getFuncionId().toHexString());
        
        dto.setAsiento(SalaMapper.toAsientoDTO(boleto.getAsiento()));
        
        dto.setEstadoBoleto(boleto.getEstadoBoleto());
        dto.setFechaCompra(boleto.getFechaCompra());
        dto.setFolio(boleto.getFolio());
        dto.setPrecio(boleto.getPrecio());

        return dto;
    }
    
    public static Boleto toEntity( BoletoDTO dto) {

        if (dto == null) {
            return null;
        }

        Boleto boleto = new Boleto();

//        boleto.setId(new ObjectId(dto.getId()));
        
        boleto.setUsuarioId(new ObjectId(dto.getUsuarioId()));
        
        boleto.setFuncionId(new ObjectId(dto.getFuncionId()));

        boleto.setAsiento(SalaMapper.toAsientoEntity(dto.getAsiento()));
        
        boleto.setEstadoBoleto(dto.getEstadoBoleto());
        boleto.setFechaCompra(dto.getFechaCompra());
        boleto.setFolio(dto.getFolio());
        boleto.setPrecio(dto.getPrecio());


        return boleto;
    }
    
    public static Pago toPagoEntity(PagoDTO dto){
        if (dto==null) {
            return null;
        }
        
        Pago pago = new Pago(
                new ObjectId(dto.getId()), 
                new ObjectId(dto.getBoletoId()), 
                dto.getMonto(), 
                dto.getMetodoPago(), 
                dto.getFechaPago(), 
                dto.getEstadoPago()
        );
        
        return pago;
    }
}
