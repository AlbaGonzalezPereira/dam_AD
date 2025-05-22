package com.example.saludo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.saludo.service.SaludoService;

/**
 * rutas de la aplicación
 */
@RestController
@RequestMapping("/saludo") // a partir de que ruta recoge las peticiones
public class SaludoController {
     //injectar dependencias automáticamente (hace el constructor, crea el objeto, sin que tengas que hacerlo manualmente)
    private final SaludoService saludoServicio;

@Autowired
    public SaludoController(SaludoService saludoServicio) {
        this.saludoServicio = saludoServicio;
    }

    @GetMapping("/{nombre}") //parámetro que recogemos de la url (por get)
    public ResponseEntity<String> saludarusuario(@PathVariable String nombre){//ResponseEntity es una clase que almacena la respuesta y el código
        String saludo = saludoServicio.saludarUsuario(nombre);
        return ResponseEntity.ok(saludo); //Código 200
    }

    @GetMapping
    public ResponseEntity<String> saludarDefault() {
        return ResponseEntity.ok("Hola, usuario desconocido!");
    }

}
