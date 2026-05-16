/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.funciones.ActualizarFuncionDTO;
import dto.funciones.FuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import entidades.Funcion;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import excepciones.negocio.NegocioException;
import interfaces.IFuncionBO;
import interfaces.IFuncionDAO;
import java.time.LocalDate;
import java.util.List;
import mappers.FuncionMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class FuncionBO implements IFuncionBO{
    
    private final IFuncionDAO funcionDAO;

    public FuncionBO(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    @Override
    public FuncionDTO guardarFuncion(RegistrarFuncionDTO funcionDTO) throws NegocioException {
        
        if (funcionDTO == null) {
            throw new NegocioException("La función no puede estar vacía");
        }

        // Validar película
        if (funcionDTO.getPeliculaId() == null || funcionDTO.getPeliculaId().trim().isEmpty()) {
            throw new NegocioException("La película es obligatoria");
        }

        ObjectId peliculaId;

        try {
            peliculaId = new ObjectId(funcionDTO.getPeliculaId());
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Id de película inválido");
        }

        // Validar sala
        if (funcionDTO.getNumSala() == null) {
            throw new NegocioException("El número de sala es obligatorio");
        }

        if (funcionDTO.getNumSala() <= 0) {
            throw new NegocioException("El número de sala debe ser mayor a 0");
        }

        // Validar capacidad
        if (funcionDTO.getCapacidadSala() == null) {
            throw new NegocioException("La capacidad de la sala es obligatoria");
        }

        if (funcionDTO.getCapacidadSala() <= 0) {
            throw new NegocioException("La capacidad debe ser mayor a 0");
        }

        // Validar fecha
        if (funcionDTO.getFecha() == null) {
            throw new NegocioException("La fecha es obligatoria");
        }

        // No permitir fechas pasadas
        if (funcionDTO.getFecha().isBefore(LocalDate.now())) {
            throw new NegocioException("La fecha no puede ser anterior a la actual");
        }

        if (funcionDTO.getHora() == null) {
            throw new NegocioException("La hora es obligatoria");
        }

        try {
            boolean existeFuncion = funcionDAO.existeFuncionEnSalaHorario(
                    
                    funcionDTO.getNumSala(),
                    funcionDTO.getFecha(),
                    funcionDTO.getHora()
                );

            if (existeFuncion) {
                throw new NegocioException("Ya existe una función programada en esa sala, fecha y horario");
            }

            
            Funcion funcionEntidad = FuncionMapper.toEntity(funcionDTO);

            
            Funcion funcionGuardada = funcionDAO.guardarFuncion(funcionEntidad);

            
            return FuncionMapper.toDTO(funcionGuardada);

        } catch (DaoException e) {
            throw new NegocioException("Error al guardar la función",e);
        }
    }

    @Override
    public FuncionDTO actualizarFuncion(ActualizarFuncionDTO funcionDTO) throws NegocioException {
        if (funcionDTO == null) {
            throw new NegocioException(
                "La función no puede estar vacía"
            );
        }

        // Validar id de función
        if (funcionDTO.getId() == null || funcionDTO.getId().trim().isEmpty()) {
            throw new NegocioException(
                "El id de la función es obligatorio"
            );
        }

        ObjectId funcionId;
        

        try {
            funcionId = new ObjectId(funcionDTO.getId());
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Id de función inválido");
        }

        if (funcionDTO.getPeliculaId() == null || funcionDTO.getPeliculaId().trim().isEmpty()) {
            throw new NegocioException("La película es obligatoria");
        }

        try {
            new ObjectId(funcionDTO.getPeliculaId());
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Id de película inválido");
        }

        // Validar sala
        if (funcionDTO.getNumSala() == null) {
            throw new NegocioException("El número de sala es obligatorio");
        }

        if (funcionDTO.getNumSala() <= 0) {
            throw new NegocioException("El número de sala debe ser mayor a 0");
        }

        // Validar capacidad
        if (funcionDTO.getCapacidadSala() == null) {
            throw new NegocioException("La capacidad de la sala es obligatoria");
        }

        if (funcionDTO.getCapacidadSala() <= 10) {
            throw new NegocioException("La capacidad minima es de 10");
        }

        // Validar fecha
        if (funcionDTO.getFecha() == null) {
            throw new NegocioException("La fecha es obligatoria");
        }

        if (funcionDTO.getFecha().isBefore(LocalDate.now())) {
            throw new NegocioException("La fecha no puede ser anterior a la actual");
        }

        // Validar hora
        if (funcionDTO.getHora() == null) {
            throw new NegocioException("La hora es obligatoria");
        }

        try {
            boolean conflictoHorario = funcionDAO.existeFuncionEnSalaHorario(
                    funcionId,
                    funcionDTO.getNumSala(),
                    funcionDTO.getFecha(),
                    funcionDTO.getHora()
            );

            if (conflictoHorario) {
                throw new NegocioException(
                        "Ya existe una función programada "
                        + "en esa sala, fecha y horario"
                );
            }

            Funcion funcionEntidad = FuncionMapper.toEntity(funcionDTO);

            Funcion funcionActualizada = funcionDAO.actualizarFuncion(funcionEntidad);

            return FuncionMapper.toDTO(funcionActualizada);

        } catch (DaoException e) {
            throw new NegocioException(
                    "Error al actualizar la función",
                    e
            );
        } catch (EntityNotFoundException e) {
            throw new NegocioException(
                    e.getMessage()
            );
        }
    }

    @Override
    public boolean desactivarFuncion(String id) throws NegocioException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new NegocioException( "El id de la funcion es obligatorio");
            }
            
            ObjectId objectId;
            
            try {
                objectId = new ObjectId(id);    
            } catch (IllegalArgumentException e) {
                throw new NegocioException("Id inválido");
            }
            
            return funcionDAO.desactivarFuncion(objectId);
            
        }catch (DaoException e) {
            throw new NegocioException("Error al desactivar la funcion:", e);
        }
    }

    @Override
    public FuncionDTO buscarPorId(String id) throws NegocioException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new NegocioException( "El id de la funcion es obligatorio");
            }
            
            ObjectId objectId;
            
            try {
                objectId = new ObjectId(id);    
            } catch (IllegalArgumentException e) {
                throw new NegocioException("Id inválido");
            }
            
            Funcion funcionEncontrada = funcionDAO.buscarPorId(objectId);
            
            return FuncionMapper.toDTO(funcionEncontrada);
            
        } catch (DaoException e) {
            throw new NegocioException("Error al buscar por id:", e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public List<FuncionDTO> listarFunciones() throws NegocioException {
        try {
            List<Funcion> listaFuncionesEntidad = funcionDAO.listarFunciones();
            
            return FuncionMapper.toDTOList(listaFuncionesEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al listar funciones:", e);
        }
    }

    @Override
    public List<FuncionDTO> listarFuncionesPaginado(int pagina, int limite) throws NegocioException {
        try {
            List<Funcion> listaFuncionesEntidad = funcionDAO.listarFuncionesPaginado(pagina, limite);
            
            return FuncionMapper.toDTOList(listaFuncionesEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al paginar funciones:", e);
        }
    }

    @Override
    public List<FuncionDTO> buscarFuncionesPorPelicula(String peliculaId) throws NegocioException {
        if (peliculaId == null || peliculaId.trim().isEmpty()) {
            throw new NegocioException(
                    "El id de la película es obligatorio"
            );
        }

        ObjectId objectId;

        try {
            objectId = new ObjectId(peliculaId.trim());

        } catch (IllegalArgumentException e) {
            throw new NegocioException(
                    "Id de película inválido"
            );
        }

        try {

            List<Funcion> listaFunciones = funcionDAO.buscarFuncionesPorPelicula(objectId);

            return FuncionMapper.toDTOList(listaFunciones);

        } catch (DaoException e) {

            throw new NegocioException(
                    "Error al buscar funciones por película", 
                    e
            );
        }
    }

    @Override
    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDate fecha) throws NegocioException {
        if (fecha == null) {
            throw new NegocioException(
                    "La fecha es obligatoria"
            );
        }
        
        try {

            List<Funcion> listaFunciones = funcionDAO.buscarFuncionesPorFecha(fecha);

            return FuncionMapper.toDTOList(listaFunciones);

        } catch (DaoException e) {

            throw new NegocioException(
                    "Error al buscar funciones por fecha", 
                    e
            );
        }
    }

    @Override
    public List<FuncionDTO> buscarFuncionesPorPeliculaYFecha(String peliculaId, LocalDate fecha) throws NegocioException {
        // Validar película
        if (peliculaId == null || peliculaId.trim().isEmpty()) {
            throw new NegocioException(
                    "El id de la película es obligatorio"
            );
        }

        ObjectId objectId;

        try {
            objectId = new ObjectId(peliculaId.trim());
        } catch (IllegalArgumentException e) {
            throw new NegocioException(
                    "Id de película inválido"
            );
        }

        // Validar fecha
        if (fecha == null) {
            throw new NegocioException(
                    "La fecha es obligatoria"
            );
        }

        try {
            List<Funcion> listaFunciones = funcionDAO.buscarFuncionesPorPeliculaYFecha(
                    objectId, 
                    fecha
            );

            return FuncionMapper.toDTOList(listaFunciones);

        } catch (DaoException e) {
            throw new NegocioException(
                    "Error al buscar funciones por película y fecha",
                    e
            );
        }    
    }

    @Override
    public List<FuncionDTO> buscarFuncionesActivas() throws NegocioException {
        try {

            List<Funcion> listaFunciones = funcionDAO.buscarFuncionesActivas();

            return FuncionMapper.toDTOList(listaFunciones);

        } catch (DaoException e) {

            throw new NegocioException(
                    "Error al buscar funciones activas",
                    e
            );
        }
    }
    
}
