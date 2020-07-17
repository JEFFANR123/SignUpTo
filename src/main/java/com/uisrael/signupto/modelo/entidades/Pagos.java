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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author janrango
 */
@Entity
@Table(name = "T_PAGOS")
public class Pagos implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;
    
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    
    @Column(name = "VALOR_PAGO")
    private double valorPago;
      
    @Lob
    @Column(name = "COMPROBANTE_PAGO")
    private byte [] comprobantePago;
    
    @Column(name = "COMENTARIO")
    private String comentario;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "FK_IDUSUARIO")
    private Usuario fkIdUsuario;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(Usuario fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    public byte[] getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(byte[] comprobantePago) {
        this.comprobantePago = comprobantePago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idPago;
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
        final Pagos other = (Pagos) obj;
        if (this.idPago != other.idPago) {
            return false;
        }
        return true;
    }
    
    
}
