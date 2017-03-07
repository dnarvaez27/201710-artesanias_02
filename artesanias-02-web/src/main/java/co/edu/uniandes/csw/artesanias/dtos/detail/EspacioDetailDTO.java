/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import javax.xml.bind.annotation.XmlRootElement;
import co.edu.uniandes.csw.artesanias.dtos.*;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class EspacioDetailDTO extends EspacioDTO {

    private List<FeriaDTO> ferias;

    private CiudadDTO ciudad;

    public EspacioDetailDTO() {
        super();
    }

    public EspacioDetailDTO(EspacioEntity entity) {
        super(entity);
        if (entity == null)
            return;
        for (FeriaEntity feria : entity.getFerias())
            ferias.add(new FeriaDTO(feria));
        ciudad = new CiudadDTO(entity.getCiudad());
    }

    @Override
    public EspacioEntity toEntity() {
        EspacioEntity entity = super.toEntity();
        entity.setCiudad(this.ciudad.toEntity());
        entity.setFerias(listDtoToEntity(ferias));
        return entity;
    }

    public List<FeriaDTO> getFerias() {
        return ferias;
    }

    public void setFerias(List<FeriaDTO> ferias) {
        this.ferias = ferias;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }
    
    private List<FeriaEntity> listDtoToEntity(List<FeriaDTO> ferias) {
        List<FeriaEntity> entities = new LinkedList<FeriaEntity>();
        for (FeriaDTO feria : ferias)
            entities.add(feria.toEntity());
        return entities;
    }
}
