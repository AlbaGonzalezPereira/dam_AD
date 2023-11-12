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
import modelo.Socio;

/**
 * clase de la interfaz VentanaVerSocios
 * @author alba_
 */
public class VentanaVerSocios extends JFrame implements Ventana{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public VentanaVerSocios() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSocios = new JLabel("LISTA SOCIOS");
		lblSocios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSocios.setBounds(158, 22, 135, 27);
		contentPane.add(lblSocios);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellidos" }));
	}

    @Override
    public void agregarControlador(Controlador control) {     
    }
    
    /**
     * método que limpia los datos en la tabla
     */
    public void limpiar(){
        //System.out.println("limpiando");//comprobamos
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // eliminamos todas las filas de la tabla
    }
    
    /**
     * método para cargar datos en la tabla
     * @param socios 
     */
    public void cargarDatos(ArrayList<Socio> socios){
        limpiar(); //limpiamos para que no carguen duplicados
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (Socio socio : socios) {
            model.addRow(new Object[]{socio.getDni(), socio.getNombre(), socio.getApellidos()});
        }
    }
}
