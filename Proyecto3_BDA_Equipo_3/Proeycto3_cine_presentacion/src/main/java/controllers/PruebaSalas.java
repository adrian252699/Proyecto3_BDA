/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import dto.funciones.RegistrarFuncionDTO;
import dto.salas.AsientoDTO;
import dto.salas.CrearSalaDTO;
import dtos.PeliculaDTO;
import excepciones.presentacion.ControllerException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jalt2
 */
public class PruebaSalas {

    /**
     * @param args the command line arguments
     * @throws excepciones.presentacion.ControllerException
     */
    public static void main(String[] args) throws ControllerException {
        FuncionController control = new FuncionController();
        PeliculaController controlPelicula = new PeliculaController();

        String[] filas = {"A", "B", "C", "D", "E"};
        
        List<PeliculaDTO> peliculas = controlPelicula.listarPeliculas();

        try {

            for (int numPeliculas = 1; numPeliculas < peliculas.size(); numPeliculas++) {

                List<AsientoDTO> asientos = new ArrayList<>();

                for (String fila : filas) {
                    for (int i = 1; i <= 10; i++) {
                        asientos.add(new AsientoDTO(fila, i, true));
                    }
                }
                
                PeliculaDTO pelicula = peliculas.get(numPeliculas);

                RegistrarFuncionDTO funcionDTO = new RegistrarFuncionDTO(pelicula.getId(), pelicula.getTitulo(), asientos, 1, LocalDate.now(), LocalTime.now());

                control.guardarFuncion(funcionDTO);

            }
            

        } catch (ControllerException ex) {
            Logger.getLogger(PruebaSalas.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
    }
    
}
