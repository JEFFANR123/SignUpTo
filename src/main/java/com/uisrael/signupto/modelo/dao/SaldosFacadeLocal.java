/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Saldos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface SaldosFacadeLocal {

    void create(Saldos saldos);

    void edit(Saldos saldos);

    void remove(Saldos saldos);

    Saldos find(Object id);

    List<Saldos> findAll();

    List<Saldos> findRange(int[] range);

    int count();
    
}
