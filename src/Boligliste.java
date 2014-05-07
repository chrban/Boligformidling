import javax.swing.*;
import java.io.Serializable;
import java.text.DecimalFormat;
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

        Enebolig en = new Enebolig("Adresse",1,1, 1, 2000, 2000, 123, "bildesti",  1, -1, -1, 10);
        Enebolig to = new Enebolig("Adresse",1,1, 1, 2000, 2000, 123, "bildesti",  1, -1, -1, 10);
        Enebolig tre = new Enebolig("Adresse",2,1, 1, 2000, 2000, 123, "bildesti",  1, -1, -1, 10);
        Enebolig fire = new Enebolig("Adresse",2,1, 1, 2000, 2000, 123, "bildesti",  1, -1, -1, 10);
        System.out.println( en.getId() + " " + to.getId()+" ");

        if(leggTil(en))
            System.out.println("lagt til en");

        if(leggTil(to));
            System.out.println("lagt til to");

        if(leggTil(tre));
             System.out.println("lagt til tre");

        if(leggTil(fire));
            System.out.println("lagt til fire");

        System.out.println(eneboliger.size());
    }

    public SortedSet<Enebolig> getEneboliger(){return eneboliger;}

    public boolean leggTil(Enebolig e)// kan dette gå?
    {
        System.out.println("legger til enebolig");
        return eneboliger.add(e);

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

    public Object[][] matchPåKrav(int[] krav)
    {

        Object[][] ut = new Object[eneboliger.size()][10];
        int plass = 0;
        int[] specs;



        // enebol
        if(krav[0] == 1)
        {
            JOptionPane.showMessageDialog(null,"enebolig togla inni boligliste matchPåKrav");
            Iterator<Enebolig> iter = eneboliger.iterator();
            while(iter.hasNext())// gjønogang av listen
            {
                Enebolig enebolig = iter.next();
                specs = enebolig.getSpecArray(); // hva nå med den første? siden vi bruker next..
                if(krav[10] < specs[10] && krav[11]> specs[10] )// dette er ikke idiotsikkert.
                {
                    JOptionPane.showMessageDialog(null,"Inni idiotsikkerTestenTilOsen");
                    double matchkoeffisient;
                    double matches = 0;
                    double urelevante = 0;

                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if(krav[i] == 0)
                            urelevante++;

                        JOptionPane.showMessageDialog(null, "Krav nr " + i + "\n Bolig har: " + specs[i] +"\n Krav sier: " + krav[i]);
                        if(specs[i] == krav[i])
                        {
                            matches++;
                            JOptionPane.showMessageDialog(null,"Matches: " +
                                    "" + matches);
                        }
                    }
                    if(matches >= (4-urelevante))
                    {
                        DecimalFormat df = new DecimalFormat("0.00");
                        matchkoeffisient = matches/(5-urelevante);
                        ut[plass] = enebolig.tilMatchTabell();
                        //int tull = 5-urelevante;
                       // JOptionPane.showMessageDialog(null, enebolig.toString()+"\n" +
                         //       "" + matches + "/" + tull );
                        ut[plass++][0] = df.format(matchkoeffisient);
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
                    int matchkoeffisient;
                    int matches = 0;
                    for (int i = 1; i <= 5; i++)// av krav
                    {

                        if(specs[i] == krav[i])
                        {
                            matches++;
                        }
                    }
                    if(matches >= 4)
                    {
                        matchkoeffisient = matches/5;
                        ut[plass] = rekkehus.tilMatchTabell();
                        ut[plass++][0] = matchkoeffisient;
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
                    int matchkoeffisient;
                    int matches = 0;

                    for(int i = 1; i <= 2; i++)// av krav
                        if (specs[i] == krav[i])
                            matches++;

                    for(int i =6; i <= 8;i++)
                        if(specs[i] == krav[i])
                            matches++;

                    if (matches >= 3) {
                        matchkoeffisient = matches/4;
                        ut[plass] = leilighet.tilMatchTabell();
                        ut[plass++][0] = matchkoeffisient;
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
                    int matchkoeffisient;
                    int matches = 0;
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if (specs[i] == krav[i]) {
                            matches++;
                        }
                    }
                    if (matches >= 4) {
                        matchkoeffisient = matches/5;
                        ut[plass] = hybel.tilMatchTabell();
                        ut[plass++][0] = matchkoeffisient;
                    }
                }
            }
        }
        /*Object[][] temp = ut;
        double høyeste = 0;
        int plass = 0;
        for(int i = 0; i < plass; i++)
        {

            if(ut[i][0] <= høyeste)
                temp[]

        }*/



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
        System.out.println("Lengden på eneboliger i tilTabellmetoden er : " + eneboliger.size());

        Enebolig enebolig;
        Iterator<Enebolig> iter = eneboliger.iterator();
        int i = 0;


        while(iter.hasNext())
        {
            enebolig = iter.next();
            ut[i++] = enebolig.tilTabell();
            System.out.println(i);
        }
        System.out.println(eneboliger.size() + "er lwngden på eneboliger som blir retrunert fra listen");
        return ut;
    }

    public String[][] rekkehusTilTabell()
    {
        String[][] ut = new String[rekkehus.size()][8];
        Rekkehus rekkehuset;
        Iterator<Rekkehus> iter = rekkehus.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            rekkehuset = iter.next();
            ut[i++] = rekkehuset.tilTabell();
        }
        return ut;
    }

    public String[][] leiligheterTilTabell()
    {
        String[][] ut = new String[leiligheter.size()][8];
        Leilighet leilighet;
        Iterator<Leilighet> iter = leiligheter.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            leilighet = iter.next();
            ut[i++] = leilighet.tilTabell();
        }
        return ut;
    }

    public String[][] hyblerTilTabell()
    {
        String[][] ut = new String[hybler.size()][8];
        Hybel hybel;
        Iterator<Hybel> iter = hybler.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            hybel = iter.next();
            ut[i++] = hybel.tilTabell();
        }
        return ut;
    }

    public Object[][] alleBoligerTilEnkelTabell()
    {
        int lengde = eneboliger.size() + rekkehus.size()+leiligheter.size()+hybler.size();
        Object[][] ut = new Object[lengde][4];

        Enebolig enebolig;
        Iterator<Enebolig> eiter = eneboliger.iterator();
        int i = 0;

        while(eiter.hasNext())
        {
            enebolig = eiter.next();
            ut[i++] = enebolig.tilEnkelTabell();
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

    public Bolig finnBolig(String inn)
    {
        System.out.println("leter etter boligsøker");
        int id = 0;
        try{
            id = Integer.parseInt(inn);
        }
        catch (NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Noe er galt med boligvalget ditt :(");
            System.out.println("det fucket seg i parsingen");
            return null;
        }


        Iterator<Rekkehus> it = rekkehus.iterator();
        Rekkehus re = null;
        Iterator<Enebolig> ite = eneboliger.iterator();
        Enebolig en = null;
        Iterator<Hybel> iter = hybler.iterator();
        Hybel hy = null;
        Iterator<Leilighet> iterat = leiligheter.iterator();
        Leilighet lei = null;

        while(it.hasNext() || ite.hasNext() || iter.hasNext() || iterat.hasNext()){
            if(it.hasNext())
                re = it.next();
            if(ite.hasNext())
                en = ite.next();
            if(iter.hasNext())
                hy = iter.next();
            if(iterat.hasNext())
                lei = iterat.next();

            if (re != null && re.getId()== id )
                return re;
            else if (en != null && en.getId() == id)
                return en;
            else if (hy != null && hy.getId() == id)
                return hy;
            else if (lei != null && lei.getId() == id)
                return lei;
        }
        return null;
    }

    public void duvet()
    {

    }

    /*public TreeSet<? extends Bolig> getBoligerAvType(int  t)
    {

    }
*/
}
