/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import dto.funciones.FuncionDTO;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jalt2
 */
public class ModeloTablaFunciones extends AbstractTableModel{
    
    private List<FuncionDTO> funciones;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    
    private final String[] columnas = {
        "Pelicula",
        "Sala",
        "Fecha",
        "Hora"
    };
    
    public ModeloTablaFunciones(List<FuncionDTO> funciones) {
        this.funciones = funciones;
    }
    
    public FuncionDTO obtenerFuncion(int fila) {
        return funciones.get(fila);
    }

    @Override
    public int getRowCount() {
        return funciones.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FuncionDTO funcion = funciones.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return funcion.getTituloPelicula();

            case 1:
                return funcion.getNumSala();

            case 2:
                return funcion.getFecha();

            case 3:
                return funcion.getHora().format(formatter);

            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }

    public List<FuncionDTO> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<FuncionDTO> funciones) {
        this.funciones = funciones;
        fireTableDataChanged();
    }
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
