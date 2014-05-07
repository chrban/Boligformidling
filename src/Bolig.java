import java.io.*;
import java.util.Date;


abstract class Bolig implements Serializable, Comparable<Object>
{
    private int id;
    private String adresse;
    private int sted;
    private int boareal;
    private int rom;
    private int byggAr;
    private int utleiepris;
    private Date lagtUt;
    private int eierID;
    private String bildesti;

    public Bolig(){}




    public Bolig(int i,String ad,int s, int b, int r, int by, int u, int e, String sti)
    {
        id = i;
        adresse = ad;
        sted = s;
        boareal = b;
        rom = r;
        byggAr = by;
        utleiepris = u;
        lagtUt = new Date();
        eierID = e;
        bildesti = sti;
    }

    public int getId(){return id;}
    public String getAdresse()
    {
        return adresse;
    }
    public int getSted(){
        return sted;
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
    public String getBildesti(){return bildesti;}

    public int compareTo(Object o)// MÅ LEGGE INN ORDENTLIGE KONSTANTER
    {
        Bolig b = (Bolig) o;
        if(b.getId()> id)
            return new Integer(1);
        else if(b.getId() == id)
            return new Integer(0);
        else
            return new Integer(-1);
    }

    public String toString()
    {
        return "Informasjon om bolig med adresse: " + adresse + "\n\n";
    }


}






