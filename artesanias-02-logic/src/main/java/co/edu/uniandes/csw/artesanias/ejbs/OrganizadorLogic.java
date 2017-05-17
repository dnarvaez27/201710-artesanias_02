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

import co.edu.uniandes.csw.artesanias.entities.EspectadorEntity;
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
import co.edu.uniandes.csw.artesanias.entities.OrganizadorEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.FeriaPersistence;
import co.edu.uniandes.csw.artesanias.persistence.OrganizadorPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author IVAN
 */
@Stateless
public class OrganizadorLogic {

    @Inject
    private OrganizadorPersistence persistence;

    @Inject
    private FeriaPersistence feriaPersistence;

    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    public List<OrganizadorEntity> getOrganizadores() {
        return persistence.findAll();
    }

    public OrganizadorEntity getOrganizador(Long id) {
        return persistence.find(id);
    }

    public OrganizadorEntity createOrganizador(OrganizadorEntity entity) throws BusinessLogicException {
        check(entity);
        return persistence.create(entity);
    }

    public OrganizadorEntity updateOrganizador(OrganizadorEntity entity) {
        return persistence.update(entity);

    }

    public void deleteOrganizador(Long id) {
        persistence.delete(id);
    }

    //--------------------------------------------------------------------------
    // Métodos de feria
    //--------------------------------------------------------------------------
    
    public FeriaEntity getFeria(Long idOrganizador, Long idFeria) {
        for (FeriaEntity f : persistence.find(idOrganizador).getFerias()) {
            if (f.getId().equals(idFeria))
                return f;
        }
        throw new IllegalArgumentException("La feria no está asociada al organizador");
    }

    public List<FeriaEntity> getFerias(Long id) {
        return persistence.find(id).getFerias();
    }

    public void removeFeria(Long idFeria, Long idOrganizador) {
        OrganizadorEntity oe = persistence.find(idOrganizador);
        FeriaEntity fe = feriaPersistence.find(idFeria);
        if (fe == null)
            throw new IllegalArgumentException("La feria no existe");
        fe.getOrganizadores().remove(oe);
    }

    public FeriaEntity addFeria(Long idFeria, Long idOrganizador) {
        OrganizadorEntity oe = persistence.find(idOrganizador);
        FeriaEntity fe = feriaPersistence.find(idFeria);
        if (fe == null)
            throw new IllegalArgumentException("La feria no existe");
        oe.getFerias().add(fe);
        return fe;
    }
    
    private void check( OrganizadorEntity entity ) throws BusinessLogicException
	{
		if( entity.getCorreo( ) == null || entity.getCorreo( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "El correo no puede ser vacío", Response.Status.BAD_REQUEST );
		}
		if( entity.getContrasena( ) == null || entity.getContrasena( ).isEmpty( ) )
		{
			throw new BusinessLogicException( "La contraseña no puede ser vacía", Response.Status.BAD_REQUEST );
		}
	}
}
