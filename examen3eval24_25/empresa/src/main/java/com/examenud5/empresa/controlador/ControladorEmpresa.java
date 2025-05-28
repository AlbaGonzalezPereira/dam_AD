package com.examenud5.empresa.controlador;

import com.examenud5.empresa.entities.Proyecto;
import com.examenud5.empresa.servicios.ServicioDepartamento;
import com.examenud5.empresa.servicios.ServicioEmpleado;
import com.examenud5.empresa.servicios.ServicioProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpresa {
    @Autowired
    private ServicioDepartamento serverDepartamento;
    @Autowired
    private ServicioEmpleado serverEmpleado;
    @Autowired
    private ServicioProyecto serverProyecto;

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarEmpleado(@PathVariable Integer id){
        Boolean respuesta = serverEmpleado.eliminarEmpleado(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{anho_inicio}")
    public ResponseEntity<List> buscarProyectosPorAnho(@PathVariable Integer anho_inicio){
        List<String> listadoProyectos = serverProyecto.buscarProyectosPorAnho(anho_inicio);
        return ResponseEntity.ok(listadoProyectos);
    }

    @PostMapping("")
    public ResponseEntity<List> proyectosEmpleados(@RequestBody String nombre){
        List<String> listadoProyectosEmpleado = serverProyecto.proyectosEmpleado(nombre);
        return ResponseEntity.ok(listadoProyectosEmpleado);
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<String> obtenerNombreCompletoEmpleado(@PathVariable Integer id){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> departamento = rt.postForEntity("http://192.168.109.17:8502/departamento", id, String.class);
        ResponseEntity<String> empleado = rt.getForEntity("http://192.168.109.17:8502/nombre/" + id, String.class);
        String nombreDepartamento = departamento.getBody();
        String nombreEmpleado = empleado.getBody();
        return ResponseEntity.ok(nombreEmpleado + " " + nombreDepartamento);
    }
}
