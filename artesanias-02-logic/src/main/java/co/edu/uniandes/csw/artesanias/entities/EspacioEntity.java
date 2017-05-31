/*
 * The MIT License
 *
 * Copyright 2017 Miller.
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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author ja.espinoza
 */
@Entity
@Table(name= "espacio")
public class EspacioEntity implements Serializable
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

        @Column(nullable = false)
	private String direccion;
	
        @Column(nullable = false)
	private String telefono;
        
        @Column(nullable = false)
	private Integer capacidad;
        
        private String imagen;
	
        @PodamExclude
        @ManyToOne(targetEntity = CiudadEntity.class, fetch = FetchType.LAZY)
        @JoinColumn(name = "id_ciudad")
        private CiudadEntity ciudad;
        
    
        @PodamExclude
	@OneToMany( targetEntity = PabellonEntity.class, fetch = FetchType.LAZY )
	private List<PabellonEntity> pabellones = new LinkedList<>( );
	

        @PodamExclude
	@OneToMany( targetEntity = FeriaEntity.class, fetch = FetchType.LAZY )
	private List<FeriaEntity> ferias = new LinkedList<>( );
        
        public String getImagen()
        {
            return imagen;
        }
	
        public void setImagen( String pImage)
        {
            this.imagen = pImage;
        }
        
	public List<FeriaEntity> getFerias( )
	{
		return ferias;
	}
	
	public void setFerias( List<FeriaEntity> ferias )
	{
		this.ferias = ferias;
	}
	
        public CiudadEntity getCiudad()
        {
            return ciudad;
        }
        
        public void setCiudad( CiudadEntity ciudad )
        {
            this.ciudad = ciudad;
        }
        
	
	public List<PabellonEntity> getPabellones( )
	{
		return pabellones;
	}
	

	public void setPabellones( List<PabellonEntity> pabellones )
	{
		this.pabellones = pabellones;
	}
	
	/**
	 * @return the id
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	

	public String getDireccion( )
	{
		return direccion;
	}
	

	public void setDireccion( String direccion )
	{
		this.direccion = direccion;
	}
        
        public String getTelefono( )
	{
		return telefono;
	}
	

	public void setTelefono( String telefono )
	{
		this.telefono = telefono;
	}
	
	/**
	 * @return the capacidad
	 */
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).equals( ( ( EspacioEntity ) obj ).getId( ) );
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