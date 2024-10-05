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

public class VentanaCrearAutor extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel etiquetaCrearAutor;
	private JLabel etiquetaAutor;
	private JLabel etiquetaTituloLibro;
	private JLabel etiquetaPaginas;
	private JLabel etiquetaEditorial;
	private JTextField textoNombreAutor;
	private JTextField textoTituloLibro;
	private JTextField textoPaginas;
	private JTextField textoEditorial;
	private JButton btnCrear;
	private JButton btnCancelar;
	private AplicacionAutores app;

	public VentanaCrearAutor(AplicacionAutores app) {
		this.app = app;
		setTitle("Aplicación autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		etiquetaAutor = new JLabel("Nombre autor:");
		etiquetaAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaAutor.setBounds(65, 56, 120, 14);
		contentPane.add(etiquetaAutor);

		etiquetaCrearAutor = new JLabel("CREAR AUTOR");
		etiquetaCrearAutor.setBounds(83, 11, 154, 20);
		etiquetaCrearAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(etiquetaCrearAutor);

		textoNombreAutor = new JTextField();
		textoNombreAutor.setBounds(65, 81, 214, 20);
		contentPane.add(textoNombreAutor);
		textoNombreAutor.setColumns(10);

		etiquetaTituloLibro = new JLabel("Título libro:");
		etiquetaTituloLibro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaTituloLibro.setBounds(65, 112, 68, 14);
		contentPane.add(etiquetaTituloLibro);

		textoTituloLibro = new JTextField();
		textoTituloLibro.setColumns(10);
		textoTituloLibro.setBounds(65, 137, 214, 20);
		contentPane.add(textoTituloLibro);

		etiquetaPaginas = new JLabel("Número de páginas:");
		etiquetaPaginas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaPaginas.setBounds(65, 168, 120, 14);
		contentPane.add(etiquetaPaginas);

		textoPaginas = new JTextField();
		textoPaginas.setColumns(10);
		textoPaginas.setBounds(65, 193, 214, 20);
		contentPane.add(textoPaginas);

		etiquetaEditorial = new JLabel("Editorial:");
		etiquetaEditorial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaEditorial.setBounds(65, 224, 214, 14);
		contentPane.add(etiquetaEditorial);

		textoEditorial = new JTextField();
		textoEditorial.setColumns(10);
		textoEditorial.setBounds(65, 249, 214, 20);
		contentPane.add(textoEditorial);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(172, 299, 89, 23);
		btnCrear.addActionListener(this);
		contentPane.add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(39, 299, 89, 23);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCancelar))
                    cerrarVentana(); //cerramos la ventana
                    //Se podría crear un método que fuera cerrarVentana y setVisible(false)
                if(e.getSource().equals(btnCrear)){
                    app.crearAutor(textoNombreAutor.getText(), textoTituloLibro.getText(), textoPaginas.getText(), textoEditorial.getText());                    
                }       
        }
        
        public void cerrarVentana(){
            this.dispose();//destruye la ventana
        }
}
