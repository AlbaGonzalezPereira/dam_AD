package ejercicio2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * clase Principal que contienen el main del ejercicio2
 * @author alba_
 */
public class Principal {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Introduce el nombre de usuario: ");
        String usu = sc.nextLine();
        System.out.println("Introduce la contraseña: ");
        String pass = sc.nextLine();   
        
        if(comprobarUsuario(usu, pass))
            System.out.println("SE HA INICIADO SESIÓN CON ÉXITO");
        else
            System.out.println("ERROR: NO SE HA PODIDO INICIAR SESIÓN");       
    }

    private static boolean comprobarUsuario(String usu, String pass) throws DOMException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //lo necesitamos para obtener un documentBuilder
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();//para parsear el xml
            Document documento = builder.parse(new File("usuarios.xml"));
            documento.normalize();//para asegurarse que la estructura del documento esté normalizada
            NodeList usuarios = documento.getElementsByTagName("usuario");//tenemos todos los usuarios
            for (int i = 0; i < usuarios.getLength(); i++) {//recorremos todos los usuarios
                Node nodo = usuarios.item(i);//tenemos cada nodo, es decir, cada usuario
                Element elemento = (Element)nodo; //convertimos el nodo en un elemento
                String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();//obtenemos el nombre
                String contrasena = elemento.getElementsByTagName("contraseña").item(0).getTextContent(); //obtenemos la contraseña
                //System.out.println(nombre + " " + contrasena);
                if(usu.equals(nombre) && pass.equals(contrasena))
                    return true;
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
