import java.io.Serializable;

/**
 * Created by Kristoffer on 04.04.2014.
 */
public class Boligsøker extends Person implements Serializable
{
    int[] Krav;









































































    Boligsøker neste;

    public Person (){}

    public Person(String n, String a, String t, String e, int[] k )
    {
        super(n, a, t, e);
        Krav = k;
    }

   public int[] getKrav()
   {
       return Krav;
   }

   public String toString()
   {
       return super.toString() + " pikk";
   }

}
