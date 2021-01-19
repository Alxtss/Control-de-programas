package Conexion;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class bugReport {

    private final Properties props;

    public bugReport() {
        props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        //correo de emisor(quien envia el reporte de fallos)-- modificar a conveniencia
        props.put("from", "corrreodeejemplo@gmail.com");
        props.put("username", "corrreodeejemplo@gmail.com");
        props.put("password", "123456");
    }

    public void enviarReporte(String to, String subject, String content) throws MessagingException {
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(props.getProperty("from")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);
        Transport.send(message);

        System.out.println("mensaje enviado!");
    }
}
