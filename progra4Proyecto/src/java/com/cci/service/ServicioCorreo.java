/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.Formatter;
import com.cci.model.Correo;
import java.sql.Timestamp;

/**
 *
 * @author fbriceno
 */
public class ServicioCorreo extends Servicio {

    public void insertarCorreo(Correo c) throws GlobalException {
        java.util.Date myDate = new java.util.Date(c.getFecha().toString());
        Timestamp ts = new Timestamp(myDate.getTime());
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("INSERT INTO correo (destinatario,remitente,asunto,body,fecha) VALUES ('%s','%s','%s','%s','%s');", c.getDestinatario(), c.getRemitente(), c.getAsunto(), c.getBody(), ts);
        try {
            Statement s = getConexion().createStatement();
            s.executeUpdate(sbuf.toString());
            //"INSERT INTO persona (id,nombre,apellidos,user,pass) values (" + miUsuario.getId() + ",'" + miUsuario.getNombre() + "','" + miUsuario.getApellidos() + "','"+ miUsuario.getUser()+ "','" + miUsuario.getPass()+ "');"
            s.close();

        } catch (SQLException ex) {
            String error = ex.getMessage();
            //  System.out.println(error);
        }
    }
    
    public List<Correo> listarTodosCorreos() throws GlobalException {
        List<Correo> listaCorreo = new ArrayList<Correo>();
        Correo miCorreo = null;
        try {
            Statement s = getConexion().createStatement();
            ResultSet rs = s.executeQuery("select * from correo");
            while (rs.next()) {
                miCorreo = new Correo();
                miCorreo.setRemitente(rs.getString("remitente"));
                miCorreo.setAsunto(rs.getString("asunto"));
                miCorreo.setBody(rs.getString("body"));
                miCorreo.setFecha(rs.getDate("fecha"));
                listaCorreo.add(miCorreo);
            }
            s.close();

        } catch (SQLException ex) {
            String error = ex.getMessage();
            System.out.println(error);
        }

        return listaCorreo;
    }


    //buscar Correos por asunto

    public void eliminarCorreo(Correo c) {
        Correo miCorreo = c;
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("DELETE FROM correo WHERE idCorreo= %d;",
                miCorreo.getId());
        try {
            Statement s = getConexion().createStatement();
            s.executeUpdate(sbuf.toString());
            s.close();

        } catch (GlobalException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
