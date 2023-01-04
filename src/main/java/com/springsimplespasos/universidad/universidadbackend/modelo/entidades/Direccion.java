package com.springsimplespasos.universidad.universidadbackend.modelo.entidades;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Embeddable;
import java.io.Serializable;

// No es una entidad, si no, una clase embebida dentro de otra. al momento de cargar busca las entidades no tiene en cuenta las que esten anotadad con @Embeddable
@Embeddable
@ApiModel(description = "Direccion", value = "Direccion", reference = "Direccion")
public class Direccion implements Serializable {

    @ApiModelProperty(name ="calle", example = "Calle falsa")
    private String calle;
    @ApiModelProperty(name ="Altura", example = "448")
    private String numero;
    @ApiModelProperty(name ="Codigo Postal", example = "1888")
    private String codigoPostal;
    @ApiModelProperty(name ="Departamento", example = "10A")
    private String departamento;
    @ApiModelProperty(name ="Piso del departamento", example = "10")
    private String piso;
    @ApiModelProperty(name ="Localidad", example = "FLorencio Varela")
    private String localidad;

    public Direccion() {
    }

    public Direccion(String calle, String numero, String codigoPostal, String departamento, String piso, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.departamento = departamento;
        this.piso = piso;
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", departamento='" + departamento + '\'' +
                ", piso='" + piso + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
