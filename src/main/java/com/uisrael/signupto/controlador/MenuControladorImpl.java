/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.CartaFacadeLocal;
import com.uisrael.signupto.modelo.dao.MenuCartaFacadeLocal;
import com.uisrael.signupto.modelo.dao.MenuFacadeLocal;
import com.uisrael.signupto.modelo.dao.TipoCartaFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Carta;
import com.uisrael.signupto.modelo.entidades.Menu;
import com.uisrael.signupto.modelo.entidades.MenuCarta;
import com.uisrael.signupto.modelo.entidades.MenuCartaId;
import com.uisrael.signupto.modelo.entidades.TipoCarta;
import com.uisrael.signupto.servicio.MenuServicio;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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

    @EJB
    private MenuCartaFacadeLocal menuCartaFacadeLocal;

    @EJB
    private TipoCartaFacadeLocal tipoCartaFacadeLocal;

    @Inject
    private MenuServicio menuServicio;

    private List<Carta> listaCarta;

    private Menu menu;

    private String f_seleccionada = "";

    private String accionMenu;

    private String opcMenu;

    private List<Menu> listaMenu;

    private List<Menu> listaFiltrada;

    private List<Menu> listaMenuDiario;

    private List<Carta> listaOpcionesCartas;

    private List<Integer> selecciones;

    private List<TipoCarta> listaTipo;

    private List<MenuCarta> lstMenuCartas;

    private int lstTipoId;

    @PostConstruct
    public void init() {
        menu = new Menu();
        listaMenu = menuFacadeLocal.findAll();
        listaCarta = cartaFacadeLocal.findAll();
        listaTipo = tipoCartaFacadeLocal.findAll();
        lstMenuCartas = new ArrayList<>();
        selecciones = new ArrayList<>();

        if (!listaTipo.isEmpty()) {
            lstTipoId = listaTipo.get(0).getIdTipoCarta();
            cargarItemsRelacionados();
        }
        listarOM();
        listarMenuDiario();
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

    public void cargarItemsRelacionados() {
        listaOpcionesCartas = cartaFacadeLocal.listaCarta(lstTipoId);
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
            listaFiltrada = menuServicio.listaMenuDiario();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Listado disponibles."));
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Error al cargar los disponibles."));

        }
    }

    public void listarMenuDiario() {
        Date hoy = new Date();
        try {
            listaMenuDiario = menuFacadeLocal.listadoFiltrado(hoy, DiasFecha(hoy, 1));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Listado disponibles."));

        } catch (Exception e) {
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
                //menu.setLstMenuCarta(lstMenuCartas);
                menuFacadeLocal.create(menu);
                lstMenuCartas.forEach((mc) -> {
                    mc.getMenuCartaId().setMenuId(menu.getIdMenu());
                });
                lstMenuCartas.forEach((mc) -> {
                    menuCartaFacadeLocal.create(mc);
                });
                listarOM();
                listaMenu = menuFacadeLocal.findAll();
                menu = new Menu();
                lstMenuCartas.clear();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Agregado correctamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Fecha incorrecta"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Ocurrio un error"));
        }

    }

    public void agregarSeleccion() {
        for (int i = 0; i < selecciones.size(); i++) {
            final int id = selecciones.get(i);
            MenuCarta mc = new MenuCarta();
            Optional<Carta> optCartaOptional = listaOpcionesCartas.stream().filter(x -> x.getIdCarta() == id).findFirst();
            if (optCartaOptional.isPresent()) {
                mc.setCarta(optCartaOptional.get());
                mc.setMenu(menu);
                MenuCartaId menuCartaId = new MenuCartaId();
                menuCartaId.setCartaId(mc.getCarta().getIdCarta());
                menuCartaId.setMenuId(mc.getMenu().getIdMenu());
                mc.setMenuCartaId(menuCartaId);
                lstMenuCartas.add(mc);
            }
        }
        selecciones.clear();
    }

    public int[] ordenarArreglos(int[] arreglo) {
        int i, j, k;
        if (arreglo.length > 1) {
            int nElmsIzq = arreglo.length / 2;
            int nElmsDer = arreglo.length - nElmsIzq;
            int arrayIzq[] = new int[nElmsIzq];
            int arrayDer[] = new int[nElmsDer];

            //Arreglo Izquierda
            for (i = 0; i < nElmsIzq; i++) {
                arrayIzq[i] = arreglo[i];
            }
            //Arreglo Derecha
            for (i = nElmsIzq; i < nElmsIzq + nElmsDer; i++) {
                arrayDer[i = nElmsIzq] = arreglo[i];
            }
            arrayIzq = ordenarArreglos(arrayIzq);
            arrayDer = ordenarArreglos(arrayDer);
            i = 0;
            j = 0;
            k = 0;
            while (arrayIzq.length != j && arrayDer.length != k) {
                if (arrayIzq[j] < arrayDer[k]) {
                    arreglo[i] = arrayIzq[j];
                    i++;
                    j++;
                } else {
                    arreglo[i] = arrayDer[j];
                    i++;
                    k++;
                }
            }
            while (arrayIzq.length != j) {
                arreglo[i] = arrayIzq[j];
                i++;
                j++;
            }
            while (arrayDer.length != k) {
                arreglo[i] = arrayDer[k];
                i++;
                k++;
            }
        }
        return arreglo;
    }

    public void eliminaMenu(int idMenu) {

        Menu tempMenuCarta = menuFacadeLocal.find(idMenu);
        try {
            for (MenuCarta mc : tempMenuCarta.getLstMenuCarta()) {
                menuCartaFacadeLocal.remove(mc);
            }
            menuFacadeLocal.remove(tempMenuCarta);
            listarOM();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Eliminado Correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Ocurrio un error al eliminar."));
        }
    }

    //GETs and SETs
    public int getLstTipoId() {
        return lstTipoId;
    }

    public void setLstTipoId(int lstTipoId) {
        this.lstTipoId = lstTipoId;
    }

    public List<TipoCarta> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<TipoCarta> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public List<Menu> getListaMenuDiario() {
        return listaMenuDiario;
    }

    public void setListaMenuDiario(List<Menu> listaMenuDiario) {
        this.listaMenuDiario = listaMenuDiario;
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

    public List<Carta> getListaOpcionesCartas() {
        return listaOpcionesCartas;
    }

    public void setListaOpcionesCartas(List<Carta> listaOpcionesCartas) {
        this.listaOpcionesCartas = listaOpcionesCartas;
    }

    public List<Integer> getSelecciones() {
        return selecciones;
    }

    public void setSelecciones(List<Integer> selecciones) {
        this.selecciones = selecciones;
    }

    public List<MenuCarta> getLstMenuCartas() {
        return lstMenuCartas;
    }

    public void setLstMenuCartas(List<MenuCarta> lstMenuCartas) {
        this.lstMenuCartas = lstMenuCartas;
    }

}
