import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class UtleierListe {

    public UtleierListe(){
        Utleier en = new Utleier("PIKK", "Carl Ivar", "Haggen", "Fyllingen 8",  "666 666 66", "heiainnvandring@gmail.com","FRP");
        Utleier to = new Utleier("HAKO", "Kong", "Harald", "Slottsplassen 1b", "00 00 00 01","sonjaxoxo@gmail.com", "Monarkiet");

        settInn(en);
        settInn(to);
    }

    private List<Utleier> liste = new LinkedList<>();

    public void settInn( Utleier u ){
        liste.add( u );
        JOptionPane.showMessageDialog(null, toString() + "setter inn utleier");
        sorter();
    }
    public void fjernUtleier( String navn ){
        Iterator<Utleier> iterator = liste.iterator();
        while ( iterator.hasNext() ){
            if ( iterator.next().getNavn().equals(navn)) {
                liste.remove(iterator);
                return;
            }
        }
    }
    public void sorter(){
        Collections.sort(liste, new UtleierSammenlikner());
    }

    public String[][] tilTabell()
    {
        JOptionPane.showMessageDialog(null, liste.size());
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
        JOptionPane.showMessageDialog(null, liste.size());
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
}
