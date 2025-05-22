package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
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
public class Permite {
    @Id
   // private int id_vulnerabilidad;
     @ManyToOne
    @JoinColumn(name="id_vulnerabilidad") 
    private Vulnerabilidad vulnerabilidad;
    @Id
    //private int id_ataque;
    @ManyToOne
    @JoinColumn(name="id_ataque") 
    private Ataque ataque;
    private Byte impacto;
    private LocalDate fecha_detectada;
    
    public void setVulnerabilidadBidireccional(Vulnerabilidad vuln) {
        this.vulnerabilidad = vuln;
    }
    
    public void setAtaqueBidireccional(Ataque ataque) {
        this.ataque = ataque;
    }
}
