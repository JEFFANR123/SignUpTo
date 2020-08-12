/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.CartaFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Carta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author janrango
 */
@Stateless
public class CartaFacade extends AbstractFacade<Carta> implements CartaFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartaFacade() {
        super(Carta.class);
    }

    @Override
    public List<Carta> listaCarta(int opc) {
       
       return em.createQuery("SELECT p FROM Carta p WHERE p.tipoCarta.idTipoCarta = :opc",Carta.class)
               .setParameter("opc", opc).getResultList();
    }

}
