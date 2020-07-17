/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CredencialesFacadeLocal;
import com.uisrael.signupto.modelo.dao.PagosFacadeLocal;
import com.uisrael.signupto.modelo.dao.UsuarioFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Pagos;
import com.uisrael.signupto.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.Date;
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
    
    @EJB
    private PagosFacadeLocal pagosFacadeLocal;

    private Usuario usuario;
    
    private Pagos pagos;
    
    private Credenciales credenciales;
    
    private List<Credenciales> listaCredenciales;
    
    @PostConstruct
    public void init(){
        credenciales = new Credenciales();
        usuario = new Usuario();
        pagos = new Pagos();
        listaCredenciales = credencialesFacadeLocal.findAll();
    
    }
    
    public void iniciaPago(){
    
        Date hoy = new Date();
        this.pagos.setFkIdUsuario(usuario);
        pagos.setFechaPago(hoy);
        pagos.setValorPago(0.0);
        pagos.setEstado("R");
        pagos.setComentario("Creacion Tarjeta");
        pagosFacadeLocal.create(pagos);
        
    }

    public void guardarUsuarioCredenciales() {

        try {
            this.credenciales.setIdUsuario(usuario);
            credencialesFacadeLocal.create(credenciales);
            iniciaPago();
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
