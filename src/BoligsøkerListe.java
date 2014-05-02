import javax.swing.*;
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
            if( løper.getFornavn().equals(b.getFornavn())) {
                JOptionPane.showMessageDialog(null, b.toString()+"\n finnes fra før");
                return false;
            }
            løper = løper.neste;
        }

        if( første == null ){
            første = b;
            JOptionPane.showMessageDialog(null, b.toString() +"på første plass");
            return true;
        }
        else{
            løper = første;
            while( løper.neste != null )
                løper = løper.neste;
            løper.neste = b;
            JOptionPane.showMessageDialog(null, b.toString()+"ikke på første plass...");
            return true;

        }
    }

    public boolean fjernSøker(String n){

        Boligsøker løper = første;

        if( første != null && første.getFornavn().equals(n)){
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
            if( løper.neste != null && løper.neste.getFornavn().equals(n)){
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