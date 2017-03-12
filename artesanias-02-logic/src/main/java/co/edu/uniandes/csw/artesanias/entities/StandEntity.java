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
package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author ja.espinosa12
 */
@Entity
public class StandEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private Integer numero;
	
	private String dimensiones;
	
	private String descripcion;
	
	private Double precio;
	
	@ManyToOne( targetEntity = PabellonEntity.class )
	private PabellonEntity pabellon;
	
	@OneToMany( fetch = FetchType.LAZY, targetEntity = ArtesanoEntity.class, mappedBy = "stand" )
	private List<ArtesanoEntity> artesanos;
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}
	
	public void setDimensiones( String pDimensiones )
	{
		this.dimensiones = pDimensiones;
	}
	
	public void setDescripcion( String pDescripcion )
	{
		this.descripcion = pDescripcion;
	}
	
	public void setPrecio( Double pPrecio )
	{
		this.precio = pPrecio;
	}
	
	/**
	 * @return the id
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * @return the numero
	 */
	public Integer getNumero( )
	{
		return numero;
	}
	
	/**
	 * @return the dimensiones
	 */
	public String getDimensiones( )
	{
		return dimensiones;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion( )
	{
		return descripcion;
	}
	
	/**
	 * @return the precio
	 */
	public Double getPrecio( )
	{
		return precio;
	}
	
	/**
	 * Retrieves the pabellon of the StandEntity
	 *
	 * @return The pabellon of the StandEntity
	 */
	public PabellonEntity getPabellon( )
	{
		return pabellon;
	}
	
	/**
	 * Updates the pabellon of the StandEntity by the one given by parameter
	 *
	 * @param pabellon The new pabellon of the StandEntity
	 */
	public void setPabellon( PabellonEntity pabellon )
	{
		this.pabellon = pabellon;
	}
	
	/**
	 * Retrieves the artesano of the StandEntity
	 *
	 * @return The artesano of the StandEntity
	 */
	public List<ArtesanoEntity> getArtesanos( )
	{
		return artesanos;
	}
	
	/**
	 * Updates the artesano of the StandEntity by the one given by parameter
	 *
	 * @param artesanos The new artesano of the StandEntity
	 */
	public void setArtesanos( List<ArtesanoEntity> artesanos )
	{
		this.artesanos = artesanos;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( this.getId( ) != null )
		{
			
			return this.getId( ).equals( ( ( SalonEntity ) obj ).getId( ) );
			
		}
		return super.equals( obj );
	}
	
	public int hashCode( )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).hashCode( );
		}
		return super.hashCode( );
	}
}