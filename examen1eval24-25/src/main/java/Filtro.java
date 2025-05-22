import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author a24exame-dam-1
 */
public class Filtro implements FilenameFilter{
    private String palabra;

    public Filtro(String palabra) {
        this.palabra = palabra;
    }
    

    @Override
    public boolean accept(File dir, String name) {
        return name.contains(palabra);
    }
    
}
