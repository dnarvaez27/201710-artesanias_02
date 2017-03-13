/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author IVAN
 */
@XmlRootElement
public class OrganizadorDTO
{
	private Long id;
	
	private String correo;
	
	private String contrasena;
	
	private String foto;
	
	private String identificación;
	
	public OrganizadorDTO( )
	{
		
	}
	
	public OrganizadorDTO( OrganizadorEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.correo = entity.getCorreo( );
			this.contrasena = entity.getContrasena( );
			this.identificación = entity.getIdentificacion( );
			this.foto = entity.getFoto( );
		}
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public String getIdentificacion( )
	{
		return identificación;
	}
	
	public String getCorreo( )
	{
		return correo;
	}
	
	public String getContrasena( )
	{
		return contrasena;
	}
	
	public String getFoto( )
	{
		return foto;
	}
	
	public void setID( Long id )
	{
		this.id = id;
	}
	
	public void setCorreo( String correo )
	{
		this.correo = correo;
	}
	
	public void setContrasena( String contrasena )
	{
		this.contrasena = contrasena;
	}
	
	public void setIdentificacion( String identificacion )
	{
		this.identificación = identificacion;
	}
	
	public void setFoto( String foto )
	{
		this.foto = foto;
	}
	
	public OrganizadorEntity toEntity( )
	{
		OrganizadorEntity entity = new OrganizadorEntity( );
		entity.setId( this.id );
		entity.setCorreo( this.correo );
		entity.setContrasena( this.contrasena );
		entity.setIdentificacion( this.identificación );
		entity.setFoto( this.foto );
		return entity;
	}
}
