import java.io.File;
import java.io.FilenameFilter;

/**
 * Clase para hacer un filtro de archivos con una extensión
 * @author alba_
 */
public class Filtro implements FilenameFilter{
    private String extension;

    public Filtro(String extension) {
        this.extension = extension;
    }
    
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
  
}
