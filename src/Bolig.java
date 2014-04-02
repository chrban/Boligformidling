import java.io.Serializable;
import java.util.Date;

abstract class Bolig implements Serializable
{
    private String adresse;
    private int boareal;
    private int rom;
    private int byggAr;
    private int utleiepris;
    private Date lagtUt;

    public Bolig(){}

    public Bolig(String ad, int b, int r, int by, int u, Date l)
    {
        adresse = ad;
        boareal = b;
        rom = r;
        byggAr = by;
        utleiepris = u;
        lagtUt = l;
    }


    public String getAdresse()
    {
        return adresse;
    }
    public int getBoareal()
    {
        return boareal;
    }
    public int getRom()
    {
        return rom;
    }
    public int getUtleiepris()
    {
        return utleiepris;
    }
    public Date getLagtUt()
    {
        return lagtUt;
    }

    public String toString()
    {
        return "Informasjon om bolig med adresse: " + adresse + "\n\n";
    }


}






