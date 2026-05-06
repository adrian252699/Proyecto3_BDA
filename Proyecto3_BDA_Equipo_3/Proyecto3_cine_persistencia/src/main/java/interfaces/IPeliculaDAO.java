/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Pelicula;
import excepciones.DaoException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public interface IPeliculaDAO {
    public Pelicula guardarPelicula(Pelicula pelicula)throws DaoException;
    
    public Pelicula actualizarPelicula(Pelicula pelicula)throws DaoException;
    
    public boolean eliminarPelicula(ObjectId _id)throws DaoException;
    
    public Pelicula buscarPeliculaId(ObjectId _id)throws DaoException;
    
    public List<Pelicula> listarPeliculas()throws DaoException;
}
