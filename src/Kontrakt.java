import sun.java2d.pipe.SpanShapeRenderer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

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
        df = new SimpleDateFormat("yyyy MM dd");
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
    public String toString(){
        return "Kontrakt for: " + bolig.getAdresse() + " \nUtleier: " + eier.getNavn() + " \nLeier: " + leier.getFornavn() + " \nKontraktstart: " + df.format(start.getTime()) + " \nKontraktslutt: " + df.format(slutt.getTime()) + "\n";
    }
    public void setTilUtleid(){
        bolig.setTilUtleid();
    }
    public void setLeietaker(){
        leier.setBolig();
    }

    public String[] tilTabell()
    {
        String [] ut = new String[4];
        ut[0] = eier.getNavn();
        ut[1] = leier.getNavn();
        ut[2] = df.format(start.getTime());
        ut[3] = df.format(slutt.getTime());

        return ut;
    }
}
