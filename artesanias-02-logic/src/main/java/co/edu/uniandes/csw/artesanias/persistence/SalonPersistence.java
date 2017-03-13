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

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author ia.salazar
 */
@Stateless
public class SalonPersistence
{
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	public SalonEntity find( Long id )
	{
		return em.find( SalonEntity.class, id );
	}
	
	public List<SalonEntity> findAll( )
	{
		Query q = em.createQuery( "select u from SalonEntity u" );
		return q.getResultList( );
	}
	
	public List<SalonEntity> findAllFromPabellon( Long pabellonId )
	{
		TypedQuery<SalonEntity> q = em.createQuery( "SELECT S FROM SalonEntity S WHERE S.pabellon.id = :pabellonId", SalonEntity.class );
		q.setParameter( "pabellonId", pabellonId );
		return q.getResultList( );
	}
	
	public SalonEntity create( SalonEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	public SalonEntity update( SalonEntity entity )
	{
		return em.merge( entity );
	}
	
	public void delete( Long id )
	{
		SalonEntity entity = em.find( SalonEntity.class, id );
		em.remove( entity );
	}
}