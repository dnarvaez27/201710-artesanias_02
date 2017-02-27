package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Entity
public class ArtesanoEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	/**
	 * Nombre del Artesano
	 */
	@Column( nullable = false )
	private String nombre;
	
	/**
	 * Identificación del Artesano
	 */
	@Column( unique = true )
	private String identificacion;
	
	/**
	 * Telefono del Artesano
	 */
	private String telefono;
	
	/**
	 * Artesanias hechas por el Artesano
	 */
	@OneToMany( cascade = CascadeType.ALL, targetEntity = ArtesaniaEntity.class, fetch = FetchType.LAZY, mappedBy = "artesano" )
	private List<ArtesaniaEntity> artesanias;
	
	/**
	 * Reviews sobre el Artesano
	 */
	@OneToMany( cascade = CascadeType.ALL, targetEntity = ReviewEntity.class, fetch = FetchType.LAZY, mappedBy = "artesano" )
	private List<ReviewEntity> reviews;
	
	/**
	 * Retrieves the id of the ArtesanoEntity
	 *
	 * @return The id of the ArtesanoEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesanoEntity by the one given by parameter
	 *
	 * @param id The new id of the ArtesanoEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the ArtesanoEntity
	 *
	 * @return The nombre of the ArtesanoEntity
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the ArtesanoEntity by the one given by parameter
	 *
	 * @param nombre The new nombre of the ArtesanoEntity
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the identificacion of the ArtesanoEntity
	 *
	 * @return The identificacion of the ArtesanoEntity
	 */
	public String getIdentificacion( )
	{
		return identificacion;
	}
	
	/**
	 * Updates the identificacion of the ArtesanoEntity by the one given by parameter
	 *
	 * @param identificacion The new identificacion of the ArtesanoEntity
	 */
	public void setIdentificacion( String identificacion )
	{
		this.identificacion = identificacion;
	}
	
	/**
	 * Retrieves the telefono of the ArtesanoEntity
	 *
	 * @return The telefono of the ArtesanoEntity
	 */
	public String getTelefono( )
	{
		return telefono;
	}
	
	/**
	 * Updates the telefono of the ArtesanoEntity by the one given by parameter
	 *
	 * @param telefono The new telefono of the ArtesanoEntity
	 */
	public void setTelefono( String telefono )
	{
		this.telefono = telefono;
	}
	
	/**
	 * Retrieves the artesanias of the ArtesanoEntity
	 *
	 * @return The artesanias of the ArtesanoEntity
	 */
	public List<ArtesaniaEntity> getArtesanias( )
	{
		return artesanias;
	}
	
	/**
	 * Updates the artesanias of the ArtesanoEntity by the one given by parameter
	 *
	 * @param artesanias The new artesanias of the ArtesanoEntity
	 */
	public void setArtesanias( List<ArtesaniaEntity> artesanias )
	{
		this.artesanias = artesanias;
	}
	
	/**
	 * Retrieves the reviews of the ArtesanoEntity
	 *
	 * @return The reviews of the ArtesanoEntity
	 */
	public List<ReviewEntity> getReviews( )
	{
		return reviews;
	}
	
	/**
	 * Updates the reviews of the ArtesanoEntity by the ones given by parameter
	 *
	 * @param reviews The new reviews of the ArtesanoEntity
	 */
	public void setReviews( List<ReviewEntity> reviews )
	{
		this.reviews = reviews;
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
		if( obj instanceof ArtesanoEntity )
		{
			return id.equals( ( ( ArtesanoEntity ) obj ).id );
		}
		return false;
	}
}