# LOMBOK
Lombok es una librería de código abierto utilizada para el desarrollo en Java que permite generar automáticamente código común y repetitivo, como getters, setters, constructores, y más, mediante el uso de anotaciones. Esto mejora la legibilidad del código y acelera el desarrollo pues evita la escritura de código técnico repetitivo (el famoso boilerplate).
 
Para el uso de Lombok solo será necesario añadir al ``pom.xml`` de maven la siguiente dependencia:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

Su funcionamiento consiste en utilizar ciertas anotaciones a la hora de definir las clases que inyectarán código en la fase de compilación.

Dentro de las **anotaciones más utilizadas** están las siguientes:

- **``@Data``**: usando esta anotación se genera en tiempo de compilación:

  - Los ``getters`` y ``setters`` para todos los campos,
  - El método ``toString``,
  - El método ``hashCode``,
  - El método ``equals``,
  - Un constructor.

- **``@AllArgsConstructor``**: permite generar en tiempo de compilación un constructor con todos los parámetros.

- **``@NoArgsConstructor``**: permite generar en tiempo de compilación un constructor sin parámetros.

- **``@RequiredArgsConstructor``**: permite generar un constructor personalizado con todos aquellos marcados como final o que tengan la anotación @NotNull.

- **``@Builder``**: permite una nueva forma de crear nuevos objetos diferente al típico ``new``.
```java
Persona persona = Persona.builder()
                    .id(1)
                    .nombre("Persona 1")
                    .direccion("Direccion 1")
                    .edad(45)
                    .build();
```

- **``@SuperBuilder``**: es la alternativa a @Builder cuando se quiere establecer una relación de herencia entre clases. Toda superclase debe tener esta anotación para que no se generen problemas a la hora de generar relaciones de herencia.

Ejemplo:

Crear la clase ``Persona`` que tenga las siguientes propiedades: ``id``, ``nombre``, ``edad`` y ``direccion``. Se deberán implementar getters, setters, el constructor vacío, el constructor con todos los parámetros y un constructor con los tres últimos parámetros

```java
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Persona {

  private int id;
  @NonNull
  private String nombre;
  @NonNull
  private int edad;
  @NonNull
  private String direccion;

}
```
