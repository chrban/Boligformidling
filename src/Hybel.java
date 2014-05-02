import java.io.*;

public class Hybel extends Bolig implements Serializable {
    private int badDelesMed;
    private int kjøkkenDelesMed;
    private int[] specArray;

    public Hybel(){

    }

    public Hybel(String ad,int s, int b, int r, int by, int u,int id, int bad, int kj) {
        super(ad, s, b, r, by, u, id);
        badDelesMed= bad;
        kjøkkenDelesMed = kj;
    }

    public void lesObjektFraFil(DataInputStream in) throws IOException{
        badDelesMed = in.readInt();
        kjøkkenDelesMed = in.readInt();
        super.lesObjektFraFil(in);
    }
    public void skrivTilFil(DataOutputStream out) throws IOException{
        out.writeInt(badDelesMed);
        out.writeInt(kjøkkenDelesMed);
        super.skrivTilFil(out);
    }

    public int getBadDelesMed(){
        return badDelesMed;
    }

    public int getKjøkkenDelesMed(){
        return kjøkkenDelesMed;
    }

    public int[]getSpecArray(){
        specArray = new int[Konstanter.SPEC_LENGDE];
        specArray[1] = super.getSted();
        specArray[2] = super.getRom();
        specArray[3] = Konstanter.URELEVANT;
        specArray[4] = Konstanter.URELEVANT;
        specArray[5] = Konstanter.URELEVANT;
        specArray[6] = Konstanter.URELEVANT;
        specArray[7] = Konstanter.URELEVANT;
        specArray[8] = badDelesMed;
        specArray[9] = kjøkkenDelesMed;
        specArray[10] = super.getUtleiepris();

        return specArray;
    }

    public String toString(){
        return super.toString() + "Bad deles med: " + badDelesMed + "\nKjøkken deles med: " + kjøkkenDelesMed + "\n";
    }
}
