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

import co.edu.uniandes.csw.artesanias.dtos.CiudadDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.CiudadDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.CiudadLogic;
import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Miller
 */
@Path("/ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadResource {
    
    @Inject private CiudadLogic logic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public CiudadDTO createCiudad(CiudadDTO dto) throws BusinessLogicException {
        return new CiudadDTO(logic.createCiudad(dto.toEntity()));
    }
    
    @GET
    public List<CiudadDTO> getCiudades() {
        return listEntity2DTO(logic.getCiudades());
    }
    
    @GET
    @Path("{id: \\d+}")
    public CiudadDetailDTO getCiudad(@PathParam("id") Long id) throws BusinessLogicException {
        if (logic.getCiudad(id) == null)
            throw new WebApplicationException("La ciudad buscada no existe", 404);
        return new CiudadDetailDTO(logic.getCiudad(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO updateCiudad(@PathParam("id") Long id, CiudadDTO dto) throws BusinessLogicException {
        CiudadEntity entity = dto.toEntity();
        entity.setId(id);
        if (logic.getCiudad(id) == null)
            throw new WebApplicationException("La ciudad buscada no existe", 404);
        return new CiudadDTO(logic.updateCiudad(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) throws BusinessLogicException {
          logic.deleteCiudad(id);
    }
    
    @Path( "{idCiudad: \\d+}/artesanos" )
    public CiudadArtesanoResource getCiudadArtesanoResource() {
        return new CiudadArtesanoResource();
    }
    
    @Path( "{idCiudad: \\d+}/espacios" )
    public CiudadEspacioResource getCiudadEspacioResource() {
        return new CiudadEspacioResource();
    }
    
    private List<CiudadDTO> listEntity2DTO(List<CiudadEntity> entities) {
        List<CiudadDTO> rta = new LinkedList<>();
        for (CiudadEntity entity : entities)
            rta.add(new CiudadDTO(entity));
        return rta;
    }
}