/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.modelo.entidades;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author janrango
 */
public class ReportSemanalConsumo {
    
    private Date dia;
    
    private BigDecimal sumatoriaVentas;

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public BigDecimal getSumatoriaVentas() {
        return sumatoriaVentas;
    }

    public void setSumatoriaVentas(BigDecimal sumatoriaVentas) {
        this.sumatoriaVentas = sumatoriaVentas;
    }
    
    
    
}
