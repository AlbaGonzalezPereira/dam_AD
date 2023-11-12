package vista;

import controlador.Controlador;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * clase que muestra la ventana principal del programa
 * @author alba_
 */
public class VentanaPrincipal extends JFrame implements Ventana{
    
        //private Controlador controlador;

	private JPanel contentPane;
        private JButton btnAlquilarLibro;
        private JButton btnDevolverLibro;
        private JButton btnLibrosDisponibles;
        private JButton btnVerSocios;
        private JButton btnLibrosAlquilados;
        private JButton btnHistorico;

	public VentanaPrincipal() {
               
            
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                /****************creamos los objetos button*******************/
		btnAlquilarLibro = new JButton("Alquilar libro");
		btnAlquilarLibro.setBounds(220, 57, 131, 23);
		contentPane.add(btnAlquilarLibro);
               
		btnDevolverLibro = new JButton("Devolver libro");
		btnDevolverLibro.setBounds(220, 91, 131, 23);
		contentPane.add(btnDevolverLibro);

		btnLibrosDisponibles = new JButton("Ver libros disponibles");
		btnLibrosDisponibles.setBounds(21, 91, 131, 23);
		contentPane.add(btnLibrosDisponibles);

		btnVerSocios = new JButton("Ver socios");
		btnVerSocios.setBounds(21, 57, 131, 23);
		contentPane.add(btnVerSocios);

		btnLibrosAlquilados = new JButton("Ver libros alquilados");
		btnLibrosAlquilados.setBounds(21, 130, 131, 23);
		contentPane.add(btnLibrosAlquilados);

		btnHistorico = new JButton("Ver histórico");
		btnHistorico.setBounds(220, 130, 131, 23);
		contentPane.add(btnHistorico);
	}

    /**
     * método que añade a los botones una escucha
     * @param control 
     */
        @Override
    public void agregarControlador(Controlador control) {
        //agregamos a cada botón su contrador, ya que hicimos un controlador general
        btnAlquilarLibro.addActionListener(control);
        btnDevolverLibro.addActionListener(control);
        btnHistorico.addActionListener(control);
        btnLibrosDisponibles.addActionListener(control);
        btnLibrosAlquilados.addActionListener(control);
        btnVerSocios.addActionListener(control);
    }

    /*********************declaramos getters********************************/
    public JButton getBtnAlquilarLibro() {
        return btnAlquilarLibro;
    }

    public JButton getBtnDevolverLibro() {
        return btnDevolverLibro;
    }

    public JButton getBtnLibrosDisponibles() {
        return btnLibrosDisponibles;
    }

    public JButton getBtnVerSocios() {
        return btnVerSocios;
    }

    public JButton getBtnLibrosAlquilados() {
        return btnLibrosAlquilados;
    }

    public JButton getBtnHistorico() {
        return btnHistorico;
    }
    
    public JButton getAlquilarLibro(){
        return btnAlquilarLibro;
    }
}
