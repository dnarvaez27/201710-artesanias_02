package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.ArtesanoPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class ArtesanoLogic
{
	@Inject
	private ArtesanoPersistence persistence;
	
	/**
	 * Creates an Artesano in the Data Base
	 *
	 * @param entity Objet from ArtesanoEntity with the new data.
	 * @return Objet from ArtesanoEntity with the new data and ID.
	 */
	public ArtesanoEntity createArtesano( ArtesanoEntity entity ) throws BusinessLogicException
	{
		checkNNValues( entity );
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of Artesano.
	 *
	 * @return Collection of objects of ArtesanoEntity.
	 */
	public List<ArtesanoEntity> getArtesanos( )
	{
		return persistence.findAll( );
	}
	
	/**
	 * Retrieves the data of an instance of Artesano by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of ArtesanoEntity with the data from the Artesano consulted.
	 * @throws BusinessLogicException en caso que no exista un artesano con el ID dado (404 NOT FOUND)
	 */
	public ArtesanoEntity getArtesano( Long id ) throws BusinessLogicException
	{
		ArtesanoEntity en = persistence.find( id );
		if( en != null )
		{
			return en;
		}
		throw new BusinessLogicException( String.format( "No existe el artesano con el id %s", id ), Response.Status.NOT_FOUND );
	}
	
	/**
	 * Updates the information from an instance of Artesano.
	 *
	 * @param entity Instance of ArtesanoEntity with the new data.
	 * @return Instance of ArtesanoEntity with the updated data.
	 */
	public ArtesanoEntity updateArtesano( ArtesanoEntity entity ) throws BusinessLogicException
	{
		ArtesanoEntity info = getArtesano( entity.getId( ) );
		boolean nombre = entity.getNombre( ) == null || entity.getNombre( ).isEmpty( );
		boolean telefono = entity.getTelefono( ) == null || entity.getTelefono( ).isEmpty( );
		boolean ciudad = entity.getCiudad( ) == null;
		boolean ident = entity.getIdentificacion( ) == null || entity.getIdentificacion( )
		                                                             .isEmpty( );
		
		entity.setNombre( nombre ? info.getNombre( ) : entity.getNombre( ) );
		entity.setIdentificacion( ident ? info.getIdentificacion( ) : entity.getIdentificacion( ) );
		entity.setTelefono( telefono ? info.getTelefono( ) : entity.getTelefono( ) );
		entity.setCiudad( ciudad ? info.agetCiudad( ) : entity.getCiudad( ) );
		checkNNValues( entity );
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance o f Artesano from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteArtesano( Long id )
	{
		persistence.delete( id );
	}
	
	private void checkNNValues( ArtesanoEntity entity ) throws BusinessLogicException
	{
		boolean nombre = entity.getNombre( ) == null || entity.getNombre( ).isEmpty( );
		boolean ident = entity.getIdentificacion( ) == null || entity.getIdentificacion( )
		                                                             .isEmpty( );
		if( nombre || ident )
		{
			throw new BusinessLogicException( String.format( "%s del Artesano no puede estar vacío", nombre ? "El nombre" : "La identificacion" ), Response.Status.BAD_REQUEST );
		}
	}
}