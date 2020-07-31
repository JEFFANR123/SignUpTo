/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.ConsumoFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Consumo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author janrango
 */
@Stateless
public class ConsumoFacade extends AbstractFacade<Consumo> implements ConsumoFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsumoFacade() {
        super(Consumo.class);
    }

    @Override
    public List<Double> userConsumos(String ciUser) {
    return em.createQuery("SELECT p.valorConsumo FROM Consumo p WHERE p.fkIdUsuario.cedula =:ciUser",Double.class)
            .setParameter("ciUser", ciUser).getResultList();
    }
    
}
