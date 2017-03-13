package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.ReviewPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class ReviewLogic
{
	@Inject
	private ReviewPersistence persistence;
	
	/**
	 * Creates an Review in the Data Base
	 *
	 * @param entity Objet from ReviewEntity with the new data.
	 * @return Objet from ReviewEntity with the new data and ID.
	 */
	public ReviewEntity createReview( ReviewEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of Review.
	 *
	 * @return Collection of objects of ReviewEntity.
	 */
	public List<ReviewEntity> getReviews( )
	{
		return persistence.findAll( );
	}
	
	public List<ReviewEntity> getReviewsFromArtesano( Long id )
	{
		return persistence.findAllFromArtesano( id );
	}
	
	/**
	 * Retrieves the data of an instance of Review by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of ReviewEntity with the data from the Review consulted.
	 */
	public ReviewEntity getReview( Long artesanoId, Long id ) throws BusinessLogicException
	{
		ReviewEntity res = persistence.find( artesanoId, id );
		if( res != null )
		{
			return res;
		}
		throw new BusinessLogicException( String.format( "El review %s no pertenece al artesano %s ", id, artesanoId ), Response.Status.FORBIDDEN );
	}
	
	/**
	 * Updates the information from an instance of Review.
	 *
	 * @param entity Instance of ReviewEntity with the new data.
	 * @return Instance of ReviewEntity with the updated data.
	 */
	public ReviewEntity updateReview( ReviewEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance of Review from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteReview( Long id )
	{
		persistence.delete( id );
	}
	
	private void check( ReviewEntity entity ) throws BusinessLogicException
	{
		if( entity.getPuntuacion( ) < 0 || entity.getPuntuacion( ) > 5 )
		{
			throw new BusinessLogicException( "La puntuación debe estar en un rango de 0 a 5", Response.Status.BAD_REQUEST );
		}
	}
}