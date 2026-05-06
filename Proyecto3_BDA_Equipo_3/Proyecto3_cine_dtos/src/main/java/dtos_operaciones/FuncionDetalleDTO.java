/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos_operaciones;

import dtos.AsientoDTO;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jalt2
 */
public class FuncionDetalleDTO {
    //Para mostrar asientos
    private String id;
    private String peliculaTitulo;
    private LocalDate fecha;
    private String hora;
    private ArrayList<AsientoDTO> asientos;

    public FuncionDetalleDTO() {
    }

    public FuncionDetalleDTO(String id, String peliculaTitulo, LocalDate fecha, String hora, ArrayList<AsientoDTO> asientos) {
        this.id = id;
        this.peliculaTitulo = peliculaTitulo;
        this.fecha = fecha;
        this.hora = hora;
        this.asientos = asientos;
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

    public ArrayList<AsientoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<AsientoDTO> asientos) {
        this.asientos = asientos;
    }

    @Override
    public String toString() {
        return "FuncionDetalle{" + "id=" + id + ", peliculaTitulo=" + peliculaTitulo + ", fecha=" + fecha + ", hora=" + hora + ", asientos=" + asientos + '}';
    }
    
    
}
