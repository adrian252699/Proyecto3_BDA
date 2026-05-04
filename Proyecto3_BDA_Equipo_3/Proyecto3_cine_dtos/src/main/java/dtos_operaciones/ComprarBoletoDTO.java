/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos_operaciones;

/**
 *
 * @author jalt2
 */
public class ComprarBoletoDTO {
    //Datos necesarios para comprar boleto
    private String idUsuario;
    private String idFuncion;
    private String fila;
    private Integer numero;
    private String metodoPago;

    public ComprarBoletoDTO() {
    }

    public ComprarBoletoDTO(String idUsuario, String idFuncion, String fila, Integer numero, String metodoPago) {
        this.idUsuario = idUsuario;
        this.idFuncion = idFuncion;
        this.fila = fila;
        this.numero = numero;
        this.metodoPago = metodoPago;
    }
    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(String idFuncion) {
        this.idFuncion = idFuncion;
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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "ComprarBoletoDTO{" + "idUsuario=" + idUsuario + ", idFuncion=" + idFuncion + ", fila=" + fila + ", numero=" + numero + ", metodoPago=" + metodoPago + '}';
    }
    
    
}
