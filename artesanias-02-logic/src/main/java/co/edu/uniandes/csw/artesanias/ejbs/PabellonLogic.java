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
public class PabellonLogic {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------

    @Inject
    private PabellonPersistence persistence;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------

    public PabellonEntity getPabellon(Long id) {
        return persistence.find(id);
    }

    public List<PabellonEntity> getPabellones() {
        return persistence.findAll();
    }

    public PabellonEntity createPabellon(PabellonEntity entity) throws BusinessLogicException {
        checkData(entity);
        return persistence.create(entity);
    }

    public PabellonEntity updatePabellon(PabellonEntity entity) throws BusinessLogicException {
        checkData(entity);
        return persistence.update(entity);
    }

    public void deletePabellon(Long id) {
        persistence.delete(id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------

    private void checkData(PabellonEntity e) throws BusinessLogicException {
        if (e.getTipo() == null || e.getTipo().isEmpty()) {
            throw new BusinessLogicException("La feria debe tener un nombre", Response.Status.NOT_FOUND);
        }
        if (e.getCapacidad() == null) {
            throw new BusinessLogicException("La feria debe tener un número de boletas", Response.Status.NOT_FOUND);
        }
        if (e.getSalones() == null) {
            throw new BusinessLogicException("La feria debe tener un espacio donde se realiza", Response.Status.NOT_FOUND);
        }
        if (e.getStands() == null) {
            throw new BusinessLogicException("La feria debe tener un espacio donde se realiza", Response.Status.NOT_FOUND);
        }
    }
}
