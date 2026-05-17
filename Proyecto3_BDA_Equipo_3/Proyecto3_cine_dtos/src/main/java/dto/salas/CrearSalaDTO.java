/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.salas;

import java.util.List;

/**
 *
 * @author jalt2
 */
public class CrearSalaDTO {
    private Integer numSala;
    private Integer capacidad;
    private List<AsientoDTO> asientos;

    public CrearSalaDTO() {
    }

    public CrearSalaDTO(Integer numSala, Integer capacidad, List<AsientoDTO> asientos) {
        this.numSala = numSala;
        this.capacidad = capacidad;
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

    public List<AsientoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }
    
    
}
