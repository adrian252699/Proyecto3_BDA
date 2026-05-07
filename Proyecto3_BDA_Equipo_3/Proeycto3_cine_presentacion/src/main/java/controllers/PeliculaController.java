/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dtos.PeliculaDTO;
import excepciones.ControllerException;
import excepciones.NegocioException;
import factory.FabricaObjetosBO;
import interfaces.IPeliculaBO;

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
}
