/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.salas.AsientoDTO;
import dtos.boletos.BoletoDTO;
import dtos.boletos.PagoDTO;
import excepciones.negocio.NegocioException;

/**
 *
 * @author jalt2
 */
public interface IBoletoBO {
    BoletoDTO reservarAsiento(String usuarioId, String funcionId, AsientoDTO asiento) throws NegocioException;
    void confirmarPago(String boletoId, PagoDTO pago) throws NegocioException;
}
