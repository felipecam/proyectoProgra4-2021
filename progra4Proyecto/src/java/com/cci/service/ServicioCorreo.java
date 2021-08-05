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

/**
 *
 * @author fbriceno
 */
public class ServicioCorreo extends Servicio {

    public void insertarCorreo(Correo c) {
        //String Sql = "INSERT INTO Articulos (NombreArticulo, CodigoBarras, Precio, IVA, Costo, Saldo) VALUES ('" + articulo.getNombreArticulo() + "','" + articulo.getCodigoBarra() + "', " + articulo.getPrecio() + ", " + articulo.getIva() + ", " + articulo.getCosto() + ", " + articulo.getSaldo() + ")";
        // String Sql = "INSERT INTO persona (id,nombre,apellidos,telefono) values ()";
        Correo miCorreo = c;
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("INSERT INTO correo (destinatario,asunto,body,fecha) VALUES ('%s','%s','%s',CURDATE());", miCorreo.getDestinatario(), miCorreo.getAsunto(), miCorreo.getBody());
        try {
            Statement s = getConexion().createStatement();
            s.executeUpdate(sbuf.toString());
            //"INSERT INTO persona (id,nombre,apellidos,user,pass) values (" + miUsuario.getId() + ",'" + miUsuario.getNombre() + "','" + miUsuario.getApellidos() + "','"+ miUsuario.getUser()+ "','" + miUsuario.getPass()+ "');"
            s.close();

        } catch (GlobalException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCorreo(Correo c) {
        Correo miCorreo = c;
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("UPDATE correo SET destinatario = '%s', asunto = '%s', body= '%s' WHERE idCorreo= %d;",
                miCorreo.getDestinatario(),
                miCorreo.getAsunto(),
                miCorreo.getBody(),
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

    //lista correos
    public List<Correo> listarTodos() {
        List<Correo> listaCorreo = new ArrayList<Correo>();
        Correo miCorreo = null;
        try {
            Statement s = getConexion().createStatement();
            ResultSet rs = s.executeQuery("select * from correo");
            while (rs.next()) {
                miCorreo = new Correo();
                miCorreo.setId(Integer.parseInt(rs.getString("idCorreo")));
                miCorreo.setDestinatario(rs.getString("destinatario"));
                miCorreo.setAsunto(rs.getString("asunto"));
                miCorreo.setBody(rs.getString("body"));
                //  miCorreo.setFecha(rs.getDate("fecha"));
                listaCorreo.add(miCorreo);
            }
            s.close();

        } catch (GlobalException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaCorreo;
    }

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
