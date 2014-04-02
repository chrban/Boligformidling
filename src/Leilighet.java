import java.io.Serializable;

/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Leilighet extends Bolig implements Serializable
{
    private int etasje;
    private boolean balkong;
    private boolean heis;

    public Leilighet(){}

    public Leilighet(String ad, int b, int r, int by, int u, int e, boolean bal, boolean h)
    {
        super(ad, b, r, by, u);
        etasje = e;
        balkong = bal;
        heis = h;
    }
}
