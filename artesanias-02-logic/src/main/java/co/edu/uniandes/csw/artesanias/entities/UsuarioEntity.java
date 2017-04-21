/*
 * The MIT License
 *
 * Copyright 2017 IVAN.
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
 * @author IVAN, d.narvaez11
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class UsuarioEntity implements Serializable
{
	/**
	 * Identificador único de cada instancia de Usuario
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	/**
	 * Correo electronico del Usuario
	 */
	private String correo;
	
	/**
	 * Contrasena de acceso al sistema del Usuario
	 */
	private String contrasena;
	
	/**
	 * Foto del Usuario
	 */
	private String foto;
	
	/**
	 * Retrieves the id of the UsuarioEntity
	 *
	 * @return The id of the UsuarioEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the UsuarioEntity by the one given by parameter
	 *
	 * @param id The new id of the UsuarioEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the correo of the UsuarioEntity
	 *
	 * @return The correo of the UsuarioEntity
	 */
	public String getCorreo( )
	{
		return correo;
	}
	
	/**
	 * Updates the correo of the UsuarioEntity by the one given by parameter
	 *
	 * @param correo The new correo of the UsuarioEntity
	 */
	public void setCorreo( String correo )
	{
		this.correo = correo;
	}
	
	/**
	 * Retrieves the contrasena of the UsuarioEntity
	 *
	 * @return The contrasena of the UsuarioEntity
	 */
	public String getContrasena( )
	{
		return contrasena;
	}
	
	/**
	 * Updates the contrasena of the UsuarioEntity by the one given by parameter
	 *
	 * @param contrasena The new contrasena of the UsuarioEntity
	 */
	public void setContrasena( String contrasena )
	{
		this.contrasena = contrasena;
	}
	
	/**
	 * Retrieves the foto of the UsuarioEntity
	 *
	 * @return The foto of the UsuarioEntity
	 */
	public String getFoto( )
	{
		return foto;
	}
	
	/**
	 * Updates the foto of the UsuarioEntity by the one given by parameter
	 *
	 * @param foto The new foto of the UsuarioEntity
	 */
	public void setFoto( String foto )
	{
		this.foto = foto;
	}
	
	@Override
	public int hashCode( )
	{
		if( this.id != null )
		{
			return this.id.hashCode( );
		}
		return super.hashCode( );
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if( this.id != null )
		{
			return this.id.equals( ( ( UsuarioEntity ) obj ).id );
		}
		return super.equals( obj );
	}
}