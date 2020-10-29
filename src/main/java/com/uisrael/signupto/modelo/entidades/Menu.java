/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_MENU")
public class Menu implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMenu;
    
    @Column(name = "NOMBREMENU")
    private String nombreMenu;
    
    @Column(name = "PRECIO")
    private BigDecimal precio;
    
    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<MenuCarta> lstMenuCarta;

    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    
    //GETs & SETs
    
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<MenuCarta> getLstMenuCarta() {
        return lstMenuCarta;
    }

    public void setLstMenuCarta(List<MenuCarta> lstMenuCarta) {
        this.lstMenuCarta = lstMenuCarta;
    }
    
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdMenu() {
        return idMenu;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idMenu;
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
        final Menu other = (Menu) obj;
        if (this.idMenu != other.idMenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + '}';
    }

  
}
