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
import java.util.ArrayList;
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
    
    private List<Credenciales> listaAdministradores;
    
    private List<Credenciales> listaClientes;

    private List<Usuario> infomacionUsuarios;

    @PostConstruct
    public void init() {
        credenciales = new Credenciales();
        usuario = new Usuario();
        pagos = new Pagos();
        listaCredenciales = credencialesFacadeLocal.findAll();
        listaAdministradores = credencialesFacadeLocal.listaUsuarioCredencialeses("A");
        listaClientes = credencialesFacadeLocal.listaUsuarioCredencialeses("C");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
            infomacionUsuarios = usuarioFacadeLocal.consultaUsuarios(usrpass.getIdUsuario().getCedula());
        } catch (Exception e) {
            System.out.println("Ningún usuario ha iniciado sesión.");
        }

    }

    public void iniciaPago() {

        Date hoy = new Date();
        this.pagos.setFkIdUsuario(usuario);
        pagos.setFechaPago(hoy);
        pagos.setValorPago(0.0);
        pagos.setEstado("R");
        pagos.setComentario("Creacion Tarjeta");
        pagosFacadeLocal.create(pagos);

    }

    public static boolean validaCedula(String x) {
        int suma = 0;
        if (x.length() == 9) {
            return false;
        } else {
            int a[] = new int[x.length() / 2];
            int b[] = new int[(x.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < x.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(x.charAt(c)));
                c = c + 2;
                if (i < (x.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(x.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length() - 1)))) {
                return true;
            } else if (suma % 10 == 0 && x.charAt(x.length() - 1) == '0') {
                return true;
            } else {
                return false;
            }

        }
    }

    public void buscarUsuario() {
        String ciUsuario;
        boolean existe = false;
        try {

        } catch (Exception e) {
        }
    }

    public void guardarUsuarioCredenciales() {
        ArrayList listaBusquedaUsuario = new ArrayList();
        listaBusquedaUsuario.addAll(usuarioFacadeLocal.consultaUsuarios(usuario.getCedula()));
        try {
            if (validaCedula(usuario.getCedula()) == true) {
                if (listaBusquedaUsuario.isEmpty()) {
                    this.credenciales.setIdUsuario(usuario);
                    credencialesFacadeLocal.create(credenciales);
                    iniciaPago();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se guardó exitosamente."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "La cédula ya existe."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Verifique los Datos Ingresados"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Verifique los Datos Ingresados"));
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

    public List<Usuario> getInfomacionUsuarios() {
        return infomacionUsuarios;
    }

    public void setInfomacionUsuarios(List<Usuario> infomacionUsuarios) {
        this.infomacionUsuarios = infomacionUsuarios;
    }

    public List<Credenciales> getListaAdministradores() {
        return listaAdministradores;
    }

    public void setListaAdministradores(List<Credenciales> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public List<Credenciales> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Credenciales> listaClientes) {
        this.listaClientes = listaClientes;
    }

    
}
