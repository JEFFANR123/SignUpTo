/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CredencialesFacadeLocal;
import com.uisrael.signupto.modelo.dao.UsuarioFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Usuario;
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
public class UsuarioCredencialesControlador implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    private CredencialesFacadeLocal credencialesFacadeLocal;

    private Usuario usuario;

    private Credenciales credenciales;
    
    private List<Credenciales> listaCredenciales;
    
    @PostConstruct
    public void init(){
        credenciales = new Credenciales();
        listaCredenciales = credencialesFacadeLocal.findAll();
    
    }

    public void guardarUsuarioCredenciales() {

        try {
            this.credenciales.setIdUsuario(usuario);
            credencialesFacadeLocal.create(credenciales);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se guardó exitosamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "El usuario se guardó exitosamente."));

        }
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public CredencialesFacadeLocal getCredencialesFacadeLocal() {
        return credencialesFacadeLocal;
    }

    public void setCredencialesFacadeLocal(CredencialesFacadeLocal credencialesFacadeLocal) {
        this.credencialesFacadeLocal = credencialesFacadeLocal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    public List<Credenciales> getListaCredenciales() {
        return listaCredenciales;
    }

    public void setListaCredenciales(List<Credenciales> listaCredenciales) {
        this.listaCredenciales = listaCredenciales;
    }
    
    

}
