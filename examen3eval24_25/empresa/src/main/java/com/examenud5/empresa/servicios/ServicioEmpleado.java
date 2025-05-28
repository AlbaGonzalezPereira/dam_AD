package com.examenud5.empresa.servicios;

import com.examenud5.empresa.entities.Empleado;
import com.examenud5.empresa.repositorio.RepositorioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioEmpleado {
    @Autowired
    private RepositorioEmpleado repoEmpleado;

    public Boolean eliminarEmpleado (int id){
        Optional<Empleado> empleado = repoEmpleado.findById(id);
        if(empleado.isPresent()) {
            repoEmpleado.deleteById(id);
            return true;
        }
        return false;
    }
}
