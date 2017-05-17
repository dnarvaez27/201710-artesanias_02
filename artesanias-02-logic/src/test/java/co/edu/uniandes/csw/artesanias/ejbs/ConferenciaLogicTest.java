/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.persistence.ArtesaniaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import java.util.LinkedList;
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
@RunWith(Arquillian.class)
public class ConferenciaLogicTest extends PersistenceTest<ConferenciaEntity> {

    @Inject
    private ConferenciaLogic logic;

    private FeriaEntity feria;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConferenciaEntity.class.getPackage())
                .addPackage(ConferenciaLogic.class.getPackage())
                .addPackage(ConferenciaPersistence.class.getPackage())
                .addPackage(FeriaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Override
    protected void clearData() {
        data.clear();

        em.createQuery("DELETE FROM ConferenciaEntity ").executeUpdate();
        em.createQuery("DELETE FROM FeriaEntity ").executeUpdate();
    }

    @Override
    protected void insertData() {
        feria = factory.manufacturePojo(FeriaEntity.class);
        em.persist(feria);

        List<ConferenciaEntity> conferencias = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            ConferenciaEntity entity = factory.manufacturePojo(ConferenciaEntity.class);
            entity.setFeria(feria);
            em.persist(entity);

            conferencias.add(entity);
        }
        feria.setConferencias(conferencias);
    }

    @Test
    public void create() throws Exception {
        ConferenciaEntity newEntity = factory.manufacturePojo(ConferenciaEntity.class);
        newEntity.setFeria(feria);

        ConferenciaEntity result = logic.createConferencia(newEntity);
        Assert.assertNotNull(result);

        ConferenciaEntity entity = em.find(ConferenciaEntity.class, result.getId());
        Assert.assertNotNull(entity);

        Assert.assertEquals(result.getConferencista(), entity.getConferencista());

        Assert.assertTrue(result.equals(entity));

        newEntity = factory.manufacturePojo(ConferenciaEntity.class);
        newEntity.setFeria(feria);
        newEntity.setTema("");
        try {
            logic.createConferencia(newEntity);
            Assert.fail();
        } catch (Exception e) {
            // Debería fallar
        }

    }

    @Test
    public void getConferencias() throws Exception {
        List<ConferenciaEntity> list = logic.getConferencias();
        Assert.assertEquals(feria.getConferencias().size(), list.size());
        for (ConferenciaEntity conferencia : list) {
            Assert.assertNotNull(conferencia);
        }
    }
    
    @Test
	public void getConferencia( ) throws Exception
	{
		ConferenciaEntity conferencia = feria.getConferencias().get( 0 );
		ConferenciaEntity retrieved = logic.getConferenciasFromFeria(feria.getId()).get(0);
		
		Assert.assertNotNull( retrieved );
		
        }		
        
        
        @Test
	public void deleteConferencia( ) throws Exception
	{
		ConferenciaEntity conferencia = feria.getConferencias().get( 0 );
		logic.deleteConferencia(conferencia.getId(), feria.getId());
		
		Assert.assertNull( em.find( ConferenciaEntity.class, conferencia.getId( ) ) );
		
		
	}
        @Test
	public void updateConferencia( ) throws Exception
	{
		ConferenciaEntity conferencia = feria.getConferencias().get( 0 );
		ConferenciaEntity upConferencia = factory.manufacturePojo( ConferenciaEntity.class );
		
		upConferencia.setFeria(feria );
		upConferencia.setId( conferencia.getId( ) );
		
		logic.updateConferencia(upConferencia );
		ConferenciaEntity retrieved = em.find( ConferenciaEntity.class, conferencia.getId( ) );
		
		Assert.assertNotNull( retrieved );
		Assert.assertEquals( upConferencia.getTema(), retrieved.getTema());
	}
}
