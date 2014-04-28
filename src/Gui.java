import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


/**
 * Created by mac on 02.04.14.
 */
public class Gui extends JFrame
{
    private JTabbedPane fane = new JTabbedPane();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private JButton knapp1, knapp2, knapp3;
    private JTextField fornavn, etternavn, adresse, mail;
    private JTextArea infonavn;
    private JMenuBar menybar = new JMenuBar();


    public Gui()
    {
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        setSize(skjerm);
        setLocationByPlatform(true);





        //MENYBAR
        setJMenuBar(menybar);

        JMenu fil = new JMenu("Fil");
        add(fil);

        JMenuItem lagre = new JMenuItem("Lagre");
        fil.add(lagre);

        fil.addSeparator();

        JMenuItem avslutt = new JMenuItem("Avslutt");
        fil.add(avslutt);

        JMenu rediger = new JMenu("Rediger");
        add(rediger);

        JMenuItem angre = new JMenuItem("Angre");
        rediger.add(angre);



        // FANA #1 - Utleier

        JPanel panel1 = new JPanel(layout);
        fane.addTab("Ny utleier", null, panel1, "Registrering");


            c.insets = new Insets(5,5,5,5);




        // border
        Border ramme = BorderFactory.createLineBorder(Color.BLACK);
        panel1.setBorder(ramme);


        //Inndatafelt
            c.gridx = 0;
            c.gridy = 0;
        panel1.add(new JLabel("Fornavn: "), c);

        fornavn = new JTextField();
            c.gridx = 1;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
        panel1.add(fornavn, c);
        fornavn.setBorder(ramme);


            c.gridx = 0;
            c.gridy = 1;
        panel1.add(new JLabel("Etternavn: "), c);

        etternavn = new JTextField();
            c.gridx = 1;
            c.gridy = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(etternavn, c);


            c.gridx = 0;
            c.gridy = 2;
        panel1.add(new JLabel("Adresse: "), c);

        adresse = new JTextField();
            c.gridx = 1;
            c.gridy = 2;
            c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(adresse, c);

            c.gridx = 0;
            c.gridy = 3;
        panel1.add(new JLabel("Mail: "), c);

        mail = new JTextField();
            c.gridx = 1;
            c.gridy = 3;
            c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(mail, c);


        // FANA #2 - Boligsøker

        JPanel panel2 = new JPanel(layout);
        fane.addTab("Ny boligsøker", null, panel2, "registrerer boligsøker");



        add(fane);
    }

    public void regBoligsøker()
    {
        /*
        - les inn fra gui og legg i variabler
        - opprett og send til register?
        *  ikke verre enn det?
        *
        * */
    }

    public void regBolig()
    {
        /*
        - Les inn fra gui og legg i varz.
        - test på type
        - opprett og send til register. siden vi allerede her bestemmer type, kan vi ha en legg til metode for hver ty
          type bolig i registerklassen. det er easymode, kan prøve oss på nope litt mer expert senere.
        */
    }

    public void regUtleier()
    {
        /*
        - Les inn til varz
        - opprett og legg i liste? lag liste først.
        - hvordan registrere boliger som er knyttet til utleieren?
        */
    }

    public void mekkKontrakt()
    {
        /*
        - Les inn identifiserende informasjon om søker og utleier fra gui
        - finn og returner begge objektene.
        - mekk en kontrakt med info fra begge.
        */
    }

    public void matchPåKrav()
    {
        /*
        - hvem skal det matches på? hvordan velger vi hvilken boligsøker vi skal finne boliger til?
            - Velge fra liste i gui?(liker denne best, sikkert mulig å få til med JTable)
            - skrive inn kundenummer eller noe?

        - Finne boligsøker og få tak i kravene
        - test på boligtype
            - Hent liste til riktig boligtype, mekk iterator
            - Løkke(For gjennomgang av boliglisten)
                - Løkke(For gjennomløping av krav og specs)
                    - Hvis match, legg til poeng
                    - hvis null, reg som blankt felt

                - kalkuler matchkoefisient og lagre den et sted. Hvor? jeg vet ikke.

            Må på en måte skrive ut en sortert liste av boliger som har høy nok matchkoefisient.
                - Kan lagre koefisienten i boligobjektet og endre den naturlige sorteringen til å ta hensyn på
                  koefisienten, ikke på prisen.
                - Kan lagre bolignr og score i todimmensjonell array, sortere den på score og så lage en turskrift ved
                  å kalle opp toString for hver bolig i rekkefølgen definert av arrayen.
        */
    }

    public void visBoligsøkere()
    {
        /*
        - vis de.
        */
        /
    }

    public void visBoliger(){} // få til sanntid-endringer mht endringer i innfeltene?

    public void visKontrakter(){}

    public void slettBoligsøker(){} //  var det dette som vi skulle kunne slette, eller noe annet?

}
