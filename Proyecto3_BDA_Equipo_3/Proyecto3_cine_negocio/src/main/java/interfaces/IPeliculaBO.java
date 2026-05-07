/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PeliculaDTO;
import excepciones.negocio.NegocioException;
import java.util.List;

/**
 *
 * @author jalt2
 */
public interface IPeliculaBO {
    public PeliculaDTO guardarPelicula(PeliculaDTO pelicula)throws NegocioException;
    
    public PeliculaDTO actualizarPelicula(PeliculaDTO pelicula)throws NegocioException;
    
    public boolean eliminarPelicula(String id)throws NegocioException;
    
    public PeliculaDTO buscarPeliculaId(String id)throws NegocioException;
    
    public List<PeliculaDTO> listarPeliculas()throws NegocioException;
    
    public List<PeliculaDTO> listarPeliculasPaginado(int pagina, int limite) throws NegocioException;
    
    public List<PeliculaDTO> buscarPorGenero(String genero)throws NegocioException;

    public List<PeliculaDTO> buscarPorClasificacion(String clasificacion)throws NegocioException;
}
