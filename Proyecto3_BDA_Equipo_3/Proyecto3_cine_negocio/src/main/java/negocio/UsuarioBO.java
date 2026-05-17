/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dto.usuarios.ActualizarCorreoDTO;
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
import java.time.LocalDate;
import java.util.List;
import mappers.UsuarioMapper;
import org.bson.types.ObjectId;
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
    
    //Ya en ocntroller
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
    
    //Ya en ocntroller
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
    
    //Ya en ocntroller
    @Override
    public UsuarioDTO actualizarUsuario(ActualizarUsuarioDTO usuario) throws NegocioException {
        if (usuario==null) {
            throw new NegocioException("El usuario no puede estar vacio");
        }
        
        if (usuario.getId() == null || usuario.getId().trim().isEmpty()) {
            throw new NegocioException( "El id del usuario es obligatorio");
        }
        
        if (usuario.getPasswordActual() == null || usuario.getPasswordActual().trim().isEmpty()) {
            throw new NegocioException("La contraseña actual es obligatoria");
        }
        
        if (usuario.getNombre()== null || usuario.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre es obligatorio");
        }
        
        if (usuario.getNombre().trim().length() > 50){
            throw new NegocioException("La longitud maxima del nombre es 50 caracteres");
        }
        
        if (usuario.getApellidoPaterno()==null || usuario.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio");
        }
        
        if (usuario.getApellidoPaterno().trim().length() > 50) {
            throw new NegocioException("La longitud maxima del apellido paterno es 50 caracteres");
        }
        
        //Apellido materno (Opcional)
        if (usuario.getApellidoMaterno() != null && usuario.getApellidoMaterno().trim().length() > 50) {
            throw new NegocioException("La longitud maxima del apellido materno es 50 caracteres");
        }
        
        
        
        validarTelefono(usuario.getTelefono());
        
        if (usuario.getFechaNacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria");
        }
        
        //Fecha de nacimiento valida (una fecha despues de la actual)
        if (usuario.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser mayor a la actual");
        }
        
        //Edad minima de 13 años
        if (usuario.getFechaNacimiento().isAfter(LocalDate.now().minusYears(13))) {
            throw new NegocioException("El usuario debe ser mayor de 13 años");
        }
        
        String nombre = usuario.getNombre().trim();

        String apellidoPaterno = usuario.getApellidoPaterno().trim();

        String apellidoMaterno = usuario.getApellidoMaterno() == null ? null : usuario.getApellidoMaterno().trim();
        
        ObjectId objectId;
        
        try {
            objectId = new ObjectId(usuario.getId());
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Id inválido");
        }
        
        try {
            
            Usuario usuarioActual = usuarioDAO.buscarPorId(objectId);
            
            boolean passwordCorrecta = PasswordUtil.verificar(
                    usuario.getPasswordActual(), 
                    usuarioActual.getPasswordHash()
            );
            
            if (!passwordCorrecta) {
                throw new NegocioException("La contraseña actual es incorrecta");
            }
            
            usuario.setNombre(nombre);

            usuario.setApellidoPaterno(apellidoPaterno);

            usuario.setApellidoMaterno(apellidoMaterno);
            
            Usuario usuarioEntidad = UsuarioMapper.toEntity(usuario);
            Usuario usuarioActualizado = usuarioDAO.actualizarUsuario(usuarioEntidad);
            
            return UsuarioMapper.toDTO(usuarioActualizado);
        } catch (DaoException e) {
            throw new NegocioException("Error al actualizar usuario:", e);
        }catch (EntityNotFoundException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }   

    //Ya en ocntroller
    @Override
    public UsuarioDTO actualizarPassword(String id, ActualizarPasswordDTO password) throws NegocioException {
        if (id == null || id.trim().isEmpty()) {
            throw new NegocioException( "El id del usuario es obligatorio");
        }

        if (password == null) {
            throw new NegocioException( "Los datos de contraseña son obligatorios");
        }

        ObjectId objectId;

        try {
            objectId = new ObjectId(id);    
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Id inválido");
        }

        //Validar dto
        if (password.getPasswordActual() == null || password.getPasswordActual().trim().isEmpty()) {
            throw new NegocioException("La contraseña actual no puede estar vacia");
        }

        validarPassword(password.getPasswordNueva());
        
        try {
            //Validar passwordActual ingresada con la password del usuario
            Usuario usuarioActual = usuarioDAO.buscarPorId(objectId);
            
            boolean passwordCorrecta = PasswordUtil.verificar(
                    password.getPasswordActual(), 
                    usuarioActual.getPasswordHash()
            );
            
            if (!passwordCorrecta) {
                throw new NegocioException("La contraseña actual es incorrecta");
            }
            
            //vallidar que la password nueva no sea igual a la actual
            boolean mismaPassword = PasswordUtil.verificar(
                    password.getPasswordNueva(), 
                    usuarioActual.getPasswordHash()
            );
            
            if (mismaPassword) {
                throw new NegocioException("La contraseña nueva no puede ser igual a la actual");
            }
            
            String passwordHash = PasswordUtil.hash(password.getPasswordNueva());
            
            //Cambiar contraseña
            Usuario usuarioActualizado = usuarioDAO.actualizarPassword(objectId, passwordHash);
            
            return UsuarioMapper.toDTO(usuarioActualizado);
            
        } catch (DaoException e) {
            throw new NegocioException("Error al actualizar la contraseña:", e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }
    
    //Ya en ocntroller
    @Override
    public UsuarioDTO actualizarCorreo(String id, ActualizarCorreoDTO correoDTO) throws NegocioException {
        ObjectId objectId;
        
        if (id == null || id.trim().isEmpty()) {
            throw new NegocioException( "El id del usuario es obligatorio");
        }

        if (correoDTO == null) {
            throw new NegocioException("Los datos son obligatorios");
        }

        if (correoDTO.getPasswordActual() == null || correoDTO.getPasswordActual().trim().isEmpty()) {
            throw new NegocioException("La contraseña actual es obligatoria");
        }

        if (correoDTO.getNuevoCorreo() == null || correoDTO.getNuevoCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo es obligatorio");
        }

        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new NegocioException("Id inválido");
        }

        String correoNuevo = correoDTO.getNuevoCorreo().trim().toLowerCase();

        validarCorreoFormato(correoNuevo);
        
        try {
            Usuario usuarioActual = usuarioDAO.buscarPorId(objectId);
            
            boolean passwordCorrecta = PasswordUtil.verificar(
                    correoDTO.getPasswordActual().trim(), 
                    usuarioActual.getPasswordHash()
            );
            
            if (!passwordCorrecta) {
                throw new NegocioException("La contraseña actual es incorrecta");
            }
            
            if (usuarioActual.getEmail().equalsIgnoreCase(correoNuevo)) {
                throw new NegocioException("El nuevo correo no puede ser igual al actual");  
            }
            
            validarCorreoExistente(correoNuevo);
            
            Usuario usuarioActualizado = usuarioDAO.actualizarCorreo(objectId, correoNuevo);
            
            return UsuarioMapper.toDTO(usuarioActualizado);
            
        } catch (DaoException e) {
            throw new NegocioException("Error al actualizar el correo:", e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException(ex.getMessage());
        } 
    }
    
    //Ya en ocntroller
    @Override
    public boolean desactivarUsuario(String id, String password) throws NegocioException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new NegocioException( "El id del usuario es obligatorio");
            }
            
            if (password == null || password.trim().isEmpty()) {
                throw new NegocioException( "Contraseña requerida");
            }
            
            ObjectId objectId;
            
            try {
                objectId = new ObjectId(id);    
            } catch (IllegalArgumentException e) {
                throw new NegocioException("Id inválido");
            }
            
            Usuario usuarioActual = usuarioDAO.buscarPorId(objectId);
            
            boolean passwordCorrecta = PasswordUtil.verificar(
                    password, 
                    usuarioActual.getPasswordHash()
            );
            
            if (!passwordCorrecta) {
                throw new NegocioException("La contraseña actual es incorrecta");
            }
            
            return usuarioDAO.desactivarUsuario(objectId);
            
        }catch (DaoException e) {
            throw new NegocioException("Error al desactivar usuario:", e);
        }catch (EntityNotFoundException ex) {
            throw new NegocioException(ex.getMessage());
        } 
    }

    //Ya en ocntroller
    @Override
    public boolean activarUsuario(String id) throws NegocioException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new NegocioException( "El id del usuario es obligatorio");
            }
            
            ObjectId objectId;
            
            try {
                objectId = new ObjectId(id);    
            } catch (IllegalArgumentException e) {
                throw new NegocioException("Id inválido");
            }
            
            return usuarioDAO.activarUsuario(objectId);
            
        }catch (DaoException e) {
            throw new NegocioException("Error al activar usuario:", e);
        }
    }

    //Ya en ocntroller
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

    //Ya en ocntroller
    @Override
    public UsuarioDTO buscarPorId(String id) throws NegocioException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new NegocioException( "El id del usuario es obligatorio");
            }
            
            ObjectId objectId;
            
            try {
                objectId = new ObjectId(id);    
            } catch (IllegalArgumentException e) {
                throw new NegocioException("Id inválido");
            }
            
            Usuario usuarioEncontrado = usuarioDAO.buscarPorId(objectId);
            
            return UsuarioMapper.toDTO(usuarioEncontrado);
            
        } catch (DaoException e) {
            throw new NegocioException("Error al buscar por id:", e);
        } catch (EntityNotFoundException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public boolean existeCorreo(String correo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Ya en ocntroller
    @Override
    public List<UsuarioDTO> listarUsuarios() throws NegocioException {
        try {
            List<Usuario> listaUsuariosEntidad = usuarioDAO.listarUsuarios();
            
            return UsuarioMapper.toDTOList(listaUsuariosEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al listar usuarios:", e);
        }
    }

    //Ya en ocntroller
    @Override
    public List<UsuarioDTO> listarUsuariosPaginado(int pagina, int limite) throws NegocioException {
        try {
            List<Usuario> listaUsuariosEntidad = usuarioDAO.listarUsuariosPaginado(pagina, limite);
            
            return UsuarioMapper.toDTOList(listaUsuariosEntidad);
        } catch (DaoException e) {
            throw new NegocioException("Error al paginar usuarios:", e);
        }
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

    @Override
    public boolean desactivarUsuarioAdmin(String id) throws NegocioException {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new NegocioException( "El id del usuario es obligatorio");
            }
            
            ObjectId objectId;
            
            try {
                objectId = new ObjectId(id);    
            } catch (IllegalArgumentException e) {
                throw new NegocioException("Id inválido");
            }

            return usuarioDAO.desactivarUsuario(objectId);
            
        }catch (DaoException e) {
            throw new NegocioException("Error al desactivar usuario:", e);
        }
    }

    @Override
    public List<UsuarioDTO> listarClientes() throws NegocioException {
        try {
            List<Usuario> clientesEntidad = usuarioDAO.listarClientes();
            
            return UsuarioMapper.toDTOList(clientesEntidad);
        } catch (DaoException e) {
           throw new NegocioException("Error al listar clientes:", e);
        }
    }
    
    
}
