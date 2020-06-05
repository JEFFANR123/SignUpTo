/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao.impl;

import com.uisrael.signupto.modelo.entidades.GeneraCodigo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface GeneraCodigoFacadeLocal {

    void create(GeneraCodigo generaCodigo);

    void edit(GeneraCodigo generaCodigo);

    void remove(GeneraCodigo generaCodigo);

    GeneraCodigo find(Object id);

    List<GeneraCodigo> findAll();

    List<GeneraCodigo> findRange(int[] range);

    int count();
    
}
