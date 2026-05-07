/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vistas;

import controllers.PeliculaController;
import dtos.PeliculaDTO;
import excepciones.ControllerException;
import java.util.List;

/**
 *
 * @author jalt2
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ControllerException {
        // TODO code application logic here
        PeliculaController controller = new PeliculaController();
        
        
        PeliculaDTO pelicula1 = new PeliculaDTO("Stars Wars: Episode III - La venganza de los sith", List.of("Accion","Aventura"), 140.0, "PG-13");
        
        PeliculaDTO guardada = controller.guardarPelicula(pelicula1);
        
        System.out.println("Id: " + guardada.getId());
    }
    
}
