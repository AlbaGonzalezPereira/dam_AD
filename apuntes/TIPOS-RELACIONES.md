# Tipos de Relaciones en JPA/Hibernate
En JPA/Hibernate, las relaciones entre entidades se definen mediante anotaciones que indican la cardinalidad y la dirección de la asociación.

1. **@OneToOne** (Uno a Uno)

- **Unidireccional**: Una entidad tiene una referencia a otra, pero no viceversa.

```java
@Entity
public class Persona {
    @OneToOne
    private HistorialMedico historialMedico;
}
```

- **Bidireccional**: Ambas entidades tienen referencias mutuas.

```java
@Entity
public class Persona {
    @OneToOne(mappedBy = "persona") // donde no esté el id en la base de datos
    private HistorialMedico historialMedico;
}

@Entity
public class HistorialMedico {
    @OneToOne
    @JoinColumn(name = "persona_id")// donde esté el id en la base de datos
    private Persona persona;
}
```

En este caso, HistorialMedico es el lado propietario de la relación.


1. **@OneToMany / @ManyToOne** (Uno a Muchos / Muchos a Uno)
- **Unidireccional**: Solo una entidad conoce la relación.

```java
@Entity
public class Departamento {
    @OneToMany
    @JoinColumn(name = "departamento_id")
    private List<Empleado> empleados;
}
```

Aquí, ``Empleado`` no tiene referencia a Departamento.


- **Bidireccional**: Ambas entidades conocen la relación.

```java
@Entity
public class Departamento {
    @OneToMany(mappedBy = "departamento")
    private List<Empleado> empleados;
}

@Entity
public class Empleado {
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}
```

3. **@ManyToMany** (Muchos a Muchos)
- **Bidireccional**: Ambas entidades tienen colecciones que se refieren mutuamente.

```java
@Entity
public class Estudiante {
@ManyToMany
@JoinTable(
    name = "estudiante_curso",
    joinColumns = @JoinColumn(name = "estudiante_id"),
    inverseJoinColumns = @JoinColumn(name = "curso_id")
)
private Set<Curso> cursos;
}

@Entity
public class Curso {
@ManyToMany(mappedBy = "cursos")
private Set<Estudiante> estudiantes;
}
```


## Bidireccionalidad en Relaciones
La bidireccionalidad permite que ambas entidades conozcan y naveguen la relación.
javaespanol.blogspot.com

- **Lado propietario**: Es la entidad que contiene la anotación ``@JoinColumn`` o ``@JoinTable``.

- **Lado inverso**: Utiliza la propiedad mappedBy para indicar que la relación está mapeada por el otro lado.

Es importante mantener la coherencia entre ambos lados de la relación para evitar inconsistencias.

