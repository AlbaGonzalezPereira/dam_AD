package com.usuarios.usuarios.controlador;

import com.usuarios.usuarios.entidades.Usuario;
import com.usuarios.usuarios.servicio.ServicioUsuario;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario serverUser;

    /**
     * método que crea un usuario nuevo
     * @param nuevoUsuario
     * @return
     */
    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario nuevoUsuario){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        serverUser.crearUsuario(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario creado correctamente");
        //return ResponseEntity.ok(serverUser.crearUsuario(nuevoUsuario));
    }

    /**
     * método que actualiza un usuario si existe
     * @param usuario
     * @return
     */
    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        Usuario usu = serverUser.actualizarUsuario(usuario);
        if(usu==null){
            return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario no encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario actualizado correctamente");
        }

    }

    /**
     * método que elimina un usuario si coincide el nombre con la contraseña
     * @param usuario
     * @return
     */
    @DeleteMapping("/")
    public ResponseEntity<String> eliminarUsuario(@RequestBody Usuario usuario){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        Usuario usu = serverUser.eliminarUsuario(usuario);
        if(usu==null){
            return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario no encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario eliminado correctamente");
        }
    }

    /**
     * método que busca un usuario mediante el nombre y contraseña
     * @param usuario
     * @return
     */
    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody Usuario usuario){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        Boolean usuEncontrado = serverUser.validarUsuario(usuario);
        if(!usuEncontrado){
            return ResponseEntity.status(HttpStatus.SC_OK).body(false);
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(true);
        }
    }

    /**
     * método controlador que se encargará de obtener qué nombre de usuario le corresponde a un identificador en particular.
     * @param id
     * @return
     */
    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable Integer id){
        String nombre = serverUser.obtenerInfoUsuarioPorId(id);
        if(nombre == null){
            return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario no encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(nombre);
        }
    }

    /**
     * método controlador que se encargará de obtener qué ID de un usuario a partir de su nombre
     * @param nombre
     * @return
     */
    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<String> obtenerInfoUsuarioPorNombre(@PathVariable String nombre){
        Integer id = serverUser.obtenerInfoUsuarioPorNombre(nombre);
        if(id == null){
            return ResponseEntity.status(HttpStatus.SC_OK).body("Usuario no encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(id.toString());
        }
    }

    /**
     * método controlador que comprobará si un usuario existe
     * @param id
     * @return
     */
    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable Integer id){
        Boolean idExist = serverUser.checkIfExist(id);
        if(!idExist){
            return ResponseEntity.status(HttpStatus.SC_OK).body(false);
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(true);
        }
    }

}
