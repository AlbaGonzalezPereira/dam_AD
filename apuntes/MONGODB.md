# MONGODB
**MongoDB** (mongodb.com) es un sistema de base de datos NoSQL, orientada a documentos y de código abierto.

Almacena los documentos en un tipo de formato JSON llamado **BSON** (Binary JSON), lo que permite una estructura flexible y escalable.

Es ideal para aplicaciones que requieren manejar grandes volúmenes de datos no estructurados o semi-estructurados.

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

## Operaciones MongoDB en Java

Para conectarse y trabajar con MongoDB desde Java se usa la **MongoDB Java Driver**.

- **Conexión**:
  
    A continuación, se muestra cómo establecer una conexión:

    ```java
    mongoClient = new MongoClient(
        new MongoClientURI("mongodb://localhost:27017/")
    );
    database = mongoClient.getDatabase("biblioteca");
    collection = database.getCollection("autores");
    ```

    **Nota**: Hay que asegurarse de tener el driver de MongoDB para Java en el proyecto. Si se utiliza Maven, se puede agregar la dependencia correspondiente en el archivo ``pom.xml``.

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
    - ``$group`` ->	Agrupa documentos según algún campo
    - ``$match`` ->	Filtra documentos (como WHERE)
    - ``$unwind`` -> Descompone arrays en documentos individuales
    - ``$project`` -> Selecciona los campos que se mostrarán
    - ``$sort`` -> Ordena documentos

## Operaciones CRUD
- **Crear** (Insertar documentos)
    ```java
    import com.mongodb.client.MongoCollection;
    import org.bson.Document;

    // Obtenemos la colección 'usuarios'
    MongoCollection<Document> collection = database.getCollection("usuarios");

    // Creamos un nuevo documento
    Document doc = new Document("nombre", "Juan")
                    .append("edad", 30);

    // Insertamos el documento en la colección
    collection.insertOne(doc);
    ```

- **Leer** (Consultar documentos)
    ```java
    import com.mongodb.client.FindIterable;

    // Obtenemos todos los documentos de la colección
    FindIterable<Document> documentos = collection.find();

    for (Document document : documentos) {
        System.out.println(document.toJson());
    }
    ```

- **Actualizar documentos**
    ```java
    import com.mongodb.client.model.Filters;
    import com.mongodb.client.model.Updates;

    // Actualizamos el campo 'edad' del usuario con nombre 'Juan' a 31
    collection.updateOne(Filters.eq("nombre", "Juan"), Updates.set("edad", 31));
    ```

- **Eliminar documentos**
    ```java
    // Eliminamos el documento donde el nombre sea 'Juan'
    collection.deleteOne(Filters.eq("nombre", "Juan"));
    ```