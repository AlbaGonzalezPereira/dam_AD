package com.usuarios.usuarios.repositorios;

import com.usuarios.usuarios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,Integer> {
    @Query("Select u from Usuario u where u.nombre = :nom")
    Usuario findByNombre(@Param("nom") String nombre);
    //String registrarUsuario(Usuario usu); //no es necesario porque ya lo genera el JpaRepository que tiene un método save()

}
