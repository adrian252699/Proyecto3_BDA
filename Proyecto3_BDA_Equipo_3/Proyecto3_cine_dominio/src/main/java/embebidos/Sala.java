/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package embebidos;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Sala {
    private ObjectId id;
    private Integer numSala;
    private Integer capacidad;
    private List<Asiento> asientos;

    public Sala() {
    }

    public Sala(ObjectId id, Integer numSala, Integer capacidad, List<Asiento> asientos) {
        this.id = id;
        this.numSala = numSala;
        this.capacidad = capacidad;
        this.asientos = asientos;
    }
    
    
    
    public Sala(Integer numSala, Integer capacidad) {
        this.numSala = numSala;
        this.capacidad = capacidad;
    }

    public Sala(Integer numSala, Integer capacidad, List<Asiento> asientos) {
        this.numSala = numSala;
        this.capacidad = capacidad;
        this.asientos = asientos;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }
    

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Sala{" + "numSala=" + numSala + ", capacidad=" + capacidad + '}';
    }
    
    
}
