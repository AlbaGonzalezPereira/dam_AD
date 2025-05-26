# TAREA 501

## MICROSERVICIOS REALIZADOS:
Se han implementado todos los microservicios que se solicitaban (opcionales y obligatorios), realizando cada una de las partes.

**Importante**: El APIGateway está en el puerto 8081, ya que no funcionaba en 8080.

## CONFIGURACIÓN:
En este caso, le cambiamos el puerto 8080 a la API Gateway por el 8081 debido a que lo tenemos ocupado por otros servicios.

## PASOS (desde Visual Studio Code):
1. Si tenemos **un servicio**:
- Tecleamos ``crtl + shift + p`` para que abra los comandos de VSCode.
- Buscamos **Spring Initializr**.
- **Configuramos** el nombre del paquete, versión del SpringBoot (3.5), si es Java (17), etc.
- Añadimos el **artifactId**, que tiene que ser igual al nombre del microservicio.
- Añadimos las **dependencias** necesarias.
- **Guardamos en la carpeta** donde va a estar el microservicio.
  
2. Si tenemos **varios microservicios** en un mismo proyecto:
- Creamos un fichero ``pom.xml`` en la raíz del proyecto que nos indicará los servicios que tenemos implementados.
    ``` xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.microservicios</groupId>      <!-- Nombre del paquete-->
        <artifactId>tarea501</artifactId><!-- Nombre del proyecto-->
        <version>0.0.1-SNAPSHOT</version>
        <packaging>pom</packaging>          <!-- Empaquetamiento-->
        <description>Tarea 501 de Acceso a Datos</description>
        <modules> <!-- Nombre de los microservicios-->
            <module>eureka</module>
        </modules>
        <properties> <!-- Propiedades del proyecto-->
            <java.version>17</java.version>
            <spring-cloud.version>2023.0.0</spring-cloud.version>
            <maven.compiler.source>17</maven.compiler.source>
            <maven.compiler.target>17</maven.compiler.target>
        </properties>
    </project>
    ```

- Modificamos el **archivo del main**, añadiendo antes de la clase ``@EnableEurekaServer``:
    ```java
    @EnableEurekaServer
    @SpringBootApplication
    public class EurekaApplication {

        public static void main(String[] args) {
            SpringApplication.run(EurekaApplication.class, args);
        }

    }
    ```
- **Configuramos el servidor** en el archivo ``application.properties``, en este caso Eureka, con las siguientes **propiedades**:

    ```properties
    server.port=8500
    eureka.instance.hostname=localhost
    eureka.client.registerWithEureka=false
    eureka.client.fetchRegistry=false
    eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
    ```

  - **server.port:** es el puerto
  - **eureka.instance.hostname:** es el host (IP)
  - **eureka.client.registerWithEureka:** si debe registrarse automáticamente en el servidor (en este caso, al ser el propio servidor hay que indicarle que no se registre a sí mismo).
  - **eureka.client.fetchRegistry:** si el microservicio debe consultar el registro de Eureka para obtener información.
  - **eureka.client.serviceUrl.defaultZone:** URL del servidor.

- Probamos la url poniendo ``localhost:8500`` y nos abrirá el servidor Eureka.
- En **Instances currently registered with Eureka**, en la columna de Application aparecerán los microservicios asociados o *No instances available* si no los hay.

- Creamos los **servicios** como el primero, con las dependencias apropiadas y vamos añadiendo en el pom.xml cada uno de ellos:
  - **Eureka** --> dependencia: Eureka server
  - **API Gateway** --> dependencias: Eureka Discovery Client y Reactive Gateway
  - **Usuarios** --> dependencias: Eureka Discovery Client, Lombok, Spring web, JPA (usamos hibernate para la bbdd) y mysql
  - **Reservas** --> dependencias: Eureka Discovery Client, Lombok, Spring web, JPA (usamos hibernate para la bbdd) y mysql
  - **Comentarios** --> dependencias: Eureka Discovery Client, Lombok, Spring web, Spring Data MongoDB, Spring for GraphQL

    ```xml
    <modules> <!-- Nombre de los módulos (microservicios)-->
        <module>eureka</module>
        <module>gateway</module>
        <module>usuarios</module>
        <module>reservas</module>
        <module>comentarios</module>
    </modules>
    ```
    
- Le damos a **actualizar todos los proyectos** (icono actualizar de Maven) para poder iniciarlos cuando los creemos.

## CREAMOS LAS ENTIDADES, SERVICIOS Y CONTROLADORES CORRESPONDIENTES
- **Entidad**:

Literalmente, cada clase involucrada con el almacenamiento y recuperación de datos se denomina entidad y se identificará a través de JPA usando la anotación ``@Entity``.

Por otro lado, también es recomendable marcar el identificador con la anotación ``@Id`` y establecer la estrategia de generación con ``@GeneratedValue``.

  - ``@Table(name = "")`` --> MySQL
  - ``@Document(name ="")`` --> MongoDB

- **Repositorio**:

Básicamente, un repositorio reúne todas las operaciones de datos para un tipo de dominio determinado en un solo lugar. Se utiliza la anotación ``@Repository``. Es una interfaz. Haremos un repositorio por cada entidad.

