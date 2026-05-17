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
public class RegistrarFuncionDTO {
    private String peliculaId;
    
    private String tituloPelicula;

    private Integer numSala;

    private LocalDate fecha;

    private LocalTime hora;

    public RegistrarFuncionDTO() {
    }

    public RegistrarFuncionDTO(String peliculaId, Integer numSala, LocalDate fecha, LocalTime hora) {
        this.peliculaId = peliculaId;
        this.numSala = numSala;
        this.fecha = fecha;
        this.hora = hora;
    }

    public RegistrarFuncionDTO(String peliculaId, String tituloPelicula, Integer numSala, LocalDate fecha, LocalTime hora) {
        this.peliculaId = peliculaId;
        this.tituloPelicula = tituloPelicula;
        this.numSala = numSala;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
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
