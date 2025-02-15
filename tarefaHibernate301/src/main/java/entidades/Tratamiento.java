package entidades;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import lombok.*;


/**
 * Clase de la tabla tratamiento de la base de datos hospitalbd
 * @author alba_
 */

@Entity //para decir que es una entidad (tabla bbdd)
@Data //lombok: getter, setter, constructores, toString
@AllArgsConstructor //incluye el constructor con todos los argumentos
@NoArgsConstructor //contructor vacío
@RequiredArgsConstructor //contructor con los argumentos que no puedan ser nulos
public class Tratamiento {
    @Id
    @NonNull
    private int id;
    
    @NonNull
    private String tipo;
    
    private BigDecimal costo;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_hospital")
    private Hospital hospital;
    
    @OneToMany(mappedBy="tratamiento", cascade = CascadeType.ALL)
    private List<Recibe> recibes;
    
    
    @Override
    public String toString(){
        return "tipo: " + tipo + ", costo: " + costo;
    }
    
}
