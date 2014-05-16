import javax.swing.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/*
Filen inneholder alle datafelt og metoder som er til felles for alle
boligtypene.
Skrevet av: Kristoffer, Christer, Emil.
 */

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
    //get metoder for klassen Bolig
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
    public String getBildesti(){
        String[] sti;
        String os = System.getProperty("os.name");
        if(os.startsWith("Windows")) {
            sti = bildesti.split("ing\\\\");
        }else{
            sti = bildesti.split("Boligformidling\\S");
        }

        return sti[1];
    }
    public String getEierID(){
        return eierID;
    }
    public boolean getUtleid(){
        return utleid;
    }
    //end of get metoder
    //setter boligen til utleid
    public void setTilUtleid(){
        utleid = true;
    }
    //setter boligen til ikke lengre utleid
    public void setTilIkkeUtleid(){
        utleid = false;
    }
    //sammenligner to bolig objekter på id
    public int compareTo(Object o)
    {
        Bolig b = (Bolig) o;
        if(b.getId()> id)
            return 1;
        else if(b.getId() == id)
            return 0;
        else
            return -1;
    }
    public String toString()
    {
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
}//end of class Bolig






