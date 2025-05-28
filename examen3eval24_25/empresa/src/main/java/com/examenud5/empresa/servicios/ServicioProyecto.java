package com.examenud5.empresa.servicios;

import com.examenud5.empresa.entities.Empleado;
import com.examenud5.empresa.entities.Proyecto;
import com.examenud5.empresa.repositorio.RepositorioProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProyecto {
    @Autowired
    private RepositorioProyecto repoProyecto;

    public List<String> buscarProyectosPorAnho(Integer anho_inicio) {
        List<Proyecto> proyectosAnho = repoProyecto.findByAnho(anho_inicio);
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < proyectosAnho.size(); i++) {
            nombres.add(proyectosAnho.get(i).getTitulo());
        }
        return nombres;

        }

    public List<String> proyectosEmpleado(String nombre) {
        List<Proyecto> proyectos = repoProyecto.findAll();
        List<String> titulos = new ArrayList<>();
        for (int i = 0; i < proyectos.size(); i++) {
            for(int j = 0; j < proyectos.get(i).getListaEmpleados().size(); j++){
                if(proyectos.get(i).getListaEmpleados().get(j).getNombre().equals(nombre)){
                    titulos.add(proyectos.get(i).getTitulo());
                }
            }
        }

        return titulos;
    }
}
