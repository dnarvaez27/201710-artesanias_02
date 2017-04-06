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
import co.edu.uniandes.csw.artesanias.entities.FeriaEntity;
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
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    @Inject 
    private EspacioPersistence persistence;
    
    @Inject
    private FeriaLogic feriaLogic;
    
    @Inject
    private PabellonLogic pabellonLogic;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    public EspacioEntity getEspacio(Long id) {
        return persistence.find(id);
    }
    
    public List<EspacioEntity> getEspacios() {
        return persistence.findAll();
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
    
    public void deleteEspacio(Long id) {
        persistence.delete(id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de feria
    //--------------------------------------------------------------------------
    
    public FeriaEntity getFeria(Long idFeria, Long idEspacio) {
        for (FeriaEntity f : persistence.find(idEspacio).getFerias()) {
            if (f.getId().equals(idFeria))
                return f;
        }
        throw new IllegalArgumentException("La feria no está asociado al espacio");
    }
    
    public List<FeriaEntity> getFerias(Long id) {
        return persistence.find(id).getFerias();
    }
    
    public void removeFeria(Long idFeria, Long idEspacio) {
        FeriaEntity fe = feriaLogic.getFeria(idFeria);
        fe.setEspacio(null);
        persistence.find(idEspacio).getFerias().remove(fe);
    }
    
    public FeriaEntity addFeria(Long idFeria, Long idEspacio) {
        FeriaEntity fe = feriaLogic.getFeria(idFeria);
        fe.setEspacio(persistence.find(idEspacio));
        return fe;
    }
    
    //--------------------------------------------------------------------------
    // Métodos de pabellon
    //--------------------------------------------------------------------------
    
    public PabellonEntity getPabellon(Long idPabellon, Long idEspacio) {
        for (PabellonEntity p : persistence.find(idEspacio).getPabellones()) {
            if (p.getId().equals(idPabellon))
                return p;
        }
        throw new IllegalArgumentException("El pabellon no está asociado al espacio");
    }
    
    public List<PabellonEntity> getPabellones(Long id) {
        return persistence.find(id).getPabellones();
    }
    
    public void removePabellon(Long idPabellon, Long idEspacio) {
        PabellonEntity pe = pabellonLogic.getPabellon(idPabellon);
        pe.setEspacio(null);
        persistence.find(idEspacio).getFerias().remove(pe);
    }
    
    public PabellonEntity addPabellon(Long idPabellon, Long idEspacio) {
        PabellonEntity pe = pabellonLogic.getPabellon(idPabellon);
        pe.setEspacio(persistence.find(idEspacio));
        return pe;
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    private void checkData(EspacioEntity e) throws BusinessLogicException {
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
