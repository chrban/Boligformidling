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
    private static int id = 2000;

    public Leilighet(){}

    public Leilighet(String ad,int s, int b, int r, int by, int u, String uid, String sti, int e, int bal, int h)
    {
        super(id++,ad,s, b, r, by, u, uid, sti);
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

    public Object[] tilTabell()
    {
        Object[] ut = new Object[9];

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


    public Object[] tilMatchTabell()
    {
        Object[] ut = new Object[10];

        ut[0] = new Integer(0);
        ut[1] = sted();
        ut[2] = getBoareal() + " m²";
        ut[3] = getUtleiepris() + " kr/m";
        ut[4] = getAdresse();
        ut[5] = Integer.toString(super.getRom());
        ut[6] = getBooleanVerdiBalkong();
        ut[7] = getBooleanVerdiHeis();
        ut[8] = super.getBildesti();
        ut[9] = id;

        return ut;
    }


    public Object[] tilEnkelTabell()
    {
        Object[] ut = new Object[4];

        ut[0] = super.getId();
        ut[1] = sted();
        ut[2] = "Leilighet";
        ut[3] = getAdresse();

        return ut;
    }

    public String[] getUnikArray()
    {
        String[] unik = new String[5];

        unik[0] = getEierID();
        unik[1] = getAdresse();
        unik[2] = Integer.toString(etasje);
        unik[3] = Integer.toString(getBoareal());
        unik[4] = Integer.toString(getUtleiepris());

        return unik;
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
