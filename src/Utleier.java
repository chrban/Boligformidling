import java.io.*;

/**
 * Created by mac on 02.04.14.
 */
public class Utleier extends Person implements Serializable
{
    private String firma;
    public Utleier neste;
    public Boligliste boliger;

    public Utleier(String fn, String en, String a, String t, String e, String f)
    {
        super(fn, en, a, t, e);
        firma = f;
    }

    public String getNavn()
    {
        return super.getFornavn();
    }

    public String getEmail()
    {
        return super.getEmail();
    }



    public String toString()
    {
        return super.toString() + "\nFirma: " + firma;
    }


    //klasser som kan være nødvendige:
    /*
    public Bolig harBolig(Bolig b)
    {
        return Bolig;
    }
    public Bolig harBolig(String bolignummer)
    {
        return Bolig;
    }

    public Boligliste getBoliger()
    {
        return boliger;
    }

    */
}
