/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.TarjetaConsumo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface TarjetaConsumoFacadeLocal {

    void create(TarjetaConsumo tarjetaConsumo);

    void edit(TarjetaConsumo tarjetaConsumo);

    void remove(TarjetaConsumo tarjetaConsumo);

    TarjetaConsumo find(Object id);

    List<TarjetaConsumo> findAll();

    List<TarjetaConsumo> findRange(int[] range);

    int count();
    
}
