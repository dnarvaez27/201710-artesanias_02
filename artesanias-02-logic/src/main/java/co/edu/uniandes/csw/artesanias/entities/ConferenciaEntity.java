package co.edu.uniandes.csw.artesanias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

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
	
	 
        @Temporal(javax.persistence.TemporalType.DATE)
		private Date fechaInicio;
        @Temporal(javax.persistence.TemporalType.DATE)
		private Date fechaFin;
        @Temporal(javax.persistence.TemporalType.TIME)
		private Time horaInicio;
        @Temporal(javax.persistence.TemporalType.TIME)
	        private Time horaFin;
        
    
    @ManyToOne
		private FeriaEntity feria;
    @ManyToOne(targetEntity = SalonEntity.class)
		private SalonEntity salon;
	
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