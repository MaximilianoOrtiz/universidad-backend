package com.springsimplespasos.universidad.universidadbackend.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)

/**
 *Permite manejar tipo polimorficos, a la hora de serializar el Json Jackson no determina que tipo de dato es
 * */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
/**
 *
 * */
@JsonSubTypes({
        @JsonSubTypes.Type(value = Alumno.class, name = "alumno"),
        @JsonSubTypes.Type(value = Profesor.class, name = "profesor"),
        @JsonSubTypes.Type(value = Empleado.class, name = "empleado")
})
public abstract class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 60)
//   @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
//    @NotEmpty(message = "Debe de ingresar un valor")
    private String nombre;
    @Column (nullable = false, length = 60)
 //   @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
//    @NotEmpty(message = "Debe de ingresar un valor")
    private String apellido;
    @Column (nullable = false, length = 10, unique = true)
//    @Size(min = 0, max = 8, message = "Dni invalido")
//    @NotNull(message = "No se aceptan valores nulos")
    private String dni;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaUltimaModificacion;
    @Embedded // Hace referencia a una clase enbebida
    @AttributeOverrides({//         nombre del atributo en clase           nombre de la db
            @AttributeOverride(name = "codogoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "dpto", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }

    @PreUpdate
    private void antesDeUpdate(){
        this.fechaUltimaModificacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion=" + direccion +
                ", fechaAlta=" + fechaAlta +
                ", fechaUltimaModificacion=" + fechaUltimaModificacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id.equals(persona.id) && dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni);
    }
}
