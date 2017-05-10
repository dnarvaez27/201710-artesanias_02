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

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
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
public class FeriaPersistenceTest extends PersistenceTest<FeriaEntity> {

    @Inject
    private FeriaPersistence persistence;

    private EspacioEntity e1;
    private EspacioEntity e2;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FeriaEntity.class.getPackage())
                .addPackage(FeriaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    protected void clearData() {
        em.createQuery("DELETE FROM FeriaEntity").executeUpdate();
    }

    protected void insertData() {
        PodamFactory factory = new PodamFactoryImpl();

        e1 = e1 == null ? factory.manufacturePojo(EspacioEntity.class) : e1;
        e2 = e2 == null ? factory.manufacturePojo(EspacioEntity.class) : e2;

        List<FeriaEntity> ferias1 = new LinkedList<FeriaEntity>();
        List<FeriaEntity> ferias2 = new LinkedList<FeriaEntity>();

        for (int i = 0; i < 10; i++) {
            FeriaEntity entity = factory.manufacturePojo(FeriaEntity.class);
            entity.setEspacio(i % 2 == 0 ? e1 : e2);

            em.persist(entity);
            data.add(entity);

            if (i % 2 == 0) {
                ferias1.add(entity);
            } else {
                ferias2.add(entity);
            }
        }
        e1.setFerias(ferias1);
        e2.setFerias(ferias2);
    }

    @Test
    public void create() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();

        FeriaEntity newEntity = factory.manufacturePojo(FeriaEntity.class);
        e1 = factory.manufacturePojo(EspacioEntity.class);
        e2 = factory.manufacturePojo(EspacioEntity.class);

        FeriaEntity result = persistence.create(newEntity);
        Assert.assertNotNull(result);

        FeriaEntity entity = em.find(FeriaEntity.class, result.getId());
        Assert.assertNotNull(entity);
    }

    @Test
    public void find() throws Exception {
        FeriaEntity entity = e1.getFerias().get(0);
        FeriaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        entity = e2.getFerias().get(0);
        newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    @Test
    public void findAll() throws Exception {
        List<FeriaEntity> finded = persistence.findAll();
        Assert.assertEquals(data.size(), finded.size());
        for (FeriaEntity currentEntity : finded) {
            boolean found = false;
            for (FeriaEntity entity : data) {
                if (currentEntity.getId().equals(entity.getId())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void update() throws Exception {
        FeriaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FeriaEntity upEntity = factory.manufacturePojo(FeriaEntity.class);
        upEntity.setId(entity.getId());
        persistence.update(upEntity);
        FeriaEntity resp = em.find(FeriaEntity.class, entity.getId());
        Assert.assertEquals(upEntity.getId(), resp.getId());
    }

    @Test
    public void delete() throws Exception {
        FeriaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        FeriaEntity deleted = em.find(FeriaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
