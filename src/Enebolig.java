/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Enebolig extends Bolig implements serializable
{
    private int etasjer;
    private boolean harKjeller;
    private int tomtstørrelse;

    public Enebolig(){}

    public Enebolig(int e, boolean k, int t)
    {
        super();
        etasjer = e;
        harKjeller = k;
        tomtstørrelse = t;
    }
   /*
    public datatype[] specs()
    {
        returner array med spesifikasjoner om boligen(både fra super- og subklassen) for sammenlikning med boligsøkers krav.
    }
    */
    
}
