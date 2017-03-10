/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.CiudadDTO;
import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Miller
 */
public class CiudadDetailDTO extends CiudadDTO {

    private List<EspacioDTO> espacios;
    private List<ArtesanoDTO> artesanos;

    public CiudadDetailDTO() {
        super();
        espacios = new LinkedList<EspacioDTO>();
        artesanos = new LinkedList<ArtesanoDTO>();
    }

    public CiudadDetailDTO(CiudadEntity entity) {
        super(entity);
        if (entity == null) return;
        for (EspacioEntity e : entity.getEspacios())
            espacios.add(new EspacioDTO(e));
        for (ArtesanoEntity a : entity.getArtesanos())
            artesanos.add(new ArtesanoDTO(a));
    }

    public CiudadEntity toEntity() {
        CiudadEntity entity = super.toEntity();
        List<ArtesanoEntity> lart = new LinkedList<ArtesanoEntity>();
        for (ArtesanoDTO artesano : artesanos)
            lart.add(artesano.toEntity());
        List<EspacioEntity> lbol = new LinkedList<EspacioEntity>();
        for (EspacioDTO espacio : espacios)
            lbol.add(espacio.toEntity());
        entity.setArtesanos(lart);
        entity.setEspacios(lbol);
        return entity;
    }

    public List<EspacioDTO> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<EspacioDTO> espacios) {
        this.espacios = espacios;
    }

    public List<ArtesanoDTO> getArtesanos() {
        return artesanos;
    }

    public void setArtesanos(List<ArtesanoDTO> artesanos) {
        this.artesanos = artesanos;
    }
}
