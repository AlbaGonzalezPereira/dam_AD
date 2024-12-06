
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
 * Clase que lee XML. Repaso 1eval
 * @author alba_
 */
public class PrincipalLeerXML {

    public static void main(String[] args) {
        File archivoXml = new File("usuarios.xml");

        // Creación de la factory
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(archivoXml);
            // normalizamos:
            doc.getDocumentElement().normalize();
            System.out.println(doc.getDocumentElement().getNodeName()); //leemos etiqueta padre (usuarios)
            NodeList listaUsus = doc.getElementsByTagName("usuario");
            for (int i = 0; i < listaUsus.getLength(); i++) {
                Node nodo = listaUsus.item(i);
                //System.out.println(nodo.getNodeName());//comprobamos el nodo en el que estamos
                Element elemento = (Element) nodo;
                String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                System.out.println("nombre = " + nombre);
                Element atributo = (Element) elemento.getElementsByTagName("nombre").item(0);
                System.out.println(atributo.getAttribute("id"));
                String contrasinal = elemento.getElementsByTagName("contraseña").item(0).getTextContent();
                System.out.println("contrasinal = " + contrasinal);
            }
            
            File ficheroCarpeta = new File("F:\\02.DAW\\curso 2022-2023\\05.PROG");
            Filtro filtro = new Filtro(".PNG");
            String[] ficherosPng = ficheroCarpeta.list(filtro); //ficheroCarpeta.list(new Filtro(".PNG"))
            for (String fichero : ficherosPng) {
                System.out.println(fichero);
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
