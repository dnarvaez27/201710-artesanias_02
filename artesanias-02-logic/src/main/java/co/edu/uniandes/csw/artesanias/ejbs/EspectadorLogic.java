package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.persistence.EspectadorPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class EspectadorLogic
{
	@Inject
	private EspectadorPersistence persistence;
	
	/**
	 * Creates an Espectador in the Data Base
	 *
	 * @param entity Objet from EspectadorEntity with the new data.
	 * @return Objet from EspectadorEntity with the new data and ID.
	 */
	public EspectadorEntity createEspectador( EspectadorEntity entity )
	{
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of Espectador.
	 *
	 * @return Collection of objects of EspectadorEntity.
	 */
	public List<EspectadorEntity> getEspectadors( )
	{
		return persistence.findAll( );
	}
	
	/**
	 * Retrieves the data of an instance of Espectador by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of EspectadorEntity with the data from the Espectador consulted.
	 */
	public EspectadorEntity getEspectador( Long id )
	{
		return persistence.find( id );
	}
	
	/**
	 * Updates the information from an instance of Espectador.
	 *
	 * @param entity Instance of EspectadorEntity with the new data.
	 * @return Instance of EspectadorEntity with the updated data.
	 */
	public EspectadorEntity updateEspectador( EspectadorEntity entity )
	{
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance of Espectador from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteEspectador( Long id )
	{
		persistence.delete( id );
	}
}