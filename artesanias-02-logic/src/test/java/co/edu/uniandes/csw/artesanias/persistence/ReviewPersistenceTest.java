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
	
	private ArtesanoEntity artesano1;
	
	private ArtesanoEntity artesano2;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ReviewEntity.class.getPackage( ) )
		                 .addPackage( ReviewPersistence.class.getPackage( ) )
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM ReviewEntity" ).executeUpdate( );
	}
	
	protected void insertData( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		
		artesano1 = artesano1 == null ? factory.manufacturePojo( ArtesanoEntity.class ) : artesano1;
		artesano2 = artesano2 == null ? factory.manufacturePojo( ArtesanoEntity.class ) : artesano2;
		
		List<ReviewEntity> reviews1 = new LinkedList<>( );
		List<ReviewEntity> reviews2 = new LinkedList<>( );
		
		for( int i = 0; i < 10; i++ )
		{
			ReviewEntity entity = factory.manufacturePojo( ReviewEntity.class );
			entity.setArtesano( i % 2 == 0 ? artesano1 : artesano2 );
			
			em.persist( entity );
			data.add( entity );
			
			if( i % 2 == 0 )
			{
				reviews1.add( entity );
			}
			else
			{
				reviews2.add( entity );
			}
		}
		artesano1.setReviews( reviews1 );
		artesano2.setReviews( reviews2 );
	}
	
	@Test
	public void create( ) throws Exception
	{
		PodamFactory factory = new PodamFactoryImpl( );
		
		ReviewEntity newEntity = factory.manufacturePojo( ReviewEntity.class );
		artesano1 = factory.manufacturePojo( ArtesanoEntity.class );
		artesano2 = factory.manufacturePojo( ArtesanoEntity.class );
		
		ReviewEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		ReviewEntity entity = em.find( ReviewEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
	}
	
	@Test
	public void find( ) throws Exception
	{
		ReviewEntity entity = artesano1.getReviews( ).get( 0 );
		ReviewEntity newEntity = persistence.find( artesano1.getId( ), entity.getId( ) );
		Assert.assertNotNull( newEntity );
		Assert.assertEquals( entity.getComentario( ), newEntity.getComentario( ) );
	}
	
	@Test
	public void findAll( ) throws Exception
	{
		List<ReviewEntity> finded = persistence.findAll( );
		Assert.assertEquals( data.size( ), finded.size( ) );
		for( ReviewEntity currentEntity : finded )
		{
			boolean found = false;
			for( ReviewEntity entity : data )
			{
				if( currentEntity.getId( ).equals( entity.getId( ) ) )
				{
					found = true;
					break;
				}
			}
			Assert.assertTrue( found );
		}
	}
	
	@Test
	public void findAllFromArtesano( ) throws Exception
	{
		List<ReviewEntity> reviews = persistence.findAllFromArtesano( artesano1.getId( ) );
		Assert.assertEquals( artesano1.getReviews( ).size( ), reviews.size( ) );
		for( ReviewEntity review : reviews )
		{
			boolean found = false;
			for( ReviewEntity aReview : artesano1.getReviews( ) )
			{
				if( review.getId( ).equals( aReview.getId( ) ) )
				{
					found = true;
					break;
				}
			}
			Assert.assertTrue( found );
		}
	}
	
	@Test
	public void update( ) throws Exception
	{
		ReviewEntity entity = data.get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		ReviewEntity upEntity = factory.manufacturePojo( ReviewEntity.class );
		upEntity.setId( entity.getId( ) );
		
		persistence.update( upEntity );
		
		ReviewEntity resp = em.find( ReviewEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getComentario( ), resp.getComentario( ) );
	}
	
	@Test
	public void delete( ) throws Exception
	{
		ReviewEntity entity = data.get( 0 );
		persistence.delete( entity.getId( ) );
		ReviewEntity deleted = em.find( ReviewEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
}