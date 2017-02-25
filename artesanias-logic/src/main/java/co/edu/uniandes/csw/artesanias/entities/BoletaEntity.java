/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * Entidad de las boletas.
 * @author ma.trujillo10
 */
@Entity
public class BoletaEntity implements Serializable {
    
    //--------------------------------------------------------------------------
    // Constantes
    //--------------------------------------------------------------------------

    /**
     * Descuento para menores de edad.
     */
    public static double MENORES = 0.7;

    /**
     * Precio regular de la boleta.
     */
    public static double REGULAR = 1.0;
    
    /**
     * Descuento para mayores de 65 años.
     */
    public static double MAYORES = 0.8;

        
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    /**
     * Id de la boleta.
     * Valor auto generado por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de boleta.
     */
    private Double tipo;

    /**
     * Fecha de inicio de la boleta.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;

    /**
     * Fecha de fin de la boleta.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fin;

    /**
     * Fecha de la boleta.
     */
    private Double precio;
    
        
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------

    /**
     * Devuelve el id de la boleta.
     * @return id de la bolet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el valor del id de la boleta.
     * post: Se cambio el id de la boleta.
     * @param id nuevo id de la boleta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el tipo de la boleta.
     * @return tipo de la boleta.
     */
    public Double getTipo() {
        return tipo;
    }

    /**
     * Cambia el valor del tipo de la boleta.
     * post: Se cambio el tipo de la boleta.
     * @param tipo nuevo tipo de la boleta.
     */
    public void setTipo(Double tipo) {
        assert(tipo == MENORES || tipo == REGULAR || tipo == MAYORES);
        this.tipo = tipo;
    }

    /**
     * Devuelve la fecha de inicio de la boleta.
     * @return fecha de inicio de la boleta.
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * Cambia la fecha de inicio de la boleta.
     * post: Se cambio la fecha de inicio de la boleta.
     * @param inicio nueva fecha de inicio de la boleta.
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * Devuelve la fecha de fin de la boleta.
     * @return fecha de inicio de la boleta.
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Cambia la fecha de fin de la boleta.
     * post: Se cambio la fecha de fin de la boleta.
     * @param fin nueva fecha de fin de la boleta.
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * Devuelve el precio de la boleta.
     * @return precio de la bolet.
     */
    public Double getPrecio() {
        return precio*tipo;
    }

    /**
     * Cambia el precio de la boleta.
     * post: Se cambio el precio de la boleta.
     * @param precio nuevo precio de la boleta.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    //--------------------------------------------------------------------------
    // Métodos del objeto
    //--------------------------------------------------------------------------
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)    return true;
        if (obj == null)    return false;
        if (getClass() != obj.getClass())   return false;
        final BoletaEntity other = (BoletaEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}