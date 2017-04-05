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

import co.edu.uniandes.csw.artesanias.entities.PabellonEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.PabellonPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author ja.espinosa12
 */
@Stateless
public class PabellonLogic
{
	@Inject
	private PabellonPersistence persistence;
	
	public PabellonEntity getPabellon( Long idEspacio, Long id ) throws BusinessLogicException
	{
                checkId(idEspacio);
                checkId(id);
		return persistence.find( idEspacio, id );
	}
	
	public List<PabellonEntity> getPabellones( Long idEspacio ) throws BusinessLogicException
	{
                checkId(idEspacio);
		return persistence.findAll( idEspacio );
	}
	
	public PabellonEntity createPabellon( PabellonEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.create( entity );
	}
	
	public PabellonEntity updatePabellon( PabellonEntity entity ) throws BusinessLogicException
	{
		check( entity );
		return persistence.update( entity );
	}
	
	public void deletePabellon( Long id ) throws BusinessLogicException
	{
                checkId(id);
		persistence.delete( id );
	}
	
	private void check( PabellonEntity entity ) throws BusinessLogicException
	{
		if( entity.getCapacidad() <= 0 )
		{
			throw new BusinessLogicException( "La capacidad del Pabellon debe ser mayor a 0", Response.Status.BAD_REQUEST );
		}
	}
        /**
     * Revisa la validez del id dado
     * @param id a ser revisado
     * @throws BusinessLogicException si el id es nulo o menor a 0.
     */
    private void checkId(Long id) throws BusinessLogicException {
        if (id == null || id < 0)
            throw new BusinessLogicException("El id ingresado no es válido.", Response.Status.BAD_REQUEST);
    }
}