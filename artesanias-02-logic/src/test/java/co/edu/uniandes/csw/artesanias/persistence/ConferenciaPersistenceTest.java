/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import java.util.LinkedList;
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
public class ConferenciaPersistenceTest extends PersistenceTest<ConferenciaEntity>{
    
        @Inject
	private ConferenciaPersistence persistence;
	
	private FeriaEntity feria;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ConferenciaEntity.class.getPackage( ) )
		                 .addPackage( ConferenciaPersistence.class.getPackage( ) )
		
		                 .addPackage( FeriaEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
        
        
        @Override
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM ConferenciaEntity " ).executeUpdate( );
		em.createQuery( "DELETE FROM FeriaEntity " ).executeUpdate( );
	}

    @Override
    protected void insertData() {
        PodamFactory factory = new PodamFactoryImpl( );
		feria = factory.manufacturePojo( FeriaEntity.class );
		feria.setId( 1L );
		em.persist( feria );
		
		List<ConferenciaEntity> conferencias = new LinkedList<>( );
		
		for( int i = 0; i < 10; i++ )
		{
			ConferenciaEntity conferencia = factory.manufacturePojo( ConferenciaEntity.class );
			conferencia.setFeria(feria);
                        
			em.persist( conferencia );
			
			conferencias.add( conferencia );
		}
		feria.setConferencias(conferencias);
                 System.out.println(feria.getNombre());
    }
        
	@Test
	public void create( ) throws Exception
	{
		ConferenciaEntity newEntity = factory.manufacturePojo( ConferenciaEntity.class );
		
		ConferenciaEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		ConferenciaEntity entity = em.find( ConferenciaEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
		
		Assert.assertEquals( result.getConferencista(), entity.getConferencista());
	
		Assert.assertTrue( result.equals( entity ) );
                
                
	}
        
        @Test
	public void find( ) throws Exception
	{
		
		ConferenciaEntity entity = feria.getConferencias().get( 0 );
		Assert.assertNotNull(feria);
		
		
		
		
		
	}
        
        @Test
	public void findAll( ) throws Exception
	{
		List<ConferenciaEntity> retrieved = persistence.findAll( );
		Assert.assertEquals( feria.getConferencias().size( ), retrieved.size( ) );
		for( ConferenciaEntity currentConferencia : retrieved )
		{
			Assert.assertNotNull(currentConferencia);
		}
	}
        
        @Test
	public void delete( ) throws Exception
	{
		ConferenciaEntity entity = feria.getConferencias().get( 0 );
		Assert.assertNotNull(entity);
                persistence.delete( entity.getId( ) );
		ConferenciaEntity deleted = em.find( ConferenciaEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
        
        @Test
	public void update( ) throws Exception
	{
		ConferenciaEntity entity = feria.getConferencias().get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		ConferenciaEntity upEntity = factory.manufacturePojo( ConferenciaEntity.class );
		upEntity.setId( entity.getId( ) );
		
		persistence.update( upEntity );
		
		ConferenciaEntity resp = em.find( ConferenciaEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getId( ), resp.getId( ) );
		Assert.assertEquals( upEntity.hashCode( ), resp.hashCode( ) );
		
	}
	
}