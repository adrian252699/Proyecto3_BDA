/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import dto.usuarios.UsuarioDTO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jalt2
 */
public class ModeloTablaUsuarios extends AbstractTableModel{
    private List<UsuarioDTO> usuarios;
    
   
    
    private final String[] columnas = {
        "Nombre",
        "Apellido Paterno",
        "Apellido Materno",
        "Rol",
        "Correo",
        "Telefono",
        "Fecha de Nacimiento",
        "Activado",
        "CreatedAt"
    };
    
    public ModeloTablaUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }
    
    public UsuarioDTO obtenerUsuario(int fila) {
        return usuarios.get(fila);
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UsuarioDTO usuario = usuarios.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> usuario.getNombre();
            case 1 -> usuario.getApellidoPaterno();
            case 2 -> usuario.getApellidoMaterno();
            case 3 -> usuario.getRol();
            case 4 -> usuario.getEmail();
            case 5 -> usuario.getTelefono();
            case 6 -> usuario.getFechaNacimiento();
            case 7 -> usuario.getActivo();
            case 8 -> usuario.getCreatedAt();
            default -> null;
        };
    }
    
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setFunciones(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged();
    }
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
