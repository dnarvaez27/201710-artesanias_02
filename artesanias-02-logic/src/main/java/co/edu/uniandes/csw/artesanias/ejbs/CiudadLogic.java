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

import co.edu.uniandes.csw.artesanias.entities.ArtesanoEntity;
import co.edu.uniandes.csw.artesanias.entities.CiudadEntity;
import co.edu.uniandes.csw.artesanias.entities.EspacioEntity;
import co.edu.uniandes.csw.artesanias.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.artesanias.persistence.CiudadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Logica del recurso ciudad.
 * @author Miller
 */
@Stateless
public class CiudadLogic {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    @Inject 
    private CiudadPersistence persistence;
    
    @Inject
    private EspacioLogic espacioLogic;
    
    @Inject
    private ArtesanoLogic artesanoLogic;
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    public CiudadEntity getCiudad(Long id) {
        return persistence.find(id);
    }
    
    public List<CiudadEntity> getCiudades() {
        return persistence.findAll();
    }
    
    public CiudadEntity createCiudad(CiudadEntity entity) throws BusinessLogicException {
        checkData(entity);
        return persistence.create(entity);
    }
    
    public CiudadEntity updateCiudad(CiudadEntity entity) throws BusinessLogicException {
        CiudadEntity e = persistence.find(entity.getId());
        if (entity.getNombre() == null || entity.getNombre().isEmpty())
            entity.setNombre(e.getNombre());
        if (entity.getPais() == null || entity.getPais().isEmpty())
            entity.setPais(e.getPais());
        if (entity.getImage() == null || entity.getImage().isEmpty())
            entity.setImage(e.getImage());
        if (entity.getArtesanos() == null || entity.getArtesanos().isEmpty())
            entity.setArtesanos(e.getArtesanos());
        if (entity.getEspacios() == null || entity.getEspacios().isEmpty())
            entity.setEspacios(e.getEspacios());
        checkData(entity);
        return persistence.update(entity);
    }
    
    public void deleteCiudad(Long id) {
        persistence.delete(id);
    }
    
    //--------------------------------------------------------------------------
    // Métodos de espacio
    //--------------------------------------------------------------------------
    
    public EspacioEntity getEspacio(Long idEspacio, Long idCiudad) {
        for (EspacioEntity e : persistence.find(idCiudad).getEspacios()) {
            if (e.getId().equals(idEspacio))
                return e;
        }
        throw new IllegalArgumentException("El espacio no está asociado a la ciudad");
    }
    
    public List<EspacioEntity> getEspacios(Long id) {
        return persistence.find(id).getEspacios();
    }
    
    public void removeEspacio(Long idEspacio, Long idCiudad) throws BusinessLogicException {
        EspacioEntity ee = espacioLogic.getEspacio(idCiudad, idEspacio);
        ee.setCiudad(null);
        persistence.find(idCiudad).getEspacios().remove(ee);
    }
    
    public EspacioEntity addEspacio(Long idEspacio, Long idCiudad) throws BusinessLogicException {
        EspacioEntity ee = espacioLogic.getEspacio(idCiudad, idEspacio);
        ee.setCiudad(persistence.find(idCiudad));
        return ee;
    }
    
    //--------------------------------------------------------------------------
    // Métodos de artesano
    //--------------------------------------------------------------------------
    
    public ArtesanoEntity getArtesano(Long idArtesano, Long idCiudad) {
        for (ArtesanoEntity a : persistence.find(idCiudad).getArtesanos()) {
            if (a.getId().equals(idArtesano))
                return a;
        }
        throw new IllegalArgumentException("El artesano no está asociado a la ciudad");
    }
    
    public List<ArtesanoEntity> getArtesanos(Long id) {
        return persistence.find(id).getArtesanos();
    }
    
    public void removeArtesano(Long idArtesano, Long idCiudad) throws BusinessLogicException {
        ArtesanoEntity ae = artesanoLogic.getArtesano(idArtesano);
        ae.setCiudad(null);
        persistence.find(idCiudad).getArtesanos().remove(ae);
    }
    
    //--------------------------------------------------------------------------
    // Métodos Auxiliares
    //--------------------------------------------------------------------------
    
    /**
     * Revisa las reglas de negocio.
     * @param e entidad a ser revisada.
     * @throws BusinessLogicException si no se cumple alguna regla de negocio.
     */
    private void checkData(CiudadEntity e) throws BusinessLogicException {
        if (e.getNombre() == null || e.getNombre().isEmpty())
            throw new BusinessLogicException("El nombre de la ciudad no puede ser vacío o nulo", Response.Status.BAD_REQUEST);
        if (e.getPais() == null || e.getPais().isEmpty())
            throw new BusinessLogicException("El pais de la ciudad no puede ser vacío o nulo", Response.Status.BAD_REQUEST);
        if (e.getImage() == null || e.getImage().isEmpty())
            throw new BusinessLogicException("La imagen de la ciudad no puede ser vacío o nulo", Response.Status.BAD_REQUEST);
    }
}
