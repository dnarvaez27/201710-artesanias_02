package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import co.edu.uniandes.csw.artesanias.persistence.ReviewPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
	public ReviewEntity createReview( ReviewEntity entity )
	{
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
	
	/**
	 * Retrieves the data of an instance of Review by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of ReviewEntity with the data from the Review consulted.
	 */
	public ReviewEntity getReview( Long id )
	{
		return persistence.find( id );
	}
	
	/**
	 * Updates the information from an instance of Review.
	 *
	 * @param entity Instance of ReviewEntity with the new data.
	 * @return Instance of ReviewEntity with the updated data.
	 */
	public ReviewEntity updateReview( ReviewEntity entity )
	{
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
}