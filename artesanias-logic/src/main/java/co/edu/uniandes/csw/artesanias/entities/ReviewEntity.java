package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author d.narvaez11
 */
@Entity
public class ReviewEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
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
	@ManyToOne( targetEntity = ArtesanoEntity.class )
	private ArtesanoEntity artesano;
	
	/**
	 * Retrieves the id of the ReviewEntity
	 *
	 * @return The id of the ReviewEntity
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ReviewEntity by the one given by parameter
	 *
	 * @param id The new id of the ReviewEntity
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the puntuacion of the ReviewEntity
	 *
	 * @return The puntuacion of the ReviewEntity
	 */
	public Float getPuntuacion( )
	{
		return puntuacion;
	}
	
	/**
	 * Updates the puntuacion of the ReviewEntity by the one given by parameter
	 *
	 * @param puntuacion The new puntuacion of the ReviewEntity
	 */
	public void setPuntuacion( Float puntuacion )
	{
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Retrieves the comentario of the ReviewEntity
	 *
	 * @return The comentario of the ReviewEntity
	 */
	public String getComentario( )
	{
		return comentario;
	}
	
	/**
	 * Updates the comentario of the ReviewEntity by the one given by parameter
	 *
	 * @param comentario The new comentario of the ReviewEntity
	 */
	public void setComentario( String comentario )
	{
		this.comentario = comentario;
	}
	
	/**
	 * Retrieves the artesano of the ReviewEntity
	 *
	 * @return The artesano of the ReviewEntity
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ReviewEntity by the one given by parameter
	 *
	 * @param artesano The new artesano of the ReviewEntity
	 */
	public void setArtesano( ArtesanoEntity artesano )
	{
		this.artesano = artesano;
	}
}