/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.funciones.ActualizarFuncionDTO;
import dto.funciones.FuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import excepciones.negocio.NegocioException;
import interfaces.IFuncionBO;
import interfaces.IFuncionDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jalt2
 */
public class FuncionBO implements IFuncionBO{
    
    private IFuncionDAO funcionDAO;

    public FuncionBO(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    @Override
    public FuncionDTO guardarFuncion(RegistrarFuncionDTO funcion) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FuncionDTO actualizarFuncion(ActualizarFuncionDTO funcion) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivarFuncion(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FuncionDTO buscarPorId(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> listarFunciones() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> listarFuncionesPaginado(int pagina, int limite) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> buscarFuncionesPorPelicula(String peliculaId) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDate fecha) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> buscarFuncionesPorPeliculaYFecha(String peliculaId, LocalDate fecha) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> buscarFuncionesActivas() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
