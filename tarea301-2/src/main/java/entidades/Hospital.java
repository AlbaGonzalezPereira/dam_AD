
package entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 * Clase de la tabla hospital de la base de datos hospitalbd
 * @author alba_
 */

@Entity //para decir que es una entidad (tabla bbdd)
@Data //lombok: getter, setter, constructores, toString
@AllArgsConstructor //incluye el constructor con todos los argumentos
@NoArgsConstructor //contructor vacío
@RequiredArgsConstructor //contructor con los argumentos que no puedan ser nulos
public class Hospital {
    @Id
    @NonNull
    private int id;
    @NonNull
    private String nombre;
    private String ubicacion;  
    
    @OneToMany(mappedBy="hospital", cascade = CascadeType.ALL)
    private List<Tratamiento> tratamientos;
    
}
