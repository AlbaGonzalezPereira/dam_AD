# TAREA 401

## A TENER EN CUENTA:
Se debe **crear un menú** que permita identificar, de forma clara y concisa, cada una de las acciones que se piden implementar. Además, **todos los mensajes** que se muestren, ya sean de información, de introducción de datos o de error deberán, también, ser **claros y concisos**.

Se deberá intentar obtener el resultado directamente con una consulta en XQuery o en MongoDB. 

## Descripción del proyecto:

Se pide desarrollar una plataforma de comercio electrónico para la compra de videojuegos que permita a los usuarios explorar videojuegos, añadirlos a su carrito, realizar compras y gestionar sus cuentas. La aplicación combinará el uso de dos bases de datos:

- Una **base de datos estática con datos XML** (en BaseX), llamada **``videojuegos``**, para almacenar información estática sobre los videojuegos disponibles.
- Una **base de datos MongoDB**, llamada **``comercio``** para gestionar detalles dinámicos como usuarios, compras y carritos de compras.

### Posible estructura de la base de datos Mongo
A modo de ayuda y posible idea, sería conveniente crear tres colecciones:

- **``Usuarios``**: almacena la información personal de cada usuario.
- **``Carrito``**: contiene la lista de videojuegos que el usuario ha seleccionado para comprar.
- **``Compras``**: son los videojuegos que el usuario ha comprado.

## Tareas a realizar
Se pide implementar un menú que permita realizar las siguientes operaciones sobre la base de datos BaseX y MongoDB. Una posible estructura de menú puede ser la siguiente:

```java
CONSULTAS BASE DE DATOS XML
    1. DESCRIPCIÓN DE LA OPERACIÓN 1
    2. DESCRIPCIÓN DE LA OPERACIÓN 2.
    ...
    7. DESCRIPCIÓN DE LA OPERACIÓN 7.

CONSULTAS BASE DE DATOS MONGODB
    8. DESCRIPCIÓN DE LA OPERACIÓN 8
    ...
    17. DESCRIPCIÓN DE LA OPERACIÓN 17
```

En cuanto a las operaciones que se deberán realizar, serán las siguientes:

Sobre la **base de datos XML** se pide:

- **Modificar el valor de un elemento** de un XML según un ID.
- **Eliminar un ``videojuego``** según su ID.
- **Obtener todos los videojuegos** ordenados por plataforma y en segundo lugar por título (se mostrarán los siguientes campos: ``id``, ``titulo``, ``precio``, ``disponibilidad``, ``edad_minima_recomendada`` y ``plataforma``).
- **Listar videojuegos con una edad_minima_recomendada menor o igual a X años** (se mostrarán los siguientes campos: ``id``, ``titulo``, ``precio``, ``disponibilidad``, ``edad_minima_recomendada`` y ``plataforma``). Se deberá mostrar la información ordenada según la edad_minima_recomendada.
- **Mostrar la ``plataforma``, el ``titulo`` y el ``precio`` del videojuego más barato para cada ``plataforma``**. En el caso de haber varios se devolverá el de la primera posición.
- **Mostrar el ``titulo`` y el ``genero`` de aquellos videojuegos cuya ``descripcion`` incluya una subcadena**, independientemente del uso de mayúsculas o minúsculas. Se deberá mostrar la información ordenada alfabéticamente según el ``genero``.
- **Mostrar la cantidad total de videojuegos para cada ``plataforma``** (teniendo en cuenta el elemento ``disponibilidad``) y calcular el porcentaje que representa respecto al total de videojuegos. Se deberá mostrar la información ordenada de forma descendente por la cantidad de videojuegos.
- **Consulta 6**: Mostrar el precio que costaría comprar todos los videojuegos disponibles (teniendo en cuenta el ``precio`` de cada videojuego y la ``disponibilidad`` de cada uno).

**NOTA SOBRE LAS CONSULTAS ANTERIORES**:

