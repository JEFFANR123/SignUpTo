/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
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
@Table(name = "T_TARJETACONSUMO_MENU")
public class TarjetaConsumoMenu implements Serializable {

    @EmbeddedId TarjetaConsumoMenuId tarjetaConsumoMenuId;

    @ManyToOne
    @JoinColumn(name = "TARJETACONSUMOID", updatable = false, insertable = false)
    private TarjetaConsumo tarjetaConsumo;

    @ManyToOne
    @JoinColumn(name = "MENUID", updatable = false, insertable = false)
    private Menu menu;

    @Column(name = "FECHA_CONSUMO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaConsumo;

    @Column(name = "VALOR_CONSUMO")
    private double valorConsumo;

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

    public TarjetaConsumoMenuId getTarjetaConsumoMenuId() {
        return tarjetaConsumoMenuId;
    }

    public void setTarjetaConsumoMenuId(TarjetaConsumoMenuId tarjetaConsumoMenuId) {
        this.tarjetaConsumoMenuId = tarjetaConsumoMenuId;
    }

    public TarjetaConsumo getTarjetaConsumo() {
        return tarjetaConsumo;
    }

    public void setTarjetaConsumo(TarjetaConsumo tarjetaConsumo) {
        this.tarjetaConsumo = tarjetaConsumo;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.tarjetaConsumoMenuId);
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
        final TarjetaConsumoMenu other = (TarjetaConsumoMenu) obj;
        if (!Objects.equals(this.tarjetaConsumoMenuId, other.tarjetaConsumoMenuId)) {
            return false;
        }
        return true;
    }

}
