import java.io.*;
import java.util.Date;

abstract class Bolig implements Serializable, Comparable<Object>
{
    private String adresse;
    private int boareal;
    private int rom;
    private int byggAr;
    private int utleiepris;
    private Date lagtUt;

    public Bolig(){}

    public Bolig(String ad, int b, int r, int by, int u)
    {
        adresse = ad;
        boareal = b;
        rom = r;
        byggAr = by;
        utleiepris = u;
        lagtUt = new Date();
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
    public void lesObjektFraFil(DataInputStream in) throws IOException{
        adresse = in.readUTF();
        boareal = in.readInt();
        rom = in.readInt();
        byggAr = in.readInt();
        utleiepris = in.readInt();
        long dato = in.readLong();
        lagtUt = new Date(dato * 1000);

    }
    public void skrivTilFil(DataOutputStream out)throws IOException{
        out.writeUTF(adresse);
        out.writeInt(boareal);
        out.writeInt(rom);
        out.writeInt(byggAr);
        out.writeInt(utleiepris);
        long dato = lagtUt.getTime();
        out.writeLong(dato);
    }

    public int compareTo(Object o)// MÅ LEGGE INN ORDENTLIGE KONSTANTER
    {
        Bolig b = (Bolig) o;
        if(b.getUtleiepris()> utleiepris)
            return new Integer(1);
        else if(b.getUtleiepris() == utleiepris)
            return new Integer(0);
        else
            return new Integer(-1);
    }

    public String toString()
    {
        return "Informasjon om bolig med adresse: " + adresse + "\n\n";
    }


}






