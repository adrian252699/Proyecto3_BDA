/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import dtos.PeliculaDTO;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jalt2
 */
public class ModeloTablaPeliculas extends AbstractTableModel {
    
    private List<PeliculaDTO> peliculas;
    
    private final String[] columnas = {
        "Título",
        "Géneros",
        "Duración",
        "Clasificación"
    };
    
    public ModeloTablaPeliculas(List<PeliculaDTO> peliculas) {
        this.peliculas = peliculas;
    }
    
    @Override
    public int getRowCount() {
        return peliculas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PeliculaDTO pelicula = peliculas.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return pelicula.getTitulo();

            case 1:
                return String.join(
                        ", ",
                        pelicula.getGeneros()
                );

            case 2:
                return pelicula.getDuracion();

            case 3:
                return pelicula.getClasificacion();

            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }

    public List<PeliculaDTO> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<PeliculaDTO> peliculas) {
        this.peliculas = peliculas;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
    
    
    
}
