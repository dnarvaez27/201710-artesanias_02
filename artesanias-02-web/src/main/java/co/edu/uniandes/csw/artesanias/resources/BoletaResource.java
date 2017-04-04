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
import co.edu.uniandes.csw.artesanias.dtos.EspectadorDTO;
import co.edu.uniandes.csw.artesanias.ejbs.BoletaLogic;
import co.edu.uniandes.csw.artesanias.ejbs.EspectadorLogic;
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BoletaResource {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    @Inject private BoletaLogic logic;
    @Inject private FeriaLogic feriaLogic;
    @Inject private EspectadorLogic espectadorLogic;
    
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    @POST
    public BoletaDTO createBoleta(BoletaEntity entity) 
            throws BusinessLogicException {
        FeriaEntity ef = feriaLogic.getFeria(entity.getFeria().getId());
        EspectadorEntity ee = espectadorLogic.getEspectador(
                entity.getEspectador().getId());
        if (ef == null)
            throw new WebApplicationException("La feria no existe", 404);
        if (ee == null)
            throw new WebApplicationException("El espectador no existe", 404);
        entity.setFeria(ef);
        entity.setEspectador(ee);
        return new BoletaDTO(logic.createBoleta(entity));
    }
    
    @GET
    public List<BoletaDTO> getBoletas(@PathParam("idFeria") Long idFeria) 
            throws BusinessLogicException {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("La feria no existe", 404);
        return listEntity2DTO(logic.getBoletas(idFeria));
    }
    
    @GET
    @Path("{id: \\d+}")
    public BoletaDTO getBoleta(@PathParam("idFeria") Long idFeria, 
            @PathParam("id") Long id ) throws BusinessLogicException {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("La feria no existe", 404);
        if (logic.getBoleta(idFeria, id) == null)
            throw new WebApplicationException("La boleta no existe", 404);
        return new BoletaDTO(logic.getBoleta(idFeria, id));
    }
    
    @GET
    @Path("{id: \\d+}/espectador")
    public EspectadorDTO getEspectador(@PathParam("idFeria") Long idFeria, 
            @PathParam("id") Long id) throws BusinessLogicException {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("La feria no existe", 404);
        if (logic.getBoleta(idFeria, id) == null)
            throw new WebApplicationException("La boleta no existe", 404);
        return new EspectadorDTO(logic.getEspectador(idFeria, id));
    } 
    
    @PUT
    @Path("{id: \\d+}")
    public BoletaDTO updateBoleta(@PathParam("idFeria") Long idFeria, 
            @PathParam("id") Long id, BoletaDTO dto) throws BusinessLogicException {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("La feria no existe", 404);
        if (logic.getBoleta(idFeria, id) == null)
            throw new WebApplicationException("La boleta no existe", 404);
        BoletaEntity entity = dto.toEntity();
        entity.setId(id);
        return new BoletaDTO(logic.updateBoleta(idFeria, entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBoleta(@PathParam("idFeria") Long idFeria, 
            @PathParam("id") Long id) throws BusinessLogicException {
        if (feriaLogic.getFeria(idFeria) == null)
            throw new WebApplicationException("La feria no existe", 404);
        if (logic.getBoleta(idFeria, id) == null)
            throw new WebApplicationException("La boleta no existe", 404);
        logic.deleteBoleta(idFeria, id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private List<BoletaDTO> listEntity2DTO(List<BoletaEntity> entities) {
        List<BoletaDTO> rta = new LinkedList<BoletaDTO>();
        for (BoletaEntity entity : entities)
            rta.add(new BoletaDTO(entity));
        return rta;
    }
}
