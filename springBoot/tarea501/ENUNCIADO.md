# PROYECTO 501

## Enunciado

Se desea diseñar un sistema formado por **microservicios** que permitan gestionar un sistema de reservas y comentarios. Este sistema estará formado por los siguientes microservicios los cuales se podrán comunicar entre ellos:

- Servicio de Reservas de Hoteles
- Servicio de Usuarios
- Servicio de Comentarios y Comentarios
- Servidor Eureka
- API Gateway


### SOBRE LAS RUTAS DE APLICACIÓN
Todas las rutas que se indican en las especificaciones serán para irse acumulando una sobre otra.

Si se comenta que la ruta raíz del microservicio es ``/microservicio`` y después se comenta que un método se ejecuta sobre la ruta ``/metodo``. La ruta resultante del acceso al método será ``/microservicio/metodo``

## Características técnicas
A continuación, se detallan las **características técnicas** de cada uno de los microservicios:

### Servidor Eureka
El servidor Eureka **atenderá las peticiones de conexión** en el puerto ``8500``.

Su tarea será la de permitir el registro de los servicios que forman el sistema.

### API Gateway
Será el **punto de entrada al sistema**. A través de él un usuario podrá acceder al resto de microservicios del sistema.

**Características técnicas**:

- El servicio se llamará ``gateway``.
- Estará ejecutándose en el puerto ``8080``.
- Hará uso del servidor Eureka y no tendrá porque aparecer registrado en él.

**Funcionalidades**:

- Será el punto de entrada y de utilización de las funcionalidades del sistema.
- Todas las peticiones de los servicios deberán poder ser accesibles y funcionales a través de este sistema.
- Deberá permitir utilizar las API Rest de los servicios que lo implementen y GraphIQL del servicio que lo implemente.


### Microservicio de Usuarios
Se encargará de todas las **gestiones relacionadas con los usuarios** del sistema.

**Características técnicas**:

- El servicio se llamará ``usuarios``.
- Implementará una **API Rest** para la comunicación entre el exterior y el servicio.
- La API tendrá como ruta raíz ``/usuarios`` y, a partir de ella, se irán construyendo el resto de rutas.
- El servicio estará ejecutándose en el puerto ``8502``.
- Tendrá configurado Hibernate en modo ``validate`` para no pisar la estructura de la siguiente base de datos.
- Utilizará una base de datos MySQL llamada ``usuariosProyecto``.


**Funcionalidades**:

- Crear un nuevo usuario (``crearUsuario``):
    - Se encargará de registrar un nuevo usuario en el sistema.
    - URL de ejecución ``/registrar``
    - Método de consulta: ``POST``
    - Recibirá un objeto con la información del usuario (``nombre``, ``correo_electrónico``, ``direccion`` y ``contraseña``)
    - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

- Actualizar nuevo usuario (``actualizarUsuario``):
    - Se encargará de actualizar los datos un usuario del sistema.
    - URL de ejecución ``/registrar``
    - Método de consulta: ``PUT``
    - Recibirá un objeto con la información del usuario (``id``, ``nombre``, ``correo_electrónico``, ``direccion`` y ``contraseña``)
    - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

- Eliminar usuario (``eliminarUsuario``):
    - Eliminará un usuario del sistema.
    - URL de ejecución la raíz del servicio
    - Método de consulta: ``DELETE``
    - Recibirá un objeto con la información del usuario (``nombre`` y ``contraseña``)
    - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

- Validar usuario (``validarUsuario``):
    - Se encargará de comprobar si la tupla usuario y contraseña está en el sistema.
    - URL de ejecución ``/validar``
    - Método de consulta: ``POST``
    - Recibirá un objeto con la información del usuario (``nombre`` y ``contraseña``)
    - Devolverá un booleano indicando si es correcto o no.

