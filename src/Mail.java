import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.activation.*;

public class Mail
{
    public void sendMail(String navn, String adresse, String sted, int pris){
        String to = "e.oppegaard@hotmail.com";
        final String from = "kamera@bang.is";
        final String brukernavn = "bangis5";
        final String password = "Svarten1975";
        String host = "localhost";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.domeneshop.no");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(brukernavn, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom( new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("BoligMatch");
            message.setText("Hei " + navn + ".\nVi har funnet en bolig som vi tror passer for deg: \nBolig: "
            + adresse + "\n" + sted + "\n" + pris + "\nHvis denne passer for deg, tinder me up and reply");

            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Mailen ble sendt!");
        }catch (MessagingException mex){
            mex.printStackTrace();
            throw new RuntimeException(mex);
        }
    }
}
