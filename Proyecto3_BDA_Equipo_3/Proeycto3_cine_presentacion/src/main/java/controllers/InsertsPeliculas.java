/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import dtos.PeliculaDTO;
import excepciones.presentacion.ControllerException;
import java.util.Arrays;

/**
 *
 * @author jalt2
 */
public class InsertsPeliculas {

    /**
     * @param args the command line arguments
     * @throws excepciones.presentacion.ControllerException
     */
    public static void main(String[] args) throws ControllerException {
        // TODO code application logic here
        PeliculaController control = new PeliculaController();
        
        
        
        control.guardarPelicula(
            new PeliculaDTO(
                "El Ultimo Guerrero",
                Arrays.asList("Accion", "Aventura"),
                140.0,
                "B"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Sombras del Terror",
                Arrays.asList("Terror"),
                105.0,
                "C"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Viaje Estelar",
                Arrays.asList("Ciencia Ficcion", "Aventura"),
                155.0,
                "B15"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Risas en Vacaciones",
                Arrays.asList("Comedia"),
                95.0,
                "A"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Corazones Rotos",
                Arrays.asList("Drama"),
                125.0,
                "B15"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Mision Extrema",
                Arrays.asList("Accion", "Ciencia Ficcion"),
                145.0,
                "B"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "La Mansion Oscura",
                Arrays.asList("Terror", "Drama"),
                118.0,
                "C"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Aventureros del Tiempo",
                Arrays.asList("Aventura", "Ciencia Ficcion"),
                132.0,
                "A"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Locuras de Oficina",
                Arrays.asList("Comedia", "Drama"),
                102.0,
                "B"
            )
        );

        control.guardarPelicula(
            new PeliculaDTO(
                "Operacion Fenix",
                Arrays.asList("Accion"),
                138.0,
                "B15"
            )
        );
    }
    
}
