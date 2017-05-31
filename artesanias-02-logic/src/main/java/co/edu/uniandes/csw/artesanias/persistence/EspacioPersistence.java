/*
 * The MIT License
 *
 * Copyright 2017 ja.espinosa12.
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
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author ja.espinosa12
 */
@Stateless
public class EspacioPersistence {
    
    /**
     * Entity Manager encargado de la persistencia de Entities
     */
    @PersistenceContext(unitName = "artesaniasPU")
    protected EntityManager em;
    
    public EspacioEntity create(EspacioEntity entity) 
    {
        em.persist(entity);
        return entity;
    }

    public EspacioEntity find(Long ciudadId, Long id) 
    {
        TypedQuery<EspacioEntity> q = em.createQuery( "SELECT R FROM EspacioEntity R WHERE R.id = :id AND R.ciudad.id = :ciudadId", EspacioEntity.class );
		q.setParameter( "id", id );
		q.setParameter( "ciudadId", ciudadId );
		EspacioEntity entity;
		try
		{
			entity = q.getSingleResult( );
		}
		catch( Exception e )
		{
			entity = null;
		}
		return entity;
    }


    public List<EspacioEntity> findAll() {
        TypedQuery<EspacioEntity> q = em.createQuery( "SELECT U FROM EspacioEntity U", EspacioEntity.class );
		return q.getResultList( );
    }

    /**
	 * 
	 */
	public List<EspacioEntity> findAllFromCiudad( Long ciudadId )
	{
		TypedQuery<EspacioEntity> q = em.createQuery( "SELECT R FROM EspacioEntity R WHERE R.ciudad.id = :ciudadId", EspacioEntity.class );
		q.setParameter( "ciudadId", ciudadId );
		return q.getResultList( );
	}
    
    public EspacioEntity update(EspacioEntity entity) {
        return em.merge(entity);
    }

    public void delete(Long id) {
        EspacioEntity entity = em.find(EspacioEntity.class, id);
        em.remove(entity);
    }
}
