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
import java.util.List;

/**
 * @author d.narvaez11
 */
@Entity
public class ArtesanoEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	/**
	 * Nombre del Artesano
	 */
	@Column( nullable = false )
	private String nombre;
	
	/**
	 * Identificación del Artesano
	 */
	@Column( unique = true )
	private String identificacion;
	
	/**
	 * Telefono del Artesano
	 */
	private String telefono;
	
	/**
	 * Artesanias hechas por el Artesano
	 */
	@OneToMany( cascade = CascadeType.ALL, targetEntity = ArtesaniaEntity.class, fetch = FetchType.LAZY, mappedBy = "artesano" )
	private List<ArtesaniaEntity> artesanias;
	
	/**
	 * Reviews sobre el Artesano
	 */
	@OneToMany( cascade = CascadeType.ALL, targetEntity = ReviewEntity.class, fetch = FetchType.LAZY, mappedBy = "artesano" )
	private List<ReviewEntity> reviews;
	
	/**
	 * Ciudad de Origen del Artesano
	 */
	@ManyToOne( targetEntity = CiudadEntity.class )
	private CiudadEntity ciudad;
	
	/**
	 * Stand en el que un Artesano se encuentra
	 */
	@ManyToOne( targetEntity = StandEntity.class )
	private StandEntity stand;
	
	/**
	 * Retrieves the id of the ArtesanoEntity
	 *
	 * @return The id of the ArtesanoEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesanoEntity by the one given by parameter
	 *
	 * @param id The new id of the ArtesanoEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the ciudad of the ArtesanoEntity
	 *
	 * @return The ciudad of the ArtesanoEntity
	 */
	public CiudadEntity getCiudad( )
	{
		return ciudad;
	}
	
	/**
	 * Updates the ciudad of the ArtesanoEntity by the one given by parameter
	 *
	 * @param ciudad The new ciudad of the ArtesanoEntity
	 */
	public void setCiudad( CiudadEntity ciudad )
	{
		this.ciudad = ciudad;
	}
	
	/**
	 * Retrieves the nombre of the ArtesanoEntity
	 *
	 * @return The nombre of the ArtesanoEntity
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Updates the nombre of the ArtesanoEntity by the one given by parameter
	 *
	 * @param nombre The new nombre of the ArtesanoEntity
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Retrieves the identificacion of the ArtesanoEntity
	 *
	 * @return The identificacion of the ArtesanoEntity
	 */
	public String getIdentificacion( )
	{
		return identificacion;
	}
	
	/**
	 * Updates the identificacion of the ArtesanoEntity by the one given by parameter
	 *
	 * @param identificacion The new identificacion of the ArtesanoEntity
	 */
	public void setIdentificacion( String identificacion )
	{
		this.identificacion = identificacion;
	}
	
	/**
	 * Retrieves the telefono of the ArtesanoEntity
	 *
	 * @return The telefono of the ArtesanoEntity
	 */
	public String getTelefono( )
	{
		return telefono;
	}
	
	/**
	 * Updates the telefono of the ArtesanoEntity by the one given by parameter
	 *
	 * @param telefono The new telefono of the ArtesanoEntity
	 */
	public void setTelefono( String telefono )
	{
		this.telefono = telefono;
	}
	
	/**
	 * Retrieves the artesanias of the ArtesanoEntity
	 *
	 * @return The artesanias of the ArtesanoEntity
	 */
	public List<ArtesaniaEntity> getArtesanias( )
	{
		return artesanias;
	}
	
	/**
	 * Updates the artesanias of the ArtesanoEntity by the one given by parameter
	 *
	 * @param artesanias The new artesanias of the ArtesanoEntity
	 */
	public void setArtesanias( List<ArtesaniaEntity> artesanias )
	{
		this.artesanias = artesanias;
	}
	
	/**
	 * Retrieves the reviews of the ArtesanoEntity
	 *
	 * @return The reviews of the ArtesanoEntity
	 */
	public List<ReviewEntity> getReviews( )
	{
		return reviews;
	}
	
	/**
	 * Updates the reviews of the ArtesanoEntity by the ones given by parameter
	 *
	 * @param reviews The new reviews of the ArtesanoEntity
	 */
	public void setReviews( List<ReviewEntity> reviews )
	{
		this.reviews = reviews;
	}
	
	/**
	 * Retrieves the stand of the ArtesanoEntity
	 *
	 * @return The stand of the ArtesanoEntity
	 */
	public StandEntity getStand( )
	{
		return stand;
	}
	
	/**
	 * Updates the stand of the ArtesanoEntity by the one given by parameter
	 *
	 * @param stand The new stand of the ArtesanoEntity
	 */
	public void setStand( StandEntity stand )
	{
		this.stand = stand;
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
		return obj instanceof ArtesanoEntity && id.equals( ( ( ArtesanoEntity ) obj ).id );
	}
}