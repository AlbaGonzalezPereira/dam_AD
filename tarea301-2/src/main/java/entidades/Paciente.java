
package entidades;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 * Clase de la tabla paciente de la base de datos hospitalbd
 * @author alba_
 */

@Entity //para decir que es una entidad (tabla bbdd)
@Data //lombok: getter, setter, constructores, toString
@AllArgsConstructor //incluye el constructor con todos los argumentos
@NoArgsConstructor //contructor vacío
@RequiredArgsConstructor //contructor con los argumentos que no puedan ser nulos
public class Paciente {
    @Id
    @NonNull
    private int id;
    
    @NonNull
    private String nombre;
    
    private LocalDate fecha_nacimiento;
    private String direccion;
    
    @OneToMany(mappedBy="paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;
    
    @OneToMany(mappedBy="paciente", cascade = CascadeType.ALL)
    private List<Recibe> recibes;
    
}
