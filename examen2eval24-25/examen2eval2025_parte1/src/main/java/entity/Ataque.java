package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "ataques")
public class Ataque {
    @Id
    private int id_ataque;
    private String nombre;
    private String tipo;
    
    @OneToMany(mappedBy = "ataque") 
    private List<Permite> permites;
    
    public void setPermiteBidireccional(Permite perm) {
        this.permites.add(perm);
        perm.setAtaqueBidireccional(this); //asignamos el permite al ataque
    }
    
}
