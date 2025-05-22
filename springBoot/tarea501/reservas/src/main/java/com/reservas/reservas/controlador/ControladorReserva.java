package com.reservas.reservas.controlador;

import com.reservas.reservas.DTO.*;
import com.reservas.reservas.entidades.Habitacion;
import com.reservas.reservas.entidades.Hotel;
import com.reservas.reservas.servicios.ServicioHabitacion;
import com.reservas.reservas.servicios.ServicioHotel;
import com.reservas.reservas.servicios.ServicioReserva;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ControladorReserva {

    //inyectamos los servicios
    @Autowired
    private ServicioHabitacion serverHabitacion;
    @Autowired
    private ServicioHotel serverHotel;
    @Autowired
    private ServicioReserva serverReserva;

    /**
     * método que comprueba si un usuario está logueado
     * @param user
     * @param pass
     * @return
     */
    private Boolean comprobarLogin(String user, String pass){
        RestTemplate rt = new RestTemplate();
        DTOUsuario usuario = new DTOUsuario(user, pass);
        ResponseEntity<Boolean> re = rt.postForEntity("http://localhost:8502/usuarios/validar", usuario, Boolean.class);
        Boolean result = re.getBody();  //recupera lo que salga en el body de postman
        if(!result){
            return false;
        }
        return true;
    }

    /**
     * método controlador que devuelve el id del usuario a partir de su nombre
     * @param nombreUsu
     * @return
     */
    private Integer obtenerIdUsuario(String nombreUsu){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> re = rt.getForEntity("http://localhost:8502/usuarios/info/nombre/"+nombreUsu, String.class);
        String result = re.getBody();  //recupera lo que salga en el body de postman
        if(result == null){
            return null;
        }

        Integer resultadoInt = Integer.parseInt(result);
        if(resultadoInt == 0 || result == null){
            return 0;
        }
        return resultadoInt;
    }

    /**
     * método que obtiene las habitaciones de un hotel por el id del mismo
     * @param idHotel
     * @return
     */
    private List<Habitacion> obtenerHabitacionByIdHotel(int idHotel){
        Hotel hotel = serverHotel.obtenerHotelById(idHotel);
        List<Habitacion> habitaciones = serverHabitacion.obtenerHabitacionByHotel(hotel);
        return habitaciones;
    }

    /*****************************************habitación*********************************************/
    /**
     * método controlador que crea una nueva habitación
     * @param nuevaHabitacion
     * @return
     */
    @PostMapping("/habitacion")
    public ResponseEntity<String> crearHabitacion(@RequestBody DTOHabitacion nuevaHabitacion){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        //Hacemos el login
        //sin usar el método comprobarLogin()
//        RestTemplate rt = new RestTemplate();
//        DTOUsuario usuario = new DTOUsuario(nuevaHabitacion.getNombre(), nuevaHabitacion.getContrasena());
//        ResponseEntity<Boolean> re = rt.postForEntity("http://localhost:8502/usuarios/validar", usuario, Boolean.class);
//        Boolean result = re.getBody();  //recupera lo que salga en el body de postman

        //usando el método comprobarLogin()
        Boolean result = comprobarLogin(nuevaHabitacion.getNombre(), nuevaHabitacion.getContrasena());
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverHabitacion.crearHabitacion(nuevaHabitacion.getHabitacion());
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que actualiza una habitación
     * @param habitacion
     * @return
     */
    @PatchMapping("/habitacion")
    public ResponseEntity<String> actualizarHabitacion(@RequestBody DTOHabitacion habitacion){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        Boolean result = comprobarLogin(habitacion.getNombre(), habitacion.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverHabitacion.actualizarHabitacion(habitacion.getHabitacion());
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que elimina una habitación mediante su id y pasando un usuario y una contraseña
     * @param id
     * @param usu
     * @return
     */
    @DeleteMapping("/habitacion/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable int id, @RequestBody DTOUsuario usu){
        Boolean result = comprobarLogin(usu.getNombre(), usu.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverHabitacion.eliminarHabitacion(id);
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }


    /*******************************************hotel**************************************************/
    /**
     * método controlador que crea un hotel
     * @param nuevoHotel
     * @return
     */
    @PostMapping("/hotel")
    public ResponseEntity<String> crearHotel(@RequestBody DTOHotel nuevoHotel){
        Boolean result = comprobarLogin(nuevoHotel.getNombre(), nuevoHotel.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverHotel.crearHotel(nuevoHotel.getHotel());
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que actualiza un hotel
     * @param hotel
     * @return
     */
    @PatchMapping("/hotel")
    public ResponseEntity<String> actualizarHotel(@RequestBody DTOHotel hotel){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        Boolean result = comprobarLogin(hotel.getNombre(), hotel.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverHotel.actualizarHotel(hotel.getHotel());
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que elimina un hotel
     * @param id
     * @param hot
     * @return
     */
    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<String> eliminarHotel(@PathVariable int id, @RequestBody DTOHotel hot){
        Boolean result = comprobarLogin(hot.getNombre(), hot.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverHotel.eliminarHotel(id);
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que devuelve el id de un hotel a partir de un nombre pasado por url
     * @param nombre
     * @param usu
     * @return
     */
    @PostMapping("/hotel/id/{nombre}")
    public ResponseEntity<String> obtenerIdApartirNombre(@PathVariable String nombre, @RequestBody DTOUsuario usu){
        Boolean result = comprobarLogin(usu.getNombre(), usu.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }
        Integer id = serverHotel.obtenerIdApartirNombre(nombre);
        if(id == null){
            return ResponseEntity.status(HttpStatus.SC_OK).body("Hotel no encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(id.toString());
        }
    }

    /**
     * método controlador que devuelve el nombre de un hotel a partir de un id pasado por url
     * @param id
     * @param usu
     * @return
     */

    @PostMapping("/hotel/nombre/{id}")
    public ResponseEntity<String> obtenerNombreApartirId(@PathVariable Integer id, @RequestBody DTOUsuario usu){
        Boolean result = comprobarLogin(usu.getNombre(), usu.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }
        String nombre = serverHotel.obtenerNombreApartirId(id);
        if(nombre == null){
            return ResponseEntity.status(HttpStatus.SC_OK).body("Hotel no encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(nombre.toString());
        }
    }


    /*******************************************reserva********************************************/

    /**
     * método controlador que crea una reserva
     * @param nuevaReserva
     * @return
     */
    @PostMapping()
    public ResponseEntity<String> crearReserva(@RequestBody DTOReserva nuevaReserva){
        Boolean result = comprobarLogin(nuevaReserva.getNombre(), nuevaReserva.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverReserva.crearReserva(nuevaReserva.getReserva());
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que actualiza una reserva
     * @param reserva
     * @return
     */
    @PatchMapping()
    public ResponseEntity<String> cambiarEstado(@RequestBody DTOReserva reserva){ //@RequestBody porque se lo pasamos en el body de la petición de postman
        Boolean result = comprobarLogin(reserva.getNombre(), reserva.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        String respuesta = serverReserva.cambiarEstado(reserva.getReserva());
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que devuelve las reservas de un usuario
     * @param usu
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> listarReservasUsuario(@RequestBody DTOUsuario usu){
        Boolean result = comprobarLogin(usu.getNombre(), usu.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        Integer idUsuario = obtenerIdUsuario(usu.getNombre());
        List<DTOReservaUsuario> respuesta = serverReserva.listarReservasUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que lista las reservas en función de su estado (confirmada, pendiente, cancelada)
     * @param estado
     * @param usu
     * @return
     */
    @GetMapping("/{estado}")
    public ResponseEntity<?> listarReservasSegunEstado(@PathVariable String estado, @RequestBody DTOUsuario usu){
        Boolean result = comprobarLogin(usu.getNombre(), usu.getContrasena());
        //comprobamos si está logueado
        if(!result){
            return ResponseEntity.status(HttpStatus.SC_OK).body("No estás logueado");
        }

        List<DTOReservaUsuario> respuesta = serverReserva.listarReservasSegunEstado(estado);
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

    /**
     * método controlador que devuelve true/false si hay una reserva
     * @param idUsuario
     * @param idHotel
     * @param idReserva
     * @return
     */
    @GetMapping("/check/{idUsuario}/{idHotel}/{idReserva}")
    public ResponseEntity<Boolean> checkReserva(@PathVariable Integer idUsuario, @PathVariable Integer idHotel, @PathVariable Integer idReserva) {
        //creamos una lista de habitaciones para encontrar el id del hotel
        List<Habitacion> habitaciones = obtenerHabitacionByIdHotel(idHotel);
        Boolean respuesta = false;

        //comprobamos si no existen habitaciones de ese hotel
        if(habitaciones == null){
            return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
        }

        // recorremos las habitaciones para que nos encuentre la reserva del usuario con el hotel a través de la habitación
        for (Habitacion hab : habitaciones) {
            respuesta = serverReserva.checkReserva(idUsuario, hab.habitacion_id, idReserva); //devuelve true o false
            if(respuesta){ //si la encuentra
                break; //no sigue buscando ya que devuelve true
            }
        }
        return ResponseEntity.status(HttpStatus.SC_OK).body(respuesta);
    }

}
