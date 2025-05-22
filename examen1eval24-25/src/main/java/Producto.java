import java.io.Serializable;

/**
 *
 * @author a24exame-dam-1
 */
public class Producto implements Serializable {
    private String nombre;
    private float precio;
    private int stock;

    public Producto() {
    }

    public Producto(String nombre, float precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto: " + "nombre=" + nombre + ", precio=" + precio + ", stock=" + stock;
    }
    
    
    
}
