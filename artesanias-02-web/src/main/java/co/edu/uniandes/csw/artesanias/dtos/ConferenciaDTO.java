/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import java.sql.Time;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ia.salazar
 */
@XmlRootElement
public class ConferenciaDTO {
    
    /**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Long id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String conferencista;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String tema;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaInicio;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaFin;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Time horaInicio;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Time horaFin;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public FeriaEntity feria;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public SalonEntity salon;

    public void setId(Long id) {
        this.id = id;
    }

    public void setConferencista(String conferencista) {
        this.conferencista = conferencista;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Long getId() {
        return id;
    }

    public String getConferencista() {
        return conferencista;
    }

    public String getTema() {
        return tema;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public FeriaEntity getFeria() {
        return feria;
    }

    public SalonEntity getSalon() {
        return salon;
    }

    public void setFeria(FeriaEntity feria) {
        this.feria = feria;
    }

    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }
    public ConferenciaDTO(){
        
    }
    
       public ConferenciaDTO(ConferenciaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.tema = entity.getTema();
            this.fechaFin = entity.getFechaFin();
            this.fechaInicio = entity.getFechaInicio();
            this.conferencista = entity.getConferencista();
            this.horaInicio=entity.getHoraInicio();
            this.horaFin=entity.getHoraFin();
            this.salon=entity.getSalon();
            this.feria=entity.getFeria();
                    
                    
        }
    }

    /**
     *
     * @return
     */
    public ConferenciaEntity toEntity() {
        ConferenciaEntity entity = new ConferenciaEntity();

        entity.setId(this.getId());
       entity.setTema(this.getTema());
       entity.setFechaFin(this.getFechaFin());
       entity.setFechaInicio(this.getFechaInicio());
       entity.setConferencista(this.getConferencista());
       entity.setHoraInicio(this.getHoraInicio());
       entity.setHoraFin(this.getHoraFin());
       entity.setSalon(this.getSalon());
       entity.setFeria(this.getFeria());
        return entity;
    }
}