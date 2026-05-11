/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import daos.PeliculaDAO;
import daos.UsuarioDAO;
import interfaces.IPeliculaBO;
import interfaces.IPeliculaDAO;
import interfaces.IUsuarioBO;
import interfaces.IUsuarioDAO;
import negocio.PeliculaBO;
import negocio.UsuarioBO;

/**
 *
 * @author jalt2
 */
public class FabricaObjetosBO {
    public static IPeliculaBO crearPeliculaBO() {
        IPeliculaDAO peliculaDAO = new PeliculaDAO();
        return new PeliculaBO(peliculaDAO);
    }
    
    public static IUsuarioBO crearUsuarioBO(){
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        return new UsuarioBO(usuarioDAO);
    }
}
