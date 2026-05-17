/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public interface IUsuarioDAO {
    public Usuario guardarUsuario(Usuario usuario)throws DaoException;
    
    public Usuario actualizarUsuario(Usuario usuario)throws DaoException, EntityNotFoundException;
    
    public Usuario actualizarPassword(ObjectId id, String passwordHash)throws DaoException,EntityNotFoundException;
    
    public Usuario actualizarCorreo(ObjectId id, String correo)throws DaoException,EntityNotFoundException;
    
    public boolean desactivarUsuario(ObjectId id)throws DaoException;
    
    public boolean activarUsuario(ObjectId id)throws DaoException;
    
    public Usuario buscarPorCorreo(String correo)throws DaoException,EntityNotFoundException;
    
    public Usuario buscarPorId(ObjectId id)throws DaoException,EntityNotFoundException;
    
    public boolean existeCorreo(String correo)throws DaoException;
    
    public List<Usuario> listarUsuarios()throws DaoException;
    
    public List<Usuario> listarClientes()throws DaoException;
    
    public List<Usuario> listarUsuariosPaginado(int pagina, int limite)throws DaoException;
}
