package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

import gui.VentanaBorrarUsuario;
import gui.VentanaCambiarContraseña;
import gui.VentanaCrearUsuario;
import gui.VentanaInicioSesion;
import gui.VentanaMenuUsuario;
import gui.VentanaVerUsuario;

public class AplicacionUsuarios {

	private final String RUTA = "src/usuarios.json";
	private VentanaInicioSesion ventanaInicioSesion;

	private VentanaCrearUsuario ventanaCrearUsuario;
	private VentanaMenuUsuario ventanaMenuUsuario;
	private VentanaVerUsuario ventanaVerUsuario;
	private VentanaCambiarContraseña ventanaCambiarContraseña;
	private VentanaBorrarUsuario ventanaBorrarUsuario;

	/**
	 * @param ventanaInicioSesion
	 * @param ventanaCrearUsuario
	 * @param ventanaMenuUsuario
	 * @param ventanaVerUsuario
	 * @param ventanaCambiarContraseña
	 * @param ventanaBorrarUsuario
	 */
	public AplicacionUsuarios() {
		// super();
		this.ventanaInicioSesion = new VentanaInicioSesion(this);
		this.ventanaCrearUsuario = new VentanaCrearUsuario(this);
		// this.ventanaMenuUsuario = new VentanaMenuUsuario(null, RUTA_FICHERO);
		// this.ventanaVerUsuario = new VentanaVerUsuario();
		// this.ventanaCambiarContraseña = ventanaCambiarContraseña;
		// this.ventanaBorrarUsuario = ventanaBorrarUsuario;
	}

