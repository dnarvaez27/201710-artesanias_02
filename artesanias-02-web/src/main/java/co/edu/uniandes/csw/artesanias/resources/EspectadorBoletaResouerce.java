/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.detail.BoletaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspectadorLogic;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Miller
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EspectadorBoletaResouerce {
    
    @Inject private EspectadorLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de las boletas
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public BoletaDetailDTO getEspectadorBoleta(@PathParam("idEspectador") Long idEspectador,
            @PathParam("id") Long id) {
        if (logic.getEspectador(idEspectador) == null)
            throw new WebApplicationException("No existe el espectador", 404);
        return new BoletaDetailDTO(logic.getBoleta(idEspectador, id));
    }
    
    @GET
    public List<BoletaDetailDTO> getEspectadorBoletas(@PathParam("idEspectador") Long idEspectador) {
        if (logic.getEspectador(idEspectador) == null)
            throw new WebApplicationException("No existe el espectador", 404);
        return listBoletaEntity2DetailDTO(logic.getBoletas(idEspectador));
    }
    
    @PUT
    public List<BoletaDetailDTO> updateEspectadorBoleta(@PathParam("idEspectador") Long idEspectador,
            List<BoletaDetailDTO> boletas) {
        if (logic.getEspectador(idEspectador) == null)
            throw new WebApplicationException("No existe el espectador", 404);
        try{
            for (BoletaDetailDTO o : boletas) {
                logic.addBoleta(idEspectador, o.toEntity());
            }
        }
        catch( Exception e ){
            throw new WebApplicationException("Hubo un error actualizando las boletas", 404);
        }
        return listBoletaEntity2DetailDTO(logic.getEspectador(idEspectador).getBoletas());
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEspectadorBoleta(@PathParam("idEspectador") Long idEspectador,
            @PathParam("id") Long id) {
        if (logic.getEspectador(idEspectador) == null)
            throw new WebApplicationException("No existe el espectador", 404);
        logic.removeBoleta(idEspectador, id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<BoletaDetailDTO> listBoletaEntity2DetailDTO(List<BoletaEntity> entities) {
        List<BoletaDetailDTO> rta = new LinkedList<>();
        for (BoletaEntity entity : entities)
            rta.add(new BoletaDetailDTO(entity));
        return rta;
    }
    
}
