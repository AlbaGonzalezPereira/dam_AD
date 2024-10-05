package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;

public class VentanaMenuAutor extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel etiquetaMenuAutor;
	private JTextPane textoNombreAutor;
	private JButton btnVerDatos;
	private JButton btnCambiarTituloLibro;
	private JButton btnBorrarAutor;
	private JButton btnCerrarValidacion;
	private AplicacionAutores app;
	private String nombreAutor;

	public VentanaMenuAutor(AplicacionAutores app, String nombreAutor) {
		this.app = app;
		this.nombreAutor = nombreAutor;

		setTitle("Aplicación autores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		etiquetaMenuAutor = new JLabel("Menú del autor:");
		etiquetaMenuAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		etiquetaMenuAutor.setBounds(10, 24, 147, 14);
		contentPane.add(etiquetaMenuAutor);

		btnVerDatos = new JButton("Ver datos");
		btnVerDatos.setBounds(71, 64, 163, 23);
		btnVerDatos.addActionListener(this);
		contentPane.add(btnVerDatos);

		btnCambiarTituloLibro = new JButton("Cambiar título del libro");
		btnCambiarTituloLibro.setBounds(71, 98, 163, 23);
		btnCambiarTituloLibro.addActionListener(this);
		contentPane.add(btnCambiarTituloLibro);

		btnBorrarAutor = new JButton("Borrar autor");
		btnBorrarAutor.setBounds(71, 132, 163, 23);
		btnBorrarAutor.addActionListener(this);
		contentPane.add(btnBorrarAutor);

		btnCerrarValidacion = new JButton("Cerrar validación");
		btnCerrarValidacion.setBounds(150, 227, 145, 23);
		btnCerrarValidacion.addActionListener(this);
		contentPane.add(btnCerrarValidacion);

		textoNombreAutor = new JTextPane();
		textoNombreAutor.setEditable(false);
		textoNombreAutor.setBounds(167, 24, 132, 20);
		textoNombreAutor.setText(nombreAutor);
		contentPane.add(textoNombreAutor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBorrarAutor)){
                    app.mostrarVentanaBorrarAutor(nombreAutor);
                }
                if(e.getSource().equals(btnCambiarTituloLibro)){
                    app.mostrarVentanaCambiarTitulo(nombreAutor);
                }
                if(e.getSource().equals(btnVerDatos)){
                    app.mostrarVentanaVerDatos(nombreAutor);
                }
                if(e.getSource().equals(btnCerrarValidacion)){
                    app.cerrarSesion();
                }
	}

}
