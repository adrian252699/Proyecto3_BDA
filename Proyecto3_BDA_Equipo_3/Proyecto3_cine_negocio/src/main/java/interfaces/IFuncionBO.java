/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.funciones.ActualizarFuncionDTO;
import dto.funciones.FuncionDTO;
import dto.funciones.RegistrarFuncionDTO;
import excepciones.negocio.NegocioException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jalt2
 */
public interface IFuncionBO {
    public FuncionDTO guardarFuncion(RegistrarFuncionDTO funcion) throws NegocioException;

    public FuncionDTO actualizarFuncion(ActualizarFuncionDTO funcion) throws NegocioException;

    public boolean desactivarFuncion(String id) throws NegocioException;

    public FuncionDTO buscarPorId(String id) throws NegocioException;

    public List<FuncionDTO> listarFunciones() throws NegocioException;

    public List<FuncionDTO> listarFuncionesPaginado(int pagina,int limite) throws NegocioException;

    public List<FuncionDTO> buscarFuncionesPorPelicula(String peliculaId) throws NegocioException;

    public List<FuncionDTO> buscarFuncionesPorFecha(LocalDate fecha) throws NegocioException;

    public List<FuncionDTO> buscarFuncionesPorPeliculaYFecha(String peliculaId, LocalDate fecha) throws NegocioException;
    
    List<FuncionDTO> buscarFuncionesActivas()throws NegocioException; 
}
