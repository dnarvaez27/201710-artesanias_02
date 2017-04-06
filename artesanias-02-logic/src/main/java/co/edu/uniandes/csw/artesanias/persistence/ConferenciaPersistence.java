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

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;

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
public class ConferenciaPersistence
{
	@PersistenceContext( unitName = "artesaniasPU" )
	protected EntityManager em;
	
	public ConferenciaEntity find( Long idConferencia, Long id )
	{
            Query q = em.createQuery( "select u from ConferenciaEntity u WHERE u.id=idConferencia AND u.feria.id=id" );
		q.setParameter( "id", id );
		q.setParameter( "idConferencia", idConferencia );
		List<ConferenciaEntity> res = q.getResultList( );
		return res.size( ) > 0 ? res.get( 0 ) : null;
	}
	public ConferenciaEntity findFromSalon( Long idSalon, Long id )
	{
            Query q = em.createQuery( "select u from ConferenciaEntity u WHERE u.id=idConferencia AND u.salon.id=idSalon" );
		q.setParameter( "id", id );
		q.setParameter( "idConferencia", idSalon );
		List<ConferenciaEntity> res = q.getResultList( );
		return res.size( ) > 0 ? res.get( 0 ) : null;
	}
	public List<ConferenciaEntity> findAll( )
	{
		Query q = em.createQuery( "select u from ConferenciaEntity u" );
		return q.getResultList( );
	}
	
	public ConferenciaEntity create( ConferenciaEntity entity )
	{
		em.persist( entity );
		return entity;
	}
	
	public ConferenciaEntity update( ConferenciaEntity entity )
	{
		return em.merge( entity );
	}
	
	public void delete( Long id )
	{
		ConferenciaEntity entity = em.find( ConferenciaEntity.class, id );
		em.remove( entity );
	}

    public List<ConferenciaEntity> findAllFromSalon(Long id) {
        
       TypedQuery<ConferenciaEntity> q = em.createQuery( "SELECT A FROM ConferenciaEntity A WHERE A.salon.id = :salonId", ConferenciaEntity.class );
		q.setParameter( "salonId", id );
		return q.getResultList( );
    }

    public List<ConferenciaEntity> findAllFromFeria(Long feriaId) {
        TypedQuery<ConferenciaEntity> q = em.createQuery("SELECT A FROM ConferenciaEntity A WHERE A.feria.id = :feriaId",  ConferenciaEntity.class);
		q.setParameter( "feriaId", feriaId );
		return q.getResultList( );
    }
}