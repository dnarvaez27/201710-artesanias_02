/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.espinosa12
 */
@RunWith( Arquillian.class )
public class StandPersistenceTest 
{

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
    
    private PabellonEntity pabellon;
    
    
    @PersistenceContext(unitName = "artesaniasPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<StandEntity> data = new ArrayList<StandEntity>();
    
    @Before
    public void setUp()
    {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    protected void clearData()
    {
        em.createQuery("delete from StandEntity").executeUpdate();
    }
    
    protected void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        pabellon = factory.manufacturePojo(PabellonEntity.class);
        em.persist(pabellon);
        for(int i = 0; i < 3; i++)
        {
            StandEntity entity = factory.manufacturePojo(StandEntity.class);
            em.persist(entity);
            entity.setPabellon(pabellon);
            data.add(entity);
        }
        pabellon.setStands(data);
    }
    
    @Test
    public void createStandTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        StandEntity newEntity = factory.manufacturePojo(StandEntity.class);
        StandEntity result = standPersistence.create(newEntity);

        Assert.assertNotNull(result);

        StandEntity entity = em.find(StandEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }
    @Test
    public void findStandTest()
    {
        StandEntity entity = pabellon.getStands().get( 0 );
	StandEntity newEntity = standPersistence.find( pabellon.getId( ), entity.getId( ) );
	Assert.assertNotNull( newEntity );
	Assert.assertEquals( entity.getDescripcion(), newEntity.getDescripcion() );
    }
    
    @Test
    public void findAlltest()
    {
        List<StandEntity> list = standPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (StandEntity ent : list) {
            boolean found = false;
            for (StandEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
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
