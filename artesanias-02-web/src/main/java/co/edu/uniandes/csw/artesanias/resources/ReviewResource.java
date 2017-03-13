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
 * @author d.narvaez11
 */
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ReviewResource
{
	@Inject
	private ReviewLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRec;
	
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
	
	@GET
	public List<ReviewDTO> getReviews( @PathParam( "artesanoId" ) Long id )
	{
		return listEntity2DTO( logic.getReviewsFromArtesano( id ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public ReviewDTO getReview(
			@PathParam( "artesanoId" ) Long artesanoId, @PathParam( "id" ) Long id ) throws BusinessLogicException
	{
		return new ReviewDTO( logic.getReview( artesanoId, id ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ReviewDTO updateReview(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id, ReviewDTO dto ) throws BusinessLogicException
	{
		ArtesanoEntity artes = new ArtesanoEntity( );
		artes.setId( artesanoId );
		ReviewEntity entity = dto.toEntity( );
		
		entity.setId( id );
		entity.setArtesano( artes );
		return new ReviewDTO( logic.updateReview( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteReview( @PathParam( "id" ) Long id )
	{
		logic.deleteReview( id );
	}
	
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