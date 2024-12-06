# xml
# lectura y escritura de ficheros XML
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
## bytes
### inputStream
``FileInputStream (File archivo)`` o ``FileInputStream (String ruta):`` permiten abrir el archivo especificado como parámetro en modo lectura y crear una instancia que permite leer el contenido.
### OutputStream
* ``FileOutputStream (File archivo)`` o ``FileOutputStream (String ruta)``
* ``FileOutputStream (File archivo, boolean append)`` o ``FileOutputStream (String ruta, boolean append):``

### Objeto serializable
```java
 // Serializar 'estudiante'
        FileOutputStream fos = new FileOutputStream("xyz.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);
  
        // Desserializar 'estudiante'
        FileInputStream fis = new FileInputStream("xyz.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Estudiante estudiante1 = (Estudiante)ois.readObject();
```

### caracteres
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

# filtrar
```java
class FiltroExtension implements FilenameFilter {
    private String extension;

    public FiltroExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}

public class Main {
    public static void main(String[] args) {
        File directorio = new File("C:\\Users\\jgarea\\Documents\\NetBeansProjects\\Ficheros\\src\\ficheros");
        String[] lista = directorio.list(new FiltroExtension(".txt"));
        for (String archivo : lista) {
            System.out.println(archivo);
        }
    }
}
```
https://github.com/jgarea/daw_poo/tree/9ff350b2478f33bfd5e8da867c532872f090eb45/Tarea09

# XML con DOM Lectura
* Las instrucciones necesarias para leer un archivo XML y crear un objeto Document serían las siguientes:
```java
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ; 
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse( new File( "fitler.xml" ));
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
         System.out.println("Root element :" + 
         doc.getDocumentElement().getNodeName());
         
         NodeList nList = doc.getElementsByTagName("alumno");
         
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
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

# Creación de un fichero XML a partir de un documento
```java
public class CrearXml { 
    public static void main(String argv[]) { 
        try { 

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            DOMImplementation implementation = docBuilder.getDOMImplementation();
            Document doc = implementation.createDocument(null, "root", null); 

            //Elemento raíz 
            Element rootElement = doc.getDocumentElement(); 
            
            //Primer elemento 
            Element elemento1 = doc.createElement("elemento1"); 
            rootElement.appendChild(elemento1); 
            
            //Se agrega un atributo al nodo elemento y su valor 
            Attr attr = doc.createAttribute("id"); 
            attr.setValue("valor del atributo"); 
            elemento1.setAttributeNode(attr); 
            
            Element elemento2 = doc.createElement("elemento2"); 
            elemento2.setTextContent("Contenido del elemento 2"); 
            rootElement.appendChild(elemento2); 
            
            //Se escribe el contenido del XML en un archivo 
            TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
            Transformer transformer = transformerFactory.newTransformer(); 
            DOMSource source = new DOMSource(doc); 
            StreamResult result = new StreamResult(new File("/ruta/prueba.xml")); 
            transformer.transform(source, result); 
        
        } catch (ParserConfigurationException pce) { 
            pce.printStackTrace(); 
        } catch (TransformerException tfe) { 
            tfe.printStackTrace(); 
        } 
    } 
} 
```
## Resultado
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>	
<root>	
    <elemento1 id="valor del atributo"/>	
    <elemento2>Contenido del elemento 2</elemento2>
</root>
```
* Hacer 116 y 117

# Conexion a base de datos
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

# Hacer transacciones
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
# INSERT
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

# SELECT
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

# UPDATE
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

# DELETE
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
