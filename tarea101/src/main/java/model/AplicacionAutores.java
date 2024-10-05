package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gui.VentanaBorrarAutor;
import gui.VentanaCambiarTitulo;
import gui.VentanaCrearAutor;
import gui.VentanaInicioSesion;
import gui.VentanaMenuAutor;
import gui.VentanaVerDatos;
import java.io.IOException;
import java.util.regex.Pattern;

public class AplicacionAutores {

    private final String RUTA_FICHERO = "datos.json";
    private VentanaInicioSesion ventanaInicioSesion;
    private VentanaCrearAutor ventanaCrearAutor;
    private VentanaMenuAutor ventanaMenuAutor;
    private VentanaVerDatos ventanaVerDatos;
    private VentanaCambiarTitulo ventanaCambiarTitulo;
    private VentanaBorrarAutor ventanaBorrarAutor;

    /**
     * crea el fichero JSON si todavía no existe
     */
    private void crearFicheroJson() {
        File ruta = new File(RUTA_FICHERO);
        if (!ruta.exists()) {
            try ( FileWriter fw = new FileWriter(ruta);) {
                ruta.createNewFile();
                fw.write("[]");//escribimos los corchetes en el json
            } catch (IOException ex) {
                System.out.println("No se ha podido crear el fichero" + ex.getMessage());
            }
        }
    }

    private void guardarFicheroJson(JSONArray autores) {
        try ( FileWriter fw = new FileWriter(RUTA_FICHERO);) {
//            fw.write("[");
//            for (int i = 0; i < autores.length(); i++) {//recorremos el Array
//                JSONObject autor = autores.getJSONObject(i); //obtenemos el objeto json
//                fw.write(autor.toString()); //pasamos autor a String
//            }
//            fw.write("]");
            fw.write(autores.toString());
        } catch (IOException ex) {
            System.out.println("No se ha podido guardar el fichero" + ex.getMessage());
        }
    }

    /**
     * devuelve un JSONArray que contiene a todos los autores registrados en la aplicación
     * @return -- JSONArray
     */
    private JSONArray obtenerAutoresJson() {
        try ( FileReader fr = new FileReader(RUTA_FICHERO)) { // leemos el fichero
            JSONTokener aut = new JSONTokener(fr); //declaramos un tokener para trocear cada autor
            JSONArray autores = new JSONArray(aut); //declaramos un JSONArray de autores
            return autores; //devolvemos el array
        } catch (IOException ex) {
            System.out.println("No se ha podido obtener el fichero" + ex.getMessage());
        }
        return null;
    }

    /**
     * devuelve la posición de un autor dentro del array de autores
     * @param nombreAutor
     * @param autores
     * @return -- Si el autor no está en el array, devuelve -1
     */
    private int obtenerPosicionAutor(String nombreAutor, JSONArray autores) {
        JSONObject autor;
        for (int i = 0; i < autores.length(); i++) {
            autor = autores.getJSONObject(i);//metemos en autor un JSONObject que hace referencia a un autor
            if (autor.getString("autor").equals(nombreAutor)) {//comparamos la llave autor con el valor nombreAutor
                return i;//si es verdadero, devuelve la posición
            }
        }
        return -1; //en caso de no existir
    }

    /**
     * devuelve todos los datos de un autor, en formato JSONObject. 
     * @param nombreAutor
     * @return -- Si el autor no existe, devuelve null
     */
    private JSONObject obtenerAutoresJson(String nombreAutor) {
        JSONArray autores = obtenerAutoresJson(); //llamamos al método que nos devuelve todos los datos de los autores
        int posicion = obtenerPosicionAutor(nombreAutor, autores);
        if (posicion != -1) {
            return autores.getJSONObject(posicion);//devolvemos el autor encontrado
        }
        return null;
    }

    /**
     * ejecuta la ventana de inicio de validación
     */
    public void ejecutar() {
        crearFicheroJson(); //llamamos a la función de crear fichero
        ventanaInicioSesion = new VentanaInicioSesion(this); //creamos la ventana
        ventanaInicioSesion.setVisible(true);
        //System.out.println(obtenerAutoresJson());//comprobamos que devuelve el array
    }

