/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra4tareadb;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author fbriceno
 */
public class Db extends Servicio {

    public Db() {
    }



    //insertar correo
    public void insertarCorreo(Correo c) {

        java.util.Date myDate = new java.util.Date(c.getFecha().toString());
        Timestamp ts = new Timestamp(myDate.getTime());
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("INSERT INTO correo (destinatario,remitente,asunto,body,fecha) VALUES ('%s','%s','%s','%s','%s');", c.getDestinatario(),c.getRemitente(), c.getAsunto(), c.getBody(), ts);
        try {
            Statement s = getConexion().createStatement();
            s.executeUpdate(sbuf.toString());
            //"INSERT INTO persona (id,nombre,apellidos,user,pass) values (" + miUsuario.getId() + ",'" + miUsuario.getNombre() + "','" + miUsuario.getApellidos() + "','"+ miUsuario.getUser()+ "','" + miUsuario.getPass()+ "');"
            s.close();

        } catch (SQLException ex) {
            String error = ex.getMessage();
            System.out.println(error);
           }
        }
    

    public void editarCorreo() {
        Correo miCorreo = new Correo("test@test.com", "test@test.com", "asunto", "body", null, 6);
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

        } catch (SQLException ex) {
            String error = ex.getMessage();
            System.out.println(error);
        }
    }

}
