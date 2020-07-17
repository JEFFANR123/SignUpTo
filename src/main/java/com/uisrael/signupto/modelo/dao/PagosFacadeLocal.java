/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Pagos;
import com.uisrael.signupto.modelo.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface PagosFacadeLocal {

    void create(Pagos pagos);

    void edit(Pagos pagos);

    void remove(Pagos pagos);

    Pagos find(Object id);

    List<Pagos> findAll();

    List<Pagos> findRange(int[] range);

    int count();

    List<Pagos> lstFltPagos(String estado);

}
