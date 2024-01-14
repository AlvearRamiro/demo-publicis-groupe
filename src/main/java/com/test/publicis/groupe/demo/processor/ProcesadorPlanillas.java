package com.test.publicis.groupe.demo.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.publicis.groupe.demo.domain.Empleado;
import com.test.publicis.groupe.demo.provider.DemoProveedorMiembrosPlanilla;

import lombok.Setter;

@Service
@Setter
public class ProcesadorPlanillas {

    List<Empleado> listEmpleado;
    
    @Autowired
    DemoProveedorMiembrosPlanilla proveedorMiembrosPlanilla;

    public double total(String id) throws Exception {

        if (id.isEmpty() || id == null) {

            throw new IllegalArgumentException("Id de planilla no puede ser vacio.");
        }

        try {
            listEmpleado = proveedorMiembrosPlanilla.obtenerListaEmpleados(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Error al consultar la planilla, por favor comunicarse con el proveedor de planillas.");
        }

        if (listEmpleado.stream().anyMatch(e -> e.getMontoMensual() < 0)) {

            throw new IllegalArgumentException("Lista contiene montoMensual negativo.");
        }

        if (listEmpleado.stream().anyMatch(e -> e.getId() == 0)) {

            throw new IllegalArgumentException("Lista contiene id igual a cero.");
        }

        if (listEmpleado.stream().anyMatch(e -> e.getNombre().isEmpty())) {

            throw new IllegalArgumentException("Lista contiene nombre vacio.");
        }

        return listEmpleado.stream().filter(e -> e.isActivo()).mapToDouble(e -> e.getMontoMensual()).sum();
    }
}
