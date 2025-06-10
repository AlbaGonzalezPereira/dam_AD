
package bbdd;

/**
 *
 * @author alba_
 */
public class Jugador {
    private int dorsal;
    private String posicion;
    private float altura;

    public Jugador(int dorsal, String posicion, float altura) {
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.altura = altura;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Jugador: " + "dorsal=" + dorsal + ", posicion=" + posicion + ", altura=" + altura;
    }
    
    
    
}
