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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_PasswordUser")
public class RecuperarPass implements Serializable{
    
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IDUSUARIO")
    private Usuario idUsuario;
    
    @Column (name = "CI_USUARIO")
    private String ciUsuario;
    
    @Column (name = "USUARIOPASS")
    private String usuarioPass;

    public RecuperarPass() {
    }

    public RecuperarPass(Usuario idUsuario, String ciUsuario, String usuarioPass) {
        this.idUsuario = idUsuario;
        this.ciUsuario = ciUsuario;
        this.usuarioPass = usuarioPass;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getUsuarioPass() {
        return usuarioPass;
    }

    public void setUsuarioPass(String usuarioPass) {
        this.usuarioPass = usuarioPass;
    }


    public String getCiUsuario() {
        return ciUsuario;
    }

    public void setCiUsuario(String ciUsuario) {
        this.ciUsuario = ciUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idUsuario);
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
        final RecuperarPass other = (RecuperarPass) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RecuperarPass{" + "idUsuario=" + idUsuario + '}';
    }

    
}
