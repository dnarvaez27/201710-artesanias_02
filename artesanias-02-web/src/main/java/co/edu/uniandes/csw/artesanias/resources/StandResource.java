/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.StandDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.StandLogic;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author ja.espinosa12
 */
@Path( "/stands" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class StandResource
{
        /**
         * lógica correspondiente a los stands
         */
	@Inject
	private StandLogic logic;
	
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
         * Crea un nuevo stand con base al dto ingresado en el pabellón dado
         * @param pabellonId
         * @param dto
         * @return StandDTO
         * @throws BusinessLogicException 
         */
	@POST
	public StandDTO createStand( @PathParam( "pabellonId" )
			                             Long pabellonId, StandDetailDTO dto ) throws BusinessLogicException
	{
		PabellonEntity en = new PabellonEntity( );
		en.setId( pabellonId );
		dto.setPabellon( new PabellonDTO( en ) );
		StandEntity entity = logic.createStand( dto.toEntity( ) );
		return new StandDTO( entity );
	}
	
        /**
         * Retorna todos los stands en el pabellón dado
         * @param pabellonId
         * @return Lista de StandDTO
         */
	@GET
	public List<StandDTO> getStands( @PathParam( "pabellonId" ) Long pabellonId )
	{
		return listEntity2DTO( logic.getStandsFromPabellon( pabellonId ) );
	}
	
        /**
         * Retorna el stand con id dado
         * @param id
         * @return StandDTO
         */
	@GET
	@Path( "{id: \\d+}" )
	public StandDTO getStand( @PathParam( "id" ) Long id )
	{
		return new StandDTO( logic.getStand( id ) );
	}
	
        /**
         * Actualiza el stand con id igresado en el pabellón dado con base al dto dado
         * @param pabellonId
         * @param id
         * @param dto
         * @returnStandDTO
         * @throws BusinessLogicException 
         */
	@PUT
	@Path( "{id: \\d+}" )
	public StandDTO updateStand(
			@PathParam( "pabellonId" ) Long pabellonId,
			@PathParam( "id" ) Long id, StandDTO dto ) throws BusinessLogicException
	{
		PabellonEntity pab = new PabellonEntity( );
		pab.setId( pabellonId );
		StandEntity entity = dto.toEntity( );
		entity.setId( id );
		entity.setPabellon( pab );
		return new StandDTO( logic.updateStand( entity ) );
	}
	
        /**
         * Elimina el stand con id ingresado
         * @param id 
         */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteStand( @PathParam( "id" ) Long id )
	{
		logic.deleteStand( id );
	}
	
        /**
         * Retorna una lista de StandDTO con base en una lista de StandEntity
         * @param entities
         * @return rta
         */
	private List<StandDTO> listEntity2DTO( List<StandEntity> entities )
	{
		List<StandDTO> rta = new LinkedList<>( );
		for( StandEntity entity : entities )
		{
			rta.add( new StandDTO( entity ) );
		}
		return rta;
	}
}