/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;

/**
 *
 * @author jalt2
 */
public class FuncionDTO {
    private String id;
    private String tituloPelicula;
    private LocalDate fecha;
    private String hora;
    private Integer numSala;

    public FuncionDTO() {
    }

    public FuncionDTO(String id, String tituloPelicula, LocalDate fecha, String hora, Integer numSala) {
        this.id = id;
        this.tituloPelicula = tituloPelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.numSala = numSala;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
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

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    @Override
    public String toString() {
        return "FuncionDTO{" + "id=" + id + ", tituloPelicula=" + tituloPelicula + ", fecha=" + fecha + ", hora=" + hora + ", numSala=" + numSala + '}';
    }
    
    
}
