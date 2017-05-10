/*
 * The MIT License
 *
 * Copyright 2017 Miller.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Miller
 */
@RunWith(Arquillian.class)
public class BoletaPersistenceTest extends PersistenceTest<BoletaEntity> {

    @Inject
    private BoletaPersistence persistence;

    private FeriaEntity f1;
    private FeriaEntity f2;
    private EspectadorEntity e1;
    private EspectadorEntity e2;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BoletaEntity.class.getPackage())
                .addPackage(BoletaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    protected void clearData() {
        em.createQuery("DELETE FROM EspacioEntity ").executeUpdate();
    }

    protected void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        f1 = f1 == null ? factory.manufacturePojo(FeriaEntity.class) : f1;
        f2 = f2 == null ? factory.manufacturePojo(FeriaEntity.class) : f2;
        e1 = e1 == null ? factory.manufacturePojo(EspectadorEntity.class) : e1;
        e2 = e2 == null ? factory.manufacturePojo(EspectadorEntity.class) : e2;

        List<BoletaEntity> boletas1 = new LinkedList<BoletaEntity>();
        List<BoletaEntity> boletas2 = new LinkedList<BoletaEntity>();

        for (int i = 0; i < 10; i++) {
            BoletaEntity entity = factory.manufacturePojo(BoletaEntity.class);
            entity.setFeria(i % 2 == 0 ? f1 : f2);
            entity.setEspectador(i % 2 == 0 ? e2 : e1);

            em.persist(entity);
            data.add(entity);

            if (i % 2 == 0) {
                boletas1.add(entity);
            } else {
                boletas2.add(entity);
            }
        }
        f1.setBoletas(boletas1);
        e1.setBoletas(boletas2);
        f2.setBoletas(boletas2);
        e2.setBoletas(boletas1);
    }

    @Test
    public void create() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();

        BoletaEntity newEntity = factory.manufacturePojo(BoletaEntity.class);
        f1 = factory.manufacturePojo(FeriaEntity.class);
        f2 = factory.manufacturePojo(FeriaEntity.class);
        e1 = factory.manufacturePojo(EspectadorEntity.class);
        e2 = factory.manufacturePojo(EspectadorEntity.class);

        BoletaEntity result = persistence.create(newEntity);
        Assert.assertNotNull(result);

        BoletaEntity entity = em.find(BoletaEntity.class, result.getId());
        Assert.assertNotNull(entity);
    }

    @Test
    public void find() throws Exception {
        BoletaEntity entity = e1.getBoletas().get(0);
        BoletaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        entity = f1.getBoletas().get(0);
        newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    @Test
    public void findAll() {
        List<BoletaEntity> finded = persistence.findAll();
        Assert.assertEquals(data.size(), finded.size());
        for (BoletaEntity boletaEntity : finded) {
            boolean found = false;
            for (BoletaEntity entity : data) {
                if (boletaEntity.getId().equals(entity.getId())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void update() {
        BoletaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BoletaEntity upEntity = factory.manufacturePojo(BoletaEntity.class);
        upEntity.setId(entity.getId());

        persistence.update(upEntity);

        BoletaEntity resp = em.find(BoletaEntity.class, entity.getId());
        Assert.assertEquals(upEntity.getId(), resp.getId());
    }

    @Test
    public void delete() {
        BoletaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        BoletaEntity deleted = em.find(BoletaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
