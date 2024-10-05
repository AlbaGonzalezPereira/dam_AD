package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;

public class VentanaCambiarTitulo extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel etiquetaNuevoTitulo;
	private JTextField textoNuevoTitulo;
	private JButton btnCambiarTitulo;
	private JButton btnCancelar;
	private AplicacionAutores app;
	private String nombreAutor;

	public VentanaCambiarTitulo(AplicacionAutores app, String nombreAutor) {
		this.app = app;
		this.nombreAutor = nombreAutor;
		setTitle("Aplicación autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 265, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		etiquetaNuevoTitulo = new JLabel("Escribe el nuevo título del libro:");
		etiquetaNuevoTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiquetaNuevoTitulo.setBounds(21, 22, 207, 14);
		contentPane.add(etiquetaNuevoTitulo);

		textoNuevoTitulo = new JTextField();
		textoNuevoTitulo.setBounds(21, 58, 194, 20);
		contentPane.add(textoNuevoTitulo);
		textoNuevoTitulo.setColumns(10);

		btnCambiarTitulo = new JButton("Cambiar");
		btnCambiarTitulo.setBounds(134, 111, 89, 23);
		btnCambiarTitulo.addActionListener(this);
		contentPane.add(btnCambiarTitulo);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(21, 111, 89, 23);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCancelar)){
                    this.dispose();
                }
                if(e.getSource().equals(btnCambiarTitulo)){
                    app.cambiarTituloLibro(nombreAutor, textoNuevoTitulo.getText());
                }
	}

}
