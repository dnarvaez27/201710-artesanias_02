/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ja.espinosa12
 */

@XmlRootElement
public class EspacioDTO
{

	private Long id;
	

	private String direccion;
	
        private String telefono;
        
	private Integer capacidad;
        
        private String imagen;
	

	public EspacioDTO( )
	{
		
	}
	
	public EspacioDTO( EspacioEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.direccion = entity.getDireccion( );
                        this.telefono = entity.getTelefono( );
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
	

	public String getDireccion( )
	{
		return direccion;
	}

	public void setDireccion( String direccion )
	{
		this.direccion = direccion;
	}
        
        public String getTelefono( )
	{
		return telefono;
	}

	public void setTelefono( String telefono )
	{
		this.telefono = telefono;
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
	
	public EspacioEntity toEntity( )
	{
		EspacioEntity entity = new EspacioEntity( );
		entity.setId( this.getId( ) );
		entity.setDireccion( this.getDireccion( ) );
                entity.setTelefono( this.getTelefono( ) );
		entity.setCapacidad( this.getCapacidad( ) );
                entity.setImagen(this.getImagen());
		return entity;
	}
	
}
