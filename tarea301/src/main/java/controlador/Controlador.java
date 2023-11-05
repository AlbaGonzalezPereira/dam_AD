package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        ventanaVerSocios= new VentanaVerSocios();
        this.vista = vista;
        this.modelo = modelo;
        ventanaPrincipal.agregarControlador(this);
        ventanaVerSocios.agregarControlador(this);
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
        if(e.getSource().equals(ventanaPrincipal.getBtnAlquilarLibro())){
//        String g=modelo.obtenerDatosSocio("empleado");
//        System.out.println(g);
        }
        else if(e.getSource().equals(ventanaPrincipal.getBtnDevolverLibro())){
            System.out.println("Devolver libro");
        }
        else if(e.getSource().equals(ventanaPrincipal.getBtnLibrosAlquilados())){
            System.out.println("Libros alquilados");
        }
        else if(e.getSource().equals(ventanaPrincipal.getBtnLibrosDisponibles())){
            System.out.println("Libros disponibles");
        }
        else if(e.getSource().equals(ventanaPrincipal.getBtnHistorico())){
            System.out.println("Ver historico");
        }
        else if(e.getSource().equals(ventanaPrincipal.getBtnVerSocios())){
            //System.out.println("Veer socios");
            //ventanaPrincipal.setVisible(false);
            ventanaVerSocios.setVisible(true);
            mostrarDatosSocios();
            
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
}
