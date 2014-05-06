import javax.swing.*;
import java.io.Serializable;

/**
 * Created by Kristoffer on 04.04.2014.
 */
public class Boligsøker extends Person implements Serializable
{
    int[] Krav;

    Boligsøker neste;

    // datafelt for krav
    int boligtype, by, rom, minPris, maxPris, parkering, antEtasjer, kjeller,
        heis, balkong, delerBadMed, delerKjøkkenMed;


    public Boligsøker (){}

    public Boligsøker(String id, String fn, String en, String a, String t, String e, int blgtp, int b, int r, int
                      map, int mip, int p, int ae, int k, int h,
                      int blkng, int dbm, int dkm )
    {
        super(id, fn, en, a, t, e);
        // krav
        boligtype = blgtp; by = b; rom = r; minPris = mip; maxPris = map; parkering = p; antEtasjer = ae;
        kjeller = k; heis = h; balkong = blkng;
        delerBadMed = dbm; delerKjøkkenMed= dkm;
        Krav = new int[12];
    }

   public int[] getKrav()
   {
       Krav[0]= boligtype;   Krav[1] = by;             Krav[2] = rom;      Krav[3] = parkering;
       Krav[4] = antEtasjer; Krav[5] = kjeller;        Krav[6] = heis;     Krav[7] = balkong;
       Krav[8] = delerBadMed;Krav[9] = delerKjøkkenMed;Krav[10] = minPris; Krav[11] = maxPris;

       return Krav;
   }
   public String getNavn()
   {
       return super.getFornavn() + " " + super.getEtternavn();
   }
   public String getId(){
       return super.getId();
   }

   public String[] tilTabell()
   {
       String[] ut = new String[6];

       ut[0] = super.getFornavn();
       ut[1] = super.getEtternavn();
       ut[2] = super.getAdresse();
       ut[3] = super.getEmail();
       ut[4] = super.getTlf();
       ut[5] = "";

       return ut;
   }

    public String[] tilTabellMedId()
    {
        String[] ut = new String[6];

        ut[0] = super.getId();
        ut[1] = super.getFornavn();
        ut[2] = super.getEtternavn();
        ut[3] = super.getAdresse();
        ut[4] = super.getEmail();
        ut[5] = super.getTlf();

        return ut;
    }

   public String toString()
   {
       return super.toString() + " pikk";
   }

}

//Visualisering av kravArray:
// krav = {by, rom, minPris, maksPris, prakering, antEtasjer, kjeller, minTomt, maxTomt, heis, balkong, delerBadMed, delerKjøkkenMed}
    /*
    0 Boligtype
    1 by
    2 rom
    3 minPris
    4 maxPris
    5 parkering
    6 antEtasjer
    7 kjelle
    8 heis
    9 balkong
    10 delerBadMed
    11 delerKjøkkenMed



    Boligtyper:
    1 Enebolig
    2 Rekkehus
    3 Leilighet
    4 Hybel
    */
