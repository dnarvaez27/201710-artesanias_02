/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ia.salazar
 */
@XmlRootElement
public class SalonDetailDTO extends SalonDTO{
    
     public SalonDetailDTO() {
        super();
    }
     
     public SalonDetailDTO(SalonEntity entity) {
        super(entity);
        
    }
     
        @Override
    public SalonEntity toEntity() {
        SalonEntity entity = super.toEntity();
        return entity;
    }
     
}
