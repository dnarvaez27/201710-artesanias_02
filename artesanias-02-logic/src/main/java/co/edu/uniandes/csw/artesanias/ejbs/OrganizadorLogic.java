/*
 * The MIT License
 *
 * Copyright 2017 IVAN.
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

import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.OrganizadorPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author IVAN
 */
@Stateless
public class OrganizadorLogic
{
	@Inject
	private OrganizadorPersistence persistence;
	
	public List<OrganizadorEntity> getOrganizadores( )
	{
		return persistence.findAll( );
	}
	
	public OrganizadorEntity getOrganizador( Long id )throws BusinessLogicException
	{
            OrganizadorEntity org = persistence.find( id );
            if (org!=null) {
                return org;
            }
		throw new BusinessLogicException("El organizador no se encuentra", Response.Status.NOT_FOUND);
	}
	
	public OrganizadorEntity createOrganizador( OrganizadorEntity entity )
	{
		return persistence.create( entity );
	}
	
	public OrganizadorEntity updateOrganizador( OrganizadorEntity entity ) throws BusinessLogicException
	{
             OrganizadorEntity org = persistence.find( entity.getId() );
            if (org!=null) {
                return persistence.update( entity );
            }
		throw new BusinessLogicException("El organizador no se encuentra", Response.Status.NOT_FOUND);
		
	}
	
	public void deleteOrganizador( Long id ) throws BusinessLogicException
	{
             OrganizadorEntity org = persistence.find( id );
            if (org!=null) {
                persistence.delete( id );
            }
		throw new BusinessLogicException("El organizador no se encuentra", Response.Status.NOT_FOUND);
		
	}
		
	
}
