/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ja.espinosa12
 */

@XmlRootElement
public class StandDetailDTO extends StandDTO{
    
    public StandDetailDTO()
    {
        super();
    }
    
    public StandDetailDTO( StandEntity entity)
    {
        super(entity);
    }
    
    public StandEntity toEntity()
    {
        return super.toEntity();
    }
    
}
