/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
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
// TODO todos los métodos deben recibir el idEspacio porque este es un subrecurso de Espacio 
// TODO en los métodos que reciben el id del pabellon se debe verificar que exista o sino disparar WebApplicationExcepton 404

@Path( "/pabellones" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class PabellonResource
{
        /**
         * lógica correspondiente a los pabellones
         */
	@Inject
	private PabellonLogic logic;
	
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
         * Crea un nuevo pabellón con base al PabellonEntity ingresado
         * @param entity
         * @return PabellonDTO
         * @throws BusinessLogicException 
         */
	@POST
	public PabellonDTO createPabellon( PabellonEntity entity ) throws BusinessLogicException
	{
		return new PabellonDTO( logic.createPabellon( entity ) );
	}
	
        /**
         * Retorna una lista con todos los pabellones
         * @return lista de PabellonDTO
         */
	@GET
	public List<PabellonDTO> getPabellones( )
	{
		return listEntity2DTO( logic.getPabellones( ) );
	}
	
        /**
         * Retorna el pabellón con id dado
         * @param id
         * @return PabellonDTO
         */
	@GET
	@Path( "{id: \\d+}" )
	public PabellonDTO getPabellon( @PathParam( "id" ) Long id )
	{
		return new PabellonDTO( logic.getPabellon( id ) );
	}
	
        /**
         * Actualiza el pabellón con id dado con base al dto ingresado
         * @param id
         * @param dto
         * @return PabellonDTO
         * @throws BusinessLogicException 
         */
	@PUT
	@Path( "{id: \\d+}" )
	public PabellonDTO updatePabellon( @PathParam( "id" ) Long id, PabellonDTO dto ) throws BusinessLogicException
	{
		PabellonEntity entity = dto.toEntity( );
		entity.setId( id );
		return new PabellonDTO( logic.updatePabellon( entity ) );
	}
	
        /**
         * Elimina el pabellón con id ingresado
         * @param id 
         */
	@DELETE
	@Path( "{id: \\d+}" )
	public void deletePabellon( @PathParam( "id" ) Long id )
	{
		logic.deletePabellon( id );
	}
	
        /**
         * Retorna una lista de PabellonDTO con base a la lista de PabellonEntity ingresada
         * @param entities
         * @return rta
         */
	private List<PabellonDTO> listEntity2DTO( List<PabellonEntity> entities )
	{
		List<PabellonDTO> rta = new LinkedList<>( );
		for( PabellonEntity entity : entities )
		{
			rta.add( new PabellonDTO( entity ) );
		}
		return rta;
	}
	
        // TODO actualizar el diagrama de clasespara indicar que salon y satand son subrecursos de pabellon
        /**
         * Ruta a los stands del pabellón
         * @return StandResource
         */
	@Path( "{pabellonId: \\d+}/stands" )
	public Class<StandResource> getStandResource( )
	{
		return StandResource.class;
	}
	
        /**
         * Ruta a los salones del pabellón
         * @return SalonResource
         */
	@Path( "{pabellonId: \\d+}/salones" )
	public Class<SalonResource> getSalonResource( )
	{
		return SalonResource.class;
	}
}