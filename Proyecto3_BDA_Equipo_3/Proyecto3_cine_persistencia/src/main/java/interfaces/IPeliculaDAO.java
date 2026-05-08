/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Pelicula;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public interface IPeliculaDAO {
    public Pelicula guardarPelicula(Pelicula pelicula)throws DaoException;
    
    public Pelicula actualizarPelicula(Pelicula pelicula)throws DaoException, EntityNotFoundException;
    
    public boolean eliminarPelicula(ObjectId _id)throws DaoException, EntityNotFoundException;
    
    public Pelicula buscarPeliculaId(ObjectId _id)throws DaoException,EntityNotFoundException;
    
    public List<Pelicula> listarPeliculas()throws DaoException;
    
    public List<Pelicula> listarPeliculasPaginado(int pagina, int limite) throws DaoException;
    
    public List<Pelicula> buscarPorGenero(String genero)throws DaoException;

    public List<Pelicula> buscarPorClasificacion(String clasificacion)throws DaoException;
}
