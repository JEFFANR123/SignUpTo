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
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_HISTORYCONSUMO")
public class HistoriaConsumo implements Serializable {

    @Id
    private int idHistory;
    
    @Column(name = "MENUSELECCIONADO")
    private String menuSeleccionado;
    
    @Column(name = "DATOSCONSUMO")
    private String datosConsumo;

    public HistoriaConsumo() {
    }

    public HistoriaConsumo(int idHistory, String menuSeleccionado, String datosConsumo) {
        this.idHistory = idHistory;
        this.menuSeleccionado = menuSeleccionado;
        this.datosConsumo = datosConsumo;
    }

    public int getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public String getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(String menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
    }

    public String getDatosConsumo() {
        return datosConsumo;
    }

    public void setDatosConsumo(String datosConsumo) {
        this.datosConsumo = datosConsumo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idHistory;
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
        final HistoriaConsumo other = (HistoriaConsumo) obj;
        if (this.idHistory != other.idHistory) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoryConsumo{" + "idHistory=" + idHistory + '}';
    }


}
