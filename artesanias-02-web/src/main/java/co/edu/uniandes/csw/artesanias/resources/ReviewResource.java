package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ReviewDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ReviewLogic;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

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
@Path( "/reviews" )
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
	
	private Long artesanoId;
	
	public ReviewResource( )
	{
		
	}
	
	public ReviewResource( Long artesanoId )
	{
		this.artesanoId = artesanoId;
	}
	
	@POST
	public ReviewDTO createReview( ReviewDTO dto )
	{
		return new ReviewDTO( logic.createReview( dto.toEntity( ) ) );
	}
	
	@GET
	public List<ReviewDTO> getReviews( )
	{
		return listEntity2DTO( logic.getReviewsFromArtesano( artesanoId ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public ReviewDTO getReview( @PathParam( "id" ) Long id )
	{
		return new ReviewDTO( logic.getReview( id ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ReviewDTO updateReview( @PathParam( "id" ) Long id, ReviewDTO dto )
	{
		ReviewEntity entity = dto.toEntity( );
		entity.setId( id );
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