/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entidad de los espacios.
 * @author ma.trujillo10
 */
@Entity
public class EspacioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String direccion;
    
    private String telefono;
    
    private Integer capacidad;
    
    @ManyToOne(targetEntity = CiudadEntity.class)
    @JoinColumn(name = "ciudad_id")
    private CiudadEntity ciudad;
    
    @ManyToMany
    @OneToMany(mappedBy = "espacio")
    private Set<FeriaEntity> ferias;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = ciudad;
    }

    public Set<FeriaEntity> getFerias() {
        return ferias;
    }

    public void setFerias(Set<FeriaEntity> ferias) {
        this.ferias = ferias;
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
        final EspacioEntity other = (EspacioEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
