/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.HistoriaConsumoFacadeLocal;
import com.uisrael.signupto.modelo.entidades.HistoriaConsumo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author janrango
 */
@Stateless
public class HistoriaConsumoFacade extends AbstractFacade<HistoriaConsumo> implements HistoriaConsumoFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaConsumoFacade() {
        super(HistoriaConsumo.class);
    }
    
}
