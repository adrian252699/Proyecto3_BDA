/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package embebidos;

import java.time.LocalDate;

/**
 *
 * @author jalt2
 */
public class CodigoQR {
    private String codigo;
    private LocalDate fechaGeneracion;

    public CodigoQR() {
    }

    public CodigoQR(String codigo, LocalDate fechaGeneracion) {
        this.codigo = codigo;
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    @Override
    public String toString() {
        return "CodigoQR{" + "codigo=" + codigo + ", fechaGeneracion=" + fechaGeneracion + '}';
    }
    
    
}
