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
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Persistencia de la entidad boleta.
 * @author Miller
 */
@Stateless
public class BoletaPersistence {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    /**
     * Manejador o controlador de entidades.
     */
    @PersistenceContext(unitName = "artesaniasPU")
    protected EntityManager em;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------

    /**
     * Devuelve la entidad boleta con el id indicado.
     * @param idFeria id de la feria de la que se quiere saber la boleta.
     * @param id de la boleta a buscar.
     * @return la entidad boleta con el id indicado.
     */
    public BoletaEntity find(Long idFeria, Long id) {
        TypedQuery<BoletaEntity> q= em.createQuery(
                "select u from BoletaEntity u where u.feria.id = :idF and u.id = :id"
                , BoletaEntity.class);
        q.setParameter("idF", idFeria);
        q.setParameter("id", id);
        return q.getResultList().get(0);
    }
    
    /**
     * Devuelve el conjunto de todas las entidades boleta de la base de datos.
     * @param idFeria id de la feria de la que se quiere saber las boletas.
     * @return el conjunto de todas las entidades boleta de la base de datos.
     */
    public List<BoletaEntity> findAll(Long idFeria) {
        return em.createQuery("select u from BoletaEntity u where u.feria.id = "+ idFeria).getResultList();
    }

    /**
     * Crea una nueva entidad boleta.
     * post: Se creó una nueva fila en la tabla BoletaEntity.
     * @param entity entidad a ser creada.
     * @return entidad creada.
     */
    public BoletaEntity create(BoletaEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza la información de la entidad boleta que comparte el id con la 
     * entidad indicada. Se almacena la información de la entidad boleta indicada.
     * post: Se cambió la información de la entidad boleta por la indicada.
     * @param entity entidad con la nueva información.
     * @return la entidad con los datos actualizados.
     */
    public BoletaEntity update(BoletaEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina la entidad boleta con el id indicado.
     * post: Se eliminó la fila con el id indicado de la tabla BoletaEntity.
     * @param idFeria id de la feria de la que se quiere eliminar la boleta.
     * @param id de la entidad boleta a eliminar.
     */
    public void delete(Long idFeria, Long id) {
        em.remove(find(idFeria, id));
    }
    
    /**
     * Devuelve la cantidad de boletas de una feria
     * @param idFeria id de la feria de la que se quiere saber la cantidad de boletas.
     * @return numero de boletas de una feria.
     */
    public Integer count(Long idFeria) {
        TypedQuery<Integer> q = em.createQuery(
                "select count(0) from BoletaEntity u where u.feria.id = :idF group by u.feria.id"
                , Integer.class);
        q.setParameter("idF", idFeria);
        return q.getResultList().get(0);
    }
}
