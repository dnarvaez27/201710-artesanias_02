/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

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
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.espinosa12
 */
@RunWith(Arquillian.class)
public class PabellonPersistenceTest {
    
    public PabellonPersistenceTest() {
    }

    @Deployment
    public static JavaArchive createdeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PabellonEntity.class.getPackage())
                .addPackage(PabellonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private PabellonPersistence pabellonPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PabellonEntity> data = new ArrayList<PabellonEntity>();
    
    @Test
    public void createPabellonTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PabellonEntity newEntity = factory.manufacturePojo(PabellonEntity.class);

        PabellonEntity result = pabellonPersistence.create(newEntity);

        Assert.assertNotNull(result);
        PabellonEntity entity = em.find(PabellonEntity.class, result.getId());
        Assert.assertNotNull(entity);
        
    }
    
}
