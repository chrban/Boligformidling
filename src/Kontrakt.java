import sun.java2d.pipe.SpanShapeRenderer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
/*
  Filen inneholder metoder og datafelt for å kunne lagre og gjøre opperasjoner på kontrakter.
  Skrevet av: Emil, s198772
  Sist oppdatert: 11/05/2014
 */
public class Kontrakt implements Serializable{
    private Utleier eier;
    private Boligsøker leier;
    private Bolig bolig;
    Kontrakt neste;
    private Calendar start;
    private Calendar slutt;
    private SimpleDateFormat df;


    public Kontrakt(Utleier e, Boligsøker l, Bolig b, Calendar s, Calendar sl){
        eier = e;
        leier = l;
        bolig = b;
        start = s;
        slutt = sl;
        df = new SimpleDateFormat("yyyy/MM/dd");
    }
    //getMetoder for forskjellige datafelt, mangle blir ikke brukt, men de er her om det i fremtiden ved en
    // oppdatering av programmet skulle får bruk for dem.
    public Utleier getUtleier(){
        return eier;
    }
    public Boligsøker getBoligsøker(){
        return leier;
    }
    public Bolig getBolig(){
        return bolig;
    }
    public String getUtleierNavn(){
        return eier.getNavn();
    }
    public String getBoligsøkerNavn(){
        return leier.getFornavn();
    }
    public Calendar getKontraktStart(){
        return start;
    }
    public Calendar getKontraktSlutt(){
        return slutt;
    }
    public String toString(){
        return "Kontrakt for: " + bolig.getAdresse() + " \nUtleier: " + eier.getNavn() + " \nLeier: " + leier.getFornavn()+ " "+ leier.getEtternavn() + " \nKontraktstart: " + df.format(start.getTime()) + " \nKontraktslutt: " + df.format(slutt.getTime()) + "\n";
    }
    //end of getMetoder
    //setMetodene har i oppgave å sette bolig og boligsøker sine boolean felt til true, slik at disse ikke lengre
    //dukker opp når vi skal feks. matche eller registrere kontrakter.
    public void setTilUtleid(){
        bolig.setTilUtleid();
    }
    public void setLeietaker(){
        leier.setBolig();
    }
    //end of setMetoder
    //I GUI bruker vi JTable til å vise informasjon og da må dataen vi skal vise være i form av en Stringarray,
    //denne metoden returnerer en Stringarray med informasjon om kontrakten.
    public String[] tilTabell()
    {
        String [] ut = new String[4];
        ut[0] = eier.getNavn();
        ut[1] = leier.getNavn();
        ut[2] = df.format(start.getTime());
        ut[3] = df.format(slutt.getTime());

        return ut;
    }
}//End of class Kontrakter
