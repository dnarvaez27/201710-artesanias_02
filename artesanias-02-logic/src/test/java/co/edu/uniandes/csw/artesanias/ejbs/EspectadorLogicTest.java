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

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.persistence.EspectadorPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
@RunWith( Arquillian.class )
public class EspectadorLogicTest extends PersistenceTest<EspectadorEntity>
{
	@Inject
	private EspectadorLogic logic;
	
	private FeriaEntity feria;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( EspectadorEntity.class.getPackage( ) )
		                 .addPackage( EspectadorLogic.class.getPackage( ) )
		                 .addPackage( EspectadorPersistence.class.getPackage( ) )
		
		                 .addPackage( BoletaEntity.class.getPackage( ) )
		
		                 .addPackage( FeriaEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	@Override
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM BoletaEntity " ).executeUpdate( );
		em.createQuery( "DELETE FROM EspectadorEntity " ).executeUpdate( );
	}
	
	@Override
	protected void insertData( )
	{
		feria = factory.manufacturePojo( FeriaEntity.class );
		feria.setInicio( toDate( 2016, 10, 5 ) );
		feria.setFin( toDate( 2017, 5, 9 ) );
		feria.setTotalBoletas( 10 );
		em.persist( feria );
		
		for( int i = 0; i < 10; i++ )
		{
			EspectadorEntity entity = factory.manufacturePojo( EspectadorEntity.class );
			entity.setBoletas( assignBoletas( entity ) );
			
			em.persist( entity );
			data.add( entity );
		}
	}
	
	private List<BoletaEntity> assignBoletas( EspectadorEntity espectador )
	{
		List<BoletaEntity> boletas = new LinkedList<>( );
		for( int i = 0; i < 5; i++ )
		{
			BoletaEntity entity = factory.manufacturePojo( BoletaEntity.class );
			entity.setEspectador( espectador );
			
			em.persist( entity );
			
			boletas.add( entity );
		}
		return boletas;
	}
	
	@Test
	public void createEspectador( ) throws Exception
	{
		EspectadorEntity newEntity = factory.manufacturePojo( EspectadorEntity.class );
		
		EspectadorEntity result = logic.createEspectador( newEntity );
		Assert.assertNotNull( result );
		
		EspectadorEntity retrieved = em.find( EspectadorEntity.class, newEntity.getId( ) );
		Assert.assertNotNull( retrieved );
		
		newEntity = factory.manufacturePojo( EspectadorEntity.class );
		newEntity.setCorreo( "" );
		try
		{
			logic.createEspectador( newEntity );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
		
		newEntity = factory.manufacturePojo( EspectadorEntity.class );
		newEntity.setContrasena( "" );
		try
		{
			logic.createEspectador( newEntity );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	@Test
	public void getEspectadors( ) throws Exception
	{
		List<EspectadorEntity> list = logic.getEspectadors( );
		Assert.assertEquals( data.size( ), list.size( ) );
		for( EspectadorEntity espectador : list )
		{
			Assert.assertTrue( exists( espectador.getId( ) ) );
		}
	}
	
	@Test
	public void getEspectador( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		EspectadorEntity returned = logic.getEspectador( entity.getId( ) );
		
		Assert.assertNotNull( returned );
		Assert.assertEquals( entity.getCorreo( ), returned.getCorreo( ) );
		Assert.assertEquals( entity.getEdad( ), returned.getEdad( ) );
		Assert.assertEquals( entity.getContrasena( ), returned.getContrasena( ) );
		Assert.assertEquals( entity.getFoto( ), returned.getFoto( ) );
	}
	
	@Test
	public void updateEspectador( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		
		EspectadorEntity upEntity = factory.manufacturePojo( EspectadorEntity.class );
		upEntity.setId( entity.getId( ) );
		
		EspectadorEntity returned = logic.updateEspectador( upEntity );
		Assert.assertNotNull( returned );
		
		EspectadorEntity retrieved = em.find( EspectadorEntity.class, entity.getId( ) );
		Assert.assertNotNull( retrieved );
		
		Assert.assertEquals( upEntity.getCorreo( ), retrieved.getCorreo( ) );
		Assert.assertEquals( upEntity.getEdad( ), retrieved.getEdad( ) );
		Assert.assertEquals( upEntity.getContrasena( ), retrieved.getContrasena( ) );
		Assert.assertEquals( upEntity.getFoto( ), retrieved.getFoto( ) );
	}
	
	@Test
	public void deleteEspectador( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		logic.deleteEspectador( entity.getId( ) );
		EspectadorEntity deleted = em.find( EspectadorEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
	
	@Test
	public void getBoleta( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		BoletaEntity main = entity.getBoletas( ).get( 0 );
		
		BoletaEntity returned = logic.getBoleta( entity.getId( ), main.getId( ) );
		Assert.assertNotNull( returned );
		
		Assert.assertEquals( main.getTipo( ), returned.getTipo( ) );
		Assert.assertEquals( main.getPrecio( ), returned.getPrecio( ) );
		Assert.assertEquals( dateToString( main.getInicio( ) ), dateToString( returned.getInicio( ) ) );
		Assert.assertEquals( dateToString( main.getFin( ) ), dateToString( returned.getFin( ) ) );
		
		try
		{
			logic.getBoleta( entity.getId( ), -1L );
			Assert.fail( );
		}
		catch( Exception e )
		{
			// Debe fallar
		}
	}
	
	@Test
	public void getBoletas( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		List<BoletaEntity> boletas = logic.getBoletas( entity.getId( ) );
		
		for( BoletaEntity boleta : boletas )
		{
			Assert.assertTrue( existsBoleta( entity, boleta.getId( ) ) );
		}
	}
	
	@Test
	public void removeBoleta( ) throws Exception
	{
		EspectadorEntity entity = data.get( 0 );
		BoletaEntity main = entity.getBoletas( ).get( 0 );
		
		logic.removeBoleta( entity.getId( ), main.getId( ) );
		
		EspectadorEntity retrievedEspectador = em.find( EspectadorEntity.class, entity.getId( ) );
		Assert.assertFalse( existsBoleta( retrievedEspectador, main.getId( ) ) );
		
		BoletaEntity retrievedBoleta = em.find( BoletaEntity.class, main.getId( ) );
		Assert.assertNull( retrievedBoleta );
	}
	
	@Test
	public void addBoleta( ) throws Exception
	{
		EspectadorEntity espectador = data.get( 0 );
		
		BoletaEntity boleta = factory.manufacturePojo( BoletaEntity.class );
		boleta.setFeria( feria );
		
		Date inicio = new Date( feria.getInicio( ).getTime( ) + 7200000L );
		Date fin = new Date( feria.getFin( ).getTime( ) - 3600000L );
		
		boleta.setInicio( inicio );
		boleta.setFin( fin );
		boleta.setTipo( 1 );
		
		BoletaEntity returnedBoleta = logic.addBoleta( espectador.getId( ), boleta );
		
		EspectadorEntity retrievedEspectador = em.find( EspectadorEntity.class, espectador.getId( ) );
		Assert.assertTrue( existsBoleta( retrievedEspectador, returnedBoleta.getId( ) ) );
	}
	
	private boolean existsBoleta( EspectadorEntity espectador, Long idBoleta )
	{
		for( BoletaEntity boleta : espectador.getBoletas( ) )
		{
			if( boleta.getId( ).equals( idBoleta ) )
			{
				return true;
			}
		}
		return false;
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