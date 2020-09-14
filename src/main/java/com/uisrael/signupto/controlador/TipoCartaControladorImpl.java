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
public class TipoCartaControladorImpl implements Serializable {

    @EJB
    private TipoCartaFacadeLocal tipoCartaFacadeLocal;

    private TipoCarta tipoCarta;

    private List<TipoCarta> listaTipoCarta;

    @PostConstruct
    public void init() {
        tipoCarta = new TipoCarta();
        listaTipoCarta = tipoCartaFacadeLocal.findAll();
    }

    public void insertarTipoCarta() {

        try {
            tipoCartaFacadeLocal.create(tipoCarta);
            listaTipoCarta = tipoCartaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Ingresado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ingresado Correctamente"));
        }
    }

    public void eliminarTipoCarta(TipoCarta dltTipoCarta) {
        try {
            tipoCartaFacadeLocal.remove(dltTipoCarta);
            listaTipoCarta = tipoCartaFacadeLocal.findAll();
        } catch (Exception e) {
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

    public List<TipoCarta> getListaTipoCarta() {
        return listaTipoCarta;
    }

    public void setListaTipoCarta(List<TipoCarta> listaTipoCarta) {
        this.listaTipoCarta = listaTipoCarta;
    }

}
