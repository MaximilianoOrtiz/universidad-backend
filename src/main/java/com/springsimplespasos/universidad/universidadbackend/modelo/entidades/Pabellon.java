package com.springsimplespasos.universidad.universidadbackend.modelo.entidades;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "pabellones")
public class Pabellon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "metros_cuadrados")
    @Positive(message = "El valor debe de ser positivo")
    private Double mtr2;
    @Column(name = "nombre_pabellon", unique = true, nullable = false)
    @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
    private String nombre;
    @Embedded // Hace referencia a una clase enbebida
    @AttributeOverrides({//        nnombre del atributo en clase           nombre de la db
            @AttributeOverride(name = "codogoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "dpto", column = @Column(name = "departamento"))
    })

    private Direccion direccion;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaUltimaModificacion;

    @OneToMany( // Cuando la terminacion en Many es recomendable hacer carga perezosa, para no tener tanta demanda a la base de datos
            mappedBy = "pabellon",
            fetch = FetchType.LAZY
    )
    private Set<Aula> aulas;

    public Pabellon() {
    }

    public Pabellon(Integer id, Double mtr2, String nombre, Direccion direccion) {
        this.id = id;
        this.mtr2 = mtr2;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMtr2() {
        return mtr2;
    }

    public void setMtr2(Double mtr2) {
        this.mtr2 = mtr2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Set<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pabellon pabellon = (Pabellon) o;
        return id.equals(pabellon.id) && nombre.equals(pabellon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Pabellon{" +
                "id=" + id +
                ", mtr2=" + mtr2 +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", fechaAlta=" + fechaAlta +
                ", fechaUltimaModificacion=" + fechaUltimaModificacion +
                '}';
    }
}

