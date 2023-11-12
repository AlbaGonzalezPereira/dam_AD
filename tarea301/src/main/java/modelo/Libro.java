package modelo;

/**
 * clase que hace referencia a la tabla libros de la base de datos donde 
 * muestra el registro de todos los libros
 * @author alba_
 */
public class Libro {
    private String codigo;
    private String titulo;
    private String autor;

    //insertamos los constructores necesarios
    public Libro(String codigo, String titulo, String idAutor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = idAutor;
    }

    public Libro(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public Libro(String codigo) {
        this.codigo = codigo;
    }
    
    
    //insertamos getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
   
}
