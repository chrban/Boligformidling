import java.io.*;
import java.util.Date;

abstract class Bolig implements Serializable, Comparable<Object>
{
    private String adresse;
    private int sted;
    private int boareal;
    private int rom;
    private int byggAr;
    private int utleiepris;
    private Date lagtUt;
    private int eierID;

    public Bolig(){}

    public Bolig(String ad,int s, int b, int r, int by, int u, int e)
    {
        adresse = ad;
        sted = s;
        boareal = b;
        rom = r;
        byggAr = by;
        utleiepris = u;
        lagtUt = new Date();
        eierID = e;
    }


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
    public void lesObjektFraFil(DataInputStream in) throws IOException{
        adresse = in.readUTF();
        sted = in.readInt();
        boareal = in.readInt();
        rom = in.readInt();
        byggAr = in.readInt();
        utleiepris = in.readInt();
        long dato = in.readLong();
        lagtUt = new Date(dato * 1000);
        eierID = in.readInt();

    }
    public void skrivTilFil(DataOutputStream out)throws IOException{
        out.writeUTF(adresse);
        out.writeInt(sted);
        out.writeInt(boareal);
        out.writeInt(rom);
        out.writeInt(byggAr);
        out.writeInt(utleiepris);
        long dato = lagtUt.getTime();
        out.writeLong(dato);
        out.writeInt(eierID);
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






