/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import embebidos.Asiento;
import embebidos.Sala;
import excepciones.daos.DaoException;
import excepciones.daos.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author jalt2
 */
public interface ISalaDAO {
    public Sala crearSala(Sala sala)throws DaoException;
    public Sala modificarSala(Sala salaModificar)throws DaoException,EntityNotFoundException;
    public Sala buscarPorNumero(Integer numSala)throws DaoException,EntityNotFoundException;
    public List<Sala> listarSalas()throws DaoException;
    public List<Asiento> listarAsientosDisponibles(Integer numSala)throws DaoException;
    public List<Asiento> listarAsientosOcupados(Integer numSala)throws DaoException;
}
