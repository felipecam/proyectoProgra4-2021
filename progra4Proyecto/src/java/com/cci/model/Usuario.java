/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.model;

import java.io.Serializable;

/**
 *
 * @author fbriceno
 */
public class Usuario implements Serializable{
    
    private int id;
    private String user;
    private String pass;
    private String nombre;
    private String apellidos;

    public Usuario() {
    }    

    public Usuario(int id, String user, String pass, String nombre, String apellidos) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
        this.apellidos = apellidos;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    } 
    
}
