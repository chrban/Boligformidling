import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class UtleierListe implements Serializable{
    private Boligliste boliger;

    public UtleierListe(Boligliste b){
        boliger = b;
    }

    private List<Utleier> liste = new LinkedList<>();

    public void settInn( Utleier u ){
        liste.add( u );
        sorter();
    }
    public boolean fjernUtleier( String navn, String etternavn ){
        Iterator<Utleier> iterator = liste.iterator();
        while ( iterator.hasNext() ){
            Utleier fjern = iterator.next();
            if(fjern.getFornavn().equals(navn) && fjern.getEtternavn().equals(etternavn)){
                liste.remove(fjern);
                return true;
            }
        }
        return false;
    }
    public void sorter(){
        Collections.sort(liste, new UtleierSammenlikner());
    }

    public String[][] tilTabell()
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
    }
    public String[][] tilTabellMedId()
    {
        String[][] ut = new String[liste.size()][7];
        Utleier utleier;
        Iterator<Utleier> iter = liste.iterator();
        int i = 0;

        while(iter.hasNext())
        {
            utleier = iter.next();
            ut[i++] = utleier.tilTabellMedId();
        }
        return ut;
    }

    public String toString(){
        sorter();
        String utleiere = "";
        Iterator<Utleier> iterator = liste.iterator();
        while ( iterator.hasNext() ){
            utleiere += iterator.next().toString() + "\n";
        }
        return utleiere;
    }
    public Utleier getUtleier(String i){
        System.out.println("leter etter boligsøker");
        Iterator<Utleier> it = liste.iterator();
        Utleier retur;
        while(it.hasNext()){
            retur = it.next();
            if( retur.getId().equals(i) )
                return retur;
        }
        return null;
    }
    public boolean harBoliger(Utleier b){
        if(boliger.harBolig(b))
            return true;
        else
            return false;
    }
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
}
