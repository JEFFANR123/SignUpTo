/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_MENUACCESS")
public class MenuAccess implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMenuAccess;
    
    @Column(name = "NOMBRE") 
    private String nombre;
    
    @Column(name = "TIPOMENU")
    private String tipoMenu;
    
    @Column(name = "URLMENU")
    private String urlMenu;
    
    @Column(name = "TIPOUSUARIO")
    private String tipoUsuario;
    
    @ManyToOne
    @JoinColumn(name = "MenuAccess")
    private MenuAccess fkMenuAccess;
    
    @Column(name = "ESTADO")
    private boolean estado=true;

    public int getIdMenuAccess() {
        return idMenuAccess;
    }

    public void setIdMenuAccess(int idMenuAccess) {
        this.idMenuAccess = idMenuAccess;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

  
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }


    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public MenuAccess getFkMenuAccess() {
        return fkMenuAccess;
    }

    public void setFkMenuAccess(MenuAccess fkMenuAccess) {
        this.fkMenuAccess = fkMenuAccess;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
