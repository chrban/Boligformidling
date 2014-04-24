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

   /* public <? extends Bolig> void leggTilBolig(Bolig b)// kan dette gå?
    {


    }*/

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
