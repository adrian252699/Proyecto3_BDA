/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.salas;

/**
 *
 * @author jalt2
 */
public class AsientoDTO {
    private String fila;
    private Integer numero;
    private Boolean disponible;

    public AsientoDTO() {
    }

    public AsientoDTO(String fila, Integer numero, Boolean disponible) {
        this.fila = fila;
        this.numero = numero;
        this.disponible = disponible;
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

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "AsientoDTO{" + "fila=" + fila + ", numero=" + numero + ", disponible=" + disponible + '}';
    }
    
    
}
