package com.test.publicis.groupe.demo.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.test.publicis.groupe.demo.domain.Empleado;

@Component
public class DemoProveedorMiembrosPlanilla extends ProveedorMiembrosPlanilla {

    public List<Empleado> obtenerListaEmpleados(String id) throws Exception {
        // To do here...
        return new ArrayList<Empleado>();
    }
}
