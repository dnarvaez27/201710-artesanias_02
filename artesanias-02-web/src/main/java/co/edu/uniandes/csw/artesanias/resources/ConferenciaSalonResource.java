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
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
 



@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ConferenciaSalonResource
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
	public List<ConferenciaDTO> getConferenciasFromSalon( @PathParam("salonId") Long salonId) 
	{
		
		
                
            return listEntity2DTO( conferenciaLogic.getConferenciasFromsalon(salonId));
	}
        
       
	
	@GET
	@Path( "{id: \\d+}" )
	public ConferenciaDTO getConferencia(@PathParam("salonId")Long salonId, @PathParam( "id" ) Long id ) throws BusinessLogicException
	{
           
		return new ConferenciaDetailDTO( conferenciaLogic.getConferencia( id,salonId ) );
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
            
		
		ConferenciaEntity entity = dto.toEntity( );
		entity.setId( id );
		return new ConferenciaDTO( conferenciaLogic.updateConferencia( entity ) );
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public void deleteSConferencia( @PathParam("salonId")Long salonId,@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            
		
		conferenciaLogic.deleteConferencia( id,salonId );
	}
}