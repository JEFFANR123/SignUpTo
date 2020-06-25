/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.MenuAccessFacadeLocal;
import com.uisrael.signupto.modelo.entidades.MenuAccess;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author janrango
 */
@Stateless
public class MenuAccessFacade extends AbstractFacade<MenuAccess> implements MenuAccessFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuAccessFacade() {
        super(MenuAccess.class);
    }

    @Override
    public List<MenuAccess> listaMenuAccesses() {
        return em.createQuery("SELECT p FROM MenuAccess p WHERE p.tipoMenu = 'S' ", MenuAccess.class).getResultList();
    }

}
