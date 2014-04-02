/**
 * Created by mac on 01.04.14.
 */
abstract class Person implements serielizable
{

    private String navn;
    private String adresse;
    private String tlf;
    private String eMail;

    public Person(){}

    public Person(String n, String a, String t, String e)
    {
        navn = n;
        adresse = a;
        tlf = t;
        eMail = e;
    }

    public String getNavn()
    {
        return navn;
    }

    public String getEmail()
    {
        return eMail;
    }

    public String toString()
    {
        return "Informasjon om " + navn +"\n\n";
    }
}
