import java.io.Serializable;

/**
 * Created by mac on 02.04.14.
 */

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
   /*
    public datatype[] specs()
    {
        returner array med spesifikasjoner om boligen(både fra super- og subklassen) for sammenlikning med boligsøkers krav.
    }
    */

    }


}
