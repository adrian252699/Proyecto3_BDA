/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;

import dto.funciones.RegistrarFuncionDTO;
import dto.salas.AsientoDTO;
import excepciones.presentacion.ControllerException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jalt2
 */
public class InsertsFunciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ControllerException {
        // TODO code application logic here
        FuncionController control = new FuncionController();
        // =========================
    // ASIENTOS SALA 1
    // =========================
    List<AsientoDTO> asientosSala1 = new ArrayList<>();

    asientosSala1.add(new AsientoDTO("A", 1, true));
    asientosSala1.add(new AsientoDTO("A", 2, true));
    asientosSala1.add(new AsientoDTO("A", 3, false));
    asientosSala1.add(new AsientoDTO("A", 4, true));
    asientosSala1.add(new AsientoDTO("A", 5, true));

    asientosSala1.add(new AsientoDTO("B", 1, true));
    asientosSala1.add(new AsientoDTO("B", 2, false));
    asientosSala1.add(new AsientoDTO("B", 3, true));
    asientosSala1.add(new AsientoDTO("B", 4, true));
    asientosSala1.add(new AsientoDTO("B", 5, true));

    // =========================
    // FUNCION 1
    // =========================
    control.guardarFuncion(
        new RegistrarFuncionDTO(
            "665f1a001",
            "El Ultimo Guerrero",
            asientosSala1,
            1,
            LocalDate.of(2026, 5, 20),
            LocalTime.of(18, 30)
        )
    );

    // =========================
    // ASIENTOS SALA 2
    // =========================
    List<AsientoDTO> asientosSala2 = new ArrayList<>();

    asientosSala2.add(new AsientoDTO("A", 1, true));
    asientosSala2.add(new AsientoDTO("A", 2, true));
    asientosSala2.add(new AsientoDTO("A", 3, true));
    asientosSala2.add(new AsientoDTO("A", 4, true));
    asientosSala2.add(new AsientoDTO("A", 5, false));

    asientosSala2.add(new AsientoDTO("B", 1, true));
    asientosSala2.add(new AsientoDTO("B", 2, true));
    asientosSala2.add(new AsientoDTO("B", 3, true));
    asientosSala2.add(new AsientoDTO("B", 4, false));
    asientosSala2.add(new AsientoDTO("B", 5, true));

    // =========================
    // FUNCION 2
    // =========================
    control.guardarFuncion(
        new RegistrarFuncionDTO(
            "665f1a002",
            "Sombras del Terror",
            asientosSala2,
            2,
            LocalDate.of(2026, 5, 20),
            LocalTime.of(21, 0)
        )
    );

    // =========================
    // ASIENTOS SALA 3
    // =========================
    List<AsientoDTO> asientosSala3 = new ArrayList<>();

    asientosSala3.add(new AsientoDTO("A", 1, true));
    asientosSala3.add(new AsientoDTO("A", 2, true));
    asientosSala3.add(new AsientoDTO("A", 3, true));
    asientosSala3.add(new AsientoDTO("A", 4, true));
    asientosSala3.add(new AsientoDTO("A", 5, true));

    asientosSala3.add(new AsientoDTO("B", 1, true));
    asientosSala3.add(new AsientoDTO("B", 2, true));
    asientosSala3.add(new AsientoDTO("B", 3, false));
    asientosSala3.add(new AsientoDTO("B", 4, true));
    asientosSala3.add(new AsientoDTO("B", 5, true));

    // =========================
    // FUNCION 3
    // =========================
    control.guardarFuncion(
        new RegistrarFuncionDTO(
            "665f1a003",
            "Viaje Estelar",
            asientosSala3,
            3,
            LocalDate.of(2026, 5, 21),
            LocalTime.of(16, 45)
        )
    );

    // =========================
    // ASIENTOS SALA 4
    // =========================
    List<AsientoDTO> asientosSala4 = new ArrayList<>();

    asientosSala4.add(new AsientoDTO("A", 1, true));
    asientosSala4.add(new AsientoDTO("A", 2, false));
    asientosSala4.add(new AsientoDTO("A", 3, true));
    asientosSala4.add(new AsientoDTO("A", 4, true));
    asientosSala4.add(new AsientoDTO("A", 5, true));

    asientosSala4.add(new AsientoDTO("B", 1, true));
    asientosSala4.add(new AsientoDTO("B", 2, true));
    asientosSala4.add(new AsientoDTO("B", 3, true));
    asientosSala4.add(new AsientoDTO("B", 4, true));
    asientosSala4.add(new AsientoDTO("B", 5, false));

    // =========================
    // FUNCION 4
    // =========================
    control.guardarFuncion(
        new RegistrarFuncionDTO(
            "665f1a004",
            "Risas en Vacaciones",
            asientosSala4,
            4,
            LocalDate.of(2026, 5, 21),
            LocalTime.of(14, 15)
        )
    );

    // =========================
    // ASIENTOS SALA 5
    // =========================
    List<AsientoDTO> asientosSala5 = new ArrayList<>();

    asientosSala5.add(new AsientoDTO("A", 1, true));
    asientosSala5.add(new AsientoDTO("A", 2, true));
    asientosSala5.add(new AsientoDTO("A", 3, true));
    asientosSala5.add(new AsientoDTO("A", 4, false));
    asientosSala5.add(new AsientoDTO("A", 5, true));

    asientosSala5.add(new AsientoDTO("B", 1, true));
    asientosSala5.add(new AsientoDTO("B", 2, true));
    asientosSala5.add(new AsientoDTO("B", 3, true));
    asientosSala5.add(new AsientoDTO("B", 4, true));
    asientosSala5.add(new AsientoDTO("B", 5, true));

    // =========================
    // FUNCION 5
    // =========================
    control.guardarFuncion(
        new RegistrarFuncionDTO(
            "665f1a005",
            "Corazones Rotos",
            asientosSala5,
            5,
            LocalDate.of(2026, 5, 22),
            LocalTime.of(19, 45)
        )
    );
    }
    
}
