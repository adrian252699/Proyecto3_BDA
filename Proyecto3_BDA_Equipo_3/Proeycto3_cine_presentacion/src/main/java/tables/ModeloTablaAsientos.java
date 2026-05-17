/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import dto.salas.AsientoDTO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jalt2
 */
public class ModeloTablaAsientos extends AbstractTableModel{
    private List<AsientoDTO> asientos;
    
    private final String[] columnas = {
        "Fila",
        "Numero",
        "Disponible"
    };

    public ModeloTablaAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }
    
    public AsientoDTO obtenerAsiento(int fila) {
        return asientos.get(fila);
    }
    
    @Override
    public int getRowCount() {
        return asientos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AsientoDTO asiento = asientos.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> asiento.getFila();
            case 1 -> asiento.getNumero();
            case 2 -> asiento.getDisponible();
            default -> null;
        };
    }
    
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }

    public List<AsientoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
        fireTableDataChanged();
    }
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
