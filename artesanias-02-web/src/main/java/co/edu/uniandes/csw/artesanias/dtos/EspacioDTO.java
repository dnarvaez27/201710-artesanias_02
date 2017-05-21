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

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Miller
 */
@XmlRootElement
public class EspacioDTO {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

    /**
     * Id del espacio.
     * El valor es auto generado por la base de datos.
     */
    private Long id;
    
    /**
     * Nombre del espacio.
     */
    private String nombre;
    
    /**
     * Dirección del espacio.
     */
    private String direccion;
    
    /**
     * Telefono del espacio.
     */
    private String telefono;
    
    /**
     * Capacidad del espacio.
     */
    private Integer capacidad;
    
    private String image;
    
    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    
    public EspacioDTO() {}

    /**
     * Crea un objeto EspacioDTO a partir de un objeto EspacioEntity.
     * @param entity entidad espacio desde la cual se va a crear el nuevo objeto.
     */
    public EspacioDTO(EspacioEntity entity) {
        if (entity == null) return;
        this.id = entity.getId();
        this.direccion = entity.getDireccion();
        this.telefono = entity.getTelefono();
        this.nombre = entity.getNombre();
        this.capacidad = entity.getCapacidad();
        this.image = entity.getImage();
    }
    
    //--------------------------------------------------------------------------
    // Getters y Setters
    //--------------------------------------------------------------------------

    /**
     * Devuelve el id del espacio.
     * @return id del espacio.
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id del espacio.
     * post: Se cambió el id del espacio.
     * @param id nuevo id del espacio.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Devuelve el nombre del espacio.
     * @return nombre del espacio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del espacio.
     * post: Se cambió el nombre del espacio.
     * @param nombre nuevo nombre del espacio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la dirección del espacio.
     * @return dirección del espacio.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Cambia la dirección del espacio.
     * post: Se cambió la dirección del espacio.
     * @param direccion nueva dirección del espacio.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve el número de teléfono del espacio.
     * @return número de teléfono del espacio.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Cambia el número de teléfono del espacio.
     * post: Se cambió el número de teléfono del espacio.
     * @param telefono nuevo número de teléfono del espacio.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve la capacidad del espacio.
     * @return capacidad del espacio.
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * Cambia la capacidad del espacio.
     * post: Se cambió la capacidad del espacio.
     * @param capacidad nueva capacidad del espacio.
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
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
     * Convierte un objeto EspacioDTO a EspacioEntity.
     * @return Nuevo objeto EspacioEntity.
     */
    public EspacioEntity toEntity() {
        EspacioEntity entity = new EspacioEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        entity.setCapacidad(this.capacidad);
        entity.setImage(this.image);
        return entity;
    }
}
