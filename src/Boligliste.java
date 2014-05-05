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

    public String[][] matchPåKrav(int[] krav)
    {
        String ut[][];
        ut = new String[eneboliger.size()][2];
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
                        ut[plass][0] = enebolig.toString();
                        ut[plass++][1] = enebolig.getBildesti();
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
                        ut[plass][0] = rekkehus.toString();
                        ut[plass++][1] = rekkehus.getBildesti();
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
                    for(int i = 1; i <= 2; i++)// av krav
                        if (specs[i] == krav[i])
                            matches++;

                    for(int i =6; i <= 8;i++)
                        if(specs[i] == krav[i])
                            matches++;

                    if (matches >= 3) {
                        ut[plass][0] = leilighet.toString();
                        ut[plass++][1] = leilighet.getBildesti();
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
                        ut[plass][0] = hybel.toString();
                        ut[plass++][1] = hybel.getBildesti();
                    }
                }
            }
        }
            return ut;
        /*todo
        - hvem skal det matches på? hvordan velger vi hvilken boligsøker vi skal finne boliger til?
            - Velge fra liste i gui?(liker denne best, sikkert mulig å få til med JTable)
            - skrive inn kundenummer eller noe?

        - Finne boligsøker og få tak i kravene
        - test på boligtype
            - Hent liste til riktig boligtype, mekk iterator
            - Løkke(For gjennomgang av boliglisten)
                - Løkke(For gjennomløping av krav og specs)
                    - Hvis match, legg til poeng
                    - hvis null, reg som blankt felt

                - kalkuler matchkoefisient og lagre den et sted. Hvor? jeg vet ikke.

            Må på en måte skrive ut en sortert liste av boliger som har høy nok matchkoefisient.
                - Kan lagre koefisienten i boligobjektet og endre den naturlige sorteringen til å ta hensyn på
                  koefisienten, ikke på prisen.
                - Kan lagre bolignr og score i todimmensjonell array, sortere den på score og så lage en turskrift ved
                  å kalle opp toString for hver bolig i rekkefølgen definert av arrayen.




     */
    }

    public String[][] eneboligerTilTabell()
    {
        String[][] ut = new String[eneboliger.size()][8];
        Enebolig enebolig;
        Iterator<Enebolig> iter = eneboliger.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            enebolig = iter.next();
            ut[i++] = enebolig.tilTabell();
        }
        return ut;
    }
    /* public String[][] tilTabell()
    {
        String[][] ut = new String[liste.size()][6];
        Utleier utleier;
        Iterator<Utleier> iter = liste.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            utleier = iter.next();
            ut[i++] = utleier.tilTabell();
        }
        return ut;
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
