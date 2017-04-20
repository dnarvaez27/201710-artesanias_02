package co.edu.uniandes.csw.artesanias.resources.asociaciones;

import co.edu.uniandes.csw.artesanias.dtos.asociacion.ArtesanoFeriaDTO;
import co.edu.uniandes.csw.artesanias.ejbs.asociaciones.ArtesanoFeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.asociaciones.ArtesanoFeriaAssociation;
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
public class ArtesanoFeriasResource
{
	@Inject
	private ArtesanoFeriaLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	//	COMMON
	
	@POST
	public ArtesanoFeriaDTO createArtesanoFerias( ArtesanoFeriaDTO dto ) throws BusinessLogicException
	{
		return new ArtesanoFeriaDTO( logic.createArtesanoFeria( dto.toEntity( ) ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ArtesanoFeriaDTO updateArtesanoFerias( @PathParam( "id" ) Long id, ArtesanoFeriaDTO dto )
	{
		ArtesanoFeriaAssociation entity = dto.toEntity( );
		entity.setId( id );
		return new ArtesanoFeriaDTO( logic.updateArtesanoFeria( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteArtesanoFerias( @PathParam( "id" ) Long id )
	{
		logic.deleteArtesanoFeria( id );
	}
	
	private List<ArtesanoFeriaDTO> listEntity2DTO( List<ArtesanoFeriaAssociation> entityList )
	{
		List<ArtesanoFeriaDTO> list = new LinkedList<>( );
		
		for( ArtesanoFeriaAssociation entity : entityList )
		{
			list.add( new ArtesanoFeriaDTO( entity ) );
		}
		return list;
	}
	
	@GET
	@Path( "{idFeria: \\d+}/{idStand: \\d+}" )
	public ArtesanoFeriaDTO getArtesanoFerias( @PathParam( "idFeria" ) Long idFeria, @PathParam( "idStand" ) Long idStand )
	{
		return new ArtesanoFeriaDTO( logic.getArtesanoFeria( idFeria, idStand ) );
	}
	
	//	FERIA
	
	@GET
	@Path( "/a" )
	public List<ArtesanoFeriaDTO> getArtesanosFromFeria( @PathParam( "idFeria" ) Long idFeria )
	{
		return listEntity2DTO( logic.getArtesanosFrom( idFeria ) );
	}
	
	//		ARTESANO
	
	@GET
	@Path( "/f" )
	public List<ArtesanoFeriaDTO> getFeriasFrom( @PathParam( "artesanoId" ) Long artesanoId )
	{
		return listEntity2DTO( logic.getFeriasFrom( artesanoId ) );
	}
}