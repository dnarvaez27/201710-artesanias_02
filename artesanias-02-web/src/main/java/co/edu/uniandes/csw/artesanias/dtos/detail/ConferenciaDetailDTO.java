/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.*;
import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author IVAN
 */
@XmlRootElement
public class ConferenciaDetailDTO extends ConferenciaDTO
{
	private FeriaDTO feria;
	
	private SalonDetailDTO salon;
	
	public ConferenciaDetailDTO( )
	{
		super( );
	}
	
	public ConferenciaDetailDTO( ConferenciaEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			salon = new SalonDetailDTO( entity.getSalon( ) );
			feria = new FeriaDTO( entity.getFeria( ) );
		}
	}
	
	@Override
	public ConferenciaEntity toEntity( )
	{
		ConferenciaEntity entity = super.toEntity( );
		entity.setSalon( salon.toEntity( ) );
		entity.setFeria( feria.toEntity( ) );
		return entity;
	}
	
	public FeriaDTO getFeria( )
	{
		return feria;
	}
	
	public SalonDetailDTO getSalon( )
	{
		return salon;
	}
	
	public void setFeria( FeriaDTO feria )
	{
		this.feria = feria;
	}
	
	public void setSalon( SalonDetailDTO salon )
	{
		this.salon = salon;
	}
}