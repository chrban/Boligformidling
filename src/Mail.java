import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.activation.*;
/*
Filen inneholder metode for å sende mail til en klient. Dette regner vi med at ikke blir tatt med i
vurderingen siden klassen importerer fra et biblotek som ikke ligger i Javas standard klassebiblotek.
Men velger å ha den med for at vi syntes det var kult.
Skrevet av: Emil, Christer
Siste versjon: 12/05/2014
 */
public class Mail
{
    public boolean sendMail(String epost, String tekst){
        final String from = "mail@adresse";
        final String brukernavn = "username";
        final String password = "password";
        String host = "localhost";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.domene.no");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(brukernavn, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom( new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(epost));
            message.setSubject("BoligMatch");
            message.setText(tekst);

            Transport.send(message);
            return true;
        }catch (MessagingException mex){
            return false;
        }
        catch (RuntimeException re){
            return false;
        }
    }
}//enf of class Mail