- Obtener el nombre de un usuario a partir de su identificador (``obtenerInfoUsuarioPorId``):
    - Se encargará de obtener qué nombre de usuario le corresponde a un identificador en particular.
    - URL de ejecución ``/info/id/``
    - Método de consulta: ``GET``
    - Recibirá un parámetro en la URL denominado ``id``.
    - Devolverá un String con el nombre del usuario.

- Obtener el ID de un usuario a partir de su nombre (``obtenerInfoUsuarioPorNombre``):
    - Se encargará de obtener qué ID de un usuario a partir de su nombre.
    - URL de ejecución ``/info/nombre/``
    - Método de consulta: ``GET``
    - Recibirá un parámetro en la URL denominado ``nombre``.
    - Devolverá un String con el ID del usuario.

- Comprobar si un usuario existe (``checkIfExist``):
    - Recibirá un ID de usuario y devolverá un booleano indicando si ese ID existe o no.
    - URL de ejecución ``/checkIfExist/``
    - Método de consulta: ``GET``
    - Recibirá un parámetro en la URL denominado ``id``.
    - Devolverá un Booleano indicando si el usuario existe o no.
  
### Microservicio de Reservas
Este servicio será el encargado de llevar el registro de los datos de los hoteles, las habitaciones que cada hotel tiene y las reservas hechas por los usuarios.

**Características técnicas**:
- El servicio se llamará ``reservas``.
- Implementará una API Rest para la comunicación entre el exterior y el servicio.
- La ruta raíz de la API será ``/reservas``
- El servicio estará ejecutándose en el puerto ``8501``.
- Tendrá configurado Hibernate en modo ``validate`` para no pisar la estructura de la siguiente base de datos.
- Utilizará una base de datos MySQL llamada ``reservasProyecto``.

**Funcionalidades**:
Salvo que se indique lo contrario, todas las peticiones de los siguientes métodos recibirán el ``usuario`` y ``contraseña`` del usuario. Se validará dicha información frente al servicio ``usuarios`` y permitirán realizar la consulta si el nombre y contraseña concuerdan.

- **Gestión de habitaciones**:

Los métodos siguientes se ejecutarán añadiendo a la ruta raíz de la API la siguiente ruta ``/habitacion``
  - Crear una nuevo habitación (``crearHabitación``):
      - Se encargará de dar de alta una nueva habitación en un hotel.
      - URL de ejecución: la ruta raíz del gestor de habitaciones.
      - Método de consulta: ``POST``.
      - Recibirá un objeto con la información de la habitación (numeroHabitacion, tipo, precio y idHotel)
      - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Actualizar información de una habitación (``actualizarHabitacion``):
      - Se encargará de actualizar los datos de una habitación en un hotel.
      - URL de ejecución: la ruta raíz del gestor de habitaciones.
      - Método de consulta: ``PATCH``.
      - Recibirá un objeto con la información de la habitación (``id``, ``numeroHabitacion``, ``tipo``, ``precio``, ``idHotel`` y ``disponible``)
      - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Eliminar habitación (``eliminarHabitacion``):
      - Se encargará de eliminar los datos de una habitación.
      - URL de ejecución: la ruta raíz del gestor de habitaciones.
      - Método de consulta: ``DELETE``.
      - Recibirá a través de la URL el identificador de la habitación
      - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

