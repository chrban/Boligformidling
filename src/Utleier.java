import java.io.*;
/*
Filen inneholder datafelt og metoder for klassen Utleier
Skrevet av: Kristoffer, Christer og Emil
Siste versjon:
 */
public class Utleier extends Person implements Serializable
{
    private String firma;

    public Utleier(String id, String fn, String en, String a, String t, String e, String f)
    {
        super(id, fn, en, a, t, e);
        firma = f;
    }
    //Start of getMetoder
    public String getFornavn()
    {
        return super.getFornavn();
    }

    public String getEmail()
    {
        return super.getEmail();
    }

    public String getNavn()
    {
        return super.getFornavn() + " " + super.getEtternavn();
    }
    //end of getMetoder
    //Returnerer en String-array med informasjon om en utleier
    public String[] tilTabell()
    {
    String[] ut = new String[6];

    ut[0] = super.getFornavn();
    ut[1] = super.getEtternavn();
    ut[2] = super.getAdresse();
    ut[3] = super.getEmail();
    ut[4] = super.getTlf();
    ut[5] = firma;

    return ut;
    }
    //Returnerer en String-array med informasjon om en utliere + utleierens ID
    public String[] tilTabellMedId()
    {
        String[] ut = new String[7];

        ut[0] = super.getId();
        ut[1] = super.getFornavn();
        ut[2] = super.getEtternavn();
        ut[3] = super.getAdresse();
        ut[4] = super.getEmail();
        ut[5] = super.getTlf();
        ut[6] = firma;

        return ut;
    }
    public String toString()
    {
        return super.toString() + "\nFirma: " + firma.substring(0,1).toUpperCase()+firma.substring(1)+
                "\nUtleier ID: " + getId()+
                "\nAdresse: " + getAdresse()+
                "\nMail: " + getEmail()+
                "\nTlf: " + getTlf();

    }
}//end of class Utleier
