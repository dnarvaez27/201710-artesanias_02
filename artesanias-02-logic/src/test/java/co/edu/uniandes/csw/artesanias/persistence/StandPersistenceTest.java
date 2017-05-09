/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
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
public class StandPersistenceTest 
{
    public StandPersistenceTest() 
    {
    }

    @Deployment
    public static JavaArchive createdeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StandEntity.class.getPackage())
                .addPackage(StandPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private StandPersistence standPersistence;
    
    @PersistenceContext(unitName = "artesaniasPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<StandEntity> data = new ArrayList<StandEntity>();
    
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
        em.createQuery("delete from StandEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++)
        {
            StandEntity entity = factory.manufacturePojo(StandEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createStandTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        StandEntity newEntity = factory.manufacturePojo(StandEntity.class);

        StandEntity result = standPersistence.create(newEntity);
        Assert.assertNotNull(result);
        
        StandEntity entity = em.find(StandEntity.class, result.getId());
        Assert.assertNotNull(entity);  
    }
    @Test
    public void findStandTest()
    {
       
    }
    
    @Test
    public void findAlltest()
    {
        List<StandEntity> finded = standPersistence.findAll( );
		Assert.assertEquals( data.size( ), finded.size( ) );
		for( StandEntity standEntity : finded )
		{
			boolean found = false;
			for( StandEntity entity : data )
			{
				if( standEntity.getId( ).equals( entity.getId( ) ) )
				{
					found = true;
					break;
				}
			}
			Assert.assertTrue( found );
		}
    }
    
    @Test
    public void updateStandTest()
    {
        StandEntity entity = data.get( 0 );
	PodamFactory factory = new PodamFactoryImpl( );
	StandEntity upEntity = factory.manufacturePojo(StandEntity.class );
	upEntity.setId( entity.getId( ) );
		
	standPersistence.update( upEntity );
		
	StandEntity resp = em.find( StandEntity.class, entity.getId( ) );
	Assert.assertEquals( upEntity.getDescripcion(), resp.getDescripcion() );
    }
    
    @Test
    public void deleteStandTest()
    {
        StandEntity entity = data.get( 0 );
	standPersistence.delete( entity.getId( ) );
	StandEntity deleted = em.find( StandEntity.class, entity.getId( ) );
	Assert.assertNull( deleted );
    }
}
