import java.io.*;

public class Hybel extends Bolig implements Serializable {
    private int badDelesMed;
    private int kjøkkenDelesMed;
    private int[] specArray;

    public Hybel(){

    }

    public Hybel(String ad,int s, int b, int r, int by, int u,int id, String sti, int bad, int kj) {
        super(ad, s, b, r, by, u, id, sti);
        badDelesMed= bad;
        kjøkkenDelesMed = kj;
    }


    public int getBadDelesMed(){
        return badDelesMed;
    }

    public int getKjøkkenDelesMed(){
        return kjøkkenDelesMed;
    }

    public int[]getSpecArray(){
        specArray = new int[Konstanter.SPEC_LENGDE];
        specArray[1] = super.getSted();
        specArray[2] = super.getRom();
        specArray[3] = Konstanter.URELEVANT;
        specArray[4] = Konstanter.URELEVANT;
        specArray[5] = Konstanter.URELEVANT;
        specArray[6] = Konstanter.URELEVANT;
        specArray[7] = Konstanter.URELEVANT;
        specArray[8] = badDelesMed;
        specArray[9] = kjøkkenDelesMed;
        specArray[10] = super.getUtleiepris();

        return specArray;
    }

    public String[] tilTabell()
    {
        String[] ut = new String[8];

        ut[0] = sted();
        ut[1] = getBoareal() + " m²";
        ut[2] = getUtleiepris() + " kr/m";
        ut[3] = getAdresse();
        ut[4] = Integer.toString(super.getRom());
        ut[5] = Integer.toString(badDelesMed);
        ut[6] = Integer.toString(kjøkkenDelesMed);
        ut[7] = super.getBildesti();

        return ut;
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

    public String toString(){
        return super.toString() + "Bad deles med: " + badDelesMed + "\nKjøkken deles med: " + kjøkkenDelesMed + "\n";
    }
}
