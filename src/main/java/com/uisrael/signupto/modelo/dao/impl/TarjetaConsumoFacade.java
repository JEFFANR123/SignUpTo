/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.dao.TarjetaConsumoFacadeLocal;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author janrango
 */
@Stateless
public class TarjetaConsumoFacade extends AbstractFacade<TarjetaConsumo> implements TarjetaConsumoFacadeLocal {

    @PersistenceContext(unitName = "signuptoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaConsumoFacade() {
        super(TarjetaConsumo.class);
    }

    @Override
    public List<Double> sumaConsumosCliente(String ciUser) {
        return em.createQuery("SELECT p.valorConsumo FROM TarjetaConsumoMenu p WHERE p.tarjetaConsumo.fkIdUsuario.cedula =:ciUser", Double.class)
                .setParameter("ciUser", ciUser).getResultList();
    }

    @Override
    public TarjetaConsumo idTarjetaConsumoEditar(int idUsuario) {
    return em.createQuery("SELECT p FROM TarjetaConsumo p WHERE p.fkIdUsuario.idUsuario =:idUsuario", TarjetaConsumo.class)
                .setParameter("idUsuario", idUsuario).getSingleResult();
    }

}
