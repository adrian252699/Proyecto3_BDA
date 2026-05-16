/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.funciones;

/**
 *
 * @author jalt2
 */
public class SalaDTO {
    private Integer numSala;

    private Integer capacidad;

    public SalaDTO() {
    }

    public SalaDTO(Integer numSala, Integer capacidad) {
        this.numSala = numSala;
        this.capacidad = capacidad;
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
}
