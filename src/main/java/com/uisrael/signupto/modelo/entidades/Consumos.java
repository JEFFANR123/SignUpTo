/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_CONSUMOS")
public class Consumos implements Serializable{
    
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE)
 private int idConsumo;
 
 @OneToOne(cascade = CascadeType.PERSIST)
 @JoinColumn(name = "IDUSUARIO")
 private Usuario idUsuario;
 
 @OneToOne(cascade = CascadeType.PERSIST)
 @JoinColumn(name = "IDMENU")
 private Menu idMenu;
 
 private Date fechaSeleccionDate;

    public Consumos() {
    }

    public Consumos(int idConsumo, Usuario idUsuario, Menu idMenu, Date fechaSeleccionDate) {
        this.idConsumo = idConsumo;
        this.idUsuario = idUsuario;
        this.idMenu = idMenu;
        this.fechaSeleccionDate = fechaSeleccionDate;
    }

    public Date getFechaSeleccionDate() {
        return fechaSeleccionDate;
    }

    public void setFechaSeleccionDate(Date fechaSeleccionDate) {
        this.fechaSeleccionDate = fechaSeleccionDate;
    }

 
    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idConsumo;
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
        final Consumos other = (Consumos) obj;
        if (this.idConsumo != other.idConsumo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consumos{" + "idConsumo=" + idConsumo + ", idUsuario=" + idUsuario + ", idMenu=" + idMenu + '}';
    }
    
 
}
