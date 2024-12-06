
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Repasando Acceso a Datos xml. Ejercicio 117
 * @author alba_
 */
public class PrincipalXML117 {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            DOMImplementation implentacion = db.getDOMImplementation();
            Document doc = implentacion.createDocument(null, "peliculas", null);
            
            Element root = doc.getDocumentElement();
            Element pelicula = doc.createElement("pelicula");
            Element titulo = doc.createElement("titulo");
            Element ano = doc.createElement("ano");
            Element precio = doc.createElement("precio");
            titulo.setTextContent("El señor de los anillos");
            ano.setTextContent("1999");
            precio.setTextContent("19.99");
            Attr id = doc.createAttribute("id");
            id.setValue("1");
            pelicula.setAttribute("id", "1");
            pelicula.appendChild(titulo);
            pelicula.appendChild(ano);
            pelicula.appendChild(precio);
            root.appendChild(pelicula);
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transform = tf.newTransformer();
            DOMSource dom = new DOMSource(doc);
            StreamResult sr = new StreamResult("peliculasCreadas.xml");
            transform.transform(dom, sr);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrincipalXML117.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(PrincipalXML117.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(PrincipalXML117.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
