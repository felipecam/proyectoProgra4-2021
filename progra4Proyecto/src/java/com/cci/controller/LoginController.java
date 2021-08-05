/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.controller;

import com.cci.model.Correo;
import com.cci.model.Usuario;
import com.cci.service.LoginService;
import com.cci.service.ServicioUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author fbriceno
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String usuario;
    private String clave;
    private String nombre;
    private String apellidos;
//    private String body;
//    private String destinatario;
//    private String asunto;
    private int id;
    private List<Correo> listaCorreo = new ArrayList<Correo>();
    private Usuario usuarioData = new Usuario();
    private boolean isEditarCorreo;
    private String mensajeError;
    private Correo correoData = new Correo();

    public LoginController() {
    }

    public LoginController(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public boolean isIsEditarCorreo() {
        return isEditarCorreo;
    }

    public void setIsEditarCorreo(boolean isEditarCorreo) {
        this.isEditarCorreo = isEditarCorreo;
    }

    public List<Correo> getListaCorreo() {
        return listaCorreo;
    }

    public void setListaCorreo(List<Correo> listaCorreo) {
        this.listaCorreo = listaCorreo;
    }

//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public String getDestinatario() {
//        return destinatario;
//    }
//
//    public void setDestinatario(String destinatario) {
//        this.destinatario = destinatario;
//    }
//
//    public String getAsunto() {
//        return asunto;
//    }
//
//    public void setAsunto(String asunto) {
//        this.asunto = asunto;
//    }
    public Correo getCorreoData() {
        return correoData;
    }

    public void setCorreoData(Correo correoData) {
        this.correoData = correoData;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioData() {
        return usuarioData;
    }

    public void setUsuarioData(Usuario usuarioData) {
        this.usuarioData = usuarioData;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String ingresar() throws IOException, MessagingException {
        LoginService loginService = new LoginService();
        usuarioData = loginService.BuscarPorUsuarioPassword(this.usuario, this.clave);
        if (usuarioData != null) {

            this.listaCorreo = correoData.readEmail();
            this.usuarioData = new Usuario();
            this.isEditarCorreo = false;

            return "SI";
        } else {
            return "NO";
        }
    }

    public String regitraUsuario() throws IOException, MessagingException {
        LoginService loginService = new LoginService();
        String registrar = loginService.crear(usuarioData);
        if (registrar.equals("SI")) {
            this.listaCorreo = correoData.readEmail();
            this.usuarioData = new Usuario();
            this.isEditarCorreo = false;
            return "SI";
        } else {
            return "NO";
        }
    }

    public void salir() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/faces/index.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirect() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/faces/index.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void enviarCorreo() {
//        LoginService loginService = new LoginService();
//        loginService.InsertarCorreo(this.correoData);
//        this.correoData.EnviarCorreo();
//   //     this.listaCorreo = loginService.buscarCorreo();
//       // this.isCorreo = false;
//    }
    public void editarCorreo(Correo correoSeleccionado) {
        this.correoData = correoSeleccionado;
        this.isEditarCorreo = true;
    }

    public void salvarEditarCorreo() {
        LoginService loginService = new LoginService();
        loginService.editarCorreo(this.correoData);
        //  this.listaCorreo = loginService.buscarCorreo();

    }

    public void actualizarCorreo() {
        try {
            this.listaCorreo = correoData.readEmail();
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//        public void eliminarCorreo(Correo correoSeleccionado) {
//        this.correoData = correoSeleccionado;
//        LoginService loginService = new LoginService();
//        loginService.eliminarCorreo(correoData);
//        this.listaCorreo= loginService.buscarCorreo();
//    }
}
