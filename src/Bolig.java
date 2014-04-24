import java.io.*;
import java.util.Date;

abstract class Bolig implements Serializable
{
    private String adresse;
    private int sted;
    private int boareal;
    private int rom;
    private int byggAr;
    private int utleiepris;
    private Date lagtUt;

    public Bolig(){}

    public Bolig(String ad,int s, int b, int r, int by, int u)
    {
        adresse = ad;
        sted = s;
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

    public String toString()
    {
        return "Informasjon om bolig med adresse: " + adresse + "\n\n";
    }


}






