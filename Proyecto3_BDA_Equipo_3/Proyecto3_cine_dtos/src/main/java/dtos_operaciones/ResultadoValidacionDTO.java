/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos_operaciones;

/**
 *
 * @author jalt2
 */
public class ResultadoValidacionDTO {
    private Boolean valido;
    private String mensaje;

    public ResultadoValidacionDTO() {
    }

    public ResultadoValidacionDTO(Boolean valido, String mensaje) {
        this.valido = valido;
        this.mensaje = mensaje;
    }
    
    public Boolean getValido() {
        return valido;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ResultadoValidacionDTO{" + "valido=" + valido + ", mensaje=" + mensaje + '}';
    }
    
    
}
