/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.TipoCartaFacadeLocal;
import com.uisrael.signupto.modelo.entidades.TipoCarta;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author janrango
 */
@Named
@ViewScoped
public class TipoCartaControladorImpl implements Serializable{

    @EJB
    private TipoCartaFacadeLocal tipoCartaFacadeLocal;

    private TipoCarta tipoCarta;

    private List<TipoCarta> lTipoCarta;

    @PostConstruct
    public void init() {
        tipoCarta = new TipoCarta();
        lTipoCarta = tipoCartaFacadeLocal.findAll();
    }
    
    public void insertarTipoCarta(){
    
        try {
            tipoCartaFacadeLocal.create(tipoCarta);
            lTipoCarta = tipoCartaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Ingresado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aviso","Ingresado Correctamente"));
        }
    }

    public TipoCartaFacadeLocal getTipoCartaFacadeLocal() {
        return tipoCartaFacadeLocal;
    }

    public void setTipoCartaFacadeLocal(TipoCartaFacadeLocal tipoCartaFacadeLocal) {
        this.tipoCartaFacadeLocal = tipoCartaFacadeLocal;
    }

    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(TipoCarta tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public List<TipoCarta> getlTipoCarta() {
        return lTipoCarta;
    }

    public void setlTipoCarta(List<TipoCarta> lTipoCarta) {
        this.lTipoCarta = lTipoCarta;
    }
    
    

}
