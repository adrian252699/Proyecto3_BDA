/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package embebidos;

/**
 *
 * @author jalt2
 */
public class Asiento {
    private String fila;
    private Integer numAsiento;
    private Boolean disponible;

    public Asiento() {
    }

    public Asiento(String fila, Integer numAsiento, Boolean disponible) {
        this.fila = fila;
        this.numAsiento = numAsiento;
        this.disponible = disponible;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(Integer numAsiento) {
        this.numAsiento = numAsiento;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Asiento{" + "fila=" + fila + ", numAsiento=" + numAsiento + ", disponible=" + disponible + '}';
    }
    
    
}
