import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.activation.*;
/*
Filen inneholder metoder for å sende mail med informasjon om boliger til boligsøkere
Skrevet av: Emil, s198772
Sist oppdatert: 09/05/2014
 */
public class Mail
{
    /*
    Sender en mail med informasjon om en bolig til en gitt epostaddresse. Variablene i parameterlista er informasjon om
    boligen vi har tenkt å innformere boligsøkeren om.
     */
    public void sendMail(String epost, String navn, String adresse, String sted, int pris){
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
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(epost));
            message.setSubject("BoligMatch");
            message.setText("Hei " + navn + ".\nVi har funnet en bolig som vi tror passer for deg: \nBolig: "
            + adresse + "\n" + sted + "\n" + pris + "\nHvis denne passer for deg, send oss en tilbakemedlig\n Mvh. BoligFormidling.");

            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Mailen ble sendt!");
        }catch (MessagingException mex){
            mex.printStackTrace();
            throw new RuntimeException(mex);
        }
    }
}//end of class Mail
