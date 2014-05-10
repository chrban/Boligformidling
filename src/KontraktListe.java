import javax.swing.*;
import java.io.Serializable;

public class KontraktListe implements Serializable {
    private Kontrakt første;
    private Boligliste boliger;
    private BoligsøkerListe søkere;

    public KontraktListe(Boligliste b, BoligsøkerListe s){
        første = null;
        boliger = b;
        søkere = s;

    }

    public boolean leggTil(Kontrakt ny){
        if(ny == null)
            return false;

        Kontrakt løper = første;

        if ( første == null ){
            første = ny;
            ny.setTilUtleid();
            ny.setLeietaker();
            return true;
        }
        else{

            while( løper.neste != null )
                løper = løper.neste;
            løper.neste = ny;
            ny.setTilUtleid();
            ny.setLeietaker();
            return true;
        }
    }
    public boolean fjernKontrakt(Boligsøker b){

        if(første == null)
            return false;
        Bolig bo = null;

        Kontrakt løper = første;
        if(første != null && første.getBoligsøker() == b){
            if(første.neste != null){
                bo = første.getBolig();
                bo.setTilIkkeUtleid();
                første = løper.neste;
                return true;
            }else{
                bo = første.getBolig();
                bo.setTilIkkeUtleid();
                første = null;
                return true;
            }
        }

        while(løper != null){
            if(løper.neste.getBoligsøker() == b){
                bo = løper.neste.getBolig();
                bo.setTilIkkeUtleid();
                løper.neste = løper.neste.neste;
                return true;
            }
            løper = løper.neste;
        }
        return false;
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
