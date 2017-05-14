/*
 * The MIT License
 *
 * Copyright 2017 d.narvaez11.
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

import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Clase encargada de la persistnecia de los Reviews
 *
 * @author d.narvaez11
 */
@Stateless
public class ReviewPersistence
{
	/**
	 * Entity Manager, encargado de la persistencia
	 */
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Review Entity in the Data Base
	 *
	 * @param entity Review Entity to be created in the Data Base
	 * @return The Review Entity created
	 */
	public ReviewEntity create( ReviewEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Review Entity whose id matches the one given by parameter
	 *
	 * @param id Id of the Review entity searched
	 * @return The Review Entity whose id matches the one given by parameter
	 */
	public ReviewEntity find( Long artesanoId, Long id )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "SELECT R FROM ReviewEntity R WHERE R.id = :id AND R.artesano.id = :artesanoId", ReviewEntity.class );
		q.setParameter( "id", id );
		q.setParameter( "artesanoId", artesanoId );
		ReviewEntity entity;
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
	
	/**
	 * Retrieves all the Review Entities in the Data Base
	 *
	 * @return A List with all the Review Entities in the Data Base
	 */
	public List<ReviewEntity> findAll( )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "SELECT U FROM ReviewEntity U", ReviewEntity.class );
		return q.getResultList( );
	}
	
	/**
	 * Retrieves all the Review from the Artesano whose id matches the one given by parameter
	 *
	 * @param artesanoId Id of the Artesano, whose reviews are returned
	 * @return List of Reviews from the Artesano whose id matches the one given by parameter
	 */
	public List<ReviewEntity> findAllFromArtesano( Long artesanoId )
	{
		TypedQuery<ReviewEntity> q = em.createQuery( "SELECT R FROM ReviewEntity R WHERE R.artesano.id = :artesanoId", ReviewEntity.class );
		q.setParameter( "artesanoId", artesanoId );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Review Entity whose id matches the one given by parameter
	 *
	 * @param entity Review Entity with the new information
	 * @return The Review Entity with the new information updated.
	 */
	public ReviewEntity update( ReviewEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Review Entity which id is given by parameter
	 *
	 * @param id Id of the Review to delete
	 */
	public void delete( Long id )
	{
		ReviewEntity entity = em.find( ReviewEntity.class, id );
		em.remove( entity );
	}
}