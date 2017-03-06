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
	
	/**
	 * Precio de la Artesania
	 */
	private Double precio;
	
	/**
	 * Artesano quien creó la artesania
	 */
	private ArtesanoEntity artesano;
	
	public ArtesaniaDTO( )
	{
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
			this.precio = entity.getPrecio( );
			this.artesano = entity.getArtesano( );
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
		entity.setPrecio( this.precio );
		entity.setArtesano( this.artesano );
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
	
	/**
	 * Retrieves the precio of the ArtesaniaDTO
	 *
	 * @return The precio of the ArtesaniaDTO
	 */
	public Double getPrecio( )
	{
		return precio;
	}
	
	/**
	 * Updates the precio of the ArtesaniaDTO by the one given by parameter
	 *
	 * @param precio The new precio of the ArtesaniaDTO
	 */
	public void setPrecio( Double precio )
	{
		this.precio = precio;
	}
	
	/**
	 * Retrieves the artesano of the ArtesaniaDTO
	 *
	 * @return The artesano of the ArtesaniaDTO
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ArtesaniaDTO by the one given by parameter
	 *
	 * @param artesano The new artesano of the ArtesaniaDTO
	 */
	public void setArtesano( ArtesanoEntity artesano )
	{
		this.artesano = artesano;
	}
}