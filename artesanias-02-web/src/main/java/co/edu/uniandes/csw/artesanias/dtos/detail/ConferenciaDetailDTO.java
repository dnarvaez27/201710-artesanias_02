/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.*;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IVAN
 */
@XmlRootElement
public class ConferenciaDetailDTO extends ConferenciaDTO{
    
    

    
     public ConferenciaDetailDTO() {
        super();
    }
     
     public ConferenciaDetailDTO(ConferenciaEntity entity) {
        super(entity);
        
    }
     
        @Override
    public ConferenciaEntity toEntity() {
        ConferenciaEntity entity = super.toEntity();
        return entity;
    }
}