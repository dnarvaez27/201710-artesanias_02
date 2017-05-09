/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.espinosa12
 */
public class CiudadPersistenceTest 
{
    
    public CiudadPersistenceTest() 
    {
    }

    @Deployment
    public static JavaArchive createdeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private CiudadPersistence ciudadPersistence;
    
    @PersistenceContext(unitName = "artesaniasPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();
    
    @Before
    public void setUp()
    {
        try
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch( Exception e)
        {
            try
            {
                utx.rollback();
            } 
            catch( Exception e1){
                e1.printStackTrace();
                fail("configuration data base fail");
            }
        }
    }
    
    private void clearData()
    {
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++)
        {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCiudadTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);

        CiudadEntity result = ciudadPersistence.create(newEntity);
        Assert.assertNotNull(result);
        
        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());
        Assert.assertNotNull(entity);  
    }
    @Test
    public void findCiudadTest()
    {
        CiudadEntity entity = data.get( 0 );
	CiudadEntity newEntity = ciudadPersistence.find( entity.getId( ) );
	Assert.assertNotNull( newEntity );
	Assert.assertEquals( entity.getNombre(), newEntity.getNombre() );
    }
    
    @Test
    public void findAlltest()
    {
        List<CiudadEntity> finded = ciudadPersistence.findAll( );
		Assert.assertEquals( data.size( ), finded.size( ) );
		for( CiudadEntity ciudadEntity : finded )
		{
			boolean found = false;
			for( CiudadEntity entity : data )
			{
				if( ciudadEntity.getId( ).equals( entity.getId( ) ) )
				{
					found = true;
					break;
				}
			}
			Assert.assertTrue( found );
		}
    }
    
    @Test
    public void updateCiudadTest()
    {
        CiudadEntity entity = data.get( 0 );
	PodamFactory factory = new PodamFactoryImpl( );
	CiudadEntity upEntity = factory.manufacturePojo( CiudadEntity.class );
	upEntity.setId( entity.getId( ) );
		
	ciudadPersistence.update( upEntity );
		
	CiudadEntity resp = em.find( CiudadEntity.class, entity.getId( ) );
	Assert.assertEquals( upEntity.getNombre(), resp.getNombre() );
    }
    
    @Test
    public void deleteCiudadTest()
    {
        CiudadEntity entity = data.get( 0 );
	ciudadPersistence.delete( entity.getId( ) );
	CiudadEntity deleted = em.find( CiudadEntity.class, entity.getId( ) );
	Assert.assertNull( deleted );
    }
}
