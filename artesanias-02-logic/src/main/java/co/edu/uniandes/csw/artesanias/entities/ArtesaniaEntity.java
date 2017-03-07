package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Artesania Entity, contiene la información de una artesania creada por un artesano
 *
 * @author d.narvaez11
 */
@Entity
public class ArtesaniaEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
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
	@ManyToOne( targetEntity = ArtesanoEntity.class )
	private ArtesanoEntity artesano;
	
	/**
	 * Retrieves the id of the ArtesaniaEntity
	 *
	 * @return The id of the ArtesaniaEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param id The new id of the ArtesaniaEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the ArtesaniaEntity
	 *
	 * @return The nombre of the ArtesaniaEntity
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param nombre The new nombre of the ArtesaniaEntity
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the descripcion of the ArtesaniaEntity
	 *
	 * @return The descripcion of the ArtesaniaEntity
	 */
	public String getDescripcion( )
	{
		return descripcion;
	}
	
	/**
	 * Updates the descripcion of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param descripcion The new descripcion of the ArtesaniaEntity
	 */
	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}
	
	/**
	 * Retrieves the precio of the ArtesaniaEntity
	 *
	 * @return The precio of the ArtesaniaEntity
	 */
	public Double getPrecio( )
	{
		return precio;
	}
	
	/**
	 * Updates the precio of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param precio The new precio of the ArtesaniaEntity
	 */
	public void setPrecio( Double precio )
	{
		this.precio = precio;
	}
	
	/**
	 * Retrieves the artesano of the ArtesaniaEntity
	 *
	 * @return The artesano of the ArtesaniaEntity
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param artesano The new artesano of the ArtesaniaEntity
	 */
	public void setArtesano( ArtesanoEntity artesano )
	{
		this.artesano = artesano;
	}
	
	@Override
	public int hashCode( )
	{
		if( id == null )
		{
			return super.hashCode( );
		}
		return id.hashCode( );
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( id == null )
		{
			return super.equals( obj );
		}
		if( obj instanceof ArtesaniaEntity )
		{
			return id.equals( ( ( ArtesaniaEntity ) obj ).id );
		}
		return false;
	}
}