Todas las consultas anteriores tienen una consulta XQuery que permite obtener el resultado de forma directa.

Sobre la **base de datos MongoDB** se pide:

- **Crear un nuevo usuario** (no podrá haber ``email`` repetidos).
- **Identificar usuario** según el ``email``. Dado el ``email`` se obtendrá el ``id`` del usuario de forma que las siguientes consultas se harán sobre ese usuario. Para cambiar de usuario se tendrá que volver a seleccionar esta opción.
- Borrar un usuario.
- **Modificar el valor de un campo** de la información del usuario.
- **Añadir videojuegos al carrito del usuario**. Se mostrará la lista de videojuegos cuya ``edad_minima_recomendada`` sea inferior o igual a la del usuario actual y se pedirá: ``id`` del videojuego y ``cantidad``, así como si se desea seguir introduciendo más videojuegos.
- **Mostrar el carrito del usuario**. Se mostrarán los datos del carrito y el coste total.
- **Comprar el carrito del usuario**. Se mostrará el contenido del carrito junto con una orden de confirmación. Si la orden es positiva se pasarán todos los videojuegos a formar parte de una nueva compra y desaparecerán del carrito.
- **Mostrar las compras del usuario**, incluyendo la información de la fecha de cada compra.
- Teniendo en cuenta todos los usuarios, **calcular el coste de cada carrito** y listar los resultados ordenados por el total de forma descendente.
- Teniendo en cuenta todos los usuarios, **calcular el total gastado por cada usuario en todas sus compras** y listar los resultados ordenados por el total de forma ascendente.

**NOTA SOBRE OS APARTADOS ANTERIORES**:
Para la realización de los apartados **16**, **17** y **18**:

- Si se ha diseñado la **BD con una única colección** existe una consulta en MongoDB que permite obtener el resultado de forma directa.
- Si se ha diseñado la **BD utilizando varias colecciones** se puede usar Java para obtener los datos de una colección para añadirlos a la consulta de la otra colección. Pero no para formatear o calcular los datos finales.

## RESULTADO:
Creamos un método que evitará **repetición de código en cada consulta**. 

Lo que hace es crear una **conexión con el servidor BaseX**, que se ejecuta en "localhost" en el puerto 1984, utilizando las credenciales "admin"/"admin". La conexión se cierra automáticamente al finalizar.

También se va a encargar de **ejecutar una consulta** en una base de datos BaseX y **mostrar todos los resultados** en la consola. 

```java
/**
 * Método que permite devolver todas las consultas
 * @param consulta 
 */
private static void obtenerDatos(String consulta) {
    //hacemos la conexión y la consulta
    try ( BaseXClient conexionBaseX = new BaseXClient("localhost", 1984, "admin", "admin");) {
        try ( BaseXClient.Query query = conexionBaseX.query(consulta);) {
            System.out.println("Conexión Ok");
            while (query.more()) {
                System.out.println(query.next()); //imprime el resultado de todas las líneas
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    } catch (IOException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }
}
```

```java
/**
 * Método que crea e inicializa la base de datos y las colecciones en MongoDB. 
 */
private static void crearBaseDatosyColeccion() {
    if (database == null) {
        database = mongoClient.getDatabase("comercio");
    }
    try {
        coleccionUsuarios = database.getCollection("usuarios");
        coleccionVideojuegos = database.getCollection("videojuegos");
        coleccionCarrito = database.getCollection("carrito");
        coleccionCompras = database.getCollection("compras");
    } catch (Exception e) {
        database.createCollection("usuarios");
        coleccionUsuarios = database.getCollection("usuarios");
        database.createCollection("usuarios");
        coleccionCarrito = database.getCollection("carrito");
        database.createCollection("videojuegos");
        coleccionVideojuegos = database.getCollection("videojuegos");
        database.createCollection("compras");
        coleccionCompras = database.getCollection("compras");
    }
}
```