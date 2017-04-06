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
	
        /**
         * Pabellon al cual pertenece el stand
         */
	private PabellonDTO pabellon;
	
	public StandDetailDTO( )
	{
		super( );
	}
	
        /**
         * Genera un nuevo stand con el StandEntity ingresado
         * @param entity 
         */
	public StandDetailDTO( StandEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			pabellon = new PabellonDTO( entity.getPabellon( ) );
		}
	}
	
        /**
         * Convierte el stand actual a un StandEntity
         * @return entity
         */
	public StandEntity toEntity( )
	{
		StandEntity entity = super.toEntity( );
		List<ArtesanoEntity> artesanoEntities = new LinkedList<>( );
		entity.setPabellon( pabellon.toEntity( ) );
		return entity;
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