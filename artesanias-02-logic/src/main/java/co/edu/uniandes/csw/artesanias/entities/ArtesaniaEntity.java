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
 * Artesania Entity, contiene la información de una artesania creada por un artesano
 *
 * @author d.narvaez11
 */
@Entity
public class ArtesaniaEntity implements Serializable
{
	/**
	 * Identificador único de cada instancia
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	/**
	 * Nomrbe de la Artesnia
	 */
	private String nombre;
	
	/**
	 * Descripcion de la Artesania
	 */
	private String descripcion;
	
	/**
	 * Artesano quien creó la artesania
	 */
	@ManyToOne( targetEntity = ArtesanoEntity.class )
	private ArtesanoEntity artesano;
	
	/**
	 * Retrieves the id of the ArtesaniaEntity
	 *
	 * @return The id of the ArtesaniaEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param id The new id of the ArtesaniaEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the nombre of the ArtesaniaEntity
	 *
	 * @return The nombre of the ArtesaniaEntity
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param nombre The new nombre of the ArtesaniaEntity
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the descripcion of the ArtesaniaEntity
	 *
	 * @return The descripcion of the ArtesaniaEntity
	 */
	public String getDescripcion( )
	{
		return descripcion;
	}
	
	/**
	 * Updates the descripcion of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param descripcion The new descripcion of the ArtesaniaEntity
	 */
	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}
	
	/**
	 * Retrieves the artesano of the ArtesaniaEntity
	 *
	 * @return The artesano of the ArtesaniaEntity
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ArtesaniaEntity by the one given by parameter
	 *
	 * @param artesano The new artesano of the ArtesaniaEntity
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
		return obj instanceof ArtesaniaEntity && id.equals( ( ( ArtesaniaEntity ) obj ).id );
	}
}