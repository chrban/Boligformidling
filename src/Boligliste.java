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

        Enebolig en = new Enebolig("Adresse22",11,1, 1, 2000, 206500, "PdIKK", "img/eneboliger/kjipe/rwanda_mudhut.jpg",  1, -1, -1, 10,"pikk");
        Enebolig to = new Enebolig("Adresse22",12,1, 1, 2000, 20600, "PIdKK", "img/eneboliger/swære/Schloss_oslo.jgp",  1, -1, -1, 10, "pikk");
        Enebolig tre = new Enebolig("22",24,15, 13, 2000, 2000, "PIKdK", "img/eneboliger/swære/1067206-7-1306195722399.jgp",  1, -1, -1, 10, "pikk");
        Enebolig fire = new Enebolig("Adredesse",24,51, 51, 255000, 256000, "PIKK", "img/eneboliger/vanlige/hus_drag.jpg",  1, -1, -1, 10,"pikk");
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
    public boolean leggTil(Bolig b){
        if(b instanceof Enebolig){
            if(!erUnik(b)){
                eneboliger.add((Enebolig)b);
                return true;
            }
        }else if(b instanceof Rekkehus){
            if(!erUnik(b)){
                rekkehus.add((Rekkehus)b);
                return true;
            }
        }else if(b instanceof Leilighet){
            if(!erUnik(b)){
                leiligheter.add((Leilighet)b);
                return true;
            }
        }else if(b instanceof Hybel){
            if(!erUnik(b)){
                hybler.add((Hybel)b);
                return true;
            }
        }JOptionPane.showMessageDialog(null,"Bolig er allerde lagt til!");
        return false;
    }
    public boolean slettBolig(Bolig b){
        if(b instanceof Enebolig){
            eneboliger.remove(b);
            return true;
        }else if(b instanceof Rekkehus){
            rekkehus.remove(b);
            return true;
        }else if(b instanceof Leilighet){
            leiligheter.remove(b);
            return true;
        }else if(b instanceof Hybel){
            hybler.remove(b);
            return true;
        }
        return false;
    }

    public boolean erUnik(Bolig b){
        Iterator<? extends Bolig> it = null;
        String[] spec = null;
        Bolig be = null;
        if(b instanceof Enebolig) {
            it = eneboliger.iterator();
            spec = ((Enebolig) b).getUnikArray();
        }
        else if(b instanceof Rekkehus) {
            it = rekkehus.iterator();
            spec = ((Rekkehus) b).getUnikArray();
        }
        else if(b instanceof Leilighet) {
            it = leiligheter.iterator();
            spec = ((Leilighet) b).getUnikArray();
        }
        else {
            it = hybler.iterator();
            spec = ((Hybel) b).getUnikArray();
        }
        while(it.hasNext()){
            be = it.next();
            if(be instanceof Enebolig){
                if(((Enebolig) be).erLik(spec))
                    return true;
            }else if(be instanceof Rekkehus){
                if(((Rekkehus) be).erLik(spec))
                    return true;
            }else if(be instanceof Leilighet){
                if(((Leilighet) be).erLik(spec))
                    return true;
            }else{
                if(((Hybel)be).erLik(spec))
                    return true;
            }

        }
        return false;
    }
    public Object[][] matchPåKrav(int[] krav)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        Object[][] ut = null;
        int plass = 0;
        int[] specs;
        double matches = 0;
        double matchkoeffisient;
        double urelevante = 0;
        int teller = 0;

        // enebol
        if(krav[0] == 1)
        {
            Iterator<Enebolig> iter = eneboliger.iterator();
            while(iter.hasNext())// gjønogang av listen
            {
                Enebolig enebolig = iter.next();
                specs = enebolig.getSpecArray(); // hva nå med den første? siden vi bruker next..
                if(krav[10] < specs[10] && krav[11]> specs[10] && !enebolig.getUtleid() && krav[1] == specs[1])// dette er ikke idiotsikkert.
                {
                    ut = new Object[eneboliger.size()][10];
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if(krav[i] == 0)
                            urelevante++;

                        if(specs[i] == krav[i])
                            matches++;
                    }
                    if(matches >= (4-urelevante))
                    {
                        matchkoeffisient = matches/(5-urelevante);
                        ut[plass] = enebolig.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                        matchkoeffisient = 0;
                        matches = 0;
                        urelevante = 0;
                        teller++;
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
                Rekkehus rekkehuset = iter.next();
                specs = rekkehuset.getSpecArray();
                if(krav[10] < specs[10] && krav[11]> specs[10] && !rekkehuset.getUtleid() && krav[1] == specs[1])
                {
                    ut = new Object[rekkehus.size()][10];
                    for (int i = 1; i <= 5; i++)// av krav
                    {

                        if(specs[i] == krav[i])
                            matches++;
                    }
                    if(matches >= 4)
                    {
                        matchkoeffisient = matches/(5-urelevante);
                        ut[plass] = rekkehuset.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                        matchkoeffisient = 0;
                        matches = 0;
                        urelevante = 0;
                        teller++;
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
                if (krav[10] < specs[10] && krav[11] > specs[10] && !leilighet.getUtleid() && krav[1] == specs[1])
                {
                    ut = new Object[leiligheter.size()][10];
                    for(int i = 1; i <= 2; i++)// av krav
                        if (specs[i] == krav[i])
                            matches++;

                    for(int i =6; i <= 8;i++)
                        if(specs[i] == krav[i])
                            matches++;

                    if (matches >= 3) {
                        matchkoeffisient = matches/(4-urelevante);
                        ut[plass] = leilighet.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                        matchkoeffisient = 0;
                        matches = 0;
                        urelevante = 0;
                        teller++;
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
                if (krav[10] < specs[10] && krav[11] > specs[10] && !hybel.getUtleid() && krav[1] == specs[1]) {
                    ut = new Object[hybler.size()][10];
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if (specs[i] == krav[i])
                            matches++;
                    }
                    if (matches >= 4) {
                        matchkoeffisient = matches/(5-urelevante);
                        ut[plass] = hybel.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                        matchkoeffisient = 0;
                        matches = 0;
                        urelevante = 0;
                        teller++;
                    }
                }
            }
        }
        Object[][] temp = new Object[teller][10];
        for(int i = 0; i < temp.length; i++){
            temp[i] = ut[i];
            temp[i][0] = ut[i][0];
        }
        return temp;
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

    public Object[][] eneboligerTilTabell()
    {
        Object[][] ut = new Object[eneboliger.size()][8];
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

    public Object[][] rekkehusTilTabell()
    {
        Object[][] ut = new Object[rekkehus.size()][8];
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

    public Object[][] leiligheterTilTabell()
    {
        Object[][] ut = new Object[leiligheter.size()][8];
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

    public Object[][] hyblerTilTabell()
    {
        Object[][] ut = new Object[hybler.size()][8];
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

    public String finnUtleier(Bolig b){
        if(b instanceof Enebolig){
            Iterator<Enebolig> iter = eneboliger.iterator();
            while(iter.hasNext()){
                Enebolig enebolig = iter.next();
                if ( enebolig.getId() == b.getId() )
                    return enebolig.getEierID();
            }
        }
        else if(b instanceof Rekkehus){
            Iterator<Rekkehus> iter = rekkehus.iterator();
            while(iter.hasNext()){
                Rekkehus reke = iter.next();
                if( reke.getId() == b.getId())
                    return reke.getEierID();
            }
        }
        else if(b instanceof Leilighet){
            Iterator<Leilighet> iter = leiligheter.iterator();
            while(iter.hasNext()){
                Leilighet lei = iter.next();
                if( lei.getId() == b.getId())
                    return lei.getEierID();
            }
        }
        else{
            Iterator<Hybel> iter = hybler.iterator();
            while(iter.hasNext()){
                Hybel hybel = iter.next();
                if( hybel.getId() == b.getId())
                    return hybel.getEierID();
            }
        }
        return null;
    }


    public boolean harBolig(Utleier b){
        Iterator<Enebolig> ite = eneboliger.iterator();
        Enebolig ene = null;
        Iterator<Rekkehus> iter = rekkehus.iterator();
        Rekkehus reke = null;
        Iterator<Leilighet> it = leiligheter.iterator();
        Leilighet lei = null;
        Iterator<Hybel> iterat = hybler.iterator();
        Hybel hy = null;

        while(ite.hasNext() || iter.hasNext() || it.hasNext() || iterat.hasNext()){
            if(ite.hasNext())
                ene = ite.next();
            if(iter.hasNext())
                reke = iter.next();
            if(it.hasNext())
                lei = it.next();
            if(iterat.hasNext())
                hy = iterat.next();

            if( ene != null && ene.getEierID().equals(b.getId()))
                return true;
            if( reke != null && reke.getEierID().equals(b.getId()))
                return true;
            if( lei != null && lei.getEierID().equals(b.getId()))
                return true;
            if( hy != null && hy.getEierID().equals(b.getId()))
                return true;
        }
        return false;
    }
    /*public TreeSet<? extends Bolig> getBoligerAvType(int  t)
    {

    }
*/
}
