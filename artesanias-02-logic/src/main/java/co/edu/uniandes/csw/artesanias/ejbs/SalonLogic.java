/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

/**
 *
 * @author ia.salazar
 */

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.persistence.SalonPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SalonLogic {
    
     @Inject private SalonPersistence persistence;
    
     
     /**
      * 
      * @return  la lista de los salones.
      */
    public List<SalonEntity> getEmployees() {
        return persistence.findAll();
    }
    
  
    
    public SalonEntity createEmployee(SalonEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
     public SalonEntity updateEmployee(SalonEntity entity) {
        return persistence.update(entity);
    }
     
      public void deleteEmployee(Long id) {
        persistence.delete(id);
    }
}
