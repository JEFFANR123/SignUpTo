/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author janrango
 */
@Entity(name = "T_MENUCARTA")
public class MenuCarta implements Serializable {

    @EmbeddedId MenuCartaId menuCartaId;
        
    @ManyToOne
    @JoinColumn(name = "IDCARTA", updatable = false, insertable = false)
    private Carta carta;

    @ManyToOne
    @JoinColumn(name = "IDMENU", insertable = false, updatable = false)
    private Menu menu;

    public MenuCartaId getMenuCartaId() {
        return menuCartaId;
    }

    public void setMenuCartaId(MenuCartaId menuCartaId) {
        this.menuCartaId = menuCartaId;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.menuCartaId);
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
        final MenuCarta other = (MenuCarta) obj;
        if (!Objects.equals(this.menuCartaId, other.menuCartaId)) {
            return false;
        }
        return true;
    }



}
