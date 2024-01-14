package com.test.publicis.groupe.demo.provider;

import java.util.List;

import com.test.publicis.groupe.demo.domain.Empleado;

public abstract class ProveedorMiembrosPlanilla {

    public abstract List<Empleado> obtenerListaEmpleados(String id) throws Exception;
}
