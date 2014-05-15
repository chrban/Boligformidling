import javax.swing.*;
import java.awt.*;
import java.io.*;
/*
Filen inneholder datafelt og metoder for klassen Leilighet
Skrevet av: Kristoffer, Christer, Emil
Sist versjon:
 */
public class Leilighet extends Bolig implements Serializable
{
    private int etasje;
    private int balkong;
    private int heis;
    private int[] specArray;

    public Leilighet(int i,String ad,int s, int b, int r, int by, int u, String uid, String sti, int e, int bal, int h, String be)
    {
        super(i,ad,s, b, r, by, u, uid, sti, be);
        etasje = e;
        balkong = bal;
        heis = h;
    }
    //getMetoder, noen blir ikke brukt, men lar disse være her til videreutvikling
    public int getEtasje(){
        return etasje;
    }
    public int getBalkong(){
        return balkong;
    }
    public int getHeis(){
        return heis;
    }
    public boolean getBooleanVerdiHeis()
    {
        if(heis ==1)
            return true;

        return false;
    }
    public boolean getBooleanVerdiBalkong()
    {
        if(balkong ==1)
            return true;

        return false;
    }
    public String sted()
    {
        String sted = "";

        switch (super.getSted()) {
            case 1:
                sted = "Oslo";
                break;
            case 2:
                sted = "Bergen";
                break;
            case 3:
                sted = "Stavanger";
                break;
            case 4:
                sted = "Trondheim";
                break;
            case 5:
                sted = "Kristiansand";
                break;
            case 6:
                sted = "Tromsø";
                break;
            default:
                sted = "Ukjent";
                break;
        }
        return sted;
    }
    //returnerer Leilighet-objektes spesifikasjoner i form av en int-array
    public int[] getSpecArray(){// todo legg inn konstanter
        specArray = new int[Konstanter.SPEC_LENGDE];
        specArray[1] = super.getSted();
        specArray[2] = super.getRom();
        specArray[3] = Konstanter.URELEVANT;
        specArray[4] = Konstanter.URELEVANT;
        specArray[5] = Konstanter.URELEVANT;
        specArray[6] = heis;
        specArray[7] = balkong;
        specArray[8] = Konstanter.URELEVANT;
        specArray[9] = Konstanter.URELEVANT;
        specArray[10] = super.getUtleiepris();

        return specArray;
    }
    //returnerer en String-array for å kunne sjekke om Leilighet-objektet er likt andre Leilighet-objekter
    public String[] getUnikArray()
    {
        String[] unik = new String[Konstanter.UNIK_LENGDE_LEILIGHET];

        unik[0] = getEierID();
        unik[1] = getAdresse();
        unik[2] = Integer.toString(etasje);
        unik[3] = Integer.toString(getBoareal());
        unik[4] = Integer.toString(getUtleiepris());

        return unik;
    }
    //end of getMetoder
    //returnerer en Object-array for å kunne vise informasjon om Leilighet-objektet i JTables i GUI
    public Object[] tilTabell()
    {
        Object[] ut = new Object[Konstanter.TIL_TABELL];

        ut[0] = sted();
        ut[1] = getBoareal() + " m²";
        ut[2] = getUtleiepris() + " kr/m";
        ut[3] = getAdresse();
        ut[4] = Integer.toString(super.getRom());
        ut[5] = getBooleanVerdiBalkong();
        ut[6] = getBooleanVerdiHeis();
        ut[7] = super.getBildesti();
        ut[8] = super.getId();

        return ut;
    }
    //returnerer en Objekt-array for å kunne vise inforamsjon om Leilighet-objektet i matchmakingfanen i GUI
    public Object[] tilMatchTabell()
    {
        Object[] ut = new Object[Konstanter.TIL_MATCH];

        ut[0] = 0;
        ut[1] = sted();
        ut[2] = getBoareal() + " m²";
        ut[3] = getUtleiepris() + " kr/m";
        ut[4] = getAdresse();
        ut[5] = Integer.toString(super.getRom());
        ut[6] = getBooleanVerdiBalkong();
        ut[7] = getBooleanVerdiHeis();


        ImageIcon image = new ImageIcon(getBildesti());
        Image img = image.getImage();
        Image skalert = img.getScaledInstance(330, 310, Image.SCALE_SMOOTH);
        image = new ImageIcon(skalert);

        ut[8] = image;
        ut[9] = super.getId();

        return ut;
    }
    //returnerer true eller false om Leilighet-objektet er likt andre Leilighet-objekter
    public boolean erLik(String[] andre)
    {
        String [] specs = getUnikArray();
        int lik = 0;

        for(int i = 0; i <= 4; i++)
            if(andre[i].equals(specs[i]))
                lik++;

        if(lik == 5)
            return true;

        return false;
    }
}//end of class Leilighet
