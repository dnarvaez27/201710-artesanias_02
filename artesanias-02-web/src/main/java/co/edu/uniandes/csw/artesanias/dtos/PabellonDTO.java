/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ja.espinosa12
 */

@XmlRootElement
public class PabellonDTO
{
        /**
         * Id único del pabellón
         */
	private Long id;
	
        /**
         * Tipo del pabellón
         */
	private String tipo;
	
        /**
         * Capacidad del pabellón
         */
	private Integer capacidad;
        
        private String imagen;
	
        /**
         * Constructor vacío
         */
	public PabellonDTO( )
	{
		
	}
	
        /**
         * Construye un nuevo pabellón cona base a un PabellonEntity
         * @param entity 
         */
	public PabellonDTO( PabellonEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.tipo = entity.getTipo( );
			this.capacidad = entity.getCapacidad( );
                        this.imagen = entity.getImagen();
		}
	}
	
        public String getImagen()
        {
            return imagen;
        }
        
        public void setImagen( String pImage)
        {
            this.imagen = pImage;
        }
        
	/**
	 * @return the id
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * @return the tipo
	 */
	public String getTipo( )
	{
		return tipo;
	}
	
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo( String tipo )
	{
		this.tipo = tipo;
	}
	
	/**
	 * @return the capacidad
	 */
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
        /**
         * Convierte el pabellón actual en un PabellonEntity
         * @return entity
         */
	public PabellonEntity toEntity( )
	{
		PabellonEntity entity = new PabellonEntity( );
		entity.setId( this.getId( ) );
		entity.setTipo( this.getTipo( ) );
		entity.setCapacidad( this.getCapacidad( ) );
                entity.setImagen(this.getImagen());
		return entity;
	}
	
}
