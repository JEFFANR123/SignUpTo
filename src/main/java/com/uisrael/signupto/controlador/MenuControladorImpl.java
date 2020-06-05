/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.MenuFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Menu;
import java.io.Serializable;
import java.util.ArrayList;
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
public class MenuControladorImpl implements Serializable {

    @EJB
    private MenuFacadeLocal menuFacadeLocal;

    private Menu menu;

    private List<Menu> listaMenu;

    @PostConstruct
    public void init() {
        menu = new Menu();
        listaMenu = new ArrayList<>();

    }
    

    public void insertarMenu() {

        try {
            menuFacadeLocal.create(menu);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Agregado correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Ocurrio un error"));
        }

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

    
}
