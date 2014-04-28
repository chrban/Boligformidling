import java.io.*;

/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Leilighet extends Bolig implements Serializable
{
    private int etasje;
    private boolean balkong;
    private boolean heis;
    private int[] specArray;

    public Leilighet(){}

    public Leilighet(String ad,int s, int b, int r, int by, int u,int id, int e, boolean bal, boolean h)
    {
        super(ad,s, b, r, by, u, id);
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

    public int[] getSpecArray(){
        specArray = new int[7];
        specArray[0] = super.getSted();
        specArray[1] = super.getBoareal();
        specArray[2] = super.getRom();
        specArray[3] = super.getUtleiepris();
        specArray[4] = etasje;
        if (balkong)
            specArray[5] = 1;
        else
            specArray[5] = 0;
        if(heis)
            specArray[6] = 1;
        else
            specArray[6] = 0;

        return specArray;
    }
}
