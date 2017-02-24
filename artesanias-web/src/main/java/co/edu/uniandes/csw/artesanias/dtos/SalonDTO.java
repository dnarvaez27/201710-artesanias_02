/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ia.salazar
 */
@XmlRootElement
public class SalonDTO {

    private Long id;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  --> @generated @ordered
     */
    private Integer numero;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  --> @generated @ordered
     */
    private Integer capacidad;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  --> @generated @ordered
     */
    public Conferencia conferencia;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  --> @generated @ordered
     */
    public Pabellon pabellon;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  --> @generated
     */
    public void setPabellon(Pabellon pabellon) {
        this.pabellon = pabellon;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Conferencia getConferencia() {
        return conferencia;
    }

    public Pabellon getPabellon() {
        return pabellon;
    }

    public SalonDTO() {

    }

    public SalonDTO(SalonEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.numero = entity.getNumero();
            this.capacidad = entity.getCapacidad();
            this.Pabellon = entity.getPabellon();
            this.Conferencia = entity.getConferencia();
        }
    }

    /**
     *
     * @return
     */
    public SalonEntity toEntity() {
        SalonEntity entity = new SalonEntity();

        entity.setId(this.getId());
        entity.setNumero(this.getNumero());
        entity.setCapacidad(this.getCapacidad());
        entity.setPabellon(this.getPabellon());
        entity.setConferencia(this.getConferencia());
        return entity;
    }
}
