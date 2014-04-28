import java.io.*;

public class BoligsøkerListe implements Serializable {
    private Boligsøker første;

    public BoligsøkerListe(){
        første = null;
    }

    public boolean settInnNy(Boligsøker b){

        if( b == null )
            return false;

        Boligsøker løper = første;

        while( løper!=null ){
            if( løper.getNavn().equals(b.getNavn()))
                return false;
            løper = løper.neste;
        }

        if( første == null ){
            første = b;
            return true;
        }
        else{
            løper = første;
            while( løper.neste != null )
                løper = løper.neste;
            løper.neste = b;
            return true;

        }
    }

    public boolean fjernSøker(String n){

        Boligsøker løper = første;

        if( første != null && første.getNavn().equals(n)){
            if( løper.neste != null ){
                første = løper.neste;
                return true;
            }
            else{
                første = null;
                return true;
            }
        }
        while( løper != null ){
            if( løper.neste != null && løper.neste.getNavn().equals(n)){
                løper.neste = løper.neste.neste;
                return true;
            }
            løper = løper.neste;
        }
        return false;
    }

    public String toString(){
        String info = "";
        Boligsøker løper = første;

        while( løper != null ){
            info += løper.toString() + "\n";
            løper = løper.neste;
        }
        if( !info.equals(""))
            return info;
        else
            return "Ingen boligsøkere registrert!";
    }
}