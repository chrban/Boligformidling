import java.io.Serializable;

public class Hybel implements Serializable {
    private int badDelesMed;
    private int kjøkkenDelesMed;
    private int[] kravArray;

    public Hyble(String ad, int b, int r, int by, int u, int e, int bad, int kj, int[] ar) {
        super(ad, b, r, by, u);
        badDelesMed= bad;
        kjøkkenDelesMed = kj;
        kravArray = ar;
    }
    public Hybel(){

    }

    public int getBadDelesMed(){
        return badDelesMed;
    }

    public int getKjøkkenDelesMed(){
        return kjøkkenDelesMed;
    }

    public int[] getKravArray(){
        return kravArray;
    }

    public String toString(){
        return super.toString() + "Bad deles med: " + badDelesMed + "\nKjøkken deles med: " + kjøkkenDelesMed + "\n";
    }
}
