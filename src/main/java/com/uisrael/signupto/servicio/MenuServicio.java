/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.servicio;

import com.uisrael.signupto.modelo.dao.MenuFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Menu;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author janrango
 */
@LocalBean
@Stateless
public class MenuServicio {

    @Inject
    private MenuFacadeLocal menuFacadeLocal;

    public List<Menu> listaMenuDiario() {
        Date hoy = new Date();
        return menuFacadeLocal.listadoFiltrado(hoy, DiasFecha(hoy, 5));

    }

    public Date DiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

}
