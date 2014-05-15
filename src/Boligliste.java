import javax.swing.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;

/*
Filen inneholder lister for alle boligtyper og metoder som opperer på listene
Skrevet av: Kristoffer, Christer og Emil
Siste versjon: 13/05/2014.
 */

public class Boligliste implements Serializable {

    private SortedSet<Enebolig> eneboliger;
    private SortedSet<Leilighet> leiligheter;
    private SortedSet<Hybel> hybler;
    private SortedSet<Rekkehus> rekkehus;

    public Boligliste()
    {
        eneboliger = new TreeSet<>();
        leiligheter = new TreeSet<>();
        hybler = new TreeSet<>();
        rekkehus = new TreeSet<>();
    }
    //Legger inn ny bolig, pluss at den gir ny bolig en unik ID
    public boolean leggTil(Bolig b){
        if(b instanceof Enebolig){
            if(erUnik(b)){
                return eneboliger.add((Enebolig)b);
            }
        }else if(b instanceof Rekkehus){
            if(erUnik(b)){
                return rekkehus.add((Rekkehus)b);
            }
        }else if(b instanceof Leilighet){
            if(erUnik(b)){
                return leiligheter.add((Leilighet)b);
            }
        }else if(b instanceof Hybel){
            if(erUnik(b)){
                return hybler.add((Hybel)b);
            }
        }JOptionPane.showMessageDialog(null,"Bolig er allerde lagt til!");
        return false;
    }
    //Sletter bolig fra registeret
    public boolean slettBolig(Bolig b){
        if(b instanceof Enebolig){
            return eneboliger.remove(b);
        }else if(b instanceof Rekkehus){
            return rekkehus.remove(b);
        }else if(b instanceof Leilighet){
            return leiligheter.remove(b);
        }else if(b instanceof Hybel){
            return hybler.remove(b);
        }
        return false;
    }
    //Sjekker om en bolig er unik eller om det allerde finnes et slikt bolig-objekt i noen av listene
    public boolean erUnik(Bolig b){
        Iterator<? extends Bolig> it;
        String[] spec;
        Bolig be;
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
                    return false;
            }else if(be instanceof Rekkehus){
                if(((Rekkehus) be).erLik(spec))
                    return false;
            }else if(be instanceof Leilighet){
                if(((Leilighet) be).erLik(spec))
                    return false;
            }else{
                if(((Hybel)be).erLik(spec))
                    return false;
            }
        }
        return true;
    }
    //Finner boliger som kan passe for en boligsøkers krav, returnerer en Object-array med informasjon om alle
    //bolig-objektene som var ideelle
    public Object[][] matchPåKrav(int[] krav)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        Object[][] ut = null;
        int plass = 0;
        int[] specs;
        double matches = 0;
        double matchkoeffisient;
        double urelevante = 0;
        Object[][] dummy = {{"Fant","desverre","ingen","passende","bolig","for","valgt","boligsøker","!"}};
        Object[][] initial = {{"Velg","en","søker","for","å","finne","match","!","!"}};

       if(krav==null)
            return initial; //Når det kjøres for første gang.

        // Enebolig
       if(krav[0] == 1) {
           Iterator<Enebolig> iter = eneboliger.iterator();
           Enebolig bolig;
           try{//hvis listen med enebolig er tom
               bolig = iter.next();
           }catch(NoSuchElementException e){
               return dummy;
           }
           ut = new Object[eneboliger.size()][Konstanter.TIL_MATCH];
           while (iter.hasNext()) {
               specs = bolig.getSpecArray();
               if (krav[10] < specs[10] && krav[11] > specs[10] && !bolig.getUtleid() && krav[1] == specs[1]) {
                   for (int o = 1; o <= 5; o++) {
                       if (krav[o] == 0)
                           urelevante++;
                       if (specs[o] == krav[o])
                           matches++;
                   }
                   if (matches >= (3 - urelevante)) {
                       matchkoeffisient = matches / (5 - urelevante);
                       ut[plass] = bolig.tilMatchTabell();
                       ut[plass++][0] = df.format(matchkoeffisient);
                   }
               }
               matchkoeffisient = 0;
               matches = 0;
               urelevante = 0;
               bolig = iter.next();
           }
       }
       //Rekkehus
       else if(krav[0] == 2)
        {
            Iterator<Rekkehus> iter = rekkehus.iterator();
            Rekkehus bolig;
            try{//hvis listen med rekkehus er tom
                bolig = iter.next();
            }catch(NoSuchElementException e){
                return dummy;
            }
            ut = new Object[rekkehus.size()][Konstanter.TIL_MATCH];
            while (iter.hasNext()) {
                specs = bolig.getSpecArray();
                if (krav[10] < specs[10] && krav[11] > specs[10] && !bolig.getUtleid() && krav[1] == specs[1]) {
                    for (int o = 1; o <= 5; o++) {
                        if (krav[o] == 0)
                            urelevante++;
                        if (specs[o] == krav[o])
                            matches++;
                    }
                    if (matches >= (3 - urelevante)) {
                        matchkoeffisient = matches / (5 - urelevante);
                        ut[plass] = bolig.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                    }
                }
                matchkoeffisient = 0;
                matches = 0;
                urelevante = 0;
                bolig = iter.next();
            }
        }
        // leileiheiget
        else if(krav[0] == 3)
        {
            Iterator<Leilighet> iter = leiligheter.iterator();
            Leilighet bolig;
            try{//hvis listen med leiligheter er tom
                bolig = iter.next();
            }catch(NoSuchElementException e){
                return dummy;
            }
            ut = new Object[leiligheter.size()][Konstanter.TIL_MATCH];
            while (iter.hasNext())
            {
                specs = bolig.getSpecArray();
                if (krav[10] < specs[10] && krav[11] > specs[10] && !bolig.getUtleid() && krav[1] == specs[1])
                {
                    for(int i = 1; i <= 8; i++) {
                        if(krav[i] == 0)
                            urelevante++;
                        if (specs[i] == krav[i])
                            matches++;
                        if( i == 2)
                            i = 6;
                    }

                    if (matches >= 2) {
                        matchkoeffisient = matches/(5-urelevante);
                        ut[plass] = bolig.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                    }
                    matchkoeffisient = 0;
                    matches = 0;
                    urelevante = 0;
                }
                bolig = iter.next();
            }
        }
        // hyble
        else if(krav[0] == 4) {
            Iterator<Hybel> iter = hybler.iterator();
           Hybel hybel;
            try{//hvis listen med hybler er tom
                hybel = iter.next();
            }catch(NoSuchElementException e){
                return dummy;
            }
            ut = new Object[hybler.size()][Konstanter.TIL_MATCH];
            while (iter.hasNext()) {
                specs = hybel.getSpecArray();
                if (krav[10] < specs[10] && krav[11] > specs[10] && !hybel.getUtleid() && krav[1] == specs[1]) {
                    for (int i = 1; i <= 5; i++)// av krav
                    {
                        if (krav[i] == 0)
                            urelevante++;
                        if (specs[i] == krav[i])
                            matches++;
                    }
                    if (matches >= 4) {
                        matchkoeffisient = matches/(5-urelevante);
                        ut[plass] = hybel.tilMatchTabell();
                        ut[plass++][0] = df.format(matchkoeffisient);
                    }
                }
                matchkoeffisient = 0;
                matches = 0;
                urelevante = 0;
                hybel = iter.next();
            }
        }
        if (plass == 0) {
            JOptionPane.showMessageDialog(null, "Fant ingen matcher!");
            return dummy;
        }
        //Oppretter en ny Object-array slik at vi ikke får mange rader som er tomme i GUI
        Object[][] temp = new Object[plass][Konstanter.TIL_MATCH];
        for(int i = 0; i < temp.length; i++){
            temp[i] = ut[i];
        }

        return temp;
    }//end of matchPåKrav
    
    //Start of boligTilTabllMetoder
    //Metodene returnerer Object-array for å kunne vise informasjon om boligene i GUI
    public Object[][] eneboligerTilTabell()
    {
        Object[][] ut = new Object[eneboliger.size()][Konstanter.BOLIG_TIL_TABELL];

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
    public Object[][] rekkehusTilTabell()
    {
        Object[][] ut = new Object[rekkehus.size()][Konstanter.BOLIG_TIL_TABELL];
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
        Object[][] ut = new Object[leiligheter.size()][Konstanter.BOLIG_TIL_TABELL];
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
        Object[][] ut = new Object[hybler.size()][Konstanter.BOLIG_TIL_TABELL];
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
    //end of boligTilTabellMetoder
    //Finner og returnerer en bolig etter ID
    public Bolig finnBolig(String inn)
    {
        int id;
        try{
            id = Integer.parseInt(inn);
        }
        catch (NumberFormatException nfe)
        {
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
    //Finner og returnerer en boligs utleiers ID
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

    //Sjekker om en Utleier har boliger registrert på seg
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
}//end of class Boligliste
