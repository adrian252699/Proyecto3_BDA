-/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.funciones.FuncionDTO;
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
import mappers.FuncionMapper;
import mappers.SalaMapper;

import mappers.SalaMapper;

import mappers.SalaMapper;

import excepciones.negocio.NegocioException;

import excepciones.negocio.NegocioException;

import excepciones.negocio.NegocioException;

import excepciones.negocio.NegocioException;

import mappers.FuncionMapper;

import mappers.SalaMapper;

/**
 *
 * @author jalt2
 */
public class SalaBO implements ISalaBO {

    private final ISalaDAO salaDAO;

    public SalaBO(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    @Override
    public SalaDTO crearSala(CrearSalaDTO sala) throws NegocioException {
        if (sala == null) {
            throw new NegocioException("La sala no debe ser nula");
        }

        if (sala.getNumSala() == null) {
            throw new NegocioException("El número de sala es obligatorio");
        }

        if (sala.getNumSala() <= 0) {
            throw new NegocioException("El número de sala debe ser mayor a 0");
        }

        if (sala.getAsientos() == null || sala.getAsientos().isEmpty()) {
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
        if (salaModificar == null) {
            throw new NegocioException("La sala no debe ser nula");
        }

        if (salaModificar.getNumSala() == null) {
            throw new NegocioException("El número de sala es obligatorio");
        }

        if (salaModificar.getNumSala() <= 0) {
            throw new NegocioException("El número de sala debe ser mayor a 0");
        }

        if (salaModificar.getAsientos() == null || salaModificar.getAsientos().isEmpty()) {
            throw new NegocioException("La sala debe tener asientos");
        }

        try {
            Sala salaEntidad = SalaMapper.toEntity(salaModificar);
            Sala salaActualizada = salaDAO.modificarSala(salaEntidad);
            return SalaMapper.toDTO(salaActualizada);
        } catch (DaoException e) {
            throw new NegocioException("Error al modificar sala", e);
        }
    }

    @Override
    public List<SalaDTO> listarSalas() throws NegocioException {
        try {
            List<Sala> salas = salaDAO.listarSalas();
            return SalaMapper.toDTOList(salas);
        } catch (DaoException e) {
            throw new NegocioException("Error al listar salas", e);
        }
    }

    @Override
    public List<AsientoDTO> listarAsientosDisponibles(Integer numSala) throws NegocioException {
        if (numSala == null) {
            throw new NegocioException("El número de sala es obligatorio.");
        }

        if (numSala <= 0) {
            throw new NegocioException("El número de sala debe ser mayor a 0.");
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
        if (numSala == null) {
            throw new NegocioException("El número de sala es obligatorio.");
        }

        if (numSala <= 0) {
            throw new NegocioException("El número de sala debe ser mayor a 0.");
        }

        try {
            List<Asiento> listaEntidad = salaDAO.listarAsientosOcupados(numSala);
            return SalaMapper.toAsientoDTOList(listaEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al listar asientos ocupados", e);
        }
    }

    @Override
    public void reservarAsiento(FuncionDTO funcion, AsientoDTO asiento) throws NegocioException {
        if (funcion == null) {
            throw new NegocioException("La función no debe ser nula");
        }

        if (asiento == null) {
            throw new NegocioException("El asiento no debe ser nulo");
        }

        try {
            salaDAO.reservarAsiento(
                    FuncionMapper.toEntity(funcion),
                    SalaMapper.toAsientoEntity(asiento)
            );
        } catch (DaoException e) {
            throw new NegocioException("Error al reservar asiento", e);
        }
    }
}
