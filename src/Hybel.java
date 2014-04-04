import java.io.Serialiazble;

public class Hybel implements Serializable {
    private int etasje;
    private int delesMed;
    private boolean egetBad;
    private boolean egetKjøkken;

    public Hyble(String ad, int b, int r, int by, int u, int e, int deles, boolean bad, boolean kj) {
        super(ad, b, r, by, u);
        etasje = e;
        delesMed = deles;
        egetBad = bad;
        egetKjøkken = kj;
    }
    public Hybel(){

    }
}
