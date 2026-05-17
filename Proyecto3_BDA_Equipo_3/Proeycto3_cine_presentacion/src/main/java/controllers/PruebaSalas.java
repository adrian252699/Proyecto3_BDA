/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import dto.salas.AsientoDTO;
import dto.salas.CrearSalaDTO;
import excepciones.presentacion.ControllerException;
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
     */
    public static void main(String[] args) {
        SalaController control = new SalaController();

        String[] filas = {"A", "B", "C", "D", "E"};

        try {

            for (int numSala = 1; numSala <= 5; numSala++) {

                List<AsientoDTO> asientos = new ArrayList<>();

                for (String fila : filas) {
                    for (int i = 1; i <= 10; i++) {
                        asientos.add(new AsientoDTO(fila, i, true));
                    }
                }

                CrearSalaDTO salaDTO = new CrearSalaDTO(
                    numSala,
                    50,
                    asientos
                );

                control.crearSala(salaDTO);

                System.out.println("Sala " + numSala + " creada correctamente");
            }

        } catch (ControllerException ex) {
            Logger.getLogger(PruebaSalas.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
    }
    
}
