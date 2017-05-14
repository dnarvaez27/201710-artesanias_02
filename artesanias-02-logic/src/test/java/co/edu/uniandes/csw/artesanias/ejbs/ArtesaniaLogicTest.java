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

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.persistence.ArtesaniaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
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
public class ArtesaniaLogicTest extends PersistenceTest<ArtesaniaEntity>
{
	@Inject
	private ArtesaniaLogic logic;
	
	private ArtesanoEntity artesano;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ArtesaniaEntity.class.getPackage( ) )
		                 .addPackage( ArtesaniaLogic.class.getPackage( ) )
		                 .addPackage( ArtesaniaPersistence.class.getPackage( ) )
		
		                 .addPackage( ArtesanoEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	@Override
	protected void clearData( )
	{
		data.clear( );
		
		em.createQuery( "DELETE FROM ArtesaniaEntity " ).executeUpdate( );
		em.createQuery( "DELETE FROM ArtesanoEntity " ).executeUpdate( );
	}
	
	@Override
	protected void insertData( )
	{
		artesano = factory.manufacturePojo( ArtesanoEntity.class );
		em.persist( artesano );
		
		List<ArtesaniaEntity> artesanias = new LinkedList<>( );
		
		for( int i = 0; i < 10; i++ )
		{
			ArtesaniaEntity entity = factory.manufacturePojo( ArtesaniaEntity.class );
			entity.setArtesano( artesano );
			em.persist( entity );
			
			artesanias.add( entity );
		}
		artesano.setArtesanias( artesanias );
	}
	
	@Test
	public void createArtesania( ) throws Exception
	{
		ArtesaniaEntity newArtesania = factory.manufacturePojo( ArtesaniaEntity.class );
		newArtesania.setArtesano( artesano );
		
		ArtesaniaEntity result = logic.createArtesania( newArtesania );
		Assert.assertNotNull( result );
		
		ArtesaniaEntity retrieved = em.find( ArtesaniaEntity.class, newArtesania.getId( ) );
		Assert.assertNotNull( retrieved );
		
		Assert.assertEquals( newArtesania.getNombre( ), retrieved.getNombre( ) );
		Assert.assertEquals( newArtesania.getImagen( ), retrieved.getImagen( ) );
		Assert.assertEquals( newArtesania.getArtesano( ).getId( ), retrieved.getArtesano( ).getId( ) );
		
		newArtesania = factory.manufacturePojo( ArtesaniaEntity.class );
		newArtesania.setArtesano( artesano );
		newArtesania.setNombre( "" );
		try
		{
			logic.createArtesania( newArtesania );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debería fallar
		}
	}
	
	@Test
	public void getArtesanias( ) throws Exception
	{
		List<ArtesaniaEntity> list = logic.getArtesanias( );
		Assert.assertEquals( artesano.getArtesanias( ).size( ), list.size( ) );
		for( ArtesaniaEntity artesania : list )
		{
			Assert.assertTrue( exists( artesania.getId( ) ) );
		}
	}
	
	@Test
	public void getArtesaniasFromArtesano( ) throws Exception
	{
		List<ArtesaniaEntity> list = logic.getArtesaniasFromArtesano( artesano.getId( ) );
		Assert.assertEquals( artesano.getArtesanias( ).size( ), list.size( ) );
		for( ArtesaniaEntity artesania : list )
		{
			Assert.assertTrue( exists( artesania.getId( ) ) );
			Assert.assertEquals( artesano.getId( ), artesania.getArtesano( ).getId( ) );
		}
	}
	
	@Test
	public void getArtesania( ) throws Exception
	{
		ArtesaniaEntity artesania = artesano.getArtesanias( ).get( 0 );
		ArtesaniaEntity retrieved = logic.getArtesania( artesano.getId( ), artesania.getId( ) );
		
		Assert.assertNotNull( retrieved );
		Assert.assertEquals( artesania.getNombre( ), retrieved.getNombre( ) );
		Assert.assertEquals( artesania.getImagen( ), retrieved.getImagen( ) );
		Assert.assertEquals( artesano.getId( ), artesania.getArtesano( ).getId( ) );
		Assert.assertEquals( artesania.getArtesano( ).getId( ), retrieved.getArtesano( ).getId( ) );
		
		try
		{
			logic.getArtesania( -1L, -1L );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debería fallar
		}
	}
	
	@Test
	public void updateArtesania( ) throws Exception
	{
		ArtesaniaEntity artesania = artesano.getArtesanias( ).get( 0 );
		ArtesaniaEntity upArtesania = factory.manufacturePojo( ArtesaniaEntity.class );
		
		upArtesania.setArtesano( artesano );
		upArtesania.setId( artesania.getId( ) );
		
		logic.updateArtesania( upArtesania );
		ArtesaniaEntity retrieved = em.find( ArtesaniaEntity.class, artesania.getId( ) );
		
		Assert.assertNotNull( retrieved );
		Assert.assertEquals( upArtesania.getNombre( ), retrieved.getNombre( ) );
		Assert.assertEquals( upArtesania.getImagen( ), retrieved.getImagen( ) );
		Assert.assertEquals( artesano.getId( ), upArtesania.getArtesano( ).getId( ) );
		Assert.assertEquals( upArtesania.getArtesano( ).getId( ), retrieved.getArtesano( ).getId( ) );
	}
	
	@Test
	public void deleteArtesania( ) throws Exception
	{
		ArtesaniaEntity artesania = artesano.getArtesanias( ).get( 0 );
		logic.deleteArtesania( artesano.getId( ), artesania.getId( ) );
		
		Assert.assertNull( em.find( ArtesaniaEntity.class, artesania.getId( ) ) );
		
		try
		{
			logic.deleteArtesania( -1L, -1L );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debería fallar
		}
	}
	
	private boolean exists( Long idArtesania )
	{
		for( ArtesaniaEntity artesania : artesano.getArtesanias( ) )
		{
			if( artesania.getId( ).equals( idArtesania ) )
			{
				return true;
			}
		}
		return false;
	}
}