/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CredencialesFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Usuario;
import java.io.Serializable;
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
public class CredencialesControladorImpl implements Serializable {

    @EJB
    private CredencialesFacadeLocal credencialesFacadeLocal;

    private Credenciales credenciales;

    @PostConstruct
    public void init() {
        credenciales = new Credenciales();
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    public String iniciarSesion() {

        Credenciales creusr;

        String redireccion = null;

        try {
            creusr = credencialesFacadeLocal.iniciarSesion(credenciales);
            if (creusr != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", creusr);
                if (creusr.getTipoUser().equals("A")) {
                    redireccion = "protected/adminInicio.xhtml?faces-redirect=true";
                } else {
                    if (creusr.getTipoUser().equals("E") || creusr.getTipoUser().equals("C")) {
                    }
                    redireccion = "customer/clienteInicio.xhtml?faces-redirect=true";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No existe"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al Ingresar"));
        }
        return redireccion;
    }

    public void cerrarSesion() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
