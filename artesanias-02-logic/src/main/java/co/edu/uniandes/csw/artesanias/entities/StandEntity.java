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
        /**
         * Id único de cada Stand
         */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
        /**
         * número del stand
         */
	private Integer numero;
	
        /**
         * Dimensiones del stand
         */
	private String dimensiones;
	
        /**
         * Descripción del stand
         */
	private String descripcion;
	
        /**
         * Precio del stand
         */
	private Double precio;
        
        private String imagen;
	
        /**
         * Pabellón al que pertenece el stand
         */
	@ManyToOne( targetEntity = PabellonEntity.class )
	private PabellonEntity pabellon;
	
        public String getImagen()
        {
            return imagen;
        }
	
        public void setImagen( String pImage)
        {
            this.imagen = pImage;
        }
        
        /**
         * Define el id del stand
         * @param id 
         */
	public void setId( Long id )
	{
		this.id = id;
	}
	
        /**
         * Define el número del stand
         * @param numero 
         */
	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}
	
        /**
         * Define las dimensiones del stand
         * @param pDimensiones 
         */
	public void setDimensiones( String pDimensiones )
	{
		this.dimensiones = pDimensiones;
	}
	
        /**
         * Define la descripción del stand
         * @param pDescripcion 
         */
	public void setDescripcion( String pDescripcion )
	{
		this.descripcion = pDescripcion;
	}
	
        /**
         * Define el precio del stand
         * @param pPrecio 
         */
	public void setPrecio( Double pPrecio )
	{
		this.precio = pPrecio;
	}
	
	/**
         * Retorna el id del stand
	 * @return  id 
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
         * Retorna el número del stand
	 * @return numero 
	 */
	public Integer getNumero( )
	{
		return numero;
	}
	
	/**
         * Retorna las dimensiones del stand
	 * @return dimensiones
	 */
	public String getDimensiones( )
	{
		return dimensiones;
	}
	
	/**
         * Retorna la descripción del stand
	 * @return descripcion
	 */
	public String getDescripcion( )
	{
		return descripcion;
	}
	
	/**
         * Retorn el precio del stand
	 * @return precio
	 */
	public Double getPrecio( )
	{
		return precio;
	}
	
        /**
         * Retorna el pabellón al que pertenece el stand
	 * @return pabellon
         */
	public PabellonEntity getPabellon( )
	{
		return pabellon;
	}
	
	/**
	 * Define el pabellón al que pertenece el stand
	 * @param pabellon 
	 */
	public void setPabellon( PabellonEntity pabellon )
	{
		this.pabellon = pabellon;
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