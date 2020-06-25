/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.RecuperarPass;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface RecuperarPassFacadeLocal {

    void create(RecuperarPass recuperarPass);

    void edit(RecuperarPass recuperarPass);

    void remove(RecuperarPass recuperarPass);

    RecuperarPass find(Object id);

    List<RecuperarPass> findAll();

    List<RecuperarPass> findRange(int[] range);

    int count();
    
}
