# BASES DE DATOS XML

## BaseX y XQuery

BaseX es una **base de datos XML nativa** y un **procesador de XQuery** que permite **almacenar**, **consultar** y **manipular** documentos XML de manera eficiente. 

XQuery es un lenguaje diseñado para consultar y transformar datos XML, similar a cómo SQL se utiliza para bases de datos relacionales.

1. **Estructura FLWOR**: 
La mayoría de las consultas utilizan la estructura FLWOR (``For``, ``Let``, ``Where``, ``Order by``, ``Return``), que permite iterar, filtrar, ordenar y retornar datos. Es similar a ``SELECT`` en SQL:

    ```xquery
    (:Selecciona los nombres de los autores mayores de 50 años, ordenados alfabéticamente.:)
    for $autor in doc("autores.xml")//autor
    where $autor/edad > 50
    order by $autor/nombre
    return $autor/nombre
    ```

2. **Funciones de agregación**: 
Se utilizan funciones como ``count()``, ``sum()``, ``avg()``, ``min()`` y ``max()`` para realizar cálculos sobre conjuntos de datos.

    ```xquery
    (:Media de antigüedad de los profesores de la facultad de Ingeniería que trabajan algún Lunes. Solución: 7.666…:)
    avg(for $x in doc("universidad.xml")//facultad[@nombre="Ingeniería"]//profesor
    where $x/horario[@dia="Lunes"]
    return $x/@antigüedad)
    ```

3. **Acceso a atributos**: 
Para acceder a atributos se utiliza la sintaxis ``@atributo``.

    ```xquery
    for $x in doc("universidad.xml")//facultad[@nombre="Ingeniería"]//profesor
    where $x/horario[@dia="Lunes"]
    return $x/@antigüedad
    ```
## Trabajando con BaseX

1. **Preparamos el entorno**

   - Ejecutamos BaseX GUI
   - Importamos la base de datos
   - Ejecutamos en la carpeta de BaseX → bin → basexserver.bat (cliente Java)

2. **Creamos la base de datos**
   - Database → New → Selección.
   - Cargamos el archivo XML con el que vamos a trabajar (universidad.xml, autores.xml, etc.).
   - Le damos un nombre (por ejemplo, universidad) y pulsamos Create.

3. **Creamos el archivo de consultas**
   -  le llamaremos ``nombre.xq``
   -  Haremos las consultas consultas con XQuery separadas por "``,``", si van en el mismo archivo (solo si se usa como script).
  
## Actualizar datos (Modo Update)

1. **``replace value of node``**: Modifica solo el contenido del nodo.

    Ejemplo: Cambiamos un nombre

    ```xquery
    replace value of node //profesor[@id="P001"]/nombre
    with "Laura Martínez"
    ```

2. **``insert node``**: Inserta un nuevo nodo.
   
   Ejemplo: Insertamos un nuevo profesor

    ```xquery
    insert node <profesor id="P005"><nombre>Ana Ruiz</nombre></profesor>
    into //profesores
    ```

3. **``delete node``**: Elimina un nodo.

    Ejemplo: Eliminamos un profesor
    ```xquery
    delete node //profesor[@id="P004"]
    ```

4. **``rename node``**: Cambia el nombre de una etiqueta.
   
    ```xquery
    rename node //profesor[@id="P001"]/nombre
    as "nombre_completo"
    ```

## Funciones útiles
- ``db:open("universidad")``: abre una base XML ya almacenada
- ``db:list()``: lista las bases disponibles
- ``db:exists("nombreBD")``: comprueba si existe

## Estructuras importantes
```java
//Cuando tenemos el try - catch en el main...

// Conexión a base X --> ("localhost", 1984, "admin", "abc123")
try ( BaseXClient conexionBX = new BaseXClient("localhost", 1984, "admin", "admin");) {

    sc = new Scanner(System.in).useDelimiter("\n");
    String menu = "1. Consulta 1.\n2. Consulta 2\n3. Consulta 3\n4. Salir\nIntroduzca una opción";
    int opcion;
    do {
        opcion = pedirInt(menu);
        switch (opcion) {
            case 1:
                contarProfesores(conexionBX);
                break;
            case 2:
                sacarMediaProfesores(conexionBX);
                break;
            case 3:
                sacarMediaCursos(conexionBX);
                break;
        }
    } while (opcion != 4);

} catch (IOException ex) {
    Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
}

 /**
     * método que imprime todas las consultas
     * @param cliente
     * @param consulta
     */
    private static void imprimirConsultas(BaseXClient cliente, String consulta) {
        try {
            BaseXClient.Query sql = cliente.query(consulta);
            while (sql.more()) { //mientras haya consultas...
                System.out.println(sql.next());
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
```
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