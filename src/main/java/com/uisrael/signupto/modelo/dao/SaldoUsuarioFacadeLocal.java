/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.entidades.SaldoUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface SaldoUsuarioFacadeLocal {

    void create(SaldoUsuario saldoUsuario);

    void edit(SaldoUsuario saldoUsuario);

    void remove(SaldoUsuario saldoUsuario);

    SaldoUsuario find(Object id);

    List<SaldoUsuario> findAll();

    List<SaldoUsuario> findRange(int[] range);

    int count();
    
}
