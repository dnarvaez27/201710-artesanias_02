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

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
@RunWith( Arquillian.class )
public class ReviewPersistenceTest extends PersistenceTest<ReviewEntity>
{
	@Inject
	private ReviewPersistence persistence;
	
	private ArtesanoEntity artesano;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ReviewEntity.class.getPackage( ) )
		                 .addPackage( ReviewPersistence.class.getPackage( ) )
		
		                 .addPackage( ArtesanoEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM ReviewEntity" ).executeUpdate( );
		em.createQuery( "DELETE FROM ArtesanoEntity" ).executeUpdate( );
	}
	
	protected void insertData( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		artesano = factory.manufacturePojo( ArtesanoEntity.class );
		artesano.setId( 1L );
		em.persist( artesano );
		
		List<ReviewEntity> reviews = new LinkedList<>( );
		
		for( int i = 0; i < 10; i++ )
		{
			ReviewEntity entity = factory.manufacturePojo( ReviewEntity.class );
			entity.setArtesano( artesano );
			em.persist( entity );
			
			reviews.add( entity );
		}
		artesano.setReviews( reviews );
	}
	
	@Test
	public void create( ) throws Exception
	{
		ReviewEntity newEntity = factory.manufacturePojo( ReviewEntity.class );
		newEntity.setArtesano( artesano );
		
		ReviewEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		ReviewEntity entity = em.find( ReviewEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
		
		Assert.assertEquals( result.getComentario( ), entity.getComentario( ) );
		Assert.assertEquals( result.getPuntuacion( ), entity.getPuntuacion( ) );
		Assert.assertEquals( result.getArtesano( ).getId( ), entity.getArtesano( ).getId( ) );
		Assert.assertEquals( result.hashCode( ), entity.hashCode( ) );
		Assert.assertTrue( result.equals( entity ) );
	}
	
	@Test
	public void find( ) throws Exception
	{
		ReviewEntity entity = artesano.getReviews( ).get( 0 );
		ReviewEntity newEntity = persistence.find( artesano.getId( ), entity.getId( ) );
		
		Assert.assertNotNull( newEntity );
		Assert.assertEquals( entity.getComentario( ), newEntity.getComentario( ) );
		Assert.assertEquals( entity.getPuntuacion( ), newEntity.getPuntuacion( ) );
		Assert.assertEquals( entity.getArtesano( ).getId( ), newEntity.getArtesano( ).getId( ) );
		Assert.assertEquals( entity.hashCode( ), newEntity.hashCode( ) );
		Assert.assertTrue( entity.equals( newEntity ) );
		
		Assert.assertNull( persistence.find( -1L, -1L ) );
	}
	
	private boolean existsReview( Long idReview )
	{
		for( ReviewEntity entity : artesano.getReviews( ) )
		{
			if( idReview.equals( entity.getId( ) ) )
			{
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void findAll( ) throws Exception
	{
		List<ReviewEntity> finded = persistence.findAll( );
		Assert.assertEquals( artesano.getReviews( ).size( ), finded.size( ) );
		for( ReviewEntity currentReview : finded )
		{
			Assert.assertTrue( existsReview( currentReview.getId( ) ) );
		}
	}
	
	@Test
	public void findAllFromArtesano( ) throws Exception
	{
		List<ReviewEntity> reviews = persistence.findAllFromArtesano( artesano.getId( ) );
		Assert.assertEquals( artesano.getReviews( ).size( ), reviews.size( ) );
		for( ReviewEntity review : reviews )
		{
			Assert.assertTrue( existsReview( review.getId( ) ) );
		}
	}
	
	@Test
	public void update( ) throws Exception
	{
		ReviewEntity entity = artesano.getReviews( ).get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		ReviewEntity upEntity = factory.manufacturePojo( ReviewEntity.class );
		upEntity.setId( entity.getId( ) );
		upEntity.setArtesano( artesano );
		
		persistence.update( upEntity );
		
		ReviewEntity resp = em.find( ReviewEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getComentario( ), resp.getComentario( ) );
		Assert.assertEquals( upEntity.getPuntuacion( ), resp.getPuntuacion( ) );
		Assert.assertEquals( upEntity.getArtesano( ).getId( ), resp.getArtesano( ).getId( ) );
		Assert.assertEquals( upEntity.hashCode( ), resp.hashCode( ) );
		Assert.assertTrue( upEntity.equals( resp ) );
	}
	
	@Test
	public void delete( ) throws Exception
	{
		ReviewEntity entity = artesano.getReviews( ).get( 0 );
		persistence.delete( entity.getId( ) );
		ReviewEntity deleted = em.find( ReviewEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
}