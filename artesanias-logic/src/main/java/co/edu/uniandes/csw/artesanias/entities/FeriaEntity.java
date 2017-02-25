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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author ma.trujillo10
 */
@Entity
public class FeriaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Id de la feria.
     */
    private Long id;

    /**
     * Nombre de la feria.
     */
    private String nombre;

    /**
     * Fecha de inicio de la feria.
     */
    private Date inicio;

    /**
     * Fecha de fin de la feria.
     */
    private Date fin;

    /**
     * Espacio donde se da la feria.
     */
    private EspacioEntity espacio;

    /**
     * Conjunto de artesanos que asistirán a la feria.
     */
    @ManyToMany
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

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFin() {
        return fin;
    }

    public EspacioEntity getEspacio() {
        return espacio;
    }

    public Set<ArtesanoEntity> getArtesanos() {
        return artesanos;
    }

    public Set<BoletaEntity> getBoletas() {
        return boletas;
    }

    public Set<ConferenciaEntity> getConferencias() {
        return conferencias;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public void setEspacio(EspacioEntity espacio) {
        this.espacio = espacio;
    }

    public void setArtesanos(Set<ArtesanoEntity> artesanos) {
        this.artesanos = artesanos;
    }

    public void setBoletas(Set<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    public void setConferencias(Set<ConferenciaEntity> conferencias) {
        this.conferencias = conferencias;
    }

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
        if (!Objects.equals(this.id, other.id)) return false;
        return true;
    }
}
