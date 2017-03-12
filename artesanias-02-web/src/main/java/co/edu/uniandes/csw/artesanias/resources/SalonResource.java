/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.dtos.SalonDTO;
import co.edu.uniandes.csw.artesanias.ejbs.SalonLogic;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;

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
@Path( "/salones" )
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
	public List<SalonDTO> getSalones( )
	{
		return listEntity2DTO( logic.getSalones( ) );
	}
	
	@GET
	@Path( "{id: \\d+}" )
	public SalonDTO getSalon( @PathParam( "id" ) Long id )
	{
		return new SalonDTO( logic.getSalon( id ) );
	}
	
	@POST
	public SalonDTO createSalon( SalonDTO dto )
	{
		return new SalonDTO( logic.createSalon( dto.toEntity( ) ) );
	}
	
	@PUT
	@Path( "{id: \\d+}" )
	public SalonDTO updateSalon( @PathParam( "id" ) Long id, SalonDTO dto )
	{
		SalonEntity entity = dto.toEntity( );
		entity.setId( id );
		return new SalonDTO( logic.updateSalon( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteSalon( @PathParam( "id" ) Long id )
	{
		logic.deleteSalon( id );
	}
}