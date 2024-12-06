
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author alba_
 */
public class PrincipalObject {

    public static void main(String[] args) {
        //Leer Objetos
        File fichero2 = new File("alumnos.txt");
        if (fichero2.exists()) {
            try ( FileInputStream is = new FileInputStream(fichero2);  ObjectInputStream ois = new ObjectInputStream(is)) {
                Alumno alumno = (Alumno) ois.readObject();
                System.out.println(alumno);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Escribir Objetos
        Alumno al = new Alumno("Juan", "Balea");
        System.out.println(al);
        File fichero = new File("alumnos.txt");
        try ( FileOutputStream out = new FileOutputStream(fichero);  ObjectOutputStream ops = new ObjectOutputStream(out);) {
            ops.writeObject(al);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalLeerXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
