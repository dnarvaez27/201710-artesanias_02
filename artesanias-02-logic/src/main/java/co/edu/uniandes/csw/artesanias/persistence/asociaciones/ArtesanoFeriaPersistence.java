package co.edu.uniandes.csw.artesanias.persistence.asociaciones;

import co.edu.uniandes.csw.artesanias.entities.asociaciones.ArtesanoFeriaAssociation;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author d.narvaez11
 */
public class ArtesanoFeriaPersistence
{
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new ArtesanoFeria Entity in the Data Base
	 *
	 * @param entity ArtesanoFeria Entity to be created in the Data Base
	 * @return The ArtesanoFeria Entity created
	 */
	public ArtesanoFeriaAssociation create( ArtesanoFeriaAssociation entity ) throws BusinessLogicException
	{
		if( entity.getId( ) == null )
		{
			throw new BusinessLogicException( "El id no puede ser null?", Response.Status.FORBIDDEN );
		}
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the ArtesanoFeria Entity whose id matches the one given by parameter
	 *
	 * @param idArtesano Id of the ArtesanoFeria entity searched
	 * @return The ArtesanoFeria Entity whose id matches the one given by parameter
	 */
	public List<ArtesanoFeriaAssociation> findFeriasFromArtesano( Long idArtesano )
	{
		TypedQuery<ArtesanoFeriaAssociation> q = em.createQuery( "SELECT F FROM ArtesanoFeriaAssociation F WHERE F.artesano.id = :idArtesano", ArtesanoFeriaAssociation.class );
		q.setParameter( "idArtesano", idArtesano );
		return q.getResultList( );
	}
	
	public List<ArtesanoFeriaAssociation> findArtesanosFromFeria( Long idFeria )
	{
		TypedQuery<ArtesanoFeriaAssociation> q = em.createQuery( "SELECT F FROM ArtesanoFeriaAssociation F WHERE F.feria.id = :idFeria", ArtesanoFeriaAssociation.class );
		q.setParameter( "idFeria", idFeria );
		return q.getResultList( );
	}
	
	public ArtesanoFeriaAssociation find( Long idFeria, Long idStand )
	{
		TypedQuery<ArtesanoFeriaAssociation> q = em.createQuery( "SELECT A FROM ArtesanoFeriaAssociation A WHERE A.feria.id = :idFeria AND A.stand.id = :idStand", ArtesanoFeriaAssociation.class );
		q.setParameter( "idFeria", idFeria );
		q.setParameter( "idStand", idStand );
		return q.getSingleResult( );
	}
	
	/**
	 * Updates the information from the ArtesanoFeria Entity whose id matches the one given by parameter
	 *
	 * @param entity ArtesanoFeria Entity with the new information
	 * @return The ArtesanoFeria Entity with the new information updated.
	 */
	public ArtesanoFeriaAssociation update( ArtesanoFeriaAssociation entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the ArtesanoFeria Entity which id is given by parameter
	 *
	 * @param id Id of the ArtesanoFeria to delete
	 */
	public void delete( Long id )
	{
		ArtesanoFeriaAssociation entity = em.find( ArtesanoFeriaAssociation.class, id );
		em.remove( entity );
	}
}