import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
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
 * ejercicio1
 *
 * @author alba maría gonzález
 */
public class Ejercicio1 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        File fichero = new File("producto.bin");

        //a1
        if (!fichero.exists()) {

            try {
                if (fichero.createNewFile()) {//lo creamos si no existe
                    System.out.println("Se ha creado correctamente fichero.bin");
                }
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }

            //a2
            System.out.println("Introduzca una palabra: ");
            String palabra = sc.nextLine();

            //a3
            File rutaFicheros = new File("ficheros"); //creo la ruta de los ficheros
            Filtro filtro = new Filtro(palabra); //creo el filtro
            String[] ficheros = rutaFicheros.list(filtro);
            ArrayList<String> nombresFicheros = new ArrayList<>();
            for (String fichero1 : ficheros) {
                //System.out.println("fichero1 = " + fichero1);
                String nombreFichero = fichero1.split("\\.")[0];
                nombresFicheros.add(nombreFichero);

            }

            //a4
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            try {
                db = dbf.newDocumentBuilder();
                Document doc = db.parse(new File("productos.xml"));
                doc.getDocumentElement().normalize();
                NodeList elementos = doc.getElementsByTagName("producto");
                ArrayList<Producto> productos = new ArrayList<>();
                for (int i = 0; i < elementos.getLength(); i++) {
                    Producto producto;
                    Node nodo = elementos.item(i);
                    Element elemento = (Element) nodo;
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    String precio = elemento.getElementsByTagName("precio").item(0).getTextContent();
                    String stock = elemento.getElementsByTagName("stock").item(0).getTextContent();
                    producto = new Producto(nombre, Float.parseFloat(precio), Integer.parseInt(stock));

                    if (contiene(nombresFicheros, nombre)) { //si contiene la palabra que se le pase
                        productos.add(producto);
                    }

                }

                for (Producto producto : productos) {
                    System.out.println(producto);
                }

                //a5
                //guardamos los productos en el fichero
                try ( FileOutputStream ops = new FileOutputStream(fichero);  ObjectOutputStream oos = new ObjectOutputStream(ops);) {
                    oos.writeObject(productos);
                }

            } catch (ParserConfigurationException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            //leemos del fichero y lo imprimimos
            try(FileInputStream fis = new FileInputStream(fichero); ObjectInputStream ois = new ObjectInputStream(fis);) {
                ArrayList<Producto> productosLeidos;
                productosLeidos = (ArrayList<Producto>) ois.readObject();
                for (Producto productoLeido : productosLeidos) {
                    System.out.println(productoLeido);                 
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }

    private static boolean contiene(ArrayList<String> nombresFicheros, String nombre) {
        for (String nomFichero : nombresFicheros) {
            if (nombre.toLowerCase().contains(nomFichero.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
