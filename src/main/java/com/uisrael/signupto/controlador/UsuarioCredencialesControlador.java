/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CredencialesFacadeLocal;
import com.uisrael.signupto.modelo.dao.EmpresaFacadeLocal;
import com.uisrael.signupto.modelo.dao.PagosFacadeLocal;
import com.uisrael.signupto.modelo.dao.TarjetaConsumoFacadeLocal;
import com.uisrael.signupto.modelo.dao.UsuarioFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Empresa;
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

    @EJB
    private EmpresaFacadeLocal empresaFacadeLocal;

    private Usuario usuario;

    private Empresa empresa;

    private TarjetaConsumo tarjetaConsumo;

    private TarjetaConsumoControladorImpl tarjetaConsumoControladorImpl;

    private Pagos pagos;

    private Credenciales credenciales;

    private List<Credenciales> listaCredenciales;

    private List<Credenciales> listaAdministradores;

    private List<Credenciales> listaEmpleados;

    private List<Credenciales> listaClientes;

    private List<Credenciales> infomacionUsuarios;

    private List<Empresa> lstEmpresas;

    private String passTemp;

    @PostConstruct
    public void init() {
        credenciales = new Credenciales();
        usuario = new Usuario();
        pagos = new Pagos();
        empresa = new Empresa();
        tarjetaConsumo = new TarjetaConsumo();
        lstEmpresas = empresaFacadeLocal.findAll();
        listaCredenciales = credencialesFacadeLocal.findAll();
        listaAdministradores = credencialesFacadeLocal.listaUsuarioCredencialeses("A");
        listaEmpleados = credencialesFacadeLocal.listaUsuarioCredencialeses("E");
        listaClientes = credencialesFacadeLocal.listaUsuarioCredencialeses("C");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
            infomacionUsuarios = credencialesFacadeLocal.consultaUsuarios(usrpass.getIdUsuario().getCedula());
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

    public void editarInformacionUsuario() {

        try {
            usuario = credenciales.getIdUsuario();
            usuarioFacadeLocal.edit(usuario);
            credencialesFacadeLocal.edit(credenciales);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos modificados correctamente"));
            usuario = new Usuario();
            credenciales = new Credenciales();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error, consulte al Administrador"));

        }

    }

    public void editarPasswordUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
        Credenciales credencialesTemp;
        credencialesTemp = credencialesFacadeLocal.consultaUsuarios(usrpass.getIdUsuario().getCedula()).stream().findFirst().orElse(null);

        try {
            if (credencialesTemp == null || !credencialesTemp.getUserPass().equals(passTemp)) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Validacion incorrecta"));
                credenciales = new Credenciales();
                usuario = new Usuario();
            } else if (credencialesTemp.getUserPass().equals(passTemp)) {
                usuario = credenciales.getIdUsuario();
                credencialesFacadeLocal.edit(credenciales);
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Contraseña modificada"));
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Ocurrio un error validando la contraseña"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Ocurrió una excepción, contacte al Administrador"));
        }
    }

    //Agregar - eliminar - modificar empresas
    public void insertarEmpresa() {
        try {
            empresaFacadeLocal.create(empresa);
            lstEmpresas = empresaFacadeLocal.findAll();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Lista actualizada"));
            empresa = new Empresa();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error, consulte al Administrador"));

        }

    }

    public void leerEmpresa(Empresa lEmpresa) {

        empresa = lEmpresa;
    }

    public void editarEmpresa() {
        try {
            empresaFacadeLocal.edit(empresa);
            lstEmpresas = empresaFacadeLocal.findAll();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Lista Actualizada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error, consulte al Administrador"));

        }
    }

    public void eliminarEmpresa(Empresa delEmpresa) {

        try {
            empresaFacadeLocal.remove(delEmpresa);
            lstEmpresas = empresaFacadeLocal.findAll();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Datos eliminados correctamente"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error, consulte al Administrador"));
        }

    }

    // Credenciales - Inserta - Modifica - Lee
    public void editarUsuarioEmpresa() {

        try {
            usuario = credenciales.getIdUsuario();
            usuario.setFkEmpresa(empresa);
            usuarioFacadeLocal.edit(usuario);
            credencialesFacadeLocal.edit(credenciales);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se guardo los cambios correctamente!"));
            usuario = new Usuario();
            credenciales = new Credenciales();
            listaAdministradores = credencialesFacadeLocal.listaUsuarioCredencialeses("A");
            listaEmpleados = credencialesFacadeLocal.listaUsuarioCredencialeses("E");
            listaClientes = credencialesFacadeLocal.listaUsuarioCredencialeses("C");
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error, contacte al administrador"));
        }
    }

    public void darDeBaja() {
        FacesContext context = FacesContext.getCurrentInstance();
        Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
        Credenciales credencialesTemp;
        credencialesTemp = credencialesFacadeLocal.consultaUsuarios(usrpass.getIdUsuario().getCedula()).stream().findFirst().orElse(null);
        byte temp = 0;
        try {
            if (credencialesTemp != null) {
                credenciales = credencialesTemp;
                usuario = credenciales.getIdUsuario();
                credenciales.setEstado(temp);
                credencialesFacadeLocal.edit(credenciales);
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Saliendo..."));
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().redirect("/signupto/index.xhtml");
                credenciales = new Credenciales();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error, contacte al administrador"));
        }
    }

    public void leerCredenciales(Credenciales leeCredenciales) {

        credenciales = leeCredenciales;
    }

    public void editarInformacionAdmins() {
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
        listaBusquedaUsuario.addAll(credencialesFacadeLocal.consultaUsuarios(usuario.getCedula()));

        try {
            if (validaCedula(usuario.getCedula()) == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "La cédula ya existe."));
                usuario = new Usuario();
            } else if (listaBusquedaUsuario.isEmpty()) {
                usuarioFacadeLocal.create(usuario);
                credenciales.setIdUsuario(usuario);
                credenciales.setUserName(usuario.getCedula());
                credencialesFacadeLocal.create(credenciales);

                generaTarjetaConsumoInicial();
                tarjetaConsumo = new TarjetaConsumo();
                credenciales = new Credenciales();
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

    // GETs and SETs
    public String getPassTemp() {
        return passTemp;
    }

    public void setPassTemp(String passTemp) {
        this.passTemp = passTemp;
    }

    public List<Credenciales> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Credenciales> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Empresa> getLstEmpresas() {
        return lstEmpresas;
    }

    public void setLstEmpresas(List<Empresa> lstEmpresas) {
        this.lstEmpresas = lstEmpresas;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public List<Credenciales> getInfomacionUsuarios() {
        return infomacionUsuarios;
    }

    public void setInfomacionUsuarios(List<Credenciales> infomacionUsuarios) {
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
