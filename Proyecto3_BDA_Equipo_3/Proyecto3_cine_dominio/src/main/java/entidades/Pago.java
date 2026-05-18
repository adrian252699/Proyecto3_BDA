/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoPago;
import enums.MetodoPago;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Pago {
    private ObjectId _id;
    private ObjectId boletoId;
    private Double monto;
    private MetodoPago metodoPago;
    private LocalDate fechaPago;
    private EstadoPago estadoPago;

    public Pago() {
    }

    public Pago(ObjectId _id, ObjectId boletoId, Double monto, MetodoPago metodoPago, LocalDate fechaPago, EstadoPago estadoPago) {
        this._id = _id;
        this.boletoId = boletoId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estadoPago = estadoPago;
    }

    public Pago(ObjectId boletoId, Double monto, MetodoPago metodoPago, LocalDate fechaPago, EstadoPago estadoPago) {
        this.boletoId = boletoId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estadoPago = estadoPago;
    }
    
    

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(ObjectId boletoId) {
        this.boletoId = boletoId;
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

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    @Override
    public String toString() {
        return "Pago{" + "_id=" + _id + ", boletoId=" + boletoId + ", monto=" + monto + ", metodoPago=" + metodoPago + ", fechaPago=" + fechaPago + ", estadoPago=" + estadoPago + '}';
    }
    
    
}
