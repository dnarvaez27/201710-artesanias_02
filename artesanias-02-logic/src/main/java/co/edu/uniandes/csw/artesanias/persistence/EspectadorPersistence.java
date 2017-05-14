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

import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Clase encargada de la persistencia de los Espectadores
 *
 * @author d.narvaez11
 */
@Stateless
public class EspectadorPersistence
{
	/**
	 * Entity Manager, encargado de la persistencia
	 */
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	/**
	 * Creates a new Espectador Entity in the Data Base
	 *
	 * @param entity Espectador Entity to be created in the Data Base
	 * @return The Espectador Entity created
	 */
	public EspectadorEntity create( EspectadorEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	/**
	 * Retrieves the Espectador Entity whose id matches the one given by
	 * parameter
	 *
	 * @param id Id of the Espectador entity searched
	 * @return The Espectador Entity whose id matches the one given by parameter
	 */
	public EspectadorEntity find( Long id )
	{
		return em.find( EspectadorEntity.class, id );
	}
	
	/**
	 * Retrieves all the Espectador Entities in the Data Base
	 *
	 * @return A List with all the Espectador Entities in the Data Base
	 */
	public List<EspectadorEntity> findAll( )
	{
		TypedQuery<EspectadorEntity> q = em.createQuery( "SELECT E FROM EspectadorEntity E INNER JOIN UsuarioEntity U ON E.id = U.id", EspectadorEntity.class );
		return q.getResultList( );
	}
	
	/**
	 * Updates the information from the Espectador Entity whose id matches the
	 * one given by parameter
	 *
	 * @param entity Espectador Entity with the new information
	 * @return The Espectador Entity with the new information updated.
	 */
	public EspectadorEntity update( EspectadorEntity entity )
	{
		return em.merge( entity );
	}
	
	/**
	 * Deletes the Espectador Entity which id is given by parameter
	 *
	 * @param id Id of the Espectador to delete
	 */
	public void delete( Long id )
	{
		EspectadorEntity entity = em.find( EspectadorEntity.class, id );
		em.remove( entity );
	}
}