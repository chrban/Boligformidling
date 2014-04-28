
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
}
