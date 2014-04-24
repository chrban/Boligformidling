import java.io.*;
/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Enebolig extends Bolig implements Serializable
{
    private int etasjer;
    private boolean harKjeller;
    private int tomtstørrelse;
    private int[] specArray;

    public Enebolig(){}

    public Enebolig(String ad,int s, int b, int r, int by, int u, int e, boolean k, int t)
    {
        super(ad,s, b, r, by, u);
        etasjer = e;
        harKjeller = k;
        tomtstørrelse = t;
    }
    public void lesObjektFraFil(DataInputStream in) throws IOException{
        etasjer = in.readInt();
        harKjeller = in.readBoolean();
        tomtstørrelse = in.readInt();
        super.lesObjektFraFil(in);
    }
    public void skrivTilFil(DataOutputStream out) throws IOException{
        out.writeInt(etasjer);
        out.writeBoolean(harKjeller);
        out.writeInt(tomtstørrelse);
        super.skrivTilFil(out);
    }

    public int[] getSpecArray(){
        specArray = new int[7];
        specArray[0] = super.getSted();
        specArray[1] = super.getBoareal();
        specArray[2] = super.getRom();
        specArray[3] = super.getUtleiepris();
        specArray[4] = etasjer;
        if(harKjeller)
            specArray[5] = 1;
        else
            specArray[5] = 0;
        specArray[6] = tomtstørrelse;

        return specArray;
    }
   /*
    public datatype[] specs()
    {
        returner array med spesifikasjoner om boligen(både fra super- og subklassen) for sammenlikning med boligsøkers krav.
    }
    */
    
}
