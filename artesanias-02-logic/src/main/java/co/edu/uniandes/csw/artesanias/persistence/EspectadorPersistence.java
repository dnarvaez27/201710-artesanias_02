package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class EspectadorPersistence
{
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Espectador Entity in the Data Base
	 *
	 * @param entity Espectador Entity to be created in the Data Base
	 * @return The Espectador Entity created
	 */
	public EspectadorEntity create( EspectadorEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Espectador Entity whose id matches the one given by parameter
	 *
	 * @param id Id of the Espectador entity searched
	 * @return The Espectador Entity whose id matches the one given by parameter
	 */
	public EspectadorEntity find( Long id )
	{
		return em.find( EspectadorEntity.class, id );
	}
	
	/**
	 * Retrieves all the Espectador Entities in the Data Base
	 *
	 * @return A List with all the Espectador Entities in the Data Base
	 */
	public List<EspectadorEntity> findAll( )
	{
		TypedQuery<EspectadorEntity> q = em.createQuery( "SELECT U FROM EspectadorEntity U", EspectadorEntity.class );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Espectador Entity whose id matches the one given by parameter
	 *
	 * @param entity Espectador Entity with the new information
	 * @return The Espectador Entity with the new information updated.
	 */
	public EspectadorEntity update( EspectadorEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Espectador Entity which id is given by parameter
	 *
	 * @param id Id of the Espectador to delete
	 */
	public void delete( Long id )
	{
		EspectadorEntity entity = em.find( EspectadorEntity.class, id );
		em.remove( entity );
	}
}
