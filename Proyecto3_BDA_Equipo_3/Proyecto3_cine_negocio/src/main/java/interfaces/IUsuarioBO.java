/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.usuarios.ActualizarPasswordDTO;
import dto.usuarios.ActualizarUsuarioDTO;
import dto.usuarios.LoginDTO;
import dto.usuarios.RegistroUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import excepciones.negocio.NegocioException;
import java.util.List;

/**
 *
 * @author jalt2
 */
public interface IUsuarioBO {
    public UsuarioDTO guardarUsuario(RegistroUsuarioDTO usuario)throws NegocioException;
    
    public UsuarioDTO iniciarSesion(LoginDTO usuarioLogin)throws NegocioException;
    
    public UsuarioDTO actualizarUsuario(ActualizarUsuarioDTO usuario)throws NegocioException;
    
    public UsuarioDTO actualizarPassword(String id, ActualizarPasswordDTO password)throws NegocioException;
    
    public UsuarioDTO actualizarCorreo(String id, String correo)throws NegocioException;
    
    public boolean desactivarUsuario(String id)throws NegocioException;
    
    public boolean activarUsuario(String id)throws NegocioException;
    
    public UsuarioDTO buscarPorCorreo(String correo)throws NegocioException;
    
    public UsuarioDTO buscarPorId(String id)throws NegocioException;
    
    public boolean existeCorreo(String correo)throws NegocioException;
    
    public List<UsuarioDTO> listarUsuarios()throws NegocioException;
    
    public List<UsuarioDTO> listarUsuariosPaginado(int pagina, int limite)throws NegocioException;
}
