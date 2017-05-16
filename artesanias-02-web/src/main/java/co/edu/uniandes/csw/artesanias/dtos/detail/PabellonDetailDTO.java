/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.SalonDTO;
import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.entities.SalonEntity;
import co.edu.uniandes.csw.artesanias.entities.StandEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ja.espinosa12
 */
@XmlRootElement
public class PabellonDetailDTO extends PabellonDTO
{
    
    /**
     * Espacio al cuál pertenece el pabellon
     */
    private EspacioDTO espacio; 
    
    /**
     * lista de salones en el pabellón
     */
    private List<SalonDTO> salones;
	
    /**
     * Lista de stands en el pabellón
     */
    private List<StandDTO> stands;
	
    public PabellonDetailDTO( )
    {
        super();
    }
	
    /**
    * Genera un nuevo pabellón con el PabellonEntity ingresado
    * @param entity 
    */
    public PabellonDetailDTO( PabellonEntity entity )
    {
        super(entity);
	if( entity != null )
	{
            this.espacio = new EspacioDTO(entity.getEspacio());
            
            salones = new LinkedList<>( );
            for( SalonEntity salon : entity.getSalones( ) )
            {
		salones.add( new SalonDTO( salon ) );
            }
			
            stands = new LinkedList<>( );
            for( StandEntity stand : entity.getStands( ) )
            {
		stands.add( new StandDTO( stand ) );
            }
	}
    }
	
    /**
     * Convierte el pabellón actual a un Pabellonentity
     * @return entity
     */
    public PabellonEntity toEntity( )
    {
	PabellonEntity entity = super.toEntity( );
		
	List<SalonEntity> salonEntities = new LinkedList<>( );
	for( SalonDTO salon : salones )
	{
            salonEntities.add( salon.toEntity( ) );
	}
		
	List<StandEntity> standEntities = new LinkedList<>( );
	for( StandDTO stand : stands )
	{
            standEntities.add( stand.toEntity( ) );
	}
		
        entity.setSalones( salonEntities );
	entity.setStands( standEntities );
        entity.setEspacio(this.espacio.toEntity());
		
	return entity;
    }
    
    public EspacioDTO getEspacio()
    {
        return espacio;
    }
    
    public void setEspacio( EspacioDTO espacio)
    {
        this.espacio = espacio;
    }
}