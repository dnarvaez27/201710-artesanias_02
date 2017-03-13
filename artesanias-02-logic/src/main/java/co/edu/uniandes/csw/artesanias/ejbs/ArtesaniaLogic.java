package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.ArtesaniaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class ArtesaniaLogic
{
	@Inject
	private ArtesaniaPersistence persistence;
	
	/**
	 * Creates an Artesania in the Data Base
	 *
	 * @param entity Objet from ArtesaniaEntity with the new data.
	 * @return Objet from ArtesaniaEntity with the new data and ID.
	 */
	public ArtesaniaEntity createArtesania( ArtesaniaEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of Artesania.
	 *
	 * @return Collection of objects of ArtesaniaEntity.
	 */
	public List<ArtesaniaEntity> getArtesanias( )
	{
		return persistence.findAll( );
	}
	
	public List<ArtesaniaEntity> getArtesaniasFromArtesano( Long id )
	{
		return persistence.findAllFromArtesano( id );
	}
	
	/**
	 * Retrieves the data of an instance of Artesania by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of ArtesaniaEntity with the data from the Artesania consulted.
	 */
	public ArtesaniaEntity getArtesania( Long artesanoId, Long id ) throws BusinessLogicException
	{
		ArtesaniaEntity res = persistence.find( artesanoId, id );
		if( res != null )
		{
			return res;
		}
		throw new BusinessLogicException( String.format( "La artesania %s, no pertenece al artesano %s", id, artesanoId ), Response.Status.FORBIDDEN );
	}
	
	/**
	 * Updates the information from an instance of Artesania.
	 *
	 * @param entity Instance of ArtesaniaEntity with the new data.
	 * @return Instance of ArtesaniaEntity with the updated data.
	 */
	public ArtesaniaEntity updateArtesania( ArtesaniaEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance of Artesania from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteArtesania( Long id )
	{
		persistence.delete( id );
	}
	
	private void check( ArtesaniaEntity entity ) throws BusinessLogicException
	{
		if( entity.getNombre( ) == null || entity.getNombre( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "El nombre de la Artesania no puede ser vacio", Response.Status.BAD_REQUEST );
		}
	}
}