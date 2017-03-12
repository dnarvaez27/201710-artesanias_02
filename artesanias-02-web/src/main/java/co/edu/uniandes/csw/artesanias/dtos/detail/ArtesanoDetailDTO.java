package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.*;
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
	
	private CiudadDTO ciudad;
	
	private StandDTO stand;
	
	public ArtesanoDetailDTO( )
	{
		// Default Constructor. Mandatory
	}
	
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
			
			stand = new StandDTO( entity.getStand( ) );
			ciudad = new CiudadDTO( entity.getCiudad( ) );
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
		entity.setCiudad( this.ciudad.toEntity( ) );
		entity.setStand( this.stand.toEntity( ) );
		
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
	
	/**
	 * Retrieves the ciudad of the ArtesanoDetailDTO
	 *
	 * @return The ciudad of the ArtesanoDetailDTO
	 */
	public CiudadDTO getCiudad( )
	{
		return ciudad;
	}
	
	/**
	 * Updates the ciudad of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param ciudad The new ciudad of the ArtesanoDetailDTO
	 */
	public void setCiudad( CiudadDTO ciudad )
	{
		this.ciudad = ciudad;
	}
	
	/**
	 * Retrieves the stand of the ArtesanoDetailDTO
	 *
	 * @return The stand of the ArtesanoDetailDTO
	 */
	public StandDTO getStand( )
	{
		return stand;
	}
	
	/**
	 * Updates the stand of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param stand The new stand of the ArtesanoDetailDTO
	 */
	public void setStand( StandDTO stand )
	{
		this.stand = stand;
	}
}