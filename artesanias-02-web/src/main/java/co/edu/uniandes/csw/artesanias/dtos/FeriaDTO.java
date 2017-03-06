/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class FeriaDTO implements Serializable {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

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
    
    
    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    
    public FeriaDTO() {}
    
    /**
     * Crea un objeto FeriaDTO a partir de un objeto FeriaEntity.
     * @param entity entidad feria desde la cual se va a crear el nuevo objeto.
     */
    public FeriaDTO(FeriaEntity entity) {
        if (entity == null) return;
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.inicio = entity.getInicio();
        this.fin = entity.getFin();
    }
    
    //--------------------------------------------------------------------------
    // Métodos Getter y Setters
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
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Convierte un objeto FeriaDTO a FeriaEntity.
     * @return Nuevo objeto FeriaEntity.
     */
    public FeriaEntity toEntity() {
        FeriaEntity entity = new FeriaEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setInicio(this.inicio);
        entity.setFin(this.fin);
        return entity;
    }
}
