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
        if(krav[0] == 1)
        {
            Iterator<Enebolig> iter = eneboliger.iterator();

            while(iter.hasNext())// gjønogang av listen
            {
                int[] specs = iter.next().getSpecArray();

                if(krav[10] < specs[pris] && krav[11]> specs[pris] ) {

                    for (int i = 1; i < 13; i++)// av krav
                    {


                    }
                }
            }

        }
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
