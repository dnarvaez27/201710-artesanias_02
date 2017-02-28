/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        return entity;
    }
}
