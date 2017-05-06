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
import java.util.List;

/**
 * @author d.narvaez11
 */
@RunWith( Arquillian.class )
public class ArtesanoPersistenceTest extends PersistenceTest<ArtesanoEntity>
{
	@Inject
	private ArtesanoPersistence persistence;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ArtesanoEntity.class.getPackage( ) )
		                 .addPackage( ArtesanoPersistence.class.getPackage( ) )
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM ArtesanoEntity " ).executeUpdate( );
	}
	
	protected void insertData( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		for( int i = 0; i < 10; i++ )
		{
			ArtesanoEntity entity = factory.manufacturePojo( ArtesanoEntity.class );
			em.persist( entity );
			data.add( entity );
		}
	}
	
	@Test
	public void create( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		ArtesanoEntity newEntity = factory.manufacturePojo( ArtesanoEntity.class );
		
		ArtesanoEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		ArtesanoEntity entity = em.find( ArtesanoEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
	}
	
	@Test
	public void find( )
	{
		ArtesanoEntity entity = data.get( 0 );
		ArtesanoEntity newEntity = persistence.find( entity.getId( ) );
		Assert.assertNotNull( newEntity );
		Assert.assertEquals( entity.getNombre( ), newEntity.getNombre( ) );
	}
	
	@Test
	public void findAll( )
	{
		List<ArtesanoEntity> finded = persistence.findAll( );
		Assert.assertEquals( data.size( ), finded.size( ) );
		for( ArtesanoEntity artesanoEntity : finded )
		{
			boolean found = false;
			for( ArtesanoEntity entity : data )
			{
				if( artesanoEntity.getId( ).equals( entity.getId( ) ) )
				{
					found = true;
					break;
				}
			}
			Assert.assertTrue( found );
		}
	}
	
	@Test
	public void update( )
	{
		ArtesanoEntity entity = data.get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		ArtesanoEntity upEntity = factory.manufacturePojo( ArtesanoEntity.class );
		upEntity.setId( entity.getId( ) );
		
		persistence.update( upEntity );
		
		ArtesanoEntity resp = em.find( ArtesanoEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getNombre( ), resp.getNombre( ) );
	}
	
	@Test
	public void delete( )
	{
		ArtesanoEntity entity = data.get( 0 );
		persistence.delete( entity.getId( ) );
		ArtesanoEntity deleted = em.find( ArtesanoEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
}