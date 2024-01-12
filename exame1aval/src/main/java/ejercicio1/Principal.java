package ejercicio1;

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
 * Clase principal que contiene el main del ejercicio1
 * @author alba_
 */
public class Principal {
    static int opcion;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        File archivo = new File("personas.txt");
        Persona persona;
        ArrayList<Persona> personas = new ArrayList<>();
        try(BufferedReader leer = new BufferedReader(new FileReader(archivo));) {
            String registro = leer.readLine();
            while(registro!=null){
                System.out.println(registro);
                String trozos[]=registro.split(" ");//cortamos el registro
                //almacenamos en un objeto persona los trozos de nombre y apellidos
                persona = new Persona(trozos[0], trozos[1], trozos[2]);
                personas.add(persona);//añadimos al Arraylist
                registro = leer.readLine();     
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("***************MENÚ***************");
        
        do{
            System.out.println("1. Introducir nuevos valores");
            System.out.println("2. Guardar lista");
            System.out.println("3. Mostrar contenido del fichero");
            System.out.println("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());
            
            switch(opcion){
                case 1:
                    System.out.println("Introduzca el nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Introduzca el primer apellido");
                    String apellido1 = sc.nextLine();
                    System.out.println("Introduzca el segundo apellido");
                    String apellido2 = sc.nextLine();
                    persona = new Persona(nombre, apellido1, apellido2);
                    personas.add(persona); //la introducimos en el arraylist 
                    break;
                    
                case 2:
                    guardarPersonas(personas);
                    break;
                    
                case 3:
                    mostrarDatos();
                    break;
            } 
        }
        while(opcion!=0);
    }

    private static void guardarPersonas(ArrayList<Persona> personas) {
        File lectura = new File("personaObjeto.txt");
        //ordenamos el ArrayList:
        personas.sort((Persona o1, Persona o2) -> o1.toString().compareTo(o2.toString()));
        try(BufferedWriter escribir = new BufferedWriter(new FileWriter(lectura));) {
            for (Persona persona : personas) {
                escribir.write(persona.toString());//escribimos la persona en el fichero
                escribir.newLine();//para que escriba en la siguiente línea
            }
           //escribir.write(str);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void mostrarDatos() {
        File file = new File("personaObjeto.txt");
        if(file.exists()){
            try(BufferedReader leer = new BufferedReader(new FileReader(file));) {
            String registro = leer.readLine();
            while(registro!=null){
                System.out.println(registro);
                registro = leer.readLine();     
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    }  
    
}
