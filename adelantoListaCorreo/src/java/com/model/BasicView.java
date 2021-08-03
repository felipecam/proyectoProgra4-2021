package com.model;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fbriceno
 */
@ManagedBean(name ="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {

    private List<Correo> correo;

    @ManagedProperty("#{correoService}")
    private CorreoService service;


    @PostConstruct
    public void init() {
        correo = service.getCorreo();
    }

    public List<Correo> getCorreo() {
        return correo;
    }

    public void setCorreo(List<Correo> correo) {
        this.correo = correo;
    }


    public CorreoService getService() {
        return service;
    }

    public void setService(CorreoService service) {
        this.service = service;
    }
    
    
}