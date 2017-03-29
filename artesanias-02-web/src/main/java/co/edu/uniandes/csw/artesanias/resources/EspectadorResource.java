package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.EspectadorDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspectadorLogic;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Recurso REST que modela los Espectadores
 *
 * @author d.narvaez11
 */
// TODO actualizar el diagrama de clases para que se llame Espectador y no Cliente
// TODO en los métodos que reciben el id del espectador se debe verificar que exista o sino disparar WebApplicationExcepton 404

@Path( "/espectadores" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EspectadorResource
{
	/**
	 * Servicio de Logica que permite el acceso a la base de datos
	 */
	@Inject
	private EspectadorLogic logic;
	
	/**
	 * Servicio de respuesta HTTP
	 */
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRec;
	
	/**
	 * Crea un Espectador en el Sistema
	 *
	 * @param dto DTO con la información del Espectador a ser creado
	 * @return DTO con la información del Espectador creado
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@POST
	public EspectadorDTO createEspectador( EspectadorDTO dto ) throws BusinessLogicException
	{
		return new EspectadorDTO( logic.createEspectador( dto.toEntity( ) ) );
	}
	
	/**
	 * Retorna los espectadores que hacen parte del sistema
	 *
	 * @return Lista de espectadores que hacen parte del sistema
	 */
	@GET
	public List<EspectadorDTO> getEspectadors( )
	{
		return listEntity2DTO( logic.getEspectadors( ) );
	}
	
	/**
	 * Retorna un espectador cuyo id es dado por parámetro
	 *
	 * @param id Id del espectador en interés
	 * @return DTO con la información del espectador en interés
	 * @throws BusinessLogicException Si el espectador con el id dado, no existe en el sistema (404 NOT FOUND)
	 */
	@GET
	@Path( "{id: \\d+}" )
	public EspectadorDTO getEspectador( @PathParam( "id" ) Long id ) throws BusinessLogicException
	{
		return new EspectadorDTO( logic.getEspectador( id ) );
	}
	
	/**
	 * Actualiza un espectador cuyo id es dado por parámetro
	 *
	 * @param id  Id del espectador a ser actualizado
	 * @param dto DTO con la nueva información del Espectador
	 * @return DTO con la nueva información del Espectador
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public EspectadorDTO updateEspectador(
			@PathParam( "id" ) Long id, EspectadorDTO dto ) throws BusinessLogicException
	{
		EspectadorEntity entity = dto.toEntity( );
		entity.setId( id );
		return new EspectadorDTO( logic.updateEspectador( entity ) );
	}
	
	/**
	 * Elimina un espectador cuyo id es dado por parámetro
	 *
	 * @param id Id del espectador a ser eliminado
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteEspectador( @PathParam( "id" ) Long id )
	{
		logic.deleteEspectador( id );
	}
	
	/**
	 * Método encargado de realizar la transformación de Entity a DTO
	 *
	 * @param entityList Lista a ser transformada
	 * @return Lista de elementos DTO
	 */
	private List<EspectadorDTO> listEntity2DTO( List<EspectadorEntity> entityList )
	{
		List<EspectadorDTO> list = new LinkedList<>( );
		
		for( EspectadorEntity entity : entityList )
		{
			list.add( new EspectadorDTO( entity ) );
		}
		return list;
	}
	
        
        //TODO actualizar el diagrama de clases para reflejar que la boleta es un subrecurso de espectador. 
        //TODO Verificar que el expectador exista antes de llamar el subrecurso.
	/**
	 * Retorna el Sub-Recurso de las Boletas que ha adquirido el espectador
	 *
	 * @return Clase del Sub-Recurso de Boletas que ha adquitido el espectador
	 */
	@Path( "{espectadorId: \\d+}/boletas" )
	public Class<BoletaResource> getBoletaResource( )
	{
		return BoletaResource.class;
	}
}