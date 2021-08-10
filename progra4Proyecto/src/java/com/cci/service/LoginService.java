/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.Correo;
import com.cci.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 *
 * @author fbriceno
 */
public class LoginService {

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioCorreo servicioCorreo = new ServicioCorreo();
    
    //usuarios

    public List<Usuario> buscarTodo() {
        return servicioUsuario.listarTodos();
    }

    public Usuario BuscarPorUsuarioPassword(String user, String pass) {

        return servicioUsuario.BuscarPorUsuarioPassword(user, pass);
    }

    public String crear(Usuario u) {
        return servicioUsuario.add(u);
    }

    public void editar(Usuario usuario) {
        servicioUsuario.editar(usuario);
    }

        
    //correos
    public void InsertarCorreo(Correo c) throws GlobalException {
        servicioCorreo.insertarCorreo(c);
    }
    

    public List<Correo> ListaCorreos() throws GlobalException {
        try {
            Correo c = new Correo();
            c.readEmail();
            return servicioCorreo.listarTodosCorreos();
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicioCorreo.listarTodosCorreos();
    }

    public void eliminarCorreo(Correo c) {
        this.servicioCorreo.eliminarCorreo(c);
    }
    
}
