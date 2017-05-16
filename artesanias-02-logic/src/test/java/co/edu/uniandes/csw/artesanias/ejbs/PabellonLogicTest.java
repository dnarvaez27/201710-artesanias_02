/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;
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
public class PabellonLogicTest {
    
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
    private PabellonLogic pabellonLogic;

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
    private List<PabellonEntity> data = new ArrayList<PabellonEntity>();

    /**
     * 
     */
    private EspacioEntity espacio;

    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PabellonEntity.class.getPackage())
                .addPackage(PabellonLogic.class.getPackage())
                .addPackage(PabellonPersistence.class.getPackage())
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
        em.createQuery("delete from PabellonEntity").executeUpdate();
        em.createQuery("delete from EspacioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        
        espacio = factory.manufacturePojo(EspacioEntity.class);
        em.persist(espacio);
        for (int i = 0; i < 3; i++) {
            PabellonEntity entity = factory.manufacturePojo(PabellonEntity.class);
            entity.setEspacio(espacio);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un pabellon
     *
     * 
     */
    @Test
    public void createPabellonTest() throws BusinessLogicException {
        PabellonEntity newEntity = factory.manufacturePojo(PabellonEntity.class);
        PabellonEntity result = pabellonLogic.createPabellon(newEntity);
        Assert.assertNotNull(result);
        PabellonEntity entity = em.find(PabellonEntity.class, result.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
    }

    /**
     * Prueba para consultar la lista de pabellones
     *
     * 
     */
    @Test
    public void getPabellonesTest() {
        List<PabellonEntity> list = pabellonLogic.getPabellones( espacio.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PabellonEntity entity : list) {
            boolean found = false;
            for (PabellonEntity storedEntity : data) {
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
    public void getPabellonTest() throws BusinessLogicException {
        PabellonEntity entity = data.get(0);
        PabellonEntity resultEntity = pabellonLogic.getPabellon(espacio.getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        Assert.assertEquals(entity.getCapacidad(), resultEntity.getCapacidad());
    }

    /**
     * Prueba para eliminar un Pabellon
     *
     * 
     */
    @Test
    public void deletePabellonTest() throws BusinessLogicException {
        PabellonEntity entity = data.get(1);
        pabellonLogic.deletePabellon(espacio.getId(), entity.getId());
        PabellonEntity deleted = em.find(PabellonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un pabellon
     *
     * 
     */
    @Test
    public void updatePabellonTest() throws BusinessLogicException {
        PabellonEntity entity = data.get(0);
        PabellonEntity pojoEntity = factory.manufacturePojo(PabellonEntity.class);

        pojoEntity.setId(entity.getId());

        pabellonLogic.updatePabellon(pojoEntity);

        PabellonEntity resp = em.find(PabellonEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
        Assert.assertEquals(pojoEntity.getCapacidad(), resp.getCapacidad());
    }
}
