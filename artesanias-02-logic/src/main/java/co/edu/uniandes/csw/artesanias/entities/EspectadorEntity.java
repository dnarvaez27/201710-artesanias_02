package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author d.narvaez11
 */
@Entity
public class EspectadorEntity extends UsuarioEntity implements Serializable
{
	private Integer edad;
	
	@OneToMany( targetEntity = BoletaEntity.class, fetch = FetchType.LAZY )
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