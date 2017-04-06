package co.edu.uniandes.csw.artesanias.ejbs.asociaciones;

import co.edu.uniandes.csw.artesanias.entities.asociaciones.ArtesanoFeriaAssociation;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.asociaciones.ArtesanoFeriaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author d.naravaez11
 */
@Stateless
public class ArtesanoFeriaLogic
{
	@Inject
	private ArtesanoFeriaPersistence persistence;
	
	/**
	 * Creates an ArtesanoFeria in the Data Base
	 *
	 * @param entity Objet from ArtesanoFeriaAssociation with the new data.
	 * @return Objet from ArtesanoFeriaAssociation with the new data and ID.
	 */
	public ArtesanoFeriaAssociation createArtesanoFeria( ArtesanoFeriaAssociation entity ) throws BusinessLogicException
	{
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of ArtesanoFeria.
	 *
	 * @return Collection of objects of ArtesanoFeriaAssociation.
	 */
	public List<ArtesanoFeriaAssociation> getFeriasFrom( Long idArtesano )
	{
		return persistence.findFeriasFromArtesano( idArtesano );
	}
	
	public List<ArtesanoFeriaAssociation> getArtesanosFrom( Long idFeria )
	{
		return persistence.findArtesanosFromFeria( idFeria );
	}
	
	/**
	 * Retrieves the data of an instance of ArtesanoFeria by its ID.
	 *
	 * @param idFeria Identifier of the feria of the ArtesanoFeria.
	 * @param idStand Identifier of the stand of the ArtesanoFeria.
	 * @return Instance of ArtesanoFeriaAssociation with the data from the ArtesanoFeria consulted.
	 */
	public ArtesanoFeriaAssociation getArtesanoFeria( Long idFeria, Long idStand )
	{
		return persistence.find( idFeria, idStand );
	}
	
	/**
	 * Updates the information from an instance of ArtesanoFeria.
	 *
	 * @param entity Instance of ArtesanoFeriaAssociation with the new data.
	 * @return Instance of ArtesanoFeriaAssociation with the updated data.
	 */
	public ArtesanoFeriaAssociation updateArtesanoFeria( ArtesanoFeriaAssociation entity )
	{
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance of ArtesanoFeria from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteArtesanoFeria( Long id )
	{
		persistence.delete( id );
	}
}