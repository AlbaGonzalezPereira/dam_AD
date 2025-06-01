# HIBERNATE
Hibernate es un **framework de mapeo objeto-relacional (ORM)** para Java que facilita la persistencia de objetos Java en bases de datos relacionales. 

Permite trabajar directamente con objetos Java sin necesidad de escribir código SQL manual, lo que simplifica el desarrollo y mantenimiento de aplicaciones.

## Anotaciones:

Hibernate utiliza **anotaciones en las clases** Java para definir cómo se mapean los objetos a las tablas de la base de datos. 

Una **anotación** es la forma de añadir metadatos en el código fuente de Java de forma que estos están disponibles en tiempo de ejecución o de compilación y son la alternativa a la tecnología XML.

Las anotaciones tienen las siguientes **características**:

- Comienzan con el símbolo arroba ``@``.
- No cambian la actividad de un programa ordenado
- Ayudan a relacionar metadatos con los componentes del programa.
- No son comentarios sin adulteraciones, ya que pueden cambiar la forma en la que el compilador trata el programa.

Para que una clase Java funcione correctamente como entidad en Hibernate, debe cumplir con las siguientes **condiciones**:

- Tener un constructor público sin argumentos (vacío).
- Definir atributos privados.
- Implementar métodos getters y setters para acceder y modificar los atributos.

Estas características permiten que Hibernate pueda instanciar y gestionar las entidades de manera eficiente.

Algunas de las **anotaciones más importantes** son:

- **``@Entity``**: Marca la clase como una entidad persistente.

- **``@Table(name = "nombre_tabla")``**: permite mapear la entidad Java con una tabla de la base de datos, especificando el nombre de la tabla en la base de datos que se corresponde con la entidad.

- **``@Id``**: Indica el atributo que actúa como clave primaria.

- **``@GeneratedValue(strategy = GenerationType.AUTO)``**: Define la estrategia para la generación automática de valores para la clave primaria.
  - ***AUTO*** -> Es la estrategia por defecto. Le permite al gestor de persistencia elegir la estrategia de generación.
  - ***IDENTITY*** -> Se basa en el auto incremento de una columna en la base de datos y esta generar un nuevo valor con cada inserción
  - ***SEQUENCE*** -> Usa una secuencia de la base de datos para generar valores únicos. Supone una problemática cuando se configura Hibernate hibernate en modo validar (validate), ya que Hibernate no crea el esquema si no que comprueba que la definición de entidades es igual que la estructura de la base de datos que ya está creada. Como generalmente no hay una tabla de secuencias, Hibernate nos reportará un error.

- **``@Column``**: Permite personalizar el mapeo de un atributo a una columna, incluyendo propiedades como ``name``, ``length``, ``nullable``, ``insertable``, ``updatable``, entre otras.

- **``@Enumerated(EnumType.STRING)``**: Indica que un atributo de tipo enum debe almacenarse como una cadena en la base de datos.

- **``@Transient``**: permite excluir un campo de ser persistente de la base de datos

- **``@OneToOne``**: define una relación 1:1 entre dos entidades

- **``@OneToMany``**: define una relación 1:N entre dos entidades

- **``@ManyToMany``**: define una relación N:M entre dos entidades

- **``@JoinColumn``**: especifica la columna que actúa como clave foránea en una relación

- **``@NamedQuery``**: declara el nombre de una consulta a una entidad.

- **``@NamedNativeQuery``**: declara una consulta SQL nativa a una entidad.

<br>

**Ejemplo**:
```java
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
```

### Diferencias entre @Entity y @Table
Aunque ambas anotaciones pueden tener un atributo ``name``, su propósito es diferente:

- ``@Entity(name = "nombre_entidad")``: Define el nombre que se utilizará para referirse a la entidad en las consultas HQL (Hibernate Query Language).

- ``@Table(name = "nombre_tabla")``: Especifica el nombre real de la tabla en la base de datos.

Por lo general, se recomienda utilizar el atributo ``name`` en ``@Table`` para establecer el nombre de la tabla y evitar conflictos en las consultas.

## hibernate.cfg.xml
Es el archivo de **configuración principal** de Hibernate. Aquí se especifican datos importantes como:

- La conexión a la base de datos (URL, usuario, contraseña, driver).
- El dialecto de SQL que se usará (por ejemplo, para MySQL, PostgreSQL, etc.).
- Y las clases que Hibernate debe mapear.

**Mapear una clase** significa decirle a Hibernate qué clase Java debe estar vinculada a una tabla de la base de datos.

Cuando usas anotaciones como ``@Entity``, ya estás indicando que una clase es una entidad. Pero Hibernate necesita saber cuáles de esas clases debe cargar al iniciar. Para eso sirve esta línea:

```xml
<mapping class="paquete.clase"/>
```

Se escribe antes del cierre de la etiqueta <session-factory>,y se añaden tantas líneas <mapping class="..."/> como clases de entidad se quieran registrar. 

Por ejemplo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="connection.url">jdbc:mysql://localhost:3306/hospitaldb?createDatabaseIfNotExist=true&amp;serverTimezone=UTC</property>
    <property name="connection.username">root</property>
    <property name="connection.password"></property>
    <property name="hbm2ddl.auto">validate</property>
    <property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
    <property name="hibernate.dialect.storage_engine">innodb</property>
    <property name="hibernate.show_sql">true</property>
    
    <mapping class="entidades.Doctor" />
    <mapping class="entidades.Hospital" />
    <mapping class="entidades.Paciente" />
    <mapping class="entidades.Tratamiento" />
    <mapping class="entidades.Cita" />
    <mapping class="entidades.Recibe" />
  </session-factory>
