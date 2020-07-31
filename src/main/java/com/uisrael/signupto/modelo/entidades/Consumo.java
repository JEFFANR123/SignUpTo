/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_CONSUMO")
public class Consumo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idConsumo;
    
    @Column(name = "FECHACONSUMO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaConsumo;
    
    @Column(name = "COSTO")
    private double valorConsumo;
   
    @ManyToOne
    @JoinColumn(name = "FK_IDTARJETACONSUMO")
    private TarjetaConsumo fkIdTarjetaConsumo;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDMENU")
    private Menu fkIdMenu;

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public double getValorConsumo() {
        return valorConsumo;
    }

    public void setValorConsumo(double valorConsumo) {
        this.valorConsumo = valorConsumo;
    }

    public TarjetaConsumo getFkIdTarjetaConsumo() {
        return fkIdTarjetaConsumo;
    }

    public void setFkIdTarjetaConsumo(TarjetaConsumo fkIdTarjetaConsumo) {
        this.fkIdTarjetaConsumo = fkIdTarjetaConsumo;
    }

    public Menu getFkIdMenu() {
        return fkIdMenu;
    }

    public void setFkIdMenu(Menu fkIdMenu) {
        this.fkIdMenu = fkIdMenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idConsumo;
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
        final Consumo other = (Consumo) obj;
        if (this.idConsumo != other.idConsumo) {
            return false;
        }
        return true;
    }
    
    
    
}
