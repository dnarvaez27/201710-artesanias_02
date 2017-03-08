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
    
   
    public List<ConferenciaEntity> getConferencias() {
        return persistence.findAll();
    }
    public ConferenciaEntity getConferencia(Long id){
        return persistence.find(id);
    }
  
    
    public ConferenciaEntity createConferencia(ConferenciaEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
     public ConferenciaEntity updateConferencia(ConferenciaEntity entity) {
        return persistence.update(entity);
    }
     
      public void deleteConferencia(Long id) {
        persistence.delete(id);
    } 
}
