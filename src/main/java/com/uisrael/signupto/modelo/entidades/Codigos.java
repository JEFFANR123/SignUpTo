/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_CODIGOS")
public class Codigos implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IDUSUARIO")
    private Usuario idUsuario;

    @Column(name = "CODIGOGENERADO", nullable = false, length = 20)
    private String codigoGenerado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHAGENERADO", nullable = false, length = 20)
    private Date fechaGenerado;

    @Column(name = "FHVALIDADO", nullable = false, length = 25)
    private String fhValidado;

    public Codigos() {
    }

    public Codigos(Usuario idUsuario, String codigoGenerado, Date fechaGenerado, String fhValidado) {
        this.idUsuario = idUsuario;
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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idUsuario);
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
        final Codigos other = (Codigos) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Codigos{" + "idUsuario=" + idUsuario + '}';
    }



}
