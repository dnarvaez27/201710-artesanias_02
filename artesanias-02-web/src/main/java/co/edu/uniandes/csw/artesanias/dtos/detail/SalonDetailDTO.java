/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.ConferenciaDTO;
import co.edu.uniandes.csw.artesanias.dtos.SalonDTO;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ia.salazar
 */
@XmlRootElement
public class SalonDetailDTO extends SalonDTO {

    private List<ConferenciaDTO> conferencias;

    private PabellonDTO pabellon;

    public SalonDetailDTO() {
        super();
    }

    public SalonDetailDTO(SalonEntity entity) {
        super(entity);
        if (entity != null) {
            for (ConferenciaEntity conferenciaEntity : entity.getConferencia()) {
                this.conferencias.add(new ConferenciaDTO(conferenciaEntity));
            }

            this.pabellon = new PabellonDTO(entity.getPabellon());
        }
    }

    /**
     * Retrieves the pabellon of the SalonDetailDTO
     *
     * @return The pabellon of the SalonDetailDTO
     */
    public PabellonDTO getPabellon() {
        return pabellon;
    }

    /**
     * Updates the pabellon of the SalonDetailDTO by the one given by parameter
     *
     * @param pabellon The new pabellon of the SalonDetailDTO
     */
    public void setPabellon(PabellonDTO pabellon) {
        this.pabellon = pabellon;
    }

    @Override
    public SalonEntity toEntity() {
        SalonEntity entity = super.toEntity();
        List<ConferenciaEntity> lst = new LinkedList<>();
        for (ConferenciaDTO conferencia : conferencias) {
            lst.add(conferencia.toEntity());
        }
        entity.setConferencia(lst);
        entity.setPabellon(pabellon != null ? pabellon.toEntity() : null);
        return entity;
    }

    public List<ConferenciaDTO> getConferencia() {
        return conferencias;
    }

    public void setConferencia(List<ConferenciaDTO> conferencia) {
        this.conferencias = conferencia;
    }
}
