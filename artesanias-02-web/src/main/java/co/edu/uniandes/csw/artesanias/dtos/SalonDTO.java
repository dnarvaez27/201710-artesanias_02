/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ia.salazar
 */
@XmlRootElement
public class SalonDTO
{
	
	private Long id;
	
	private Integer numero;
	
	private Integer capacidad;
	

	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}
	
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
	//    public void setConferencia(Conferencia conferencia) {
	//        this.conferencia = conferencia;
	//    }
	
	//    public void setPabellon(Pabellon pabellon) {
	//        this.pabellon = pabellon;
	//    }
	
	public Long getId( )
	{
		return id;
	}
	
	public Integer getNumero( )
	{
		return numero;
	}
	
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	//	public Conferencia getConferencia( )
	//	{
	//		return conferencia;
	//	}
	//
	//	public Pabellon getPabellon( )
	//	{
	//		return pabellon;
	//	}
	
	public SalonDTO( )
	{
		
	}
	
	public SalonDTO( SalonEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.numero = entity.getNumero( );
			this.capacidad = entity.getCapacidad( );
			
		}
	}
	
	public SalonEntity toEntity( )
	{
		SalonEntity entity = new SalonEntity( );
		
		entity.setId( this.getId( ) );
		entity.setNumero( this.getNumero( ) );
		entity.setCapacidad( this.getCapacidad( ) );
		
		return entity;
	}
}
