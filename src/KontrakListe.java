
public class KontrakListe {
    private Kontrakter første;

    public KontrakListe(){
        første = null;
    }

    public boolean leggInnKontrakt(Kontrakter ny){
        if(ny == null)
            return false;

        Kontrakter løper = første;

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
        Kontrakter løper = første;

        if(løper == null)
            return "Finner ingen registrerte kontrakter";

        else if(løper.neste == null)
            ut += løper;

        while(løper != null)
        {
            ut += løper;
            løper = løper.neste;
        }
     
        return ut;
    }
}
