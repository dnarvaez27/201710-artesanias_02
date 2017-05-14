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
public class EspectadorPersistenceTest extends PersistenceTest<EspectadorEntity>
{
	@Inject
	private EspectadorPersistence persistence;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( EspectadorEntity.class.getPackage( ) )
		                 .addPackage( EspectadorPersistence.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	@Override
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM EspectadorEntity " ).executeUpdate( );
	}
	
	@Override
	protected void insertData( )
	{
		for( int i = 0; i < 10; i++ )
		{
			EspectadorEntity entity = factory.manufacturePojo( EspectadorEntity.class );
			em.persist( entity );
			data.add( entity );
		}
	}
	
	@Test
	public void create( ) throws Exception
	{
		EspectadorEntity newEntity = factory.manufacturePojo( EspectadorEntity.class );
		
		EspectadorEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		EspectadorEntity retrieved = em.find( EspectadorEntity.class, newEntity.getId( ) );
		Assert.assertNotNull( retrieved );
	}
	
	@Test
	public void find( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		EspectadorEntity returned = persistence.find( entity.getId( ) );
		
		Assert.assertNotNull( returned );
		Assert.assertEquals( entity.getCorreo( ), returned.getCorreo( ) );
		Assert.assertEquals( entity.getEdad( ), returned.getEdad( ) );
		Assert.assertEquals( entity.getContrasena( ), returned.getContrasena( ) );
		Assert.assertEquals( entity.getFoto( ), returned.getFoto( ) );
	}
	
	@Test
	public void findAll( ) throws Exception
	{
		List<EspectadorEntity> list = persistence.findAll( );
		Assert.assertEquals( data.size( ), list.size( ) );
		for( EspectadorEntity espectador : list )
		{
			Assert.assertTrue( exists( espectador.getId( ) ) );
		}
	}
	
	@Test
	public void update( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		
		EspectadorEntity upEntity = factory.manufacturePojo( EspectadorEntity.class );
		upEntity.setId( entity.getId( ) );
		
		EspectadorEntity returned = persistence.update( upEntity );
		Assert.assertNotNull( returned );
		
		EspectadorEntity retrieved = em.find( EspectadorEntity.class, entity.getId( ) );
		Assert.assertNotNull( retrieved );
		
		Assert.assertEquals( upEntity.getCorreo( ), retrieved.getCorreo( ) );
		Assert.assertEquals( upEntity.getEdad( ), retrieved.getEdad( ) );
		Assert.assertEquals( upEntity.getContrasena( ), retrieved.getContrasena( ) );
		Assert.assertEquals( upEntity.getFoto( ), retrieved.getFoto( ) );
	}
	
	@Test
	public void delete( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		persistence.delete( entity.getId( ) );
		EspectadorEntity deleted = em.find( EspectadorEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
	
	private boolean exists( Long idEspectador )
	{
		for( EspectadorEntity espectador : data )
		{
			if( espectador.getId( ).equals( idEspectador ) )
			{
				return true;
			}
		}
		return false;
	}
}