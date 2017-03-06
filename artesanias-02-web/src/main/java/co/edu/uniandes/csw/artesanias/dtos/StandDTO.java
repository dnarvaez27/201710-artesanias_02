/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import java.awt.Dimension;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ja.espinosa12
 */

@XmlRootElement
public class StandDTO {
    
    private Long id;
    
    private Integer numero;
    
    private Dimension dimensiones;
    
    private String descripcion;
    
    private Double precio;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the dimensiones
     */
    public Dimension getDimensiones() {
        return dimensiones;
    }

    /**
     * @param dimensiones the dimensiones to set
     */
    public void setDimensiones(Dimension dimensiones) {
        this.dimensiones = dimensiones;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public StandDTO( )
    {
		
    }
	
    public StandDTO( StandEntity entity )
    {
	if( entity != null )
	{
            this.id = entity.getId( );
            this.numero = entity.getNumero( );
            this.dimensiones = entity.getDimensiones( );
            this.descripcion = entity.getDescripcion();
            this.precio = entity.getPrecio();
	}
    }
	
    public StandEntity toEntity( )
    {
        StandEntity entity = new StandEntity( );
		
	entity.setId( this.getId( ) );
        entity.setNumero( this.getNumero());
        entity.setDimensiones( this.getDimensiones());
        entity.setDescripcion( this.getDescripcion());
        entity.setPrecio( this.getPrecio());
        
	return entity;
    }
}
