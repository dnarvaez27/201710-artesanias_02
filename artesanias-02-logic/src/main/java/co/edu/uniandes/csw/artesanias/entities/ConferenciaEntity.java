package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author ia.salazar
 */
@Entity
public class ConferenciaEntity implements Serializable
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String conferencista;
	
	private String tema;
	
	 
		private Date fechaInicio;
		private Date fechaFin;
		private Time horaInicio;
	        private Time horaFin;
		public FeriaEntity feria;
		public SalonEntity salon;
	
	public void setId( Long id )
	{
		this.id = id;
	}
	
	public void setConferencista( String conferencista )
	{
		this.conferencista = conferencista;
	}
	
	public void setTema( String tema )
	{
		this.tema = tema;
	}
	
	    public void setFechaInicio(Date fechaInicio) {
	        this.fechaInicio = fechaInicio;
	    }
	
	    public void setFechaFin(Date fechaFin) {
	        this.fechaFin = fechaFin;
	   }
	
	    public void setHoraInicio(Time horaInicio) {
	       this.horaInicio = horaInicio;
	    }
	
	    public void setHoraFin(Time horaFin) {
	        this.horaFin = horaFin;
	    }
	
	public Long getId( )
	{
		return id;
	}
	
	public String getConferencista( )
	{
		return conferencista;
	}
	
	public String getTema( )
	{
		return tema;
	}
	
	    public Date getFechaInicio() {
	        return fechaInicio;
	    }
	
	   public Date getFechaFin() {
	       return fechaFin;
    }
	
	    public Time getHoraInicio() {
	        return horaInicio;
	    }
	
	   public Time getHoraFin() {
	        return horaFin;
	    }
	
	    public FeriaEntity getFeria() {
	        return feria;
	   }
	
	    public SalonEntity getSalon() {
	        return salon;
	    }
	
	    public void setFeria(FeriaEntity feria) {
	        this.feria = feria;
	    }
	
	    public void setSalon(SalonEntity salon) {
	        this.salon = salon;
	    }
	
	@Override
	public boolean equals( Object obj )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).equals( ( ( ConferenciaEntity ) obj ).getId( ) );
		}
		return super.equals( obj );
	}
	
	@Override
	public int hashCode( )
	{
		if( this.getId( ) != null )
		{
			return this.getId( ).hashCode( );
		}
		return super.hashCode( );
	}
}