package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ia.salazar
 */
@Entity
public class SalonEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private Integer numero;
	
	private Integer capacidad;
	
	// TODO: 28/02/2017 Arreglar Ivan: Anotaciones
	public ConferenciaEntity conferencia;
	
	public PabellonEntity pabellon;
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}
	
	public void setCapacidad( Integer capacidad )
	{
		this.capacidad = capacidad;
	}
	
	public void setConferencia( ConferenciaEntity conferencia )
	{
		this.conferencia = conferencia;
	}
	
	public void setPabellon( PabellonEntity pabellon )
	{
		this.pabellon = pabellon;
	}
	
	public Long getId( )
	{
		return id;
	}
	
	public Integer getNumero( )
	{
		return numero;
	}
	
	public Integer getCapacidad( )
	{
		return capacidad;
	}
	
	public ConferenciaEntity getConferencia( )
	{
		return conferencia;
	}
	
	public PabellonEntity getPabellon( )
	{
		return pabellon;
	}
	
	@Override
	public boolean equals( Object obj )
	{
		
		if( this.getId( ) != null )
		{
			
			return this.getId( ).equals( ( ( SalonEntity ) obj ).getId( ) );
			
		}
		return super.equals( obj );
	}
	
	public int hashCode( )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).hashCode( );
		}
		return super.hashCode( );
	}
}