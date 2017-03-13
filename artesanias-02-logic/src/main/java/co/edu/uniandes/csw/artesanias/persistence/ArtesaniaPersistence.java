package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
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
	public ArtesaniaEntity find( Long artesanoId, Long id )
	{
		TypedQuery<ArtesaniaEntity> q = em.createQuery( "SELECT A FROM ArtesaniaEntity A WHERE A.id = :id AND A.artesano.id = :artesanoID", ArtesaniaEntity.class );
		q.setParameter( "id", id );
		q.setParameter( "artesanoID", artesanoId );
		List<ArtesaniaEntity> res = q.getResultList( );
		return res.size( ) > 0 ? res.get( 0 ) : null;
	}
	
	/**
	 * Retrieves all the Artesania Entities in the Data Base
	 *
	 * @return A List with all the Artesania Entities in the Data Base
	 */
	public List<ArtesaniaEntity> findAll( )
	{
		TypedQuery<ArtesaniaEntity> q = em.createQuery( "SELECT U FROM ArtesaniaEntity U", ArtesaniaEntity.class );
		return q.getResultList( );
	}
	
	public List<ArtesaniaEntity> findAllFromArtesano( Long id )
	{
		TypedQuery<ArtesaniaEntity> q = em.createQuery( "SELECT A FROM ArtesaniaEntity A WHERE A.artesano.id = :artesanoId", ArtesaniaEntity.class );
		q.setParameter( "artesanoId", id );
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