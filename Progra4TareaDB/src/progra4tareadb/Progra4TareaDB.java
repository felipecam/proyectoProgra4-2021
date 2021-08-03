/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra4tareadb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

/**
 *
 * @author fbriceno
 */
public class Progra4TareaDB {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws javax.mail.NoSuchProviderException
     */
    public static void main(String[] args) throws SQLException, MessagingException, NoSuchProviderException, IOException {

        Db p = new Db();
        Correo c = new Correo();
        List<Correo> lista = c.readEmail();
        lista.stream().forEach(System.out::println);


    }

}
