/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.salas.SalaDTO;
import dto.salas.AsientoDTO;
import dto.salas.CrearSalaDTO;
import embebidos.Asiento;
import embebidos.Sala;
import excepciones.daos.DaoException;
import excepciones.negocio.NegocioException;
import interfaces.ISalaBO;
import interfaces.ISalaDAO;
import java.util.List;
import mappers.SalaMapper;

/**
 *
 * @author jalt2
 */
public class SalaBO implements ISalaBO{
    
    private final ISalaDAO salaDAO;

    public SalaBO(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    @Override
    public SalaDTO crearSala(CrearSalaDTO sala) throws NegocioException {
        if (sala==null) {
            throw new NegocioException("La sala no debe ser nula");
        }
        
        if (sala.getNumSala()==null) {
            throw new NegocioException("El numero de sala es obligatorio");
        }
        
        if (sala.getAsientos().isEmpty()) {
            throw new NegocioException("La sala debe tener asientos");
        }
        
        try {
            Sala salaEntidad = salaDAO.crearSala(SalaMapper.toEntity(sala));
            return SalaMapper.toDTO(salaEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al crear sala", e);
        }
    }

    @Override
    public SalaDTO modificarSala(CrearSalaDTO salaModificar) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SalaDTO> listarSalas() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AsientoDTO> listarAsientosDisponibles(Integer numSala) throws NegocioException {
        if (numSala==null) {
            throw new NegocioException("El numero de sala es obligatorio.");
        }
        
        if (numSala<=0) {
            throw new NegocioException("El numero de sala debe ser mayor a 0.");
        }
        
        try {
            List<Asiento> listaEntidad = salaDAO.listarAsientosDisponibles(numSala);

            return SalaMapper.toAsientoDTOList(listaEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al listar asientos disponibles", e);
        }
    }

    @Override
    public List<AsientoDTO> listarAsientosOcupados(Integer numSala) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
