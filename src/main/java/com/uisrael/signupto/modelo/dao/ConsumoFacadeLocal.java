/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Consumo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface ConsumoFacadeLocal {

    void create(Consumo consumo);

    void edit(Consumo consumo);

    void remove(Consumo consumo);

    Consumo find(Object id);

    List<Consumo> findAll();

    List<Consumo> findRange(int[] range);

    int count();
    
    List<Double> userConsumos(String ciUser);
    
}
