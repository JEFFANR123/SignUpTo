/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    @Column(name = "ENTRADA")
    private String entrada;
    
    @Column(name = "SOPA")
    private String sopa;
    
    @Column(name = "SEGUNDO")
    private String segundo;
    
    @Column(name = "BEBIDA")
    private String jugo;
    
    @Column(name = "POSTRE")
    private String postre;
    
    @Column(name = "COSTO")
    private double costo;
    
    @Column(name="STOCK")
    private int stock;
    
    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    public Menu() {
    }

    public Menu(int idMenu, String entrada, String sopa, String segundo, String jugo, String postre, int stock, Date fecha) {
        this.idMenu = idMenu;
        this.entrada = entrada;
        this.sopa = sopa;
        this.segundo = segundo;
        this.jugo = jugo;
        this.postre = postre;
        this.stock = stock;
        this.fecha = fecha;
    }



    public int getIdMenu() {
        return idMenu;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSopa() {
        return sopa;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getJugo() {
        return jugo;
    }

    public void setJugo(String jugo) {
        this.jugo = jugo;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
