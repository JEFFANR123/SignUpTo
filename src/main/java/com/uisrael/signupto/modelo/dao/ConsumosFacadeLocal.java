/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Consumos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface ConsumosFacadeLocal {

    void create(Consumos consumos);

    void edit(Consumos consumos);

    void remove(Consumos consumos);

    Consumos find(Object id);

    List<Consumos> findAll();

    List<Consumos> findRange(int[] range);

    int count();
    
}
