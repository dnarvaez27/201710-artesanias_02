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
import co.edu.uniandes.csw.artesanias.dtos.CiudadDTO;
import co.edu.uniandes.csw.artesanias.dtos.ReviewDTO;
import co.edu.uniandes.csw.artesanias.dtos.asociacion.ArtesanoFeriaDTO;
import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;
import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;
import co.edu.uniandes.csw.artesanias.entities.asociaciones.ArtesanoFeriaAssociation;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) Detallado que representa un Artesano
 *
 * @author d.narvaez11
 * @see ArtesanoEntity
 * @see ArtesanoDTO
 */
@XmlRootElement
public class ArtesanoDetailDTO extends ArtesanoDTO
{
	/**
	 * Lista de Artesanias que ha realizado el Artesano
	 */
	private List<ArtesaniaDTO> artesanias;
	
	/**
	 * Lista de Reviews que ha recibido el Artesano
	 */
	private List<ReviewDTO> reviews;
	
	/**
	 * Ciudad de origen del Artesano
	 */
	private CiudadDTO ciudad;
	
	/**
	 * Stand al cual esta asignado el Artesano
	 */
	private List<ArtesanoFeriaDTO> ferias;
	
	/**
	 * Builds an empty Artesano Detail
	 */
	public ArtesanoDetailDTO( )
	{
		super( );
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ArtesanoDetailDTO by the fields from the ArtesanoDetailEntity given
	 *
	 * @param entity ArtesanoDetailEntity to fill up the ArtesanoDetailDTO
	 */
	public ArtesanoDetailDTO( ArtesanoEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			this.artesanias = new LinkedList<>( );
			this.reviews = new LinkedList<>( );
			this.ferias = new LinkedList<>( );
			
			for( ArtesaniaEntity artesaniaEntity : entity.getArtesanias( ) )
			{
				artesanias.add( new ArtesaniaDTO( artesaniaEntity ) );
			}
			for( ReviewEntity reviewEntity : entity.getReviews( ) )
			{
				reviews.add( new ReviewDTO( reviewEntity ) );
			}
			for( ArtesanoFeriaAssociation artesanoFeriaAssociation : entity.getFerias( ) )
			{
				ferias.add( new ArtesanoFeriaDTO( artesanoFeriaAssociation ) );
			}
			
			if( entity.getCiudad( ) != null )
			{
				ciudad = new CiudadDTO( entity.getCiudad( ) );
			}
		}
	}
	
	/**
	 * Retrieves a ArtesanoDetailEntity with the fields of this ArtesanoDetailDTO
	 */
	public ArtesanoEntity toEntity( )
	{
		ArtesanoEntity entity = super.toEntity( );
		
		List<ArtesaniaEntity> artesaniasEntities = new LinkedList<>( );
		List<ReviewEntity> reviewsEntities = new LinkedList<>( );
		List<ArtesanoFeriaAssociation> artesanoFeriaEntities = new LinkedList<>( );
		
		if( artesanias != null )
		{
			for( ArtesaniaDTO artesania : artesanias )
			{
				artesaniasEntities.add( artesania.toEntity( ) );
			}
		}
		if( reviews != null )
		{
			for( ReviewDTO review : reviews )
			{
				reviewsEntities.add( review.toEntity( ) );
			}
		}
		if( ferias != null )
		{
			for( ArtesanoFeriaDTO feria : ferias )
			{
				artesanoFeriaEntities.add( feria.toEntity( ) );
			}
		}
		entity.setArtesanias( artesaniasEntities );
		entity.setReviews( reviewsEntities );
		entity.setCiudad( ciudad != null ? this.ciudad.toEntity( ) : null );
		entity.setFerias( artesanoFeriaEntities );
		
		return entity;
	}
	
	/**
	 * Retrieves the artesanias of the ArtesanoDetailDTO
	 *
	 * @return The artesanias of the ArtesanoDetailDTO
	 */
	public List<ArtesaniaDTO> getArtesanias( )
	{
		return artesanias;
	}
	
	/**
	 * Updates the artesanias of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param artesanias The new artesanias of the ArtesanoDetailDTO
	 */
	public void setArtesanias( List<ArtesaniaDTO> artesanias )
	{
		this.artesanias = artesanias;
	}
	
	/**
	 * Retrieves the reviews of the ArtesanoDetailDTO
	 *
	 * @return The reviews of the ArtesanoDetailDTO
	 */
	public List<ReviewDTO> getReviews( )
	{
		return reviews;
	}
	
	/**
	 * Updates the reviews of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param reviews The new reviews of the ArtesanoDetailDTO
	 */
	public void setReviews( List<ReviewDTO> reviews )
	{
		this.reviews = reviews;
	}
	
	/**
	 * Retrieves the ciudad of the ArtesanoDetailDTO
	 *
	 * @return The ciudad of the ArtesanoDetailDTO
	 */
	public CiudadDTO getCiudad( )
	{
		return ciudad;
	}
	
	/**
	 * Updates the ciudad of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param ciudad The new ciudad of the ArtesanoDetailDTO
	 */
	public void setCiudad( CiudadDTO ciudad )
	{
		this.ciudad = ciudad;
	}
	
	/**
	 * Retrieves the ferias of the ArtesanoDetailDTO
	 *
	 * @return The ferias of the ArtesanoDetailDTO
	 */
	public List<ArtesanoFeriaDTO> getFerias( )
	{
		return ferias;
	}
	
	/**
	 * Updates the ferias of the ArtesanoDetailDTO by the one given by parameter
	 *
	 * @param ferias The new ferias of the ArtesanoDetailDTO
	 */
	public void setFerias( List<ArtesanoFeriaDTO> ferias )
	{
		this.ferias = ferias;
	}
}