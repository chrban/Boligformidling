import javax.swing.*;
import java.io.Serializable;
/*
Filen inneholder metoder for å opprette unike ID for forskjellige klasser
Skrevet av: Emil
Siste versjon: 14/05/2014
 */
public class idGenerator implements Serializable {
    private int eneboligID, rekkehusID, leilighetID, hybelID;

    public idGenerator(){
        eneboligID = 1;
        rekkehusID = 1000;
        leilighetID = 2000;
        hybelID = 3000;
    }
    public int setIdPåBolig(int i){
        if(i == 1){
            return eneboligID++;
        }else if(i == 2){
            return rekkehusID++;
        }else if(i == 3){
            return leilighetID++;
        }else
            return hybelID++;
    }
    public String setIdPåBoligsøker(String en, String fn){
        String id = "";
        try{
            id = en.substring(0,2).toUpperCase()+fn.substring(0,2).toUpperCase();}
        catch(StringIndexOutOfBoundsException sioobe){
            JOptionPane.showMessageDialog(null,"Feil ved generering av ID");
        }
        JOptionPane.showMessageDialog(null, id);
        return id;
    }
    public String setIdPåUtleier(String f, String en, String fn){
        String id = "";

        try {
            id = f.substring(0, 2).toUpperCase() + en.substring(0, 2).toUpperCase() + fn.substring(0, 2).toUpperCase();
        } catch (StringIndexOutOfBoundsException sioobe) {
            JOptionPane.showMessageDialog(null,"Feil ved generering av ID");
        }
        return id;
    }
    public String setIdPåKontrakt(Utleier u, Boligsøker s, Bolig b){
        String ID ="ID";
        try{
            ID = u.getEtternavn().substring(0,2) + s.getEtternavn().substring(0,2)+b.getId();
        }
        catch(StringIndexOutOfBoundsException SIOOBE)
        {
            ID = "AUTOID";
        }
        return ID;
    }
}
