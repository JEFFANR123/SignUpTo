/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.PagosFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Pagos;
import com.uisrael.signupto.modelo.entidades.Usuario;
import java.sql.PreparedStatement;
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
public class PagosFacade extends AbstractFacade<Pagos> implements PagosFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagosFacade() {
        super(Pagos.class);
    }

    @Override
    public List<Pagos> lstFltPagos(String est) {

        return em.createQuery("SELECT p FROM Pagos p WHERE p.estado = :est", Pagos.class)
                .setParameter("est", est).getResultList();
    }

    @Override
    public List<Double> sumaPagosCliente(String idUser) {
        return em.createQuery("SELECT p.valorPago FROM Pagos p WHERE p.fkIdUsuario.cedula = :idUser and p.estado ='A'", Double.class)
                .setParameter("idUser", idUser).getResultList();
    }

    @Override
    public List<Pagos> listaPagosUsuario(String idUser) {
        return em.createQuery("SELECT p FROM Pagos p WHERE p.fkIdUsuario.cedula = :idUser", Pagos.class)
                .setParameter("idUser", idUser).getResultList();
      }

}
