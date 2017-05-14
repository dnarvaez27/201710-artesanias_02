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
package co.edu.uniandes.csw.artesanias.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
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
	@PodamExclude
	@OneToMany( targetEntity = BoletaEntity.class, mappedBy = "espectador", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
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