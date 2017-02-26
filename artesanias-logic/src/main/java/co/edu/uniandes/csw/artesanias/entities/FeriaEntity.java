/*
 * The MIT License
 *
 * Copyright 2017 Miller.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad de las ferias.
 * @author ma.trujillo10
 */
@Entity
@XmlRootElement
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

    /**
     * Devuelve el id de la feria.
     * @return id de la feria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de la feria.
     * post: Se cambió el id de la feria.
     * @param id nuevo id de la feria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la feria.
     * @return nombre de la feria.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de la feria.
     * @param nombre nuevo nombre de la feria.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la fecha de inicio de la feria.
     * @return fecha de inicio de la feria.
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * Cambia la fecha de inicio de la feria.
     * post: Se cambió la fecha de inicio de la feria.
     * @param inicio nueva fecha de inicio de la feria.
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * Devuelve la fecha en que termina la feria.
     * @return fecha en que termina la feria.
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Cambia la fecha en que termina la feria.
     * post: Se cambió la fecha en que termina la feria.
     * @param fin nueva fecha en que termina la feria.
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * Devuelve el espacio donde se realiza la feria.
     * @return espacio donde se realiza la feria.
     */
    public EspacioEntity getEspacio() {
        return espacio;
    }

    /**
     * Cambia el espacio donde se realiza la feria.
     * post: Se cambió el espacio donde se realiza la feria.
     * @param espacio nuevo espacio donde se realiza la feria.
     */
    public void setEspacio(EspacioEntity espacio) {
        this.espacio = espacio;
    }

    /**
     * Devuelve el conjunto de artesanos que asistirán o asistieron a la feria.
     * @return conjunto de artesanos que asistirán a la feria.
     */
    @XmlTransient
    public Set<ArtesanoEntity> getArtesanos() {
        return artesanos;
    }

    /**
     * Cambia el conjunto de artesanos que asistirán o asistieron a la feria.
     * post: Se cambió el conjunto de artesanos que asistirán o asistieron a la 
     *      feria.
     * @param artesanos nuevo conjunto de artesanos que asistirán o asistieron 
     *      a la feria.
     */
    public void setArtesanos(Set<ArtesanoEntity> artesanos) {
        this.artesanos = artesanos;
    }

    /**
     * Devuelve el conjunto de boletas vendidas de la feria.
     * @return conjunto de boletas vendidas de la feria.
     */
    @XmlTransient
    public Set<BoletaEntity> getBoletas() {
        return boletas;
    }

    /**
     * Cambia el conjuto de las boletas vendidas de la feria.
     * post: Se cambió el conjunto de boletas vendidas de la feria.
     * @param boletas nuevo conjunto de boletas vendidas de la feria.
     */
    public void setBoletas(Set<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    /**
     * Devuelve el conjunto de conferencias realizadas o a realizar de la feria.
     * @return conjunto de conferencias realizadas o a realizar de la feria.
     */
    @XmlTransient
    public Set<ConferenciaEntity> getConferencias() {
        return conferencias;
    }

    /**
     * Cambia el conjunto de conferencias realizadas o a realizar de la feria.
     * post: Se cambió el conjunto de conferencias realizadas o a realizar de la
     *      feria.
     * @param conferencias nuevo conjunto de conferencias realizadas o a 
     *      realizar de la feria.
     */
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
