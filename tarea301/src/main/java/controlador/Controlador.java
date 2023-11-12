package controlador;

import exception.CodeNotFoundException;
import exception.HireNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Modelo;
import modelo.Socio;
import vista.Ventana;
import vista.VentanaAlquilarLibro;
import vista.VentanaDevolverLibro;
import vista.VentanaLibrosAlquilados;
import vista.VentanaLibrosDisponibles;
import vista.VentanaPrincipal;
import vista.VentanaVerHistorico;
import vista.VentanaVerSocios;

/**
 * clase que hace de puente entre el modelo y las vistas. En este caso, usamos un
 * controlador general pero podríamos crear un controlador individual por cada 
 * ventana
 * @author alba_
 */
public class Controlador implements ActionListener {

    private Ventana vista;
    private VentanaAlquilarLibro ventanaAlquilarLibro;
    private VentanaDevolverLibro ventanaDevolverLibro;
    private VentanaLibrosAlquilados ventanaLibrosAlquilados;
    private VentanaLibrosDisponibles ventanaLibrosDisponibles;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaVerHistorico ventanaVerHistorico;
    private VentanaVerSocios ventanaVerSocios;
    private Modelo modelo;

    public Controlador(Ventana vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        //inicializamos las ventanas
        ventanaPrincipal = new VentanaPrincipal();
        ventanaVerSocios = new VentanaVerSocios();
        ventanaLibrosDisponibles = new VentanaLibrosDisponibles();
        ventanaLibrosAlquilados = new VentanaLibrosAlquilados();
        ventanaAlquilarLibro = new VentanaAlquilarLibro();
        ventanaDevolverLibro = new VentanaDevolverLibro();
        ventanaVerHistorico = new VentanaVerHistorico();
               
        //agregamos los controladores para cada ventana
        ventanaPrincipal.agregarControlador(this);
        ventanaVerSocios.agregarControlador(this);
        ventanaLibrosDisponibles.agregarControlador(this);
        ventanaLibrosAlquilados.agregarControlador(this);
        ventanaAlquilarLibro.agregarControlador(this);
        ventanaDevolverLibro.agregarControlador(this);
        ventanaVerHistorico.agregarControlador(this);

    }

    /**
     * método que inicia la ventana principal
     */
    public void ejecutar() {
        ventanaPrincipal.setVisible(true);
    }

    /**
     * método donde recogemos todos los eventos de las vistas
     * @param e - evento recogido
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //comprobamos que se ha pulsado el botón correspondiente
        if (e.getSource().equals(ventanaPrincipal.getBtnAlquilarLibro())) {
            ventanaAlquilarLibro.setVisible(true);
        } else if (e.getSource().equals(ventanaPrincipal.getBtnDevolverLibro())) {
            //System.out.println("Devolver libro");//comprobamos
            ventanaDevolverLibro.setVisible(true);
        } else if (e.getSource().equals(ventanaPrincipal.getBtnLibrosAlquilados())) {
            //System.out.println("Libros alquilados");//comprobamos
            ventanaLibrosAlquilados.setVisible(true);
            mostrarLibrosAlquilados();
        } else if (e.getSource().equals(ventanaPrincipal.getBtnLibrosDisponibles())) {
            //System.out.println("Libros disponibles");//comprobamos
            ventanaLibrosDisponibles.setVisible(true);
            mostarLibrosDisponibles();
        } else if (e.getSource().equals(ventanaPrincipal.getBtnHistorico())) {
            //System.out.println("Ver historico");//comprobamos
            ventanaVerHistorico.setVisible(true);
            mostrarDatosHistoricos();
        } else if (e.getSource().equals(ventanaPrincipal.getBtnVerSocios())) {
            //System.out.println("Ver socios");//comprobamos
            ventanaVerSocios.setVisible(true);
            mostrarDatosSocios();
        } else if (e.getSource().equals(ventanaAlquilarLibro.getBtnCancelar())) {
            ventanaAlquilarLibro.setVisible(false);
        } else if (e.getSource().equals(ventanaAlquilarLibro.getBtnAlquilar())) {
            alquilarLibro();
            ventanaAlquilarLibro.limpiar();
            //ventanaAlquilarLibro.setVisible(false);//si queremos que cierre la ventana al alquilar un libro
        } else if (e.getSource().equals(ventanaDevolverLibro.getBtnCancelar())) {
            ventanaDevolverLibro.setVisible(false);
        } else if (e.getSource().equals(ventanaDevolverLibro.getBtnDevolver())) {
            devolverLibro();
            ventanaDevolverLibro.limpiar();
            //ventanaDevolverLibro.setVisible(false);//si queremos que cierre la ventana al devolver un libro
        }
    }

    /**
     * método que obtiene y muestra los datos de los socios
     */
    private void mostrarDatosSocios() {
        ArrayList<Socio> socios = modelo.obtenerDatosSocios();
        ventanaVerSocios.cargarDatos(socios);
    }

    /**
     * método que obtiene y muestra los datos de los libros disponibles
     */
    private void mostarLibrosDisponibles() {
        ArrayList<Libro> librosDisp = modelo.obtenerLibrosDisponibles();
        ventanaLibrosDisponibles.cargarDatos(librosDisp);
    }

    /**
     * método que obtiene y muestra los datos de los libros alquilados
     */
    private void mostrarLibrosAlquilados() {
        ArrayList<Alquiler> librosAlq = modelo.obtenerLibrosAlquilados();
        ventanaLibrosAlquilados.cargarDatos(librosAlq);
    }

    /**
     * método que permite alquilar un libro y muestra una advertencia en caso de 
     * que no pueda alquilarse 
     */
    private void alquilarLibro() {
        String codigo = ventanaAlquilarLibro.getTextCodigo();
        String dni = ventanaAlquilarLibro.getTextDNI();
        int resultado;
        try {
            resultado = modelo.anhadirLibroAlquilado(dni, codigo);
        } catch (HireNotFoundException ex) {
            ventanaAlquilarLibro.mostrarAdvertencia();
        }
        //System.out.println(resultado);//comprobamos
    }

    /**
     * método que permite devolver un libro y muestra una advertencia en caso de
     * no poder devolverlo
     */
    private void devolverLibro() {
        String codigo = ventanaDevolverLibro.getTextCodigo();
        int resultado=0;
        try {
            resultado = modelo.devolverLibroAlquilado(codigo);
        } catch (CodeNotFoundException ex) {
            ventanaDevolverLibro.mostrarAdvertencia();
        }
        System.out.println(resultado);//comprobamos
    }

    /**
     * método que muestra el histórico de los alquileres 
     */
    private void mostrarDatosHistoricos() {
        ArrayList<Alquiler> alquileres = modelo.obtenerHistoricosAlquiler();
        ventanaVerHistorico.cargarDatos(alquileres);
    }
}
