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
    public List<Carta> listaCarta(String opc) {
        //Select nombretipo, nombre from t_carta inner join t_tipocarta 
        //on t_carta.idtipocarta=t_tipocarta.idtipocarta WHERE t_tipocarta.nombretipo='Segundo'
        
       return em.createQuery("SELECT p FROM Carta p JOIN p.tipoCarta t WHERE t.nombreTipo = :opc",Carta.class)
               .setParameter("opc", opc).getResultList();
    }

}
