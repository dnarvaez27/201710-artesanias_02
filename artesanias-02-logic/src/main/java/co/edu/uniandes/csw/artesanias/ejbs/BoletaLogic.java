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
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.BoletaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Logica de la entidad boleta.
 * @author Miller
 * Reglas de negocio:
 */
@Stateless
public class BoletaLogic {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    /**
     * Persistencia de BoletaEntity
     */
    @Inject private BoletaPersistence persistence;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Se devuelve la entidad boleta con el id dado.
     * @param idFeria id de la feria que tiene la boleta con el id dado.
     * @param id de la boleta a buscar.
     * @return entidad boleta con el id dado.
     * @throws BusinessLogicException si no existe la boleta o el id no es válido.
     */
    public BoletaEntity getBoleta(Long idFeria, Long id) throws BusinessLogicException {
        checkId(idFeria);
        checkId(id);
        BoletaEntity e = persistence.find(idFeria, id);
        if (e == null)
            throw new BusinessLogicException("No existe la boleta buscada", Response.Status.NOT_FOUND);
        return e;
    }
    
    /**
     * Devuelve el conjunto de boletas de una feria.
     * @param idFeria id de la feria de la que se desea saber el conjunto de boletas
     * @return lista de boletas de una feria.
     * @throws BusinessLogicException si el id de la feria no es válido.
     */
    public List<BoletaEntity> getBoletas(Long idFeria) throws BusinessLogicException {
        checkId(idFeria);
        return persistence.findAll(idFeria);
    }
    
    /**
     * Crea una nueva entidad boleta.
     * @param entity Entidad boleta a ser ingresada en la base de datos
     * @return la nueva entidad boleta.
     * @throws BusinessLogicException Si no cumple con alguna regla de negocio.
     */
    public BoletaEntity createBoleta(BoletaEntity entity) throws BusinessLogicException {
        checkData(entity);
        if (entity.getFeria().getTotalBoletas() < persistence.count(entity.getFeria().getId()))
            throw new BusinessLogicException("No hay boletas disponibles", Response.Status.BAD_REQUEST);
        return persistence.create(entity);
    }
    
    /**
     * Actualiza los valores de la boleta con el id dado y que pertenece a la feria dada.
     * @param idFeria id de la feria que contiene a la boleta
     * @param entity entidad con la información para actualizar la boleta.
     * @return la entidad de la boleta actualizada.
     * @throws BusinessLogicException si el id de la feria no es válido o los datos que
     * se desean actualizar no son válidos.
     */
    public BoletaEntity updateBoleta(Long idFeria, BoletaEntity entity) throws BusinessLogicException {
        checkId(idFeria);
        BoletaEntity e = persistence.find(idFeria, entity.getId());
        if (e == null)
            throw new BusinessLogicException("No existe la boleta buscada", Response.Status.NOT_FOUND);
        if (entity.getFeria() == null)
            entity.setFeria(e.getFeria());
        if (e.getInicio() == null)
            entity.setInicio(e.getInicio());
        if (e.getFin() == null)
            entity.setFin(e.getFin());
        if (e.getTipo() == null)
            entity.setTipo(e.getTipo());
        if (e.getPrecio() == null)
            entity.setPrecio(e.getPrecio());
        checkData(entity);
        return persistence.update(entity);
    }
    
    /**
     * Elimina la boleta con el id dado que pertenece a la feria con el id dado.
     * @param idFeria id de la feria que contiene la boleta.
     * @param id de la boleta.
     * @throws BusinessLogicException si los id's no son válidos o la boleta no existe.
     */
    public void deleteBoleta(Long idFeria, Long id) throws BusinessLogicException {
        checkId(idFeria);
        checkId(id);
        if (persistence.find(idFeria, id) == null)
            throw new BusinessLogicException("No existe la boleta buscada", Response.Status.NOT_FOUND);
        persistence.delete(idFeria, id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    /**
     * Revisa la validez del id dado
     * @param id a ser revisado
     * @throws BusinessLogicException si el id es nulo o menor a 0.
     */
    private void checkId(Long id) throws BusinessLogicException {
        if (id == null || id < 0)
            throw new BusinessLogicException("El id ingresado no es válido.", Response.Status.BAD_REQUEST);
    }
    
    /**
     * Revisa las reglas de negocio.
     * @param e entidad a ser revisada.
     * @throws BusinessLogicException si no se cumple alguna regla de negocio.
     */
    private void checkData(BoletaEntity e) throws BusinessLogicException {
        checkId(e.getId());
        if (e.getFeria() == null)
            throw new BusinessLogicException("La boleta debe pertenecer a una feria.", Response.Status.BAD_REQUEST);
        if (e.getInicio() == null)
            throw new BusinessLogicException("La boleta debe tener una fecha de inicio.", Response.Status.BAD_REQUEST);
        if (e.getFin() == null)
            throw new BusinessLogicException("La boleta debe tener una fecha de fin.", Response.Status.BAD_REQUEST);
        if (e.getInicio().compareTo(e.getFin()) >= 0)
            throw new BusinessLogicException("La fecha de inicio debe ser estrictamente menor a la de fin.", Response.Status.BAD_REQUEST);
        if (e.getFeria().getInicio().compareTo(e.getInicio()) > 0)
            throw new BusinessLogicException("La fecha de inicio debe ser mayor a la fecha de inicio de la feria", Response.Status.BAD_REQUEST);
        if (e.getFeria().getFin().compareTo(e.getFin()) < 0)
            throw new BusinessLogicException("La fecha de fin debe ser menor a la de fin de la feria.", Response.Status.BAD_REQUEST);
        if (e.getTipo() == null)
            throw new BusinessLogicException("La boleta debe tener un tipo.", Response.Status.BAD_REQUEST);
        if (e.getTipo() < 0 || e.getTipo() > 2)
            throw new BusinessLogicException("El tipo de boleta debe estar entre 0 y 2", Response.Status.BAD_REQUEST);
        if (e.getPrecio() == null)
            throw new BusinessLogicException("La boleta debe tener un precio", Response.Status.BAD_REQUEST);
        if (e.getPrecio() < 0.0)
            throw new BusinessLogicException("El precio de la boleta no puede ser negativo", Response.Status.BAD_REQUEST);
    }
}
