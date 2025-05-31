# TAREA 301

## Descripción del proyecto
En este proyecto vamos a desarrollar una aplicación que permita validar y gestionar la estructura de un hospital.

El **diagrama conceptual** de la base de datos es el siguiente:
![Diagrama conceptual](diagrama.png)

El **script** correspondiente de la base de datos será ``bd-hospital.sql``.

## Tareas a realizar
Se debe desarrollar una aplicación utilizando **Hibernate** que tenga las siguientes **funcionalidades**:

- Crear, borrar (por id) y modificar los datos de un doctor.
- Crear, borrar (por nombre) y modificar los datos de un paciente.
- Asignar un doctor a un paciente.
    - La asignación se hará a partir del nombre del doctor y del paciente.
    - Se pedirá por teclado introducir el nombre del doctor y del paciente.
- Indicar la fecha de fin del tratamiento de un paciente.
  - El método recibirá el nombre del paciente, la fecha de inicio, el tipo y la fecha de fin del tratamiento.
- Cambiar el hospital de un tratamiento.
  - El método recibirá el id del tratamiento, el nombre del hospital en donde está ahora el tratamiento y el nombre del hospital en dónde se va a realizar el tratamiento a partir de ahora.
- Mostrar los datos de un Paciente (id, nombre, fecha_nacimiento, direccion, tratamientos que recibe y citas que tiene).
  - La consulta se hará a partir del nombre del Paciente que introduzca el usuario.
- Mostrar los datos de los tratamientos y el hospital en el que se realiza.
  - La consulta se hará a partir del nombre del hospital que introduzca el usuario.
- Mostrar el número total de tratamientos que tiene cada hospital.
  - La consulta se hará a partir del nombre del hospital que introduzca el usuario.

**NOTA**
- Todas las relaciones tendrán que ser bidireccionales
- Todos los datos se pedirán por teclado
- Habrá que crear un menú que permita probar cada una de las funcionalidades anteriores