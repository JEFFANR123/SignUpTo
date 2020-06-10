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
import java.text.SimpleDateFormat;
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

    private List<Menu> listaMenu;
    
    private String f_seleccionada = "";
    
    private String accionMenu;

    @PostConstruct
    public void init() {
        menu = new Menu();
        listaMenu = menuFacadeLocal.findAll();
        listaCarta = cartaFacadeLocal.findAll();

    }
    
     public void actualizar_fecha(SelectEvent event) {
        SimpleDateFormat fecha1 = new SimpleDateFormat("EEEEE dd MMMMM yyyy");
        StringBuilder cadena_fecha1_11 = new StringBuilder(fecha1.format(event.getObject()));
        f_seleccionada = cadena_fecha1_11.toString();
        
        
    }
     
    public void eliminarMenu(Menu delMenu){
        try {
            menuFacadeLocal.remove(delMenu);
            listaMenu = menuFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Opcion de Menu eliminado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Ocurrio un error al eliminar"));
        }
        
    }
    
    public void leerMenu(Menu lMenu){
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
    
    public void listarMenu(){
        try {
            listaMenu = menuFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Listado de menu cargado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","Ocrrio un error al cargar los menus"));
        }
    }

    public void insertarMenu() {

        try {
            menuFacadeLocal.create(menu);
            listaMenu = menuFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Agregado correctamente."));
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

    
}
