# **Aplicación para la gestión de usuarios con ficheros JSON**
## **Introducción**
En este proyecto vamos a desarrollar una aplicación que permita registrar usuarios y acceder a su información. Toda la información relacionada con los usuarios se almacenará en un fichero JSON.

La aplicación **permitirá realizar** lo siguiente:

* Crear un nuevo usuario.
* Iniciar sesión a través de un formulario de login.
* Ver la información de mi usuario.
* Cambiar la contraseña de mi usuario.
* Borrar mi usuario

En el proyecto se os proporciona un paquete gui que contiene las interfaces de la aplicación y un paquete model que contiene la lógica de la aplicación. Debéis realizar las modificaciones correspondientes sobre las clases de estos paquetes.

## **Pasos previos**

**MATERIAL A DESCARGAR**

Primero debéis descargar el proyecto desde el enlace anterior e importarlo.

**NOTA IMPORTANTE**
Una vez descargado el fichero del proyecto, es necesario renombrarlo antes de poder importarlo o descomprimirlo

Por ejemplo, si el fichero descargado se llama: ``proyecto-unidad1.tar-ea989dab2692d9ae69a8330c92b139c2.gz`` habrá que renombrarlo a: ``proyecto-unidad1.tar`` para poder descomprimirlo o importarlo.

Después, para trabajar con ficheros JSON desde Java vamos a usar el paquete org.json. Para poder usarlo, debemos descargarnos el archivo ``.jar`` correspondiente desde el repositorio de Maven.

Una vez descargado, debemos añadirlo a nuestro Build Path. Para eso, haremos lo siguiente desde nuestro proyecto de Eclipse:

**``Click derecho > Build Path > Configure Build Path > Add External JARs ``**

Además, en el fichero ``module-info.java`` de nuestro proyecto tendremos que importar los paquetes ``java.desktop`` (para usar Swing) y ``org.json``.

Con esto ya podemos empezar a trabajar.

## **Interfaces Gráficas**
Las interfaces de la aplicación ya están creadas en el paquete gui. Deberéis hacer alguna modificación sobre ellas para capturar los eventos, pero nada más.

**CAPTURA DE EVENTOS**
Para capturar los eventos en cada interfaz, debéis **sobreescribir el método actionPerformed**. Dentro de este método tendréis que utilizar **``e.getSource()``** para capturar el evento de cada botón.

Las interfaces son las siguientes:

* **``VentanaInicioSesion.java``**:
    * En esta ventana el usuario puede iniciar sesión con su usuario y contraseña, si ya está registrado.
    * Si el usuario no existe, se mostrará un diálogo con el mensaje: ``El usuario no existe``.
    * Si la contraseña no se introduce correctamente, se mostrará un diálogo con el mensaje: ``La contraseña no es correcta``.

Una vez iniciemos sesión, pasaremos a la ventana del menú de usuario. En caso de no estar registrados, podremos crear un usuario en el botón ``Crear nuevo usuario``, que nos llevará a la ventana correspondiente.

<div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga1.png" alt="Palíndromo" style = "width: 60%"></div>

<div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga2.png" alt="Palíndromo" style = "width: 60%"></div>

<div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga3.png" alt="Palíndromo" style = "width: 60%"></div>


* **``VentanaCrearUsuario.java``**:

    * En esta ventana podemos introducir los datos de un nuevo usuario.
    * Si pulsamos en ``Cancelar``, volveremos a la ventana de inicio de sesión.
    * Si pulsamos en ``Crear``, se almacenarán los datos en el archivo JSON y se volverá a la ventana de inicio de sesión.

    <div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga4.png" alt="Palíndromo" style = "width: 60%"></div>

* **``VentanaMenuUsuario.java``**:

    * En esta ventana el usuario podrá realizar acciones sobre su perfil.
    * En el campo blanco se debe mostrar el nombre del usuario que inició sesión.
    * Si pulsamos en ``Ver datos``, se abrirá la ventana de datos del usuario.
    * Si pulsamos en ``Cambiar contraseña``, se abrirá la ventana de cambio de contraseña.
    * Si pulsamos en ``Borrar usuario``, se abrirá la ventana de confirmación de borrado del usuario.
    * Si pulsamos en ``Cerrar sesión`` se volverá a la ventana de inicio.


    <div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga5.png" alt="Palíndromo" style = "width: 60%"></div>

* **``VentanaVerUsuario.java``**:
    * Se mostrarán en los diferentes campos el nombre, edad y correo electrónico del usuario.
    * Al pulsar en ``Volver`` se cerrará la ventana y se volverá al menú del usuario.

    <div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga6.png" alt="Palíndromo" style = "width: 60%"></div>

* **``VentanaCambiarContraseña.java``**:
    * En esta ventana se puede introducir la nueva contraseña del usuario.
    * Si pulsamos en ``Cambiar`` se realizará el cambio y se cerrará la sesión, volviendo a la ventana de inicio.
    * Si pulsamos en ``Cancelar`` se cerrará la ventana, volviendo al menú del usuario sin realizar el cambio.

    <div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga7.png" alt="Palíndromo" style = "width: 60%"></div>

* **``VentanaBorrarUsuario.java``**:
    * En esta ventana podremos confirmar el borrado del usuario.
    * Si se pulsa en ``Borrar``, se borrará el usuario y se cerrará la sesión, volviendo a la ventana de inicio.
    * Si se pulsa en ``Cancelar``, se cerrará la ventana y se volverá al menú del usuario.

    <div align = center><img src="https://github.com/AlbaGonzalezPereira/dam_AD/blob/main/tarea201/img/descarga8.png" alt="Palíndromo" style = "width: 60%"></div>