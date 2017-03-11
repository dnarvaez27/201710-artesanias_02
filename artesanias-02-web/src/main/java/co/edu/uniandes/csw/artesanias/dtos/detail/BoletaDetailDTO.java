/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.BoletaDTO;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;

/**
 *
 * @author Miller
 */
public class BoletaDetailDTO extends BoletaDTO {

    public BoletaDetailDTO() {super();}

    public BoletaDetailDTO(BoletaEntity entity) {super(entity);}
    
    public BoletaEntity toEntity() {return super.toEntity();}

}
