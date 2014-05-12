import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.activation.*;

public class Mail
{
    public boolean sendMail(String epost, String tekst){
        String to = epost;
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
            message.setText(tekst);

            Transport.send(message);
            //JOptionPane.showMessageDialog(null, "Mailen ble sendt!");
            return true;
        }catch (MessagingException mex){
            return false;
          //  mex.printStackTrace();
          //  throw new RuntimeException(mex);

        }
        catch (RuntimeException re){
            return false;
        }
    }

}
