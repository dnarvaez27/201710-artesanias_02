/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
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
 * @author IVAN
 */
@RunWith( Arquillian.class )
public class OrganizadorPersistenceTest extends PersistenceTest<OrganizadorEntity>{

    @Inject
    private OrganizadorPersistence persistence;
    
    @Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( OrganizadorEntity.class.getPackage( ) )
		                 .addPackage( OrganizadorPersistence.class.getPackage( ) )
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
    
    @Override
    protected void clearData() {
        em.createQuery("DELETE FROM OrganizadorEntity ").executeUpdate();
        em.createQuery("DELETE FROM OrganizadorEntity").executeUpdate();
    }

    @Override
    protected void insertData() {
        PodamFactory factory = new PodamFactoryImpl( );
		for( int i = 0; i < 10; i++ )
		{
			OrganizadorEntity entity = factory.manufacturePojo( OrganizadorEntity.class );
			em.persist( entity );
			data.add( entity );
		}
    }
    
    @Test
	public void create( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		OrganizadorEntity newEntity = factory.manufacturePojo( OrganizadorEntity.class );
		
		OrganizadorEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		OrganizadorEntity entity = em.find( OrganizadorEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
	}
	
	@Test
	public void find( )
	{
		OrganizadorEntity entity = data.get( 0 );
		OrganizadorEntity newEntity = persistence.find( entity.getId( ) );
		Assert.assertNotNull( newEntity );
		Assert.assertEquals( entity.getCorreo(), newEntity.getCorreo());
	}
	
	@Test
	public void findAll( )
	{
		List<OrganizadorEntity> finded = persistence.findAll( );
		Assert.assertEquals( data.size( ), finded.size( ) );
		for( OrganizadorEntity organizadorEntity : finded )
		{
			boolean found = false;
			for( OrganizadorEntity entity : data )
			{
				if( organizadorEntity.getId( ).equals( entity.getId( ) ) )
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
		OrganizadorEntity entity = data.get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		OrganizadorEntity upEntity = factory.manufacturePojo( OrganizadorEntity.class );
		upEntity.setId( entity.getId( ) );
		
		persistence.update( upEntity );
		
		OrganizadorEntity resp = em.find( OrganizadorEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getCorreo( ), resp.getCorreo( ) );
	}
	
	@Test
	public void delete( )
	{
		OrganizadorEntity entity = data.get( 0 );
		persistence.delete( entity.getId( ) );
		OrganizadorEntity deleted = em.find( OrganizadorEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
    
}
