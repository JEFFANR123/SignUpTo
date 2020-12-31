/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Menu;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface MenuFacadeLocal {

    void create(Menu menu);

    void edit(Menu menu);

    void remove(Menu menu);

    Menu find(Object id);

    List<Menu> findAll();

    List<Menu> findRange(int[] range);

    int count();

    List<Menu> listadoFiltrado(Date inicio, Date fin);

    BigDecimal capturaValorMenu(int idMenu);
    
    Menu capturaObjetoMenuId(int idMenuSeleccionado);

}
