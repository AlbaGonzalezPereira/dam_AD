package com.comentarios.comentarios.controlador;

import com.comentarios.comentarios.DTO.*;
import com.comentarios.comentarios.entidad.Comentarios;
import com.comentarios.comentarios.servicio.ServicioComentarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorComentarios {
    @Autowired
    private ServicioComentarios serverCommen;

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
     * método que obtiene el id de un hotel a partir de su nombre
     * @param nombreHotel
     * @param nombre
     * @param contrasena
     * @return
     */
    private Integer obtenerIdHotel(String nombreHotel, String nombre, String contrasena){
        String url = "http://localhost:8501/reservas/hotel/id/"+nombreHotel;
        DTOUsuario usuario = new DTOUsuario(nombre, contrasena);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> re = rt.postForEntity(url, usuario, String.class);
        String result = re.getBody();
        if(result.equals("Hotel no encontrado")){
            return null;
        }

        Integer resultadoInt = Integer.parseInt(result);
        if(resultadoInt == 0 || result == null){
            return 0;
        }
        return resultadoInt;
    }

    /**
     * método que compreba las reservas realizadas
     * @param idUsuario
     * @param idHotel
     * @param idReserva
     * @return
     */
    private Boolean comprobarCheckReservas(int idUsuario, int idHotel, int idReserva){
        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8501/reservas/check/"+idUsuario+"/" + idHotel + "/" + idReserva;
        ResponseEntity<Boolean> re = rt.getForEntity(url, Boolean.class);
        Boolean result = re.getBody();  //recupera lo que salga en el body de postman
        if(result == null){
            return null;
        }
        return result;
    }

    /**
     * método que obtiene el nombre de un hotel a través de su id
     * @param idHotel
     * @param nombreUsu
     * @param contrasena
     * @return
     */
    private String obtenerNombreHotelById(int idHotel, String nombreUsu, String contrasena){
        String url = "http://localhost:8501/reservas/hotel/nombre/" + idHotel;
        DTOUsuario usuario = new DTOUsuario(nombreUsu, contrasena);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> re = rt.postForEntity(url, usuario, String.class);
        String result = re.getBody();
        if(result.equals("Hotel no encontrado")){
            return null;
        }
        return result;
    }

    /**
     * método controlador que encargará de crear y almacenar el comentario de un usuario sobre una reserva en un determinado hotel.
     * Recibirá un objeto con la información del comentario (nombreHotel, id de reserva, puntuación y comentario)
     * @param comentario
     * @return
     */
    @MutationMapping
    public DTOComentarioInfo crearComentario(@Argument DTOComentario comentario){
        Boolean logueado = comprobarLogin(comentario.getNombre(), comentario.getContrasena());
        if(!logueado){
            return null;
        }

        Integer idUsuario = obtenerIdUsuario(comentario.getNombre());
        Integer idHotel = obtenerIdHotel(comentario.getNombreHotel(), comentario.getNombre(), comentario.getContrasena());
        if(idHotel == null || idHotel == 0){
            return null;
        }

        Boolean comprobarReserva = comprobarCheckReservas(idUsuario, idHotel, comentario.getReservaId());
        if(!comprobarReserva){
            return null;
        }

        //creamos el comentario
        Comentarios nuevoComentario = new Comentarios(idUsuario, idHotel, comentario.getReservaId(), comentario.getPuntuacion(), comentario.getComentario(), LocalDateTime.now());
        Comentarios comentarioCreado = serverCommen.crearComentario(nuevoComentario);
        if(comentarioCreado == null){ //si ya existía no lo crea
            return null;
        }

        //creamos el infoComentario con los datos del comentario creado:
        DTOComentarioInfo infoComentario = new DTOComentarioInfo();
        infoComentario.setNombreHotel(comentario.getNombreHotel());
        infoComentario.setReservaId(comentarioCreado.getReservaId());
        infoComentario.setPuntuacion(comentarioCreado.getPuntuacion());
        infoComentario.setComentario(comentarioCreado.getComentario());

        return infoComentario;
    }

    /**
     * método controlador que se encargará de eliminar todos los comentarios del sistema.
     * @return
     */
    @MutationMapping
    public String eliminarComentarios(){
        String comentariosEliminados = serverCommen.eliminarComentarios();
        return comentariosEliminados;
    }

    /**
     * método controlador que eliminará un determinado comentario hecho por un usuario.
     * Recibirá un identificador indicando el comentario en cuestión a eliminar.
     * @param comentarioEliminado
     * @return
     */
    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument DTOComentarioDelete comentarioEliminado){
        Boolean logueado = comprobarLogin(comentarioEliminado.getNombre(), comentarioEliminado.getContrasena());
        if(!logueado){
            return null;
        }
        //comprobamos que el idUsuario sea el del comentario
        Integer idUsuario = obtenerIdUsuario(comentarioEliminado.getNombre());
        String respuesta = serverCommen.eliminarComentarioDeUsuario(comentarioEliminado.getComentarioId(), idUsuario);
        //En caso de no comprobar que el idUsuario es el del usuario logueado:
        //String respuesta = serverCommen.eliminarComentarioDeUsuario(comentarioEliminado.getComentarioId());
        return respuesta;
    }

    /**
     * método controlador que listará todos los comentarios de las reservas de un determinado hotel
     * @param comentario
     * @return
     */
    @QueryMapping
    public List<DTOComentarioInfo> listarComentariosHotel(@Argument DTOComentarioHotel comentario){
        Boolean logueado = comprobarLogin(comentario.getNombre(), comentario.getContrasena());
        if(!logueado){
            return null;
        }
        Integer idHotel = obtenerIdHotel(comentario.getNombreHotel(), comentario.getNombre(), comentario.getContrasena());
        if(idHotel == 0 || idHotel == null){
            return null;
        }
        List<Comentarios> respuesta = serverCommen.listarComentariosHotel(idHotel);
        List<DTOComentarioInfo> comentariosInfo = new ArrayList<>();
        if(respuesta == null) {
            return null;
        }

        for (Comentarios com : respuesta){
            DTOComentarioInfo coment = new DTOComentarioInfo();
            coment.setComentario(com.getComentario());
            coment.setPuntuacion(com.getPuntuacion());
            coment.setNombreHotel(comentario.getNombreHotel());
            coment.setReservaId(com.getReservaId());
            comentariosInfo.add(coment);
        }
        return comentariosInfo;
    }

    /**
     * método controlador que listará todos los comentarios de las reservas de un determinado usuario
     * @param usuario
     * @return
     */
    @QueryMapping
    public List<DTOComentarioInfo> listarComentariosUsuario(@Argument DTOUsuario usuario){
        Boolean logueado = comprobarLogin(usuario.getNombre(), usuario.getContrasena());
        if(!logueado){
            return null;
        }

        //obtenemos el id del usuario
        Integer idUsuario = obtenerIdUsuario(usuario.getNombre());
        //listamos los comentarios
        List<Comentarios> respuesta = serverCommen.listarComentariosUsuario(idUsuario);
        //listamos info requerida
        List<DTOComentarioInfo> comentariosInfo = new ArrayList<>();
        if(respuesta == null) {
            return null;
        }

        for (Comentarios com : respuesta){
            //obtenemos el nombre del hotel
            String nombreHotel = obtenerNombreHotelById(com.getHotelId(), usuario.getNombre(), usuario.getContrasena());
            DTOComentarioInfo coment = new DTOComentarioInfo();
            coment.setComentario(com.getComentario());
            coment.setPuntuacion(com.getPuntuacion());
            coment.setNombreHotel(nombreHotel);
            coment.setReservaId(com.getReservaId());
            comentariosInfo.add(coment);
        }
        return comentariosInfo;
    }

    /**
     * Método controlador que muestra el comentario hecho por un usuario en una determinada reserva.
     * Recibirá un parámetro indicando el identificador de la reserva.
     * @param comentario
     * @return
     */
    @QueryMapping
    public List<DTOComentarioInfo> mostrarComentarioUsuarioReserva(@Argument DTOComentario comentario){
        Boolean logueado = comprobarLogin(comentario.getNombre(), comentario.getContrasena());
        if(!logueado){
            return null;
        }
        //obtenemos el id del usuario
        Integer idUsuario = obtenerIdUsuario(comentario.getNombre());
        List<Comentarios> respuesta = serverCommen.mostrarComentarioUsuarioReserva(comentario.getReservaId(), idUsuario);
        List<DTOComentarioInfo> comentariosInfo = new ArrayList<>();
        if(respuesta == null) {
            return null;
        }

        for (Comentarios com : respuesta){
            //obtenemos el nombre del hotel
            String nombreHotel = obtenerNombreHotelById(com.getHotelId(), comentario.getNombre(), comentario.getContrasena());
            DTOComentarioInfo coment = new DTOComentarioInfo();
            coment.setComentario(com.getComentario());
            coment.setPuntuacion(com.getPuntuacion());
            coment.setNombreHotel(nombreHotel);
            coment.setReservaId(com.getReservaId());
            comentariosInfo.add(coment);
        }
        return comentariosInfo;
    }

    /**
     * método controlador que muestra la puntuación media de un hotel a partir de la puntuación de sus reservas.
     * Recibirá un parámetro indicando el nombre del hotel.
     * @param comentarioHotel
     * @return
     */
    @QueryMapping
    public Double puntuacionMediaHotel(@Argument DTOComentarioHotel comentarioHotel){
        Boolean logueado = comprobarLogin(comentarioHotel.getNombre(), comentarioHotel.getContrasena());
        if(!logueado){
            return null;
        }
        //obtenemos el id del hotel
        Integer idHotel = obtenerIdHotel(comentarioHotel.getNombreHotel(), comentarioHotel.getNombre(), comentarioHotel.getContrasena());

        //Comprobamos si existe el hotel
        if(idHotel == null || idHotel==0){
            return null;
        }
        // creamos una lista de comentarios para recoger las puntuaciones
        Double respuesta = serverCommen.obtenerPuntuacionMediaHotel(idHotel);
        return respuesta;
    }

    /**
     * método controlador que muestra la puntuación media de los comentarios hechos por un usuario en sus reservas.
     * Solo recibirá la información de usuario y contraseña.
     * @param usuario
     * @return
     */
    @QueryMapping
    public Double puntuacionesMediasUsuario(@Argument DTOUsuario usuario){
        Boolean logueado = comprobarLogin(usuario.getNombre(), usuario.getContrasena());
        if(!logueado){
            return null;
        }

        //obtenemos el id del usuario
        Integer idUsuario = obtenerIdUsuario(usuario.getNombre());

        // creamos una lista de comentarios para recoger las puntuaciones
        Double respuesta = serverCommen.obtenerPuntuacionesMediasUsuario(idUsuario);
        return respuesta;
    }
}
