/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.CiudadPersistence;
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
public class CiudadLogicTest {
    
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
    private CiudadLogic ciudadLogic;

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
    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();


    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
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
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear una Ciudad
     *
     * 
     */
    @Test
    public void createCiudadTest() throws BusinessLogicException {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity result = ciudadLogic.createCiudad(newEntity);
        Assert.assertNotNull(result);
        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Ciudades
     *
     * 
     */
    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> list = ciudadLogic.getCiudades();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadEntity entity : list) {
            boolean found = false;
            for (CiudadEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar una Ciudad
     *
     * 
     */
    @Test
    public void getCiudadTest() {
        CiudadEntity entity = data.get(0);
        CiudadEntity resultEntity = ciudadLogic.getCiudad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getPais(), resultEntity.getPais());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para eliminar una Ciudad
     *
     * 
     */
    @Test
    public void deleteCiudadTest() {
        CiudadEntity entity = data.get(1);
        ciudadLogic.deleteCiudad(entity.getId());
        CiudadEntity deleted = em.find(CiudadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Ciudad
     *
     * 
     */
    @Test
    public void updateCiudadTest() throws BusinessLogicException {
        CiudadEntity entity = data.get(0);
        CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);

        pojoEntity.setId(entity.getId());

        ciudadLogic.updateCiudad(pojoEntity);

        CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getPais(), resp.getPais());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
}
