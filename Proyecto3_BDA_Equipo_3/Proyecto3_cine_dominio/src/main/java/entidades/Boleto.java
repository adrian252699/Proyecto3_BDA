/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import embebidos.Asiento;
import embebidos.CodigoQR;
import enums.EstadoBoleto;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Boleto {
    private ObjectId _id;
    private ObjectId usuarioId;
    private ObjectId funcionId;
    private Asiento asiento;
    private CodigoQR codigoQR;
    private EstadoBoleto estadoBoleto;
    private LocalDate fechaCompra;

    public Boleto() {
    }

    public Boleto(ObjectId _id, ObjectId usuarioId, ObjectId funcionId, Asiento asiento, CodigoQR codigoQR, EstadoBoleto estadoBoleto, LocalDate fechaCompra) {
        this._id = _id;
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.asiento = asiento;
        this.codigoQR = codigoQR;
        this.estadoBoleto = estadoBoleto;
        this.fechaCompra = fechaCompra;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(ObjectId usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ObjectId getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(ObjectId funcionId) {
        this.funcionId = funcionId;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public CodigoQR getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(CodigoQR codigoQR) {
        this.codigoQR = codigoQR;
    }

    public EstadoBoleto getEstadoBoleto() {
        return estadoBoleto;
    }

    public void setEstadoBoleto(EstadoBoleto estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Boleto{" + "_id=" + _id + ", usuarioId=" + usuarioId + ", funcionId=" + funcionId + ", asiento=" + asiento + ", codigoQR=" + codigoQR + ", estadoBoleto=" + estadoBoleto + ", fechaCompra=" + fechaCompra + '}';
    }
    
    
}
