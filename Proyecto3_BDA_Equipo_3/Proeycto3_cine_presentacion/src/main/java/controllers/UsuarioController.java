/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dto.usuarios.ActualizarCorreoDTO;
import dto.usuarios.ActualizarPasswordDTO;
import dto.usuarios.ActualizarUsuarioDTO;
import dto.usuarios.LoginDTO;
import dto.usuarios.RegistroUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import excepciones.negocio.NegocioException;
import excepciones.presentacion.ControllerException;
import factory.FabricaObjetosBO;
import interfaces.IUsuarioBO;
import java.util.List;

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
            throw new ControllerException(e.getMessage());
        }
    }
    
    public UsuarioDTO actualizarUsuario(ActualizarUsuarioDTO datosActualizar)throws ControllerException {
        try {
            return usuarioBO.actualizarUsuario(datosActualizar);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible iniciar sesion: "+e.getMessage());
        }
    }
    
    public UsuarioDTO actualizarCorreo(String id, ActualizarCorreoDTO correoDTO) throws ControllerException{
        try {
            return usuarioBO.actualizarCorreo(id, correoDTO);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible actualizar el correo: "+e.getMessage());
        }
    }
    
    public UsuarioDTO actualizarPassword(String id, ActualizarPasswordDTO password)throws ControllerException {
        try {
            return usuarioBO.actualizarPassword(id, password);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible actualizar la contraseña: "+e.getMessage());
        }
    }
    
    public boolean desactivarUsuario(String id, String password) throws ControllerException{
        try {
            return usuarioBO.desactivarUsuario(id,password);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible desactivar al usuario: "+e.getMessage());
        }
    }
    
    public boolean desactivarUsuarioAdmin(String id) throws ControllerException{
        try {
            return usuarioBO.desactivarUsuarioAdmin(id);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible desactivar al usuario: "+e.getMessage());
        }
    }
    
    public boolean activarUsuario(String id) throws ControllerException{
        try {
            return usuarioBO.activarUsuario(id);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible activar al usuario: "+e.getMessage());
        }
    }
    
    public UsuarioDTO buscarPorCorreo(String correo)throws ControllerException{
        try {
            return usuarioBO.buscarPorCorreo(correo);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible buscar por correo: "+e.getMessage());
        }
    }
    
    public UsuarioDTO buscarPorId(String id)throws ControllerException{
        try {
            return usuarioBO.buscarPorId(id);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible buscar por id: "+e.getMessage());
        }
    }
    
    public List<UsuarioDTO> listarUsuarios()throws ControllerException{
        try {
            return usuarioBO.listarUsuarios();
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible listar usuarios: "+e.getMessage());
        }
    }
    
    public List<UsuarioDTO> listarClientes()throws ControllerException{
        try {
            return usuarioBO.listarClientes();
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible listar los clientes: "+e.getMessage());
        }
    }
    
    public List<UsuarioDTO> listarUsuariosPaginado(int pagina, int limite) throws ControllerException{
        try {
            return usuarioBO.listarUsuariosPaginado(pagina, limite);
        } catch (NegocioException e) {
            throw new ControllerException("No fue posible paginar usuarios: "+e.getMessage());
        }
    }
}
