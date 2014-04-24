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

    public void lagSpecArray(){
        specArray = new int[]
    }

    public String toString(){
        return super.toString() + "Bad deles med: " + badDelesMed + "\nKjøkken deles med: " + kjøkkenDelesMed + "\n";
    }
}
