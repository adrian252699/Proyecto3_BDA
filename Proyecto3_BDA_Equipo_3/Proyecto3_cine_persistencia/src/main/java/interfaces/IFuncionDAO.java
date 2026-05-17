/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Funcion;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public interface IFuncionDAO {
    public Funcion guardarFuncion(Funcion funcion) throws DaoException;

    public Funcion actualizarFuncion(Funcion funcion) throws DaoException, EntityNotFoundException;

    public boolean desactivarFuncion(ObjectId id) throws DaoException;

    public Funcion buscarPorId(ObjectId id) throws DaoException, EntityNotFoundException;

    public List<Funcion> listarFunciones() throws DaoException;

    public List<Funcion> listarFuncionesPaginado(int pagina,int limite) throws DaoException;

    public List<Funcion> buscarFuncionesPorPelicula(ObjectId peliculaId) throws DaoException;

    public List<Funcion> buscarFuncionesPorFecha(LocalDate fecha) throws DaoException;
    
    public List<Funcion> buscarFuncionesPorFechaHora(LocalDate fecha, LocalTime hora) throws DaoException;

    public List<Funcion> buscarFuncionesPorPeliculaYFecha(ObjectId peliculaId, LocalDate fecha) throws DaoException;

    public boolean existeFuncionEnSalaHorario(ObjectId funcionId,Integer numSala, LocalDate fecha, LocalTime hora) throws DaoException;
    
    public boolean existeFuncionEnSalaHorario(Integer numSala, LocalDate fecha, LocalTime hora) throws DaoException;

    public List<Funcion> buscarFuncionesActivas() throws DaoException;
    
    public List<Funcion> listarFuncionesPelicula()throws DaoException;
}
