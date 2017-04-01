/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.ConferenciaEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.ConferenciaPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author ia.salazar
 */
@Stateless
public class ConferenciaLogic
{
	@Inject
	private ConferenciaPersistence persistence;
	
	public List<ConferenciaEntity> getConferencias( )
	{
		return persistence.findAll( );
	}
	
	public ConferenciaEntity getConferencia( Long id )throws BusinessLogicException
	{
            ConferenciaEntity conf = persistence.find( id );
            if (conf!=null) {
                return conf;
            }

            throw new BusinessLogicException("No existe la conferencia buscada", Response.Status.NOT_FOUND);
	}
	
	public ConferenciaEntity createConferencia( ConferenciaEntity entity ) throws BusinessLogicException
	{
		check( entity );
		persistence.create( entity );
		return entity;
	}
	
	public ConferenciaEntity updateConferencia( ConferenciaEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	public void deleteConferencia( Long id ) throws BusinessLogicException
	{
            ConferenciaEntity conf = persistence.find( id );
            if (conf!=null) {
                persistence.delete( id );
            }

            throw new BusinessLogicException("No existe la conferencia buscada", Response.Status.NOT_FOUND);
		
	}
	
	private void check( ConferenciaEntity entity ) throws BusinessLogicException
	{
		if( entity.getFechaInicio( ).after( entity.getFechaFin( ) ) )
		{
			throw new BusinessLogicException( "La fecha de inicio de la conferencia debe ser estrictamente menor a la fecha de finalización", Response.Status.BAD_REQUEST );
		}
		if( entity.getTema( ) == null || entity.getTema( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "El tema de la conferencia no debe estar vacio", Response.Status.BAD_REQUEST );
		}
	}

    public List<ConferenciaEntity> getConferenciasFromsalon( Long idSalon) {
        
        return persistence.findAllFromSalon(idSalon);
    }

     public List<ConferenciaEntity> getConferenciasFromFeria( Long idSalon) {
        
        return persistence.findAllFromSalon(idSalon);
    }

   
}