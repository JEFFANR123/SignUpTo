/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_CARTA")
public class Carta implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idCarta;
    
    @ManyToOne
    @JoinColumn(name = "IDTIPOCARTA")
    private TipoCarta tipoCarta;
    
    @Column(name = "NOMBRE")
    private String nombre;

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(TipoCarta tipoCarta) {
        this.tipoCarta = tipoCarta;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idCarta;
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
        final Carta other = (Carta) obj;
        if (this.idCarta != other.idCarta) {
            return false;
        }
        return true;
    }
    

    
}
