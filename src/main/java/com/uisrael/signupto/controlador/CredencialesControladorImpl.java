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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author janrango
 */
@Named
@ViewScoped
public class CredencialesControladorImpl implements Serializable {

    @EJB
    private CredencialesFacadeLocal credencialesFacadeLocal;

    private HttpServletRequest httpServlet;

    private Credenciales credenciales;
    
    private Credenciales creusr;

    @PostConstruct
    public void init() {
        credenciales = new Credenciales();
    }

    public CredencialesControladorImpl() {
        httpServlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String iniciarSesion() {

        String redireccion = null;
        httpServlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            creusr = credencialesFacadeLocal.iniciarSesion(credenciales);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", creusr);
            int temp = creusr.getEstado();
            if (creusr != null) {
                httpServlet.getSession().setAttribute("usuario", creusr.getUserName());
                httpServlet.getSession().setAttribute("tipoUsuario", creusr.getTipoUser());
                httpServlet.getSession().setAttribute("credenTemp", creusr);
                if (creusr.getTipoUser().equals("A") && temp == 1) {
                    redireccion = "protected/administrator/adminInicio.xhtml?faces-redirect=true";
                } else if (creusr.getTipoUser().equals("E") && temp == 1) {
                    redireccion = "protected/confMenuOptions.xhtml?faces-redirect=true";
                } else if (creusr.getTipoUser().equals("C") && temp == 1) {
                    redireccion = "customer/clienteInicio.xhtml?faces-redirect=true";
                } else if (temp == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Favor, contactar al Administrador"));
                    credenciales = new Credenciales();
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Usuario o Contraseña incorrectos"));
                credenciales = new Credenciales();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al validar las credenciales"));
            credenciales = new Credenciales();
        }
        return redireccion;
    }

    public void cerrarSesion() {
        String redireccion = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        } catch (Exception e) {
        }

    }

    public void verificarSession(String nivel) {
        httpServlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Credenciales creTemp = (Credenciales) httpServlet.getSession().getAttribute("credenTemp");

        try {
            if (creTemp != null) {
                if (!creTemp.getTipoUser().equals(nivel)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Violación de Seguridad", "Permisos de usuario inválidos, saliendo..."));
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/signupto/index.xhtml");
                    credenciales = new Credenciales();
                }
            } else {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Verificación de Seguridad."));
        }
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    public Credenciales getCreusr() {
        return creusr;
    }

    public void setCreusr(Credenciales creusr) {
        this.creusr = creusr;
    }

}
