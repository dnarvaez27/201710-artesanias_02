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

import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.dtos.detail.FeriaDetailDTO;
import co.edu.uniandes.csw.artesanias.ejbs.EspacioLogic;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.ejbs.OrganizadorLogic;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.resources.asociaciones.ArtesanoFeriasResource;
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
@Path("ferias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeriaResource {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    @Inject private FeriaLogic feriaLogic;
    @Inject private OrganizadorLogic organizadorLogic;
    @Inject private EspacioLogic espacioLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    @POST
    public FeriaDTO createFeria(FeriaEntity entity) throws BusinessLogicException {
        if (entity.getOrganizadores() == null || entity.getOrganizadores().isEmpty())
            throw new WebApplicationException("No existen los organizadores", 404);
        if (entity.getEspacio() == null)
            throw new WebApplicationException("No existe el espacio", 404);
        EspacioEntity ee = espacioLogic.getEspacio(entity.getId());
        if (ee == null)
            throw new WebApplicationException("No existe el espacio", 404);
        List<OrganizadorEntity> lst = new LinkedList<>();
        for (OrganizadorEntity o : entity.getOrganizadores()) {
            OrganizadorEntity oe = organizadorLogic.getOrganizador(o.getId());
            if (oe == null)
                throw new WebApplicationException("No existe el organizador", 404);
            lst.add(oe);
        }
        entity.setEspacio(ee);
        entity.setOrganizadores(lst);
        return new FeriaDTO(feriaLogic.createFeria(entity));
    }
    
    @GET
    public List<FeriaDTO> getFerias() {
        return listEntity2DTO(feriaLogic.getFerias());
    }
    
    @GET
    @Path("{id: \\d+}")
    public FeriaDetailDTO getFeria(@PathParam("id") Long id) throws BusinessLogicException {
        if (feriaLogic.getFeria(id) == null)
            throw new WebApplicationException("La feria no existe", 404);
        return new FeriaDetailDTO(feriaLogic.getFeria(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public FeriaDTO updateFeria(@PathParam("id") Long id, FeriaDTO dto) throws BusinessLogicException {
        FeriaEntity entity = dto.toEntity();
        entity.setId(id);
        if (feriaLogic.getFeria(id) == null)
            throw new WebApplicationException("La feria no existe", 404);
        return new FeriaDTO(feriaLogic.updateFeria(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFeria(@PathParam( "id" ) Long id) throws BusinessLogicException {
        if (feriaLogic.getFeria(id) == null)
            throw new WebApplicationException("La feria no existe", 404);
        feriaLogic.deleteFeria(id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de subrecursos
    //--------------------------------------------------------------------------
    
    @Path( "{idFeria: \\d+}/organizadores" )
    public FeriaOrganizadorResource getFeriaOrganizadorResource() {
        return new FeriaOrganizadorResource();
    }
    
    
    @Path( "{idFeria: \\d+}/boletas" )
    public FeriaBoletaResource getFeriaBoletaResource() {
        return new FeriaBoletaResource();
    }
    
    @Path( "{idFeria: \\d+}/conferencias" )
    public FeriaConferenciaResource getFeriaConferenciaResource() {
        return new FeriaConferenciaResource();
    }
    
    @Path( "{idFeria: \\d+}/artesanos" )
    public ArtesanoFeriasResource getArtesanoFeriasResource(@PathParam("id") Long id) {
        if (feriaLogic.getFeria(id) == null)
            throw new WebApplicationException("No existe la feria", 404);
        return new ArtesanoFeriasResource();
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<FeriaDTO> listEntity2DTO(List<FeriaEntity> entities) {
        List<FeriaDTO> rta = new LinkedList<FeriaDTO>();
        for (FeriaEntity entity : entities)
            rta.add(new FeriaDTO(entity));
        return rta;
    }
}
