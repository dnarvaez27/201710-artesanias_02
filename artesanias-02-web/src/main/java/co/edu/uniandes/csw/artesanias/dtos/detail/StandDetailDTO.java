/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ja.espinosa12
 */
@XmlRootElement
public class StandDetailDTO extends StandDTO
{
	private List<ArtesanoDTO> artesanos;
	
	public StandDetailDTO( )
	{
		super( );
	}
	
	public StandDetailDTO( StandEntity entity )
	{
		super( entity );
		artesanos = new LinkedList<>( );
		if( entity != null )
		{
			for( ArtesanoEntity artesanoEntity : entity.getArtesanos( ) )
			{
				artesanos.add( new ArtesanoDTO( artesanoEntity ) );
			}
		}
	}
	
	public StandEntity toEntity( )
	{
		StandEntity entity = super.toEntity( );
		List<ArtesanoEntity> artesanoEntities = new LinkedList<>( );
		for( ArtesanoDTO artesano : artesanos )
		{
			artesanoEntities.add( artesano.toEntity( ) );
		}
		entity.setArtesanos( artesanoEntities );
		return entity;
	}
}