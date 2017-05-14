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

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Clase encargada de la persistencia de las Artesanias
 *
 * @author d.narvaez11
 */
@Stateless
public class ArtesaniaPersistence
{
	/**
	 * Entity Manager encargado de la persistencia de Entities
	 */
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Artesania Entity in the Data Base
	 *
	 * @param entity Artesania Entity to be created in the Data Base
	 * @return The Artesania Entity created
	 */
	public ArtesaniaEntity create( ArtesaniaEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Artesania Entity whose id matches the one given by parameter
	 *
	 * @param id Id of the Artesania entity searched
	 * @return The Artesania Entity whose id matches the one given by parameter
	 */
	public ArtesaniaEntity find( Long artesanoId, Long id )
	{
		TypedQuery<ArtesaniaEntity> q = em.createQuery( "SELECT A FROM ArtesaniaEntity A WHERE A.id = :id AND A.artesano.id = :artesanoID", ArtesaniaEntity.class );
		q.setParameter( "id", id );
		q.setParameter( "artesanoID", artesanoId );
		ArtesaniaEntity entity;
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
	 * Retrieves all the Artesania Entities in the Data Base
	 *
	 * @return A List with all the Artesania Entities in the Data Base
	 */
	public List<ArtesaniaEntity> findAll( )
	{
		TypedQuery<ArtesaniaEntity> q = em.createQuery( "SELECT U FROM ArtesaniaEntity U", ArtesaniaEntity.class );
		return q.getResultList( );
	}
	
	/**
	 * Retrieves the Artesanias Entities whose Artesano id matches the one given by parameter
	 *
	 * @param id Artesano Id whose Artesanias are wanted
	 * @return List of the Artesano's Artesanias whose id matches the one given by parameter
	 */
	public List<ArtesaniaEntity> findAllFromArtesano( Long id )
	{
		TypedQuery<ArtesaniaEntity> q = em.createQuery( "SELECT A FROM ArtesaniaEntity A WHERE A.artesano.id = :artesanoId", ArtesaniaEntity.class );
		q.setParameter( "artesanoId", id );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Artesania Entity whose id matches the one given by parameter
	 *
	 * @param entity Artesania Entity with the new information
	 * @return The Artesania Entity with the new information updated.
	 */
	public ArtesaniaEntity update( ArtesaniaEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Artesania Entity which id is given by parameter
	 *
	 * @param id Id of the Artesania to delete
	 */
	public void delete( Long id )
	{
		ArtesaniaEntity entity = em.find( ArtesaniaEntity.class, id );
		em.remove( entity );
	}
}