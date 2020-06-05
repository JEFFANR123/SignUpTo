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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_GENERACODIGO")
public class GeneraCodigo implements Serializable {

    @Id
    private int idCodigoGenerado;

    @Column(name = "CODIGOGENERADO", nullable = false, length = 20)
    private String codigoGenerado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHAGENERADO", nullable = false, length = 20)
    private Date fechaGenerado;

    @Column(name = "FHVALIDADO", nullable = false, length = 25)
    private String fhValidado;

    public GeneraCodigo() {
    }

    public GeneraCodigo(int idCodigoGenerado, String codigoGenerado, Date fechaGenerado, String fhValidado) {
        this.idCodigoGenerado = idCodigoGenerado;
        this.codigoGenerado = codigoGenerado;
        this.fechaGenerado = fechaGenerado;
        this.fhValidado = fhValidado;
    }

    public Date getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(Date fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }



    public int getIdCodigoGenerado() {
        return idCodigoGenerado;
    }

    public void setIdCodigoGenerado(int idCodigoGenerado) {
        this.idCodigoGenerado = idCodigoGenerado;
    }

    public String getCodigoGenerado() {
        return codigoGenerado;
    }

    public void setCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
    }


    public String getFhValidado() {
        return fhValidado;
    }

    public void setFhValidado(String fhValidado) {
        this.fhValidado = fhValidado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idCodigoGenerado;
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
        final GeneraCodigo other = (GeneraCodigo) obj;
        if (this.idCodigoGenerado != other.idCodigoGenerado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneraCodigo{" + "idCodigoGenerado=" + idCodigoGenerado + '}';
    }


}
