/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.PeliculaDTO;
import entidades.Pelicula;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class PeliculaMapper {
    /*
        Esta clase solo transforma objetos de Entidad a DTO y viceversa para no usar datos
        de la capa dominio en presentacion
    */
    public Pelicula toEntity(PeliculaDTO peliculaDTO){
        if (peliculaDTO==null) {
            return null;
        }
        
        Pelicula pelicula = new Pelicula();
        
        if (peliculaDTO.getId() != null && !peliculaDTO.getId().trim().isEmpty()) {
            pelicula.setId(new ObjectId(peliculaDTO.getId()));
        }
        
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setGeneros(peliculaDTO.getGeneros());
        pelicula.setDuracion(peliculaDTO.getDuracion());
        pelicula.setClasificacion(peliculaDTO.getClasificacion());
        
        return pelicula;
    }
    
    public PeliculaDTO toDTO(Pelicula pelicula){
        if (pelicula==null) {
            return null;
        }
        
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        
        if (pelicula.getId() != null) {
            peliculaDTO.setId(pelicula.getId().toString());
        }
        
        peliculaDTO.setTitulo(pelicula.getTitulo());
        peliculaDTO.setGeneros(pelicula.getGeneros());
        peliculaDTO.setDuracion(pelicula.getDuracion());
        peliculaDTO.setClasificacion(pelicula.getClasificacion());
        
        return peliculaDTO;
    }
    
    public List<PeliculaDTO> toDTOList(List<Pelicula> listaPeliculas){
        List<PeliculaDTO> listaPeliculasDTO = new ArrayList<>();
        
        for (Pelicula pelicula : listaPeliculas) {
            listaPeliculasDTO.add(this.toDTO(pelicula));
        }
        
        return listaPeliculasDTO;
    }
    
    public List<Pelicula> toEntityList(List<PeliculaDTO> lsitaPeliculasDTO){
        List<Pelicula> listaPelioulas = new ArrayList<>();
        
        for (PeliculaDTO peliculaDTO : lsitaPeliculasDTO) {
            listaPelioulas.add(this.toEntity(peliculaDTO));
        }
        
        return listaPelioulas;
    }
}
