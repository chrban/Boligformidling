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
