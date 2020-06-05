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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_SALDOUSUARIO")
public class SaldoUsuario implements Serializable{
    
    @Id
    private int idSaldoUsuario;
    
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

    public int getIdSaldoUsuario() {
        return idSaldoUsuario;
    }

    public void setIdSaldoUsuario(int idSaldoUsuario) {
        this.idSaldoUsuario = idSaldoUsuario;
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
        hash = 37 * hash + this.idSaldoUsuario;
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
        final SaldoUsuario other = (SaldoUsuario) obj;
        if (this.idSaldoUsuario != other.idSaldoUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SaldoUsuario{" + "idSaldoUsuario=" + idSaldoUsuario + '}';
    }
    
    
}
