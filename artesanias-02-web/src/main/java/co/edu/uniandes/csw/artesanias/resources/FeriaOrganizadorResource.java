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

import co.edu.uniandes.csw.artesanias.dtos.detail.OrganizadorDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
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
public class FeriaOrganizadorResource {
    
    @Inject private FeriaLogic feriaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos dependiendo de los organizadores
    //--------------------------------------------------------------------------
    
    @GET
    @Path("{id: \\d+}")
    public OrganizadorDetailDTO getFeriaOrganizador(@PathParam("idFeria") Long idFeria,
            @PathParam("id") Long id) {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return new OrganizadorDetailDTO(feriaLogic.getOrganizador(idFeria, id));
    }
    
    @GET
    public List<OrganizadorDetailDTO> getFeriaOrganizadores(@PathParam("idFeria") Long idFeria) {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return listOrganizadorEntity2DetailDTO(feriaLogic.getOrganizadores(idFeria));
    }
    
    @PUT
    public List<OrganizadorDetailDTO> updateFeriaOrganizadores(@PathParam("idFeria") Long idFeria,
            List<OrganizadorDetailDTO> organizadores) {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        for (OrganizadorDetailDTO o : organizadores) {
            feriaLogic.addOrganizador(idFeria, o.getId());
        }
        return listOrganizadorEntity2DetailDTO(feriaLogic.getFeria(idFeria).getOrganizadores());
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFeriaOrganizador(@PathParam("idFeria") Long idFeria,
            @PathParam("id") Long id) {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("No existe la feria", 404);
        feriaLogic.removeOrganizador(idFeria, id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<OrganizadorDetailDTO> listOrganizadorEntity2DetailDTO(List<OrganizadorEntity> entities) {
        List<OrganizadorDetailDTO> rta = new LinkedList<>();
        for (OrganizadorEntity entity : entities)
            rta.add(new OrganizadorDetailDTO(entity));
        return rta;
    }
}
