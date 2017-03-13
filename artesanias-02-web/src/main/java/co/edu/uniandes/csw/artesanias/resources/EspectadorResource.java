package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.EspectadorDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspectadorLogic;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;

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
@Path( "/espectadores" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EspectadorResource
{
	@Inject
	private EspectadorLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	@QueryParam( "page" )
	private Integer page;
	
	@QueryParam( "limit" )
	private Integer maxRec;
	
	@POST
	public EspectadorDTO createEspectador( EspectadorDTO dto )
	{
		return new EspectadorDTO( logic.createEspectador( dto.toEntity( ) ) );
	}
	
	@GET
	public List<EspectadorDTO> getEspectadors( )
	{
		return listEntity2DTO( logic.getEspectadors( ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public EspectadorDTO getEspectador( @PathParam( "id" ) Long id )
	{
		return new EspectadorDTO( logic.getEspectador( id ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public EspectadorDTO updateEspectador( @PathParam( "id" ) Long id, EspectadorDTO dto )
	{
		EspectadorEntity entity = dto.toEntity( );
		entity.setId( id );
		return new EspectadorDTO( logic.updateEspectador( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteEspectador( @PathParam( "id" ) Long id )
	{
		logic.deleteEspectador( id );
	}
	
	private List<EspectadorDTO> listEntity2DTO( List<EspectadorEntity> entityList )
	{
		List<EspectadorDTO> list = new LinkedList<>( );
		
		for( EspectadorEntity entity : entityList )
		{
			list.add( new EspectadorDTO( entity ) );
		}
		return list;
	}
	
	@Path( "{espectadorId: \\d+}/boletas" )
	public Class<BoletaResource> getBoletaResource( )
	{
		return BoletaResource.class;
	}
}