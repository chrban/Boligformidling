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

    public Boligsøker(String fn, String en, String a, String t, String e, int blgtp, int b, int r, int
                      map, int mip, int p, int ae, int k, int h,
                      int blkng, int dbm, int dkm )
    {
        super(fn, en, a, t, e);

        // krav
        boligtype = blgtp; by = b; rom = r; minPris = mip; maxPris = map; parkering = p; antEtasjer = ae;
        kjeller = k; heis = h; balkong = blkng;
        delerBadMed = dbm; delerKjøkkenMed= dkm;
        Krav = new int[11];
    }

   public int[] getKrav()
   {
       Krav[0]= boligtype;  Krav[1] = by;          Krav[2] = rom;        Krav[3] = minPris;
       Krav[4] = maxPris;   Krav[5] = parkering;   Krav[6] = antEtasjer; Krav[7] = kjeller;
       Krav[8] = heis;       Krav[9] = balkong;    Krav[10] = delerBadMed; Krav[11] = delerKjøkkenMed;

       return Krav;
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
