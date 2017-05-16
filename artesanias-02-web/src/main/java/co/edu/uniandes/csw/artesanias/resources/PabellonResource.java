/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.PabellonDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspacioLogic;
import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import java.util.ArrayList;

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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author ja.espinosa12
 */
// TODO todos los métodos deben recibir el idEspacio porque este es un subrecurso de Espacio 
// TODO en los métodos que reciben el id del pabellon se debe verificar que exista o sino disparar WebApplicationExcepton 404
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PabellonResource {

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

    @QueryParam("page")
    private Integer page;

    @QueryParam("limit")
    private Integer maxRec;

    /**
     * Crea un nuevo pabellón con base al PabellonEntity ingresado
     *
     * @param entity
     * @return PabellonDTO
     * @throws BusinessLogicException
     */
    @POST
    public PabellonDTO createPabellon(@PathParam( "espacioId" ) Long id, PabellonDetailDTO dto ) throws BusinessLogicException
	{
		EspacioEntity en = new EspacioEntity( );
		en.setId( id );
		dto.setEspacio( new EspacioDTO( en ) );
		PabellonEntity entity = logic.createPabellon( dto.toEntity( ) );
		return new PabellonDTO( entity );
    }
    @GET
	public List<PabellonDTO> getPabellones( @PathParam( "espacioId" ) Long id )
	{
		return listEntity2DTO( logic.getPabellonesFromEspacio( id ) );
	}

    @GET
	@Path( "{id: \\d+}" )
	public PabellonDTO getPabellon(
			@PathParam( "espacioId" ) Long espacioId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            PabellonDTO res = new PabellonDTO( logic.getPabellon( espacioId, id ) );
            if( res != null )
            {
                return res;
            }
            else
            {
                throw new BusinessLogicException( String.format( "El pabellon %s no pertenece al espacio %s ", id, espacioId ), Response.Status.NOT_FOUND );
            }
	}
    
    @PUT
	@Path( "{id: \\d+}" )
	public PabellonDTO updatePabellon(
			@PathParam( "espacioId" ) Long espacioId,
			@PathParam( "id" ) Long id, PabellonDTO dto ) throws BusinessLogicException
	{
	
		EspacioEntity esp = new EspacioEntity( );
		esp.setId( espacioId );
		PabellonEntity entity = dto.toEntity( );
		
		entity.setId( id );
		entity.setEspacio( esp );
                PabellonDTO res = new PabellonDTO(logic.updatePabellon( entity ));
                if( res != null)
                {
                    return res;
                }
                else
                {
                    throw new BusinessLogicException( String.format( "El pabellon %s no pertenece al espacio %s ", id, espacioId ), Response.Status.NOT_FOUND );
                }
	}    
    
        @DELETE
	@Path( "{id: \\d+}" )
	public void deletePabellon(
			@PathParam( "espacioId" ) Long espacioId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            try
		{
			getPabellon( espacioId, id );
			logic.deletePabellon( espacioId, id );
		}
		catch( BusinessLogicException e )
		{
			throw new BusinessLogicException( String.format( "El pabellon %s no pertenece al espacio %s", id, espacioId ), Response.Status.FORBIDDEN );
		}
	}

	private List<PabellonDTO> listEntity2DTO( List<PabellonEntity> entityList )
	{
		List<PabellonDTO> list = new ArrayList<>( );
		for( PabellonEntity entity : entityList )
		{
			list.add( new PabellonDTO( entity ) );
		}
		return list;
	}
        
    @Path("{pabellonId: \\d+}/stands")
    public Class<StandResource> getStandResource() {
        return StandResource.class;
    }

    /**
     * Ruta a los salones del pabellón
     *
     * @return SalonResource
     */
    @Path("{pabellonId: \\d+}/salones")
    public Class<SalonResource> getSalonResource() {

        return SalonResource.class;
    }
}
