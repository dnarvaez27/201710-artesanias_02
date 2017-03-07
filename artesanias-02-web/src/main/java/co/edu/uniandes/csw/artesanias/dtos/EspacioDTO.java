/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        this.capacidad = entity.getCapacidad();
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
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        entity.setCapacidad(this.capacidad);
        return entity;
    }
}
