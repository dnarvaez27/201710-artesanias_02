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
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class CiudadDTO {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

    /**
     * Id de la ciudad
     * Valor auto generado por la base de datos.
     */
    private Long id;

    /**
     * Nombre de la ciudad.
     */
    private String nombre;

    /**
     * Nombre del pais al que pertenece la ciudad.
     */
    private String pais;
    
    private String image;
    
    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    
    public CiudadDTO() {}
    
    /**
     * Crea un objeto CiudadDTO a partir de un objeto CiudadEntity.
     * @param entity entidad ciudad desde la cual se va a crear el nuevo objeto.
     */
    public CiudadDTO(CiudadEntity entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.pais = entity.getPais();
        this.image = entity.getImage();
        
    }
    
    //--------------------------------------------------------------------------
    // Getters y Setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Convierte un objeto CiudadDTO a CiudadEntity.
     * @return Nuevo objeto CiudadEntity.
     */
    public CiudadEntity toEntity() {
        CiudadEntity entity = new CiudadEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setPais(this.pais);
        entity.setImage(this.image);
        return entity;
    }
}
