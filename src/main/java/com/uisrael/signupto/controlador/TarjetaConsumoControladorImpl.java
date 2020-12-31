/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.MenuFacadeLocal;
import com.uisrael.signupto.modelo.dao.PagosFacadeLocal;
import com.uisrael.signupto.modelo.dao.TarjetaConsumoFacadeLocal;
import com.uisrael.signupto.modelo.dao.TarjetaConsumoMenuFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Menu;
import com.uisrael.signupto.modelo.entidades.Pagos;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumo;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumoMenu;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumoMenuId;
import com.uisrael.signupto.servicio.MenuServicio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author janrango
 */
@Named
@ViewScoped
public class TarjetaConsumoControladorImpl implements Serializable {

    @EJB
    private TarjetaConsumoFacadeLocal tarjetaConsumoFacadeLocal;

    @EJB
    private TarjetaConsumoMenuFacadeLocal tarjetaConsumoMenuFacadeLocal;

    @EJB
    private PagosFacadeLocal pagosFacadeLocal;

    @EJB
    private MenuFacadeLocal menuFacadeLocal;

    @Inject
    private MenuServicio menuServicio;

    private TarjetaConsumoMenu tarjetaConsumoMenu;

    private TarjetaConsumo tarjetaConsumo;

    private Menu menu;

    private Pagos pagos;

    private double saldoDisponibleCliente;

    private Menu selecccionMenu;

    private int obtieneMenuId;

    private List listaFiltradaHoy;
    
    private Credenciales usrpass;

    @PostConstruct
    public void init() {
        menu = new Menu();
        pagos = new Pagos();
        tarjetaConsumoMenu = new TarjetaConsumoMenu();
        tarjetaConsumo = new TarjetaConsumo();
        FacesContext context = FacesContext.getCurrentInstance();
        usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
        saldoDisponibleCliente = calculoSaldoDisponible();
        listaFiltradaHoy = menuServicio.listaMenuDiario();
        
    }

    public double calculoSaldoDisponible() {

        String temp;
        List<Double> listTempConsumos;
        List<Double> listTempPagos;
        int i;
        double sumaTotalConsumos = 0;
        double sumaTotalPagos = 0;
        double saldoFinal = 0;

        try {

            temp = usrpass.getIdUsuario().getCedula();
            listTempConsumos = tarjetaConsumoFacadeLocal.sumaConsumosCliente(temp);
            listTempPagos = pagosFacadeLocal.sumaPagosCliente(temp);

            for (i = 0; i < listTempConsumos.size(); i++) {
                sumaTotalConsumos += listTempConsumos.get(i);
            }

            for (i = 0; i < listTempPagos.size(); i++) {
                sumaTotalPagos += listTempPagos.get(i);
            }

            saldoFinal = sumaTotalPagos - sumaTotalConsumos;

        } catch (Exception e) {
        }

        return saldoFinal;
    }

    public double capturaValorMenu() {

        BigDecimal valorMenuTemp = BigDecimal.ZERO;
        double valorDoubleMenuTemp = 0.0;
        try {
            valorMenuTemp = menuFacadeLocal.capturaValorMenu(obtieneMenuId);
            valorDoubleMenuTemp = valorMenuTemp.doubleValue();
        } catch (Exception e) {
        }
        return valorDoubleMenuTemp;
    }

    public void guardarConsumoCliente() {

        double valorMenuSeleccionado = capturaValorMenu();
        double valorSaldoNuevoCalculado = calculoSaldoDisponible() - valorMenuSeleccionado;
        Date hoy = new Date();
        selecccionMenu = menuFacadeLocal.capturaObjetoMenuId(obtieneMenuId);
        int idUsuarioTarjetaConsumoTemp;
        idUsuarioTarjetaConsumoTemp = usrpass.getIdUsuario().getIdUsuario();
        tarjetaConsumo = tarjetaConsumoFacadeLocal.idTarjetaConsumoEditar(idUsuarioTarjetaConsumoTemp);
        

        try {
            if (saldoDisponibleCliente < valorMenuSeleccionado) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", " !Saldo insuficiente!"));
            } else if (saldoDisponibleCliente >= valorMenuSeleccionado) {
                tarjetaConsumo.setSaldo(valorSaldoNuevoCalculado);
                tarjetaConsumoFacadeLocal.edit(tarjetaConsumo);
                tarjetaConsumoMenu.setFechaConsumo(hoy);
                tarjetaConsumoMenu.setValorConsumo(valorMenuSeleccionado);
                tarjetaConsumoMenu.setTarjetaConsumo(tarjetaConsumo);
                tarjetaConsumoMenu.setMenu(selecccionMenu);
                TarjetaConsumoMenuId tarjetaConsumoMenuId = new TarjetaConsumoMenuId();
                tarjetaConsumoMenuId.setMenuId(selecccionMenu.getIdMenu());
                tarjetaConsumoMenuId.setTarjetaConsumoId(tarjetaConsumo.getIdTarjetaConsumo());
                tarjetaConsumoMenu.setTarjetaConsumoMenuId(tarjetaConsumoMenuId);
                tarjetaConsumoMenuFacadeLocal.create(tarjetaConsumoMenu);

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Ocrrio un error en el controlador"));

            }
        } catch (Exception e) {
        }
    }

    //GETs & SETs
    public List getListaFiltradaHoy() {
        return listaFiltradaHoy;
    }

    public void setListaFiltradaHoy(List listaFiltradaHoy) {
        this.listaFiltradaHoy = listaFiltradaHoy;
    }

    public Menu getSelecccionMenu() {
        return selecccionMenu;
    }

    public void setSelecccionMenu(Menu selecccionMenu) {
        this.selecccionMenu = selecccionMenu;
    }

    public int getObtieneMenuId() {
        return obtieneMenuId;
    }

    public void setObtieneMenuId(int obtieneMenuId) {
        this.obtieneMenuId = obtieneMenuId;
    }

    public TarjetaConsumo getTarjetaConsumo() {
        return tarjetaConsumo;
    }

    public void setTarjetaConsumo(TarjetaConsumo tarjetaConsumo) {
        this.tarjetaConsumo = tarjetaConsumo;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public double getSaldoDisponibleCliente() {
        return saldoDisponibleCliente;
    }

    public void setSaldoDisponibleCliente(double saldoDisponibleCliente) {
        this.saldoDisponibleCliente = saldoDisponibleCliente;
    }

    public TarjetaConsumoMenu getTarjetaConsumoMenu() {
        return tarjetaConsumoMenu;
    }

    public void setTarjetaConsumoMenu(TarjetaConsumoMenu tarjetaConsumoMenu) {
        this.tarjetaConsumoMenu = tarjetaConsumoMenu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.tarjetaConsumoFacadeLocal);
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
        final TarjetaConsumoControladorImpl other = (TarjetaConsumoControladorImpl) obj;
        if (!Objects.equals(this.tarjetaConsumoFacadeLocal, other.tarjetaConsumoFacadeLocal)) {
            return false;
        }
        return true;
    }

}
