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

import co.edu.uniandes.csw.artesanias.dtos.ArtesaniaDTO;
import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase DTO (Data Transfer Object) detallado que representa una Artesania
 *
 * @author d.narvaez11
 * @see ArtesaniaEntity
 * @see ArtesaniaDTO
 */
@XmlRootElement
public class ArtesaniaDetailDTO extends ArtesaniaDTO implements Serializable
{
	/**
	 * Artesano al cual pertenece la artesania
	 */
	private ArtesanoDTO artesano;
	
	/**
	 * Builds an empty Artesania Detail
	 */
	public ArtesaniaDetailDTO( )
	{
		super( );
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ArtesaniiaDTO by the fields from the ArtesaniiaEntity given
	 *
	 * @param entity ArtesaniiaEntity to fill up the ArtesaniiaDTO
	 */
	public ArtesaniaDetailDTO( ArtesaniaEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			this.artesano = new ArtesanoDTO( entity.getArtesano( ) );
		}
	}
	
	/**
	 * Retrieves the artesano of the ArtesaniaDetailDTO
	 *
	 * @return The artesano of the ArtesaniaDetailDTO
	 */
	public ArtesanoDTO getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ArtesaniaDetailDTO by the one given by parameter
	 *
	 * @param artesano The new artesano of the ArtesaniaDetailDTO
	 */
	public void setArtesano( ArtesanoDTO artesano )
	{
		this.artesano = artesano;
	}
	
	/**
	 * Retrieves a ArtesaniaEntity with the fields of this ArtesaniaDTO
	 */
	public ArtesaniaEntity toEntity( )
	{
		ArtesaniaEntity entity = super.toEntity( );
		entity.setArtesano( artesano.toEntity( ) );
		return entity;
	}
}