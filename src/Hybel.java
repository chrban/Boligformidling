import java.io.*;

public class Hybel extends Bolig implements Serializable {
    private int badDelesMed;
    private int kjøkkenDelesMed;
    private int[] specArray;

    public Hyble(String ad,int s, int b, int r, int by, int u, int e, int bad, int kj) {
        super(ad, s, b, r, by, u);
        badDelesMed= bad;
        kjøkkenDelesMed = kj;
    }
    public Hybel(){

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
        specArray = new int[6];
        specArray[0] = super.getSted();
        specArray[1] = super.getBoareal();
        specArray[2] = super.getRom();
        specArray[3] = super.getUtleiepris();
        specArray[4] = badDelesMed;
        specArray[5] = kjøkkenDelesMed;

        return specArray;
    }

    public String toString(){
        return super.toString() + "Bad deles med: " + badDelesMed + "\nKjøkken deles med: " + kjøkkenDelesMed + "\n";
    }
}
