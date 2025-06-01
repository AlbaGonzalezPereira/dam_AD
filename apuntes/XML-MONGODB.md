# BASES DE DATOS XML Y MONGODB

## BaseX y XQuery
BaseX es una base de datos XML nativa y un procesador de XQuery que permite almacenar, consultar y manipular documentos XML de manera eficiente. XQuery es un lenguaje diseñado para consultar y transformar datos XML, similar a cómo SQL se utiliza para bases de datos relacionales.

1. **Estructura FLWOR**
La mayoría de las consultas utilizan la estructura FLWOR (``For``, ``Let``, ``Where``, ``Order by``, ``Return``), que permite iterar, filtrar, ordenar y retornar datos.

    ```xquery
    (:Selecciona los nombres de los autores mayores de 50 años, ordenados alfabéticamente.:)
    for $autor in doc("autores.xml")//autor
    where $autor/edad > 50
    order by $autor/nombre
    return $autor/nombre
    ```

2. **Funciones de agregación**
Se utilizan funciones como ``count()``, ``sum()``, ``avg()``, ``min()`` y ``max()`` para realizar cálculos sobre conjuntos de datos.

    ```xquery
    (:Media de antigüedad de los profesores de la facultad de Ingeniería que trabajan algún Lunes. Solución: 7.666…:)
    avg(for $x in doc("universidad.xml")//facultad[@nombre="Ingeniería"]//profesor
    where $x/horario[@dia="Lunes"]
    return $x/@antigüedad)
    ```

3. **Acceso a atributos**
Para acceder a atributos se utiliza la sintaxis ``@atributo``.

    ```xquery
    for $x in doc("universidad.xml")//facultad[@nombre="Ingeniería"]//profesor
    where $x/horario[@dia="Lunes"]
    return $x/@antigüedad
    ```

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