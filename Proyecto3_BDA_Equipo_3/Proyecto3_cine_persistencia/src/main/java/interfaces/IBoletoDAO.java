/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import embebidos.Asiento;
import entidades.Boleto;
import enums.EstadoBoleto;
import excepciones.daos.DaoException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jalt2
 */
public interface IBoletoDAO {
    Boleto crearBoleto(Boleto boleto) throws DaoException;
    List<Boleto> obtenerPorFuncion(ObjectId funcionId);
    void actualizarEstado(ObjectId boletoId, EstadoBoleto estado) throws DaoException;
    boolean asientoOcupado(ObjectId funcionId, Asiento asiento);
    
    
}
