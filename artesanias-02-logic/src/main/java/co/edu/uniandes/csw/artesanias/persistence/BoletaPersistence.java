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
     * @param id de la boleta a buscar.
     * @return la entidad boleta con el id indicado.
     */
    public BoletaEntity find(Long id) {
        return em.find(BoletaEntity.class, id);
    }

    /**
     * Devuelve el conjunto de todas las entidades boleta de la base de datos.
     * @return el conjunto de todas las entidades boleta de la base de datos.
     */
    public List<BoletaEntity> findAll() {
        return em.createQuery("select u from BoletaEntity u").getResultList();
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
     * @param id de la entidad boleta a eliminar.
     */
    public void delete(Long id) {
        em.remove(em.find(BoletaEntity.class, id));
    }
}
