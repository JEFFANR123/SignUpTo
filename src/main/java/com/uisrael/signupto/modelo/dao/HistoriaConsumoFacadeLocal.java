/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.HistoriaConsumo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface HistoriaConsumoFacadeLocal {

    void create(HistoriaConsumo historiaConsumo);

    void edit(HistoriaConsumo historiaConsumo);

    void remove(HistoriaConsumo historiaConsumo);

    HistoriaConsumo find(Object id);

    List<HistoriaConsumo> findAll();

    List<HistoriaConsumo> findRange(int[] range);

    int count();
    
}
