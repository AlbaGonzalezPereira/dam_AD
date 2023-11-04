/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.VentanaAlquilarLibro;
import vista.VentanaDevolverLibro;
import vista.VentanaLibrosAlquilados;
import vista.VentanaLibrosDisponibles;
import vista.VentanaPrincipal;
import vista.VentanaVerHistorico;
import vista.VentanaVerSocios;
import vista.Vista;

public class Controlador implements ActionListener{
    private Vista vista;
    private VentanaAlquilarLibro ventanaAlquilarLibro;
    private VentanaDevolverLibro ventanaDevolverLibro;
    private VentanaLibrosAlquilados ventanaLibrosAlquilados;
    private VentanaLibrosDisponibles ventanaLibrosDisponibles;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaVerHistorico ventanaVerHistorico;
    private VentanaVerSocios ventanaVerSocios;
    private Modelo modelo;
    
    public Controlador(Vista vista, Modelo modelo){
        ventanaPrincipal= new VentanaPrincipal();
        this.vista = vista;
        this.modelo = modelo;
    }

    
    public void ejecutar(){
        ventanaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getActionCommand().equals("BUSCAR")){
//            String resultado = modelo.obtenerDatosEmpleado(vista.getNumero());
//            vista.datosAtabla(resultado);
//        }
System.out.println("dsdsas");
    } 
}