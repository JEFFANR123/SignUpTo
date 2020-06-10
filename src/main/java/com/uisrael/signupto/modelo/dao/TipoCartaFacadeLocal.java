/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.TipoCarta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface TipoCartaFacadeLocal {

    void create(TipoCarta tipoCarta);

    void edit(TipoCarta tipoCarta);

    void remove(TipoCarta tipoCarta);

    TipoCarta find(Object id);

    List<TipoCarta> findAll();

    List<TipoCarta> findRange(int[] range);

    int count();
    
}
