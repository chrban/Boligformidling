import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
/*
Filen inneholder metoder for å opprette og gjøre opperasjoner på en liste med utleier objekter
Skrevet av: Kristoffer, Christer og Emil
Sist oppdatert:
 */
public class UtleierListe implements Serializable{
    private Boligliste boliger;
    //Konstruktøren har kun en variable i parameterlista, den brukes i en av metodene
    public UtleierListe(Boligliste b){
        boliger = b;
    }

    private List<Utleier> liste = new LinkedList<>();
    //setter inn et nytt Utleier objekt i lista
    public void settInn( Utleier u ){
        liste.add( u );
        sorter();
    }
    //fjerner et Utleier objekt med hensyn på fornavn og etternavn
    public boolean fjernUtleier( String navn, String etternavn ){
        Iterator<Utleier> iterator = liste.iterator();
        while ( iterator.hasNext() ){
            Utleier fjern = iterator.next();
            if(fjern.getFornavn().equals(navn) && fjern.getEtternavn().equals(etternavn)){
                return liste.remove(fjern);
            }
        }
        return false;
    }
    //metoden sorter lista etter reglene som blir gitt i UtleierSammenlikner klassen
    public void sorter(){
        Collections.sort(liste, new UtleierSammenlikner());
    }
    //I GUI blir det brukt JTable for å vise informasjon, det kreves at informasjonen kommer i form av en Stringarray
    //denne moten returnerer en slik Stringarray
    public String[][] tilTabell()
    {
        String[][] ut = new String[liste.size()][6];
        Utleier utleier;
        sorter();
        Iterator<Utleier> iter = liste.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            utleier = iter.next();
            ut[i++] = utleier.tilTabell();
        }
        return ut;
    }
    //Samme metode som over, bare at den tar med id'n til utleieren
    public String[][] tilTabellMedId()
    {
        String[][] ut = new String[liste.size()][7];
        Utleier utleier;
        sorter();
        Iterator<Utleier> iter = liste.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            utleier = iter.next();
            ut[i++] = utleier.tilTabellMedId();
        }
        return ut;
    }
    //Finner en gitt utleier med hensyns på id'n som kommer i parameterlista
    public Utleier getUtleier(String i){
        Iterator<Utleier> it = liste.iterator();
        Utleier retur;
        while(it.hasNext()){
            retur = it.next();
            if( retur.getId().equals(i) )
                return retur;
        }
        return null;
    }
    //Sjekker om en utleier har boliger registrert på seg
    public boolean harBoliger(Utleier b){
        if(boliger.harBolig(b))
            return true;
        else
            return false;
    }
    //Finner id'n til utleier med hensyn på fornavn og etternavn
    public String finnID(String n, String f){
        Iterator<Utleier> it = liste.iterator();
        Utleier finn = null;
        while(it.hasNext()){
            finn = it.next();
            if(finn != null && finn.getFornavn().equals(n) && finn.getEtternavn().equals(f))
                return finn.getId();
        }
        return null;
    }
    //Vanlig toString metode for utleierliste
    public String toString(){
        sorter();
        String utleiere = "";
        Iterator<Utleier> iterator = liste.iterator();
        while ( iterator.hasNext() ){
            utleiere += iterator.next().toString() + "\n";
        }
        return utleiere;
    }
}//end of class UtleierListe
