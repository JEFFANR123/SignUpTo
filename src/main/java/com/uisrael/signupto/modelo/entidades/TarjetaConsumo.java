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
@Table(name = "T_TARJETA_CONSUMOS")
public class TarjetaConsumo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TARJETA_CONSUMO")
    private int idTarjetaConsumo;
    
    @Column(name = "FECHA_EXPEDICION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaExpedicion;
          
    @Column(name = "SALDO")
    private double saldo;
    
    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario fkIdUsuario;
      

    public int getIdTarjetaConsumo() {
        return idTarjetaConsumo;
    }

    public void setIdTarjetaConsumo(int idTarjetaConsumo) {
        this.idTarjetaConsumo = idTarjetaConsumo;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Usuario getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(Usuario fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.idTarjetaConsumo;
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
        final TarjetaConsumo other = (TarjetaConsumo) obj;
        if (this.idTarjetaConsumo != other.idTarjetaConsumo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaConsumo{" + "idTarjetaConsumo=" + idTarjetaConsumo + '}';
    }
    
    
    
}
