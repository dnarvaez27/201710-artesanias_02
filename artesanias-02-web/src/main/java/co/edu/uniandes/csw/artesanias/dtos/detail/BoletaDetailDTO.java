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

import co.edu.uniandes.csw.artesanias.dtos.BoletaDTO;
import co.edu.uniandes.csw.artesanias.dtos.EspectadorDTO;
import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class BoletaDetailDTO extends BoletaDTO {
    
    private FeriaDTO feria;
    
    private EspectadorDTO espectador;

    public BoletaDetailDTO() {super();}

    public BoletaDetailDTO(BoletaEntity entity) {
        super(entity);
        if (entity == null)
            return;
        this.feria = new FeriaDTO(entity.getFeria());
        this.espectador = new EspectadorDTO(entity.getEspectador());
    }

    public FeriaDTO getFeria() {
        return feria;
    }

    public void setFeria(FeriaDTO feria) {
        this.feria = feria;
    }

    public EspectadorDTO getEspectador() {
        return espectador;
    }

    public void setEspectador(EspectadorDTO espectador) {
        this.espectador = espectador;
    }
    
    public BoletaEntity toEntity() {
        BoletaEntity entity = super.toEntity();
        entity.setFeria(this.feria.toEntity());
        entity.setEspectador(this.espectador.toEntity());
        return entity;
    }

}
