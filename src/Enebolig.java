import javax.swing.*;
import java.io.*;
/**
 * Created by Kristoffer on 02.04.2014.
 */
public class Enebolig extends Bolig implements Serializable
{
    private int etasjer;
    private int kjeller;
    private int tomtstørrelse;
    private int parkering;
    private int[] specArray;

    public Enebolig(){}

    public Enebolig(String ad,int s, int b, int r, int by, int u,int id, int e, int park, int k, int t)
    {
        super(ad,s, b, r, by, u, id);
        etasjer = e;
        kjeller = k;
        tomtstørrelse = t;
        parkering = park;
        JOptionPane.showMessageDialog(null, "eneboligkonsturkltæøwert");// todo pikk
    }
    public void lesObjektFraFil(DataInputStream in) throws IOException{
        etasjer = in.readInt();
        kjeller = in.readInt();
        tomtstørrelse = in.readInt();
        super.lesObjektFraFil(in);
    }
    public void skrivTilFil(DataOutputStream out) throws IOException{
        out.writeInt(etasjer);
        out.writeInt(kjeller);
        out.writeInt(tomtstørrelse);
        super.skrivTilFil(out);
    }

    public int[] getSpecArray(){
        specArray = new int[Konstanter.SPEC_LENGDE];
        specArray[1] = super.getSted();
        specArray[2] = super.getRom();
        specArray[3] = parkering;
        specArray[4] = etasjer;
        specArray[5] = kjeller;
        specArray[6] = Konstanter.URELEVANT;
        specArray[7] = Konstanter.URELEVANT;
        specArray[8] = Konstanter.URELEVANT;
        specArray[9] = Konstanter.URELEVANT;
        specArray[10] = super.getUtleiepris();

        return specArray;
    }
   /*
    public datatype[] specs()
    {
        returner array med spesifikasjoner om boligen(både fra super- og subklassen) for sammenlikning med boligsøkers krav.
    }
    */
    
}
