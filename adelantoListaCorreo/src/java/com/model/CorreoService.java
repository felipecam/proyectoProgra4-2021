/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.MessagingException;

/**
 *
 * @author fbriceno
 */
@ManagedBean(name = "correoService")
@ApplicationScoped
public class CorreoService {

    Correo c = new Correo();
    List<Correo> listaCorreo =  new ArrayList<>();
    
    public void llenarLista(){
        try {
            listaCorreo=c.readEmail();
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(CorreoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void init() {
        llenarLista();
    }

    public List<Correo> getCorreo() {
       llenarLista();
       return listaCorreo;
    }
}
