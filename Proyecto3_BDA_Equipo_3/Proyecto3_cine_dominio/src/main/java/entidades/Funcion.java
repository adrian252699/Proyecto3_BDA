/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import embebidos.Asiento;
import embebidos.Sala;
import java.time.LocalDate;
import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Funcion {
    private ObjectId _id;
    private ObjectId peliculaId;
    private Sala sala;
    private LocalDate fecha;
    private String hora;
    private ArrayList<Asiento> asientos;

    public Funcion() {
    }

    public Funcion(ObjectId _id, ObjectId peliculaId, Sala sala, LocalDate fecha, String hora, ArrayList<Asiento> asientos) {
        this._id = _id;
        this.peliculaId = peliculaId;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.asientos = asientos;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ArrayList<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<Asiento> asientos) {
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        return "Funcion{" + "_id=" + _id + ", peliculaId=" + peliculaId + ", sala=" + sala + ", fecha=" + fecha + ", hora=" + hora + ", asientos=" + asientos + '}';
    }
    
    
    
}
