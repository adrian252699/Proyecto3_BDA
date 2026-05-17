/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos.boletos;

import enums.EstadoPago;
import enums.MetodoPago;
import java.time.LocalDate;

/**
 *
 * @author jalt2
 */
public class PagoDTO {
    private String id;
    private String boletoId;
    private Double monto;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;
    private LocalDate fechaPago;

    public PagoDTO() {
    }

    public PagoDTO(String id, String boletoId, Double monto, MetodoPago metodoPago, EstadoPago estadoPago, LocalDate fechaPago) {
        this.id = id;
        this.boletoId = boletoId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
    }

    public String getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(String boletoId) {
        this.boletoId = boletoId;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "PagoDTO{" + "id=" + id + ", monto=" + monto + ", metodoPago=" + metodoPago + ", estadoPago=" + estadoPago + ", fechaPago=" + fechaPago + '}';
    }
    
    
}
