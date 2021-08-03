/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progra4tareadb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author fbriceno
 */
public class Correo {

    private String remitente;
    private String destinatario;
    private String asunto;
    private String body;
    private Date fecha;
    private int id;

    public Correo() {
    }

    public Correo(String remitente, String destinatario, String asunto, String body, Date fecha, int id) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.body = body;
        this.fecha = fecha;
        this.id = id;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Correo> readEmail() throws NoSuchProviderException, MessagingException, IOException {
        List<Correo> listaCorreo = new ArrayList<Correo>();
        Properties props = new Properties();
        //SI EL CORREO DEL QUE SE ENVIA UTILIZA AUTENTICACION
        String user = "jmailulatina@gmail.com";
        String password = "Qwertyuio*88";
        // el host de correo, en nuestro caso gmail
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        //
        props.setProperty("mail.pop3.starttls.enable", "false");

        // Hay que usar SSL
        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "false");

        // Puerto 995 para conectarse.
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");
        Session sesion = Session.getInstance(props);
        //sesion.setDebug(true);
        Store store = sesion.getStore("pop3");
        store.connect("pop.gmail.com", user, password);
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        Message[] mensajes = folder.getMessages();
        

        for (int i = 0; i < mensajes.length; i++) {
            Correo miCorreo = new Correo();
            miCorreo.setAsunto(mensajes[i].getSubject());
            miCorreo.setFecha(mensajes[i].getSentDate());
            miCorreo.setRemitente(mensajes[i].getFrom()[0].toString());
            miCorreo.setDestinatario(mensajes[i].getAllRecipients()[0].toString());
            miCorreo.setBody(getMessageContent(mensajes[i]));
            listaCorreo.add(miCorreo);
          //  Db  db= new Db();
          //  db.insertarCorreo(miCorreo);
        }
        return listaCorreo;
    }
    
    private String getMessageContent(Message message) throws MessagingException {
    try {
        Object content = message.getContent();
        if (content instanceof Multipart) {
            StringBuffer messageContent = new StringBuffer();
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                Part part = multipart.getBodyPart(i);
                if (part.isMimeType("text/plain")) {
                    messageContent.append(part.getContent().toString());
                }
            }
            return messageContent.toString();
        }
        return content.toString();

    } catch (IOException e) {
        e.printStackTrace();
    }
    return "";
}

    public void sendEmail() {
        Properties props = new Properties();
        //SI EL CORREO DEL QUE SE ENVIA UTILIZA AUTENTICACION
        String user = "jmailulatina@gmail.com";
        String password = "Qwertyuio*88";
        // el host de correo, en nuestro caso gmail
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        //el puerto que vamos a usar
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", user);
        //le indicamos que es necesario autentificarse
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            //Recipients
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("jfelipecamacho@hotmail.com"));
            message.setSubject("Tarea progra");
            String mensaje = "correo para: ";
            //message.setContent(mensaje,  "text/html;charset=utf-8");
            message.setContent(mensaje, "text/html");
            Transport.send(message);
        } catch (Exception e) {
            String error = e.getMessage();
        }
    }

    @Override
    public String toString() {
        return "Correo{" + "remitente=" + remitente + ", destinatario=" + destinatario + ", asunto=" + asunto + ", body=" + body + ", fecha=" + fecha + '}';
    }


    
    
}
