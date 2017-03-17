/*
 * The MIT License
 *
 * Copyright 2017 Miller.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.artesanias.resources;

import co.edu.uniandes.csw.artesanias.dtos.BoletaDTO;
import co.edu.uniandes.csw.artesanias.ejbs.BoletaLogic;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
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
 *
 * @author Miller
 */

//TODO segun el diagrama de clases, las boletas son dependientes de la feria. 
//TODO El PATH debe ser /ferias/:idFeria/boletas y cada método debe recibir un @PathParam( "idFeria" ) Long idFeria
// TODO cada método debe validar que la feria con el idFeria exista o disparar WebApplicationException 404

@Path("/boletas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletaResource {
    
    @Inject private BoletaLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public BoletaDTO createBoleta(BoletaEntity entity) {
        return new BoletaDTO(logic.createBoleta(entity));
    }
    
    @GET
    public List<BoletaDTO> getBoletas() {
        return listEntity2DTO(logic.getBoletas());
    }
    
    @GET
    @Path("{id: \\d+}")
    public BoletaDTO getBoleta(@PathParam("id") Long id ) {
        // TODO si la boleta no existe debe disparar WebApplicationException 404
		
        return new BoletaDTO(logic.getBoleta(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public BoletaDTO updateBoleta(@PathParam("id") Long id, BoletaDTO dto) {
        // TODO si la boleta no existe debe disparar WebApplicationException 404
	
        BoletaEntity entity = dto.toEntity();
        entity.setId(id);
        return new BoletaDTO(logic.updateBoleta(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBoleta(@PathParam("id") Long id) {
        // TODO si la boleta no existe debe disparar WebApplicationException 404
	
        logic.deleteBoleta(id);
    }
    
    private List<BoletaDTO> listEntity2DTO(List<BoletaEntity> entities) {
        List<BoletaDTO> rta = new LinkedList<BoletaDTO>();
        for (BoletaEntity entity : entities)
            rta.add(new BoletaDTO(entity));
        return rta;
    }
}
