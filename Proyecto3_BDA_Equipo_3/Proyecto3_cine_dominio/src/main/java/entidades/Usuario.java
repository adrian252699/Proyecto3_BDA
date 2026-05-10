/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public class Usuario {
    private ObjectId _id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private LocalDate fch_nacimiento;
    

    public Usuario() {
    }

    public Usuario(ObjectId _id, String nombre, String email, String telefono) {
        this._id = _id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" + "_id=" + _id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    
}
