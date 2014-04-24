import java.io.*;

/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Leilighet extends Bolig implements Serializable
{
    private int etasje;
    private boolean balkong;
    private boolean heis;

    public Leilighet(){}

    public Leilighet(String ad,int s, int b, int r, int by, int u, int e, boolean bal, boolean h)
    {
        super(ad,s, b, r, by, u);
        etasje = e;
        balkong = bal;
        heis = h;
    }

    public void lesObjektFraFil(DataInputStream in) throws IOException{
        etasje = in.readInt();
        balkong = in.readBoolean();
        heis = in.readBoolean();
        super.lesObjektFraFil(in);
    }
    public void skrivTilFil(DataOutputStream out) throws IOException{
        out.writeInt(etasje);
        out.writeBoolean(balkong);
        out.writeBoolean(heis);
        super.skrivTilFil(out);
    }
    public int getEtasje(){
        return etasje;
    }
    public boolean getBalkong(){
        return balkong;
    }
    public boolean getHeis(){
        return heis;
    }
}
