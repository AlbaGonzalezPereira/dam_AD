package controlador;

import exception.CodeNotFoundException;
import exception.HireNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ventanaPrincipal = new VentanaPrincipal();
        ventanaVerSocios = new VentanaVerSocios();
        ventanaLibrosDisponibles = new VentanaLibrosDisponibles();
        ventanaLibrosAlquilados = new VentanaLibrosAlquilados();
        ventanaAlquilarLibro = new VentanaAlquilarLibro();
        ventanaDevolverLibro = new VentanaDevolverLibro();
        ventanaVerHistorico = new VentanaVerHistorico();
        this.vista = vista;
        this.modelo = modelo;
        ventanaPrincipal.agregarControlador(this);
        ventanaVerSocios.agregarControlador(this);
        ventanaLibrosDisponibles.agregarControlador(this);
        ventanaLibrosAlquilados.agregarControlador(this);
        ventanaAlquilarLibro.agregarControlador(this);
        ventanaDevolverLibro.agregarControlador(this);
        ventanaVerHistorico.agregarControlador(this);

    }

    public void ejecutar() {
        ventanaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        if(e.getActionCommand().equals("BUSCAR")){
//            String resultado = modelo.obtenerDatosSocios(vista.getNumero());
//            vista.datosAtabla(resultado);
//        }
        //comprobamos que se ha pulsado el botón
        if (e.getSource().equals(ventanaPrincipal.getBtnAlquilarLibro())) {
            ventanaAlquilarLibro.setVisible(true);

        } else if (e.getSource().equals(ventanaPrincipal.getBtnDevolverLibro())) {
            //System.out.println("Devolver libro");
            ventanaDevolverLibro.setVisible(true);
        } else if (e.getSource().equals(ventanaPrincipal.getBtnLibrosAlquilados())) {
            //System.out.println("Libros alquilados");
            ventanaLibrosAlquilados.setVisible(true);
            mostrarLibrosAlquilados();
        } else if (e.getSource().equals(ventanaPrincipal.getBtnLibrosDisponibles())) {
            //System.out.println("Libros disponibles");
            ventanaLibrosDisponibles.setVisible(true);
            mostarLibrosDisponibles();
        } else if (e.getSource().equals(ventanaPrincipal.getBtnHistorico())) {
//            System.out.println("Ver historico");//comprobamos
            ventanaVerHistorico.setVisible(true);
            mostrarDatosHistoricos();
        } else if (e.getSource().equals(ventanaPrincipal.getBtnVerSocios())) {
            //System.out.println("Veer socios");
            //ventanaPrincipal.setVisible(false);
            ventanaVerSocios.setVisible(true);
            mostrarDatosSocios();

        } else if (e.getSource().equals(ventanaAlquilarLibro.getBtnCancelar())) {
            ventanaAlquilarLibro.setVisible(false);
        } else if (e.getSource().equals(ventanaAlquilarLibro.getBtnAlquilar())) {
            alquilarLibro();
            ventanaAlquilarLibro.limpiar();
            //ventanaAlquilarLibro.setVisible(false);//si queremos que cierre la ventana al alquilar un libro
        } /**
         * *********devolverLibro***************
         */
        else if (e.getSource().equals(ventanaDevolverLibro.getBtnCancelar())) {
            ventanaDevolverLibro.setVisible(false);
        } else if (e.getSource().equals(ventanaDevolverLibro.getBtnDevolver())) {
            devolverLibro();
            ventanaDevolverLibro.limpiar();
            //ventanaDevolverLibro.setVisible(false);//si queremos que cierre la ventana al devolver un libro
        }
    }

    private void mostrarDatosSocios() {
        ArrayList<Socio> socios = modelo.obtenerDatosSocios();
//        for (Socio socio : socios) {
//            System.out.println(socio);
//        }
        ventanaVerSocios.cargarDatos(socios);
        //TODO //vista.mostrarDatos(datos);
    }

    private void limpiar(Ventana vista) {
        //TODO //vista.limpiar();
    }

    private void mostarLibrosDisponibles() {
        ArrayList<Libro> librosDisp = modelo.obtenerLibrosDisponibles();
        ventanaLibrosDisponibles.cargarDatos(librosDisp);
    }

    private void mostrarLibrosAlquilados() {
        ArrayList<Alquiler> librosAlq = modelo.obtenerLibrosAlquilados();
        ventanaLibrosAlquilados.cargarDatos(librosAlq);
    }

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
        //ventanaAlquilarLibro.setTextCodigo();
    }

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

    private void mostrarDatosHistoricos() {
        ArrayList<Alquiler> alquileres = modelo.obtenerHistoricosAlquiler();
        ventanaVerHistorico.cargarDatos(alquileres);

    }
}
