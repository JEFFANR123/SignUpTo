/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CredencialesFacadeLocal;
import com.uisrael.signupto.modelo.dao.PagosFacadeLocal;
import com.uisrael.signupto.modelo.dao.TarjetaConsumoFacadeLocal;
import com.uisrael.signupto.modelo.dao.UsuarioFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Pagos;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumo;
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

    @EJB
    private TarjetaConsumoFacadeLocal tarjetaConsumoFacadeLocal;

    private Usuario usuario;

    private TarjetaConsumo tarjetaConsumo;

    private TarjetaConsumoControladorImpl tarjetaConsumoControladorImpl;

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
        tarjetaConsumo = new TarjetaConsumo();
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
        pagos.setFkIdUsuario(usuario);
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

    public void generaTarjetaConsumoInicial() {

        Date hoy = new Date();
        tarjetaConsumo.setFkIdUsuario(usuario);
        tarjetaConsumo.setFechaExpedicion(hoy);
        tarjetaConsumo.setSaldo(0.0);
        tarjetaConsumoFacadeLocal.create(tarjetaConsumo);
    }

    public void leerUsuario(Usuario leeUsr) {

        usuario = leeUsr;
    }

    public void editarUsuario() {

        usuarioFacadeLocal.edit(usuario);
    }

    public void leerCredenciales(Credenciales leeCredenciales) {

        credenciales = leeCredenciales;
    }

    public void editarCredenciales() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
            if (credenciales.getUserName().equals(usrpass.getIdUsuario().getCedula())) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El Usuario esta en uso, operacion no permitida!"));
            } else {
                credencialesFacadeLocal.edit(credenciales);
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se guardo los cambios correctamente!"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al Editar el Tipo de Usuario, contacte al administrador!"));
        }

    }

    public void guardarUsuarioCredenciales() {
        ArrayList listaBusquedaUsuario = new ArrayList();
        listaBusquedaUsuario.addAll(usuarioFacadeLocal.consultaUsuarios(usuario.getCedula()));

        try {
            if (validaCedula(usuario.getCedula()) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "La cédula ya existe."));
                usuario = new Usuario();
            } else if (listaBusquedaUsuario.isEmpty()) {
                //usuarioFacadeLocal.create(usuario);
                credenciales.setIdUsuario(usuario);
                credenciales.setUserName(usuario.getCedula());
                credencialesFacadeLocal.create(credenciales);

                generaTarjetaConsumoInicial();
                usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se guardó exitosamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Verifique los Datos Ingresados"));
                usuario = new Usuario();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error en el controlador"));
            usuario = new Usuario();
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
