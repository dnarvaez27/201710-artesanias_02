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
public class SalonDetailDTO extends SalonDTO
{
	private ConferenciaDTO conferencia;
	
	private PabellonDTO pabellon;
	
	public SalonDetailDTO( )
	{
		super( );
	}
	
	public SalonDetailDTO( SalonEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			this.conferencia = new ConferenciaDTO( entity.getConferencia( ) );
			this.pabellon = new PabellonDTO( entity.getPabellon( ) );
		}
	}
	
	@Override
	public SalonEntity toEntity( )
	{
		SalonEntity entity = super.toEntity( );
		entity.setConferencia( conferencia.toEntity( ) );
		entity.setPabellon( pabellon.toEntity( ) );
		return entity;
	}
	
	public ConferenciaDTO getConferencia( )
	{
		return conferencia;
	}
	
	public void setConferencia( ConferenciaDTO conferencia )
	{
		this.conferencia = conferencia;
	}
}