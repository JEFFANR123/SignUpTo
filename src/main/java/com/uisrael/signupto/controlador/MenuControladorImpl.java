/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CartaFacadeLocal;
import com.uisrael.signupto.modelo.dao.MenuFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Carta;
import com.uisrael.signupto.modelo.entidades.Menu;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author janrango
 */
@Named
@ViewScoped
public class MenuControladorImpl implements Serializable {

    @EJB
    private MenuFacadeLocal menuFacadeLocal;

    @EJB
    private CartaFacadeLocal cartaFacadeLocal;

    private List<Carta> listaCarta;

    private Menu menu;

    private String f_seleccionada = "";

    private String accionMenu;

    private String opcMenu;

    private List<Menu> listaMenu;

    private List<Menu> listaFiltrada;

    private List<Carta> listaEntradas;

    private List<Carta> listaSopas;

    private List<Carta> listaSegundos;

    private List<Carta> listaBebidas;

    private List<Carta> listaPostres;

    private List<Carta> listaOtros;

    @PostConstruct
    public void init() {
        menu = new Menu();
        listaMenu = menuFacadeLocal.findAll();
        listaCarta = cartaFacadeLocal.findAll();
        listaEntradas = cartaFacadeLocal.listaCarta("Entrada");
        listaSopas = cartaFacadeLocal.listaCarta("Sopa");
        listaSegundos = cartaFacadeLocal.listaCarta("Segundo");
        listaBebidas = cartaFacadeLocal.listaCarta("Bebida");
        listaPostres = cartaFacadeLocal.listaCarta("Postre");
        listaOtros = cartaFacadeLocal.listaCarta("Otro");
        listarOM();

    }

    public void actualizar_fecha(SelectEvent event) {
        SimpleDateFormat fecha1 = new SimpleDateFormat("EEEEE dd MMMMM yyyy");
        StringBuilder cadena_fecha1_11 = new StringBuilder(fecha1.format(event.getObject()));
        f_seleccionada = cadena_fecha1_11.toString();
    }

    public void eliminarMenu(Menu delMenu) {
        try {
            menuFacadeLocal.remove(delMenu);
            listaMenu = menuFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Opcion de Menu eliminado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Ocurrio un error al eliminar"));
        }

    }

    public void leerMenu(Menu lMenu) {
        menu = lMenu;
        this.setAccionMenu("M");
    }
    

    public void modificarMenu() {
        try {
            menuFacadeLocal.edit(menu);
            listaMenu = menuFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Modificado exitosamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al modificar"));
        }

    }

    public void listarOM() {
        Date hoy = new Date();
        try {
            listaFiltrada = menuFacadeLocal.listadoFiltrado(hoy, DiasFecha(hoy, 5));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Listado disponibles."));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al cargar los disponibles."));

        }
    }

    public Date DiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public void insertarMenu() {
        Date hoy = new Date();

        try {
            if (menu.getFecha().before(DiasFecha(hoy, -1))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Fecha incorrecta"));
            } else if (menu.getFecha().before(DiasFecha(hoy, 5)) || menu.getFecha().equals(hoy)) {
                menuFacadeLocal.create(menu);
                listaMenu = menuFacadeLocal.findAll();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Agregado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Fecha incorrecta"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Ocurrio un error"));
        }

    }

    public String getAccionMenu() {
        return accionMenu;
    }

    public void setAccionMenu(String accionMenu) {
        this.accionMenu = accionMenu;
    }

    public String getF_seleccionada() {
        return f_seleccionada;
    }

    public void setF_seleccionada(String f_seleccionada) {
        this.f_seleccionada = f_seleccionada;
    }

    public MenuFacadeLocal getMenuFacadeLocal() {
        return menuFacadeLocal;
    }

    public void setMenuFacadeLocal(MenuFacadeLocal menuFacadeLocal) {
        this.menuFacadeLocal = menuFacadeLocal;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public List<Carta> getListaCarta() {
        return listaCarta;
    }

    public void setListaCarta(List<Carta> listaCarta) {
        this.listaCarta = listaCarta;
    }

    public List<Menu> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Menu> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public String getOpcMenu() {
        return opcMenu;
    }

    public void setOpcMenu(String opcMenu) {
        this.opcMenu = opcMenu;
    }

    public List<Carta> getListaEntradas() {
        return listaEntradas;
    }

    public void setListaEntradas(List<Carta> listaEntradas) {
        this.listaEntradas = listaEntradas;
    }

    public List<Carta> getListaSopas() {
        return listaSopas;
    }

    public void setListaSopas(List<Carta> listaSopas) {
        this.listaSopas = listaSopas;
    }

    public List<Carta> getListaSegundos() {
        return listaSegundos;
    }

    public void setListaSegundos(List<Carta> listaSegundos) {
        this.listaSegundos = listaSegundos;
    }

    public List<Carta> getListaBebidas() {
        return listaBebidas;
    }

    public void setListaBebidas(List<Carta> listaBebidas) {
        this.listaBebidas = listaBebidas;
    }

    public List<Carta> getListaPostres() {
        return listaPostres;
    }

    public void setListaPostres(List<Carta> listaPostres) {
        this.listaPostres = listaPostres;
    }

    public List<Carta> getListaOtros() {
        return listaOtros;
    }

    public void setListaOtros(List<Carta> listaOtros) {
        this.listaOtros = listaOtros;
    }

}
