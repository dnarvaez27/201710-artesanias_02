/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class FeriaDetailDTO extends FeriaDTO {
    
    public FeriaDetailDTO() {super();}
    
    public FeriaDetailDTO(FeriaEntity entity) {super(entity);}

    @Override
    public FeriaEntity toEntity() {return super.toEntity();}
}
