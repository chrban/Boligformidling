import java.io.Serializable;

/**
 * Created by mac on 01.04.14.
 */
abstract class Person implements Serializable
{
    private String id;
    private String fnavn;
    private String enavn;
    private String adresse;
    private String tlf;
    private String eMail;


    public Person(){}

    public Person(String i, String fn, String en, String a, String t, String e)
    {
        id = i;
        fnavn = fn;
        enavn = en;
        adresse = a;
        tlf = t;
        eMail = e;
    }

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

    public String toString()
    {
        return "Informasjon om " + enavn +", " + fnavn + "\n";
    }
}
