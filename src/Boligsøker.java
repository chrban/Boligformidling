import java.io.Serializable;

/**
 * Created by Kristoffer on 04.04.2014.
 */
public class Boligsøker extends Person implements Serializable
{
    int[] Krav;

    Boligsøker neste;

    // datafelt for krav
    int boligtype, by, rom, minPris, maxPris, parkering, antEtasjer, kjeller, minTomt,
        maxTomt, heis, balkong, delerBadMed, delerKjøkkenMed;


    public Boligsøker (){}

    public Boligsøker(String n, String a, String t, String e, int blgtp, int b, int r, int
                  map, int mip, int p, int ae, int k, int mit, int mat, int h,
                  int blkng, int dbm, int dkm )
    {
        super(n, a, t, e);

        // krav
        boligtype = blgtp; by = b; rom = r; minPris = mip; maxPris = map; parkering = p; antEtasjer = ae;
        kjeller = k; minTomt = mit; maxTomt = mat; heis = h; balkong = blkng;
        delerBadMed = dbm; delerKjøkkenMed= dkm;
        Krav = new int[13];
    }

   public int[] getKrav()
   {
       Krav[0]= boligtype;  Krav[0] = by;          Krav[1] = rom;        Krav[2] = minPris;
       Krav[3] = maxPris;   Krav[4] = parkering;   Krav[5] = antEtasjer; Krav[6] = kjeller;
       Krav[7] = minTomt;   Krav[8] = maxTomt;     Krav[9] = heis;       Krav[10] = balkong;
       Krav[11] = delerBadMed; Krav[12] = delerKjøkkenMed;

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
    0 by
    1 rom
    2 minPris
    3 maxPris
    4 parkering
    5 antEtasjer
    6 kjeller
    7 minTomt
    8 maxTomt
    9 heis
    10 balkong
    11 delerBadMed
    12 delerKjøkkenMed
    */
