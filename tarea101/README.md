# **Aplicación para la gestión de autores con ficheros JSON**
## **Introducción**
En este proyecto vamos a desarrollar una aplicación que permita registrar autores y acceder a su información. Toda la información relacionada con los autores se almacenará en un fichero JSON.

La aplicación permitirá realizar lo siguiente:

+ Crear un nuevo autor.
+ Iniciar un proceso de verificación a través de un formulario.
+ Ver la información de un autor.
+ Cambiar el título de un autor.
+ Borrar el autor.
  
En el proyecto se os proporciona un paquete ``gui`` que contiene las interfaces de la aplicación y un paquete ``model`` que contiene la lógica de la aplicación. Se deberá realizar las modificaciones correspondientes sobre las clases de estos paquetes.


## **Modelo de la aplicación**
Existen diferentes estructuras en las que se podría almacenar la información dentro del fichero JSON. Un posible formato podría ser el siguiente, aunque cualquiera otra podría ser también válida:
```json
[
    {
        "autor": "María Fernández",
        "titulo": "Título 1",
        "paginas": "239",
        "editorial": "Anaya"
    },
    {
        "autor": "Elvira Nieto",
        "titulo": "Título 2",
        "paginas": "430",
        "editorial": "McMillan"
    }
]
```

Dentro de la carpeta del proyecto, en el paquete **model** se encontrarán las clases que gestionan la lógica de la aplicación.

La clase **AplicacionAutores** tendrá como atributos la ruta del fichero JSON y las diferentes ventanas de la aplicación. Además, deberá tener los siguientes métodos:

* ``private void crearFicheroJson()``: crea el fichero JSON si todavía no existe.

* ``private JSONArray obtenerAutoresJson()``: devuelve un JSONArray que contiene a todos los autores registrados en la aplicación.

* ``private int obtenerPosicionAutor(String nombreAutor, JSONArray autores)``: devuelve la posición de un autor dentro del array de autores. Si el autor no está en el array, devuelve -1.

* ``private JSONObject obtenerAutoresJson(String nombreAutor)``: devuelve todos los datos de un autor, en formato JSONObject. Si el autor no existe, devuelve null.

* ``public void ejecutar()``: ejecuta la ventana de inicio de validación.

* ``public void iniciarValidacion(String nombreAutor, String tituloLibroAutor)``: inicia la validación en base al autor y al título introducidos.

* ``public void cerrarSesion()``: cierra la sesión y vuelve a la ventana de inicio.

* ``public void crearAutor(String nombre, String titulo, String paginas, String editorial)``: registra un autor en el fichero JSON en función de los datos pasados por parámetro.

* ``public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo)``: cambia el título del libro del autor en el fichero JSON.

* ``public void borrarAutor(String nombreAutor)``: borrar el autor del fichero JSON y cierra la sesión de validación.

* ``public void mostrarVentanaCrearAutor()``: abre la ventana para crear un nuevo autor.

* ``public void mostrarVentanaVerDatos(String nombreAutor)``: abre la ventana en la que se muestran los datos del autor.

* ``public void mostrarVentanaCambiarTitulo(String nombreAutor)``: abre la ventana que permite introducir un nuevo nombre del título.

* ``public void mostrarVentanaBorrarAutor(String nombreAutor)``: abre la ventana para confirmar el borrado del autor.

La clase **Principal** tendrá un método ``main`` en el que simplemente se creará un objeto de la clase ``AplicaciónAutores`` y se llamará al método ``ejecutar()``.