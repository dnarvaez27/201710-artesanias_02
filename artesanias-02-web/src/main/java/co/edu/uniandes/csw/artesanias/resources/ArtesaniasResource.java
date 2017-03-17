package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ArtesaniaDTO;
import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.ArtesaniaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ArtesaniaLogic;
import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Sub-Recurso REST que modela las Artesanias de un Artesano.<br>
 * El recurso principal es el Artesano dada su relación de composición
 *
 * @author d.narvaez11
 */
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ArtesaniasResource
{
	/**
	 * Servicio de Logica que permite el acceso a la base de datos
	 */
	@Inject
	private ArtesaniaLogic logic;
	
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
	 * Crea una artesania en el sistema y es asignada al Artesano cuyo id es dado por parámetro
	 *
	 * @param artesanoId Id del Artesano al cual pertenece la artesania
	 * @param dto        Artesania DTO Detallada ( {@link ArtesaniaDetailDTO} ) que contiene la información necesaria para su creación
	 * @return Un DTO Detallado que contiene la informacion de la Artesania
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@POST
	public ArtesaniaDetailDTO createArtesania( @PathParam( "artesanoId" )
			                                           Long artesanoId, ArtesaniaDetailDTO dto ) throws BusinessLogicException
	{
		ArtesanoEntity en = new ArtesanoEntity( );
		en.setId( artesanoId );
		dto.setArtesano( new ArtesanoDTO( en ) );
		ArtesaniaEntity entity = logic.createArtesania( dto.toEntity( ) );
		return new ArtesaniaDetailDTO( entity );
	}
	
	/**
	 * Retorna las artesanias cuyo autor es el Artesano con id dado por parámetro
	 *
	 * @param id Id del autor cuydas artesanias son de interés
	 * @return Lista de DTOs con la información de las Artesanias del Artesano cuyo id coincide con el dado por parámetro
	 */
	@GET
	public List<ArtesaniaDTO> getArtesanias( @PathParam( "artesanoId" ) Long id )
	{
		return listEntity2DTO( logic.getArtesaniasFromArtesano( id ) );
	}
	
	/**
	 * Retorna una artesania cuyo autor es el Artesano con ids dados por parámetro
	 *
	 * @param artesanoId Id del Artesano de quien pertenece la Artesania
	 * @param id         Id de la Artesania en interés
	 * @return DTO con la información de la Artesanía cuyo id concide con el que es dado por parámetro y la cual fué creada por un Artesano con id dado por parámetro
	 * @throws BusinessLogicException Si el Artesano no tiene una Artesania con el id dado. (404 NOT FOUND)
	 */
	@GET
	@Path( "{id: \\d+}" )
	public ArtesaniaDTO get(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            // TODO si la artesania  no existe debe disparar WebApplicationException 404
	
		return new ArtesaniaDTO( logic.getArtesania( artesanoId, id ) );
	}
	
	/**
	 * Actualiza una Artesania con la información suministrada y cuyo id es dado por parámetro
	 *
	 * @param artesanoId Id del artesano creador de la Artesania a modificar
	 * @param id         Id de la Artesanía a modificar
	 * @param dto        DTO con la nueva información de la Artesanía
	 * @return DTO con la nueva información de la Artesanía
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public ArtesaniaDTO updateArtesania(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id, ArtesaniaDTO dto ) throws BusinessLogicException
	{
             // TODO si la artesania  no existe debe disparar WebApplicationException 404
	
		ArtesanoEntity arte = new ArtesanoEntity( );
		arte.setId( artesanoId );
		ArtesaniaEntity entity = dto.toEntity( );
		entity.setId( id );
		entity.setArtesano( arte );
		return new ArtesaniaDTO( logic.updateArtesania( entity ) );
	}
	
	/**
	 * Eliminar una Artesania cuyo id es dado por parámetro
	 *
	 * @param artesaniaId Id del Artesano quien creó la Artesania
	 * @param id          Id de la Artesania a eliminar
	 * @throws BusinessLogicException Si la artesanía no pertenece al artesano con id dado. (403 FORBIDEN)
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteArtesania(
			@PathParam( "artesanoId" ) Long artesaniaId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
             // TODO si la artesania  no existe debe disparar WebApplicationException 404
	
		logic.deleteArtesania( artesaniaId, id );
	}
	
	/**
	 * Método encargado de realizar la transformación de Entity a DTO
	 *
	 * @param entityList Lista a ser transformada
	 * @return Lista de elementos DTO
	 */
	private List<ArtesaniaDTO> listEntity2DTO( List<ArtesaniaEntity> entityList )
	{
		List<ArtesaniaDTO> list = new LinkedList<>( );
		for( ArtesaniaEntity entity : entityList )
		{
			list.add( new ArtesaniaDTO( entity ) );
		}
		return list;
	}
}