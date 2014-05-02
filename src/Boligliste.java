import java.io.Serializable;
import java.util.*;

public class Boligliste implements Serializable {

    private SortedSet<Enebolig> eneboliger;
    private SortedSet<Leilighet> leiligheter;
    private SortedSet<Hybel> hybler;
    private SortedSet<Rekkehus> rekkehus;


   // private SortedSet<? extends Bolig> boliger;


    public Boligliste()
    {
        eneboliger = new TreeSet<>();
        leiligheter = new TreeSet<>();
        hybler = new TreeSet<>();
        rekkehus = new TreeSet<>();
    }

    public SortedSet<Enebolig> getEneboliger(){return eneboliger;}

    public void leggTil(Enebolig e)// kan dette gå?
    {
        eneboliger.add(e);
    }

    public void leggTil(Rekkehus r)// kan dette gå?
    {
        rekkehus.add(r);
    }

    public void leggTil(Leilighet l)// kan dette gå?
    {
        leiligheter.add(l);
    }

    public void leggTil(Hybel h)// kan dette gå?
    {
        hybler.add(h);
    }

    public String[] matchPåKrav(int[] krav)
    {
        String ut[];
        ut = new String[eneboliger.size()];
        int plass = 0;
        int[] specs;
        int matches = 0;


        // enebol
        if(krav[0] == 1)
        {
            Iterator<Enebolig> iter = eneboliger.iterator();
            while(iter.hasNext())// gjønogang av listen
            {
                Enebolig enebolig = iter.next();
                specs = enebolig.getSpecArray(); // hva nå med den første? siden vi bruker next..
                if(krav[10] < specs[10] && krav[11]> specs[10] )// dette er ikke idiotsikkert.
                {
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if(specs[i] == krav[i])
                        {
                            matches++;
                        }
                    }
                    if(matches >= 4)
                    {
                        ut[plass++] += enebolig.toString();
                    }
                }
            }
        }

        // rekkehus
        if(krav[0] == 2)
        {
            Iterator<Rekkehus> iter = rekkehus.iterator();
            while(iter.hasNext())
            {
                Rekkehus rekkehus = iter.next();
                specs = rekkehus.getSpecArray();
                if(krav[10] < specs[10] && krav[11]> specs[10])
                {
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if(specs[i] == krav[i])
                        {
                            matches++;
                        }
                    }
                    if(matches >= 4)
                    {
                        ut[plass++] += rekkehus.toString();
                    }
                }
            }
        }

        // leileiheiget
        if(krav[0] == 3)
        {
            Iterator<Leilighet> iter = leiligheter.iterator();
            while (iter.hasNext())
            {
                Leilighet leilighet = iter.next();
                specs = leilighet.getSpecArray();
                if (krav[10] < specs[10] && krav[11] > specs[10])
                {
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if (specs[i] == krav[i]) {
                            matches++;
                        }
                    }
                    if (matches >= 4) {
                        ut[plass++] += leilighet.toString();
                    }
                }
            }
        }

        // hyble
        if(krav[0] == 2) {
            Iterator<Hybel> iter = hybler.iterator();
            while (iter.hasNext()) {
                Hybel hybel = iter.next();
                specs = hybel.getSpecArray();
                if (krav[10] < specs[10] && krav[11] > specs[10]) {
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if (specs[i] == krav[i]) {
                            matches++;
                        }
                    }
                    if (matches >= 4) {
                        ut[plass++] += hybel.toString();
                    }
                }
            }
        }
            return ut;
    }


    public void finnBolig()
    {

    }

    public void duvet()
    {

    }

    /*public TreeSet<? extends Bolig> getBoligerAvType(int  t)
    {

    }
*/
}
