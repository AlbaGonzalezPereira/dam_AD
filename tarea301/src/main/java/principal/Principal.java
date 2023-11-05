/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package principal;

import modelo.Modelo;
import controlador.Controlador;
import java.sql.SQLException;
import vista.Ventana;
import vista.VentanaPrincipal;

/**
 *
 * @author Juan
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        // el modelo:
        Modelo modelo = new Modelo();
        // la vista:
        Ventana vista = new VentanaPrincipal();
        // y el control:
        Controlador control = new Controlador (vista, modelo);
        
        control.ejecutar();
        // y arranca la interfaz (vista):
        //vista.arrancar();
        // configura la vista
        //vista.setControlador(control);
    }
}
