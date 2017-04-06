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
package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ReviewEntity, contiene la información necesaria para modelar un Review hacia un Artesano
 *
 * @author d.narvaez11
 */
@Entity
public class ReviewEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
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
	 * Artesano al cual va dirijido el Review
	 */
	@ManyToOne( targetEntity = ArtesanoEntity.class )
	private ArtesanoEntity artesano;
	
	/**
	 * Retrieves the id of the ReviewEntity
	 *
	 * @return The id of the ReviewEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ReviewEntity by the one given by parameter
	 *
	 * @param id The new id of the ReviewEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the puntuacion of the ReviewEntity
	 *
	 * @return The puntuacion of the ReviewEntity
	 */
	public Float getPuntuacion( )
	{
		return puntuacion;
	}
	
	/**
	 * Updates the puntuacion of the ReviewEntity by the one given by parameter
	 *
	 * @param puntuacion The new puntuacion of the ReviewEntity
	 */
	public void setPuntuacion( Float puntuacion )
	{
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Retrieves the comentario of the ReviewEntity
	 *
	 * @return The comentario of the ReviewEntity
	 */
	public String getComentario( )
	{
		return comentario;
	}
	
	/**
	 * Updates the comentario of the ReviewEntity by the one given by parameter
	 *
	 * @param comentario The new comentario of the ReviewEntity
	 */
	public void setComentario( String comentario )
	{
		this.comentario = comentario;
	}
	
	/**
	 * Retrieves the artesano of the ReviewEntity
	 *
	 * @return The artesano of the ReviewEntity
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ReviewEntity by the one given by parameter
	 *
	 * @param artesano The new artesano of the ReviewEntity
	 */
	public void setArtesano( ArtesanoEntity artesano )
	{
		this.artesano = artesano;
	}
	
	@Override
	public int hashCode( )
	{
		if( id == null )
		{
			return super.hashCode( );
		}
		return id.hashCode( );
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( id == null )
		{
			return super.equals( obj );
		}
		return obj instanceof ReviewEntity && id.equals( ( ( ReviewEntity ) obj ).id );
	}
}