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
public class BoletoDTO {
    private String id;
    private String peliculaTitulo;
    private LocalDate fecha;
    private String hora;
    private String fila;
    private Integer numero;
    private String estadoBoleto;

    public BoletoDTO() {
    }

    public BoletoDTO(String id, String peliculaTitulo, LocalDate fecha, String hora, String fila, Integer numero, String estadoBoleto) {
        this.id = id;
        this.peliculaTitulo = peliculaTitulo;
        this.fecha = fecha;
        this.hora = hora;
        this.fila = fila;
        this.numero = numero;
        this.estadoBoleto = estadoBoleto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeliculaTitulo() {
        return peliculaTitulo;
    }

    public void setPeliculaTitulo(String peliculaTitulo) {
        this.peliculaTitulo = peliculaTitulo;
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

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstadoBoleto() {
        return estadoBoleto;
    }

    public void setEstadoBoleto(String estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    @Override
    public String toString() {
        return "BoletoDTO{" + "id=" + id + ", peliculaTitulo=" + peliculaTitulo + ", fecha=" + fecha + ", hora=" + hora + ", fila=" + fila + ", numero=" + numero + ", estadoBoleto=" + estadoBoleto + '}';
    }
    
    
}
