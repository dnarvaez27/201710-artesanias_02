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
 * @author d.narvaez11
 */
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ArtesaniasResource
{
	@Inject
	private ArtesaniaLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRec;
	
	@POST
	public ArtesaniaDetailDTO createArtesania( @PathParam( "artesanoId" )
			                                           Long id, ArtesaniaDetailDTO dto ) throws BusinessLogicException
	{
		ArtesanoEntity en = new ArtesanoEntity( );
		en.setId( id );
		dto.setArtesano( new ArtesanoDTO( en ) );
		ArtesaniaEntity entity = logic.createArtesania( dto.toEntity( ) );
		return new ArtesaniaDetailDTO( entity );
	}
	
	@GET
	public List<ArtesaniaDTO> getArtesanias( @PathParam( "artesanoId" ) Long id )
	{
		return listEntity2DTO( logic.getArtesaniasFromArtesano( id ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public ArtesaniaDTO get(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
		return new ArtesaniaDTO( logic.getArtesania( artesanoId, id ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ArtesaniaDTO updateArtesania(
			@PathParam( "artesanoId" ) Long artesanoId,
			@PathParam( "id" ) Long id, ArtesaniaDTO dto ) throws BusinessLogicException
	{
		ArtesanoEntity arte = new ArtesanoEntity( );
		arte.setId( artesanoId );
		ArtesaniaEntity entity = dto.toEntity( );
		entity.setId( id );
		entity.setArtesano( arte );
		return new ArtesaniaDTO( logic.updateArtesania( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteArtesania( @PathParam( "id" ) Long id )
	{
		logic.deleteArtesania( id );
	}
	
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