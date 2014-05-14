import java.io.Serializable;
/*
Filen inneholder datafelt og metoder som Boligsøker og Utleier har til felles
Skrevet av: Kristoffer, Christer og Emil.
Siste versjon:
 */
abstract class Person implements Serializable
{
    private String id;
    private String fnavn;
    private String enavn;
    private String adresse;
    private String tlf;
    private String eMail;

    public Person(String i, String fn, String en, String a, String t, String e)
    {
        id = i;
        fnavn = fn;
        enavn = en;
        adresse = a;
        tlf = t;
        eMail = e;
    }
    //Start of getMetoder
    public String getId(){return id;}
    public String getFornavn()
    {
        return fnavn;
    }
    public String getEtternavn(){
        return enavn;
    }
    public String getAdresse(){return adresse;}
    public String getEmail()
    {
        return eMail;
    }
    public String getTlf(){return tlf;}
    //end of getMetoder
    public String toString()
    {


        return"Navn: "+ enavn.substring(0,1).toUpperCase()+enavn.substring(1)+", " + fnavn.substring(0,1).toUpperCase()+fnavn.substring(1);
    }
}//end of class Person
