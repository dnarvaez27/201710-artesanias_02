/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
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
	
	private PabellonDTO pabellon;
	
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
			
			pabellon = new PabellonDTO( entity.getPabellon( ) );
		}
	}
	
	public StandEntity toEntity( )
	{
		StandEntity entity = super.toEntity( );
		List<ArtesanoEntity> artesanoEntities = new LinkedList<>( );
		if( artesanos != null )
		{
			for( ArtesanoDTO artesano : artesanos )
			{
				artesanoEntities.add( artesano.toEntity( ) );
			}
		}
		entity.setArtesanos( artesanoEntities );
		entity.setPabellon( pabellon.toEntity( ) );
		return entity;
	}
	
	/**
	 * Retrieves the artesanos of the StandDetailDTO
	 *
	 * @return The artesanos of the StandDetailDTO
	 */
	public List<ArtesanoDTO> getArtesanos( )
	{
		return artesanos;
	}
	
	/**
	 * Updates the artesanos of the StandDetailDTO by the one given by parameter
	 *
	 * @param artesanos The new artesanos of the StandDetailDTO
	 */
	public void setArtesanos( List<ArtesanoDTO> artesanos )
	{
		this.artesanos = artesanos;
	}
	
	/**
	 * Retrieves the pabellon of the StandDetailDTO
	 *
	 * @return The pabellon of the StandDetailDTO
	 */
	public PabellonDTO getPabellon( )
	{
		return pabellon;
	}
	
	/**
	 * Updates the pabellon of the StandDetailDTO by the one given by parameter
	 *
	 * @param pabellon The new pabellon of the StandDetailDTO
	 */
	public void setPabellon( PabellonDTO pabellon )
	{
		this.pabellon = pabellon;
	}
}