</hibernate-configuration>
```
## Métodos de Session en Hibernate
persist(), merge() y remove() en JPA (Hibernate):

1. ``persist(Object entity)``
   - Inserta una nueva entidad en la base de datos.
   - Solo funciona con entidades nuevas (estado transient).
   - La entidad pasa al estado managed.
   - No devuelve nada.

  ```java
  Vulnerabilidad nuevaVulnera = new Vulnerabilidad(nombre, descripcion, nivel); //creamos una vulnerabilidad
  session.persist(nuevaVulnera); //guardamos
  trt.commit();
  ```

2. ``merge(Object entity)``
   - Actualiza una entidad existente o la inserta si no existe.
   - Devuelve una copia gestionada de la entidad.


  ```java
  /**
   * método que asigna una vulnerabilidad a un sistema. La asignación será
   * bidireccional.
   *
   * @param session
   */
  private static void asignarVulnerabilidadSistema(Session session) {
      Transaction trt = session.beginTransaction(); //iniciamos una transación
      System.out.println("Introduce el id de la vulnerabilidad: ");
      int idVulnera = Integer.parseInt(sc.nextLine());
      Vulnerabilidad vulneraEncontrado = getVulneraById(idVulnera, session);
      System.out.println("Introduce el id del sistema: ");
      int idSistema = Integer.parseInt(sc.nextLine());
      Sistema sistemaEncontrado = getSistemaById(idSistema, session);
      if (vulneraEncontrado != null && sistemaEncontrado != null) {
          vulneraEncontrado.setSistemaBidireccional(sistemaEncontrado);
          sistemaEncontrado.setVulnerabilidadBidireccional(vulneraEncontrado);
          session.merge(vulneraEncontrado);
      }
      trt.commit();
  }
  ```

3. ``remove(Object entity)``
   - Elimina una entidad del contexto y luego de la base de datos.
  ```java
  EntityManager em = emf.createEntityManager();
  em.getTransaction().begin();

  Alumno alumno = em.find(Alumno.class, 1L);
  if (alumno != null) {
      em.remove(alumno); // Elimina el alumno
  }

  em.getTransaction().commit();
  em.close();
  ```

4. ``get()`` / ``find()``	
   - Busca por ID.
  

5. ``createQuery()``	
   - Ejecuta una consulta HQL.
  

## Bidireccionalidad:

1. **ONE TO ONE**:

Entidad Vulnerabilidad:
```java
@OneToOne(mappedBy = "vulnerabilidad") //ponemos el nombre de la clase de OneToOne
private Solucion solucion;

//creamos un método para asignar una solución a una vulnerabilidad y viceversa
public void setSolucionBidireccional(Solucion solu) {
    this.solucion = solu;
    solu.setVulnerabilidad(this); //asignamos la solución a la vulnerabilida
}
```
Entidad Solución:

```java
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
```

2. **ONE TO MANY**:
Entidad Ataque

```java
@OneToMany(mappedBy = "ataque") 
  private List<Permite> permites;
  
  //donde tenga la lista hacemos esto:
  public void setPermiteBidireccional(Permite perm) {
      this.permites.add(perm);
      perm.setAtaqueBidireccional(this); //asignamos el permite al ataque
  }
```

Entidad Permite:

```java
//private int id_ataque;
  @ManyToOne
  @JoinColumn(name="id_ataque") 
  private Ataque ataque;
  private Byte impacto;
  private LocalDate fecha_detectada;
  
  public void setAtaqueBidireccional(Ataque ataque) {
      this.ataque = ataque;
  }
```

## HQL (Hibernate Query Language)

### Devolver resultados:
1. ``uniqueResult()``
  - Devuelve un **único resultado** (puede ser un objeto, un array, o una tupla dependiendo de la consulta).
  - Lanza excepción si hay más de un resultado.
  
  ```java
  String descripcion = session.createQuery(
    "SELECT s.descripcion FROM Solucion s WHERE s.vulnerabilidad = :vulnera", String.class)
    .setParameter("vulnera", vulneraConsulta)
    .uniqueResult();
  ```

  ```java
  session.createQuery("FROM Vulnerabilidad WHERE nombre = :nombre")
       .setParameter("nombre", "Ransomware")
       .uniqueResult();
  ```

2. ``list()``
  - Devuelve una **lista completa** de resultados.
  - Se usa cuando pueden haber múltiples filas como resultado. 
  
  ```java
  List<String> descripciones = session.createQuery(
    "SELECT s.descripcion FROM Solucion s WHERE s.vulnerabilidad = :vulnera", String.class)
    .setParameter("vulnera", vulneraConsulta)
    .list();
  ```

3. ``Object[] resultado = ...``
   - Cuando estás seleccionando **múltiples columnas** en la consulta.
   - Si solo seleccionas una columna, no necesitas un array.

```java
Object[] resultado = session.createQuery(
    "SELECT v.nombre, v.descripcion FROM Vulnerabilidad v WHERE v.id = :id", Object[].class)
    .setParameter("id", 1)
    .uniqueResult();
```