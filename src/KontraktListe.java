
public class KontraktListe {
    private Kontrakt første;

    public KontraktListe(){
        første = null;
    }

    public boolean leggInnKontrakt(Kontrakt ny){
        if(ny == null)
            return false;

        Kontrakt løper = første;

        if ( første == null ){
            første = ny;
            return true;
        }
        else{
            while( løper.neste != null )
                løper = løper.neste;
            løper.neste = ny;
            return true;
        }
    }

    public String toString()
    {
        String ut = "";
        Kontrakt løper = første;

        if(løper == null)
            return "Finner ingen registrerte kontrakter";

        while(løper != null)
        {
            ut += løper.toString();
            løper = løper.neste;
        }

        return ut;
    }
}
