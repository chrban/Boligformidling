import java.util.*;

/**
 * Created by Kristoffer on 16.04.2014.
 */
public class Boligliste {
    private SortedSet<Enebolig> eneboliger;
    private SortedSet<Leilighet> leiligheter;


    private Boligliste()
    {
        eneboliger = new TreeSet<>(); // er dette riktig?
    }
}
