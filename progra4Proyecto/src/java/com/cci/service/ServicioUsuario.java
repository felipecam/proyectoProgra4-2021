/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.service;

import com.cci.service.GlobalException;
import com.cci.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.Formatter;
import java.sql.PreparedStatement;

/**
 *
 * @author fbriceno
 */
public class ServicioUsuario extends Servicio {

    public List<Usuario> listarTodos() {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        Usuario miUsuario = null;
        try {
            Statement s = getConexion().createStatement();
            ResultSet rs = s.executeQuery("select * from persona");
            while (rs.next()) {
                miUsuario = new Usuario();
                miUsuario.setId(Integer.parseInt(rs.getString("id")));
                miUsuario.setNombre(rs.getString("nombre"));
                miUsuario.setApellidos(rs.getString("apellidos"));
                miUsuario.setUser(rs.getString("user"));
                miUsuario.setPass(rs.getString("pass"));
                listaUsuario.add(miUsuario);
            }
            s.close();

        } catch (GlobalException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaUsuario;
    }

    public Usuario BuscarPorUsuarioPassword(String user, String pass) {
        Usuario miUsuario = null;
        try {
            Statement s = getConexion().createStatement();
            ResultSet rs = s.executeQuery("select * from persona where user ='" + user + "' and pass= '" + pass + "'");
            if (rs.next()) {
                miUsuario = new Usuario();
                miUsuario.setId(Integer.parseInt(rs.getString("id")));
                miUsuario.setNombre(rs.getString("nombre"));
                miUsuario.setApellidos(rs.getString("apellidos"));
                miUsuario.setUser(rs.getString("user"));
                miUsuario.setPass(rs.getString("pass"));
            }
            s.close();

        } catch (GlobalException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return miUsuario;
    }

    public void crear(Usuario usuarioData) {
        Usuario miUsuario = usuarioData;
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("INSERT INTO persona(nombre,apellidos,user,pass) VALUES ('%s','%s','%s','%s');", miUsuario.getId(), miUsuario.getNombre(), miUsuario.getApellidos(), miUsuario.getUser(), miUsuario.getPass());
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

    public String add(Usuario usuarioData) {
        Usuario miUsuario = usuarioData;
//        StringBuilder sbuf = new StringBuilder();
//        Formatter fmt = new Formatter(sbuf);
//        fmt.format("INSERT INTO persona(id,nombre,apellidos,user,pass) VALUES (%d,'%s','%s','%s','%s');", miUsuario.getId(), miUsuario.getNombre(), miUsuario.getApellidos(), miUsuario.getUser(), miUsuario.getPass());
        int i = 0;
        if (usuarioData.getUser() != null) {
            PreparedStatement ps = null;
            try {
                Statement s = getConexion().createStatement();
                String sql = "INSERT INTO persona(nombre,apellidos,user,pass) VALUES(?,?,?,?)";
                ps = getConexion().prepareStatement(sql);
                ps.setString(1, miUsuario.getNombre());
                ps.setString(2, miUsuario.getApellidos());
                ps.setString(3, miUsuario.getUser());
                ps.setString(4, miUsuario.getPass());
                i = ps.executeUpdate();
                System.out.println("Data Added Successfully");

            } catch (GlobalException ex) {
                Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ServicioUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (i > 0) {
            return "SI";
        } else {
            return "NO";
        }
    }

    public void editar(Usuario usuarioData) {
        //String Sql = "INSERT INTO Articulos (NombreArticulo, CodigoBarras, Precio, IVA, Costo, Saldo) VALUES ('" + articulo.getNombreArticulo() + "','" + articulo.getCodigoBarra() + "', " + articulo.getPrecio() + ", " + articulo.getIva() + ", " + articulo.getCosto() + ", " + articulo.getSaldo() + ")";
        // String Sql = "INSERT INTO persona (id,nombre,apellidos,telefono) values ()";
        Usuario miUsuario = usuarioData;
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("UPDATE persona SET nombre = '%s', apellidos = '%s', user= '%s', pass= '%s' WHERE id= %d;",
                miUsuario.getNombre(),
                miUsuario.getApellidos(),
                miUsuario.getUser(),
                miUsuario.getPass(),
                miUsuario.getId());
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

}
