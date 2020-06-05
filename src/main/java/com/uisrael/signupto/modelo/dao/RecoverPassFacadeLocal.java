/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.entidades.RecoverPass;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface RecoverPassFacadeLocal {

    void create(RecoverPass recoverPass);

    void edit(RecoverPass recoverPass);

    void remove(RecoverPass recoverPass);

    RecoverPass find(Object id);

    List<RecoverPass> findAll();

    List<RecoverPass> findRange(int[] range);

    int count();
    
}
