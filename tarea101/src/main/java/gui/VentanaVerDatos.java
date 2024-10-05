package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.AplicacionAutores;

public class VentanaVerDatos extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel etiquetaDatosAutor;
	private JLabel etiquetaNombreAutor;
	private JLabel etiquetaPaginas;
	private JLabel etiquetaEditorial;
	private JTextPane datoNombreAutor;
	private JTextPane datoPaginas;
	private JTextPane datoEditorial;
	private JButton btnVolver;
	private AplicacionAutores app;

	public VentanaVerDatos(AplicacionAutores app, String nombreAutor, String paginas, String editorial) {
		this.app = app;

		setTitle("Aplicación autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		etiquetaDatosAutor = new JLabel("Datos autor");
		etiquetaDatosAutor.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaDatosAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		etiquetaDatosAutor.setBounds(64, 32, 169, 30);
		contentPane.add(etiquetaDatosAutor);

		datoNombreAutor = new JTextPane();
		datoNombreAutor.setEditable(false);
		datoNombreAutor.setBounds(64, 111, 169, 20);
		datoNombreAutor.setText(nombreAutor);
		contentPane.add(datoNombreAutor);

		etiquetaNombreAutor = new JLabel("Nombre:");
		etiquetaNombreAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaNombreAutor.setBounds(64, 86, 57, 14);
		contentPane.add(etiquetaNombreAutor);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(99, 278, 89, 23);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);

		etiquetaPaginas = new JLabel("Páginas:");
		etiquetaPaginas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaPaginas.setBounds(64, 142, 57, 14);
		contentPane.add(etiquetaPaginas);

		datoPaginas = new JTextPane();
		datoPaginas.setEditable(false);
		datoPaginas.setBounds(64, 167, 169, 20);
		datoPaginas.setText(paginas);
		contentPane.add(datoPaginas);

		etiquetaEditorial = new JLabel("Editorial:");
		etiquetaEditorial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etiquetaEditorial.setBounds(64, 209, 169, 14);
		contentPane.add(etiquetaEditorial);

		datoEditorial = new JTextPane();
		datoEditorial.setEditable(false);
		datoEditorial.setBounds(64, 234, 169, 20);
		datoEditorial.setText(editorial);
		contentPane.add(datoEditorial);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)){
                    this.dispose();
                }
	}
}
