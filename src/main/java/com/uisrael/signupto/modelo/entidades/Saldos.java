/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_SALDOS")
public class Saldos implements Serializable{
    
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IDUSUARIO")
    private Usuario idUsuario;
    
    @Column(name = "SALDOACTUAL")
    private double saldoActual;
    
    @Column(name = "SALDOANTERIOR")
    private double saldoAnterior;
    
    @Column(name = "PAGOREALIZADO")
    private double pagoRealizado;
    
    @Column(name = "IMGPAGO")
    private String imgPago;
    
    @Column(name = "PAGOAPROBADO")
    private double pagoAprobado;

    public Saldos() {
    }
    

    public Saldos(Usuario idUsuario, double saldoActual, double saldoAnterior, double pagoRealizado, String imgPago, double pagoAprobado) {
        this.idUsuario = idUsuario;
        this.saldoActual = saldoActual;
        this.saldoAnterior = saldoAnterior;
        this.pagoRealizado = pagoRealizado;
        this.imgPago = imgPago;
        this.pagoAprobado = pagoAprobado;
    }
    

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public double getPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(double pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public String getImgPago() {
        return imgPago;
    }

    public void setImgPago(String imgPago) {
        this.imgPago = imgPago;
    }

    public double getPagoAprobado() {
        return pagoAprobado;
    }

    public void setPagoAprobado(double pagoAprobado) {
        this.pagoAprobado = pagoAprobado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idUsuario);
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
        final Saldos other = (Saldos) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Saldos{" + "idUsuario=" + idUsuario + '}';
    }


    
    
}
