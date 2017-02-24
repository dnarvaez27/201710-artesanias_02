/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artesanias.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ia.salazar
 */
@Stateless
public class ConferenciaPersistence {
    
    @PersistenceContext(unitName="artesaniaPU")
    protected EntityManager em;
    
    
}
