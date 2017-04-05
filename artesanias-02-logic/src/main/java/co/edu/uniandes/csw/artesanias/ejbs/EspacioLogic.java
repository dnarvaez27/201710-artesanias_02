/*
 * The MIT License
 *
 * Copyright 2017 Miller.
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
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Miller
 */
@Stateless
public class EspacioLogic {
    
    @Inject private EspacioPersistence persistence;
    
    public EspacioEntity getEspacio(Long id) throws BusinessLogicException {
        checkId(id);
        return persistence.find(id);
    }
    
    public EspacioEntity getEspacio(Long idCiudad, Long id) throws BusinessLogicException {
        checkId(idCiudad);
        checkId(id);
        return persistence.find(id, idCiudad);
    }
    
    public List<EspacioEntity> getEspacios(Long idCiudad) throws BusinessLogicException {
        checkId(idCiudad);
        return persistence.findAll(idCiudad);
    }
    
    public EspacioEntity createEspacio(EspacioEntity entity) throws BusinessLogicException {
        checkData(entity);
        return persistence.create(entity);
    }
    
    public EspacioEntity updateEspacio(EspacioEntity e) throws BusinessLogicException {
        EspacioEntity entity = persistence.find(e.getId());
        if (e.getCapacidad() == null || e.getCapacidad() <= 0)
            e.setCapacidad(entity.getCapacidad());
        if (e.getCiudad() == null)
            e.setCiudad(entity.getCiudad());
        if (e.getDireccion() == null || e.getDireccion().isEmpty())
            e.setDireccion(entity.getDireccion());
        if (e.getNombre() == null || e.getNombre().isEmpty())
            e.setNombre(entity.getNombre());
        if (e.getImage() == null || e.getImage().isEmpty())
            e.setImage(entity.getImage());
        if (e.getTelefono() == null || e.getTelefono().isEmpty())
            e.setTelefono(entity.getTelefono());
        if (e.getPabellones() == null || e.getPabellones().isEmpty())
            e.setPabellones(entity.getPabellones());
        checkData(e);
        return persistence.update(entity);
    }
    
    public void deleteEspacio(Long id) throws BusinessLogicException {
        checkId(id);
        persistence.delete(id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    /**
     * Revisa la validez del id dado
     * @param id a ser revisado
     * @throws BusinessLogicException si el id es nulo o menor a 0.
     */
    private void checkId(Long id) throws BusinessLogicException {
        if (id == null || id < 0)
            throw new BusinessLogicException("El id ingresado no es válido.", Response.Status.BAD_REQUEST);
    }
    
    private void checkData(EspacioEntity e) throws BusinessLogicException {
        checkId(e.getId());
        if (e.getCapacidad() == null || e.getCapacidad() <= 0)
            throw new BusinessLogicException("la capacidad del espacio no puede ser vacío, nulo o menor o igual a 0", Response.Status.BAD_REQUEST);
        if (e.getCiudad() == null)
            throw new BusinessLogicException("La ciudad del espacio no puede ser nula", Response.Status.BAD_REQUEST);
        if (e.getDireccion() == null || e.getDireccion().isEmpty())
            throw new BusinessLogicException("La dirección del espacio no puede ser nula o vacía", Response.Status.BAD_REQUEST);
        if (e.getNombre() == null || e.getNombre().isEmpty())
            throw new BusinessLogicException("El nombre del espacio no puede ser nula o vacía", Response.Status.BAD_REQUEST);
        if (e.getImage() == null || e.getImage().isEmpty())
            throw new BusinessLogicException("La imagén del espacio no puede ser nula o vacía", Response.Status.BAD_REQUEST);
        if (e.getTelefono() == null || e.getTelefono().isEmpty())
            throw new BusinessLogicException("El teléfono del espacio no puede ser nula o vacía", Response.Status.BAD_REQUEST);
        if (e.getPabellones() == null || e.getPabellones().isEmpty())
            throw new BusinessLogicException("Los pabellones del espacio no puede ser nulos o vacíos", Response.Status.BAD_REQUEST);
        for (PabellonEntity pabellon : e.getPabellones()) {
            if (pabellon == null)
                throw new BusinessLogicException("Los pabellones del espacio no puede ser nulos o vacíos", Response.Status.BAD_REQUEST);
        }
    }
}
