/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.CredencialesFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
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
public class CredencialesFacade extends AbstractFacade<Credenciales> implements CredencialesFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CredencialesFacade() {
        super(Credenciales.class);
    }

    @Override
    public Credenciales iniciarSesion(Credenciales cre) {

        Credenciales credenciales = null;
        String consulta;

        try {
            consulta = "FROM Credenciales u WHERE u.userName = ?1 and u.userPass = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cre.getUserName());
            query.setParameter(2, cre.getUserPass());
            List<Credenciales> lista = query.getResultList();
            if (!lista.isEmpty()) {
               credenciales = lista.get(0);
            }

        } catch (Exception e) {
            throw e;
        } 
        return credenciales;
    }

    @Override
    public List<Credenciales> listaUsuarioCredencialeses(String tipo) {
    return em.createQuery("SELECT p FROM Credenciales p WHERE p.tipoUser = :tipo", Credenciales.class)
            .setParameter("tipo", tipo).getResultList();
    }

    
}
