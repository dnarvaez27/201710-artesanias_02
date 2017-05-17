/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import co.edu.uniandes.csw.artesanias.persistence.SalonPersistence;
import co.edu.uniandes.csw.artesanias.persistence.StandPersistence;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author IVAN
 */
@RunWith( Arquillian.class )
public class SalonLogicTest extends PersistenceTest<SalonEntity>{

    @Inject
    private SalonLogic logic;
    
    private PabellonEntity pabellon;
    
      @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalonEntity.class.getPackage())
                .addPackage(SalonLogic.class.getPackage())
                .addPackage(SalonPersistence.class.getPackage())
                .addPackage(PabellonEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Override
    protected void clearData() {
       em.createQuery("delete from SalonEntity").executeUpdate();
        em.createQuery("delete from PabellonEntity").executeUpdate();
   }

    @Override
    protected void insertData() {
       pabellon = factory.manufacturePojo(PabellonEntity.class);
        em.persist(pabellon);
        for (int i = 0; i < 3; i++) {
            SalonEntity entity = factory.manufacturePojo(SalonEntity.class);
            entity.setPabellon(pabellon);
            

            em.persist(entity);
            data.add(entity);
        } 
    }
    
      @Test
    public void createSalon() throws BusinessLogicException {
        SalonEntity newEntity = factory.manufacturePojo(SalonEntity.class);
        SalonEntity result = logic.createSalon(newEntity);
        Assert.assertNotNull(result);
        SalonEntity entity = em.find(SalonEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
    }
    
     @Test
    public void getSalones() {
        List<SalonEntity> list = logic.getSalones();
        Assert.assertEquals(data.size(), list.size());
        for (SalonEntity entity : list) {
            boolean found = false;
            for (SalonEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
        
    }
        
         @Test
    public void getSalon(){
        SalonEntity entity = pabellon.getSalones().get(0);
        SalonEntity resultEntity = logic.getSalon(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNumero(), resultEntity.getNumero());
    }
    
     @Test
    public void deleteStandTest() {
        SalonEntity entity = data.get(1);
        logic.deleteSalon(entity.getId());
        SalonEntity deleted = em.find(SalonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     @Test
    public void updateStandTest() throws BusinessLogicException {
        SalonEntity entity = data.get(0);
        SalonEntity pojoEntity = factory.manufacturePojo(SalonEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateSalon(pojoEntity);

        SalonEntity resp = em.find(SalonEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
    }
    
    
    
}
