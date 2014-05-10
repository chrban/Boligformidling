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
    private String eierID;
    private String bildesti;
    private boolean utleid;
    private String beskrivelse;

    public Bolig(){}




    public Bolig(int i,String ad,int s, int b, int r, int by, int u, String e, String sti, String be)
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
        utleid = false;
        beskrivelse = be;
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
    public void setTilUtleid(){
        utleid = true;
    }
    public void setTilIkkeUtleid(){
        utleid = false;
    }
    public String getEierID(){
        return eierID;
    }
    public boolean getUtleid(){
        return utleid;
    }


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
        String ut = "";
        String by = "";
        switch(sted){
            case 1: by = "Oslo";
                    break;
            case 2: by = "Bergen";
                    break;
            case 3: by = "Stavanger";
                    break;
            case 4: by = "Trondheim";
                    break;
            case 5: by = "Kristiansand";
                    break;
            case 6: by = "Tromsø";
                    break;
        }
        String utleid = "";
        if(getUtleid())
            utleid = "Utleid";
        else
            utleid = "Ledig";

        return "Informasjon om bolig: \nAdresse: " + adresse + "\nBy: " + by + "\nPris: " + utleiepris + "\nStatus: " + utleid + "\nBeskrivelse av bolig: " + beskrivelse + "\nEierID: " + eierID;
    }


}






