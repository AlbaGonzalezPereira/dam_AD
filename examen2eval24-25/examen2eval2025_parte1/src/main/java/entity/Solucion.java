package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "soluciones")
public class Solucion {
    @Id
    private int id_solucion;
    
   @OneToOne 
   @JoinColumn(name="id_vulnerabilidad") //ponemos en name como se llame en la base de datos
  // private int id_vulnerabilidad;
   private Vulnerabilidad vulnerabilidad; //usamos la clase
    private String descripcion;
    
    //creamos un método para asignar una vulnerabilidad a una solución y viceversa
    public void setVulnerabilidadBidireccional(Vulnerabilidad vulnera){
        this.vulnerabilidad = vulnera;
        vulnera.setSolucion(this); //asignamos la vulnerabilidad a la solución
    }
    
    
}
