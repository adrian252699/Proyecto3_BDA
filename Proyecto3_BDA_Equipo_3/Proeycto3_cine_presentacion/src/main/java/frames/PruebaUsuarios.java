/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package frames;

import controllers.UsuarioController;
import dto.usuarios.RegistroUsuarioDTO;
import excepciones.negocio.NegocioException;
import excepciones.presentacion.ControllerException;
import java.time.LocalDate;

/**
 *
 * @author jalt2
 */
public class PruebaUsuarios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ControllerException {
        // TODO code application logic here
        UsuarioController control = new UsuarioController();
        String fechaNacimiento = "2004-04-25";
        
        RegistroUsuarioDTO dto = new RegistroUsuarioDTO("Jesus Adrian", "Tapia", "Luzanilla", "adrian@gmail.com", "Adrian_2504", "6449977740", LocalDate.parse(fechaNacimiento));
        
        RegistroUsuarioDTO dtoFallido = new RegistroUsuarioDTO("Jesus Adrian", "Tapia", "Luzanilla", "adrian@gmail.com", "adrian2504", "64499777", LocalDate.parse(fechaNacimiento));
        
        try {
            control.guardarUsuario(dto);
        } catch (ControllerException ex) {
            throw new ControllerException("Error", ex);
        }
    }
    
}
