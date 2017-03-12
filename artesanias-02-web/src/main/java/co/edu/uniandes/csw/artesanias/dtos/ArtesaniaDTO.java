package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author d.narvaez11
 */
@XmlRootElement
public class ArtesaniaDTO implements Serializable
{
	private Long id;
	
	/**
	 * Nomrbe de la Artesnia
	 */
	private String nombre;
	
	/**
	 * Descripcion de la Artesania
	 */
	private String descripcion;
	
	public ArtesaniaDTO( )
	{
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ArtesaniaDTO by the fields from the ArtesaniaEntity given
	 *
	 * @param entity ArtesaniaEntity to fill up the ArtesaniaDTO
	 */
	public ArtesaniaDTO( ArtesaniaEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.nombre = entity.getNombre( );
			this.descripcion = entity.getDescripcion( );
		}
	}
	
	/**
	 * Retrieves an ArtesaniaEntity with the fields of this ArtesaniaDTO
	 */
	public ArtesaniaEntity toEntity( )
	{
		ArtesaniaEntity entity = new ArtesaniaEntity( );
		entity.setId( this.id );
		entity.setNombre( this.nombre );
		entity.setDescripcion( this.descripcion );
		return entity;
	}
	
	/**
	 * Retrieves the id of the ArtesaniaDTO
	 *
	 * @return The id of the ArtesaniaDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesaniaDTO by the one given by parameter
	 *
	 * @param id The new id of the ArtesaniaDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the ArtesaniaDTO
	 *
	 * @return The nombre of the ArtesaniaDTO
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the ArtesaniaDTO by the one given by parameter
	 *
	 * @param nombre The new nombre of the ArtesaniaDTO
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the descripcion of the ArtesaniaDTO
	 *
	 * @return The descripcion of the ArtesaniaDTO
	 */
	public String getDescripcion( )
	{
		return descripcion;
	}
	
	/**
	 * Updates the descripcion of the ArtesaniaDTO by the one given by parameter
	 *
	 * @param descripcion The new descripcion of the ArtesaniaDTO
	 */
	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}
}