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
@Table(name = "T_PasswordUser")
public class RecoverPass implements Serializable{
    
    @Id
    private int idPassword;
    
    //Llega desde DatosUsuario FK
    @Column (name = "CI_USUARIO")
    private String ciUsuario;
    
    //Llega desde DatosUsuario FK
    @Column (name = "USUARIOPASS")
    private String usuarioPass;

    public RecoverPass() {
    }

    public RecoverPass(int idPassword, String ciUsuario, String usuarioPass) {
        this.idPassword = idPassword;
        this.ciUsuario = ciUsuario;
        this.usuarioPass = usuarioPass;
    }

    public int getIdPassword() {
        return idPassword;
    }

    public void setIdPassword(int idPassword) {
        this.idPassword = idPassword;
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
        hash = 23 * hash + this.idPassword;
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
        final RecoverPass other = (RecoverPass) obj;
        if (this.idPassword != other.idPassword) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RecoverPass{" + "idPassword=" + idPassword + '}';
    }


    
}
