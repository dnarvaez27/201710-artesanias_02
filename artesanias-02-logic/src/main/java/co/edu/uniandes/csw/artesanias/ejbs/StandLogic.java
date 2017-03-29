/*
 * The MIT License
 *
 * Copyright 2017 ja.espinosa12.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.StandPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author ja.espinosa12
 */
@Stateless
public class StandLogic
{
	@Inject
	private StandPersistence persistence;
	
	public StandEntity getStand( Long pabellonId, Long id ) throws BusinessLogicException
	{
		StandEntity res = persistence.find( pabellonId, id );
		if( res != null )
		{
			return res;
		}
		throw new BusinessLogicException( String.format( "El stand %s no pertenece al pabelllon %s ", id, pabellonId ), Response.Status.NOT_FOUND );
	}
	
	public List<StandEntity> getStands( )
	{
		return persistence.findAll( );
	}
	
	public List<StandEntity> getStandsFromPabellon( Long pabellonId )
	{
		return persistence.findAllFromPabellon( pabellonId );
	}
	
	public StandEntity createStand( StandEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.create( entity );
	}
	
	public StandEntity updateStand( StandEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	public void deleteStand( Long id )
	{
		persistence.delete( id );
	}
	
	private void check( StandEntity entity ) throws BusinessLogicException
	{
		if( !entity.getDimensiones( ).matches( "[0-9]+x[0-9]+" ) )
		{
			throw new BusinessLogicException( "Se debe ingresar dimensiones válidas", Response.Status.BAD_REQUEST );
		}
		if( entity.getNumero( ) < 0 )
		{
			throw new BusinessLogicException( "El número del Stand debe corresponder a un numero positivo", Response.Status.BAD_REQUEST );
		}
		if( entity.getPrecio( ) < 0 )
		{
			throw new BusinessLogicException( "El precio del Stand debe ser un valor positivo", Response.Status.BAD_REQUEST );
		}
	}
}