/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.SalonPersistence;


import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

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
	
	public SalonEntity createSalon( SalonEntity entity ) throws BusinessLogicException
	{
		check( entity );
		persistence.create( entity );
		return entity;
	}
	
	public SalonEntity updateSalon( SalonEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	public void deleteSalon( Long id )
	{
		persistence.delete( id );
	}
	
	private void check( SalonEntity entity ) throws BusinessLogicException
	{
		if( entity.getNumero( ) < 0 )
		{
			throw new BusinessLogicException( "El número del Salón debe ser positivo", Response.Status.BAD_REQUEST );
		}
		if( entity.getCapacidad( ) <= 0 )
		{
			throw new BusinessLogicException( "La capacidad del Salón debe ser mayor a 0", Response.Status.BAD_REQUEST );
		}
	}

   
}