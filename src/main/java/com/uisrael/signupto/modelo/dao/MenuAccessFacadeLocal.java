/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.MenuAccess;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface MenuAccessFacadeLocal {

    void create(MenuAccess menuAccess);

    void edit(MenuAccess menuAccess);

    void remove(MenuAccess menuAccess);

    MenuAccess find(Object id);

    List<MenuAccess> findAll();

    List<MenuAccess> findRange(int[] range);

    int count();
    
    List<MenuAccess> listaMenuAccesses();
}
