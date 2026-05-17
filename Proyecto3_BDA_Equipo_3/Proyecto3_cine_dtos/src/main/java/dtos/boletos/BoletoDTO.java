/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos.boletos;

import dto.salas.AsientoDTO;
import enums.EstadoBoleto;
import java.time.LocalDateTime;

/**
 *
 * @author jalt2
 */
public class BoletoDTO {
    private String id;
    private EstadoBoleto estadoBoleto;
    private String usuarioId;
    private String funcionId;
    private Double precio;
    private String folio;
    private AsientoDTO asiento;
    private LocalDateTime fechaCompra;

    public BoletoDTO() {
    }

    public BoletoDTO(String id, EstadoBoleto estadoBoleto, String usuarioId, String funcionId, Double precio, String folio, AsientoDTO asiento, LocalDateTime fechaCompra) {
        this.id = id;
        this.estadoBoleto = estadoBoleto;
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.precio = precio;
        this.folio = folio;
        this.asiento = asiento;
        this.fechaCompra = fechaCompra;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EstadoBoleto getEstadoBoleto() {
        return estadoBoleto;
    }

    public void setEstadoBoleto(EstadoBoleto estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(String funcionId) {
        this.funcionId = funcionId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public AsientoDTO getAsiento() {
        return asiento;
    }

    public void setAsiento(AsientoDTO asiento) {
        this.asiento = asiento;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "BoletoDTO{" + "id=" + id + ", estadoBoleto=" + estadoBoleto + ", usuarioId=" + usuarioId + ", funcionId=" + funcionId + ", precio=" + precio + ", folio=" + folio + ", asiento=" + asiento + ", fechaCompra=" + fechaCompra + '}';
    }
 
}
