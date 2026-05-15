/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.usuarios.ActualizarPasswordDTO;
import dto.usuarios.ActualizarUsuarioDTO;
import dto.usuarios.LoginDTO;
import dto.usuarios.RegistroUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import entidades.Usuario;
import enums.Rol;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import excepciones.negocio.NegocioException;
import interfaces.IUsuarioBO;
import interfaces.IUsuarioDAO;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.UsuarioMapper;
import utilerias.PasswordUtil;

/**
 *
 * @author jalt2
 */
public class UsuarioBO implements IUsuarioBO{
    private final IUsuarioDAO usuarioDAO;
    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String REGEX_TELEFONO = "^[0-9]{10}$";
    private static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{8,}$";

    public UsuarioBO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public UsuarioDTO guardarUsuario(RegistroUsuarioDTO usuarioDTO) throws NegocioException {
        //Validaciones de nombre y apellido
        validarNombreApellidos(usuarioDTO);
        
        //Validaciones de correo
        String correo = usuarioDTO.getEmail().trim().toLowerCase();
        validarCorreoFormato(correo);
        validarCorreoExistente(correo);
        
        //Validar fecha nacimiento
        if (usuarioDTO.getFechaNacimiento()==null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria");
        }

        //Fecha de nacimiento valida (una fecha despues de la actual)
        if (usuarioDTO.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser mayor a la actual");
        }
        
        //Edad minima de 13 años
        if (usuarioDTO.getFechaNacimiento().isAfter(LocalDate.now().minusYears(13))) {
            throw new NegocioException("El usuario debe ser mayor de 13 años");
        }
        
        //Validar telefono
        validarTelefono(usuarioDTO.getTelefono());
        
        //Validar password
        validarPassword(usuarioDTO.getPassword());

        try {
            Usuario usuarioEntidad = UsuarioMapper.toEntity(usuarioDTO);
            
            String passwordHash = PasswordUtil.hash(usuarioDTO.getPassword());
            
            usuarioEntidad.setEmail(correo);
            usuarioEntidad.setPasswordHash(passwordHash);
            usuarioEntidad.setRol(Rol.CLIENTE);
            usuarioEntidad.setActivo(true);
            
            Usuario usuarioGuardado = usuarioDAO.guardarUsuario(usuarioEntidad);
            
            return UsuarioMapper.toDTO(usuarioGuardado);
                    
        } catch (DaoException e) {
            throw new NegocioException("Error al guardar usuario:", e);
        }
    }

    @Override
    public UsuarioDTO iniciarSesion(LoginDTO usuarioLogin) throws NegocioException {
        //Validar DTO
        if (usuarioLogin == null) {
            throw new NegocioException("Datos de login requeridos");
        }

        //Validar Correo
        validarCorreoFormato(usuarioLogin.getEmail());
        String correo = usuarioLogin.getEmail().trim().toLowerCase();
        
        //Validar password
        if (usuarioLogin.getPassword() == null || usuarioLogin.getPassword().trim().isEmpty()) {
            throw new NegocioException("La contraseña es obligatoria");
        }
        
        try {
            Usuario usuarioEncontrado = usuarioDAO.buscarPorCorreo(correo);
            
            //Verificar si el usuario esta activo
            if (!usuarioEncontrado.getActivo()) {
                throw new NegocioException("Usuario desactivado");
            }
            
            //Comparar contraseñas
            boolean passwordCorrecta = PasswordUtil.verificar(
                    usuarioLogin.getPassword(), 
                    usuarioEncontrado.getPasswordHash()
            );
            
            if (!passwordCorrecta) {
                throw new NegocioException("Correo o contraseña incorrectos");
            }
            
            return UsuarioMapper.toDTO(usuarioEncontrado);
            
        } catch (DaoException e) {
            throw new NegocioException("Error al iniciar sesion:", e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException("Correo o contraseña incorrectos");
        }
    }

    @Override
    public UsuarioDTO actualizarUsuario(ActualizarUsuarioDTO usuario) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO actualizarPassword(String id, ActualizarPasswordDTO password) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO actualizarCorreo(String id, String correo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivarUsuario(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean activarUsuario(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO buscarPorCorreo(String correo) throws NegocioException {
        //Validaciones de correo
        correo = correo.trim().toLowerCase();
        validarCorreoFormato(correo);
        validarCorreoExistente(correo);
        
        try {
            Usuario usuarioEncontrado = usuarioDAO.buscarPorCorreo(correo);
            return UsuarioMapper.toDTO(usuarioEncontrado);
            
        } catch (DaoException e) {
            throw new NegocioException("Error al buscar usuario por correo:", e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException("No se encontro un usuario:", ex);
        }
    }

    @Override
    public UsuarioDTO buscarPorId(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existeCorreo(String correo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioDTO> listarUsuariosPaginado(int pagina, int limite) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Metodos auxiliares
    private void validarNombreApellidos(RegistroUsuarioDTO usuarioDTO) throws NegocioException{
        if (usuarioDTO==null) {
            throw new NegocioException("El usuario no puede estar vacio");
        }
        
        if (usuarioDTO.getNombre()== null || usuarioDTO.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre es obligatorio");
        }
        
        if (usuarioDTO.getNombre().length() > 50){
            throw new NegocioException("La longitud maxima del nombre es 50 caracteres");
        }
        
        if (usuarioDTO.getApellidoPaterno()==null || usuarioDTO.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio");
        }
        
        if (usuarioDTO.getApellidoPaterno().trim().length() > 50) {
            throw new NegocioException("La longitud maxima del apellido paterno es 50 caracteres");
        }
        
        //Apellido materno (Opcional)
        if (usuarioDTO.getApellidoMaterno() != null && usuarioDTO.getApellidoMaterno().trim().length() > 50) {
            throw new NegocioException("La longitud maxima del apellido materno es 50 caracteres");
        }
    }
    
    private void validarPassword(String password) throws NegocioException{
        if (password == null || password.trim().isEmpty()) {
            throw new NegocioException("La contraseña es obligatoria");
        }

        if (!password.matches(REGEX_PASSWORD)) {

            throw new NegocioException(
                    """
                    La contraseña debe tener:
                    - mínimo 8 caracteres
                    - una mayúscula
                    - una minúscula
                    - un número
                    - un carácter especial
                    """
            );
        }
        
    }
    
    private void validarTelefono(String telefono) throws NegocioException{
        //Validar numero de telefono
        if (telefono==null || telefono.trim().isEmpty()) {
            throw new NegocioException("El numero de telefono es obligatorio");
        }
        
        //Formato de numero de telefono
        telefono = telefono.trim();

        if (!telefono.matches(REGEX_TELEFONO)) {
            throw new NegocioException("El teléfono debe contener exactamente 10 dígitos numéricos");
        }
    }
    
    private void validarCorreoFormato(String correo) throws NegocioException{
        //Verificar que el correo no venga vacio
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo es obligatorio");
        }
        
        correo = correo.trim().toLowerCase();
        
        //Verificar el formato del correo
        if (!correo.matches(REGEX_EMAIL)) {
            throw new NegocioException("El formato del correo es inválido");
        }
    }
    
    private void validarCorreoExistente(String correo) throws NegocioException{
        //Verificar si existe el email
        try {
            if (usuarioDAO.existeCorreo(correo)) {
                throw new NegocioException("El correo ya está registrado");
            }
        } catch (DaoException e) {
            throw new NegocioException("No fue posible validar el correo", e);
        }
    }
    
    
}
