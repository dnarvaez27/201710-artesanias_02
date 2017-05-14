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
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import co.edu.uniandes.csw.artesanias.persistence.ReviewPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
@RunWith( Arquillian.class )
public class ReviewLogicTest extends PersistenceTest<ReviewEntity>
{
	@Inject
	private ReviewLogic logic;
	
	private ArtesanoEntity artesano;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ReviewEntity.class.getPackage( ) )
		                 .addPackage( ReviewLogic.class.getPackage( ) )
		                 .addPackage( ReviewPersistence.class.getPackage( ) )
		
		                 .addPackage( ArtesanoEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	@Override
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM ReviewEntity " ).executeUpdate( );
		em.createQuery( "DELETE FROM ArtesanoEntity " ).executeUpdate( );
	}
	
	@Override
	protected void insertData( )
	{
		artesano = factory.manufacturePojo( ArtesanoEntity.class );
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
	public void createReview( ) throws Exception
	{
		ReviewEntity newReview = factory.manufacturePojo( ReviewEntity.class );
		newReview.setArtesano( artesano );
		
		ReviewEntity result = logic.createReview( newReview );
		Assert.assertNotNull( result );
		
		ReviewEntity retrieved = em.find( ReviewEntity.class, newReview.getId( ) );
		Assert.assertNotNull( retrieved );
		
		Assert.assertEquals( newReview.getPuntuacion( ), retrieved.getPuntuacion( ) );
		Assert.assertEquals( newReview.getComentario( ), retrieved.getComentario( ) );
		Assert.assertEquals( newReview.getArtesano( ).getId( ), retrieved.getArtesano( ).getId( ) );
		
		newReview = factory.manufacturePojo( ReviewEntity.class );
		newReview.setArtesano( artesano );
		newReview.setPuntuacion( -1F );
		try
		{
			logic.createReview( newReview );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
		
		newReview = factory.manufacturePojo( ReviewEntity.class );
		newReview.setArtesano( artesano );
		newReview.setPuntuacion( 6F );
		try
		{
			logic.createReview( newReview );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	@Test
	public void getReviews( ) throws Exception
	{
		List<ReviewEntity> list = logic.getReviews( );
		Assert.assertEquals( artesano.getReviews( ).size( ), list.size( ) );
		for( ReviewEntity review : list )
		{
			Assert.assertTrue( exists( review.getId( ) ) );
		}
	}
	
	@Test
	public void getReviewsFromArtesano( ) throws Exception
	{
		List<ReviewEntity> list = logic.getReviewsFromArtesano( artesano.getId( ) );
		Assert.assertEquals( artesano.getReviews( ).size( ), list.size( ) );
		for( ReviewEntity review : list )
		{
			Assert.assertTrue( exists( review.getId( ) ) );
			Assert.assertEquals( artesano.getId( ), review.getArtesano( ).getId( ) );
		}
	}
	
	@Test
	public void getReview( ) throws Exception
	{
		ReviewEntity review = artesano.getReviews( ).get( 0 );
		ReviewEntity retrieved = logic.getReview( artesano.getId( ), review.getId( ) );
		
		Assert.assertNotNull( retrieved );
		Assert.assertEquals( artesano.getId( ), review.getArtesano( ).getId( ) );
		Assert.assertEquals( review.getArtesano( ).getId( ), retrieved.getArtesano( ).getId( ) );
		Assert.assertEquals( review.getPuntuacion( ), retrieved.getPuntuacion( ) );
		Assert.assertEquals( review.getComentario( ), retrieved.getComentario( ) );
		
		try
		{
			logic.getReview( -1L, -1L );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	@Test
	public void updateReview( ) throws Exception
	{
		ReviewEntity review = artesano.getReviews( ).get( 0 );
		ReviewEntity upReview = factory.manufacturePojo( ReviewEntity.class );
		
		upReview.setArtesano( artesano );
		upReview.setId( review.getId( ) );
		
		logic.updateReview( upReview );
		ReviewEntity retrieved = em.find( ReviewEntity.class, review.getId( ) );
		
		Assert.assertNotNull( retrieved );
		Assert.assertEquals( upReview.getId( ), review.getId( ) );
		Assert.assertEquals( artesano.getId( ), upReview.getArtesano( ).getId( ) );
		Assert.assertEquals( upReview.getArtesano( ).getId( ), retrieved.getArtesano( ).getId( ) );
		Assert.assertEquals( upReview.getPuntuacion( ), retrieved.getPuntuacion( ) );
		Assert.assertEquals( upReview.getComentario( ), retrieved.getComentario( ) );
	}
	
	@Test
	public void deleteReview( ) throws Exception
	{
		ReviewEntity review = artesano.getReviews( ).get( 0 );
		logic.deleteReview( artesano.getId( ), review.getId( ) );
		
		Assert.assertNull( em.find( ReviewEntity.class, review.getId( ) ) );
		
		try
		{
			logic.deleteReview( -1L, -1L );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	private boolean exists( Long idReview )
	{
		for( ReviewEntity review : artesano.getReviews( ) )
		{
			if( review.getId( ).equals( idReview ) )
			{
				return true;
			}
		}
		return false;
	}
}