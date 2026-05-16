/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dto.usuarios.ActualizarUsuarioDTO;
import dto.usuarios.LoginDTO;
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
    
    public UsuarioDTO iniciarSesion(LoginDTO usuarioLoginDTO)throws ControllerException {
        try {
            return usuarioBO.iniciarSesion(usuarioLoginDTO);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible iniciar sesion: "+e.getMessage());
        }
    }
    
    public UsuarioDTO actualizarUsuario(ActualizarUsuarioDTO datosActualizar)throws ControllerException {
        try {
            return usuarioBO.actualizarUsuario(datosActualizar);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible iniciar sesion: "+e.getMessage());
        }
    }
    
    public UsuarioDTO actualizarCorreo(String id, String correo) throws ControllerException{
        try {
            return usuarioBO.actualizarCorreo(id, correo);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible actualizar el correo: "+e.getMessage());
        }
    }
}
