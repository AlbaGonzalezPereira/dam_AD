package principal;

import modelo.Modelo;
import controlador.Controlador;
import java.sql.SQLException;
import vista.Ventana;
import vista.VentanaPrincipal;

/**
 * Clase principal donde se crea el Modelo, la Vista y el Controlador e inicia el
 * programa
 * @author alba_
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        // creamos el modelo:
        Modelo modelo = new Modelo();
        // creamos la vista:
        Ventana vista = new VentanaPrincipal();
        // y el controlador:
        Controlador control = new Controlador (vista, modelo);
        //iniciamos el programa
        control.ejecutar();
    }
}
