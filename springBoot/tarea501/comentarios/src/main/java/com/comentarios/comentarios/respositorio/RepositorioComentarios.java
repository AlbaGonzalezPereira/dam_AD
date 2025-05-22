package com.comentarios.comentarios.respositorio;

import com.comentarios.comentarios.entidad.Comentarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RepositorioComentarios extends MongoRepository<Comentarios, String> {
    @Query("{ 'usuarioId': ?0, 'hotelId': ?1, 'reservaId': ?2 }")
    Comentarios existeComentario(int usuarioId, int hotelId, int reservaId);

    List<Comentarios> findByHotelId(Integer idHotel);

    List<Comentarios> findByUsuarioId(Integer idUsuario);

    List<Comentarios> findByReservaIdAndUsuarioId(int idReserva, Integer idUsuario);
}
