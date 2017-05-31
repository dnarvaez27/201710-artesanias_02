/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.CiudadDTO;
import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.EspacioDetailDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.PabellonDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspacioLogic;
import co.edu.uniandes.csw.artesanias.ejbs.PabellonLogic;
import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
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
public class EspacioResource {


    @Inject
    private EspacioLogic logic;

    /**
     * Servicio de respuesta HTTP
     */
    @Context
    private HttpServletResponse response;

    @QueryParam("page")
    private Integer page;

    @QueryParam("limit")
    private Integer maxRec;


    @POST
    public EspacioDTO createEspacio(@PathParam( "ciudadId" ) Long id, EspacioDetailDTO dto ) throws BusinessLogicException
	{
		CiudadEntity en = new CiudadEntity( );
		en.setId( id );
		dto.setCiudad( new CiudadDTO( en ) );
		EspacioEntity entity = logic.createEspacio( dto.toEntity( ) );
		return new EspacioDTO( entity );
    }
    @GET
	public List<EspacioDTO> getEspacios( @PathParam( "ciudadId" ) Long id )
	{
		return listEntity2DTO( logic.getEspaciosFromCiudad(id) );
	}

    @GET
	@Path( "{id: \\d+}" )
	public EspacioDTO getEspacio(
			@PathParam( "ciudadId" ) Long ciudadId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            EspacioDTO res = new EspacioDTO( logic.getEspacio( ciudadId, id ) );
            if( res != null )
            {
                return res;
            }
            else
            {
                throw new BusinessLogicException( String.format( "El espacio %s no pertenece a la ciudad %s ", id, ciudadId ), Response.Status.NOT_FOUND );
            }
	}
    
    @PUT
	@Path( "{id: \\d+}" )
	public EspacioDTO updateEspacio(
			@PathParam( "ciudadId" ) Long ciudadId,
			@PathParam( "id" ) Long id, EspacioDTO dto ) throws BusinessLogicException
	{
	
		CiudadEntity esp = new CiudadEntity( );
		esp.setId( ciudadId );
		EspacioEntity entity = dto.toEntity( );
		
		entity.setId( id );
		entity.setCiudad( esp );
                EspacioDTO res = new EspacioDTO(logic.updateEspacio( entity ));
                if( res != null)
                {
                    return res;
                }
                else
                {
                    throw new BusinessLogicException( String.format( "El espacio %s no pertenece a la ciudad %s ", id, ciudadId ), Response.Status.NOT_FOUND );
                }
	}    
    
        @DELETE
	@Path( "{id: \\d+}" )
	public void deleteEspacio(
			@PathParam( "ciudadId" ) Long ciudadId,
			@PathParam( "id" ) Long id ) throws BusinessLogicException
	{
            try
		{
			getEspacio( ciudadId, id );
			logic.deleteEspacio( ciudadId, id );
		}
		catch( BusinessLogicException e )
		{
			throw new BusinessLogicException( String.format( "El espacio %s no pertenece a la ciudad %s", id, ciudadId ), Response.Status.FORBIDDEN );
		}
	}

	private List<EspacioDTO> listEntity2DTO( List<EspacioEntity> entityList )
	{
		List<EspacioDTO> list = new ArrayList<>( );
		for( EspacioEntity entity : entityList )
		{
			list.add( new EspacioDTO( entity ) );
		}
		return list;
	}
        
    @Path("{espacioId: \\d+}/pabellones")
    public Class<PabellonResource> getPabellonResource() {
        return PabellonResource.class;
    }

    @Path("{espacioId: \\d+}/ferias")
    public Class<FeriaResource> getFeriaResource() {

        return FeriaResource.class;
    }
}
