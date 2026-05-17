/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.salas.SalaDTO;
import dto.salas.AsientoDTO;
import dto.salas.CrearSalaDTO;
import excepciones.negocio.NegocioException;
import java.util.List;

/**
 *
 * @author jalt2
 */
public interface ISalaBO {
    public SalaDTO crearSala(CrearSalaDTO sala)throws NegocioException;
    public SalaDTO modificarSala(CrearSalaDTO salaModificar)throws NegocioException;
    public List<SalaDTO> listarSalas()throws NegocioException;
    public List<AsientoDTO> listarAsientosDisponibles(Integer numSala)throws NegocioException;
    public List<AsientoDTO> listarAsientosOcupados(Integer numSala)throws NegocioException;
}
