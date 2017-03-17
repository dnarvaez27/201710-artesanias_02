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
import co.edu.uniandes.csw.artesanias.ejbs.FeriaLogic;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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

// TODO Segun organizadorResource , ferias es un subrecurso cuyo path es @Path( "{organizadorId: \\d+}/ferias" ) o se define como un subrecurso de la clase organizador.
// TODO Cada método debde recibir como PathParam a organizadorId
@Path("/ferias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeriaResource {
    
    @Inject private FeriaLogic feriaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    @POST
    public FeriaDTO createFeria(FeriaEntity entity) {
        return new FeriaDTO(feriaLogic.createFeria(entity));
    }
    //TODO debe traer las ferias no los artesanos. Por qué el método se llama getArtesanos? 
    @GET
    public List<FeriaDTO> getArtesanos() {
        return listEntity2DTO(feriaLogic.getFerias());
    }
    
    @GET
    @Path("{id: \\d+}")
    //TODO debe traer la feria no el artesano. Por qué el método se llama getArtesano? 
    public FeriaDTO getArtesano(@PathParam("id") Long id ) {
        //TODO si la feria id no existe debe disparar WebApplicationException con 404
        return new FeriaDTO(feriaLogic.getFeria(id));
    }
    
    @PUT
    @Path("{id: \\d+}")
     //TODO debe actualizar la feria no el artesano. Por qué el método se llama updateArtesano? 
    public FeriaDTO updateArtesano(@PathParam("id") Long id, FeriaDTO dto) {
        //TODO si la feria id no existe debe disparar WebApplicationException con 404
        FeriaEntity entity = dto.toEntity();
        entity.setId(id);
        return new FeriaDTO(feriaLogic.updateFeria(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
     //TODO debe borrar la feria no el artesano. Por qué el método se llama deleteArtesano? 
    public void deleteArtesano(@PathParam( "id" ) Long id ) {
        feriaLogic.deleteFeria(id);
    }
    
    private List<FeriaDTO> listEntity2DTO(List<FeriaEntity> entities) {
        List<FeriaDTO> rta = new LinkedList<FeriaDTO>();
        for (FeriaEntity entity : entities)
            rta.add(new FeriaDTO(entity));
        return rta;
    }
    
    //TODO falta GET /ferias/:id/artesanos los artesanos de la feria :id
    //TODO falta GET /ferias/:id/espacios los artesanos de la feria :id
    
    //TODO verificar los subrecursos conferencia y boleta
    
}
