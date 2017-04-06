package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Clase encargada de la persistnecia de los Reviews
 *
 * @author d.narvaez11
 */
@Stateless
public class ReviewPersistence
{
	/**
	 * Entity Manager, encargado de la persistencia
	 */
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Review Entity in the Data Base
	 *
	 * @param entity Review Entity to be created in the Data Base
	 * @return The Review Entity created
	 */
	public ReviewEntity create( ReviewEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Review Entity whose id matches the one given by parameter
	 *
	 * @param id Id of the Review entity searched
	 * @return The Review Entity whose id matches the one given by parameter
	 */
	public ReviewEntity find( Long artesanoId, Long id )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "SELECT R FROM ReviewEntity R WHERE R.id = :id AND R.artesano.id = :artesanoId", ReviewEntity.class );
		q.setParameter( "id", id );
		q.setParameter( "artesanoId", artesanoId );
		List<ReviewEntity> res = q.getResultList( );
		return res.size( ) > 0 ? res.get( 0 ) : null;
	}
	
	/**
	 * Retrieves all the Review Entities in the Data Base
	 *
	 * @return A List with all the Review Entities in the Data Base
	 */
	public List<ReviewEntity> findAll( )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "SELECT U FROM ReviewEntity U", ReviewEntity.class );
		return q.getResultList( );
	}
	
	/**
	 * Retrieves all the Review from the Artesano whose id matches the one given by parameter
	 *
	 * @param artesanoId Id of the Artesano, whose reviews are returned
	 * @return List of Reviews from the Artesano whose id matches the one given by parameter
	 */
	public List<ReviewEntity> findAllFromArtesano( Long artesanoId )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "SELECT R FROM ReviewEntity R WHERE R.artesano.id = :artesanoId", ReviewEntity.class );
		q.setParameter( "artesanoId", artesanoId );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Review Entity whose id matches the one given by parameter
	 *
	 * @param entity Review Entity with the new information
	 * @return The Review Entity with the new information updated.
	 */
	public ReviewEntity update( ReviewEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Review Entity which id is given by parameter
	 *
	 * @param id Id of the Review to delete
	 */
	public void delete( Long id )
	{
		ReviewEntity entity = em.find( ReviewEntity.class, id );
		em.remove( entity );
	}
}