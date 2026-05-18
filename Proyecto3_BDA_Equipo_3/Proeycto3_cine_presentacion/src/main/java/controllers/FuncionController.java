/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dto.funciones.ActualizarFuncionDTO;
import dto.funciones.FuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import dto.salas.AsientoDTO;
import excepciones.negocio.NegocioException;
import excepciones.presentacion.ControllerException;
import factory.FabricaObjetosBO;
import interfaces.IFuncionBO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jalt2
 */
public class FuncionController {
    private final IFuncionBO funcionBO;

    public FuncionController() {
        this.funcionBO = FabricaObjetosBO.crearFuncionBO();
    }
    
    public FuncionDTO guardarFuncion(RegistrarFuncionDTO funcionDTO) throws ControllerException{
        try {
            return funcionBO.guardarFuncion(funcionDTO);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public FuncionDTO actualizarFuncion(ActualizarFuncionDTO funcionDTO) throws ControllerException{
        try {
            return funcionBO.actualizarFuncion(funcionDTO);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public boolean desactivarFuncion(String id) throws ControllerException{
        try {
            return funcionBO.desactivarFuncion(id);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public FuncionDTO buscarPorId(String id)throws ControllerException{
        try {
            return funcionBO.buscarPorId(id);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<FuncionDTO> listarFunciones() throws ControllerException{
        try {
            return funcionBO.listarFunciones();
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<AsientoDTO> listarAsientosDisponibleFuncion(String funcionId) throws ControllerException{
        try {
            return funcionBO.listarAsientosDisponiblesFuncion(funcionId);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<FuncionDTO> listarFuncionesPaginado(int pagina, int limite) throws ControllerException{
        try {
            return funcionBO.listarFuncionesPaginado(pagina, limite);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<FuncionDTO> buscarFuncionesPorPelicula(String peliculaId) throws ControllerException{
        try {
            return funcionBO.buscarFuncionesPorPelicula(peliculaId);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDate fecha) throws ControllerException{
        try {
            return funcionBO.buscarFuncionesPorFecha(fecha);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<FuncionDTO> buscarFuncionesPorPeliculaYFecha(String peliculaId, LocalDate fecha) throws ControllerException{
        try {
            return funcionBO.buscarFuncionesPorPeliculaYFecha(peliculaId,fecha);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public List<FuncionDTO> buscarFuncionesActivas() throws ControllerException{
        try {
            return funcionBO.buscarFuncionesActivas();
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public void reservarAsiento(String funcionId, AsientoDTO asiento) throws ControllerException{
        try {
            funcionBO.reservarAsiento(funcionId, asiento);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
