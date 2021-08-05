/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.model.Correo;
import com.cci.model.Usuario;
import java.util.ArrayList;
import java.util.List;
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

    public void InsertarCorreo(Correo c) {
        servicioCorreo.insertarCorreo(c);
    }
    
    //correos

    public void editarCorreo(Correo c) {
        servicioCorreo.editarCorreo(c);
    }

    public List<Correo> buscarCorreo() {
        return servicioCorreo.listarTodos();
    }

    public void eliminarCorreo(Correo c) {
        this.servicioCorreo.eliminarCorreo(c);
    }
    
}