	/**
	 * método que crea el fichero json
	 */
	private void crearFicheroJson() {
		try {
			// Verificamos si el archivo ya existe
			File file = new File(RUTA);

			if (file.exists()) {
				System.out.println("El archivo JSON ya existe.");
				return;
			}
			file.createNewFile(); // si no existe, lo crea

			// Creamos un objeto JSON
			JSONObject usuario = new JSONObject();

			// Creamos un array para meter todos los usuarios
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(usuario);

			// Escribir el contenido del arreglo JSON en el archivo
			FileWriter fileWriter = new FileWriter(RUTA);
			jsonArray.write(fileWriter);

			// Cerrar el escritor de archivos
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * método que nos devuelve un array con todos los usuarios
	 * @return
	 */
	private JSONArray obtenerUsuariosJson() {
		try {
			// Leemos el archivo JSON
			FileReader reader = new FileReader(RUTA);

			// Creamos un JSONTokener para tokenizar el archivo
			JSONTokener tokener = new JSONTokener(reader);

			// Creamos un JSONArray a partir del archivo anterior
			JSONArray jsonArray = new JSONArray(tokener);

			reader.close(); // Cerramos
			return jsonArray; // devolvemos el array

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Método que nos da la posición del usuario
	 * @param nombreUsuario
	 * @param usuarios
	 * @return
	 */
	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {
		//recorremos el array de usuarios
		for (int i = 0; i < usuarios.length(); i++) {
			JSONObject jsonObject = usuarios.getJSONObject(i);
			String nombre = jsonObject.getString("nombre");//cogemos el nombre
			if (nombre.equals(nombreUsuario)) //comprobamos si coincide con el que se pasa
				return i;//devolvemos la posición del usuario en el array
		}
		return -1;// devolvemos -1 si no lo encuentra
	}

	/**
	 * método que nos devuelve un usuario
	 * @param nombreUsuario
	 * @return
	 */
	private JSONObject obtenerUsuarioJson(String nombreUsuario) {
		JSONArray usuarios = obtenerUsuariosJson();
		for (int i = 0; i < usuarios.length(); i++) {
			JSONObject jsonObject = usuarios.getJSONObject(i);

			String nombre = jsonObject.getString("nombre");
			//comprobamos si coincide
			if (nombre.equals(nombreUsuario))
				return jsonObject;
		}
		return null;// en caso de que no exista

	}

	/**
	 * método que inicia el programa y crea el fichero en caso de que no exista
	 */
	public void ejecutar() {
		crearFicheroJson();// llamamos al método
		ventanaCrearUsuario.setVisible(false);// ocultamos la ventana de crear usuario
		ventanaInicioSesion.setVisible(true);
	}

	/**
	 * método que inicia la sesión si encuentra el usuario y la contraseña
	 * @param nombreUsuario
	 * @param contraseñaUsuario
	 */
	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {
		JSONArray usus = obtenerUsuariosJson(); // llamamos a la función
		// Iteramos el json con los usuarios
		for (int i = 0; i < usus.length(); i++) {
			JSONObject jsonObject = usus.getJSONObject(i);
			// cogemos nombre y contraseña
			String nombre = jsonObject.getString("nombre");
			String contraseña = jsonObject.getString("contraseña");
			
			if (nombre.equals(nombreUsuario) && contraseña.equals(contraseñaUsuario)) {//comprobamos si concuerdan
				mostrarVentanaMenuUsuario(nombreUsuario);
				return;
			} else if (nombre.equals(nombreUsuario) && !contraseña.equals(contraseñaUsuario)) {
				System.out.println("¡¡La contraseña es incorrecta!!");
				return;
			}
		}
		System.out.println("¡¡El usuario no existe!!");

	}

	/**
	 * método que cierra la sesión de un usuario
	 */
	public void cerrarSesion() {
		ventanaMenuUsuario.setVisible(false);// ocultamos la ventana de menú usuario
	}

	/**
	 * método que crea un usuario
	 * @param nombre
	 * @param contraseña
	 * @param edad
	 * @param correo
	 */
	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {
		JSONArray usus = obtenerUsuariosJson(); // llamamos a la función para obtener todos los usuarios
		JSONObject nuevoUsuario = new JSONObject(); //creamos un nuevo usuario
		//añadimos los atributos
		nuevoUsuario.put("nombre", nombre);
		nuevoUsuario.put("contraseña", contraseña);
		nuevoUsuario.put("edad", edad);
		nuevoUsuario.put("correo", correo);
		//lo añadimos al array
		usus.put(nuevoUsuario);
		try {
			String ruta = "src/usuarios.json";
			// Abrimos el archivo JSON para escritura
			FileWriter writer = new FileWriter(ruta);
			// Escribimos el contenido actualizado en el archivo
			usus.write(writer);
			// Cerramos el writer
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * método que cambia la contraseña de un usuario
	 * @param nombreUsuario
	 * @param nuevaContraseña
	 */
	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {
		JSONObject usu = obtenerUsuarioJson(nombreUsuario);
		borrarUsuario(nombreUsuario);
		usu.put("contraseña", nuevaContraseña);
		crearUsuario(nombreUsuario, nuevaContraseña, usu.getString("edad"), usu.getString("correo"));
		System.out.println("Cambio correcto");

	}

	/**
	 * método que borra un usuario dado
	 * @param nombreUsuario
	 */
	public void borrarUsuario(String nombreUsuario) {
		JSONArray usus = obtenerUsuariosJson(); // obtenemos los usuarios
		JSONArray ususGuardados = new JSONArray(); // meteremos los usuarios que vamos a guardar
		// Iteramos el json con los usuarios
		for (int i = 0; i < usus.length(); i++) {

			JSONObject jsonObject = usus.getJSONObject(i);

			String nombre = jsonObject.getString("nombre");
			String contraseña = jsonObject.getString("contraseña");
			int edad = jsonObject.getInt("edad");
			String correo = jsonObject.getString("correo");
			if (!nombre.equals(nombreUsuario)) {
				ususGuardados.put(jsonObject);
			}
		}

		try {

			String filePath = "src/usuarios.json";
			// Abre el archivo JSON para escritura
			FileWriter writer = new FileWriter(filePath);

			// Escribe el contenido actualizado en el archivo

			ususGuardados.write(writer);

			// Cierra el escritor de archivos
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * método que muestra la ventana de crearUsuario
	 */
	public void mostrarVentanaCrearUsuario() {
		ventanaInicioSesion.setVisible(false);
		ventanaCrearUsuario.setVisible(true);
	}

	
	/**
	 * método para mostrar la ventana del menú. CREADO A MAYORES
	 * @param nombreUsuario
	 */
	public void mostrarVentanaMenuUsuario(String nombreUsuario) {
		ventanaInicioSesion.setVisible(false);
		ventanaMenuUsuario = new VentanaMenuUsuario(this, nombreUsuario);
		ventanaMenuUsuario.setVisible(true);// visualizamos la ventana del usuario
	}

	/**
	 * método para mostrar la ventana de datos
	 * @param nombreUsuario
	 */
	public void mostrarVentanaVerUsuario(String nombreUsuario) {
		// ventanaInicioSesion.setVisible(false);
		JSONObject usuario = obtenerUsuarioJson(nombreUsuario);
		ventanaVerUsuario = new VentanaVerUsuario(this, nombreUsuario, usuario.getString("edad"),
				usuario.getString("correo"));
		ventanaVerUsuario.setVisible(true);// visualizamos la ventana del usuario
	}

	/**
	 * método que muestra la ventana de Cambiar contraseña
	 * @param nombreUsuario
	 */
	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {
		ventanaCambiarContraseña = new VentanaCambiarContraseña(this, nombreUsuario);
		ventanaCambiarContraseña.setVisible(true);
	}

	/**
	 * método que muestra la ventana borrar usuario
	 * @param nombreUsuario
	 */
	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {
		ventanaBorrarUsuario = new VentanaBorrarUsuario(this, nombreUsuario);
		ventanaBorrarUsuario.setVisible(true);
	}

	/**
	 * método para cerrar la ventana de usuario. CREADO NUEVO
	 */
	public void cerrarVerUsuario() {
		ventanaVerUsuario.setVisible(false);// cierra la ventana
	}

	/**
	 * método que cuando le damos a "Cancelar" cierra la ventana de cambiar contraseña. CREADO NUEVO
	 */
	public void cerrarVentanaCambiarContraseña() {
		ventanaCambiarContraseña.setVisible(false);// cerramos la ventana de cambiar contraseña
	}

	/**
	 * método que cierra la ventana de borrar Usuario
	 */
	public void cerrarVentanaBorrarUsuario() {
		ventanaBorrarUsuario.setVisible(false);
		
	}
}
