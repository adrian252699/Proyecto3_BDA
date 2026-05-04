/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos_operaciones;

/**
 *
 * @author jalt2
 */
public class ValidacionQRDTO {
    private String codigoQR;

    public ValidacionQRDTO() {
    }

    public ValidacionQRDTO(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    @Override
    public String toString() {
        return "ValidacionQRDTO{" + "codigoQR=" + codigoQR + '}';
    }
    
    
}
