
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Repasando Acceso a Datos xml. Ejercicio 116
 * @author alba_
 */
public class PrincipalXML116 {
    public static void main(String[] args) {
        File archivoCine = new File("peliculas.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivoCine);
            doc.getDocumentElement().normalize();
            
            NodeList peliculas = doc.getElementsByTagName("pelicula");
            for (int i = 0; i <peliculas.getLength(); i++) {
                Node nodo = peliculas.item(i);
                Element elemento = (Element) nodo;
                String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
                String ano = elemento.getElementsByTagName("ano").item(0).getTextContent();
                String precio = elemento.getElementsByTagName("precio").item(0).getTextContent();
                String id = elemento.getAttribute("id");
                
                System.out.println("PELÍCULA #" + id);
                System.out.println("Título: " + titulo);
                System.out.println("Año: " + ano);
                System.out.println("Precio: " + precio);
                System.out.println("");
                
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrincipalXML116.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PrincipalXML116.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalXML116.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
