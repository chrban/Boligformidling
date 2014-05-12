import javax.swing.*;
import java.io.Serializable;
/*
Filen inneholder metoder for å lage og gjøre opperasjoner på en liste av Kontrakt objekter.
Skrevet av: Emil, s198772.
Sist oppdatert: 10/04/2014
 */
public class KontraktListe implements Serializable {
    private Kontrakt første;

    public KontraktListe(){
        første = null;
    }
    //metoden legger til en ny kontrakt, samtidig som den setter boligen til utleid og boligsøkeren til å ha fått bolig
    //slik at de ikke dukker opp når vi skal matche og registrere ny kontrakt.
    //ny kontrakt blir satt inn bakerst i listen.
    public boolean leggTil(Kontrakt ny){
        if(ny == null)
            return false;

        Kontrakt løper = første;

        if ( første == null ){
            første = ny;
            ny.setTilUtleid();
            ny.setLeietaker();
            return true;
        }
        else{

            while( løper.neste != null )
                løper = løper.neste;
            løper.neste = ny;
            ny.setTilUtleid();
            ny.setLeietaker();
            return true;
        }
    }
    //Denne metoden fjerner en gitt kontrakt, samtidig som den setter bolig til ikke utleid.
    //Denne metoden blir kun brukt når en boligsøker ønsker å slette seg selv og informasjon om seg fra programmet
    public boolean fjernKontrakt(Boligsøker b){

        if(første == null)
            return false;
        Bolig bo = null;

        Kontrakt løper = første;
        if(første != null && første.getBoligsøker() == b){
            if(første.neste != null){
                bo = første.getBolig();
                bo.setTilIkkeUtleid();
                første = løper.neste;
                return true;
            }else{
                bo = første.getBolig();
                bo.setTilIkkeUtleid();
                første = null;
                return true;
            }
        }

        while(løper != null){
            if(løper.neste.getBoligsøker() == b){
                bo = løper.neste.getBolig();
                bo.setTilIkkeUtleid();
                løper.neste = løper.neste.neste;
                return true;
            }
            løper = løper.neste;
        }
        return false;
    }
    //Teller opp antall kontrakter i listen slik at arrayen i neste metode får riktig lengde
    public int tellOpp()
    {
        Kontrakt løper = første;
        int i = 0;

        if(løper == null)
            return 1;

        while(løper != null)
        {
            i++;
            løper = løper.neste;
        }

        return i;
    }
    //I GUI blir det brukt JTable til å vise informasjon, da må informasjonen komme i form av en Stringarray, denne
    // metoden returnerer en slik array.
    public String[][] tilTabell()
    {
        String[][] ut = new String[tellOpp()][4];

        Kontrakt løper = første;
        int i = 0;

        if(løper == null)
        {
            ut[0][0] = "Ingen kontrakter lagret";
            return ut;
        }

        while(løper != null)
        {
            ut[i++] = løper.tilTabell();
            løper = løper.neste;
        }
        return ut;
    }
    //En helt vanlig toString metode som henter opp toString til alle kontrakt objektene i listen.
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
}//end of class KontraktListe
