import java.io.Serializable;

public class KontraktListe implements Serializable {
    private Kontrakt første;

    public KontraktListe(){
        første = null;

    }

    public boolean leggTil(Kontrakt ny){
        if(ny == null)
            return false;

        Kontrakt løper = første;

        if ( første == null ){
            første = ny;
            return true;
        }
        else{
            while( løper.neste != null )
                løper = løper.neste;
            løper.neste = ny;
            return true;
        }
    }

    public int tellOpp()
    {
        Kontrakt løper = første;
        int i = 0;

        if(løper == null)
            return 1;

        while(løper != null)
        {
            i++;
            løper = løper.neste;
        }

        return i;
    }

    public String toString()
    {
        String ut = "";
        Kontrakt løper = første;

        if(løper == null)
            return "Finner ingen registrerte kontrakter";

        while(løper != null)
        {
            ut += løper.toString();
            løper = løper.neste;
        }

        return ut;
    }

    public String[][] tilTabell()
    {
        String[][] ut = new String[tellOpp()][4];

        Kontrakt løper = første;
        int i = 0;

        if(løper == null)
        {
            ut[0][0] = "Ingen kontrakter lagret";
            return ut;
        }

        while(løper != null)
        {
            ut[i++] = løper.tilTabell();
            løper = løper.neste;
        }
        return ut;
    }
}
