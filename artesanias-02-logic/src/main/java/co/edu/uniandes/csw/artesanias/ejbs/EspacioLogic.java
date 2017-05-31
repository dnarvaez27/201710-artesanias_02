/*
 * The MIT License
 *
 * Copyright 2017 ja.espinosa12.
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
package co.edu.uniandes.csw.artesanias.ejbs;

import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.EspacioPersistence;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author ja.espinosa12
 */
@Stateless
public class EspacioLogic {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

    @Inject
    private EspacioPersistence persistence;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------

    public EspacioEntity createEspacio(EspacioEntity entity) throws BusinessLogicException {
        checkData(entity);
        return persistence.create(entity);
    }
    
    public List<EspacioEntity> getEspacios( Long id) {
        return persistence.findAll();
    }
    
    public List<EspacioEntity> getEspaciosFromCiudad( Long id )
	{
		return persistence.findAllFromCiudad( id );
	}

    public EspacioEntity getEspacio(Long ciudadId, Long id) throws BusinessLogicException {
        EspacioEntity res = persistence.find( ciudadId, id );
		if( res != null )
		{
			return res;
		}
		throw new BusinessLogicException( String.format( "El espacio %s no pertenece a la ciudad %s ", id, ciudadId ), Response.Status.NOT_FOUND );
    }

    public EspacioEntity updateEspacio(EspacioEntity entity) throws BusinessLogicException {
        checkData(entity);
        return persistence.update(entity);
    }

    public void deleteEspacio(Long ciudadId, Long id) throws BusinessLogicException {
        try
		{
			getEspacio( ciudadId, id ); // Verificar Exception
			persistence.delete( id );
		}
		catch( BusinessLogicException e )
		{
			throw new BusinessLogicException( String.format( "El espacio %s no pertenece a la ciudad %s", id, ciudadId ), Response.Status.FORBIDDEN );
		}
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------

    private void checkData(EspacioEntity e) throws BusinessLogicException {
        if (e.getDireccion() == null) {
            throw new BusinessLogicException("El espacio debe tener una dirección", Response.Status.NOT_FOUND);
        }
        if (e.getTelefono() == null) {
            throw new BusinessLogicException("El espacio debe tener un número de teléfono", Response.Status.NOT_FOUND);
        }
        if (e.getCapacidad() == null) {
            throw new BusinessLogicException("El espacio debe tener una capacidad", Response.Status.NOT_FOUND);
        }
        if (e.getPabellones() == null) {
            throw new BusinessLogicException("El espacio debe tener pabellones", Response.Status.NOT_FOUND);
        }
        if (e.getFerias() == null) {
            throw new BusinessLogicException("El espacio debe tener ferias", Response.Status.NOT_FOUND);
        }
    }
}