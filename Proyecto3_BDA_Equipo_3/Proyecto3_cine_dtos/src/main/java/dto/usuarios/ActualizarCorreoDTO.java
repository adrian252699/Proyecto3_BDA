/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.usuarios;

/**
 *
 * @author jalt2
 */
public class ActualizarCorreoDTO {
    private String nuevoCorreo;

    private String passwordActual;

    public ActualizarCorreoDTO() {
    }

    public ActualizarCorreoDTO(String nuevoCorreo, String passwordActual) {
        this.nuevoCorreo = nuevoCorreo;
        this.passwordActual = passwordActual;
    }

    public String getNuevoCorreo() {
        return nuevoCorreo;
    }

    public void setNuevoCorreo(String nuevoCorreo) {
        this.nuevoCorreo = nuevoCorreo;
    }

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }
    
    
}
