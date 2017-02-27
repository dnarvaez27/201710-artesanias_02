package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.ArtesaniaDTO;
import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.ReviewDTO;
import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
@XmlRootElement
public class ArtesanoDetailDTO extends ArtesanoDTO
{
	private List<ArtesaniaDTO> artesanias;
	
	private List<ReviewDTO> reviews;
	
	// TODO: 27/02/2017
	// Ciudad, Stands
	
	/**
	 * Builds an ArtesanoDetailDTO by the fields from the ArtesanoDetailEntity given
	 *
	 * @param entity ArtesanoDetailEntity to fill up the ArtesanoDetailDTO
	 */
	public ArtesanoDetailDTO( ArtesanoEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			this.artesanias = new LinkedList<>( );
			this.reviews = new LinkedList<>( );
			
			for( ArtesaniaEntity artesaniaEntity : entity.getArtesanias( ) )
			{
				artesanias.add( new ArtesaniaDTO( artesaniaEntity ) );
			}
			for( ReviewEntity reviewEntity : entity.getReviews( ) )
			{
				reviews.add( new ReviewDTO( reviewEntity ) );
			}
		}
	}
	
	/**
	 * Retrieves a ArtesanoDetailEntity with the fields of this ArtesanoDetailDTO
	 */
	public ArtesanoEntity toEntity( )
	{
		ArtesanoEntity entity = super.toEntity( );
		
		List<ArtesaniaEntity> artesaniasEntities = new LinkedList<>( );
		List<ReviewEntity> reviewsEntities = new LinkedList<>( );
		
		for( ArtesaniaDTO artesania : artesanias )
		{
			artesaniasEntities.add( artesania.toEntity( ) );
		}
		for( ReviewDTO review : reviews )
		{
			reviewsEntities.add( review.toEntity( ) );
		}
		
		entity.setArtesanias( artesaniasEntities );
		entity.setReviews( reviewsEntities );
		
		return entity;
	}
	
	/**
	 * Retrieves the artesanias of the ArtesanoDetailDTO
	 *
	 * @return The artesanias of the ArtesanoDetailDTO
	 */
	public List<ArtesaniaDTO> getArtesanias( )
	{
		return artesanias;
	}
	
	/**
	 * Updates the artesanias of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param artesanias The new artesanias of the ArtesanoDetailDTO
	 */
	public void setArtesanias( List<ArtesaniaDTO> artesanias )
	{
		this.artesanias = artesanias;
	}
	
	/**
	 * Retrieves the reviews of the ArtesanoDetailDTO
	 *
	 * @return The reviews of the ArtesanoDetailDTO
	 */
	public List<ReviewDTO> getReviews( )
	{
		return reviews;
	}
	
	/**
	 * Updates the reviews of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param reviews The new reviews of the ArtesanoDetailDTO
	 */
	public void setReviews( List<ReviewDTO> reviews )
	{
		this.reviews = reviews;
	}
}