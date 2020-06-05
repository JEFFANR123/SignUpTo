/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_USUARIO")
public class Usuario implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private int idUsuario;

    @Column(name = "NOMBRES")
    private String nombres;
    
    @Column(name = "APELLIDOS")
    private String apellidos;
    
    @Column(name = "CEDULA")
    private String cedula;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "TELEFONO")
    private String telefono;
        
    //Mapeado a la tabla Codigo Generado
    @OneToOne
    @JoinColumn(name = "IDCODIGOGENERADO")
    private GeneraCodigo idCodigoGenerado;
    
    //Mapeado con la tabla SaldoUsuario
    @OneToOne
    @JoinColumn(name = "IDSALDOUSUARIO")
    private SaldoUsuario idSaldoUsuario;
    
    @OneToOne
    @JoinColumn(name = "IDRECOVERPASS")
    private RecoverPass idPassword;
    
    //Mapeado con menu, un usuario puede elegir un menu diario.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMENU")
    private Menu menuSeleccionado;

    public Menu getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public Usuario(int idUsuario, String nombres, String apellidos, String cedula, String email, String telefono, GeneraCodigo idCodigoGenerado, SaldoUsuario idSaldoUsuario, RecoverPass idPassword, Menu menuSeleccionado) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.email = email;
        this.telefono = telefono;
        this.idCodigoGenerado = idCodigoGenerado;
        this.idSaldoUsuario = idSaldoUsuario;
        this.idPassword = idPassword;
        this.menuSeleccionado = menuSeleccionado;
    }

    
    public void setMenuSeleccionado(Menu menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
    }

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public GeneraCodigo getIdCodigoGenerado() {
        return idCodigoGenerado;
    }

    public void setIdCodigoGenerado(GeneraCodigo idCodigoGenerado) {
        this.idCodigoGenerado = idCodigoGenerado;
    }

    public SaldoUsuario getIdSaldoUsuario() {
        return idSaldoUsuario;
    }

    public void setIdSaldoUsuario(SaldoUsuario idSaldoUsuario) {
        this.idSaldoUsuario = idSaldoUsuario;
    }

    public RecoverPass getIdPassword() {
        return idPassword;
    }

    public void setIdPassword(RecoverPass idPassword) {
        this.idPassword = idPassword;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + '}';
    }



}
