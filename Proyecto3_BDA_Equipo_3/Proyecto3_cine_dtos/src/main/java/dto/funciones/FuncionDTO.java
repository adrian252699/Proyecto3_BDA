/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.funciones;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author jalt2
 */
public class FuncionDTO {
    private String id;

    private String peliculaId;

    private Integer numSala;
    
    private Integer capacidadSala;

    private LocalDate fecha;

    private LocalTime hora;

    private Instant createdAt;

    private Instant updatedAt;

    public FuncionDTO() {
    }

    public FuncionDTO(String id, String peliculaId, Integer numSala, LocalDate fecha, LocalTime hora, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.peliculaId = peliculaId;
        this.numSala = numSala;
        this.fecha = fecha;
        this.hora = hora;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    
}
