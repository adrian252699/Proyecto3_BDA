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
public class PagoDTO {
    private String id;
    private Double monto;
    private String metodoPago;
    private String estadoPago;
    private LocalDate fechaPago;

    public PagoDTO() {
    }

    public PagoDTO(String id, Double monto, String metodoPago, String estadoPago, LocalDate fechaPago) {
        this.id = id;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.fechaPago = fechaPago;
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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
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
