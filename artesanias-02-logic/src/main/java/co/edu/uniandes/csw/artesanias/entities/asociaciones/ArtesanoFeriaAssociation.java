/*
 * The MIT License
 *
 * Copyright 2017 d.narvaez11.
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
package co.edu.uniandes.csw.artesanias.entities.asociaciones;

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;
import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author d.narvaez11
 */
@Entity
@Table( uniqueConstraints = { @UniqueConstraint( columnNames = { "feria", "stand" } ) } )
public class ArtesanoFeriaAssociation implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@PodamExclude
	@ManyToOne( targetEntity = FeriaEntity.class )
	private FeriaEntity feria;
	
	@PodamExclude
	@ManyToOne( targetEntity = StandEntity.class )
	private StandEntity stand;
	
	@PodamExclude
	@ManyToOne( targetEntity = ArtesanoEntity.class )
	private ArtesanoEntity artesano;
	
	@Temporal( TemporalType.DATE )
	@Column( nullable = false )
	private Date fecha;
	
	/**
	 * Retrieves the id of the ArtesanoFeriaAssociation
	 *
	 * @return The id of the ArtesanoFeriaAssociation
	 */
	public Long getId( )
	{
		return id;
	}
	
	/**
	 * Updates the id of the ArtesanoFeriaAssociation by the one given by parameter
	 *
	 * @param id The new id of the ArtesanoFeriaAssociation
	 */
	public void setId( Long id )
	{
		this.id = id;
	}
	
	/**
	 * Retrieves the feria of the ArtesanoFeriaAssociation
	 *
	 * @return The feria of the ArtesanoFeriaAssociation
	 */
	public FeriaEntity getFeria( )
	{
		return feria;
	}
	
	/**
	 * Updates the feria of the ArtesanoFeriaAssociation by the one given by parameter
	 *
	 * @param feria The new feria of the ArtesanoFeriaAssociation
	 */
	public void setFeria( FeriaEntity feria )
	{
		this.feria = feria;
	}
	
	/**
	 * Retrieves the stand of the ArtesanoFeriaAssociation
	 *
	 * @return The stand of the ArtesanoFeriaAssociation
	 */
	public StandEntity getStand( )
	{
		return stand;
	}
	
	/**
	 * Updates the stand of the ArtesanoFeriaAssociation by the one given by parameter
	 *
	 * @param stand The new stand of the ArtesanoFeriaAssociation
	 */
	public void setStand( StandEntity stand )
	{
		this.stand = stand;
	}
	
	/**
	 * Retrieves the artesano of the ArtesanoFeriaAssociation
	 *
	 * @return The artesano of the ArtesanoFeriaAssociation
	 */
	public ArtesanoEntity getArtesano( )
	{
		return artesano;
	}
	
	/**
	 * Updates the artesano of the ArtesanoFeriaAssociation by the one given by parameter
	 *
	 * @param artesano The new artesano of the ArtesanoFeriaAssociation
	 */
	public void setArtesano( ArtesanoEntity artesano )
	{
		this.artesano = artesano;
	}
	
	/**
	 * Retrieves the fecha of the ArtesanoFeriaAssociation
	 *
	 * @return The fecha of the ArtesanoFeriaAssociation
	 */
	public Date getFecha( )
	{
		return fecha;
	}
	
	/**
	 * Updates the fecha of the ArtesanoFeriaAssociation by the one given by parameter
	 *
	 * @param fecha The new fecha of the ArtesanoFeriaAssociation
	 */
	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}
}