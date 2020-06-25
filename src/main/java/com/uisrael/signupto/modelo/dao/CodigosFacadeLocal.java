/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Codigos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface CodigosFacadeLocal {

    void create(Codigos codigos);

    void edit(Codigos codigos);

    void remove(Codigos codigos);

    Codigos find(Object id);

    List<Codigos> findAll();

    List<Codigos> findRange(int[] range);

    int count();
    
}