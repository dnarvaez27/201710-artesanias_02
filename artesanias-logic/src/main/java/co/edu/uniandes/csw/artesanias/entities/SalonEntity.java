/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ia.salazar
 */
@Entity
public class SalonEntity implements Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
     
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Long id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Integer numero;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Integer capacidad;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Conferencia conferencia;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
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
     * <!--  end-user-doc  -->
     * @generated
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
        
    @Override
    public boolean equals(Object obj) {
        
        if (this.getId()!=null) {
            
            return this.getId().equals(((SalonEntity) obj).getId());
            
        }
        return super.equals(obj);
    }
    
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
        }
	

