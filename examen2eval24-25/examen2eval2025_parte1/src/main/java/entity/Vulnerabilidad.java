package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author alba_
 */
@Entity
@Data //pone getter, setter y toString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vulnerabilidades") //si no coincide el nombre de la Clase con el de la tabla
public class Vulnerabilidad {

    @Id
    //  @GeneratedValue(strategy = GenerationType.AUTO) //autoincrement (poner si da error)
    private int id_vulnerabilidad;
    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;
    @NonNull
    private Byte nivel_riesgo;

    public Vulnerabilidad(String nombre, String descripcion, byte nivel_riesgo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel_riesgo = nivel_riesgo;
    }

    @OneToOne(mappedBy = "vulnerabilidad") //ponemos el nombre de la clase de OneToOne
    private Solucion solucion;

    @OneToMany(mappedBy = "vulnerabilidad")
    private List<Permite> permites;

    @ManyToMany
    @JoinTable(name = "afecta", joinColumns = @JoinColumn(name = "id_vulnerabilidad"), inverseJoinColumns = @JoinColumn(name = "id_sistema")) //nombre de la tabla intermedia
    private List<Sistema> sistemas;

    //creamos un método para asignar una solución a una vulnerabilidad y viceversa
    public void setSolucionBidireccional(Solucion solu) {
        this.solucion = solu;
        solu.setVulnerabilidad(this); //asignamos la solución a la vulnerabilida
    }
    
    //creamos un método para asignar un sistema a una vulnerabilidad y viceversa
    public void setSistemaBidireccional(Sistema sistem) {
        this.sistemas.add(sistem);
        sistem.setVulnerabilidadBidireccional(this); //asignamos el sistema a la vulnerabilidad
    }

    //hacemos un método para asignar un permite a una vulnerabilidad y viceversa
    public void setPermiteBidireccional(Permite perm) {
        this.permites.add(perm);
        perm.setVulnerabilidadBidireccional(this); //asignamos el permite a la vulnerabilidad
    }
}
