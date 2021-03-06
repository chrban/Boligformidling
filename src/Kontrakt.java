import sun.java2d.pipe.SpanShapeRenderer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
/*
  Filen inneholder metoder og datafelt for å kunne lagre og gjøre opperasjoner på kontrakter.
  Skrevet av: Kristoffer, Christer og Emil
  Sist oppdatert: 11/05/2014
 */
public class Kontrakt implements Serializable{
    private Utleier eier;
    private Boligsøker leier;
    private Bolig bolig;
    Kontrakt neste;
    private Calendar lagret;
    private Calendar start;
    private Calendar slutt;
    private SimpleDateFormat df;
    private String id;

    public Kontrakt(Utleier e, Boligsøker l, Bolig b, Calendar s, Calendar sl, String i){
        eier = e;
        leier = l;
        bolig = b;
        start = s;
        slutt = sl;
        lagret = Calendar.getInstance();
        df = new SimpleDateFormat("yyyy/MM/dd");
        id = i;
    }
    //getMetoder for forskjellige datafelt, mangle blir ikke brukt, men de er her om det i fremtiden ved en
    // oppdatering av programmet skulle får bruk for dem.
    public String getId()
    {
        return id;
    }
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
        String [] ut = new String[Konstanter.UNIK_LENGDE_LEILIGHET];
        ut[0] = eier.getNavn();
        ut[1] = leier.getNavn();
        ut[2] = df.format(start.getTime());
        ut[3] = df.format(slutt.getTime());
        ut[4] = id;

        return ut;
    }
    public String toString(){

        return "_______________LEIEKONTRAKT_______________"+
                "\n\n Denne leiekontrakten gjelder for følgende parter:\n\n"+
                "Utleier: " + eier.getFornavn() + " " + eier.getEtternavn()+
                "\nLeieteker: " + leier.getFornavn()+ " "+ leier.getEtternavn() +
                "\n\nGjelder for bolig med adresse: "+bolig.getAdresse() +
                "\n\nDet er tegnet kontrakt fra: " + df.format(start.getTime()) + " \nTil: " + df.format(slutt.getTime()) +
                "\n\nKontrakten ble tegnet: " + df.format(lagret.getTime());
    }
}//End of class Kontrakter
