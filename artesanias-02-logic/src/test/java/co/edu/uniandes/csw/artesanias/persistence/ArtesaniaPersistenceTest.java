package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dnarv on 10/05/2017.
 */
@RunWith( Arquillian.class )
public class ArtesaniaPersistenceTest extends PersistenceTest<ArtesaniaEntity>
{
	@Inject
	private ArtesaniaPersistence persistence;
	
	private ArtesanoEntity artesano;
	
	@Deployment
	public static JavaArchive createDeployment( )
	{
		return ShrinkWrap.create( JavaArchive.class )
		                 .addPackage( ArtesaniaEntity.class.getPackage( ) )
		                 .addPackage( ArtesaniaPersistence.class.getPackage( ) )
		
		                 .addPackage( ArtesanoEntity.class.getPackage( ) )
		
		                 .addAsManifestResource( "META-INF/persistence.xml", "persistence.xml" )
		                 .addAsManifestResource( "META-INF/beans.xml", "beans.xml" );
	}
	
	@Override
	protected void clearData( )
	{
		em.createQuery( "DELETE FROM ArtesaniaEntity " ).executeUpdate( );
		em.createQuery( "DELETE FROM ArtesanoEntity " ).executeUpdate( );
	}
	
	@Override
	protected void insertData( )
	{
		PodamFactory factory = new PodamFactoryImpl( );
		artesano = factory.manufacturePojo( ArtesanoEntity.class );
		artesano.setId( 1L );
		em.persist( artesano );
		
		List<ArtesaniaEntity> artesanias = new LinkedList<>( );
		
		for( int i = 0; i < 10; i++ )
		{
			ArtesaniaEntity artesania = factory.manufacturePojo( ArtesaniaEntity.class );
			artesania.setArtesano( artesano );
			em.persist( artesania );
			
			artesanias.add( artesania );
		}
		artesano.setArtesanias( artesanias );
	}
	
	@Test
	public void create( ) throws Exception
	{
		PodamFactory factory = new PodamFactoryImpl( );
		ArtesaniaEntity newEntity = factory.manufacturePojo( ArtesaniaEntity.class );
		
		ArtesaniaEntity result = persistence.create( newEntity );
		Assert.assertNotNull( result );
		
		ArtesaniaEntity entity = em.find( ArtesaniaEntity.class, result.getId( ) );
		Assert.assertNotNull( entity );
		
		Assert.assertEquals( result.getNombre( ), entity.getNombre( ) );
	}
	
	@Test
	public void find( ) throws Exception
	{
		ArtesaniaEntity entity = artesano.getArtesanias( ).get( 0 );
		ArtesaniaEntity newEntity = persistence.find( artesano.getId( ), entity.getId( ) );
		
		Assert.assertNotNull( newEntity );
		Assert.assertEquals( entity.getNombre( ), newEntity.getNombre( ) );
	}
	
	@Test
	public void findAll( ) throws Exception
	{
		List<ArtesaniaEntity> retrieved = persistence.findAll( );
		Assert.assertEquals( artesano.getArtesanias( ).size( ), retrieved.size( ) );
		for( ArtesaniaEntity currentArtesania : retrieved )
		{
			Assert.assertTrue( existsArtesania( currentArtesania.getId( ) ) );
		}
	}
	
	private boolean existsArtesania( Long idArtesania )
	{
		//	return artesano.getArtesanias( ).stream( ).anyMatch( a -> a.getId( ).equals( idArtesania ) );
		for( ArtesaniaEntity entity : artesano.getArtesanias( ) )
		{
			if( idArtesania.equals( entity.getId( ) ) )
			{
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void findAllFromArtesano( ) throws Exception
	{
		List<ArtesaniaEntity> retrieved = persistence.findAllFromArtesano( artesano.getId( ) );
		Assert.assertEquals( retrieved.size( ), artesano.getArtesanias( ).size( ) );
		for( ArtesaniaEntity artesania : retrieved )
		{
			Assert.assertTrue( existsArtesania( artesania.getId( ) ) );
		}
	}
	
	@Test
	public void update( ) throws Exception
	{
		ArtesaniaEntity entity = artesano.getArtesanias( ).get( 0 );
		PodamFactory factory = new PodamFactoryImpl( );
		ArtesaniaEntity upEntity = factory.manufacturePojo( ArtesaniaEntity.class );
		upEntity.setId( entity.getId( ) );
		
		persistence.update( upEntity );
		
		ArtesaniaEntity resp = em.find( ArtesaniaEntity.class, entity.getId( ) );
		Assert.assertEquals( upEntity.getNombre( ), resp.getNombre( ) );
	}
	
	@Test
	public void delete( ) throws Exception
	{
		ArtesaniaEntity entity = artesano.getArtesanias( ).get( 0 );
		persistence.delete( entity.getId( ) );
		ArtesaniaEntity deleted = em.find( ArtesaniaEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
}