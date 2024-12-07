REPASO 1ª EVALUACIÓN DE ACCESO A DATOS
---
- [1. **Manipulación de ficheros**](#1-manipulación-de-ficheros)
  - [1.1. Lectura y escritura de ficheros](#11-lectura-y-escritura-de-ficheros)
  - [1.2. API `java.io`](#12-api-javaio)
    - [1.2.1. Elegir la clase:](#121-elegir-la-clase)
    - [1.2.2. RandomAccessFile](#122-randomaccessfile)
  - [1.3. **bytes**](#13-bytes)
    - [1.3.1. InputStream](#131-inputstream)
    - [1.3.2. OutputStream](#132-outputstream)
    - [1.3.3. DataInputStream](#133-datainputstream)
    - [1.3.4. DataOutputStream](#134-dataoutputstream)
    - [1.3.5. Objeto serializable](#135-objeto-serializable)
    - [1.3.6. Caracteres](#136-caracteres)
- [2. **Filtrar**](#2-filtrar)
- [3. **XML con DOM Lectura**](#3-xml-con-dom-lectura)
- [4. **Creación de un fichero XML a partir de un documento**](#4-creación-de-un-fichero-xml-a-partir-de-un-documento)
  - [4.1. Resultado](#41-resultado)
- [5. **Conexión a base de datos**](#5-conexión-a-base-de-datos)
- [6. **Hacer transacciones**](#6-hacer-transacciones)
- [7. **INSERT**](#7-insert)
- [8. **SELECT**](#8-select)
- [9. **UPDATE**](#9-update)
- [10. **DELETE**](#10-delete)

# 1. **Manipulación de ficheros**
## 1.1. Lectura y escritura de ficheros
```java
/**
 * crea el fichero JSON si todavía no existe.
 */
private void crearFicheroJson() {
    File file = new File(RUTA_FICHERO);
    if (!file.exists()) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("[]");
            writer.flush();
        } catch (IOException ex) {
            System.out.println("Error al crear el fichero " + ex.getMessage());
        }
    }
}
```
## 1.2. API `java.io`
* `File`: representa un archivo/directorio y permite realizar operaciones como verificar si existe un archivo o directorio, obtener propiedades eliminarlo, crearlo.
* `InputStream` y `OutputStream` clases abstratas para leer y escribir byte a byte,se almacenan en binario. Se utilizan para almacenar un archivo como un ejecutable o una imagen.
    * Clases concretas:
        * ``ByteArrayInputStream``: Crea un InputStream a partir de un array de byte (byte[]) pasado como parámetro
        a su construtor. Cuando leemos de este Stream leemos del array de datos.
        * ``ByteArrayOutputStream``: Crea un OutputStream de manera que los bytes que escribimos se almacenan en un
        array de bytes (byte[]). La clase proporciona el método **byte[] toByteArray();** que nos permite recuperar los datos escritos.
        
        * `FileInputStream`: Crea un `InputStream` a partir de un objecto File que se recibe o se crea en el construtor de la clase y que referencia a un ficheiro en disco. Cuando leemos de este Stream leemos datos del ficheiro.
        * `FileOutputStream`: Crea un `OutputStream` de manera que los bytes que escribimos se almacenan en un fichero referenciado por el objeto File que se recibe o se crea en el construtor de la clase.
        * `BufferedInputStream` , `BufferedOutputStream`
        ---
        * ``DataInputStream``: Permite la lectura de datos primitivos del Stream como char, boolean, byte, float,
        double, o int.
        * ``DataOutputStream``: Permite volcar datos primitivos como char, boolean, byte, float, double, o int a un flujo de salida.
        * ``ObjectInputStream``: Permite leer objetos de un flujo de bytes escritos con ``ObjectOutputStream``.
        * ``ObjectOutputStream``: Permite volcar objetos en el Stream. Cuando se crea el stream siempre se envía una cabecera mediante el método ``void writeStreamHeader()``, lo que debemos tener en conta si queremos añadir objetos al final de un fichero ya existente. En este caso, una solución es emplear una clase heredada en la que este método no haga nada.

* `Reader` y `Writer`: Clases abstractas para leer y escribir caracteres en vez de bytes, se utilizan para almacenar archivos de texto.
    * Clases concretas:
        * `FileReader` , `FileWriter`

        * `BufferedReader` Esta clase proporciona como principal aportación el método ``String readLine()`` que lee
        del flujo de texto una línea.

        * `BufferedWriter`  Esta clase realmente no proporciona nuevas funcionalidades importantes sobre las
        ofrecidas por un Reader simple, pero mejora su velocidad haciendo uso de un buffer de caracteres. 

        * `PrintWriter` Esta clase proporciona métodos que nos permiten dar formato a la conversión en texto de la
        información que queremos escribir en el Stream, destacando las distintas versiones sobrecargadas de los
        métodos **print**, **println** e **printf**

### 1.2.1. Elegir la clase:
* Primero tenemos que comprobar si queremos escribir o leer caracteres o en binario.
    * Para leer y escribir en binario utilizamos las clases heredadas de `InputStream` y `OutputStream` ejemplos más arriba. Si lees o escribes objetos tienen que ser seriealizables implementando la interfaz
    * Para leer y escribir caracteres usamos las clases que heredan de `Reader` y `Writer`.

**Ejercicio**
* Crea un datos.txt con estos datos:
```
12345D,juan
64421G,alba
76532E,pepe  
```
* Lee cada una de las lineas con stream y visualiza por pantalla.
* Lee cada una de las lineas y escríbelas en otro fichero datos2.txt.

<details><summary>Solucion</summary>
<p>

```java
try ( BufferedReader read = new BufferedReader(new FileReader("texto.txt"));PrintWriter writer= new PrintWriter("datos2.txt");) {
            String linea;
            while ((linea = read.readLine()) != null) {
                System.out.println(linea);
                writer.print(linea+"\n");
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PruebaLecturaFicherosStreams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PruebaLecturaFicherosStreams.class.getName()).log(Level.SEVERE, null, ex);
        }
```

</p>
</details>

### 1.2.2. RandomAccessFile
La clase ``RandomAccessFile`` nos permite crear, almacenar y leer información en archivos sobre un soporte
almacenamiento de acceso aleatorio.

**En Java o ficheiro se “abre” cando instanciamos o obxecto RandomAccessFile que o referencia.**

El concepto más importante de los RandomAccessFile es el “puntero del fichero” o “posición”. Los bytes
almacenados en un fichero están identificados por una dirección, siendo 0 la dirección del primer byte, 1 la del segundo..., etc. 

La clase RandomAccessFile dispone de dos métodos para gestionar el posicionamiento del puntero del fichero:
* ``long getFilePointer();`` que nos devuelve el número del byte al que está apuntando actualmente el puntero del fichero, y, por lo tanto, el byte que se va a leer o escribir en la siguiente operación.
* ``void seek(long pos);`` que cambia la posición del puntero del fichero al byte identificado por el número indicado.

``String	readUTF()``

Reads in a string from this file.

``writeUTF(String str)``

Writes a string to the file using modified UTF-8 encoding in a machine-independent manner.

```java
private Usuario readUser(RandomAccessFile ras) throws EOFException, IOException
```

<details><summary>Solución</summary>
<p>

```java
String dni,nome;
int edade;
dni=ras.readUTF();
if (dni==null) throw new EOFException("End of file");
if (dni.equals("*")) return null;   // Rexistro borrado
nome=ras.readUTF();
edade=ras.readInt();
return new Usuario(dni,nome,edade);
```

</p>
</details>

## 1.3. **bytes**
### 1.3.1. InputStream
``FileInputStream (File archivo)`` o ``FileInputStream (String ruta)``: permiten abrir el archivo especificado como parámetro en modo lectura y crear una instancia que permite leer el contenido.

### 1.3.2. OutputStream
* ``FileOutputStream (File archivo)`` o ``FileOutputStream (String ruta)``
* ``FileOutputStream (File archivo, boolean append)`` o ``FileOutputStream (String ruta, boolean append):``
 
### 1.3.3. DataInputStream
* ``FileInputStream (File archivo)`` o ``FileInputStream (String ruta)``: permiten leer enteros, float, UTF de forma secuencial.

### 1.3.4. DataOutputStream
* ``FileOutputStream (File archivo)`` o ``FileOutputStream (String ruta)``: Permiten escribir de forma secuencial.
  
---
  
  **Ejemplo:**

  * Escribir en un fichero binario Empleados.dat de manera secuencial, la siguiente información:

  ```
    Departamento "Contabilidad","Informática","Dirección","Análisis","Finanzas","Hardware"
    Nª Empleados 3,10,2,5,4,8
  ```
  * Mostrar la información del fichero anterior de forma secuencial.
  

**Solución:**
```java
    public static void escritura() throws IOException {
    
        File fichero = new File("./Empleados.dat");

        //Creamos los stream de escritura
        FileOutputStream fileout = new FileOutputStream(fichero);   
        DataOutputStream dataOS = new DataOutputStream(fileout);
        
        // Inicialización de los parámetros a escribir
        String departamento[] = {"Contabilidad","Informática","Dirección","Análisis","Finanzas","Hardware"};
        int numempleados[] = {3,10,2,5,4,8};
            
        // Escribimos la información
        for (int i=0;i<numempleados.length; i++){
            dataOS.writeUTF(departamento[i]); //inserta nombre
            dataOS.writeInt(numempleados[i]);  //inserta edad
        }

        // Cerramos el stream
        dataOS.close(); 
    }
```
  * Mostrar la información del fichero anterior de forma secuencial.
  ```java
  public static void lectura() throws IOException {    
        File fichero = new File("./Empleados.dat");
        String n;
        int e;

        try ( FileInputStream filein = new FileInputStream(fichero); DataInputStream dataIS = new DataInputStream(filein);) {
            while (true) {
                // Leemos el nombre del departamento
                n = dataIS.readUTF(); 
                
                // Leemos el número de empleados del departamento
                e = dataIS.readInt();
                System.out.println("Nombre departamento: " + n + ", Numero de empleados: " + e);        
            }
        }catch (EOFException eo) {} 
    }
  ```

### 1.3.5. Objeto serializable
Sirve para leer y escribir información del programa, como objetos de las clases serializables.

```java
    // Serializar 'estudiante'
    // para crear un ObjectOutputStream necesitamos un FileOutputStream
    FileOutputStream fos = new FileOutputStream("xyz.txt"); //decimos donde tiene que apuntar ("xyz.txt"). También puede ser un tipo File que apunte a lo que contenga
    ObjectOutputStream oos = new ObjectOutputStream(fos); //nos permite guardar el objeto
    oos.writeObject(objetoEstudiante);  //guardamos el objeto 
  
    // Desserializar 'estudiante'
    // lee y recupera el objeto con su clase especificada
    FileInputStream fis = new FileInputStream("xyz.txt");
    ObjectInputStream ois = new ObjectInputStream(fis);
    Estudiante estudiante1 = (Estudiante)ois.readObject();//este método devuelve un objeto. Hacemos un casting
```
Ejemplo:
```java
    //Escribir Objetos
    Alumno al = new Alumno("Juan", "Balea");
    System.out.println(al);
    File fichero = new File("alumnos.txt");
    try (FileOutputStream out = new FileOutputStream(fichero);
        ObjectOutputStream ops = new ObjectOutputStream(out);){
        ops.writeObject(al);
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
    }

    //Leer Objetos
    File fichero2 = new File("alumnos.txt");
    if (fichero2.exists()) {
        try ( FileInputStream is = new FileInputStream(fichero2);  ObjectInputStream ois = new ObjectInputStream(is)) {
            Alumno alumno = (Alumno) ois.readObject();
            System.out.println(alumno);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
```

### 1.3.6. Caracteres
* ``BufferedReader``, ``BufferedInputStream``, ``BufferedWriter`` y ``BufferedOutputStream`` 

```java
File archivo = new File ("archivo.txt");
FileReader fr = new FileReader (archivo);
BufferedReader br = new BufferedReader(fr);

// Resto del código de ejecución
...

// Lectura de una línea del fichero
String linea = br.readLine();
```

# 2. **Filtrar**
```java
    // Creamos una clse que implemente FilenameFilter, la cual nos permite filtrar
    class FiltroExtension implements FilenameFilter {
        private String extension;

        // Constructor de la clase pasando la extensión
        public FiltroExtension(String extension) {
            this.extension = extension;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(extension);//devuelve los archivos que terminen con esa extensión
        }
}

public class Main {
    public static void main(String[] args) {
        File directorio = new File("C:\\Users\\jgarea\\Documents\\NetBeansProjects\\Ficheros\\src\\ficheros");//ponemos ruta del directorio a filtrar
        String[] lista = directorio.list(new FiltroExtension(".txt")); //list() muestra los archivos que haya en la carpeta, pero si hay un parámetro que tenga ese filtro, va a coger eso, en este caso ".txt". El filtro es el que se creó arriba
        for (String archivo : lista) {
            System.out.println(archivo);
        }
    }
}
```

Ejemplo:
```java
//Clase Filtro:
    public class Filtro implements FilenameFilter{
        private String extension;

        public Filtro(String extension) {
            this.extension = extension;
        }
        
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(extension);
        }
    }

// Clase Main:
    File ficheroCarpeta = new File("F:\\02.DAW\\curso 2022-2023\\05.PROG");
        Filtro filtro = new Filtro(".PNG");
        String[] ficherosPng = ficheroCarpeta.list(filtro); //ficheroCarpeta.list(new Filtro(".PNG"))
        for (String fichero : ficherosPng) {
            System.out.println(fichero);    
        }
```

# 3. **XML con DOM Lectura**
* Las instrucciones necesarias para leer un archivo XML y crear un objeto Document serían las siguientes:
```java
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ; 
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse( new File( "fitler.xml" )); //aquí apunta al archivo xml
```
```java
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParserDemo {
   public static void main(String[] args) {
      try {
         File inputFile = new File("clase.xml");
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         
         NodeList nList = doc.getElementsByTagName("alumno");
         
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp); //para acceder a cada uno de los nodos se usa item(posición)
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               
               Element eElement = (Element) nNode;
               
               System.out.println("numero de alumno : "+ eElement.getAttribute("numero"));
               System.out.println("nombre : "+ eElement.getElementsByTagName("nombre").item(0).getTextContent());
               System.out.println("apellido :"+ eElement.getElementsByTagName("apellido").item(0).getTextContent());
               System.out.println("apodo : "+ eElement.getElementsByTagName("apodo").item(0).getTextContent());
               System.out.println("marcas : "+eElement.getElementsByTagName("marcas").item(0).getTextContent());
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
} 
```

# 4. **Creación de un fichero XML a partir de un documento**
```java
public class CrearXml { 
    public static void main(String argv[]) { 
        try { 

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            DOMImplementation implementation = docBuilder.getDOMImplementation();
            Document doc = implementation.createDocument(null, "root", null); 

            // Cogemos el Elemento raíz para poder introducir elementos
            Element rootElement = doc.getDocumentElement(); 
            
            //Primer elemento, lo creamos y lo agregamos con appendChild 
            Element elemento1 = doc.createElement("elemento1"); 
            rootElement.appendChild(elemento1); 
            
            //Se agrega un atributo al nodo elemento y su valor 
            Attr attr = doc.createAttribute("id"); 
            attr.setValue("valor del atributo"); //ponemos valor atributo
            elemento1.setAttributeNode(attr); 
            
            Element elemento2 = doc.createElement("elemento2"); 
            elemento2.setTextContent("Contenido del elemento 2"); 
            rootElement.appendChild(elemento2); 
            
            //Se escribe el contenido del XML en un archivo 
            TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
            Transformer transformer = transformerFactory.newTransformer(); //transforma el documento DOM en un formato específico (en este caso, XML).
            DOMSource source = new DOMSource(doc);  // crea una fuente de datos XML 
            StreamResult result = new StreamResult(new File("/ruta/prueba.xml")); 
            transformer.transform(source, result); //define el destino del contenido transformado
        
        } catch (ParserConfigurationException pce) { 
            pce.printStackTrace(); 
        } catch (TransformerException tfe) { 
            tfe.printStackTrace(); 
        } 
    } 
} 
```
## 4.1. Resultado
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>	
<root>	
    <elemento1 id="valor del atributo"/>	
    <elemento2>Contenido del elemento 2</elemento2>
</root>
```
* Hacer 116 y 117

# 5. **Conexión a base de datos**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/curso";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "root";
    private static Connection conexion = null;

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Error al conectar a la base de datos " + ex.getMessage());
            }
        }
        return conexion;
    }
}
```

# 6. **Hacer transacciones**
```java
public class Transaccion {
    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();
        try {
            conexion.setAutoCommit(false); //Desactivamos autoCommit para que no haga nada hasta que se haga un commit
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO alumnos (nombre, apellidos) VALUES ('Juan', 'García')");
            sentencia.executeUpdate("INSERT INTO alumnos (nombre, apellidos) VALUES ('Ana', 'Martínez')");
            conexion.commit();
        } catch (SQLException ex) { //en caso de error
            try { 
                conexion.rollback(); //no hace ninguna de las sentencias. Revertir cambios
            } catch (SQLException ex1) {
                System.out.println("Error al hacer rollback " + ex1.getMessage());
            }
            System.out.println("Error al hacer la transacción " + ex.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.setAutoCommit(true); // Restaurar auto-commit
                }
            } catch (SQLException ex2) {
                System.out.println("Error al restaurar auto-commit " + ex2.getMessage());
            }
        }
    }
}
```
# 7. **INSERT**
```java
public void crearNuevoProveedor(String nombreProveedor, String nif, int telefono, String email){
    String sql = "INSERT INTO proveedores(nombre_proveedor,contacto,nif) VALUES (?,ROW(?,?,?),?)";
    try(Connection con=DatabasePostgresql.getInstance();
        PreparedStatement stmt = con.prepareStatement(sql);){
        stmt.setString(1, nombreProveedor);
        stmt.setString(2, null);
        stmt.setInt(3, telefono);
        stmt.setString(4, email);
        stmt.setString(5, nif);
        int consulta=stmt.executeUpdate();
        if(consulta>0)
            System.out.println("Proveedor creado.");
        else
            System.out.println("Proveedor no ha podido crearse.");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
```

# 8. **SELECT**
```java
public void obtenerTotalPedidosUsuarios(){
        String sql="SELECT usuarios.nombre, COUNT(pedidos.id_pedido) "
                + "AS total_pedidos FROM usuarios "
                + "JOIN pedidos ON usuarios.id_usuario=pedidos.id_usuario "
                + "GROUP BY usuarios.nombre";
        try(Connection con = DB.DatabaseMysql.getInstance();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();) {
            while(result.next()){
                System.out.println("Nombre: "+result.getString(1)+" Total pedidos: "+result.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
```

# 9. **UPDATE**
```java
public void actualizarProveedor(int id, String nombreProveedor, String nif, int telefono, String email){
    try(Connection con=DatabasePostgresql.getInstance();
            PreparedStatement stmt = con.prepareStatement("UPDATE proveedores SET nombre_proveedor = ?, contacto = ROW(?,?,?), nif = ? WHERE id_proveedor = ?");){
        stmt.setString(1, nombreProveedor);
        stmt.setString(2, null);
        stmt.setInt(3, telefono);
        stmt.setString(4, email);
        stmt.setString(5, nif);
        stmt.setInt(6, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Proveedor actualizado correctamente.");
        } else {
            System.out.println("No se encontró un proveedor con el ID especificado.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
```

# 10. **DELETE**
```java
public void eliminarProveedor(int id){
    try(Connection con=DatabasePostgresql.getInstance();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM proveedores WHERE id_proveedor = ?");){
            
        stmt.setInt(1, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Proveedor eliminado correctamente.");
        } else {
            System.out.println("No se encontró un proveedor con el ID especificado.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
```
