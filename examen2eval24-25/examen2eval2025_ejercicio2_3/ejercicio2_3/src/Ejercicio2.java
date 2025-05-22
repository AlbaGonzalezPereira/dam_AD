import java.util.Scanner;

public class Ejercicio2 {
    static Scanner sc;
    public static void main(String[] args) {

        // Conexión a base X --> ("localhost", 1984, "admin", "abc123")

        sc = new Scanner(System.in).useDelimiter("\n");
        String menu = "1. Consulta 1.\n2. Consulta 2\n3. Consulta 3\n4. Salir\nIntroduzca una opción";
        int opcion;
        do{
            opcion = pedirInt(menu);
            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }while(opcion != 4);
    }

    private static String pedirString(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return sc.nextLine();
            }catch (Exception ignored){}
        }
    }

    private static int pedirInt(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return sc.nextInt();
            }catch (Exception ignored){}
        }
    }
}
