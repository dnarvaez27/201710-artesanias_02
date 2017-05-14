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
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.BoletaDTO;
import co.edu.uniandes.csw.artesanias.dtos.EspectadorDTO;
import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) Detallado que representa un Espectador
 *
 * @author d.narvaez11
 * @see EspectadorEntity
 * @see EspectadorDTO
 */
@XmlRootElement
public class EspectadorDetailDTO extends EspectadorDTO implements Serializable
{
	/**
	 * Lista con las Boletas que el espectador ha adquirido
	 */
	private List<BoletaDTO> boletas;
	
	/**
	 * Builds an empty Espectador Detail
	 */
	public EspectadorDetailDTO( )
	{
            
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an EspectadorDTO by the fields from the EspectadorEntity given
	 *
	 * @param entity EspectadorEntity to fill up the EspectadorDTO
	 */
	public EspectadorDetailDTO( EspectadorEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			this.boletas = new LinkedList<>( );
			for( BoletaEntity boleta : entity.getBoletas( ) )
			{
				boletas.add( new BoletaDTO( boleta ) );
			}
		}
	}
	
	/**
	 * Retrieves a EspectadorEntity with the fields of this EspectadorDTO
	 */
	public EspectadorEntity toEntity( )
	{
		EspectadorEntity entity = super.toEntity( );
		List<BoletaEntity> boletasEntities = new LinkedList<>( );
		for( BoletaDTO boleta : boletas )
		{
			boletasEntities.add( boleta.toEntity( ) );
		}
		entity.setBoletas( boletasEntities );
		return entity;
	}
	
	/**
	 * Retrieves the boletas of the EspectadorDetailDTO
	 *
	 * @return The boletas of the EspectadorDetailDTO
	 */
	public List<BoletaDTO> getBoletas( )
	{
		return boletas;
	}
	
	/**
	 * Updates the boletas of the EspectadorDetailDTO by the one given by parameter
	 *
	 * @param boletas The new boletas of the EspectadorDetailDTO
	 */
	public void setBoletas( List<BoletaDTO> boletas )
	{
		this.boletas = boletas;
	}
}