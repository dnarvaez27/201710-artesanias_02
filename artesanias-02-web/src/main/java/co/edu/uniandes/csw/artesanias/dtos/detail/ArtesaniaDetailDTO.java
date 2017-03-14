package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.ArtesaniaDTO;
import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.entities.ArtesaniaEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Clase DTO (Data Transfer Object) detallado que representa una Artesania
 *
 * @author d.narvaez11
 * @see ArtesaniaEntity
 * @see ArtesaniaDTO
 */
@XmlRootElement
public class ArtesaniaDetailDTO extends ArtesaniaDTO implements Serializable
{
	/**
	 * Artesano al cual pertenece la artesania
	 */
	private ArtesanoDTO artesano;
	
	/**
	 * Builds an empty Artesania Detail
	 */
	public ArtesaniaDetailDTO( )
	{
		super( );
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ArtesaniiaDTO by the fields from the ArtesaniiaEntity given
	 *
	 * @param entity ArtesaniiaEntity to fill up the ArtesaniiaDTO
	 */
	public ArtesaniaDetailDTO( ArtesaniaEntity entity )
	{
		super( entity );
		if( entity != null )
		{
			this.artesano = new ArtesanoDTO( entity.getArtesano( ) );
		}
	}
	
	/**
	 * Retrieves the artesano of the ArtesaniaDetailDTO
	 *
	 * @return The artesano of the ArtesaniaDetailDTO
	 */
	public ArtesanoDTO getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ArtesaniaDetailDTO by the one given by parameter
	 *
	 * @param artesano The new artesano of the ArtesaniaDetailDTO
	 */
	public void setArtesano( ArtesanoDTO artesano )
	{
		this.artesano = artesano;
	}
	
	/**
	 * Retrieves a ArtesaniaEntity with the fields of this ArtesaniaDTO
	 */
	public ArtesaniaEntity toEntity( )
	{
		ArtesaniaEntity entity = super.toEntity( );
		entity.setArtesano( artesano.toEntity( ) );
		return entity;
	}
}