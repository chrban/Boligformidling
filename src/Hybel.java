import java.io.Serialiazble;

public class Hybel implements Serializable {
    private int etasje;
    private int badDelesMed;
    private int kjøkkenDelesMed;

    public Hyble(String ad, int b, int r, int by, int u, int e, int bad, int kj) {
        super(ad, b, r, by, u);
        etasje = e;
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
}
