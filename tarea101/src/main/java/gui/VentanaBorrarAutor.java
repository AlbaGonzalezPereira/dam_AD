package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;

public class VentanaBorrarAutor extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel etiquetaBorrarAutor;
	private JButton btnBorrar;
	private JButton btnCancelar;
	private AplicacionAutores app;
	private String nombreAutor;

	public VentanaBorrarAutor(AplicacionAutores app, String nombreAutor) {
		this.app = app;
		this.nombreAutor = nombreAutor;
		setTitle("Aplicación autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 316, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		etiquetaBorrarAutor = new JLabel("¿Está seguro de que quiere borrar este autor?");
		etiquetaBorrarAutor.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiquetaBorrarAutor.setBounds(10, 11, 296, 34);
		contentPane.add(etiquetaBorrarAutor);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(170, 56, 89, 23);
		btnBorrar.addActionListener(this);
		contentPane.add(btnBorrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(35, 56, 89, 23);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBorrar)){
                    app.borrarAutor(nombreAutor);//borra autor
                    app.cerrarSesion();//cierra sesión
                }
                if(e.getSource().equals(btnCancelar)){
                    this.dispose();
                }
	}

}
