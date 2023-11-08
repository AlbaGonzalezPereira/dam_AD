package vista;

import controlador.Controlador;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Libro;

public class VentanaLibrosDisponibles extends JFrame implements Ventana {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public VentanaLibrosDisponibles() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDisponibles = new JLabel("LIBROS DISPONIBLES");
		lblDisponibles.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisponibles.setBounds(128, 24, 192, 27);
		contentPane.add(lblDisponibles);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Titulo", "Autor" }));
	}

    @Override
    public void agregarControlador(Controlador control) {
        
    }

    public void limpiar(){
        System.out.println("limpiando");
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // eliminamos todas las filas de la tabla
    }
    
    public void cargarDatos(ArrayList<Libro> librosDisp) {
        limpiar();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (Libro libro : librosDisp) {
            model.addRow(new Object[]{libro.getCodigo(), libro.getTitulo(), libro.getAutor()});

        }
        
    }

}
