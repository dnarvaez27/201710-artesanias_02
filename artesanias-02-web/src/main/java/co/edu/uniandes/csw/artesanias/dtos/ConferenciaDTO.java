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
 * @author ia.salazar
 */
@XmlRootElement
public class ConferenciaDTO
{
	private Long id;
	
	private String conferencista;
	
	private String tema;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private String horaInicio;
	
	private String horaFin;
	
	public ConferenciaDTO( )
	{
		
	}
	
	public ConferenciaDTO( ConferenciaEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.tema = entity.getTema( );
			this.fechaFin = entity.getFechaFin( );
			this.fechaInicio = entity.getFechaInicio( );
			this.conferencista = entity.getConferencista( );
			this.horaInicio = entity.getHoraInicio( );
			this.horaFin = entity.getHoraFin( );
			
		}
	}
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public void setConferencista( String conferencista )
	{
		this.conferencista = conferencista;
	}
	
	public void setTema( String tema )
	{
		this.tema = tema;
	}
	
	public void setFechaInicio( Date fechaInicio )
	{
		this.fechaInicio = fechaInicio;
	}
	
	public void setFechaFin( Date fechaFin )
	{
		this.fechaFin = fechaFin;
	}
	
	public void setHoraInicio( String horaInicio )
	{
		this.horaInicio = horaInicio;
	}
	
	public void setHoraFin( String horaFin )
	{
		this.horaFin = horaFin;
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public String getConferencista( )
	{
		return conferencista;
	}
	
	public String getTema( )
	{
		return tema;
	}
	
	public Date getFechaInicio( )
	{
		return fechaInicio;
	}
	
	public Date getFechaFin( )
	{
		return fechaFin;
	}
	
	public String getHoraInicio( )
	{
		return horaInicio;
	}
	
	public String getHoraFin( )
	{
		return horaFin;
	}
	
	public ConferenciaEntity toEntity( )
	{
		ConferenciaEntity entity = new ConferenciaEntity( );
		
		entity.setId( this.getId( ) );
		entity.setTema( this.getTema( ) );
		entity.setFechaFin( this.getFechaFin( ) );
		entity.setFechaInicio( this.getFechaInicio( ) );
		entity.setConferencista( this.getConferencista( ) );
		entity.setHoraInicio( this.getHoraInicio( ) );
		entity.setHoraFin( this.getHoraFin( ) );
		
		return entity;
	}
}