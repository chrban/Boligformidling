import java.io.*;

/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Leilighet extends Bolig implements Serializable
{
    private int etasje;
    private int balkong;
    private int heis;
    private int[] specArray;

    public Leilighet(){}

    public Leilighet(String ad,int s, int b, int r, int by, int u,int id, String sti, int e, int bal, int h)
    {
        super(ad,s, b, r, by, u, id, sti);
        etasje = e;
        balkong = bal;
        heis = h;
    }

    public int getEtasje(){
        return etasje;
    }
    public int getBalkong(){
        return balkong;
    }
    public int getHeis(){
        return heis;
    }

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

    public String[] tilTabell()
    {
        String[] ut = new String[8];

        ut[0] = sted();
        ut[1] = getBoareal() + " m²";
        ut[2] = getUtleiepris() + " kr/m";
        ut[3] = getAdresse();
        ut[4] = Integer.toString(super.getRom());
        ut[5] = Integer.toString(balkong);
        ut[6] = Integer.toString(heis);
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

}
