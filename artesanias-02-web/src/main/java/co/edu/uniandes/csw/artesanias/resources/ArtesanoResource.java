package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ArtesanoLogic;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Path( "/artesanos" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ArtesanoResource
{
	@Inject
	private ArtesanoLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRec;
	
	@POST
	public ArtesanoDTO createArtesano( ArtesanoDTO dto )
	{
		return new ArtesanoDTO( logic.createArtesano( dto.toEntity( ) ) );
	}
	
	@GET
	public List<ArtesanoDTO> getArtesanos( )
	{
		return listEntity2DTO( logic.getArtesanos( ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public ArtesanoDTO getArtesano( @PathParam( "id" ) Long id )
	{
		return new ArtesanoDTO( logic.getArtesano( id ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ArtesanoDTO updateArtesano( @PathParam( "id" ) Long id, ArtesanoDTO dto )
	{
		ArtesanoEntity entity = dto.toEntity( );
		entity.setId( id );
		return new ArtesanoDTO( logic.updateArtesano( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteArtesano( @PathParam( "id" ) Long id )
	{
		logic.deleteArtesano( id );
	}
	
	private List<ArtesanoDTO> listEntity2DTO( List<ArtesanoEntity> entityList )
	{
		List<ArtesanoDTO> list = new LinkedList<>( );
		
		for( ArtesanoEntity entity : entityList )
		{
			list.add( new ArtesanoDTO( entity ) );
		}
		return list;
	}
	
	@Path( "{artesanoId}: \\d+}/reviews" )
	public ReviewResource getReviewResource( @PathParam( "artesanoId" ) Long artesanoId )
	{
		return new ReviewResource( artesanoId );
	}
	
	@Path( "{artesanoId: \\d+}/artesanias" )
	public ArtesaniasResource getArtesaniasResource( @PathParam( "artesanoId" ) Long artesanoId )
	{
		return new ArtesaniasResource( artesanoId );
	}
}