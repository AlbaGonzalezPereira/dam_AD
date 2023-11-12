# **Descripción del proyecto**
En este proyecto desarrollareis una aplicación que permita gestionar los alquileres de libros de una biblioteca. La biblioteca consta de varios socios y libros.

Información a almacenar
<div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea301/tabla.PNG" alt="tabla datos" style = "width: 30%"></div>
<br>

Un socio puede alquilar los libros que quiera, dentro de los libros que hay disponibles. También puede devolverlos cuando quiera.


# **Funcionalidades de la aplicación**
La aplicación debe permitir:

* Ver la información de los socios de la biblioteca
* Ver los libros que hay disponibles (sin alquilar) en un momento dado
* Ver los libros que están alquilados (queremos saber, también, desde qué fecha están alquilados).
* Ver un histórico de los libros alquilados en el pasado, mostrando quién alquiló cada libro y en qué fechas.

# **Tareas a realizar**
* Diseñar una base de datos en MySQL que cumpla con los requisitos de la aplicación
* Insertar en la BD datos ficticios.
* Desarrollar la aplicación Java usando JDBC para conectarte a la base de datos.


## **CONSIDERACIONES DEL PATRÓN MVC**
Hay que tener en cuenta las siguientes consideraciones:

* **El controlador:**<br><br>
implementará la interfaz ActionListener para poder capturar los eventos cuando se pulse un botón de la interfaz.
dispondrá del método ``actionPerformed`` para capturar eventos
consultará el elemento que lanzó el evento usando: ``e.getActionCommand().equals("NOMBREBOTON")``

* **En la vista:**<br><br>
hay que asignarle a los botones el controlador como listener: ``nombreBoton.addActionListener(controlador);``
establecer un nombre para saber quien lanzó el evento: ``nombreBoton.setActionCommand("NOMBREBOTON");``

* La cadena ``NOMBREBOTON`` tendrá que ser única para cada botón para evitar confundirlos.

# **Imágenes**
<div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea301/img1.PNG" alt="Imagen aplicación" style = "width: 80%"></div>
<div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea301/img2.PNG" alt="Imagen aplicación" style = "width: 80%"></div>