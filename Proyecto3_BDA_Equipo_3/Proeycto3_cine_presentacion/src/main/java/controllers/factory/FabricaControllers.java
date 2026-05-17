/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.factory;

import controllers.FuncionController;
import controllers.PeliculaController;
import controllers.UsuarioController;

/**
 *
 * @author jalt2
 */
public class FabricaControllers {
    private static PeliculaController peliculaController;

    private static UsuarioController usuarioController;
    
    private static FuncionController funcionController;

    private FabricaControllers() {}

    public static PeliculaController getPeliculaController() {

        if (peliculaController == null) {

            peliculaController = new PeliculaController();
        }

        return peliculaController;
    }

    public static UsuarioController getUsuarioController() {

        if (usuarioController == null) {

            usuarioController = new UsuarioController();
        }

        return usuarioController;
    }
    
    public static FuncionController getFuncionController() {
        
        if (funcionController==null) {
            
            funcionController = new FuncionController();
            
        }
        
        return funcionController;
    }
}
