package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.ReviewDTO;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author d.narvaez11
 */
@XmlRootElement
public class ReviewDetailDTO extends ReviewDTO
{
	private ArtesanoDTO artesano;
	
	public ReviewDetailDTO( )
	{
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ReviewDTO by the fields from the ReviewEntity given
	 *
	 * @param entity ReviewEntity to fill up the ReviewDTO
	 */
	public ReviewDetailDTO( ReviewEntity entity )
	{
		if( entity != null )
		{
			this.artesano = new ArtesanoDTO( entity.getArtesano( ) );
		}
	}
	
	/**
	 * Retrieves a ReviewEntity with the fields of this ReviewDTO
	 */
	public ReviewEntity toEntity( )
	{
		ReviewEntity entity = super.toEntity( );
		entity.setArtesano( this.artesano.toEntity( ) );
		return entity;
	}
	
	/**
	 * Retrieves the artesano of the ReviewDetailDTO
	 *
	 * @return The artesano of the ReviewDetailDTO
	 */
	public ArtesanoDTO getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ReviewDetailDTO by the one given by parameter
	 *
	 * @param artesano The new artesano of the ReviewDetailDTO
	 */
	public void setArtesano( ArtesanoDTO artesano )
	{
		this.artesano = artesano;
	}
}