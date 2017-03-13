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
package co.edu.uniandes.csw.artesanias.dtos;

import co.edu.uniandes.csw.artesanias.entities.BoletaEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miller
 */
@XmlRootElement
public class BoletaDTO {
    
    //--------------------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------------------
    
    /**
     * Id de la boleta.
     * Valor auto generado por la base de datos.
     */
    private Long id;

    /**
     * Tipo de boleta.
     */
    private Double tipo;

    /**
     * Fecha de inicio de la boleta.
     */
    private Date inicio;

    /**
     * Fecha de fin de la boleta.
     */
    private Date fin;

    /**
     * Fecha de la boleta.
     */
    private Double precio;
    
    //--------------------------------------------------------------------------
    // Contructor
    //--------------------------------------------------------------------------
    
    public BoletaDTO() {}

    /**
     * Crea un objeto BoletaDTO a partir de un objeto BoletaEntity.
     * @param entity entidad boleta desde la cual se va a crear el nuevo objeto.
     */
    public BoletaDTO(BoletaEntity entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.inicio = entity.getInicio();
        this.fin = entity.getFin();
        this.precio = entity.getPrecio();
    }
        
    //--------------------------------------------------------------------------
    // Getters y Setters
    //--------------------------------------------------------------------------

    /**
     * Devuelve el id de la boleta.
     * @return id de la bolet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id de la boleta.
     * post: Se cambio el id de la boleta.
     * @param id nuevo id de la boleta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el tipo de la boleta.
     * @return tipo de la boleta.
     */
    public Double getTipo() {
        return tipo;
    }

    /**
     * Cambia el tipo de la boleta.
     * post: Se cambio el tipo de la boleta.
     * @param tipo nuevo tipo de la boleta.
     */
    public void setTipo(Double tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la fecha de inicio de la boleta.
     * @return fecha de inicio de la boleta.
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * Cambia la fecha de inicio de la boleta.
     * post: Se cambio la fecha de inicio de la boleta.
     * @param inicio nueva fecha de inicio de la boleta.
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * Devuelve la fecha de fin de la boleta.
     * @return fecha de inicio de la boleta.
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Cambia la fecha de fin de la boleta.
     * post: Se cambio la fecha de fin de la boleta.
     * @param fin nueva fecha de fin de la boleta.
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * Devuelve el precio de la boleta.
     * @return precio de la bolet.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Cambia el precio de la boleta.
     * post: Se cambio el precio de la boleta.
     * @param precio nuevo precio de la boleta.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    //--------------------------------------------------------------------------
    // Métodos
    //--------------------------------------------------------------------------
    
    /**
     * Convierte un objeto BoletaDTO a BoletaEntity.
     * @return Nuevo objeto BoletaEntity.
     */
    public BoletaEntity toEntity() {
        BoletaEntity entity = new BoletaEntity();
        entity.setId(this.id);
        entity.setInicio(this.inicio);
        entity.setFin(this.fin);
        entity.setTipo(this.tipo);
        entity.setPrecio(this.precio);
        return entity;
    }
}
