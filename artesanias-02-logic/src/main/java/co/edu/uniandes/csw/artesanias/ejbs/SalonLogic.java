/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.persistence.SalonPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author ia.salazar
 */
@Stateless
public class SalonLogic
{
	@Inject
	private SalonPersistence persistence;
	
	/**
	 * @return la lista de los salones.
	 */
	public List<SalonEntity> getSalones( )
	{
		return persistence.findAll( );
	}
	
	public List<SalonEntity> getSalonesFromPabellon( Long pabellonId )
	{
		return persistence.findAllFromPabellon( pabellonId );
	}
	
	public SalonEntity getSalon( Long id )
	{
		return persistence.find( id );
	}
	
	public SalonEntity createSalon( SalonEntity entity )
	{
		persistence.create( entity );
		return entity;
	}
	
	public SalonEntity updateSalon( SalonEntity entity )
	{
		return persistence.update( entity );
	}
	
	public void deleteSalon( Long id )
	{
		persistence.delete( id );
	}
}