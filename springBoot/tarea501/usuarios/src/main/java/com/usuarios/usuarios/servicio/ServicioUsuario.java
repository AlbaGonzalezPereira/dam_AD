package com.usuarios.usuarios.servicio;

import com.usuarios.usuarios.entidades.Usuario;
import com.usuarios.usuarios.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {
    @Autowired
    private RepositorioUsuario repoUser;

    /**
     * servicio que crea un usuario
     * @param user
     * @return
     */
    public Usuario crearUsuario(Usuario user){
        return repoUser.save(user); //guardamos el usuario
    }

    /**
     * servicio que actualiza un usuario si existe
     * @param user
     * @return
     */
    public Usuario actualizarUsuario(Usuario user){
        if(user.usuario_id == 0){ //id inválido
            return null;
        }

        //buscamos el id en la base de datos
        Optional<Usuario> usuarioBd = repoUser.findById(user.usuario_id);
        if (usuarioBd.isPresent()) { //si existe en la base de datos
            return repoUser.save(user); // Actualizamos el usuario
        } else {
            return null; // Si no existe en la base de datos, devolvemos null para que no lo cree
        }
        //con la línea de abajo crea el usuario si no existe
        //return repoUser.save(user); //creamos el método aunque podríamos usar ambas ya que hacen lo mismo
    }

    /**
     * servicio que elimina un usuario si coincide su nombre y su contraseña
     * @param user
     * @return
     */
    public Usuario eliminarUsuario(Usuario user){
        List<Usuario> usuarios = repoUser.findAll();
        int idUsuario = 0;
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).nombre.equals(user.nombre) && usuarios.get(i).contrasena.equals(user.contrasena)){
                idUsuario = usuarios.get(i).usuario_id;
            }
        }

        //si existe el id
        if(idUsuario !=0) {
            repoUser.deleteById(idUsuario);
            return user;
        }

        return null; //si no lo encuentra

    }

    /**
     * servicio que busca un usuario si coincide su nombre y su contraseña
     * @param user
     * @return true/false
     */
    public Boolean validarUsuario(Usuario user){
        List<Usuario> usuarios = repoUser.findAll();
        int idUsuario = 0;
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).nombre.equals(user.nombre) && usuarios.get(i).contrasena.equals(user.contrasena)){
                idUsuario = usuarios.get(i).usuario_id;
            }
        }

        //si existe el id
        if(idUsuario !=0) {
            return true;
        }

        return false; //si no lo encuentra

    }

    public String obtenerInfoUsuarioPorId(Integer id){
        Optional<Usuario> usuarioBd = repoUser.findById(id);
        if (usuarioBd.isPresent()) { //si existe en la base de datos
            return usuarioBd.get().nombre; // Delvemos el nombre del usuario
        } else {
            return null; // Si no existe en la base de datos, devolvemos null para que no lo cree
        }

    }

    public Integer obtenerInfoUsuarioPorNombre(String nombre){
        Usuario usuarioInfo = repoUser.findByNombre(nombre);

        if (usuarioInfo != null) { //si no existe en la base de datos
            return usuarioInfo.getUsuario_id(); // Delvemos el id del usuario
       } else {
           return null; // Si no existe en la base de datos, devolvemos null para que no lo cree
       }

    }

    public Boolean checkIfExist(Integer id) {
        Optional<Usuario> usuarioExist = repoUser.findById(id);
        if (usuarioExist.isPresent()) { //si existe en la base de datos
            return true; // Devolvemos true si existe el usuario
        } else {
            return false; // Si no existe en la base de datos, devolvemos false
        }

    }
}
