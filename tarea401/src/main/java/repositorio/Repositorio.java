package repositorio;

import java.util.List;

/**
 *
 * @author alba_
 */
public interface Repositorio<T,K> {
    void insertar(T t);
    void borrar(T t);
    List<T> buscarAll();
    T buscarById(K key);
    void actualizar(T t);    
}
