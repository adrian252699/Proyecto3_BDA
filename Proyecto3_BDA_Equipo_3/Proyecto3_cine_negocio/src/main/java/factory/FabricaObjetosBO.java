/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import daos.PeliculaDAO;
import interfaces.IPeliculaBO;
import interfaces.IPeliculaDAO;
import negocio.PeliculaBO;

/**
 *
 * @author jalt2
 */
public class FabricaObjetosBO {
    public static IPeliculaBO crearPeliculaBO() {
        IPeliculaDAO peliculaDAO = new PeliculaDAO();
        return new PeliculaBO(peliculaDAO);
    }
}
