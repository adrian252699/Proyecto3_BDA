/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.ArrayList;

/**
 *
 * @author jalt2
 */
public class PeliculaDTO {
    private String id;
    private String titulo;
    private ArrayList<String> generos;
    private Double duracion;
    private String clasificacion;

    public PeliculaDTO() {
    }

    public PeliculaDTO(String id, String titulo, ArrayList<String> generos, Double duracion, String clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.generos = generos;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {
        return "PeliculaDTO{" + "id=" + id + ", titulo=" + titulo + ", generos=" + generos + ", duracion=" + duracion + ", clasificacion=" + clasificacion + '}';
    }
    
    
}
