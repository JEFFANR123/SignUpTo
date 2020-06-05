/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.HistoryConsumoFacadeLocal;
import com.uisrael.signupto.modelo.entidades.HistoryConsumo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author janrango
 */
@Stateless
public class HistoryConsumoFacade extends AbstractFacade<HistoryConsumo> implements HistoryConsumoFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryConsumoFacade() {
        super(HistoryConsumo.class);
    }
    
}