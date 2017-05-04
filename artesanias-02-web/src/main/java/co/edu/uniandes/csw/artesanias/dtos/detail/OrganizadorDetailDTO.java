/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.dtos.OrganizadorDTO;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author IVAN
 */
@XmlRootElement
public class OrganizadorDetailDTO extends OrganizadorDTO
{
	private List<FeriaDTO> ferias;
	
	public OrganizadorDetailDTO( )
	{
		
	}
	
	public OrganizadorDetailDTO( OrganizadorEntity entity )
	{
		super( entity );
		if( entity != null )
		{
                    this.ferias = new LinkedList<>();
			for( FeriaEntity feria : entity.getFerias( ) )
			{
				this.ferias.add( new FeriaDTO( feria ) );
			}
		}
	}
	
	public List<FeriaDTO> getFerias( )
	{
		return ferias;
	}
	
	public void setArtesanos( List<FeriaDTO> ferias )
	{
		this.ferias = ferias;
	}
	
	@Override
	public OrganizadorEntity toEntity( )
	{
		OrganizadorEntity entity = super.toEntity( );
		List<FeriaEntity> lista = new LinkedList<>( );
		for( FeriaDTO feria : ferias )
		{
			lista.add( feria.toEntity( ) );
		}
		entity.setFerias( lista );
		return entity;
	}
}