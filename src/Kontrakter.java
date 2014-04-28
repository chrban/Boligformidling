import java.util.Date;

public class Kontrakter {
    private Utleier eier;
    private Boligsøker leier;
    private Bolig bolig;
    Kontrakter neste;
    private Date start;
    private Date slutt;


    public Kontrakter(Utleier e, Boligsøker l, Bolig b, Date s, Date sl){
        eier = e;
        leier = l;
        bolig = b;
        start = s;
        slutt = sl;
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
        return leier.getNavn();
    }
    public String toString(){
        return "Kontrakt for: " + bolig.getAdresse() + "\nUtleier: " + eier.getNavn() + "\nLeier: " + leier.getNavn() + "\n";
    }
}
