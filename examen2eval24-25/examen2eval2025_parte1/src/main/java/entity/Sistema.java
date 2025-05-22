
package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author alba_
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sistemas")
public class Sistema {
    @Id
    private int id_sistema;
    private String nombre;
    private String version;
    
    @ManyToMany(mappedBy = "sistemas")
    private List<Vulnerabilidad> vulnerabilidades;
    
    //creamos un método para asignar una solución a una vulnerabilidad y viceversa
    public void setVulnerabilidadBidireccional(Vulnerabilidad vuln) {
        this.vulnerabilidades.add(vuln);
    }
    
}
