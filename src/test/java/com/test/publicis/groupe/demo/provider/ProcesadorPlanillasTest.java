package com.test.publicis.groupe.demo.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.test.publicis.groupe.demo.domain.Empleado;
import com.test.publicis.groupe.demo.processor.ProcesadorPlanillas;

public class ProcesadorPlanillasTest {

    String idplanilla = "7910976448";
    List<Empleado> listaEmpleados;
    ProcesadorPlanillas procesadorPlanillas;
    DemoProveedorMiembrosPlanilla proveedorMiembrosPlanillaMock = mock(DemoProveedorMiembrosPlanilla.class);

    @BeforeEach
    public void conf() {

        listaEmpleados = new ArrayList<Empleado>();
        procesadorPlanillas = new ProcesadorPlanillas();
        procesadorPlanillas.setProveedorMiembrosPlanilla(proveedorMiembrosPlanillaMock);
    }

    @Test
    public void listaActivosTest() throws Exception {

        listaEmpleados.add(Empleado.builder().id(1).nombre("Empleado 1").montoMensual(100).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(2).nombre("Empleado 2").montoMensual(200).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(3).nombre("Empleado 3").montoMensual(300).activo(true).build());
        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);

        assertEquals(600, procesadorPlanillas.total(idplanilla));
    }

    @Test
    public void idPlanillaVacioTest() throws Exception {

        idplanilla = "";
        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.total(idplanilla);
        });
        String expectedMessage = "Id de planilla no puede ser vacio.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void errorConsultarPlanillaTest() throws Exception {

        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenThrow(new Exception());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.total(idplanilla);
        });
        String expectedMessage = "Error al consultar la planilla, por favor comunicarse con el proveedor de planillas.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void listaVaciaTest() throws Exception {

        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);
        assertEquals(0, procesadorPlanillas.total(idplanilla));
    }

    @Test
    public void listaInactivosTest() throws Exception {

        listaEmpleados.add(Empleado.builder().id(1).nombre("Empleado 1").montoMensual(100).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(2).nombre("Empleado 2").montoMensual(200).activo(false).build());
        listaEmpleados.add(Empleado.builder().id(3).nombre("Empleado 3").montoMensual(300).activo(false).build());
        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);

        assertEquals(100, procesadorPlanillas.total(idplanilla));
    }

    @Test
    public void montoMensualNegativoTest() throws Exception {

        listaEmpleados.add(Empleado.builder().id(1).nombre("Empleado 1").montoMensual(100).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(2).nombre("Empleado 2").montoMensual(200).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(3).nombre("Empleado 3").montoMensual(-300).activo(true).build());
        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.total(idplanilla);
        });
        String expectedMessage = "Lista contiene montoMensual negativo.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void idIgualCeroTest() throws Exception {

        listaEmpleados.add(Empleado.builder().id(1).nombre("Empleado 1").montoMensual(100).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(2).nombre("Empleado 2").montoMensual(200).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(0).nombre("Empleado 3").montoMensual(300).activo(true).build());
        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.total(idplanilla);
        });
        String expectedMessage = "Lista contiene id igual a cero.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void nombreVacioTest() throws Exception {

        listaEmpleados.add(Empleado.builder().id(1).nombre("Empleado 1").montoMensual(100).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(2).nombre("").montoMensual(200).activo(true).build());
        listaEmpleados.add(Empleado.builder().id(3).nombre("Empleado 3").montoMensual(300).activo(true).build());
        when(proveedorMiembrosPlanillaMock.obtenerListaEmpleados(idplanilla)).thenReturn(listaEmpleados);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            procesadorPlanillas.total(idplanilla);
        });
        String expectedMessage = "Lista contiene nombre vacio.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
