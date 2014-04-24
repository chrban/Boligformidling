import java.io.*;
import java.util.Comparator;

/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Enebolig extends Bolig implements Serializable
{
    private int etasjer;
    private boolean harKjeller;
    private int tomtstørrelse;

    public Enebolig(){}

    public Enebolig(String ad, int b, int r, int by, int u, int e, boolean k, int t)
    {
        super(ad, b, r, by, u);
        etasjer = e;
        harKjeller = k;
        tomtstørrelse = t;
    }

    public int compareTo(Enebolig b)
    {
        return super.compareTo(b);
    }


   /*
    public datatype[] specs()
    {
        returner array med spesifikasjoner om boligen(både fra super- og subklassen) for sammenlikning med boligsøkers krav.
    }
    */

}
