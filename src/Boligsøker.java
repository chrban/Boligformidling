import java.io.Serializable;

/*
Filen inneholder datafelt og metoder for klassen Boligsøker
Skrevet av: Kristoffer, Christer og Emil
Siste versjon:
 */
public class Boligsøker extends Person implements Serializable
{
    int[] Krav;
    Boligsøker neste;
    // datafelt for krav
    private int boligtype, by, rom, minPris, maxPris, parkering, antEtasjer, kjeller,
        heis, balkong, delerBadMed, delerKjøkkenMed;
    private boolean harBolig;

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
        harBolig = false;
    }
    //Start of getMetoder
    //getKrav legger alle kravene en boligsøker har om en bolig i en Int-array og returnerer den
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
    //end of getMetoder
    //Returnerer en String-array med informasjon om en boligsøker
    public String[] tilTabell()
    {
       String[] ut = new String[6];

       ut[0] = super.getFornavn();
       ut[1] = super.getEtternavn();
       ut[2] = super.getAdresse();
       ut[3] = super.getEmail();
       ut[4] = super.getTlf();
       ut[5] = "-";

       return ut;
   }
    //Returnerer en String-array med informasjon om en boligsøker + ID
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
    public void setBolig(){
        harBolig = true;
    }
    public boolean harBolig(){
        return harBolig;
    }
    //Endrer int-verdier til riktig String verdi
    private String transform(int[]i)
    {
        String ut = "Søker etter ";
        int btype = i[0];
            switch(btype) {
                case 1:
                    ut += "enebolig";
                    break;
                case 2:
                    ut += "rekkehus";
                    break;
                case 3:
                    ut += "leilighet";
                    break;
                case 4:
                    ut += "hybel";
                    break;
                default:
                    ut += "-ikke valgt-";
                    break;
            }
            ut += " i den fine byen ";
            int by = i[1];
            switch(by){
                case 1: ut+= "Oslo";
                    break;
                case 2: ut+=  "Bergen";
                    break;
                case 3: ut+= "Stavanger";
                    break;
                case 4: ut+= "Trondheim";
                    break;
                case 5 :ut+= "Kristiansand";
                    break;
                case 6:ut+=  "Tromsø";
                    break;
                default: ut+=  "-ikke valgt-";
                    break;
            }

        ut += " i prisklassen " + minPris + " til " + maxPris;
        return ut;
        }
    public String toString()
     {
        return super.toString() +
                "\nBoligsøker ID: "+getId()+
                "Adresse: " + getAdresse()+
                "\nMail: " + getEmail()+
                "\nTlf: " + getTlf()+
                "\nKrav: " + transform(getKrav());
    }
}//end of class Boligsøker

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
