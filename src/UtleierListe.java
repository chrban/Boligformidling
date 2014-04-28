import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class UtleierListe {
    private List<Utleier> liste = new LinkedList<>();

    public void settInn( Utleier u ){
        liste.add( u );
        sorter();
    }
    public void sorter(){
        Collections.sort(liste, new UtleierSammenlikner());
    }
    public String toString(){
        sorter();
        String utleiere = "";
        Iterator<Utleier> iterator = liste.iterator();
        while ( iterator.hasNext() ){
            utleiere += iterator.next().getNavn() + "\n";
        }
        return utleiere;
    }
}
