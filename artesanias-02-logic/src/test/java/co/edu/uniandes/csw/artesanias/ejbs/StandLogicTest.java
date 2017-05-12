/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.StandPersistence;
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
@RunWith(Arquillian.class)
public class StandLogicTest {
    
    /**
     * 
     */

    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private StandLogic standLogic;

    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     */
    @Inject
    private UserTransaction utx;

    /**
     * 
     */
    private List<StandEntity> data = new ArrayList<StandEntity>();

    /**
     * 
     */
    private PabellonEntity pabellon;

    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StandEntity.class.getPackage())
                .addPackage(StandLogic.class.getPackage())
                .addPackage(StandPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * 
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * 
     */
    private void clearData() {
        em.createQuery("delete from StandEntity").executeUpdate();
        em.createQuery("delete from PabellonEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        
        pabellon = factory.manufacturePojo(PabellonEntity.class);
        em.persist(pabellon);
        for (int i = 0; i < 3; i++) {
            StandEntity entity = factory.manufacturePojo(StandEntity.class);
            entity.setPabellon(pabellon);
            

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Employee
     *
     * 
     */
    @Test
    public void createStandTest() throws BusinessLogicException {
        StandEntity newEntity = factory.manufacturePojo(StandEntity.class);
        StandEntity result = standLogic.createStand(newEntity);
        Assert.assertNotNull(result);
        StandEntity entity = em.find(StandEntity.class, result.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba para consultar la lista de Employees
     *
     * 
     */
    @Test
    public void getStandsTest() {
        List<StandEntity> list = standLogic.getStands();
        Assert.assertEquals(data.size(), list.size());
        for (StandEntity entity : list) {
            boolean found = false;
            for (StandEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Pabellon
     *
     * 
     */
    @Test
    public void getStandTest() throws BusinessLogicException {
        StandEntity entity = pabellon.getStands().get(0);
        StandEntity resultEntity = standLogic.getStand(pabellon.getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
    }

    /**
     * Prueba para eliminar un Pabellon
     *
     * 
     */
    @Test
    public void deleteStandTest() {
        StandEntity entity = data.get(1);
        standLogic.deleteStand(entity.getId());
        StandEntity deleted = em.find(StandEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee
     *
     * 
     */
    @Test
    public void updateStandTest() throws BusinessLogicException {
        StandEntity entity = data.get(0);
        StandEntity pojoEntity = factory.manufacturePojo(StandEntity.class);

        pojoEntity.setId(entity.getId());

        standLogic.updateStand(pojoEntity);

        StandEntity resp = em.find(StandEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }
    
}
