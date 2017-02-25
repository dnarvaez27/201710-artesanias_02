/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Entidad de las ferias.
 * @author ma.trujillo10
 */
@Entity
public class FeriaEntity implements Serializable {

    /**
     * Id de la feria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la feria.
     */
    private String nombre;

    /**
     * Fecha de inicio de la feria.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;

    /**
     * Fecha de fin de la feria.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fin;

    /**
     * Espacio donde se da la feria.
     */
    @ManyToOne(targetEntity = EspacioEntity.class)
    @JoinColumn(name = "espacio_id")
    private EspacioEntity espacio;

    /**
     * Conjunto de artesanos que asistirán a la feria.
     */
    @ManyToMany
    @JoinTable(
      name="ferias_artesanos",
      joinColumns=@JoinColumn(name="feria_id", referencedColumnName="id"),
      inverseJoinColumns=@JoinColumn(name="artesano_id", referencedColumnName="id"))
    private Set<ArtesanoEntity> artesanos;

    /**
     * Conjunto de boletas vendidas de la feria.
     */
    @OneToMany
    private Set<BoletaEntity> boletas;

    /**
     * Conjunto de conferencias que se van a realizar en la feria.
     */
    @OneToMany
    private Set<ConferenciaEntity> conferencias;

    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public EspacioEntity getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioEntity espacio) {
        this.espacio = espacio;
    }

    public Set<ArtesanoEntity> getArtesanos() {
        return artesanos;
    }

    public void setArtesanos(Set<ArtesanoEntity> artesanos) {
        this.artesanos = artesanos;
    }

    public Set<BoletaEntity> getBoletas() {
        return boletas;
    }

    public void setBoletas(Set<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    public Set<ConferenciaEntity> getConferencias() {
        return conferencias;
    }

    public void setConferencias(Set<ConferenciaEntity> conferencias) {
        this.conferencias = conferencias;
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
        final FeriaEntity other = (FeriaEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