- **Gestión de hoteles**
Los métodos siguientes se ejecutarán añadiendo a la ruta raíz de la API la siguiente ruta: ``/hotel``.

  - Crear un nuevo hotel (``crearHotel``):
      - Se encargará de dar de alta un nuevo hotel.
      - URL de ejecución: la ruta raíz del gestor de hoteles.
      - Método de consulta: ``POST``.
      - Recibirá un objeto con la información del hotel (``nombre`` y ``direccion``)
      - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Modificar información de un hotel (``actualizarHotel``):
      - Se encargará de actualizar los datos de un hotel.
      - URL de ejecución: la ruta raíz del gestor de hoteles.
      - Método de consulta: ``PATCH``.
      - Recibirá un objeto con la información de la habitación (``id``, ``nombre`` y ``direccion``)
      - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Eliminar hotel (``eliminarHotel``):
      - Se encargará de eliminar los datos de un hotel junto con todas sus habitaciones.
      - URL de ejecución: la ruta raíz del gestor de hoteles.
      - Método de consulta: ``DELETE``.
      - Recibirá a través de la URL el identificador del hotel.
      - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Obtener el ID de un hotel a partir de su nombre (``obtenerIdApartirNombre``):
      - Buscará el ID asociado al nombre del hotel.
      - URL de ejecución: ``/id``.
      - Método de consulta: ``POST``.
      - Recibirá a través de la URL el ``nombre`` del hotel.
      - Devolverá una cadena indicando el ID del hotel en cuestión.

  - Obtener el nombre de un hotel a partir de su identificador (``obtenerNombreAPartirId``):
      - Buscará el nombre asociado a un ID.
      - URL de ejecución: ``/nombre``.
      - Método de consulta: ``POST``.
      - Recibirá a través de la URL el ``id`` del hotel.
      - Devolverá una cadena indicando el ``nombre`` del hotel en cuestión.

- **Gestión de reservas**

Los métodos siguientes se ejecutarán sobre la raíz del microservicio.

  - Crear reserva (``crearReserva``):
    - Se encargará de crear una nueva reserva.
    - URL de ejecución: la ruta raíz del microservicio.
    - Método de consulta: ``POST``.
    - Recibirá un objeto con la información de la reserva (``fecha_inicio``, ``fecha_fin`` y ``habitacion_id``)
    - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Cambiar estado de una reserva (``cambiarEstado``):
    - Se encargará de modificar el estado de una reserva.
    - URL de ejecución: la ruta raíz del microservicio.
    - Método de consulta: ``PATCH``.
    - Recibirá un objeto con la información de la reserva (``reserva_id`` y ``estado``)
    - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

  - Listar reservas del usuario (``listarReservasUsuario``):
    - Se encargará de listar las reservas que están asociadas al usuario.
    - URL de ejecución: la ruta raíz del microservicio.
    - Método de consulta: ``GET``.
    - Solo recibirá la información de ``usuario`` y ``contraseña``.
    - Devolverá una lista con la información de las reservas (``fecha_inicio``, ``fecha_fin`` y ``habitacion_id``).

  - Listar reservas según estado (``listarReservasSegunEstado``):
    - Se encargará de listar todas las reservas que tengan un determinado estado independientemente del usuario que la haya hecho.
    - URL de ejecución: la ruta raíz del microservicio.
    - Método de consulta: ``GET``.
    - Recibirá a través de la URL el ``estado`` de la habitación.
- Devolverá una lista con la información de las reservas (``fecha_inicio``, ``fecha_fin`` y ``habitacion_id``).

  - Comprobar reserva (``checkReserva``):
    - Se encargará de validar si una reserva está asociada a un usuario y a un hotel en concreto.
    - URL de ejecución: ``/check``
    - Método de consulta: ``GET``.
    - Recibirá a través de la URL el ``idUsuario``, el ``idHotel`` y el ``idReserva``.
    - Devolverá una booleano indicando si existe o si no.
    - Este método no recibirá la información de usuario y contraseña.

### Microservicio de Comentarios
Este servicio se encargará de gestionar los comentarios que los usuarios hacen sobre las reservas.

**Características técnicas**:

- El servicio se llamará ``comentarios``.
- Implementará una **API GraphQL** para la comunicación entre el exterior y el servicio.
- Deberá habilitarse GraphIQL para la realización de peticiones.
- GraphIQL deberá utilizar el endpoint ``/comentarios`` para resolver las peticiones.
- El servicio estará ejecutándose en el puerto ``8503``.
- Utilizará una base de datos MongoDB llamada ``comentariosProyecto``.
- La base de datos mongoDB tendrá una colección llamada ``comentarios`` 

**USUARIO Y CONTRASEÑA**

