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
@Table(name = "T_TIPO_CARTAS")
public class TipoCarta implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TIPO_CARTA")
    private int idTipoCarta;

    @Column(name = "NOMBRE_TIPO")
    private String nombreTipo;

    @Column(name = "ESTADO")
    private boolean estado;

    public int getIdTipoCarta() {
        return idTipoCarta;
    }

    public void setIdTipoCarta(int idTipoCarta) {
        this.idTipoCarta = idTipoCarta;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idTipoCarta;
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
        final TipoCarta other = (TipoCarta) obj;
        if (this.idTipoCarta != other.idTipoCarta) {
            return false;
        }
        return true;
    }
    
    

}
