/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.persistence.EspectadorPersistence;
import co.edu.uniandes.csw.artesanias.persistence.OrganizadorPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PersistenceTest;
import java.util.Date;
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
public class OrganizadorLogicTest extends PersistenceTest<OrganizadorEntity> {

    @Inject
    private OrganizadorLogic logic;

    private FeriaEntity feria;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrganizadorEntity.class.getPackage())
                .addPackage(OrganizadorLogic.class.getPackage())
                .addPackage(OrganizadorPersistence.class.getPackage())
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(FeriaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Override
    protected void clearData() {
        em.createQuery("DELETE FROM FeriaEntity ").executeUpdate();
        em.createQuery("DELETE FROM OrganizadorEntity ").executeUpdate();
    }

    @Override
    protected void insertData() {
        feria = factory.manufacturePojo(FeriaEntity.class);
        feria.setInicio(toDate(2016, 10, 5));
        feria.setFin(toDate(2017, 5, 9));
        feria.setTotalBoletas(10);
        em.persist(feria);

        List<OrganizadorEntity> organizadores = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            OrganizadorEntity entity = factory.manufacturePojo(OrganizadorEntity.class);
            entity.setFerias(asignarFerias(entity, organizadores));
            organizadores.add(entity);
            em.persist(entity);
            data.add(entity);
        }

    }

    private List<FeriaEntity> asignarFerias(OrganizadorEntity organizador, List organisadores) {
        List<FeriaEntity> ferias = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            FeriaEntity entity = factory.manufacturePojo(FeriaEntity.class);
            ferias.add(entity);
            entity.setOrganizadores(organisadores);
            em.persist(entity);

        }
        organizador.setFerias(ferias);
        return ferias;
    }
    
    	@Test
	public void createOrganizador( ) throws Exception
	{
		OrganizadorEntity newEntity = factory.manufacturePojo( OrganizadorEntity.class );
		
		OrganizadorEntity result = logic.createOrganizador(newEntity );
		Assert.assertNotNull( result );
		
		OrganizadorEntity retrieved = em.find( OrganizadorEntity.class, newEntity.getId( ) );
		Assert.assertNotNull( retrieved );
		
		newEntity = factory.manufacturePojo( OrganizadorEntity.class );
		newEntity.setCorreo( "" );
		try
		{
			logic.createOrganizador(newEntity );
			Assert.fail( );
		}
		catch( Exception e )
		{
			
		}
		
		newEntity = factory.manufacturePojo( OrganizadorEntity.class );
		newEntity.setContrasena( "" );
		try
		{
			logic.createOrganizador(newEntity );
			Assert.fail( );
		}
		catch( Exception e )
		{
			
		}
	}
    
        @Test
	public void getOrganizadores( ) throws Exception
	{
		List<OrganizadorEntity> list = logic.getOrganizadores();
		Assert.assertEquals( data.size( ), list.size( ) );
		for( OrganizadorEntity organizador : list )
		{
			Assert.assertTrue( exists( organizador.getId( ) ) );
		}
	}
        
        private boolean exists( Long id )
	{
		for( OrganizadorEntity organizador : data )
		{
			if( organizador.getId( ).equals( id ) )
			{
				return true;
			}
		}
		return false;
	}
        
        @Test
	public void getOrganizador( ) throws Exception
	{
		OrganizadorEntity entity = data.get( 0 );
		OrganizadorEntity returned = logic.getOrganizador(entity.getId( ) );
		
		Assert.assertNotNull( returned );
		Assert.assertEquals( entity.getCorreo( ), returned.getCorreo( ) );
		Assert.assertEquals( entity.getContrasena( ), returned.getContrasena( ) );
		Assert.assertEquals( entity.getFoto( ), returned.getFoto( ) );
	}
        
        @Test
	public void updateOrganizador( ) throws Exception
	{
		OrganizadorEntity entity = data.get( 0 );
		
		OrganizadorEntity upEntity = factory.manufacturePojo( OrganizadorEntity.class );
		upEntity.setId( entity.getId( ) );
		
		OrganizadorEntity returned = logic.updateOrganizador(upEntity );
		Assert.assertNotNull( returned );
		
		OrganizadorEntity retrieved = em.find( OrganizadorEntity.class, entity.getId( ) );
		Assert.assertNotNull( retrieved );
		
		Assert.assertEquals( upEntity.getCorreo( ), retrieved.getCorreo( ) );
		Assert.assertEquals( upEntity.getContrasena( ), retrieved.getContrasena( ) );
		Assert.assertEquals( upEntity.getFoto( ), retrieved.getFoto( ) );
	}
        
        @Test
	public void deleteOrganizador( ) throws Exception
	{
		OrganizadorEntity entity = data.get( 0 );
		logic.deleteOrganizador(entity.getId( ) );
		OrganizadorEntity deleted = em.find( OrganizadorEntity.class, entity.getId( ) );
		Assert.assertNull( deleted );
	}
        
        @Test
	public void getFerias( ) throws Exception
	{
		OrganizadorEntity entity = data.get( 0 );
		List<FeriaEntity> ferias = logic.getFerias(entity.getId( ) );
		
		for( FeriaEntity feria : ferias )
		{
			Assert.assertNotNull(feria );
		}
	}
        
        @Test
	public void removeFeria( ) throws Exception
	{
		OrganizadorEntity entity = data.get( 0 );
		FeriaEntity main = entity.getFerias().get( 0 );
		
		logic.removeFeria(entity.getId( ), main.getId( ) );
		
		OrganizadorEntity organizador = em.find( OrganizadorEntity.class, entity.getId( ) );
		Assert.assertNotNull(organizador);
		
		FeriaEntity feria = em.find( FeriaEntity.class, main.getId( ) );
		Assert.assertNull( feria );
	}
        
        
	
}


