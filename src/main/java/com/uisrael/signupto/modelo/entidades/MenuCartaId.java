/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 *
 * @author janrango
 */
@Embeddable
public class MenuCartaId implements Serializable {

    
    @Column(name = "MENU_ID")
    private int menuId;

    @Column(name = "CARTA_ID")
    private int cartaId;

    public MenuCartaId() {
    }

    public MenuCartaId(int menuId, int cartaId) {
        this.menuId = menuId;
        this.cartaId = cartaId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCartaId() {
        return cartaId;
    }

    public void setCartaId(int cartaId) {
        this.cartaId = cartaId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.menuId;
        hash = 37 * hash + this.cartaId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuCartaId other = (MenuCartaId) obj;
        if (this.menuId != other.menuId) {
            return false;
        }
        if (this.cartaId != other.cartaId) {
            return false;
        }
        return true;
    }

}