    /**
     * inicia la validación en base al autor y al título introducidos
     * @param nombreAutor
     * @param tituloLibroAutor 
     */
    public void iniciarValidacion(String nombreAutor, String tituloLibroAutor) {
        JSONArray autores = obtenerAutoresJson();
        int posicion = obtenerPosicionAutor(nombreAutor, autores);
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "El autor no existe.");
        }
        if (posicion != -1) {
            JSONObject autor = autores.getJSONObject(posicion);
            if (!autor.getString("titulo").equals(tituloLibroAutor)) {
                JOptionPane.showMessageDialog(null, "Combinación de autor y título no existente.");
            } else {
                mostrarMenuAutor(nombreAutor);
            }
        }
    }

    /**
     * cierra la sesión y vuelve a la ventana de inicio
     */
    public void cerrarSesion() {
        //borramos todas las ventanas
        if (ventanaInicioSesion != null) {
            ventanaInicioSesion.setVisible(false);
            ventanaInicioSesion = null;
        }

        if (ventanaCrearAutor != null) {
            ventanaCrearAutor.setVisible(false);
            ventanaCrearAutor = null;
        }

        if (ventanaMenuAutor != null) {
            ventanaMenuAutor.setVisible(false);
            ventanaMenuAutor = null;
        }

        if (ventanaVerDatos != null) {
            ventanaVerDatos.setVisible(false);
            ventanaVerDatos = null;
        }

        if (ventanaCambiarTitulo != null) {
            ventanaCambiarTitulo.setVisible(false);
            ventanaCambiarTitulo = null;
        }

        if (ventanaBorrarAutor != null) {
            ventanaBorrarAutor.setVisible(false);
            ventanaBorrarAutor = null;
        }

        //creamos la principal y la hacemos visible
        ventanaInicioSesion = new VentanaInicioSesion(this);
        ventanaInicioSesion.setVisible(true);
    }

    /**
     * registra un autor en el fichero JSON en función de los datos pasados por parámetro
     * @param nombre
     * @param titulo
     * @param paginas
     * @param editorial 
     */
    public void crearAutor(String nombre, String titulo, String paginas, String editorial) {
        JSONObject autor = new JSONObject();
        JSONArray autores = obtenerAutoresJson();
        String patron = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$";
        //controlamos que el nombre no esté vacío
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "El autor no puede estar vacío");
            return;
        }
        
        //controlamos el nombre que cumpla el patrón de nombres en español
        if (!Pattern.matches(patron, nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre del autor contiene caracteres no permitidos");
            return;
        }

        //controlamos que el título no esté vacío
        if (titulo.equals("")) {
            JOptionPane.showMessageDialog(null, "El título no puede estar vacío");
            return;
        }

        //controlamos el número de páginas que sea un número y mayor que 0
        try {
            if (Integer.parseInt(paginas) <= 0) {
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Hay que introducir un número de páginas");
            return;
        }

        //controlamos que la editorial no esté vacía
        if (editorial.equals("")) {
            JOptionPane.showMessageDialog(null, "La editorial no puede estar vacía");
            return;
        }

        if (obtenerPosicionAutor(nombre, autores) == -1) {
            autor.put("autor", nombre);
            autor.put("titulo", titulo);
            autor.put("paginas", paginas);
            autor.put("editorial", editorial);
            autores.put(autor);//metemos autor en autores
            guardarFicheroJson(autores);//guardamos en el fichero
            ventanaCrearAutor.cerrarVentana();//una vez bien creado cerramos la ventana, no antes
        } else {
            JOptionPane.showMessageDialog(null, "El autor ya existe");
        }
    }

    /**
     * cambia el título del libro del autor en el fichero JSON
     * @param nombreAutor
     * @param nuevoTitulo 
     */
    public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo) {
        //JSONObject autor = obtenerAutoresJson(nombreAutor);//obtenemos el autor
        //autor.put("titulo", nuevoTitulo); //actualizamos el valor
        if (nuevoTitulo.equals("")) {
            JOptionPane.showMessageDialog(null, "El título no puede estar vacío");
            return;
        }

        JSONArray autores = obtenerAutoresJson();
        int posicion = obtenerPosicionAutor(nombreAutor, autores);//obtenemos la posición
        if (posicion != -1) {
            autores.getJSONObject(posicion).put("titulo", nuevoTitulo);
            guardarFicheroJson(autores);//guardamos fichero
            cerrarSesion();
        }
    }

    /**
     * borrar el autor del fichero JSON y cierra la sesión de validación
     * @param nombreAutor 
     */
    public void borrarAutor(String nombreAutor) {
        JSONArray autores = obtenerAutoresJson();//obtenemos todos los autores
        int posicion = obtenerPosicionAutor(nombreAutor, autores);//buscamos el autor con nombre nombreAutor en autores
        autores.remove(posicion);//eliminamos el autor de posición "X"
        guardarFicheroJson(autores); //guardamos el fichero sin el autor 
    }

    /**
     * abre la ventana para crear un nuevo autor
     */
    public void mostrarVentanaCrearAutor() {
        ventanaCrearAutor = new VentanaCrearAutor(this);
        ventanaCrearAutor.setVisible(true);
    }

    /**
     * abre la ventana en la que se muestran los datos del autor
     * @param nombreAutor 
     */
    public void mostrarVentanaVerDatos(String nombreAutor) {
        JSONObject autor = obtenerAutoresJson(nombreAutor);
        ventanaVerDatos = new VentanaVerDatos(this, nombreAutor, autor.getString("paginas"), autor.getString("editorial"));
        ventanaVerDatos.setVisible(true);
    }

    /**
     * abre la ventana que permite introducir un nuevo nombre del título
     * @param nombreAutor 
     */
    public void mostrarVentanaCambiarTitulo(String nombreAutor) {
        ventanaCambiarTitulo = new VentanaCambiarTitulo(this, nombreAutor);
        ventanaCambiarTitulo.setVisible(true);
    }

    /**
     * abre la ventana para confirmar el borrado del autor
     * @param nombreAutor 
     */
    public void mostrarVentanaBorrarAutor(String nombreAutor) {
        ventanaBorrarAutor = new VentanaBorrarAutor(this, nombreAutor);
        ventanaBorrarAutor.setVisible(true);
    }

    /**
     * abre la ventana para mostrar el menú del autor
     * @param nombreAutor 
     */
    public void mostrarMenuAutor(String nombreAutor) {
        ventanaMenuAutor = new VentanaMenuAutor(this, nombreAutor);
        ventanaMenuAutor.setVisible(true);
    }
}
