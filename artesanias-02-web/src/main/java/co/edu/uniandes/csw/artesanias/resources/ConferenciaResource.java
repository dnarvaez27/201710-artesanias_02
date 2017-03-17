/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.ConferenciaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.ConferenciaLogic;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IVAN
 */
//TODO segun el diagrama de clases, las conferencias son dependientes dela feria. 
//TODO El PATH debe ser /ferias/:idFeria/conferencias y cada método debe recibir un @PathParam( "idFeria" ) Long idFeria
// TODO cada método debe validar que la feria con el idFeria exista o disparar WebApplicationException 404

@Path( "/conferencias" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ConferenciaResource
{
	@Inject
	private ConferenciaLogic conferenciaLogic;
	
	@Context
	private HttpServletResponse response;
	
	private List<ConferenciaDTO> listEntity2DTO( List<ConferenciaEntity> entityList )
	{
		List<ConferenciaDTO> list = new ArrayList<>( );
		for( ConferenciaEntity entity : entityList )
		{
			list.add( new ConferenciaDTO( entity ) );
		}
		return list;
	}
	
	@GET
	public List<ConferenciaDTO> getConferencias( )
	{
		return listEntity2DTO( conferenciaLogic.getConferencias( ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public ConferenciaDTO getConferencia( @PathParam( "id" ) Long id )
	{
            // TODO si la conferencia no existe debe disparar WebApplicationException 404
		return new ConferenciaDetailDTO( conferenciaLogic.getConferencia( id ) );
	}
	
	@POST
	public ConferenciaDTO createConferencia( ConferenciaDTO dto ) throws BusinessLogicException
	{
		return new ConferenciaDTO( conferenciaLogic.createConferencia( dto.toEntity( ) ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public ConferenciaDTO updateConferencia(
			@PathParam( "id" ) Long id, ConferenciaDTO dto ) throws BusinessLogicException
	{
            // TODO si la conferencia no existe debe disparar WebApplicationException 404
		
		ConferenciaEntity entity = dto.toEntity( );
		entity.setId( id );
		return new ConferenciaDTO( conferenciaLogic.updateConferencia( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteSConferencia( @PathParam( "id" ) Long id )
	{
            // TODO si la conferencia no existe debe disparar WebApplicationException 404
		
		conferenciaLogic.deleteConferencia( id );
	}
}