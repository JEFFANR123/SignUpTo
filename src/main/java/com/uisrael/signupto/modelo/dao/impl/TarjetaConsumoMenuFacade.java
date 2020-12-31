/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.entidades.TarjetaConsumoMenu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.uisrael.signupto.modelo.dao.TarjetaConsumoMenuFacadeLocal;

/**
 *
 * @author janrango
 */
@Stateless
public class TarjetaConsumoMenuFacade extends AbstractFacade<TarjetaConsumoMenu> implements TarjetaConsumoMenuFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaConsumoMenuFacade() {
        super(TarjetaConsumoMenu.class);
    }

    @Override
    public List<Double> userConsumos(String ciUser) {
    return em.createQuery("SELECT p.valorConsumo FROM Consumo p WHERE p.fkIdUsuario.cedula =:ciUser",Double.class)
            .setParameter("ciUser", ciUser).getResultList();
    }
    
}