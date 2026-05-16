/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.funciones;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author jalt2
 */
public class ActualizarFuncionDTO {
    private String id;

    private String peliculaId;

    private Integer numSala;

    private Integer capacidadSala;

    private LocalDate fecha;

    private LocalTime hora;

    public ActualizarFuncionDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(String peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public Integer getCapacidadSala() {
        return capacidadSala;
    }

    public void setCapacidadSala(Integer capacidadSala) {
        this.capacidadSala = capacidadSala;
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
    
    
}
