/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.dtos.detail;

import co.edu.uniandes.csw.artesanias.dtos.CiudadDTO;
import co.edu.uniandes.csw.artesanias.dtos.EspacioDTO;
import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.dtos.PabellonDTO;
import co.edu.uniandes.csw.artesanias.dtos.SalonDTO;
import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
public class EspacioDetailDTO extends EspacioDTO
{
    
 
    private CiudadDTO ciudad; 
    

    private List<PabellonDTO> pabellones;
	

    private List<FeriaDTO> ferias;
	
    public EspacioDetailDTO( )
    {
        super();
    }
	
    public EspacioDetailDTO( EspacioEntity entity )
    {
        super(entity);
	if( entity != null )
	{
            this.ciudad = new CiudadDTO(entity.getCiudad());
            
           pabellones = new LinkedList<>( );
            for( PabellonEntity pabellon : entity.getPabellones( ) )
            {
		pabellones.add( new PabellonDTO( pabellon ) );
            }
			
            ferias = new LinkedList<>( );
            for( FeriaEntity feria : entity.getFerias( ) )
            {
		ferias.add( new FeriaDTO( feria ) );
            }
	}
    }
	
    public EspacioEntity toEntity( )
    {
	EspacioEntity entity = super.toEntity( );
		
	List<PabellonEntity> pabellonEntities = new LinkedList<>( );
	for( PabellonDTO pabellon : pabellones )
	{
            pabellonEntities.add( pabellon.toEntity( ) );
	}
		
	List<FeriaEntity> feriaEntities = new LinkedList<>( );
	for( FeriaDTO feria : ferias )
	{
            feriaEntities.add( feria.toEntity( ) );
	}
		
        entity.setPabellones( pabellonEntities );
	entity.setFerias( feriaEntities );
        entity.setCiudad(this.ciudad.toEntity());
		
	return entity;
    }
    
    public CiudadDTO getCiudad()
    {
        return ciudad;
    }
    
    public void setCiudad( CiudadDTO ciudad)
    {
        this.ciudad = ciudad;
    }
}