package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.ReviewDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.ReviewDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ReviewLogic;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Sub-Recurso REST que modela los Reviews de un Artesano.<br>
 * El recurso principal es el Artesano dada su relación de composición
 *
 * @author d.narvaez11
 */
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ReviewResource
{
	/**
	 * Servicio de Logica que permite el acceso a la base de datos
	 */
	@Inject
	private ReviewLogic logic;
	
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
	 * Crea un Review en el sistema y es asignado al artesano cuyo id coincide con el que es dado por parámetro
	 *
	 * @param id  Id del artesano al cual será asignado el Review
	 * @param dto DTO con la información del Review
	 * @return DTO con la información del Review
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@POST
	public ReviewDTO createReview(
			@PathParam( "artesanoId" ) Long id, ReviewDetailDTO dto ) throws BusinessLogicException
	{
		ArtesanoEntity en = new ArtesanoEntity( );
		en.setId( id );
		dto.setArtesano( new ArtesanoDTO( en ) );
		ReviewEntity entity = logic.createReview( dto.toEntity( ) );
		return new ReviewDTO( entity );
	}
	
	/**
	 * Retorna los Reviews del artesano cuyo id coincide con el que es dado por parámetro
	 *
	 * @param id Id del Artesano cuyos reviews son de interés
	 * @return Lista de Reviews del Artesano cuyo id coincide con el que es dado por parámetro
	 */
	@GET
	public List<ReviewDTO> getReviews( @PathParam( "artesanoId" ) Long id )
	{
		return listEntity2DTO( logic.getReviewsFromArtesano( id ) );
	}
	
	/**
	 * Retorna el Review de un Artesano cuyos ids coinciden con los que son dados por parámetro
	 *
	 * @param artesanoId Id del Artesano cuyo Review es de interés
	 * @param id         Id del Review en interés
	 * @return DTO con la información del Review del Artesano cuyos ids son dados por parámetro
	 * @throws BusinessLogicException Si el Artesano no tiene un Review con el id dado. (404 NOT FOUND)
	 */
	@GET
	@Path( "{id: \\d+}" )
	public ReviewDTO getReview(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            // TODO si el review  no existe debe disparar WebApplicationException 404
	
		return new ReviewDTO( logic.getReview( artesanoId, id ) );
	}
	
	/**
	 * Actualiza un Review de un Artesano cuyos ids son dados por parámetro
	 *
	 * @param artesanoId Id del Artesano cuyo Review será actualizado
	 * @param id         Id del Review que será modificado
	 * @param dto        DTO con la nueva información del Review
	 * @return DTO con la nueva información del Review
	 * @throws BusinessLogicException Si no cumple con los requisitos mínimos para su creación. (400 BAD REQUEST)
	 */
	@PUT
	@Path( "{id: \\d+}" )
	public ReviewDTO updateReview(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id, ReviewDTO dto ) throws BusinessLogicException
	{
             // TODO si el review  no existe debe disparar WebApplicationException 404
	
		ArtesanoEntity artes = new ArtesanoEntity( );
		artes.setId( artesanoId );
		ReviewEntity entity = dto.toEntity( );
		
		entity.setId( id );
		entity.setArtesano( artes );
		return new ReviewDTO( logic.updateReview( entity ) );
	}
	
	/**
	 * Elimina un Review cuyo Id es dado por parámetro
	 *
	 * @param artesanoId Id del Artesano cuyo Review será eliminado
	 * @param id         Id del Review a ser eliminado
	 * @throws BusinessLogicException Si el Review no pertenece al artesano con id dado. (403 FORBIDEN)
	 */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteReview(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
             // TODO si el review  no existe debe disparar WebApplicationException 404
	
		logic.deleteReview( artesanoId, id );
	}
	
	/**
	 * Método encargado de realizar la transformación de Entity a DTO
	 *
	 * @param entityList Lista a ser transformada
	 * @return Lista de elementos DTO
	 */
	private List<ReviewDTO> listEntity2DTO( List<ReviewEntity> entityList )
	{
		List<ReviewDTO> list = new ArrayList<>( );
		for( ReviewEntity entity : entityList )
		{
			list.add( new ReviewDTO( entity ) );
		}
		return list;
	}
}