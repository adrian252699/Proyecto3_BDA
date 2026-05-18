/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import embebidos.Asiento;
import embebidos.Sala;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Funcion {
    private ObjectId _id;
    private ObjectId peliculaId;
    private String tituloPelicula;
    private Sala sala;
    private LocalDate fecha;
    private LocalTime hora;
    private List<Asiento> asientos;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean activo;

    public Funcion() {
    }

    public Funcion(ObjectId _id, ObjectId peliculaId, String tituloPelicula, Sala sala, LocalDate fecha, LocalTime hora, List<Asiento> asientos, Instant createdAt, Instant updatedAt, boolean activo) {
        this._id = _id;
        this.peliculaId = peliculaId;
        this.tituloPelicula = tituloPelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.asientos = asientos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(ObjectId peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    

    @Override
    public String toString() {
        return "Funcion{" + "_id=" + _id + ", peliculaId=" + peliculaId + ", sala=" + sala + ", fecha=" + fecha + ", hora=" + hora + ", asientos=" + asientos + '}';
    }
}
