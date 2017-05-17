/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.persistence.BoletaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Miller
 */
@RunWith(Arquillian.class)
public class BoletaLogicTest extends PersistenceTest<BoletaEntity> {
    
    @Inject
    private BoletaLogic logic;
    
    private FeriaEntity feria;
    
    private EspectadorEntity espectador;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(BoletaLogic.class.getPackage())
                .addPackage(BoletaPersistence.class.getPackage())
                .addPackage(FeriaEntity.class.getPackage())
                .addPackage(EspectadorEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Override
    protected void clearData() {
        em.createQuery("DELETE FROM BoletaEntity").executeUpdate();
        em.createQuery("DELETE FROM FeriaEntity").executeUpdate();
        em.createQuery("DELETE FROM espectador").executeUpdate();
    }

    @Override
    protected void insertData() {
        feria = factory.manufacturePojo(FeriaEntity.class);
        espectador = factory.manufacturePojo(EspectadorEntity.class);

        em.persist(feria);
        em.persist(espectador);

        for (int i = 0; i < 10; i++) {
            BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
            entity.setFeria(feria);
            entity.setEspectador(espectador);
            entity.setTipo(i%3);
            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------------------------------------------
    // Métodos de Boleta
    //--------------------------------------------------------------------------
    
    @Test
    public void createBoleta() throws Exception {
        BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
        entity.setFeria(feria);
        entity.setEspectador(espectador);
        entity.setTipo(1);
        
        org.junit.Assert.assertNotNull(logic.createBoleta(entity));
        BoletaEntity r = em.find(BoletaEntity.class, entity.getId());
        org.junit.Assert.assertNotNull(r);
        org.junit.Assert.assertEquals(entity.getPrecio(), r.getPrecio());
        org.junit.Assert.assertEquals(entity.getTipo(), r.getTipo());
        try {
            entity = factory.manufacturePojo(BoletaEntity.class);
            entity.setTipo(-1);
            entity = logic.createBoleta(entity);
        } catch (Exception e) {
            // Debe Fallar
            try {
                entity = factory.manufacturePojo(BoletaEntity.class);
                entity.setInicio(null);
                entity = logic.createBoleta(entity);
            } catch (Exception e1) {
                // Debe Fallar
            }
        }
    }
    
    @Test
    public void getBoletas() throws Exception {
        List<BoletaEntity> fs = logic.getBoletas();
        org.junit.Assert.assertEquals(data.size(), fs.size());
        for (BoletaEntity f : fs) {
            org.junit.Assert.assertTrue(data.contains(f));
        }
    }
    
    @Test
    public void getBoleta() throws Exception {
        BoletaEntity f = data.get(0);
        BoletaEntity r = logic.getBoleta(f.getId());
        org.junit.Assert.assertNotNull(r);
        org.junit.Assert.assertEquals(f.getPrecio(), r.getPrecio());
        org.junit.Assert.assertEquals(f.getTipo(), r.getTipo());
    }
    
    @Test
    public void updateBoleta() throws Exception {
        BoletaEntity f = data.get(0);
        BoletaEntity uf = factory.manufacturePojo(BoletaEntity.class);
        uf.setId(f.getId());
        uf.setTipo(1);
        logic.updateBoleta(uf);
        BoletaEntity r = em.find(BoletaEntity.class, uf.getId());
        org.junit.Assert.assertNotNull(r);
        org.junit.Assert.assertEquals(uf.getPrecio(), r.getPrecio());
        org.junit.Assert.assertEquals(uf.getTipo(), r.getTipo());
    }
    
    @Test
    public void deleteBoleta() throws Exception {
        BoletaEntity f = data.get(0);
        logic.deleteBoleta(f.getId());
        org.junit.Assert.assertNull(em.find(BoletaEntity.class, f.getId()));
    }
}
