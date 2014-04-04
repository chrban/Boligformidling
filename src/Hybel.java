import java.io.Serialiazble;

public class Hybel implements Serializable {
    private int badDelesMed;
    private int kjøkkenDelesMed;

    public Hyble(String ad, int b, int r, int by, int u, int e, int bad, int kj) {
        super(ad, b, r, by, u);
        badDelesMed= bad;
        kjøkkenDelesMed = kj;
    }
    public Hybel(){

    }

    public int getBadDelesMed(){
        return badDelesMed;
    }

    public int getKjøkkenDelesMed(){
        return kjøkkenDelesMed;
    }
    public String toString(){
        return super.toString() + "Bad deles med: " + badDelesMed + "\nKjøkken deles med: " + kjøkkenDelesMed + "\n";
    }
}
