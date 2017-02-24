/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ia.salazar
 */
@Stateless
public class SalonPersistence {
    
     @PersistenceContext(unitName="artesaniasPU")
    protected EntityManager em;
     
      public SalonEntity find(Long id) {
      
        return em.find(SalonEntity.class, id);
    }
      
      public List<SalonEntity> findAll() {
       
        Query q = em.createQuery("select u from EmployeeEntity u");
        return q.getResultList();
    }

    public SalonEntity create(SalonEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public SalonEntity update(SalonEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        SalonEntity entity = em.find(SalonEntity.class, id);
        em.remove(entity);
    }

}
