/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 *
 * @author janrango
 */
@Embeddable
public class TarjetaConsumoMenuId implements Serializable {
       
    @Column(name = "TARJETACONSUMO_ID")
    private int tarjetaConsumoId;

    @Column(name = "MENU_ID")
    private int menuId;

    public int getTarjetaConsumoId() {
        return tarjetaConsumoId;
    }

    public void setTarjetaConsumoId(int tarjetaConsumoId) {
        this.tarjetaConsumoId = tarjetaConsumoId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.tarjetaConsumoId;
        hash = 71 * hash + this.menuId;
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
        final TarjetaConsumoMenuId other = (TarjetaConsumoMenuId) obj;
        if (this.tarjetaConsumoId != other.tarjetaConsumoId) {
            return false;
        }
        if (this.menuId != other.menuId) {
            return false;
        }
        return true;
    }
    
    
    
}
