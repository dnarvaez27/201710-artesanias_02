package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.ReviewEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author d.narvaez11
 */
@XmlRootElement
public class ReviewDTO implements Serializable
{
	private Long id;
	
	/**
	 * Puntuación del Review
	 */
	private Float puntuacion;
	
	/**
	 * Comentarios del review
	 */
	private String comentario;
	
	/**
	 * Artesano al cual va dirijido el Review
	 */
	private ArtesanoEntity artesano;
	
	public ReviewDTO( )
	{
		// Default Constructor. Mandatory
	}
	
	/**
	 * Builds an ReviewDTO by the fields from the ReviewEntity given
	 *
	 * @param entity ReviewEntity to fill up the ReviewDTO
	 */
	public ReviewDTO( ReviewEntity entity )
	{
		if( entity != null )
		{
			this.id = entity.getId( );
			this.puntuacion = entity.getPuntuacion( );
			this.comentario = entity.getComentario( );
			this.artesano = entity.getArtesano( );
		}
	}
	
	/**
	 * Retrieves a ReviewEntity with the fields of this ReviewDTO
	 */
	public ReviewEntity toEntity( )
	{
		ReviewEntity entity = new ReviewEntity( );
		entity.setId( this.id );
		entity.setPuntuacion( this.puntuacion );
		entity.setComentario( this.comentario );
		entity.setArtesano( this.artesano );
		return entity;
	}
	
	/**
	 * Retrieves the id of the ReviewDTO
	 *
	 * @return The id of the ReviewDTO
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ReviewDTO by the one given by parameter
	 *
	 * @param id The new id of the ReviewDTO
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the puntuacion of the ReviewDTO
	 *
	 * @return The puntuacion of the ReviewDTO
	 */
	public Float getPuntuacion( )
	{
		return puntuacion;
	}
	
	/**
	 * Updates the puntuacion of the ReviewDTO by the one given by parameter
	 *
	 * @param puntuacion The new puntuacion of the ReviewDTO
	 */
	public void setPuntuacion( Float puntuacion )
	{
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Retrieves the comentario of the ReviewDTO
	 *
	 * @return The comentario of the ReviewDTO
	 */
	public String getComentario( )
	{
		return comentario;
	}
	
	/**
	 * Updates the comentario of the ReviewDTO by the one given by parameter
	 *
	 * @param comentario The new comentario of the ReviewDTO
	 */
	public void setComentario( String comentario )
	{
		this.comentario = comentario;
	}
	
	/**
	 * Retrieves the artesano of the ReviewDTO
	 *
	 * @return The artesano of the ReviewDTO
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ReviewDTO by the one given by parameter
	 *
	 * @param artesano The new artesano of the ReviewDTO
	 */
	public void setArtesano( ArtesanoEntity artesano )
	{
		this.artesano = artesano;
	}
}