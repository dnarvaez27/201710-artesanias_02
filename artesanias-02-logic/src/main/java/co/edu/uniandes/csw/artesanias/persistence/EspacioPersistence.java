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

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Persistencia de la entidad espacio.
 * @author Miller
 */
@Stateless
public class EspacioPersistence {
    
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
     * Devuelve la entidad espacio con el id indicado.
     * @param id de la espacio a buscar.
     * @return la entidad espacio con el id indicado.
     */
    public EspacioEntity find(Long id) {
        return em.find(EspacioEntity.class, id);
    }
    
    /**
     * Devuelve la entidad espacio con el id indicado.
     * @param id de la espacio a buscar.
     * @return la entidad espacio con el id indicado.
     */
    public EspacioEntity find(Long id, Long idCiudad) {
        TypedQuery<EspacioEntity> q= em.createQuery(
                "select u from EspacioEntity u where u.ciudad.id = :idC and u.id = :id"
                , EspacioEntity.class);
        q.setParameter("idC", idCiudad);
        q.setParameter("id", id);
        List<EspacioEntity> r = q.getResultList();
        System.out.println(r.size());
        return r.isEmpty() ? null : r.get(0);
    }

    /**
     * Devuelve el conjunto de todas las entidades espacio de la base de datos.
     * @return el conjunto de todas las entidades espacio de la base de datos.
     */
    public List<EspacioEntity> findAll(Long idCiudad) {
        return em.createQuery("select u from EspacioEntity u where u.ciudad.id = " + idCiudad).getResultList();
    }

    /**
     * Crea una nueva entidad espacio.
     * post: Se creó una nueva fila en la tabla EspacioEntity.
     * @param entity entidad a ser creada.
     * @return entidad creada.
     */
    public EspacioEntity create(EspacioEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza la información de la entidad espacio que comparte el id con la 
     * entidad indicada. Se almacena la información de la entidad espacio indicada.
     * post: Se cambió la información de la entidad espacio por la indicada.
     * @param entity entidad con la nueva información.
     * @return la entidad con los datos actualizados.
     */
    public EspacioEntity update(EspacioEntity entity) {
        return em.merge(entity);
    }

    /**
     * Elimina la entidad espacio con el id indicado.
     * post: Se eliminó la fila con el id indicado de la tabla EspacioEntity.
     * @param id de la entidad espacio a eliminar.
     */
    public void delete(Long id) {
        em.remove(em.find(EspacioEntity.class, id));
    }
}
