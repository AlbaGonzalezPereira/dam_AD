package vista;

import controlador.Controlador;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * clase de la interfaz VentanaAlquilarLibro
 * @author alba_
 */
public class VentanaAlquilarLibro extends JFrame implements Ventana {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textDNI;
        //declaramos los botones Alquilar y Cancelar
        private JButton btnCancelar;
        private JButton btnAlquilar;

	public VentanaAlquilarLibro() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlquiler = new JLabel("ALQUILER LIBRO");
		lblAlquiler.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlquiler.setBounds(140, 29, 147, 20);
		contentPane.add(lblAlquiler);

		textCodigo = new JTextField();
		textCodigo.setBounds(201, 90, 104, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(201, 133, 104, 20);
		contentPane.add(textDNI);

		JLabel lblCodigo = new JLabel("Código libro:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(102, 93, 89, 17);
		contentPane.add(lblCodigo);

		JLabel lblDni = new JLabel("DNI socio:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(117, 136, 74, 14);
		contentPane.add(lblDni);

		btnAlquilar = new JButton("Alquilar");
		btnAlquilar.setBounds(306, 227, 89, 23);
		contentPane.add(btnAlquilar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(26, 227, 89, 23);
		contentPane.add(btnCancelar);
	}
        
    //insertamos los getters y setters necesarios
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnAlquilar() {
        return btnAlquilar;
    }

    public String getTextCodigo() {
        return textCodigo.getText();
    }

    public String getTextDNI() {
        return textDNI.getText();
    }

    /**
     * método que nos vacía los campos de código y dni
     */
    public void limpiar() {
        textCodigo.setText("");
        textDNI.setText("");
    }
    
    /**
     * método que añade a los botones una escucha
     * @param control 
     */
    @Override
    public void agregarControlador(Controlador control) {
        //añadimos los actionListener a los botones para "escuchar" el evento
        btnAlquilar.addActionListener(control);
        btnCancelar.addActionListener(control);     
    }

    /**
     * método que muestra la advertencia en un pop up
     */
    public void mostrarAdvertencia() {
        JOptionPane.showMessageDialog(contentPane, "No se puede alquilar el libro con ese código",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
    }


}
