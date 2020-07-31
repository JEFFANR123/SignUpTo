/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.ConsumoFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Consumo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author janrango
 */
@Named
@ViewScoped
public class ConsumoControladorImpl implements Serializable{
    
    @EJB
    ConsumoFacadeLocal consumoFacadeLocal;
    
    private Consumo consumo;
    
    @PostConstruct
    public void init(){
    consumo = new Consumo();    
    }
    
    public void guardarConsumo(){
    
        try {
            consumoFacadeLocal.create(consumo);
        } catch (Exception e) {
        }
    
    }

    //GETs & SETs
    
    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }
    
    
    
}
