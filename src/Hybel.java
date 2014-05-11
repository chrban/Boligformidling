import java.io.*;

public class Hybel extends Bolig implements Serializable {
    private int egetBad;
    private int egetKjøkken;
    private int[] specArray;
    private static int id = 3000;

    public Hybel(){

    }

    public Hybel(String ad,int s, int b, int r, int by, int u, String uid, String sti, int bad, int kj, String be) {
        super(ad, s, b, r, by, u, uid, sti, be);
        egetBad= bad;
        egetKjøkken = kj;
    }

    public void setID(int i)
    {
        super.setId(i);
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
        specArray[8] = egetBad;
        specArray[9] = egetKjøkken;
        specArray[10] = super.getUtleiepris();

        return specArray;
    }

    public String[] getUnikArray()
    {
        String[] unik = new String[4];

        unik[0] = getEierID();
        unik[1] = getAdresse();
        unik[2] = Integer.toString(getBoareal());
        unik[3
                ] = Integer.toString(getUtleiepris());

        return unik;
    }

    public Object[] tilTabell()
    {
        Object[] ut = new Object[9];

        ut[0] = sted();
        ut[1] = getBoareal() + " m²";
        ut[2] = getUtleiepris() + " kr/m";
        ut[3] = getAdresse();
        ut[4] = Integer.toString(super.getRom());
        ut[5] = getBooleanVerdiBad();
        ut[6] = getBooleanVerdiKjøkken();
        ut[7] = super.getBildesti();
        ut[8] = super.getId();

        return ut;
    }


    public Object[] tilMatchTabell()
    {
        Object[] ut = new Object[8];

        ut[0] = new Integer(0);
        ut[1] = sted();
        ut[2] = getBoareal() + " m²";
        ut[3] = getUtleiepris() + " kr/m";
        ut[4] = getAdresse();
        ut[5] = Integer.toString(super.getRom());
        ut[6] = getBooleanVerdiBad();
        ut[7] = getBooleanVerdiKjøkken();
        ut[8] = super.getBildesti();
        ut[9] = id;

        return ut;
    }


    public Object[] tilEnkelTabell()
    {
        Object[] ut = new Object[4];

        ut[0] = super.getId();
        ut[1] = sted();
        ut[2] = "Enebolig";
        ut[3] = getAdresse();

        return ut;
    }



    public boolean getBooleanVerdiBad()
    {
        if(egetBad == 1)
            return true;

        return false;
    }

    public boolean getBooleanVerdiKjøkken()
    {
        if(egetKjøkken == 1)
            return true;

        return false;
    }

    public boolean erLik(String[] andre)
    {
        String [] specs = getUnikArray();
        int lik = 0;

        for(int i = 0; i <= 3; i++)
            if(andre[i].equals(specs[i]))
                lik++;

        if(lik == 4)
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

    public String toString(){
        return super.toString() + "Eget Bad: " + egetBad + "\nEget kjøkken: " + egetKjøkken+ "\n";
    }
}
