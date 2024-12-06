REPASO 1ª EVALUACIÓN DE ACCESO A DATOS
---
- [1. **Manipulación de ficheros**](#1-manipulación-de-ficheros)
  - [1.1. Lectura y escritura de ficheros](#11-lectura-y-escritura-de-ficheros)
  - [1.2. bytes](#12-bytes)
    - [1.2.1. InputStream](#121-inputstream)
    - [1.2.2. OutputStream](#122-outputstream)
    - [1.2.3. DataInputStream](#123-datainputstream)
    - [1.2.4. DataOutputStream](#124-dataoutputstream)
    - [1.2.5. Objeto serializable](#125-objeto-serializable)
    - [1.2.6. Caracteres](#126-caracteres)
- [2. Filtrar](#2-filtrar)
- [3. XML con DOM Lectura](#3-xml-con-dom-lectura)
- [4. Creación de un fichero XML a partir de un documento](#4-creación-de-un-fichero-xml-a-partir-de-un-documento)
  - [4.1. Resultado](#41-resultado)
- [5. Conexion a base de datos](#5-conexion-a-base-de-datos)
- [6. Hacer transacciones](#6-hacer-transacciones)
- [7. INSERT](#7-insert)
- [8. SELECT](#8-select)
- [9. UPDATE](#9-update)
- [10. DELETE](#10-delete)

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
## 1.2. bytes
### 1.2.1. InputStream
``FileInputStream (File archivo)`` o ``FileInputStream (String ruta)``: permiten abrir el archivo especificado como parámetro en modo lectura y crear una instancia que permite leer el contenido.

### 1.2.2. OutputStream
* ``FileOutputStream (File archivo)`` o ``FileOutputStream (String ruta)``
* ``FileOutputStream (File archivo, boolean append)`` o ``FileOutputStream (String ruta, boolean append):``
 
### 1.2.3. DataInputStream
* ``FileInputStream (File archivo)`` o ``FileInputStream (String ruta)``: permiten leer enteros, float, UTF de forma secuencial.

### 1.2.4. DataOutputStream
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

### 1.2.5. Objeto serializable
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

### 1.2.6. Caracteres
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

# 2. Filtrar
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
https://github.com/jgarea/daw_poo/tree/9ff350b2478f33bfd5e8da867c532872f090eb45/Tarea09

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

# 3. XML con DOM Lectura
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

# 4. Creación de un fichero XML a partir de un documento
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

# 5. Conexion a base de datos
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

# 6. Hacer transacciones
```java
public class Transaccion {
    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();
        try {
            conexion.setAutoCommit(false);
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate("INSERT INTO alumnos (nombre, apellidos) VALUES ('Juan', 'García')");
            sentencia.executeUpdate("INSERT INTO alumnos (nombre, apellidos) VALUES ('Ana', 'Martínez')");
            conexion.commit();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error al hacer rollback " + ex1.getMessage());
            }
            System.out.println("Error al hacer la transacción " + ex.getMessage());
        }
    }
}
```
# 7. INSERT
```java
public void crearNuevoProveedor(String nombreProveedor, String nif, int telefono, String email){
        try(Connection con=DatabasePostgresql.getInstance();
                PreparedStatement stmt = con.prepareStatement("INSERT INTO proveedores(nombre_proveedor,contacto,nif) VALUES (?,ROW(?,?,?),?)");){
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

# 8. SELECT
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

# 9. UPDATE
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

# 10. DELETE
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
