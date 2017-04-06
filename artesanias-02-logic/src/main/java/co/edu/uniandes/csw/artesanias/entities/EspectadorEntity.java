package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.List;

/**
 * EspectadorEntity, contiene la información necesaria para modelar un Espectador quien acude a Ferias Artesanales
 *
 * @author d.narvaez11
 */
@Entity
@PrimaryKeyJoinColumn( referencedColumnName = "id" )
public class EspectadorEntity extends UsuarioEntity implements Serializable
{
	/**
	 * Edad del Espectador, necesaria para verificar el acceso a compras por internet y a festivales con restricción
	 */
	private Integer edad;
	
	/**
	 * Boletas que el Espectador ha comprado
	 */
	@OneToMany( targetEntity = BoletaEntity.class, mappedBy = "espectador", fetch = FetchType.LAZY )
	private List<BoletaEntity> boletas;
	
	/**
	 * Retrieves the edad of the EspectadorEntity
	 *
	 * @return The edad of the EspectadorEntity
	 */
	public Integer getEdad( )
	{
		return edad;
	}
	
	/**
	 * Updates the edad of the EspectadorEntity by the one given by parameter
	 *
	 * @param edad The new edad of the EspectadorEntity
	 */
	public void setEdad( Integer edad )
	{
		this.edad = edad;
	}
	
	/**
	 * Retrieves the boletas of the EspectadorEntity
	 *
	 * @return The boletas of the EspectadorEntity
	 */
	public List<BoletaEntity> getBoletas( )
	{
		return boletas;
	}
	
	/**
	 * Updates the boletas of the EspectadorEntity by the one given by parameter
	 *
	 * @param boletas The new boletas of the EspectadorEntity
	 */
	public void setBoletas( List<BoletaEntity> boletas )
	{
		this.boletas = boletas;
	}
}