Para definir un repositorio es necesario crear una interfaz en Java que extienda la interfaz ``JpaRepository`` o de ``MongoRepository`` la cual recibirá dos parámetros:

  - La entidad o dominio que se va a utilizar.
  - El tipo de dato del Id.

Spring Data ofrece la posibilidad de definir el repositorio solo con la interfaz. Esta será implementada automáticamente en tiempo de ejecución cuando la aplicación se ejecute, utilizando la anotación ``@Autowired``.



- **Servicio**:

Un servicio se encarga de conectar varios respositorios y agrupar la lógica del sistema y su funcionalidad.

Para la **creación de un servicio** se necesitará:

  - Definir una clase en Java
  - Utilizar la anotación ``@Service``
  - Establecer la variable de conexión con los distintos repositorios.
  - Definir los métodos que permitan recibir la información del controlador, procesarla, hacer las peticiones correspondientes a los repositorio y devolver la solución al controlador.
  
  Crearemos objetos privados del repositorio con ``@Autowired``. Un servicio por repositorio.

- **Controlador**: 
Para definir un controlador definiremos una clase en Java que tendrá la anotación ``@Controller`` o ``@RestController``. Esto permite que Spring automáticamente lo detecte y cree un Bean de la clase en el contexto de aplicación Spring. Además, se usará la anotación ``@RequestMapping("/ruta")`` para indicar la URL base de la aplicación.

Por otro lado, para coger el valor ``/{reader}`` y parsearlo a una variable del método se utilizará la anotación ``@PathVariable("reader")``.

Aquí se llaman a todos todos los servicios con ``@Autowired``.

- **DTO**:

Los dto son objetos que se usan para transportar datos entre capas.

Sus **principales usos** son:

  - Evitar exponer las entidades de la base de datos o los modelos de nuestra aplicación.
  - Permitir ensamblar distintos objetos.
  - Eliminar campos que no queremos que se vean.
  - Pasar de un tipo de dato a otro.

## CONSULTAS GRAPHIQL:

1. ``crearComentario``:
    ```graphql
    mutation{
        crearComentario(comentario:{
            nombre: "Juan Pérez"
            contrasena: "clave123"
            nombreHotel: "Hotel A"
            reservaId: 1
            puntuacion: 5.7
            comentario: "Buen servicio y muy amplio"
        }) {
            nombreHotel
            reservaId
            puntuacion
            comentario
        }
    }
    ```

2. ``eliminarComentarios``:
    ```graphql
    mutation{
        eliminarComentarios
    }
    ```

3. ``eliminarComentarioDeUsuario``:

    - Sin com probar id de usuario logueado
    ```
    mutation{
        eliminarComentarioDeUsuario(comentarioEliminado:{
                comentarioId: "6820778374f7b3f83e3bd7a5"
                nombre: "Ana Martínez"
                contrasena: "abcxyz"
            })
        }
    ```

    - Comprobando id usuario logueado
    ```
    mutation{
        eliminarComentarioDeUsuario(comentarioEliminado:{
                comentarioId: "68207659dbe15c3704d29ac7"
                nombre: "Juan Pérez"
                contrasena: "clave123"
            })
        }
    ```

4. ``listarComentariosHotel``:
    ```graphql
    query{
        listarComentariosHotel(comentario: {
            nombreHotel: "Hotel A"
            nombre: "Juan Pérez"
            contrasena: "clave123"
        }){
            nombreHotel
            reservaId
            puntuacion
            comentario
            }
    }
    ```

5. ``listarComentariosUsuario``:
    ```graphql
    query{
        listarComentariosUsuario(usuario:{
            nombre: "Carlos Rodriguez",
            contrasena: "password789"
        }){
        nombreHotel
        reservaId
        puntuacion
        comentario
        }
    }
    ```

6. ``mostrarComentarioUsuarioReserva``:
    ```graphql
    query {
    mostrarComentarioUsuarioReserva(comentario:{
        reservaId: 2
        nombre: "María López"
        contrasena: "secreto456"
    }){
        nombreHotel
        reservaId
        puntuacion
        comentario
    }
    }
    ```

7. ``puntuacionMediaHotel``:

- **Consulta MongoDB**:
    ```json
    db.getCollection('comentarios').aggregate(
    [
        { $match: { hotelId: 2 } },
        {
        $group: {
            _id: '$hotelId',
            mediaPuntuacion: { $avg: '$puntuacion' }
        }
        },
        { $project: { mediaPuntuacion: 1, _id: 0 } }
    ],
    { maxTimeMS: 60000, allowDiskUse: true }
    );
    ```

- **Consulta graphiql**:
    ```graphql
    query {
        puntuacionMediaHotel(comentarioHotel:{
            nombreHotel: "Hotel A"
            nombre: "Juan Pérez"
            contrasena: "clave123"
        })
    }
    ```

8. ``puntuacionesMediasUsuario``:
    ```graphql
    query {
        puntuacionesMediasUsuario(usuario:{
            nombre: "Carlos Rodriguez"
            contrasena: "password789"
        })
    }
    ```


