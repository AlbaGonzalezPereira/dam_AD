
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alba_
 */
public class Principal110 {

    static int opcion;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        File ficheroTexto = new File("ficherotexto.txt");
        do {
            System.out.println("1. Escribir");
            System.out.println("2. Leer");
            System.out.println("0. Salir");
            System.out.println("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Introduce la frase a escribir: ");
                    String frase = sc.nextLine();

                    //Escribimos varias líneas en un archivo de texto
                    try ( BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroTexto, true));) {
                        bw.write(frase);
                        bw.newLine();

                    } catch (IOException ex) {
                        Logger.getLogger(Principal110.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 2:  
                    //leemos el fichero e imprimimos las frases que hay:
                    try ( BufferedReader br = new BufferedReader(new FileReader(ficheroTexto));) {
                    ArrayList<String> lineas = new ArrayList<>();
                    String linea = br.readLine();//leemos la primera línea
                    while (linea != null) { //si la línea es distinto de nulo y mientras no llegue al final...
                        lineas.add(linea);
                        linea = br.readLine();
                    }

                    //recorremos las líneas
                    for (String line : lineas) {
                        System.out.println(line);
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Principal110.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal110.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
                default:
                    System.out.println("Elige unha opción correcta");
            }
        } while (opcion != 0);

    }

}
