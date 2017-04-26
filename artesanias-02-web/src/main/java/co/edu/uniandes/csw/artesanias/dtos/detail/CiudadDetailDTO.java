/*
 * The MIT License
 *
 * Copyright 2017 Miller.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
        espacios = new LinkedList<EspacioDTO>();
        artesanos = new LinkedList<ArtesanoDTO>();
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
