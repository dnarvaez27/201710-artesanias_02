package co.edu.uniandes.csw.artesanias.dtos.detail;
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
import co.edu.uniandes.csw.artesanias.dtos.*;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class FeriaDetailDTO extends FeriaDTO {
    
    private List<ArtesanoDTO> artesanos;
    
    private List<BoletaDTO> boletas;
    
    private List<ConferenciaDTO> conferencias;
    
    public FeriaDetailDTO() {super();}
    
    public FeriaDetailDTO(FeriaEntity entity) {
        super(entity);
        if (entity == null) return;
        for (ArtesanoEntity artesano : entity.getArtesanos())
            artesanos.add(new ArtesanoDTO(artesano));
        for (BoletaEntity boleta : entity.getBoletas())
            boletas.add(new BoletaDTO(boleta));
        for (ConferenciaEntity conferencia : entity.getConferencias())
            conferencias.add(new ConferenciaDTO(conferencia));
    }

    @Override
    public FeriaEntity toEntity() {
        FeriaEntity entity = super.toEntity();
        List<ArtesanoEntity> lart = new LinkedList<ArtesanoEntity>();
        for (ArtesanoDTO artesano : artesanos)
            lart.add(artesano.toEntity());
        List<BoletaEntity> lbol = new LinkedList<BoletaEntity>();
        for (BoletaDTO boleta : boletas)
            lbol.add(boleta.toEntity());
        List<ConferenciaEntity> lcon = new LinkedList<ConferenciaEntity>();
        for (ConferenciaDTO conferencia : conferencias)
            lcon.add(conferencia.toEntity());
        entity.setConferencias(lcon);
        entity.setArtesanos(lart);
        entity.setBoletas(lbol);
        return entity;
    }

    public List<ArtesanoDTO> getArtesanos() {
        return artesanos;
    }

    public void setArtesanos(List<ArtesanoDTO> artesanos) {
        this.artesanos = artesanos;
    }

    public List<BoletaDTO> getBoletas() {
        return boletas;
    }

    public void setBoletas(List<BoletaDTO> boletas) {
        this.boletas = boletas;
    }
    
    public List<ConferenciaDTO> getConferencias() {
        return conferencias;
    }

    public void setConferencias(List<ConferenciaDTO> conferencias) {
        this.conferencias = conferencias;
    }
}
