import java.io.*;

/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Leilighet extends Bolig implements Serializable
{
    private int etasje;
    private int balkong;
    private int heis;
    private int[] specArray;

    public Leilighet(){}

    public Leilighet(String ad,int s, int b, int r, int by, int u,int id, int e, int bal, int h)
    {
        super(ad,s, b, r, by, u, id);
        etasje = e;
        balkong = bal;
        heis = h;
    }

    public void lesObjektFraFil(DataInputStream in) throws IOException{
        etasje = in.readInt();
        balkong = in.readInt();
        heis = in.readInt();
        super.lesObjektFraFil(in);
    }
    public void skrivTilFil(DataOutputStream out) throws IOException{
        out.writeInt(etasje);
        out.writeInt(balkong);
        out.writeInt(heis);
        super.skrivTilFil(out);
    }
    public int getEtasje(){
        return etasje;
    }
    public int getBalkong(){
        return balkong;
    }
    public int getHeis(){
        return heis;
    }

    public int[] getSpecArray(){// todo legg inn konstanter
        specArray = new int[Konstanter.SPEC_LENGDE];
        specArray[1] = super.getSted();
        specArray[2] = super.getRom();
        specArray[3] = Konstanter.URELEVANT;
        specArray[4] = Konstanter.URELEVANT;
        specArray[5] = Konstanter.URELEVANT;
        specArray[6] = heis;
        specArray[7] = balkong;
        specArray[8] = Konstanter.URELEVANT;
        specArray[9] = Konstanter.URELEVANT;
        specArray[10] = super.getUtleiepris();

        return specArray;
    }
}
