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
@Table(name = "T_TARJETA_CONSUMO")
public class TarjetaConsumo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTarjetaConsumo;
    
    @Column(name = "FECHA_EXPEDICION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaExpedicion;
    
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCaducidad;
    
    @Column(name = "PAGADO")
    private String pagado;
    
    @Column(name = "TOTAL_ALMUEZOS")
    private int totalAlmuerzos;
    
    @Column(name = "COSTOS")
    private double costos;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDUSUARIO")
    private Usuario fkIdUsuario;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDEMPRESA")
    private Empresa fkIdEmpresa;
    

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

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public int getTotalAlmuerzos() {
        return totalAlmuerzos;
    }

    public void setTotalAlmuerzos(int totalAlmuerzos) {
        this.totalAlmuerzos = totalAlmuerzos;
    }

    public double getCostos() {
        return costos;
    }

    public void setCostos(double costos) {
        this.costos = costos;
    }

    public Usuario getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(Usuario fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    public Empresa getFkIdEmpresa() {
        return fkIdEmpresa;
    }

    public void setFkIdEmpresa(Empresa fkIdEmpresa) {
        this.fkIdEmpresa = fkIdEmpresa;
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
