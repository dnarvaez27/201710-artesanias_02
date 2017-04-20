package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.ArtesanoDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ArtesanoLogic;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.resources.asociaciones.ArtesanoFeriasResource;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Recurso REST que modela los Artesanos del Sistema
 *
 * @author d.narvaez11
 */
@Path( "/artesanos" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ArtesanoResource
{
	/**
	 * Servicio de Logica que permite el acceso a la base de datos
	 */
	@Inject
	private ArtesanoLogic logic;
	
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
	 * Crea un Artesano en el sistema, cuya información es dada por parámetro a través de un DTO
	 *
	 * @param dto DTO con la información del Artesano
	 * @return DTO con la información del Artesano
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@POST
	public ArtesanoDetailDTO createArtesano( ArtesanoDetailDTO dto ) throws BusinessLogicException
	{
		return new ArtesanoDetailDTO( logic.createArtesano( dto.toEntity( ) ) );
	}
	
	/**
	 * Retorna los Artesanos que hacen parte del Sistema
	 *
	 * @return Lista de DTO's con la información de los Artesanos que hacen parte del sistema
	 */
	@GET
	public List<ArtesanoDTO> getArtesanos( )
	{
		return listEntity2DTO( logic.getArtesanos( ) );
	}
	
	/**
	 * Retorna un Artesano cuyo id es dado por parámetro
	 *
	 * @param id Id del Artesano en interés
	 * @return Artesano cuyo id coincide con el dado por parámetro
	 * @throws BusinessLogicException Si el artesano con el id dado, no existe en el sistema (404 NOT FOUND)
	 */
	@GET
	@Path( "{id: \\d+}" )
	public ArtesanoDetailDTO getArtesano( @PathParam( "id" ) Long id ) throws BusinessLogicException
	{
		return new ArtesanoDetailDTO( logic.getArtesano( id ) );
	}
	
	/**
	 * Actualiza un Artesano cuyo id es dado por parámetro
	 *
	 * @param id  Id del Artesano a ser modificado
	 * @param dto DTO con la nueva información del Artesano
	 * @return DTO con la nueva información del Artesano
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public ArtesanoDTO updateArtesano( @PathParam( "id" ) Long id, ArtesanoDTO dto ) throws BusinessLogicException
	{
		logic.getArtesano( id );// Verifica si el artesano existe
		
		ArtesanoEntity entity = dto.toEntity( );
		entity.setId( id );
		return new ArtesanoDTO( logic.updateArtesano( entity ) );
	}
	
	/**
	 * Elimina un artesano, del sistema, cuyo id es dado por parámetro
	 *
	 * @param id Id del Artesano a ser eliminado
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteArtesano( @PathParam( "id" ) Long id ) throws BusinessLogicException
	{
		logic.getArtesano( id );// Verifica si el artesano existe
		logic.deleteArtesano( id );
	}
	
	/**
	 * Método encargado de realizar la transformación de Entity a DTO
	 *
	 * @param entityList Lista a ser transformada
	 * @return Lista de elementos DTO
	 */
	private List<ArtesanoDTO> listEntity2DTO( List<ArtesanoEntity> entityList )
	{
		List<ArtesanoDTO> list = new LinkedList<>( );
		
		for( ArtesanoEntity entity : entityList )
		{
			list.add( new ArtesanoDTO( entity ) );
		}
		return list;
	}
	
	//	SUB-RECURSOS
	
	/**
	 * Retorna el Sub-Recurso de Reviews del Artesano
	 *
	 * @return Clase del Sub-Recurso de Reviews del Artesano
	 */
	@Path( "{artesanoId: \\d+}/reviews" )
	public Class<ReviewResource> getReviewResource( @PathParam( "artesanoId" ) Long artesanoId ) throws BusinessLogicException
	{
		logic.getArtesano( artesanoId );// Verifica si el artesano existe
		return ReviewResource.class;
	}
	
	/**
	 * Retorna el Sub-Recurso de las Artesanias del Artesano
	 *
	 * @return Clase del Sub-Recurso de las Artesanias del Artesano
	 */
	@Path( "{artesanoId: \\d+}/artesanias" )
	public Class<ArtesaniasResource> getArtesaniasResource( @PathParam( "artesanoId" ) Long artesanoId ) throws BusinessLogicException
	{
		logic.getArtesano( artesanoId );// Verifica si el artesano existe
		return ArtesaniasResource.class;
	}
	
	@Path( "{artesanoId: \\d+}/ferias" )
	public Class<ArtesanoFeriasResource> getFeriasResourceAso( @PathParam( "artesanoId" ) Long artesanoId ) throws BusinessLogicException
	{
		logic.getArtesano( artesanoId );// Verifica si el artesano existe
		return ArtesanoFeriasResource.class;
	}
}