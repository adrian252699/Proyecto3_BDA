/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Pelicula {
    private ObjectId _id;
    private String titulo;
    private ArrayList<String> generos;
    private Double duracion;
    private String clasificacion;

    public Pelicula() {
    }

    public Pelicula(ObjectId _id, String titulo, ArrayList<String> generos, Double duracion, String clasificacion) {
        this._id = _id;
        this.titulo = titulo;
        this.generos = generos;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
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
        return "Pelicula{" + "_id=" + _id + ", titulo=" + titulo + ", generos=" + generos + ", duracion=" + duracion + ", clasificacion=" + clasificacion + '}';
    }

    
    
    
}
