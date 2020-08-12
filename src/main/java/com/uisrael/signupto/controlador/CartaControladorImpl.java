/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CartaFacadeLocal;
import com.uisrael.signupto.modelo.dao.TipoCartaFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Carta;
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
public class CartaControladorImpl implements Serializable {

    @EJB
    private CartaFacadeLocal cartaFacadeLocal;

    @EJB
    private TipoCartaFacadeLocal tipoCartaFacadeLocal;

    private Carta carta;

    private TipoCarta tipoCarta;

    private List<Carta> listaCarta;

    private List<TipoCarta> listaTipoCartas;
  
    private String accionCarta;

    @PostConstruct
    public void init() {
        carta = new Carta();
        tipoCarta = new TipoCarta();
        listaCarta = cartaFacadeLocal.findAll();
        listaTipoCartas = tipoCartaFacadeLocal.findAll();
    }

    public void insertarCarta() {
        try {
            carta.setTipoCarta(tipoCarta);
            cartaFacadeLocal.create(carta);
            listaCarta = cartaFacadeLocal.findAll();
            listaTipoCartas = tipoCartaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agregado Correctamente "));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error al guardar"));
        }
    }

    public void eliminarCarta() {
        try {
            cartaFacadeLocal.remove(carta);
            listaCarta = cartaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agregado Correctamente "));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error al guardar"));
        }
    }

      public void leerCarta(Carta lCarta) {
        carta = lCarta;
        this.setAccionCarta("M");
    }

    public void modificarCarta() {
        try {
            cartaFacadeLocal.edit(carta);
            listaCarta = cartaFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agregado Correctamente "));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error al guardar"));
        }
    }

    public List<TipoCarta> getListaTipoCartas() {
        return listaTipoCartas;
    }

    public void setListaTipoCartas(List<TipoCarta> listaTipoCartas) {
        this.listaTipoCartas = listaTipoCartas;
    }


    public CartaFacadeLocal getCartaFacadeLocal() {
        return cartaFacadeLocal;
    }

    public void setCartaFacadeLocal(CartaFacadeLocal cartaFacadeLocal) {
        this.cartaFacadeLocal = cartaFacadeLocal;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public List<Carta> getListaCarta() {
        return listaCarta;
    }

    public void setListaCarta(List<Carta> listaCarta) {
        this.listaCarta = listaCarta;
    }

    public String getAccionCarta() {
        return accionCarta;
    }

    public void setAccionCarta(String accionCarta) {
        this.accionCarta = accionCarta;
    }

    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(TipoCarta tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public TipoCartaFacadeLocal getTipoCartaFacadeLocal() {
        return tipoCartaFacadeLocal;
    }

    public void setTipoCartaFacadeLocal(TipoCartaFacadeLocal tipoCartaFacadeLocal) {
        this.tipoCartaFacadeLocal = tipoCartaFacadeLocal;
    }

}
