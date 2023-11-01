package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import exception.UserNotFoundException;
import exception.WrongPasswordException;
import model.AplicacionUsuarios;

public class VentanaInicioSesion extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textoUsuario;
	private JTextField textoContraseña;
	private JButton btnIniciarSesion;
	private JButton btnCrearNuevoUsuario;
	private AplicacionUsuarios app;

	public VentanaInicioSesion(AplicacionUsuarios app) {
		this.app = app;

		setTitle("Aplicación usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel etiquetaInicioSesion = new JLabel("Inicio de sesión");
		etiquetaInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
		etiquetaInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaInicioSesion.setBounds(97, 26, 270, 44);
		contentPane.add(etiquetaInicioSesion);

		JLabel etiquetaUsuario = new JLabel("Usuario:");
		etiquetaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaUsuario.setBounds(160, 90, 46, 14);
		contentPane.add(etiquetaUsuario);

		textoUsuario = new JTextField();
		textoUsuario.setBounds(160, 115, 149, 20);
		contentPane.add(textoUsuario);
		textoUsuario.setColumns(10);

		JLabel etiquetaContraseña = new JLabel("Contraseña:");
		etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaContraseña.setBounds(160, 146, 149, 14);
		contentPane.add(etiquetaContraseña);

		textoContraseña = new JTextField();
		textoContraseña.setColumns(10);
		textoContraseña.setBounds(160, 171, 149, 20);
		contentPane.add(textoContraseña);

		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setBounds(176, 215, 118, 23);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);

		btnCrearNuevoUsuario = new JButton("Crear nuevo usuario");
		btnCrearNuevoUsuario.setBounds(10, 303, 149, 23);
		btnCrearNuevoUsuario.addActionListener(this);
		contentPane.add(btnCrearNuevoUsuario);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		// System.out.println(evento);
		if (evento.equals("Iniciar sesión")) {
			this.iniciarSesion();
		} else {
			// System.out.println("este es crear usuario");//comprobamos que entra
			app.mostrarVentanaCrearUsuario();
		}

	}

	private void iniciarSesion () {
		try {
			// System.out.println("El usuario no existe");//comprobamos que entra
			app.iniciarSesion(textoUsuario.getText(), textoContraseña.getText()); // para que nos busque usuario y contraseña
			// limpiamos los campos usuario y contraseña para que no aparezcan al cerrar sesión:
			textoUsuario.setText("");
			textoContraseña.setText("");
		} catch (UserNotFoundException e) {
			//mostramos el mensaje de que el usuario no existe en un popup
			JOptionPane.showMessageDialog(contentPane, "El usuario no existe",
					"Login", JOptionPane.WARNING_MESSAGE);
			//throw new RuntimeException(e);
		} catch (WrongPasswordException e) {
			//mostramos el mensaje de que la contraseña es incorrecta en un popup
			JOptionPane.showMessageDialog(contentPane, "Contraseña incorrecta",
					"Login", JOptionPane.WARNING_MESSAGE);
			//throw new RuntimeException(e);
		}

	}

}
