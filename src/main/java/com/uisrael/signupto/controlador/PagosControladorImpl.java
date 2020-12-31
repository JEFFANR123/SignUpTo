/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisrael.signupto.controlador;

import com.uisrael.signupto.modelo.dao.PagosFacadeLocal;
import com.uisrael.signupto.modelo.entidades.Credenciales;
import com.uisrael.signupto.modelo.entidades.Pagos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author janrango
 */
@Named
@ViewScoped
public class PagosControladorImpl implements Serializable {

    @EJB
    private PagosFacadeLocal pagosFacadeLocal;

    private Pagos pagos;

    private List<Pagos> lstPagosA;

    private List<Pagos> lstPagosR;

    private List<Pagos> lstPagosP;

    private UploadedFile file;

    private StreamedContent downFile;

    private int[] sumPagos;
    
    private double saldoUsuario;

    @PostConstruct
    public void init() {
        pagos = new Pagos();
        lstPagosP = pagosFacadeLocal.lstFltPagos("P");
        lstPagosA = pagosFacadeLocal.lstFltPagos("A");
        lstPagosR = pagosFacadeLocal.lstFltPagos("R");
        
    }

    public byte[] tranformar(InputStream comprobante) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = comprobante.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] byteArray = buffer.toByteArray();
        return byteArray;
    }

    public void insertarPagos() {
        Date hoy = new Date();

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Credenciales usrpass = (Credenciales) context.getExternalContext().getSessionMap().get("username");
            if (usrpass != null && file != null) {

                pagos.setFkIdUsuario(usrpass.getIdUsuario());
                pagos.setEstado("P");
                pagos.setComprobantePago(tranformar(file.getInputstream()));
                pagos.setFechaPago(hoy);
                pagosFacadeLocal.create(pagos);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El pago fue registrado exitosamente"));
                FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            } else {
                context.getExternalContext().redirect("./../index.xhtml");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error al ingresar el pago"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error al ingresar el pago"));
        }

    }

    public void descargarPago() throws IOException {
        byte[] content = pagos.getComprobantePago();
        downFile = new DefaultStreamedContent(new ByteArrayInputStream(content), "image/jpg", "comprobante.jpg");
    }

    public void leePago(Pagos edtPago) {
        pagos = edtPago;
    }

    public void modificarPago() {
        pagosFacadeLocal.edit(pagos);
    }

    //GETS Y SETS

    public double getSaldoUsuario() {
        return saldoUsuario;
    }

    public void setSaldoUsuario(double saldoUsuario) {
        this.saldoUsuario = saldoUsuario;
    }
    
    
    public int[] getSumPagos() {
        return sumPagos;
    }

    public void setSumPagos(int[] sumPagos) {
        this.sumPagos = sumPagos;
    }

    public StreamedContent getDownFile() {
        return downFile;
    }

    public void setDownFile(StreamedContent downFile) {
        this.downFile = downFile;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public List<Pagos> getLstPagosA() {
        return lstPagosA;
    }

    public void setLstPagosA(List<Pagos> lstPagosA) {
        this.lstPagosA = lstPagosA;
    }

    public List<Pagos> getLstPagosR() {
        return lstPagosR;
    }

    public void setLstPagosR(List<Pagos> lstPagosR) {
        this.lstPagosR = lstPagosR;
    }

    public List<Pagos> getLstPagosP() {
        return lstPagosP;
    }

    public void setLstPagosP(List<Pagos> lstPagosP) {
        this.lstPagosP = lstPagosP;
    }

}
