/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.PeliculaDTO;
import excepciones.presentacion.ControllerException;
import excepciones.negocio.NegocioException;
import factory.FabricaObjetosBO;
import interfaces.IPeliculaBO;
import java.util.List;

/**
 *
 * @author jalt2
 */
public class PeliculaController {
    private final IPeliculaBO peliculaBO;

    public PeliculaController() {
        this.peliculaBO = FabricaObjetosBO.crearPeliculaBO();
    }
    
    public PeliculaDTO guardarPelicula(PeliculaDTO peliculaDTO)throws ControllerException{
        try {
            return this.peliculaBO.guardarPelicula(peliculaDTO);
        } catch (NegocioException e) {
            throw new ControllerException("Error al guardar la pelicula", e);
        }
    }
    
    public PeliculaDTO buscarPeliculaId(String id)throws ControllerException{
        try {
            return this.peliculaBO.buscarPeliculaId(id);
        } catch (NegocioException e) {
            throw new ControllerException("Error al guardar la pelicula", e);
        }
    }
    
    public PeliculaDTO actualizarPelicula(PeliculaDTO peliculaDTO)throws ControllerException{
        try {
            return this.peliculaBO.actualizarPelicula(peliculaDTO);
        } catch (NegocioException e) {
            throw new ControllerException("Error al actualizar la pelicula", e);
        }
    }
    
    public Boolean eliminarPelicula(String id)throws ControllerException{
        try {
            return this.peliculaBO.eliminarPelicula(id);
        } catch (NegocioException e) {
            throw new ControllerException("Error al eliminar la pelicula", e);
        }
    }
    
    public List<PeliculaDTO> listarPeliculas() throws ControllerException{
        try {
            return this.peliculaBO.listarPeliculas();
        } catch (NegocioException e) {
            throw new ControllerException("Error al listar las pelicula", e);
        }
    }
    
    public List<PeliculaDTO> listarPeliculasPaginado(int pagina, int limite) throws ControllerException{
        try {
            return this.peliculaBO.listarPeliculasPaginado(pagina, limite);
        } catch (NegocioException e) {
            throw new ControllerException("Error al listar las pelicula", e);
        }
    }
    
    public List<PeliculaDTO> buscarPorGenero(String genero) throws ControllerException{
        try {
            return this.peliculaBO.buscarPorGenero(genero);
        } catch (NegocioException e) {
            throw new ControllerException("Error al buscar por genero de pelicula", e);
        }
    }
    
    public List<PeliculaDTO> buscarPorClasificacioni(String clasificacion) throws ControllerException{
        try {
            return this.peliculaBO.buscarPorClasificacion(clasificacion);
        } catch (NegocioException e) {
            throw new ControllerException("Error al buscar por clasificacion", e);
        }
    }
}
