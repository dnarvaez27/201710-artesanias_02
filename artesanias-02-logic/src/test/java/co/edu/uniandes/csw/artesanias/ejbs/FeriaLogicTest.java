/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.persistence.FeriaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import java.util.LinkedList;
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
public class FeriaLogicTest extends PersistenceTest<FeriaEntity> {

    @Inject
    private FeriaLogic logic;

    private EspacioEntity espacio;

    private BoletaEntity boleta;

    private OrganizadorEntity organizador;

    private ConferenciaEntity conferencia;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FeriaEntity.class.getPackage())
                .addPackage(FeriaLogic.class.getPackage())
                .addPackage(FeriaPersistence.class.getPackage())
                .addPackage(EspacioEntity.class.getPackage())
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(OrganizadorEntity.class.getPackage())
                .addPackage(ConferenciaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Override
    protected void clearData() {
        em.createQuery("DELETE FROM BoletaEntity ").executeUpdate();
        em.createQuery("DELETE FROM FeriaEntity ").executeUpdate();
        em.createQuery("DELETE FROM EspacioEntity ").executeUpdate();
        em.createQuery("DELETE FROM OrganizadorEntity ").executeUpdate();
        em.createQuery("DELETE FROM ConferenciaEntity ").executeUpdate();
    }

    @Override
    protected void insertData() {
        espacio = factory.manufacturePojo(EspacioEntity.class);
        boleta = factory.manufacturePojo(BoletaEntity.class);
        organizador = factory.manufacturePojo(OrganizadorEntity.class);
        conferencia = factory.manufacturePojo(ConferenciaEntity.class);

        em.persist(espacio);
        em.persist(boleta);
        em.persist(organizador);
        em.persist(conferencia);

        List<BoletaEntity> bs = new LinkedList<>();
        bs.add(boleta);
        List<OrganizadorEntity> os = new LinkedList<>();
        os.add(organizador);
        List<ConferenciaEntity> cs = new LinkedList<>();
        cs.add(conferencia);

        for (int i = 0; i < 10; i++) {
            FeriaEntity entity = factory.manufacturePojo(FeriaEntity.class);

            entity.setEspacio(espacio);
            entity.setBoletas(bs);
            entity.setOrganizadores(os);
            entity.setConferencias(cs);

            em.persist(entity);
            data.add(entity);
        }
    }

    //--------------------------------------------------------------------------
    // Métodos de Feria
    //--------------------------------------------------------------------------
    
    @Test
    public void createFeria() throws Exception {
        FeriaEntity entity = factory.manufacturePojo(FeriaEntity.class);
        List<BoletaEntity> bs = new LinkedList<>();
        bs.add(boleta);
        List<OrganizadorEntity> os = new LinkedList<>();
        os.add(organizador);
        List<ConferenciaEntity> cs = new LinkedList<>();
        cs.add(conferencia);
        entity.setEspacio(espacio);
        entity.setBoletas(bs);
        entity.setOrganizadores(os);
        entity.setConferencias(cs);
        
        org.junit.Assert.assertNotNull(logic.createFeria(entity));
        FeriaEntity r = em.find(FeriaEntity.class, entity.getId());
        org.junit.Assert.assertNotNull(r);
        org.junit.Assert.assertEquals(entity.getNombre(), r.getNombre());
        try {
            entity = factory.manufacturePojo(FeriaEntity.class);
            entity.setNombre(null);
            entity = logic.createFeria(entity);
        } catch (Exception e) {
            // Debe Fallar
            try {
                entity = factory.manufacturePojo(FeriaEntity.class);
                entity.setInicio(null);
                entity = logic.createFeria(entity);
            } catch (Exception e1) {
                // Debe Fallar
                try {
                    entity = factory.manufacturePojo(FeriaEntity.class);
                    entity.setFin(null);
                    entity = logic.createFeria(entity);
                } catch (Exception e2) {
                    // Debe Fallar
                }
            }
        }
    }
    
    @Test
    public void getFerias() throws Exception {
        List<FeriaEntity> fs = logic.getFerias();
        org.junit.Assert.assertEquals(data.size(), fs.size());
        for (FeriaEntity f : fs) {
            org.junit.Assert.assertTrue(data.contains(f));
        }
    }
    
    @Test
    public void getFeria() throws Exception {
        FeriaEntity f = data.get(0);
        FeriaEntity r = logic.getFeria(f.getId());
        org.junit.Assert.assertNotNull(r);
        org.junit.Assert.assertEquals(f.getNombre(), r.getNombre());
    }
    
    @Test
    public void updateFeria() throws Exception {
        FeriaEntity f = data.get(0);
        FeriaEntity uf = factory.manufacturePojo(FeriaEntity.class);
        uf.setId(f.getId());
        logic.updateFeria(uf);
        FeriaEntity r = em.find(FeriaEntity.class, uf.getId());
        org.junit.Assert.assertNotNull(r);
        org.junit.Assert.assertEquals(uf.getNombre(), r.getNombre());
    }
    
    @Test
    public void deleteFeria() throws Exception {
        FeriaEntity f = data.get(0);
        logic.deleteFeria(f.getId());
        org.junit.Assert.assertNull(em.find(FeriaEntity.class, f.getId()));
    }
    
    //--------------------------------------------------------------------------
    // Métodos de Organizador
    //--------------------------------------------------------------------------
    
    @Test
    public void getOrganizador() throws Exception {
        FeriaEntity f = data.get(0);
        OrganizadorEntity o = logic.getOrganizador(f.getId(), f.getOrganizadores()
                .get(0).getId());
        org.junit.Assert.assertNotNull(o);
        org.junit.Assert.assertEquals(o.getCorreo(), f.getOrganizadores().get(0)
                .getCorreo());
    }
    
    @Test
    public void getOrganizadores() throws Exception {
        FeriaEntity f = data.get(0);
        List<OrganizadorEntity> os = logic.getOrganizadores(f.getId());
        org.junit.Assert.assertEquals(f.getOrganizadores().size(), os.size());
        org.junit.Assert.assertEquals(f.getOrganizadores().get(0), os.get(0));
    }
    
    @Test
    public void removeOrganizador() throws Exception {
        FeriaEntity f = data.get(0);
        Long id = f.getOrganizadores().get(0).getId();
        logic.removeOrganizador(f.getId(), id);
        org.junit.Assert.assertNull(em.find(OrganizadorEntity.class, id));
    }
    
    @Test
    public void addOrganizador() throws Exception {
        FeriaEntity f = data.get(0);
        int s = f.getOrganizadores().size();
        OrganizadorEntity o = factory.manufacturePojo(OrganizadorEntity.class);
        em.persist(o);
        logic.addOrganizador(f.getId(), o.getId());
        org.junit.Assert.assertNotEquals(f.getOrganizadores().size(), s);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de Boleta
    //--------------------------------------------------------------------------
    
    @Test
    public void getBoleta() throws Exception {
        FeriaEntity f = data.get(0);
        BoletaEntity o = logic.getBoleta(f.getId(), f.getBoletas()
                .get(0).getId());
        org.junit.Assert.assertNotNull(o);
        org.junit.Assert.assertEquals(o.getPrecio(), f.getBoletas().get(0)
                .getPrecio());
        org.junit.Assert.assertEquals(o.getTipo(), f.getBoletas().get(0)
                .getTipo());
    }
    
    @Test
    public void getBoletas() throws Exception {
        FeriaEntity f = data.get(0);
        List<BoletaEntity> os = logic.getBoletas(f.getId());
        org.junit.Assert.assertEquals(f.getBoletas().size(), os.size());
        org.junit.Assert.assertEquals(f.getBoletas().get(0), os.get(0));
    }
    
    @Test
    public void removeBoleta() throws Exception {
        FeriaEntity f = data.get(0);
        Long id = f.getBoletas().get(0).getId();
        logic.removeBoleta(f.getId(), id);
        org.junit.Assert.assertNull(em.find(BoletaEntity.class, id));
    }
    
    @Test
    public void addBoleta() throws Exception {
        FeriaEntity f = data.get(0);
        int s = f.getBoletas().size();
        BoletaEntity o = factory.manufacturePojo(BoletaEntity.class);
        em.persist(o);
        logic.addBoleta(f.getId(), o.getId());
        org.junit.Assert.assertNotEquals(f.getBoletas().size(), s);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de Conferencia
    //--------------------------------------------------------------------------
    
    @Test
    public void getConferencia() throws Exception {
        FeriaEntity f = data.get(0);
        ConferenciaEntity o = logic.getConferencia(f.getId(), f.getConferencias()
                .get(0).getId());
        org.junit.Assert.assertNotNull(o);
        org.junit.Assert.assertEquals(o.getConferencista(), f.getConferencias().get(0)
                .getConferencista());
        org.junit.Assert.assertEquals(o.getTema(), f.getConferencias().get(0)
                .getTema());
    }
    
    @Test
    public void getConferencias() throws Exception {
        FeriaEntity f = data.get(0);
        List<ConferenciaEntity> os = logic.getConferencias(f.getId());
        org.junit.Assert.assertEquals(f.getConferencias().size(), os.size());
        org.junit.Assert.assertEquals(f.getConferencias().get(0), os.get(0));
    }
    
    @Test
    public void removeConferencia() throws Exception {
        FeriaEntity f = data.get(0);
        Long id = f.getConferencias().get(0).getId();
        logic.removeConferencia(f.getId(), id);
        org.junit.Assert.assertNull(em.find(ConferenciaEntity.class, id));
    }
    
    @Test
    public void addConferencia() throws Exception {
        FeriaEntity f = data.get(0);
        int s = f.getConferencias().size();
        ConferenciaEntity o = factory.manufacturePojo(ConferenciaEntity.class);
        em.persist(o);
        logic.addConferencia(f.getId(), o.getId());
        org.junit.Assert.assertNotEquals(f.getConferencias().size(), s);
    }
}