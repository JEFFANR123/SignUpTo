/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Menu;
import com.uisrael.signupto.modelo.entidades.ReportSemanalConsumo;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumoMenu;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface TarjetaConsumoMenuFacadeLocal {

    void create(TarjetaConsumoMenu consumo);

    void edit(TarjetaConsumoMenu consumo);

    void remove(TarjetaConsumoMenu consumo);

    TarjetaConsumoMenu find(Object id);

    List<TarjetaConsumoMenu> findAll();

    List<TarjetaConsumoMenu> findRange(int[] range);

    int count();
    
    List<Double> userConsumos(String ciUser);
    
    List<TarjetaConsumoMenu> consultaTarjetaConsumoMenu(String ciUser);
    
    List<TarjetaConsumoMenu> consultaCodigosTCM(String ciUser, Date hoy);
    
    List<TarjetaConsumoMenu> buscarCodidoConsumo(int codigo);
    
    List<TarjetaConsumoMenu> buscarConsumoMenus(Menu idMenu);
    
    List<Object[]> consultaListConsumoSemanal();
    
}
