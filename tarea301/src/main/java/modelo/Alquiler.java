package modelo;

import java.util.Date;

/**
 * clase que hace referencia a la tabla alquiler de la base de datos donde 
 * muestra el registro de todos los alquileres de los libros
 * @author alba_
 */
public class Alquiler {
    private int idAlquiler;
    private Date fechaAlquiler;
    private Date fechaDevolucion;
    private String dniSocio;
    private String codigoLibro;
    private Libro libro;

    //insertamos constructores necesarios
    public Alquiler(Date fechaAlquiler, String dniSocio, Libro libro) {
        this.fechaAlquiler = fechaAlquiler;
        this.dniSocio = dniSocio;
        this.libro = libro;
    }

    public Alquiler(Date fechaAlquiler, Date fechaDevolucion, String dniSocio, Libro libro) {
        this(fechaAlquiler,dniSocio, libro);//llamamos al constructor anterior
        this.fechaDevolucion = fechaDevolucion;    
    }
       
    //añadimos getters y setters necesarios
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }
  
}
