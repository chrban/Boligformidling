import java.io.Serializable;


public class Rekkehus extends Bolig implements Serializable
{
    private int etasjer;
    private boolean harKjeller;
    private int tomtstørrelse;

    public Rekkehus(){}

    public Rekkehus(String ad, int b, int r, int by, int u, int e, boolean k, int t)
    {
        super(ad, b, r, by, u);
        etasjer = e;
        harKjeller = k;
        tomtstørrelse = t;
    }
    public int compareTo(Rekkehus b)
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
