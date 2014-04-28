import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class UtleierSammenlikner implements Comparator<Utleier>, Serializable{
    private String rekkefølge = "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j<K,k" +
                                "<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t<U,u<V,v" +
                                "<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

    private RuleBasedCollator kollator;

    public UtleierSammenlikner(){
        try{
            kollator = new RuleBasedCollator(rekkefølge);
        }
        catch (ParseException pe){
            JOptionPane.showMessageDialog(null, "Feil i rekkefølge!");
        }
    }
    public int compare(Utleier u1, Utleier u2){
        String n1 = u1.getNavn();
        String n2 = u2.getNavn();
        int d = kollator.compare(n1,n2);
        return d;
    }
}
