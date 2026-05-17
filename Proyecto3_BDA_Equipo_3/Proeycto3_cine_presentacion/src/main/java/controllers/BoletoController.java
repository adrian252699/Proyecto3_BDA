/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dto.salas.AsientoDTO;
import dtos.boletos.BoletoDTO;
import dtos.boletos.PagoDTO;
import excepciones.negocio.NegocioException;
import excepciones.presentacion.ControllerException;
import factory.FabricaObjetosBO;
import interfaces.IBoletoBO;

/**
 *
 * @author jalt2
 */
public class BoletoController {
    private final IBoletoBO boletoBO;

    public BoletoController() {
        this.boletoBO = FabricaObjetosBO.crearBoletoBO();
    }
    
    public BoletoDTO reservarAsiento(String usuarioId, String funcionId, AsientoDTO asiento) throws ControllerException{
        try {
            return boletoBO.reservarAsiento(usuarioId, funcionId, asiento);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    public void confirmarPago(String boletoId, PagoDTO pagoDTO) throws ControllerException {
        try {
            boletoBO.confirmarPago(boletoId, pagoDTO);
        } catch (NegocioException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
