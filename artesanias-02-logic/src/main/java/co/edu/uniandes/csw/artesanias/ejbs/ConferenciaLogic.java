/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.SalonPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ia.salazar
 */
@Stateless
public class ConferenciaLogic {
     @Inject private ConferenciaPersistence persistence;
    
   
    public List<ConferenciaEntity> getEmployees() {
        return persistence.findAll();
    }
    
  
    
    public ConferenciaEntity createEmployee(ConferenciaEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
     public ConferenciaEntity updateEmployee(ConferenciaEntity entity) {
        return persistence.update(entity);
    }
     
      public void deleteEmployee(Long id) {
        persistence.delete(id);
    } 
}
