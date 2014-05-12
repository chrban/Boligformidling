import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;
import java.io.Serializable;
/*
Filen inneholder metoder for å lage regler for hvordan listen i UtleierListe skal kunne
sorteres.
Skrevet av: Emil, s198772
Sist oppdatert: 29/4/2014
 */
public class UtleierSammenlikner implements Comparator<Utleier>, Serializable{
    private String rekkefølge = "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j<K,k" +
                                "<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t<U,u<V,v" +
                                "<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

    private RuleBasedCollator kollator;
    //Oppretter en RuleBasedCollator med hensyn til rekkefølgen dekklarert ovenfor
    public UtleierSammenlikner(){
        try{
            kollator = new RuleBasedCollator(rekkefølge);
        }
        catch (ParseException pe){
            JOptionPane.showMessageDialog(null, "Feil i rekkefølge!");
        }
    }
    //sammenligner to utleier objekter for å avgjøre hvem som kommer først med hensyn til rekkefølgen som er gitt
    public int compare(Utleier u1, Utleier u2){
        String n1 = u1.getFornavn();
        String n2 = u2.getFornavn();
        String e1 = u1.getEtternavn();
        String e2 = u2.getEtternavn();
        int d = kollator.compare(n1,n2);
        if( d != 0 )
            return d;
        else{
            d = kollator.compare(e1,e2);
            return d;
        }
    }
}//end of Class UtleierSammenlikner
