package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase DTO (Data Transfer Object) basica que representa un Espectador
 *
 * @author d.narvaez11
 * @see EspectadorEntity
 */
@XmlRootElement
public class EspectadorDTO implements Serializable
{
	/**
	 * Identificador único de cada instancia de Espectador
	 */
	private Long id;
	
	/**
	 * Correo y login del espectador
	 */
	private String correo;
	
	/**
	 * Contrasena de acceso del espectador
	 */
	private String contrasena;
	
	/**
	 * Foto del espectador
	 */
	private String foto;
	
	/**
	 * Edad del espectador
	 */
	private Integer edad;
	
	/**
	 * Builds an empty Espectador
	 */
	public EspectadorDTO( )
	{
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an EspectadorDTO by the fields from the EspectadorEntity given
	 *
	 * @param entity EspectadorEntity to fill up the EspectadorDTO
	 */
	public EspectadorDTO( EspectadorEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.correo = entity.getCorreo( );
			this.contrasena = entity.getContrasena( );
			this.foto = entity.getFoto( );
			this.edad = entity.getEdad( );
		}
	}
	
	/**
	 * Retrieves a EspectadorEntity with the fields of this EspectadorDTO
	 */
	public EspectadorEntity toEntity( )
	{
		EspectadorEntity entity = new EspectadorEntity( );
		entity.setId( this.id );
		entity.setCorreo( this.correo );
		entity.setContrasena( this.contrasena );
		entity.setFoto( this.foto );
		entity.setEdad( this.edad );
		return entity;
	}
	
	/**
	 * Retrieves the id of the EspectadorDTO
	 *
	 * @return The id of the EspectadorDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the EspectadorDTO by the one given by parameter
	 *
	 * @param id The new id of the EspectadorDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the correo of the EspectadorDTO
	 *
	 * @return The correo of the EspectadorDTO
	 */
	public String getCorreo( )
	{
		return correo;
	}
	
	/**
	 * Updates the correo of the EspectadorDTO by the one given by parameter
	 *
	 * @param correo The new correo of the EspectadorDTO
	 */
	public void setCorreo( String correo )
	{
		this.correo = correo;
	}
	
	/**
	 * Retrieves the contrasena of the EspectadorDTO
	 *
	 * @return The contrasena of the EspectadorDTO
	 */
	public String getContrasena( )
	{
		return contrasena;
	}
	
	/**
	 * Updates the contrasena of the EspectadorDTO by the one given by parameter
	 *
	 * @param contrasena The new contrasena of the EspectadorDTO
	 */
	public void setContrasena( String contrasena )
	{
		this.contrasena = contrasena;
	}
	
	/**
	 * Retrieves the foto of the EspectadorDTO
	 *
	 * @return The foto of the EspectadorDTO
	 */
	public String getFoto( )
	{
		return foto;
	}
	
	/**
	 * Updates the foto of the EspectadorDTO by the one given by parameter
	 *
	 * @param foto The new foto of the EspectadorDTO
	 */
	public void setFoto( String foto )
	{
		this.foto = foto;
	}
	
	/**
	 * Retrieves the edad of the EspectadorDTO
	 *
	 * @return The edad of the EspectadorDTO
	 */
	public Integer getEdad( )
	{
		return edad;
	}
	
	/**
	 * Updates the edad of the EspectadorDTO by the one given by parameter
	 *
	 * @param edad The new edad of the EspectadorDTO
	 */
	public void setEdad( Integer edad )
	{
		this.edad = edad;
	}
}