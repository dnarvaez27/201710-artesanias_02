/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
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
public class ConferenciaPersistence {
    
    @PersistenceContext(unitName="artesaniaPU")
    protected EntityManager em;
    
      public ConferenciaEntity find(Long id) {
      
        return em.find(ConferenciaEntity.class, id);
    }
      
      public List<ConferenciaEntity> findAll() {
       
        Query q = em.createQuery("select u from EmployeeEntity u");
        return q.getResultList();
    }

    public ConferenciaEntity create(ConferenciaEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public ConferenciaEntity update(ConferenciaEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        ConferenciaEntity entity = em.find(ConferenciaEntity.class, id);
        em.remove(entity);
    }

}
