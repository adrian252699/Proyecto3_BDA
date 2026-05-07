/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.PeliculaDTO;
import entidades.Pelicula;
import excepciones.DaoException;
import excepciones.NegocioException;
import interfaces.IPeliculaBO;
import interfaces.IPeliculaDAO;
import java.util.List;
import mappers.PeliculaMapper;

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
        
        //Falta Validar las clasificaciones
        if (peliculaDTO.getClasificacion()==null || peliculaDTO.getClasificacion().trim().isEmpty()) {
            throw new NegocioException("La clasificacion es requerida");
        }
        
        //Falta Validar que generos no pueda tener "" o " "
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

    @Override
    public PeliculaDTO actualizarPelicula(PeliculaDTO pelicula) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarPelicula(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PeliculaDTO buscarPeliculaId(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PeliculaDTO> listarPeliculas() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PeliculaDTO> listarPeliculasPaginado(int pagina, int limite) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
