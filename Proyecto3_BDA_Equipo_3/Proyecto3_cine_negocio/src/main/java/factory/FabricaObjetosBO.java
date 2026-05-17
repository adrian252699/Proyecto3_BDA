/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import daos.BoletoDAO;
import daos.FuncionDAO;
import daos.PagoDAO;
import daos.PeliculaDAO;
import daos.SalaDAO;
import daos.UsuarioDAO;
import interfaces.IBoletoBO;
import interfaces.IBoletoDAO;
import interfaces.IFuncionBO;
import interfaces.IFuncionDAO;
import interfaces.IPagoDAO;
import interfaces.IPeliculaBO;
import interfaces.IPeliculaDAO;
import interfaces.ISalaBO;
import interfaces.ISalaDAO;
import interfaces.IUsuarioBO;
import interfaces.IUsuarioDAO;
import negocio.BoletoBO;
import negocio.FuncionBO;
import negocio.PeliculaBO;
import negocio.SalaBO;
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
    
    public static IFuncionBO crearFuncionBO(){
        IFuncionDAO funcionDAO = new FuncionDAO();
        ISalaDAO salaDAO = new SalaDAO();
        return new FuncionBO(funcionDAO,salaDAO);
    }
    
    public static ISalaBO crearSalaBO(){
        ISalaDAO salaDAO = new SalaDAO();
        return new SalaBO(salaDAO);
    }
    
    public static IBoletoBO crearBoletoBO(){
        IBoletoDAO boletoDAO = new BoletoDAO();
        IPagoDAO pagoDAO = new PagoDAO();
        return new BoletoBO(boletoDAO, pagoDAO);
    }
}
