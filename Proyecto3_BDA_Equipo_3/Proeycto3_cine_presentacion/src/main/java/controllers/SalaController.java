/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dto.funciones.FuncionDTO;
import dto.salas.AsientoDTO;
import dto.salas.CrearSalaDTO;
import dto.salas.SalaDTO;
import excepciones.negocio.NegocioException;
import excepciones.presentacion.ControllerException;
import factory.FabricaObjetosBO;
import interfaces.ISalaBO;
import java.util.List;

/**
 *
 * @author jalt2
 */
public class SalaController {
    private final ISalaBO salaBO;

    public SalaController() {
        this.salaBO = FabricaObjetosBO.crearSalaBO();
    }
    
    public List<AsientoDTO> listarAsientosDisponibles(Integer numSala)throws ControllerException{
        try {
            return salaBO.listarAsientosDisponibles(numSala);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible listar asientos disponibles: ", e);
        }
    }
    
    public SalaDTO crearSala(CrearSalaDTO salaDTO) throws ControllerException{
        try {
            return salaBO.crearSala(salaDTO);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible crear sala: ", e);
        }
    }
    
    public void reservarAsiento(FuncionDTO funcion, AsientoDTO asiento)throws ControllerException{
        try {
            salaBO.reservarAsiento(funcion, asiento);
        } catch (NegocioException e) {
            throw new ControllerException("No fue reservar asiento: ", e);
        }
    }
}
