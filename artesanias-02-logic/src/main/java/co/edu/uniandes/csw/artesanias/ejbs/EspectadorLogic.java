/*
 * The MIT License
 *
 * Copyright 2017 d.narvaez11.
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

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.EspectadorPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Stateless
public class EspectadorLogic
{
	@Inject
	private EspectadorPersistence persistence;
	
	@Inject
	private BoletaLogic boletaLogic;
	
	/**
	 * Creates an Espectador in the Data Base
	 *
	 * @param entity Objet from EspectadorEntity with the new data.
	 * @return Objet from EspectadorEntity with the new data and ID.
	 * @throws co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException
	 */
	public EspectadorEntity createEspectador( EspectadorEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.create( entity );
	}
	
	/**
	 * Retrieves the list of the registers of Espectador.
	 *
	 * @return Collection of objects of EspectadorEntity.
	 */
	public List<EspectadorEntity> getEspectadors( )
	{
		return persistence.findAll( );
	}
	
	/**
	 * Retrieves the data of an instance of Espectador by its ID.
	 *
	 * @param id Identifier of the instance to consult.
	 * @return Instance of EspectadorEntity with the data from the Espectador
	 * consulted.
	 */
	public EspectadorEntity getEspectador( Long id )
	{
		EspectadorEntity entity = persistence.find( id );
		return entity;
	}
	
	/**
	 * Updates the information from an instance of Espectador.
	 *
	 * @param entity Instance of EspectadorEntity with the new data.
	 * @return Instance of EspectadorEntity with the updated data.
	 * @throws co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException
	 */
	public EspectadorEntity updateEspectador( EspectadorEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	/**
	 * Deletes an instance of Espectador from the Data Base.
	 *
	 * @param id Identifier of the instance to remove.
	 */
	public void deleteEspectador( Long id )
	{
		persistence.delete( id );
	}
	
	//--------------------------------------------------------------------------
	// Métodos de boleta
	//--------------------------------------------------------------------------
	
	public BoletaEntity getBoleta( Long idEspectador, Long idBoleta )
	{
		for( BoletaEntity b : persistence.find( idEspectador ).getBoletas( ) )
		{
			if( b.getId( ).equals( idBoleta ) )
			{
				return b;
			}
		}
		throw new IllegalArgumentException( "La boleta no está asociado a la espectador" );
	}
	
	public List<BoletaEntity> getBoletas( Long idEspectador )
	{
		return persistence.find( idEspectador ).getBoletas( );
	}
	
	public void removeBoleta( Long idEspectador, Long idBoleta )
	{
		BoletaEntity be = boletaLogic.getBoleta( idBoleta );
		be.setEspectador( null );
		persistence.find( idEspectador ).getBoletas( ).remove( be );
		boletaLogic.deleteBoleta( be.getId( ) );
	}
	
	public BoletaEntity addBoleta( Long idEspectador, BoletaEntity boleta ) throws BusinessLogicException
	{
		EspectadorEntity espectador = persistence.find( idEspectador );
		boleta.setEspectador( espectador );
		
		List<BoletaEntity> boletas = espectador.getBoletas( );
		if( boletas == null )
		{
			boletas = new LinkedList<>( );
		}
		boletas.add( boleta );
		espectador.setBoletas( boletas );
		
		boletaLogic.createBoleta( boleta );
		return boleta;
	}
	
	private void check( EspectadorEntity entity ) throws BusinessLogicException
	{
		if( entity.getCorreo( ) == null || entity.getCorreo( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "El correo no puede ser vacío", Response.Status.BAD_REQUEST );
		}
		if( entity.getContrasena( ) == null || entity.getContrasena( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "La contraseña no puede ser vacía", Response.Status.BAD_REQUEST );
		}
	}
}