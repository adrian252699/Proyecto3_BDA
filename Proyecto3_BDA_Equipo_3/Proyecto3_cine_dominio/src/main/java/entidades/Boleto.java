/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import embebidos.Asiento;
import enums.EstadoBoleto;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Boleto {
    private ObjectId _id;
    private ObjectId usuarioId;
    private ObjectId funcionId;
    private Double precio;
    private String folio;
    private Integer numeroBoleto;
    private Boolean usado;
    private Asiento asiento;
    private EstadoBoleto estadoBoleto;
    private LocalDateTime fechaCompra;

    public Boleto() {
    }

    public Boleto(ObjectId _id, ObjectId usuarioId, ObjectId funcionId, Double precio, String folio, Integer numeroBoleto, Boolean usado, Asiento asiento, EstadoBoleto estadoBoleto, LocalDateTime fechaCompra) {
        this._id = _id;
        this.usuarioId = usuarioId;
        this.funcionId = funcionId;
        this.precio = precio;
        this.folio = folio;
        this.numeroBoleto = numeroBoleto;
        this.usado = usado;
        this.asiento = asiento;
        this.estadoBoleto = estadoBoleto;
        this.fechaCompra = fechaCompra;
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

    public Integer getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(Integer numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public Boolean getUsado() {
        return usado;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
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

    public EstadoBoleto getEstadoBoleto() {
        return estadoBoleto;
    }

    public void setEstadoBoleto(EstadoBoleto estadoBoleto) {
        this.estadoBoleto = estadoBoleto;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Boleto{" + "_id=" + _id + ", usuarioId=" + usuarioId + ", funcionId=" + funcionId + ", asiento=" + asiento + ", estadoBoleto=" + estadoBoleto + ", fechaCompra=" + fechaCompra + '}';
    }
}
