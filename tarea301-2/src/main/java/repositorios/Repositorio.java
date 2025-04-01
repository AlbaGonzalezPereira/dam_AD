
package repositorios;

/**
 * Interfaz de métodos para implementar
 * @author alba_
 */
public interface Repositorio<T> {
    public int crear(T t);
    public int modificar(T t);
    public int borrar(int id);  
    public void mostrarTodos();
    public int devolverUltimoId();
    public T buscarById(int id);
    public T buscarByName(String nombre);
}
