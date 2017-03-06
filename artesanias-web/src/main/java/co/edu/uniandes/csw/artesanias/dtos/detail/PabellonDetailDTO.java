/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ja.espinosa12
 */
@XmlRootElement
public class PabellonDetailDTO extends PabellonDTO
{
    public PabellonDetailDTO()
    {
        super();
    }
    
    public PabellonDetailDTO( PabellonEntity entity )
    {
        super(entity);
    }
    
    public PabellonEntity toEntity()
    {
        return super.toEntity();
    }
}
