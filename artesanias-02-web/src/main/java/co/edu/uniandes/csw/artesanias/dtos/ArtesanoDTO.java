package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author d.narvaez11
 */
@XmlRootElement
public class ArtesanoDTO implements Serializable
{
	private Long id;
	
	/**
	 * Nombre del Artesano
	 */
	private String nombre;
	
	/**
	 * Identificación del Artesano
	 */
	private String identificacion;
	
	/**
	 * Telefono del Artesano
	 */
	private String telefono;
	
	/**
	 * Builds an empty ArtesanoDTO
	 */
	public ArtesanoDTO( )
	{
		
	}
	
	/**
	 * Builds an ArtesanoDTO by the fields from the ArtesanoEntity given
	 *
	 * @param entity ArtesanoEntity to fill up the ArtesanoDTO
	 */
	public ArtesanoDTO( ArtesanoEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.nombre = entity.getNombre( );
			this.identificacion = entity.getIdentificacion( );
			this.telefono = entity.getTelefono( );
		}
	}
	
	/**
	 * Retrieves an ArtesanoEntity with the fields of this ArtesanoDTO
	 */
	public ArtesanoEntity toEntity( )
	{
		ArtesanoEntity entity = new ArtesanoEntity( );
		entity.setId( this.id );
		entity.setNombre( this.nombre );
		entity.setIdentificacion( this.identificacion );
		entity.setTelefono( this.telefono );
		return entity;
	}
	
	/**
	 * Retrieves the id of the ArtesanoDTO
	 *
	 * @return The id of the ArtesanoDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesanoDTO by the one given by parameter
	 *
	 * @param id The new id of the ArtesanoDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the ArtesanoDTO
	 *
	 * @return The nombre of the ArtesanoDTO
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the ArtesanoDTO by the one given by parameter
	 *
	 * @param nombre The new nombre of the ArtesanoDTO
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the identificacion of the ArtesanoDTO
	 *
	 * @return The identificacion of the ArtesanoDTO
	 */
	public String getIdentificacion( )
	{
		return identificacion;
	}
	
	/**
	 * Updates the identificacion of the ArtesanoDTO by the one given by parameter
	 *
	 * @param identificacion The new identificacion of the ArtesanoDTO
	 */
	public void setIdentificacion( String identificacion )
	{
		this.identificacion = identificacion;
	}
	
	/**
	 * Retrieves the telefono of the ArtesanoDTO
	 *
	 * @return The telefono of the ArtesanoDTO
	 */
	public String getTelefono( )
	{
		return telefono;
	}
	
	/**
	 * Updates the telefono of the ArtesanoDTO by the one given by parameter
	 *
	 * @param telefono The new telefono of the ArtesanoDTO
	 */
	public void setTelefono( String telefono )
	{
		this.telefono = telefono;
	}
}