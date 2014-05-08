import javax.swing.*;
import java.io.*;

public class BoligsøkerListe implements Serializable {
    private Boligsøker første;

    public BoligsøkerListe() {
        første = null;
        Boligsøker en = new Boligsøker("OSKR", "Kristoffer Gard","Osen", "Nedeoverbakkeveien 63b", "815 493 00", "hotmale92@hotmail.com", 3, 3, 3,
        100000, 2000, -1, 2, 1, 0,
        0, 0, 0 );
        Boligsøker to = new Boligsøker("BACH", "Chister","Banks", "Oppoverbakkeveien 63b", "815 493 00", "hotdame92@hotmail.com", 3, 3, 3,
                100000, 2000, -1, 2, 1, 0,
                0, 0, 0 );
        settInnNy(en);
        settInnNy(to);
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


    public int[] getKravPåId(String inn)
    {
        Boligsøker løper = første;

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

    public boolean fjernSøker(String n) {

        Boligsøker løper = første;

        if (første != null && første.getFornavn().equals(n)) {
            if (løper.neste != null) {
                første = løper.neste;

                return true;
            } else {
                første = null;
                return true;
            }
        }
        while (løper != null) {
            if (løper.neste != null && løper.neste.getFornavn().equals(n)) {
                løper.neste = løper.neste.neste;
                return true;
            }
            løper = løper.neste;
        }
        return false;
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
            if(!løper.harBolig())
                ut[i++] = løper.tilTabellMedId();
            løper = løper.neste;
        }
        return ut;
    }

    public int tellOpp()
    {
        Boligsøker løper = første;
        int i = 0;
        if( løper == null)
            return i;
        if(løper.neste == null)
            return 1;
        while(løper != null)
        {
            if(!løper.harBolig())
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
    public void setBoligPåBoligsøker(Boligsøker b){
        Boligsøker løper = første;
        while( løper != null ){
            if ( løper.getId() ==  b.getId() ) {
                løper.setBolig();
                return;
            }
            løper = løper.neste;
        }
    }

    /*//todo Christer, finn riktig måte din maddafakka
    public Object[][] fyllTabell()
    {
        Boligsøker runner = første;
        int teller = 0;

        while (runner != null) {
            teller++;
            runner = runner.neste;
        }


        int i = 0;
        int k = 0;

        Boligsøker løper = første;
        Object[][] personCeller = new Object[teller][2];

        System.out.println("teller: "+teller);


        while(løper != null) {
            personCeller[i][k++] = løper.getFornavn();
            personCeller[i][k++] = løper.getEtternavn();
            personCeller[i][k] = løper.getEmail();
            k++;
            i++;


        }//end while

        return personCeller;

    }//end fylla
    */
}




