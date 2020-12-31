/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.MenuFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Menu;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author janrango
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    @Override
    public List<Menu> listadoFiltrado(Date inicio, Date fin) {
     return em.createQuery("SELECT p FROM Menu p WHERE p.fecha >= :inicio AND p.fecha < :fin",Menu.class)
             .setParameter("inicio", inicio).setParameter("fin", fin).getResultList();
    
    }

    @Override
    public BigDecimal capturaValorMenu(int idMenu) {
    return em.createQuery("SELECT p.precio FROM Menu p WHERE p.idMenu =:idMenu", BigDecimal.class)
             .setParameter("idMenu", idMenu).getSingleResult();
    }

    @Override
    public Menu capturaObjetoMenuId(int idMenu) {
       return em.createQuery("SELECT p FROM Menu p WHERE p.idMenu =:idMenu", Menu.class)
             .setParameter("idMenu", idMenu).getSingleResult();
    }

    
}
