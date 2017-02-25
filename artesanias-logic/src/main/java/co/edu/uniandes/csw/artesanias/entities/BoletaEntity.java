/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ma.trujillo10
 */
@Entity
public class BoletaEntity implements Serializable {

    public static double MENORES = 0.7;
    public static double REGULAR = 1.0;
    public static double MAYORES = 0.8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tipo;

    /**
     * Fecha de inicio de la boleta.
     */
    private Date inicio;

    /**
     * Fecha de fin de la boleta.
     */
    private Date fin;

    private Double precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTipo() {
        return tipo;
    }

    public void setTipo(Double tipo) {
        this.tipo = tipo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Double getPrecio() {
        return precio*tipo;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
