package com.test.publicis.groupe.demo.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Empleado {

    int id;
    String nombre;
    float montoMensual;
    boolean activo;
}
