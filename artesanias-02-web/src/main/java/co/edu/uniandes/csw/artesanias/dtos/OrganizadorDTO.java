/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author IVAN
 */
@XmlRootElement
public class OrganizadorDTO implements Serializable
{
	private Long id;
	
	private String correo;
	
	private String contrasena;
	
	private String foto;
	
	private String identificacion;
	
	public OrganizadorDTO( )
	{
	}
	
	/**
	 * Builds an OrganizadorDTO by the fields from the OrganizadorEntity given
	 *
	 * @param entity OrganizadorEntity to fill up the OrganizadorDTO
	 */
	public OrganizadorDTO( OrganizadorEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.correo = entity.getCorreo( );
			this.contrasena = entity.getContrasena( );
			this.foto = entity.getFoto( );
			this.identificacion = entity.getIdentificacion( );
		}
	}
	
	/**
	 * Retrieves a OrganizadorEntity with the fields of this OrganizadorDTO
	 */
	public OrganizadorEntity toEntity( )
	{
		OrganizadorEntity entity = new OrganizadorEntity( );
		entity.setId( this.id );
		entity.setCorreo( this.correo );
		entity.setContrasena( this.contrasena );
		entity.setFoto( this.foto );
		entity.setIdentificacion( this.identificacion );
		return entity;
	}
	
	/**
	 * Retrieves the id of the OrganizadorDTO
	 *
	 * @return The id of the OrganizadorDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the OrganizadorDTO by the one given by parameter
	 *
	 * @param id The new id of the OrganizadorDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the correo of the OrganizadorDTO
	 *
	 * @return The correo of the OrganizadorDTO
	 */
	public String getCorreo( )
	{
		return correo;
	}
	
	/**
	 * Updates the correo of the OrganizadorDTO by the one given by parameter
	 *
	 * @param correo The new correo of the OrganizadorDTO
	 */
	public void setCorreo( String correo )
	{
		this.correo = correo;
	}
	
	/**
	 * Retrieves the contrasena of the OrganizadorDTO
	 *
	 * @return The contrasena of the OrganizadorDTO
	 */
	public String getContrasena( )
	{
		return contrasena;
	}
	
	/**
	 * Updates the contrasena of the OrganizadorDTO by the one given by parameter
	 *
	 * @param contrasena The new contrasena of the OrganizadorDTO
	 */
	public void setContrasena( String contrasena )
	{
		this.contrasena = contrasena;
	}
	
	/**
	 * Retrieves the foto of the OrganizadorDTO
	 *
	 * @return The foto of the OrganizadorDTO
	 */
	public String getFoto( )
	{
		return foto;
	}
	
	/**
	 * Updates the foto of the OrganizadorDTO by the one given by parameter
	 *
	 * @param foto The new foto of the OrganizadorDTO
	 */
	public void setFoto( String foto )
	{
		this.foto = foto;
	}
	
	/**
	 * Retrieves the identificación of the OrganizadorDTO
	 *
	 * @return The identificación of the OrganizadorDTO
	 */
	public String getIdentificacion( )
	{
		return identificacion;
	}
	
	/**
	 * Updates the identificación of the OrganizadorDTO by the one given by parameter
	 *
	 * @param identificacion The new identificación of the OrganizadorDTO
	 */
	public void setIdentificacion( String identificacion )
	{
		this.identificacion = identificacion;
	}
}