/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.funciones.ActualizarFuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import dto.salas.AsientoDTO;
import dto.salas.CrearSalaDTO;
import dto.salas.SalaDTO;
import embebidos.Asiento;
import embebidos.Sala;
import entidades.Funcion;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class SalaMapper {
    private SalaMapper(){
    }
    
    /**
     * Convierte una entidad Funcion a FuncionDTO
     * @param sala
     * @return 
     */
    public static SalaDTO toDTO( Sala sala ) {

        if (sala == null) {
            return null;
        }

        SalaDTO dto = new SalaDTO();

        dto.setId(
                sala.getId().toHexString()
        );

        dto.setAsientos(toAsientoDTOList(sala.getAsientos()));
        dto.setCapacidad(sala.getCapacidad());
        dto.setNumSala(sala.getNumSala());

        return dto;
    }
    
    public static AsientoDTO toAsientoDTO(Asiento asiento){
        if (asiento==null) {
            return null;
        }
        
        AsientoDTO dto = new AsientoDTO();
        
        dto.setDisponible(asiento.getDisponible());
        dto.setFila(asiento.getFila());
        dto.setNumero(asiento.getNumAsiento());
        
        return dto;
    }

    /**
     * Convierte RegistrarFuncionDTO a entidad Funcion
     * @param dto
     * @return 
     */
    public static Sala toEntity(
            CrearSalaDTO dto
    ) {

        if (dto == null) {
            return null;
        }

        Sala sala = new Sala();

        sala.setNumSala(
                dto.getNumSala()
        );

        sala.setAsientos(toAsientoEntityList(dto.getAsientos()));
        
        sala.setCapacidad(dto.getCapacidad());


        return sala;
    }
    
    public static Sala toEntity(
            SalaDTO dto
    ) {

        if (dto == null) {
            return null;
        }

        Sala sala = new Sala();

        sala.setNumSala(
                dto.getNumSala()
        );

        sala.setAsientos(toAsientoEntityList(dto.getAsientos()));
        
        sala.setCapacidad(dto.getCapacidad());


        return sala;
    }
    
    public static Asiento toAsientoEntity(
            AsientoDTO dto
    ) {

        if (dto == null) {
            return null;
        }

        Asiento asiento = new Asiento();

        asiento.setDisponible(dto.getDisponible());
        asiento.setFila(dto.getFila());
        asiento.setNumAsiento(dto.getNumero());


        return asiento;
    }

    public static List<Sala> toEntityList(List<SalaDTO> listaSalaDTO){
        List<Sala> listaEntidad = new ArrayList<>();
        
        for (SalaDTO salaDTO : listaSalaDTO) {
            listaEntidad.add(toEntity(salaDTO));
        }
        
        return listaEntidad;
    }
    
    public static List<Asiento> toAsientoEntityList(List<AsientoDTO> listaAsientoDTO){
        List<Asiento> listaAsiento = new ArrayList<>();
        
        for (AsientoDTO asientoDTO : listaAsientoDTO) {
            listaAsiento.add(toAsientoEntity(asientoDTO));
        }
        
        return listaAsiento;
    }
    
    public static List<AsientoDTO> toAsientoDTOList(
        List<Asiento> asientos
    ) {

        List<AsientoDTO> listaDTO = new ArrayList<>();

        for (Asiento asiento : asientos) {
            listaDTO.add(toAsientoDTO(asiento));
        }
        return listaDTO;
    }

    /**
     * Convierte lista de entidades a lista DTO
     * @param salas
     * @return 
     */
    public static List<SalaDTO> toDTOList(
        List<Sala> salas
    ) {

        List<SalaDTO> listaDTO =
                new ArrayList<>();

        for (Sala sala : salas) {

            listaDTO.add(
                    toDTO(sala)
            );
        }

        return listaDTO;
    }
    

}
