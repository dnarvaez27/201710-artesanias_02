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

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import javax.xml.bind.annotation.XmlRootElement;
import co.edu.uniandes.csw.artesanias.dtos.*;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
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
    
    private List<PabellonDTO> pabellones;

    public EspacioDetailDTO() {
        super();
    }

    public EspacioDetailDTO(EspacioEntity entity) {
        super(entity);
        if (entity == null)
            return;
        for (FeriaEntity feria : entity.getFerias())
            ferias.add(new FeriaDTO(feria));
        for (PabellonEntity pabellon : entity.getPabellones())
            pabellones.add(new PabellonDTO(pabellon));
        ciudad = new CiudadDTO(entity.getCiudad());
    }

    @Override
    public EspacioEntity toEntity() {
        EspacioEntity entity = super.toEntity();
        entity.setCiudad(this.ciudad.toEntity());
        entity.setFerias(listDtoToEntityF(ferias));
        entity.setPabellones(listDtoToEntityP(pabellones));
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

    public List<PabellonDTO> getPabellones() {
        return pabellones;
    }

    public void setPabellones(List<PabellonDTO> pabellones) {
        this.pabellones = pabellones;
    }
    
    private List<FeriaEntity> listDtoToEntityF(List<FeriaDTO> ferias) {
        List<FeriaEntity> entities = new LinkedList<FeriaEntity>();
        for (FeriaDTO feria : ferias)
            entities.add(feria.toEntity());
        return entities;
    }
    
    private List<PabellonEntity> listDtoToEntityP(List<PabellonDTO> pabellones) {
        List<PabellonEntity> entities = new LinkedList<PabellonEntity>();
        for (PabellonDTO pabellon : pabellones)
            entities.add(pabellon.toEntity());
        return entities;
    }
}
