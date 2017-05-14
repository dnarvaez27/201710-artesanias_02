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
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase DTO (Data Transfer Object) basica  que representa un Review
 *
 * @author d.narvaez11
 * @see ReviewEntity
 */
@XmlRootElement
public class ReviewDTO implements Serializable
{
	/**
	 * Identificador unico de cada instancia de Review
	 */
	private Long id;
	
	/**
	 * Puntuación del Review
	 */
	private Float puntuacion;
	
	/**
	 * Comentarios del review
	 */
	private String comentario;
	
	/**
	 * Builds an empty Review
	 */
	public ReviewDTO( )
	{
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ReviewDTO by the fields from the ReviewEntity given
	 *
	 * @param entity ReviewEntity to fill up the ReviewDTO
	 */
	public ReviewDTO( ReviewEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.puntuacion = entity.getPuntuacion( );
			this.comentario = entity.getComentario( );
		}
	}
	
	/**
	 * Retrieves a ReviewEntity with the fields of this ReviewDTO
	 */
	public ReviewEntity toEntity( )
	{
		ReviewEntity entity = new ReviewEntity( );
		entity.setId( this.id );
		entity.setPuntuacion( this.puntuacion );
		entity.setComentario( this.comentario );
		return entity;
	}
	
	/**
	 * Retrieves the id of the ReviewDTO
	 *
	 * @return The id of the ReviewDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ReviewDTO by the one given by parameter
	 *
	 * @param id The new id of the ReviewDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the puntuacion of the ReviewDTO
	 *
	 * @return The puntuacion of the ReviewDTO
	 */
	public Float getPuntuacion( )
	{
		return puntuacion;
	}
	
	/**
	 * Updates the puntuacion of the ReviewDTO by the one given by parameter
	 *
	 * @param puntuacion The new puntuacion of the ReviewDTO
	 */
	public void setPuntuacion( Float puntuacion )
	{
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Retrieves the comentario of the ReviewDTO
	 *
	 * @return The comentario of the ReviewDTO
	 */
	public String getComentario( )
	{
		return comentario;
	}
	
	/**
	 * Updates the comentario of the ReviewDTO by the one given by parameter
	 *
	 * @param comentario The new comentario of the ReviewDTO
	 */
	public void setComentario( String comentario )
	{
		this.comentario = comentario;
	}
}