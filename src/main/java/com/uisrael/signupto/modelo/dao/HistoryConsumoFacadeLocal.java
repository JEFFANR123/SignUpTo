/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.HistoryConsumo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface HistoryConsumoFacadeLocal {

    void create(HistoryConsumo historyConsumo);

    void edit(HistoryConsumo historyConsumo);

    void remove(HistoryConsumo historyConsumo);

    HistoryConsumo find(Object id);

    List<HistoryConsumo> findAll();

    List<HistoryConsumo> findRange(int[] range);

    int count();
    
}