Salvo que se indique lo contrario, todas las peticiones de las siguientes funcionalidades recibirán el ``usuario`` y ``contraseña`` del usuario. Se validará dicha información frente al servicio ``usuarios`` y permitirán realizar la consulta si el nombre y contraseña concuerdan.

**INFORMACIÓN RECIBIDA E INFORMACIÓN ALMACENADA**
Salvo que se indique lo contrario, todos los **parámetros que recibirán** las siguientes funcionalidades serán nombres, es decir, recibirán el nombre del hotel y el nombre del usuario. Los únicos **valores numéricos** serán: la **puntuación** y el **identificador** de la reserva.

A la hora de **almacenarlos** en la base de datos se almacenarán los **identificadores** de los nombres por lo que habrá que hacer las **llamadas a los servicios** necesarios para hacer las conversiones.

**Funcionalidades**:

- Crear comentario (``crearComentario``):
  - Se encargará de crear y almacenar el comentario de un usuario sobre una reserva en un determinado hotel.
  - Recibirá un objeto con la información del comentario (``nombreHotel``, ``id de reserva``, ``puntuación`` y ``comentario``)
  - Devolverá el mismo objeto recibido a modo de confirmación.
  - Consultará el microservicio de reservas para obtener el ``id de hotel`` a partir del ``nombreHotel``.
  - Consultará el microservicio de usuarios para obtener el ``id de usuario`` a partir del nombre de usuario.
  - Deberá comprobar frente al microservicio reservas (método ``checkReserva``) si la combinación (idUsuario - idHotel - idReserva) existe antes de poder crear el comentario.
  - Si el usuario ya hizo un comentario sobre esa combinación (idUsuario - idHotel - idReserva) no se podrá realizar el comentario.

- Eliminar todos los comentarios (``eliminarComentarios``):
  - Se encargará de eliminar todos los comentarios del sistema.
  - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.
  - Este método no recibirá la información de usuario y contraseña

- Eliminar un comentario de un usuario (``eliminarComentarioDeUsuario``):
  - Se encargará de eliminar un determinado comentario hecho por un usuario.
  - Recibirá un identificador indicando el comentario en cuestión a eliminar.
  - Devolverá una cadena indicando si la operación se completó correctamente o si hubo algún fallo.

- Listar todos los comentarios de las reservas de un determinado hotel (``listarComentariosHotel``):
  - Se encargará de mostrar todos los comentarios hechos por todos los usuarios a reservas de un determinado hotel.
  - Recibirá un parámetro indicando el nombre del hotel.
  - Devolverá una lista con los comentarios (``nombreHotel``, ``reserva_id``, ``puntuacion`` y ``comentario``).

- Listar todos los comentarios de las reservas de un determinado usuario (``listarComentariosUsuario``):
  - Se encargará de mostrar todos los comentarios hechos por un usuario.
  - Solo recibirá la información de usuario y contraseña.
  - Devolverá una lista con los comentarios (``nombreHotel``, ``reserva_id``, ``puntuacion`` y ``comentario``).

- Mostrar comentario de un usuario en una reserva (``mostrarComentarioUsuarioReserva``):
  - Mostrará el comentario hecho por un usuario en una determinada reserva.
  - Recibirá un parámetro indicando el identificador de la reserva.
  - Devolverá una lista con los comentarios (``nombreHotel``, ``reserva_id``, ``puntuacion`` y ``comentario``).

- Obtener puntuación media de un hotel (``puntuacionMediaHotel``):
  - Mostrará la puntuación media de un hotel a partir de la puntuación de sus reservas.
  - Recibirá un parámetro indicando el nombre del hotel.
  - Devolverá un double con el resultado.

- Obtener puntuación media de un usuario (``puntuacionesMediasUsuario``):
  - Se encargará de mostrar la puntuación media de los comentarios hechos por un usuario en sus reservas.
  - Solo recibirá la información de usuario y contraseña.
  - Devolverá un double con el resultado.