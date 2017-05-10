/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ia.salazar
 */
@RunWith( Arquillian.class )
public class SalonPersistenceTest extends PersistenceTest<SalonEntity>{

    @Inject
    private SalonPersistence persistence;
    
    PabellonEntity fatherEntity;
    
    @Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( SalonEntity.class.getPackage( ) )
		                 .addPackage( SalonEntity.class.getPackage( ) )
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
    
    @Override
    protected void clearData() {
        em.createQuery("DELETE FROM SalonEntity ").executeUpdate();
      
    }

    @Override
    protected void insertData() {
        PodamFactory factory = new PodamFactoryImpl( );
		for( int i = 0; i < 10; i++ )
		{
			SalonEntity entity = factory.manufacturePojo( SalonEntity.class );
			em.persist( entity );
			data.add( entity );
		}
    }
    
    @Test
	public void create( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		SalonEntity newEntity = factory.manufacturePojo( SalonEntity.class );
		
		SalonEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		SalonEntity entity = em.find( SalonEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
	}
	
	@Test
	public void find( )
	{
		SalonEntity entity = data.get( 0 );
		SalonEntity newEntity = persistence.find( entity.getId( ) );
		Assert.assertNotNull( newEntity );
		Assert.assertEquals( entity.getId(), newEntity.getId());
	}
	
	@Test
	public void findAll( )
	{
		List<SalonEntity> finded = persistence.findAll( );
		Assert.assertEquals( data.size( ), finded.size( ) );
		for( SalonEntity SalonEntity : finded )
		{
			boolean found = false;
			for( SalonEntity entity : data )
			{
				if( SalonEntity.getId( ).equals( entity.getId( ) ) )
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
		SalonEntity entity = data.get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		SalonEntity upEntity = factory.manufacturePojo( SalonEntity.class );
		upEntity.setId( entity.getId( ) );
		
		persistence.update( upEntity );
		
		SalonEntity resp = em.find( SalonEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getId( ), resp.getId( ) );
	}
	
	@Test
	public void delete( )
	{
		SalonEntity entity = data.get( 0 );
		persistence.delete( entity.getId( ) );
		SalonEntity deleted = em.find( SalonEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
    
}