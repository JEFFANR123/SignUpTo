/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.MenuAccessFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.MenuAccess;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author janrango
 */
@Named
@SessionScoped
public class MenuAccessControladorImpl implements Serializable {

    @EJB
    MenuAccessFacadeLocal menuAccessFacadeLocal;

    private List<MenuAccess> listaMenu;

    private List<MenuAccess> menuOpts;

    private MenuAccess menu;

    private MenuModel model;

    @PostConstruct
    public void init() {
        //this.listarMenu();
        listaMenu = menuAccessFacadeLocal.findAll();
        model = new DefaultMenuModel();
        this.permisosMenu();
        menuOpts = menuAccessFacadeLocal.findAll();
        menu = new MenuAccess();
        
    }

    public void listarMenu() {
        try {
            menuOpts = menuAccessFacadeLocal.findAll();
        } catch (Exception e) {
        }

    }

    public void guardarMenu() {
        try {
            menuAccessFacadeLocal.create(menu);
            menuOpts = menuAccessFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Navegacion modificada exitosamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al guardar la navegacion."));
        }

    }
    
    public void eliminarMenu(MenuAccess delMenu){
     try {
            menuAccessFacadeLocal.remove(delMenu);
            menuOpts = menuAccessFacadeLocal.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Navegacion modificada exitosamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al guardar la navegacion."));
        }
    }

    public void cerrarSesion() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public void permisosMenu() {

        Credenciales usrpass = (Credenciales) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");

        for (MenuAccess menuAccess : listaMenu) {
            if (menuAccess.getTipoMenu().equals("S") && menuAccess.getTipoUsuario().equals(usrpass.getTipoUser())) {
                DefaultSubMenu primerMenu = new DefaultSubMenu(menuAccess.getNombre());
                for (MenuAccess menuAccess1 : listaMenu) {
                    MenuAccess subMenuAccess = menuAccess1.getFkMenuAccess();
                    if (subMenuAccess != null) {
                        if (subMenuAccess.getIdMenuAccess() == menuAccess.getIdMenuAccess()) {
                            DefaultMenuItem item = new DefaultMenuItem(menuAccess1.getNombre());
                            item.setUrl(menuAccess1.getUrlMenu());
                            primerMenu.addElement(item);
                        }
                    }
                }
                model.getElements().add(primerMenu);
            } else {
                if (menuAccess.getFkMenuAccess() == null && menuAccess.getTipoUsuario().equals(usrpass.getTipoUser())) {
                    DefaultMenuItem item = new DefaultMenuItem(menuAccess.getNombre());
                    item.setUrl(menuAccess.getUrlMenu());
                    model.addElement(item);
                }
            }
        }

    }

    public MenuAccess getMenu() {
        return menu;
    }

    public void setMenu(MenuAccess menu) {
        this.menu = menu;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public List<MenuAccess> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<MenuAccess> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public List<MenuAccess> getMenuOpts() {
        return menuOpts;
    }

    public void setMenuOpts(List<MenuAccess> menuOpts) {
        this.menuOpts = menuOpts;
    }

}
