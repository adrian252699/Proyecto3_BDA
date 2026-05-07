/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.PeliculaDTO;
import entidades.Pelicula;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import excepciones.negocio.NegocioException;
import interfaces.IPeliculaBO;
import interfaces.IPeliculaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.PeliculaMapper;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class PeliculaBO implements IPeliculaBO{
    
    private IPeliculaDAO peliculaDAO;
    private final PeliculaMapper mapper;

    public PeliculaBO(IPeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
        this.mapper = new PeliculaMapper();
    }
    
    //Listo
    @Override
    public PeliculaDTO guardarPelicula(PeliculaDTO peliculaDTO) throws NegocioException {
        //Validaciones de campos
        if (peliculaDTO == null) {
            throw new NegocioException("La pelicula no puede ser nula");
        }
        
        if ( peliculaDTO.getTitulo() == null || peliculaDTO.getTitulo().trim().isEmpty() ) {
            throw new NegocioException("El titulo es obligatorio");
        }
        
        if (peliculaDTO.getDuracion() == null) {
            throw new NegocioException("La duracion de la pelicula es obligatoria");
        }
        
        if (peliculaDTO.getDuracion()<=0) {
            throw new NegocioException("La duracion de la pelicula debe ser mayor a 0");
        }
        
        if (peliculaDTO.getClasificacion()==null || peliculaDTO.getClasificacion().trim().isEmpty()) {
            throw new NegocioException("La clasificacion es requerida");
        }
        
        if (peliculaDTO.getGeneros()==null || peliculaDTO.getGeneros().isEmpty()) {
            throw new NegocioException("La pelicula debe tener al menos un genero");
        }
        
        try {
            //Datos que vienen de presentacion se convierten en pelicula ( entidad )
            Pelicula peliculaEntidad = mapper.toEntity(peliculaDTO);
            
            //Se guardan estos datos y la dao devuelve otro objeto pelicula (es el mismo pero con _id)
            Pelicula peliculaGuardada = peliculaDAO.guardarPelicula(peliculaEntidad);
            
            //El objeto Pelicula con _id se transforma en DTO y se manda a presentacion
            return mapper.toDTO(peliculaGuardada);
            
        } catch (DaoException e) {
            throw new NegocioException("No fue posible guardar la pelicula",e);
        }
    }
    
    //Listo
    @Override
    public PeliculaDTO actualizarPelicula(PeliculaDTO peliculaDTO) throws NegocioException {
        if (peliculaDTO==null) {
            throw new NegocioException("La pelicula no puede ser nula");
        }
        
        if (peliculaDTO.getId()==null||peliculaDTO.getId().trim().isEmpty()) {
            throw new NegocioException("El id es obligatorio");
        }
        
        if (!ObjectId.isValid(peliculaDTO.getId())) {
            throw new NegocioException("El formato del id es inválido");
        }
        
        boolean hayCambios = peliculaDTO.getTitulo() != null || peliculaDTO.getDuracion() != null || peliculaDTO.getClasificacion() != null || peliculaDTO.getGeneros() != null;

        if (!hayCambios) {
            throw new NegocioException("Debe enviar al menos un campo para actualizar");
        }
        
        try {
            //Buscar Pelicula antes de actualizar
            Pelicula peliculaActual = peliculaDAO.buscarPeliculaId(new ObjectId(peliculaDTO.getId()));
            
            
            //Actualizar solo campos no nulos (no todos los campos son obligatorios)
            
            if (peliculaDTO.getTitulo() != null) {
                if (peliculaDTO.getTitulo().trim().isEmpty()) {
                    throw new NegocioException("El título no puede estar vacío");
                }
                
                peliculaActual.setTitulo(peliculaDTO.getTitulo().trim());
            }
            
            if (peliculaDTO.getDuracion() != null) {
                if (peliculaDTO.getDuracion()<=0) {
                    throw new NegocioException("Duracion invalida");
                }
                
                peliculaActual.setDuracion(peliculaDTO.getDuracion());
            }
            
            if (peliculaDTO.getClasificacion() != null) {
                if (peliculaDTO.getClasificacion().trim().isEmpty()) {
                    throw new NegocioException("La clasificacion no puede estar vacío");
                }
                
                peliculaActual.setClasificacion(peliculaDTO.getClasificacion().trim());
            }
            
            if (peliculaDTO.getGeneros() != null) {
                if (peliculaDTO.getGeneros().isEmpty()) {
                    throw new NegocioException("Debe existir al menos un genero");
                }
                
                peliculaActual.setGeneros(peliculaDTO.getGeneros());
            }
            
            //Guardar los cambios en la peliculaActual
            Pelicula peliculaActualizada = peliculaDAO.actualizarPelicula(peliculaActual);
            
            return mapper.toDTO(peliculaActualizada);
            
        } catch (DaoException e) {
            throw new NegocioException("No fue posible actualizar la pelicula",e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException("No se encontro una pelicula",ex);
        }
    }

    @Override
    public boolean eliminarPelicula(String id) throws NegocioException {
        if (id == null || id.trim().isEmpty()) {
            throw new NegocioException("El id es obligatorio");
        }

        if (!ObjectId.isValid(id)) {
            throw new NegocioException("El formato del id es inválido");
        }
        
        try {
            ObjectId objectId = new ObjectId(id);
            
            //Verificar que exista una pelicula con ese id
            peliculaDAO.buscarPeliculaId(objectId);
            
            return peliculaDAO.eliminarPelicula(objectId);
            
        } catch (DaoException e) {
            
            throw new NegocioException("No fue posible eliminar la pelicula",e);
            
        } catch (EntityNotFoundException ex) {
            
            throw new NegocioException("No se encontro una pelicula",ex);
        } 
    }

    @Override
    public PeliculaDTO buscarPeliculaId(String id) throws NegocioException {
        if (id == null || id.trim().isEmpty()) {
            throw new NegocioException("El id es obligatorio");
        }

        if (!ObjectId.isValid(id)) {
            throw new NegocioException("El formato del id es inválido");
        }
        
        try {
            
            Pelicula peliculaEncontrada = peliculaDAO.buscarPeliculaId(new ObjectId(id));
            
            return mapper.toDTO(peliculaEncontrada);
            
        } catch (DaoException e) {
            
            throw new NegocioException("No fue posible buscar la pelicula",e);
            
        } catch (EntityNotFoundException ex) {
            
            throw new NegocioException("No se encontro una pelicula",ex);
        }   
    }

    @Override
    public List<PeliculaDTO> listarPeliculas() throws NegocioException {
        try {
            return mapper.toDTOList(peliculaDAO.listarPeliculas());
        } catch (DaoException ex) {
            throw new NegocioException("No fue posible listar las peliculas",ex);
        }
    }

    @Override
    public List<PeliculaDTO> listarPeliculasPaginado(int pagina, int limite) throws NegocioException {
        if (pagina <= 0) {
            throw new NegocioException("La página debe ser mayor a 0");
        }

        if (limite <= 0) {
            throw new NegocioException("El límite debe ser mayor a 0");
        }
        
        try {
            return mapper.toDTOList(peliculaDAO.listarPeliculasPaginado(pagina, limite));
        } catch (DaoException ex) {
            throw new NegocioException("No fue posible listar las peliculas",ex);
        }
    }

    @Override
    public List<PeliculaDTO> buscarPorGenero(String genero) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PeliculaDTO> buscarPorClasificacion(String clasificacion) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
