import java.util.Date;

public class Kontrakt{
    private Utleier eier;
    private Boligsøker leier;
    private Bolig bolig;
    Kontrakt neste;
    private Date start;
    private Date slutt;


    public Kontrakt(Utleier e, Boligsøker l, Bolig b, Date s, Date sl){
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
        return leier.getFornavn();
    }
    public Date getKontraktStart(){
        return start;
    }
    public Date getKontraktSlutt(){
        return slutt;
    }
    public String toString(){
        return "Kontrakt for: " + bolig.getAdresse() + "\nUtleier: " + eier.getNavn() + "\nLeier: " + leier.getFornavn() + "\n";
    }

    public String[] tilTabell()
    {
        String [] ut = new String[4];
        ut[0] = eier.getNavn();
        ut[1] = leier.getNavn();
        ut[2] = start.toString();
        ut[3] = slutt.toString();

        return ut;
    }
}
