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

import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspacioLogic;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
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
 *
 * @author Miller
 */
@Path("/espacios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EspacioResource {
    
    @Inject private EspacioLogic espacioLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public EspacioDTO createEspacio(EspacioEntity entity) throws BusinessLogicException {
        return new EspacioDTO(espacioLogic.createEspacio(entity));
    }
    
    @GET
    public List<EspacioDTO> getEspacios(@PathParam("idCiudad") Long idCiudad) throws BusinessLogicException {
        return listEntity2DTO(espacioLogic.getEspacios(idCiudad));
    }
    
    @GET
    @Path("{id: \\d+}")
    public EspacioDTO getEspacio(@PathParam("idCiudad") Long idCiudad, @PathParam("id") Long id ) throws BusinessLogicException {
        return new EspacioDTO(espacioLogic.getEspacio(idCiudad, id));
    }
    
    @GET
    @Path("{id: \\d+}")
    public EspacioDTO getEspacio(@PathParam("id") Long id) throws BusinessLogicException {
        return new EspacioDTO(espacioLogic.getEspacio(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public EspacioDTO updateEspacio(@PathParam("id") Long id, EspacioDTO dto) throws BusinessLogicException {
        EspacioEntity entity = dto.toEntity();
        entity.setId(id);
        return new EspacioDTO(espacioLogic.updateEspacio(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEspacio(@PathParam("id") Long id) throws BusinessLogicException {
        espacioLogic.deleteEspacio(id);
    }
    
    private List<EspacioDTO> listEntity2DTO(List<EspacioEntity> entities) {
        List<EspacioDTO> rta = new LinkedList<EspacioDTO>();
        for (EspacioEntity entity : entities)
            rta.add(new EspacioDTO(entity));
        return rta;
    }
}
