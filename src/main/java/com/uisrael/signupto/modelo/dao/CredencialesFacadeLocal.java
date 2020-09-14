/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.dao;

import com.uisrael.signupto.modelo.entidades.Credenciales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author janrango
 */
@Local
public interface CredencialesFacadeLocal {

    void create(Credenciales credenciales);

    void edit(Credenciales credenciales);

    void remove(Credenciales credenciales);

    Credenciales find(Object id);

    List<Credenciales> findAll();

    List<Credenciales> findRange(int[] range);

    int count();
    
    Credenciales iniciarSesion(Credenciales cre);
    
    List<Credenciales> listaUsuarioCredencialeses(String tipo);
    
}
