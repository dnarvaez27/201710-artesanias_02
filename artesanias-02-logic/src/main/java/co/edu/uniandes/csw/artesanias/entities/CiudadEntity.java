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
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad de las ciudades.
 * @author ma.trujillo10
 */
@Entity
@XmlRootElement
public class CiudadEntity implements Serializable {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

    /**
     * Id de la ciudad
     * Valor auto generado por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la ciudad.
     */
    private String nombre;

    /**
     * Nombre del pais al que pertenece la ciudad.
     */
    private String pais;

    /**
     * Conjunto de espacios que se encuentran en la ciudad.
     * Relación bidireccional con EspacioEntity. En caso de eliminar la ciudad, 
     * se eliminarían todos los espacios asociados. El dueño de la relación es
     * EspacioEntity.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudad",
            targetEntity = EspacioEntity.class)
    private List<EspacioEntity> espacios;

    /**
     * Conjunto de artesanos que se originan en la ciudad.
     * Relación bidireccional con ArtesanoEntity. El dueño de la relación es
     * ArtesanoEntity.
     */
    @OneToMany(mappedBy = "ciudad", targetEntity = ArtesanoEntity.class)
    private List<ArtesanoEntity> artesanos;

    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Devuelve el id de la ciudad.
     * @return id de la ciudad.
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de la ciudad.
     * post: Se cambió el id de la ciudad.
     * @param id nuevo id de la ciudad.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la ciudad.
     * @return nombre de la ciudad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de la ciudad.
     * post: Se cambió el nombre de la ciudad.
     * @param nombre nuevo nombre de la ciudad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre del país a la que pertenece la ciudad.
     * @return nombre del pais al que pertenece la ciudad.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Cambia el nombre del país al que pertenece la ciudad.
     * post: Se cambió el nombre del país al que pertenece la ciudad.
     * @param pais nuevo nombre del país al que pertenece la ciudad.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Devuelve el conjunto de espacios que se encuentran en la ciudad.
     * @return Conjunto de espacios que se encuentran en la ciudad.
     */
    @XmlTransient
    public List<EspacioEntity> getEspacios() {
        return espacios;
    }

    /**
     * Cambia el conjunto de espacios que se encuentran en la ciudad.
     * post: Se cambió el conjunto de espacios que se encuentran en la ciudad.
     * @param espacios nuevo conjunto de espacios que se encuentran en la ciudad.
     */
    public void setEspacios(List<EspacioEntity> espacios) {
        this.espacios = espacios;
    }

    /**
     * Devuelve el conjunto de artesanos originarios de la ciudad.
     * @return conjunto de artesanos originarios de la ciudad.
     */
    public List<ArtesanoEntity> getArtesanos() {
        return artesanos;
    }

    /**
     * Cambia el conjunto de artesanos originarios de la ciudad.
     * post: Se cambió el conjunto de artesanos originarios de la ciudad.
     * @param artesanos nuevo conjunto de artesanos originarios de la ciudad.
     */
    public void setArtesanos(List<ArtesanoEntity> artesanos) {
        this.artesanos = artesanos;
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
        final CiudadEntity other = (CiudadEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
