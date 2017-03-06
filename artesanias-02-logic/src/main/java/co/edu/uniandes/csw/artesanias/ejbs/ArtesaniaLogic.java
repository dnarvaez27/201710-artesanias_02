package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.persistence.ArtesaniaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
	public ArtesaniaEntity createArtesania( ArtesaniaEntity entity )
	{
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
	
	/**
	 * Retrieves the data of an instance of Artesania by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of ArtesaniaEntity with the data from the Artesania consulted.
	 */
	public ArtesaniaEntity getArtesania( Long id )
	{
		return persistence.find( id );
	}
	
	/**
	 * Updates the information from an instance of Artesania.
	 *
	 * @param entity Instance of ArtesaniaEntity with the new data.
	 * @return Instance of ArtesaniaEntity with the updated data.
	 */
	public ArtesaniaEntity updateArtesania( ArtesaniaEntity entity )
	{
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
}