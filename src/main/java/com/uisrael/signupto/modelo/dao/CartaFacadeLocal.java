/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Carta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface CartaFacadeLocal {

    void create(Carta carta);

    void edit(Carta carta);

    void remove(Carta carta);

    Carta find(Object id);

    List<Carta> findAll();

    List<Carta> findRange(int[] range);

    int count();
    
}
