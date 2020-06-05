/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.entidades.Credenciales;
import java.io.Serializable;
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
public class ControladorUserImpl implements Serializable {

    public void sesionActiva() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
            if (usrpass == null) {
                context.getExternalContext().redirect("./../index.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Sesion no activa! "));
        }
    }
}
