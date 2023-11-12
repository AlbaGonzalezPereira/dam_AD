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

public class VentanaDevolverLibro extends JFrame implements Ventana{

	private JPanel contentPane;
	private JTextField textCodigo;
        private JButton btnDevolver;
        private JButton btnCancelar;

	public VentanaDevolverLibro() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDevolver = new JLabel("DEVOLVER LIBRO");
		lblDevolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDevolver.setBounds(143, 45, 161, 20);
		contentPane.add(lblDevolver);

		textCodigo = new JTextField();
		textCodigo.setBounds(207, 104, 122, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(127, 104, 70, 17);
		contentPane.add(lblCodigo);

		btnDevolver = new JButton("Devolver");
		btnDevolver.setBounds(294, 178, 89, 23);
		contentPane.add(btnDevolver);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(51, 178, 89, 23);
		contentPane.add(btnCancelar);
	}

    public JButton getBtnDevolver() {
        return btnDevolver;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public String getTextCodigo() {
        return textCodigo.getText();
    }

    @Override
    public void agregarControlador(Controlador control) {
        btnDevolver.addActionListener(control);
        btnCancelar.addActionListener(control);
    }
    
    public void limpiar(){
        textCodigo.setText("");
    }

    /**
     * método que muestra la advertencia cuando no se puede devolver un libro
     */
    public void mostrarAdvertencia() {
        JOptionPane.showMessageDialog(contentPane, "No se puede devolver el libro con ese codigo",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
        }

}
