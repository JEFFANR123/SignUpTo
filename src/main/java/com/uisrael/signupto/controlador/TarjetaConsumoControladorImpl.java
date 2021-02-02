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
import com.uisrael.signupto.modelo.entidades.ReportSemanalConsumo;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumo;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumoMenu;
import com.uisrael.signupto.modelo.entidades.TarjetaConsumoMenuId;
import com.uisrael.signupto.servicio.MenuServicio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.Query;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

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

    private List<TarjetaConsumoMenu> tarjetaConsumoMenusList;

    private List<TarjetaConsumoMenu> lstCodigoConsumo;

    private List<Object[]> lstReportSemanalConsumos;

    private Menu menu;

    private Pagos pagos;

    private double saldoDisponibleCliente;

    private Menu selecccionMenu;

    private int obtieneMenuId;

    private List listaFiltradaHoy;

    private Credenciales usrpass;

    private BarChartModel barModel;

    String temp;

    @PostConstruct
    public void init() {
        menu = new Menu();
        pagos = new Pagos();
        tarjetaConsumoMenu = new TarjetaConsumoMenu();
        tarjetaConsumo = new TarjetaConsumo();
        cargarReporte();
        FacesContext context = FacesContext.getCurrentInstance();
        usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
        saldoDisponibleCliente = calculoSaldoDisponible();
        listaFiltradaHoy = menuServicio.listaMenuDiario();
        temp = usrpass.getIdUsuario().getCedula();
        tarjetaConsumoMenusList = tarjetaConsumoMenuFacadeLocal.consultaTarjetaConsumoMenu(temp);

    }

    public void cargarReporte() {

        lstReportSemanalConsumos = tarjetaConsumoMenuFacadeLocal.consultaListConsumoSemanal();
        dashboardLoad();

    }

    public void dashboardLoad() {

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Packages");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        //Query q = deliveryService.getEm().createQuery("SELECT x.customer.alias, COUNT(x.id) FROM IprmPackage x GROUP BY x.customer.alias");
        //List<Object[]> results = q.getResultList();

        for (Object[] rowResult : lstReportSemanalConsumos) {
            labels.add(String.valueOf(rowResult[0]));
            values.add(((Number) (rowResult[1])).intValue());
            System.out.println("Valor 0:" + rowResult[0]);
            System.out.println("Valor 1:" + rowResult[1]);
        }

        barDataSet.setData(values);

        data.setLabels(labels);
        barModel.setData(data);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Packages");
        //options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }

    public double calculoSaldoDisponible() {

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

    public static int generarCodigo() {
        return (int) (100000 * Math.random());
    }

    public void guardarConsumoCliente() {

        double valorMenuSeleccionado = capturaValorMenu();
        double valorSaldoNuevoCalculado = calculoSaldoDisponible() - valorMenuSeleccionado;
        int tempCodigo = generarCodigo();
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
                tarjetaConsumoMenu.setCodigoConsumo(tempCodigo);
                tarjetaConsumoMenu.setEstado(false);
                TarjetaConsumoMenuId tarjetaConsumoMenuId = new TarjetaConsumoMenuId();
                tarjetaConsumoMenuId.setMenuId(selecccionMenu.getIdMenu());
                tarjetaConsumoMenuId.setTarjetaConsumoId(tarjetaConsumo.getIdTarjetaConsumo());
                tarjetaConsumoMenu.setTarjetaConsumoMenuId(tarjetaConsumoMenuId);
                tarjetaConsumoMenuFacadeLocal.create(tarjetaConsumoMenu);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Se ha guardado correctamente!"));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Ocrrio un error en el controlador"));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", " Error desde Exception, contacte al Administrador "));
        }
    }

    public void buscarCodigoConsumo() {

        int codConsumo;
        codConsumo = tarjetaConsumoMenu.getCodigoConsumo();
        try {
            tarjetaConsumoMenu = tarjetaConsumoMenuFacadeLocal.buscarCodidoConsumo(codConsumo).stream().findFirst().orElse(null);
            if (tarjetaConsumoMenu == null) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " El codigo no existe"));
            } else {

                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Procesando..."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Ocurrió una excepción."));
        }

    }

    public void validaCodigoConsumo() {

        int codConsumo;
        codConsumo = tarjetaConsumoMenu.getCodigoConsumo();

        try {
            tarjetaConsumoMenu = tarjetaConsumoMenuFacadeLocal.buscarCodidoConsumo(codConsumo).stream().findFirst().orElse(null);
            if (tarjetaConsumoMenu.isEstado() == true) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", " El código fue consumido o invalido"));
            } else {
                tarjetaConsumoMenu.setEstado(true);
                tarjetaConsumoMenuFacadeLocal.edit(tarjetaConsumoMenu);
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", " Se validó el consumo"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Ocurrió un error al validar el código, contacte al Administrador"));
        }

    }

    public List<TarjetaConsumoMenu> getLstCodigoConsumo() {
        return lstCodigoConsumo;
    }

    public void setLstCodigoConsumo(List<TarjetaConsumoMenu> lstCodigoConsumo) {
        this.lstCodigoConsumo = lstCodigoConsumo;
    }

    //GETs & SETs
    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<Object[]> getLstReportSemanalConsumos() {
        return lstReportSemanalConsumos;
    }

    public void setLstReportSemanalConsumos(List<Object[]> lstReportSemanalConsumos) {
        this.lstReportSemanalConsumos = lstReportSemanalConsumos;
    }

    public List<TarjetaConsumoMenu> getTarjetaConsumoMenusList() {
        return tarjetaConsumoMenusList;
    }

    public void setTarjetaConsumoMenusList(List<TarjetaConsumoMenu> tarjetaConsumoMenusList) {
        this.tarjetaConsumoMenusList = tarjetaConsumoMenusList;
    }

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
