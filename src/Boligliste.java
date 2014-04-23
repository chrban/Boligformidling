import java.io.Serializable;
import java.util.*;
/**
 * Created by Kristoffer on 23.04.2014.
 */
public class Boligliste implements Serializable {

    private SortedSet<Enebolig> eneboliger;
    private SortedSet<Leilighet> leiligheter;

    private SortedSet<? extends Bolig> boliger;

    public Boligliste()
    {
        boliger = new TreeSet<>();
    }
}
