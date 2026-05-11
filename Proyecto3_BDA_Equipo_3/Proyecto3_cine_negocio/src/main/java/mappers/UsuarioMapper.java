/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.usuarios.ActualizarUsuarioDTO;
import dto.usuarios.RegistroUsuarioDTO;
import dto.usuarios.UsuarioDTO;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class UsuarioMapper {
    
    private UsuarioMapper() {
    }
    
    public static UsuarioDTO toDTO(Usuario usuario){
        if (usuario == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId().toHexString());

        dto.setNombre(usuario.getNombre());

        dto.setApellidoMaterno(usuario.getApellidoMaterno());

        dto.setApellidoPaterno(usuario.getApellidoPaterno());

        dto.setEmail(usuario.getEmail());

        dto.setTelefono(usuario.getTelefono());

        dto.setFechaNacimiento(usuario.getFechaNacimiento());

        dto.setRol(usuario.getRol());

        dto.setActivo(usuario.getActivo());

        dto.setCreatedAt(usuario.getCreatedAt());

        dto.setUpdatedAt(usuario.getUpdatedAt());

        dto.setUltimoLogin(usuario.getUltimoLogin());

        return dto;
    }
    
    public static Usuario toEntity(RegistroUsuarioDTO dto){
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(
                dto.getNombre()
        );

        usuario.setApellidoMaterno(
                dto.getApellidoMaterno()
        );

        usuario.setApellidoPaterno(
                dto.getApellidoPaterno()
        );

        usuario.setEmail(
                dto.getEmail()
        );

        usuario.setTelefono(
                dto.getTelefono()
        );

        usuario.setFechaNacimiento(
                dto.getFechaNacimiento()
        );

        return usuario;
    }
    
    public static Usuario toEntity(ActualizarUsuarioDTO dto){
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId(new ObjectId(dto.getId()));

        usuario.setNombre(dto.getNombre());

        usuario.setApellidoMaterno(dto.getApellidoMaterno());

        usuario.setApellidoPaterno(dto.getApellidoPaterno());

        usuario.setTelefono(dto.getTelefono());

        usuario.setFechaNacimiento(dto.getFechaNacimiento());

        return usuario;
    }
    
    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios){
        List<UsuarioDTO> listaDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            listaDTO.add(toDTO(usuario));
        }

        return listaDTO;
    }
}
