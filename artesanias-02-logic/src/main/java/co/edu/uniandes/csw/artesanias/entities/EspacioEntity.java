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

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad de los espacios.
 *
 * @author ma.trujillo10
 */
@Entity
@Table( name = "espacio", uniqueConstraints = {
		@UniqueConstraint( columnNames = { "direccion", "ciudad" } )
} )
public class EspacioEntity implements Serializable
{
	
	//--------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------
	
	/**
	 * Id del espacio.
	 * El valor es auto generado por la base de datos.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	/**
	 * Nombre del espacio.
	 */
	@Column( nullable = false )
	private String nombre;
	
	/**
	 * Dirección del espacio.
	 */
	@Column( nullable = false )
	private String direccion;
	
	/**
	 * Telefono del espacio.
	 */
	@Column( unique = true, nullable = false )
	private String telefono;
	
	/**
	 * Capacidad del espacio.
	 */
	@Column( nullable = false )
	private Integer capacidad;
	
	/**
	 * Ciudad donde se encuentra el espacio.
	 * Relación bidireccional con CiudadEntity. El dueño de la relación es
	 * EspacioEntity.
	 */
	@ManyToOne( targetEntity = CiudadEntity.class )
	@JoinColumn( name = "ciudad_id" )
	//    @Column(nullable=false) // Generaba error para ejecutar
	private CiudadEntity ciudad;
	
	/**
	 * Conjunto de ferias que se han dado en el espacio.
	 * Relación bidireccional con FeriaEntity. El dueño de la relación es
	 * FeriaEntity.
	 */
	@OneToMany( mappedBy = "espacio" )
	private List<FeriaEntity> ferias = new LinkedList<FeriaEntity>( );
        
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "espacio",
            targetEntity = PabellonEntity.class, fetch = FetchType.LAZY)
        private List<PabellonEntity> pabellones;
	
	//--------------------------------------------------------------------------
	// Métodos
	//--------------------------------------------------------------------------
	
	/**
	 * Devuelve el id del espacio.
	 *
	 * @return id del espacio.
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Cambia el id del espacio.
	 * post: Se cambió el id del espacio.
	 *
	 * @param id nuevo id del espacio.
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Devuelve el nombre del espacio.
	 *
	 * @return nombre del espacio.
	 */
	public String getNombre( )
	{
		return nombre;
	}
	
	/**
	 * Cambia el nombre del espacio.
	 * post: Se cambió el nombre del espacio.
	 *
	 * @param nombre nuevo nombre del espacio.
	 */
	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve la dirección del espacio.
	 *
	 * @return dirección del espacio.
	 */
	public String getDireccion( )
	{
		return direccion;
	}
	
	/**
	 * Cambia la dirección del espacio.
	 * post: Se cambió la dirección del espacio.
	 *
	 * @param direccion nueva dirección del espacio.
	 */
	public void setDireccion( String direccion )
	{
		this.direccion = direccion;
	}
	
	/**
	 * Devuelve el número de teléfono del espacio.
	 *
	 * @return número de teléfono del espacio.
	 */
	public String getTelefono( )
	{
		return telefono;
	}
	
	/**
	 * Cambia el número de teléfono del espacio.
	 * post: Se cambió el número de teléfono del espacio.
	 *
	 * @param telefono nuevo número de teléfono del espacio.
	 */
	public void setTelefono( String telefono )
	{
		this.telefono = telefono;
	}
	
	/**
	 * Devuelve la capacidad del espacio.
	 *
	 * @return capacidad del espacio.
	 */
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	/**
	 * Cambia la capacidad del espacio.
	 * post: Se cambió la capacidad del espacio.
	 *
	 * @param capacidad nueva capacidad del espacio.
	 */
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
	/**
	 * Devuelve la ciudad a la que pertenece el espacio.
	 *
	 * @return ciudad a la que pertenece el espacio.
	 */
	public CiudadEntity getCiudad( )
	{
		return ciudad;
	}
	
	/**
	 * Cambia la ciudad a la que pertenece el espacio.
	 * post: Se cambió la ciudad a la que pertenece el espacio.
	 *
	 * @param ciudad nueva ciudad del espacio.
	 */
	public void setCiudad( CiudadEntity ciudad )
	{
		this.ciudad = ciudad;
	}
	
	/**
	 * Devuelve el conjunto de ferias que se han realizado en el espacio.
	 *
	 * @return conjunto de ferias que se han realizado en el espacio.
	 */
	public List<FeriaEntity> getFerias( )
	{
		return ferias;
	}
	
	/**
	 * Cambia el conjunto de ferias que se han realizado en el espacio.
	 * post: Se cambió el conjunto de ferias que se han realizado en el espacio.
	 *
	 * @param ferias nuevo conjunto de ferias que se han realizado en el espacio.
	 */
	public void setFerias( List<FeriaEntity> ferias )
	{
		this.ferias = ferias;
	}
        
        public List<PabellonEntity> getPabellones()
        {
            return pabellones;
        }
        
        public void setPabellones(List<PabellonEntity> pabellones) {
        this.pabellones = pabellones;
        }
	
	//--------------------------------------------------------------------------
	// Métodos del objeto
	//--------------------------------------------------------------------------
	
	@Override
	public int hashCode( )
	{
		int hash = 7;
		hash = 41 * hash + Objects.hashCode( this.id );
		return hash;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( this == obj )
		{
			return true;
		}
		if( obj == null )
		{
			return false;
		}
		if( getClass( ) != obj.getClass( ) )
		{
			return false;
		}
		final EspacioEntity other = ( EspacioEntity ) obj;
		return Objects.equals( this.id, other.id );
	}
}
