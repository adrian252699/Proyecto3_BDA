/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dto.usuarios.RegistroUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import excepciones.negocio.NegocioException;
import excepciones.presentacion.ControllerException;
import factory.FabricaObjetosBO;
import interfaces.IUsuarioBO;

/**
 *
 * @author jalt2
 */
public class UsuarioController {
    private final IUsuarioBO usuarioBO;

    public UsuarioController() {
        this.usuarioBO = FabricaObjetosBO.crearUsuarioBO();
    }
    
    public UsuarioDTO guardarUsuario(RegistroUsuarioDTO usuarioNuevo) throws ControllerException{
        try {
            return usuarioBO.guardarUsuario(usuarioNuevo);
        } catch (NegocioException e) {
            throw new ControllerException("Error al guardar usuario: ",e);
        }
    }
}
