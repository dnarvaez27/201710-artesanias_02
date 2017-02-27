package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class ArtesaniaPersistence
{
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Artesania Entity in the Data Base
	 *
	 * @param entity Artesania Entity to be created in the Data Base
	 * @return The Artesania Entity created
	 */
	public ArtesaniaEntity create( ArtesaniaEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Artesania Entity whose id matches the one given by parameter
	 *
	 * @param id Id of the Artesania entity searched
	 * @return The Artesania Entity whose id matches the one given by parameter
	 */
	public ArtesaniaEntity find( Long id )
	{
		return em.find( ArtesaniaEntity.class, id );
	}
	
	/**
	 * Retrieves all the Artesania Entities in the Data Base
	 *
	 * @return A List with all the Artesania Entities in the Data Base
	 */
	public List<ArtesaniaEntity> findAll( )
	{
		Query q = em.createQuery( "SELECT U FROM ArtesaniaEntity U" );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Artesania Entity whose id matches the one given by parameter
	 *
	 * @param entity Artesania Entity with the new information
	 * @return The Artesania Entity with the new information updated.
	 */
	public ArtesaniaEntity update( ArtesaniaEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Artesania Entity which id is given by parameter
	 *
	 * @param id Id of the Artesania to delete
	 */
	public void delete( Long id )
	{
		ArtesaniaEntity entity = em.find( ArtesaniaEntity.class, id );
		em.remove( entity );
	}
}