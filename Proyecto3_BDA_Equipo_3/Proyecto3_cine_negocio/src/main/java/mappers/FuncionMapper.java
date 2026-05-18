/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.funciones.ActualizarFuncionDTO;
import dto.funciones.FuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import embebidos.Sala;
import entidades.Funcion;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class FuncionMapper {
    private FuncionMapper() {
    }

    /**
     * Convierte una entidad Funcion a FuncionDTO
     * @param funcion
     * @return 
     */
    public static FuncionDTO toDTO(
            Funcion funcion
    ) {

        if (funcion == null) {
            return null;
        }

        FuncionDTO dto = new FuncionDTO();

        dto.setId(
                funcion.getId().toHexString()
        );

        dto.setPeliculaId(
                funcion.getPeliculaId().toHexString()
        );

        dto.setTituloPelicula(funcion.getTituloPelicula());

        dto.setNumSala(
                funcion.getSala().getNumSala()
        );

        dto.setCapacidadSala(
                funcion.getSala().getCapacidad()
        );

        dto.setFecha(
                funcion.getFecha()
        );

        dto.setHora(
                funcion.getHora()
        );

        return dto;
    }

    /**
     * Convierte RegistrarFuncionDTO a entidad Funcion
     * @param dto
     * @return 
     */
    public static Funcion toEntity(
            RegistrarFuncionDTO dto
    ) {

        if (dto == null) {
            return null;
        }

        Funcion funcion = new Funcion();

        funcion.setPeliculaId(
                new ObjectId(dto.getPeliculaId())
        );

        Sala sala = new Sala();

        sala.setNumSala(
                dto.getNumSala()
        );

        funcion.setSala(sala);

        funcion.setFecha(
                dto.getFecha()
        );
        
        
        funcion.setTituloPelicula(dto.getTituloPelicula());

        funcion.setHora(
                dto.getHora()
        );

        return funcion;
    }
    
    public static Funcion toEntity(
            FuncionDTO dto
    ) {

        if (dto == null) {
            return null;
        }

        Funcion funcion = new Funcion();

        funcion.setPeliculaId(
                new ObjectId(dto.getPeliculaId())
        );

        Sala sala = new Sala();

        sala.setNumSala(
                dto.getNumSala()
        );

        funcion.setSala(sala);

        funcion.setFecha(
                dto.getFecha()
        );
        
        
        funcion.setTituloPelicula(dto.getTituloPelicula());

        funcion.setHora(
                dto.getHora()
        );

        return funcion;
    }

    /**
     * Convierte ActualizarFuncionDTO a entidad Funcion
     * @param dto
     * @return 
     */
    public static Funcion toEntity(
            ActualizarFuncionDTO dto
    ) {

        if (dto == null) {
            return null;
        }

        Funcion funcion = new Funcion();

        funcion.setId(
                new ObjectId(dto.getId())
        );

        funcion.setPeliculaId(
                new ObjectId(dto.getPeliculaId())
        );

        Sala sala = new Sala();

        sala.setNumSala(
                dto.getNumSala()
        );

        sala.setCapacidad(
                dto.getCapacidadSala()
        );

        funcion.setSala(sala);

        funcion.setFecha(
                dto.getFecha()
        );

        funcion.setHora(
                dto.getHora()
        );

        return funcion;
    }

    /**
     * Convierte lista de entidades a lista DTO
     * @param funciones
     * @return 
     */
    public static List<FuncionDTO> toDTOList(
        List<Funcion> funciones
    ) {

        List<FuncionDTO> listaDTO =
                new ArrayList<>();

        for (Funcion funcion : funciones) {

            listaDTO.add(
                    toDTO(funcion)
            );
        }

        return listaDTO;
    }
    
    
}
