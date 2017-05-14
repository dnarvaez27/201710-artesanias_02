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
import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import co.edu.uniandes.csw.artesanias.persistence.ArtesanoPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

/**
 * @author d.narvaez11
 */
@RunWith( Arquillian.class )
public class ArtesanoLogicTest extends PersistenceTest<ArtesanoEntity>
{
	@Inject
	private ArtesanoLogic logic;
	
	private CiudadEntity ciudad;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ArtesanoEntity.class.getPackage( ) )
		                 .addPackage( ArtesanoLogic.class.getPackage( ) )
		                 .addPackage( ArtesanoPersistence.class.getPackage( ) )
		
		                 .addPackage( CiudadEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	@Override
	protected void clearData( )
	{
		data.clear( );
		
		em.createQuery( "DELETE FROM ArtesanoEntity " ).executeUpdate( );
		em.createQuery( "DELETE FROM CiudadEntity " ).executeUpdate( );
	}
	
	@Override
	protected void insertData( )
	{
		ciudad = factory.manufacturePojo( CiudadEntity.class );
		em.persist( ciudad );
		
		for( int i = 0; i < 10; i++ )
		{
			ArtesanoEntity entity = factory.manufacturePojo( ArtesanoEntity.class );
			entity.setCiudad( ciudad );
			em.persist( entity );
			data.add( entity );
		}
	}
	
	@Test
	public void createArtesano( ) throws Exception
	{
		ArtesanoEntity entity = factory.manufacturePojo( ArtesanoEntity.class );
		entity.setCiudad( ciudad );
		
		ArtesanoEntity result = logic.createArtesano( entity );
		Assert.assertNotNull( result );
		
		ArtesanoEntity retrieved = em.find( ArtesanoEntity.class, entity.getId( ) );
		Assert.assertNotNull( retrieved );
		
		Assert.assertEquals( entity.getNombre( ), retrieved.getNombre( ) );
		Assert.assertEquals( entity.getIdentificacion( ), retrieved.getIdentificacion( ) );
		Assert.assertEquals( entity.getTelefono( ), retrieved.getTelefono( ) );
		Assert.assertEquals( entity.getImage( ), retrieved.getImage( ) );
		
		entity = factory.manufacturePojo( ArtesanoEntity.class );
		entity.setCiudad( ciudad );
		entity.setNombre( "" );
		try
		{
			logic.createArtesano( entity );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
		
		entity = factory.manufacturePojo( ArtesanoEntity.class );
		entity.setCiudad( ciudad );
		entity.setIdentificacion( "" );
		try
		{
			logic.createArtesano( entity );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
		
		entity = factory.manufacturePojo( ArtesanoEntity.class );
		entity.setIdentificacion( "" );
		try
		{
			logic.createArtesano( entity );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	@Test
	public void getArtesanos( ) throws Exception
	{
		List<ArtesanoEntity> artesanos = logic.getArtesanos( );
		Assert.assertEquals( data.size( ), artesanos.size( ) );
		
		for( ArtesanoEntity artesano : artesanos )
		{
			Assert.assertTrue( exists( artesano.getId( ) ) );
		}
	}
	
	@Test
	public void getArtesano( ) throws Exception
	{
		ArtesanoEntity artesano = data.get( 0 );
		ArtesanoEntity retrieved = logic.getArtesano( artesano.getId( ) );
		
		Assert.assertNotNull( retrieved );
		Assert.assertEquals( artesano.getNombre( ), retrieved.getNombre( ) );
		Assert.assertEquals( artesano.getIdentificacion( ), retrieved.getIdentificacion( ) );
		Assert.assertEquals( artesano.getTelefono( ), retrieved.getTelefono( ) );
		Assert.assertEquals( artesano.getImage( ), retrieved.getImage( ) );
		
		try
		{
			logic.getArtesano( -1L );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	@Test
	public void updateArtesano( ) throws Exception
	{
		ArtesanoEntity artesano = data.get( 0 );
		ArtesanoEntity upArtesano = factory.manufacturePojo( ArtesanoEntity.class );
		
		upArtesano.setId( artesano.getId( ) );
		upArtesano.setCiudad( ciudad );
		
		logic.updateArtesano( upArtesano );
		ArtesanoEntity retrieved = em.find( ArtesanoEntity.class, artesano.getId( ) );
		
		Assert.assertEquals( upArtesano.getNombre( ), retrieved.getNombre( ) );
		Assert.assertEquals( upArtesano.getTelefono( ), retrieved.getTelefono( ) );
		Assert.assertEquals( upArtesano.getImage( ), retrieved.getImage( ) );
		Assert.assertEquals( upArtesano.getIdentificacion( ), retrieved.getIdentificacion( ) );
	}
	
	@Test
	public void deleteArtesano( ) throws Exception
	{
		ArtesanoEntity artesano = data.get( 0 );
		logic.deleteArtesano( artesano.getId( ) );
		
		Assert.assertNull( em.find( ArtesanoEntity.class, artesano.getId( ) ) );
	}
	
	private boolean exists( Long idArtesano )
	{
		for( ArtesanoEntity artesanoEntity : data )
		{
			if( artesanoEntity.getId( ).equals( idArtesano ) )
			{
				return true;
			}
		}
		return false;
	}
}
