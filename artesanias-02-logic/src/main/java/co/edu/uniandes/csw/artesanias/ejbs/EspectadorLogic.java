package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.EspectadorPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class EspectadorLogic
{
	
	//--------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------
	
	@Inject
	private EspectadorPersistence persistence;
	
	@Inject
	private BoletaLogic boletaLogic;
	
	//--------------------------------------------------------------------------
	// Métodos
	//--------------------------------------------------------------------------
	
	/**
	 * Creates an Espectador in the Data Base
	 *
	 * @param entity Objet from EspectadorEntity with the new data.
	 * @return Objet from EspectadorEntity with the new data and ID.
	 * @throws co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException
	 */
	public EspectadorEntity createEspectador( EspectadorEntity entity ) throws BusinessLogicException
	{
		check( entity );
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
	 * @return Instance of EspectadorEntity with the data from the Espectador
	 * consulted.
	 */
	public EspectadorEntity getEspectador( Long id )
	{
		EspectadorEntity entity = persistence.find( id );
		return entity;
	}
	
	/**
	 * Updates the information from an instance of Espectador.
	 *
	 * @param entity Instance of EspectadorEntity with the new data.
	 * @return Instance of EspectadorEntity with the updated data.
	 * @throws co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException
	 */
	public EspectadorEntity updateEspectador( EspectadorEntity entity ) throws BusinessLogicException
	{
		check( entity );
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
	
	//--------------------------------------------------------------------------
	// Métodos de boleta
	//--------------------------------------------------------------------------
	
	public BoletaEntity getBoleta( Long idEspectador, Long idBoleta )
	{
		for( BoletaEntity b : persistence.find( idEspectador ).getBoletas( ) )
		{
			if( b.getId( ).equals( idBoleta ) )
			{
				return b;
			}
		}
		throw new IllegalArgumentException( "La boleta no está asociado a la espectador" );
	}
	
	public List<BoletaEntity> getBoletas( Long id )
	{
		return persistence.find( id ).getBoletas( );
	}
	
	public void removeBoleta( Long idEspectador, Long idBoleta )
	{
		BoletaEntity be = boletaLogic.getBoleta( idBoleta );
		be.setEspectador( null );
		persistence.find( idEspectador ).getBoletas( ).remove( be );
	}
	
	public BoletaEntity addBoleta( Long idEspectador, Long idBoleta )
	{
		BoletaEntity be = boletaLogic.getBoleta( idBoleta );
		be.setEspectador( persistence.find( idEspectador ) );
		return be;
	}
	
	//--------------------------------------------------------------------------
	// Métodos Auxiliares
	//--------------------------------------------------------------------------
	
	private void check( EspectadorEntity entity ) throws BusinessLogicException
	{
		if( entity.getCorreo( ) == null || entity.getCorreo( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "El correo no puede ser vacío", Response.Status.BAD_REQUEST );
		}
		if( entity.getContrasena( ) == null || entity.getContrasena( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "La contraseña no puede ser vacía", Response.Status.BAD_REQUEST );
		}
	}
}
