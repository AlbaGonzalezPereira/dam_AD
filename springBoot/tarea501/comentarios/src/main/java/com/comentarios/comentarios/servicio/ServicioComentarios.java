package com.comentarios.comentarios.servicio;

import com.comentarios.comentarios.DTO.DTOComentario;
import com.comentarios.comentarios.DTO.DTOComentarioInfo;
import com.comentarios.comentarios.entidad.Comentarios;
import com.comentarios.comentarios.respositorio.RepositorioComentarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioComentarios {
    @Autowired
    private RepositorioComentarios repoCommen;

    public Comentarios crearComentario(Comentarios nuevoComentario) {
        Comentarios con = repoCommen.existeComentario(nuevoComentario.getUsuarioId(), nuevoComentario.getHotelId(), nuevoComentario.getReservaId());
        if(con != null){
            return null;
        }
        return repoCommen.save(nuevoComentario);
    }


    public String eliminarComentarios() {
       repoCommen.deleteAll();
       return "Comentarios eliminados";
    }

    /**
     * método que elimina un comentario de usuario sin comprobar que el id del usuario corresponde al id del usuario logueado
     * @param comentarioId
     * @return
     */
    public String eliminarComentarioDeUsuario(String comentarioId) {
        Optional<Comentarios> comentario = repoCommen.findById(comentarioId);
        if(comentario.isPresent()){
            repoCommen.delete(comentario.get());
            return "Comentario eliminado correctamente";
        }

        return "Comentario no encontrado";
    }

    /**
     *
     * @param comentarioId
     * @param idUsuario
     * @return
     */
    public String eliminarComentarioDeUsuario(String comentarioId, int idUsuario) {
        Optional<Comentarios> comentario = repoCommen.findById(comentarioId);
        if(comentario.isPresent()){
            if(comentario.get().getUsuarioId() == idUsuario){
                repoCommen.delete(comentario.get());
                return "Comentario eliminado correctamente";
            } else {
                return "Usuario no válido";
            }
        }
        return "Comentario no encontrado";
    }

    public List<Comentarios> listarComentariosHotel(Integer idHotel) {
        List<Comentarios> comentarios = repoCommen.findByHotelId(idHotel);
        return comentarios;
    }

    public List<Comentarios> listarComentariosUsuario(Integer idUsuario) {
        List<Comentarios> comentarios = repoCommen.findByUsuarioId(idUsuario);
        return comentarios;
    }

    public List<Comentarios> mostrarComentarioUsuarioReserva(int idReserva, Integer idUsuario) {
        List<Comentarios> comentarios = repoCommen.findByReservaIdAndUsuarioId(idReserva, idUsuario);
        return comentarios;
    }

    public Double obtenerPuntuacionMediaHotel(Integer idHotel) {
        List<Comentarios> comentarios = repoCommen.findByHotelId(idHotel);
        if(comentarios == null){
            return null;
        }

        int contadorComentarios = 0;
        double media = 0;
        for(Comentarios com: comentarios){
            Double puntuacion = com.getPuntuacion(); //cogemos las puntuaciones de los comentarios
            media += puntuacion;
            contadorComentarios++;
        }
        double mediaPuntuacion = media/contadorComentarios; //hacemos la media
        return mediaPuntuacion;
    }

    public Double obtenerPuntuacionesMediasUsuario(Integer idUsuario) {
        List<Comentarios> comentarios = repoCommen.findByUsuarioId(idUsuario);
        if(comentarios == null){
            return null;
        }

        int contadorComentarios = 0;
        double media = 0;
        for(Comentarios com: comentarios){
            Double puntuacion = com.getPuntuacion(); //cogemos las puntuaciones de los comentarios
            media += puntuacion;
            contadorComentarios++;
        }
        double mediaPuntuacion = media/contadorComentarios; //hacemos la media
        return mediaPuntuacion;
    }
}
