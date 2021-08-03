package progra4tareadb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import progra4tareadb.GlobalException;
/**
 *
 * @author fbriceno
 */
public abstract class Servicio {

    private Connection conexion;

    private void conectar() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //STRING DE CONEXION
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/progra4tarea?user=progra3&password=123456&useSSL=false");
        } catch (ClassNotFoundException | SQLException ex) {
           
        }
    }

    protected Connection getConexion()  {
        try {
            if (conexion != null && !conexion.isClosed()) {
                return conexion;
            } else {
                conectar();
                return conexion;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
