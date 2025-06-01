# MONGODB
**MongoDB** (mongodb.com) es un sistema de base de datos documental (por lo que es NoSQL) y de código abierto.

Almacena los documentos en un tipo de formato JSON llamado **BSON** (Binary JSON).

## MongoDB Compass

**MongoDB Compass** es una herramienta gráfica de administración y análisis de bases de datos MongoDB que proporciona una interfaz amigable para interactuar con bases de datos sin necesidad de usar comandos en la línea de terminal.

Sería el MySQL Workbench de MongoDB.

Por defecto, MongoDB se configura en el puerto 27017, por lo tanto, para utilizarlo a nivel local se establecería una conexión a ``localhost:27017``.

Una vez establecida la conexión ya se podría comenzar a crear bases de datos, colecciones, etc.

## Conceptos básicos

MongoDB utiliza el modelo documental para representar la información. El formato BSON tiene un aspecto como el siguiente:

```json
{
    "_id": ObjectId("5f4f7fef2d4b45b7f11b6d7a"), 
    "id_usuario": "Sean",
    "edad": 29,
    "estado": "A"
}
```

Un **ObjectID** no es más que un valor de 12 bytes.

En **MongoDB**, los datos se estructuran en **colecciones** (``Collections``), que almacenan documentos que tienen contenidos similares. Lo normal es que los documentos contengan campos similares, pero no es un requisito.

**documento**: 
Un documento no es más que una forma de almacenar datos como un conjunto de pares campo-valor.

Por hacer una **analogía** con las bases de datos relacionales:

|   **RDBMS**	        |   **MongoDB**                                 |
|-----------------------|-----------------------------------------------|
|   Base de datos	    |   Base de datos                               |
|   Tabla	            |   Colecciones                                 |
|   Tupla / fila	    |   Documento                                   |
|   Columna	            |   Campo                                       |
|   Unión de tablas	    |   Documentos embebidos                        |
|   Clave primaria	    |   Clave primaria (_id como clave por defecto) | 

## Operaciones en Java

Para conectarse y trabajar con MongoDB desde Java se usa la **MongoDB Java Driver**.

- **Conexión**:

    ```java
    mongoClient = new MongoClient(
        new MongoClientURI("mongodb://localhost:27017/")
    );
    database = mongoClient.getDatabase("biblioteca");
    collection = database.getCollection("autores");
    ```

- **Filtros y operadores comunes en MongoDB**:
    - ``$eq``:	Igual a	
    ```java
    eq("edad", 30)
    ```

    - ``$gt``:	Mayor que	
    ```java
    gt("edad", 60)
    ```

    - ``$and``:	Y lógico	
    ```java
    and(eq(...), gt(...))
    ```

    - ``$in``:	En un conjunto	
    ```java
    in("nacionalidad", Arrays.asList("Japonesa", "Española"))
    ```

    - ``$size``: Tamaño exacto de array. Filtra por tamaño del array	
    ```java
    size("premios", 0)
    ```

- **Agregaciones en MongoDB**:
    - Agrupar (``$group``)
    - Filtrar (``$match``)
    - Descomponer arrays (``$unwind``)
    - Proyectar los campos a mostrar (``$project``)
    - Ordenar (``$sort``)