import javax.swing.*;
import java.io.*;

public class BoligsøkerListe implements Serializable {
    private Boligsøker første;

    public BoligsøkerListe() {
        første = null;
    }

    public boolean settInnNy(Boligsøker b) {

        if (b == null)
            return false;

        Boligsøker løper = første;

        while (løper != null) {
            if (løper.getFornavn().equals(b.getFornavn()) && løper.getEtternavn().equals(b.getEtternavn())) {
                JOptionPane.showMessageDialog(null, b.toString() + "\n finnes fra før");
                return false;
            }
            løper = løper.neste;
        }

        if (første == null) {
            første = b;
            return true;
        } else {
            løper = første;
            while (løper.neste != null)
                løper = løper.neste;
            løper.neste = b;

            return true;

        }
    }
    public boolean fjernSøker(Boligsøker b) {

        Boligsøker løper = første;

        if (første != null && første.getId().equals(b.getId())) {
            if (løper.neste != null) {
                første = løper.neste;

                return true;
            } else {
                første = null;
                return true;
            }
        }
        while (løper != null) {
            if (løper.neste != null && løper.neste.getId().equals(b.getId())) {
                løper.neste = løper.neste.neste;
                return true;
            }
            løper = løper.neste;
        }
        return false;
    }
    public String finnBoligsøkerID(String n, String e){
        if(første == null)
            return "";
        Boligsøker løper = første;
        while(løper != null){
            if(løper.getFornavn().equals(n) && løper.getEtternavn().equals(e)){
                return løper.getId();
            }
            løper = løper.neste;
        }
        return "";
    }
    public Boligsøker getBoligsøker(String i)
    {
        Boligsøker løper = første;
        System.out.println("leter etter boligsøker");

        if(løper == null)
            return null;

        while( løper != null )
        {
            if( løper.getId().equals(i))
                return løper;
            løper = løper.neste;


        }
        return null;
    }
    public int[] getKravPåId(String inn)
    {
        Boligsøker løper = første;
        System.out.println("get krav på id: "+inn);

        if(løper == null)
            return null;

        while(løper != null)
        {
            if(løper.getId().equals(inn))
                return løper.getKrav();

            løper = løper.neste;
        }
        return null;
    }
    public String[][] tilTabell()
    {
        Boligsøker løper = første;
        String[][] ut = new String[tellOpp()][6];
        int i = 0;

        if(løper == null)
            return ut;

        while(løper != null)
        {
            ut[i++] = løper.tilTabell();
            løper = løper.neste;
        }
        return ut;
    }
    public String[][] tilTabellMedId()
    {
        Boligsøker løper = første;
        String[][] ut = new String[tellOpp()][6];
        int i = 0;

        if(løper == null)
            return ut;

        while(løper != null)
        {
            ut[i++] = løper.tilTabellMedId();
            løper = løper.neste;
        }
        return ut;
    }
    public String[][] tilMatchTabll(){
        Boligsøker løper = første;
        int i = 0;
        while( løper != null ){
            if(!løper.harBolig())
                i++;
            løper=løper.neste;
        }
        Boligsøker runner = første;
        String[][] ut = new String[i][6];
        int x = 0;
        while(runner != null){
            if(!runner.harBolig())
                ut[x++] = runner.tilTabellMedId();
            runner = runner.neste;
        }
        return ut;
    }
    public int tellOpp()
    {
        Boligsøker løper = første;
        int i = 0;
        if( løper == null)
            return i;
        while(løper != null)
        {
            i++;
            løper = løper.neste;
        }
        return i;
    }
    public String toString() {
        String info = "";
        Boligsøker løper = første;

        while (løper != null) {
            info += løper.toString() + "\n";
            løper = løper.neste;
        }
        if (!info.equals(""))
            return info;
        else
            return "Ingen boligsøkere registrert!";
    }

}




