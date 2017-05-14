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

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.ReviewDTO;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase DTO (Data Transfer Object) Detallado que representa un Review
 *
 * @author d.narvaez11
 * @see ReviewEntity
 * @see ReviewDTO
 */
@XmlRootElement
public class ReviewDetailDTO extends ReviewDTO
{
	/**
	 * Artesano al cual van dirigidos los Reviews
	 */
	private ArtesanoDTO artesano;
	
	/**
	 * Builds an empty Review Detail
	 */
	public ReviewDetailDTO( )
	{
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ReviewDTO by the fields from the ReviewEntity given
	 *
	 * @param entity ReviewEntity to fill up the ReviewDTO
	 */
	public ReviewDetailDTO( ReviewEntity entity )
	{
		if( entity != null )
		{
			this.artesano = new ArtesanoDTO( entity.getArtesano( ) );
		}
	}
	
	/**
	 * Retrieves a ReviewEntity with the fields of this ReviewDTO
	 */
	public ReviewEntity toEntity( )
	{
		ReviewEntity entity = super.toEntity( );
		entity.setArtesano( this.artesano.toEntity( ) );
		return entity;
	}
	
	/**
	 * Retrieves the artesano of the ReviewDetailDTO
	 *
	 * @return The artesano of the ReviewDetailDTO
	 */
	public ArtesanoDTO getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ReviewDetailDTO by the one given by parameter
	 *
	 * @param artesano The new artesano of the ReviewDetailDTO
	 */
	public void setArtesano( ArtesanoDTO artesano )
	{
		this.artesano = artesano;
	}
}