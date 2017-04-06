package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Clase encargada de la persistencia de los Artesanos
 *
 * @author d.narvaez11
 */
@Stateless
public class ArtesanoPersistence
{
	/**
	 * Entity Manager, encargado de la persistnecia
	 */
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Artesano Entity in the Data Base
	 *
	 * @param entity Artesano Entity to be created in the Data Base
	 * @return The Artesano Entity created
	 */
	public ArtesanoEntity create( ArtesanoEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Artesano Entity whose id matches the one given by parameter
	 *
	 * @param id Id of the Artesano entity searched
	 * @return The Artesano Entity whose id matches the one given by parameter
	 */
	public ArtesanoEntity find( Long id )
	{
		TypedQuery<ArtesanoEntity> q = em.createQuery( "SELECT A FROM ArtesanoEntity A where A.id = :idArtesano", ArtesanoEntity.class );
		q.setParameter( "idArtesano", id );
		ArtesanoEntity en = q.getSingleResult( );
		if( en != null )
		{
			TypedQuery<ArtesaniaEntity> q2 = em.createQuery( "SELECT R FROM ArtesaniaEntity R WHERE R.artesano.id = :idArtesano", ArtesaniaEntity.class );
			q2.setParameter( "idArtesano", id );
			en.setArtesanias( q2.getResultList( ) );
			
			TypedQuery<ReviewEntity> q3 = em.createQuery( "SELECT R FROM ReviewEntity R WHERE R.artesano.id = :idArtesano", ReviewEntity.class );
			q3.setParameter( "idArtesano", id );
			en.setReviews( q3.getResultList( ) );
		}
		return en;
	}
	
	/**
	 * Retrieves all the Artesano Entities in the Data Base
	 *
	 * @return A List with all the Artesano Entities in the Data Base
	 */
	public List<ArtesanoEntity> findAll( )
	{
		TypedQuery<ArtesanoEntity> q = em.createQuery( "SELECT U FROM ArtesanoEntity U", ArtesanoEntity.class );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Artesano Entity whose id matches the one given by parameter
	 *
	 * @param entity Artesano Entity with the new information
	 * @return The Artesano Entity with the new information updated.
	 */
	public ArtesanoEntity update( ArtesanoEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Artesano Entity which id is given by parameter
	 *
	 * @param id Id of the Artesano to delete
	 */
	public void delete( Long id )
	{
		ArtesanoEntity entity = em.find( ArtesanoEntity.class, id );
		em.remove( entity );
	}
}