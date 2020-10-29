/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.MenuCarta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface MenuCartaFacadeLocal {

    void create(MenuCarta menuCarta);

    void edit(MenuCarta menuCarta);

    void remove(MenuCarta menuCarta);

    MenuCarta find(Object id);

    List<MenuCarta> findAll();

    List<MenuCarta> findRange(int[] range);

    int count();
    
}
