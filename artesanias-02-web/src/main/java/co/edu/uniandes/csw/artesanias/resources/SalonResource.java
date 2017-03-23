/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.SalonDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.SalonDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.SalonLogic;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author IVAN
 */
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )

public class SalonResource
{
	@Inject
	private SalonLogic logic;
	
	@Context
	private HttpServletResponse response;
	
	private List<SalonDTO> listEntity2DTO( List<SalonEntity> entityList )
	{
		List<SalonDTO> list = new LinkedList<>( );
		for( SalonEntity entity : entityList )
		{
			list.add( new SalonDTO( entity ) );
		}
		return list;
	}
	
	@GET
	public List<SalonDTO> getSalones( @PathParam( "pabellonId" ) Long pabellonId )
	{
		return listEntity2DTO( logic.getSalonesFromPabellon( pabellonId ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
        
	public SalonDTO getSalon( @PathParam( "id" ) Long id,  @PathParam( "pabellonId") Long pabellonId )
	{
		return new SalonDTO( logic.getSalon( id ) );
	}
	
	@POST
	public SalonDTO createSalon( @PathParam( "pabellonId" )
			                             Long pabellonId, SalonDetailDTO dto ) throws BusinessLogicException
	{
		PabellonEntity en = new PabellonEntity( );
		en.setId( pabellonId );
		dto.setPabellon( new PabellonDTO( en ) );
		SalonEntity entity = logic.createSalon( dto.toEntity( ) );
		return new SalonDTO( entity );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public SalonDTO updateSalon(
			@PathParam( "pabellonId" ) Long pabellonId,
			@PathParam( "id" ) Long id, SalonDTO dto ) throws BusinessLogicException
	{
		PabellonEntity pab = new PabellonEntity( );
		pab.setId( pabellonId );
		SalonEntity entity = dto.toEntity( );
		entity.setId( id );
		entity.setPabellon( pab );
		return new SalonDTO( logic.updateSalon( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteSalon( @PathParam( "id" ) Long id,@PathParam( "pabellonId" ) Long pabellonId )
	{
		logic.deleteSalon( id );
	}
        
        
	
//	@Path( "{salonId: \\d+}" )
//	public Class<ConferenciaResource> getConferenciaFromSalonResource( )
//	{
//		return ConferenciaResource.class;
//	}
        
//        @Path( "{salonId: \\d+}" )
//	public Class<ConferenciaResource> getConferenciaFromFeriaResource( )
//	{
//		return ConferenciaResource.class;
//	}
        
}
