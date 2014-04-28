import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Created by mac on 02.04.14.
 */
public class Gui extends JFrame
{
    private JTabbedPane fane = new JTabbedPane();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private JButton knapp1, knapp2, knapp3;
    private JTextField fornavn, etternavn, adresse, mail, firma;
    private JTextArea infonavn;
    private JMenuBar menybar = new JMenuBar();
    private JRadioButton personType;
    private JPanel panel1, bspanel, utpanel;
    private JComboBox boligtypeBox,byBox,romBox;
    private JSlider minPrisSlider,maxPrisSlider;
    private String[] boligtypeValg = {"Enebolig","Hybel", "Leilighet","Rekkehus"};
    private String[] byvalg = {"Oslo","Bergen","Stavanger","Trondheim","Kristiansand","Tromsø"};
    private String[] romValg = {"1","2","3","4","5","6"};


    /* TEMP ROT og SØPPEL

    Registrere boligsøker/utleier
    Registrer bolig



        border
        Border ramme = BorderFactory.createLineBorder(Color.BLACK);
        panel1.setBorder(ramme);


    End søppppel
    */



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






        // FANA #1 - Registrering av personer (boligsøker/utleier)

        JPanel panel1 = new JPanel(layout);
        fane.addTab("Registrer persion", null, panel1, "Registrering av boligsøker/uleier");


        //  c.insets = new Insets(5,5,5,5); // gjelder alle til den nulstilles


        // esktra panelø
        utpanel = new JPanel(layout);
        bspanel = new JPanel(layout);







        //Inndatafelt
        c.gridx = 0;
        c.gridy = 0;
        panel1.add(new JLabel("Fornavn: "), c);

        fornavn = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(fornavn, c);



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

        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        utpanel.add(new JLabel("Firma: "), c);

        firma = new JTextField();
        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        utpanel.add(firma, c);


        // IF utleier, then ADD denne:
        c.gridx = 0;
        c.gridy = 1;
        panel1.add(utpanel);
        // TODO <-


        //BOLISØKER PANEL (bspanel)


        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Boligtype: "), c);

        boligtypeBox = new JComboBox(boligtypeValg);
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(boligtypeBox, c);


        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("By: "), c);

        byBox = new JComboBox(byvalg);
        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(byBox, c);



        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Rom: "), c);

        romBox = new JComboBox(romValg);
        c.gridx = 2;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(romBox, c);




        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("min Pris: "), c);

        c.gridx = 2;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;


        minPrisSlider = new JSlider();
        minPrisSlider.setMinimum(0);
        minPrisSlider.setMaximum(50000);
        minPrisSlider.setValue(250);
        minPrisSlider.setMajorTickSpacing(10000);
        minPrisSlider.setPaintTicks(true);
        minPrisSlider.setPaintLabels(true);
        minPrisSlider.addChangeListener(new minPrisLytter());

        bspanel.add(minPrisSlider, c);



        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("max Pris: "), c);



        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 3;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;

        maxPrisSlider = new JSlider();
        maxPrisSlider.setMinimum(0);
        maxPrisSlider.setMaximum(50000);
        maxPrisSlider.setValue(250);
        maxPrisSlider.setMajorTickSpacing(10000);
        maxPrisSlider.setPaintTicks(true);
        maxPrisSlider.setPaintLabels(true);
        maxPrisSlider.addChangeListener(new maxPrisLytter());

        bspanel.add(maxPrisSlider, c);










        //IF BOLIGSØKER ADD DENNE::
        c.gridx = 5;
        c.gridy = 1;
        panel1.add(bspanel);
        //TODO <<---





            /*
      Boligtype
    0 by
    1 rom
    2 minPris
    3 maxPris
    4 parkering
    5 antEtasjer
    6 kjeller
    7 minTomt
    8 maxTomt
    9 heis
    10 balkong
    11 delerBadMed
    12 delerKjøkkenMed
    */


























        // FANA #2 - Boligsøker

        JPanel panel2 = new JPanel(layout);
        fane.addTab("Ny bolig", null, panel2, "registrerer ny bolig");

        JPanel panel3 = new JPanel(layout);
        fane.addTab("Vis tabell", null, panel3, "Liste over bloiger og personer");

        JPanel panel4 = new JPanel(layout);
        fane.addTab("MatchMaking!", null, panel4, "Match hus og menneske");




        add(fane);
    }

// LYTTERE

    private class minPrisLytter implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            int minPris = minPrisSlider.getValue();
        }
    }

    private class maxPrisLytter implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            int maxPris = maxPrisSlider.getValue();
        }
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

    }


    public void visBoliger()
    {
     /*
     - Skal vi kunne vise alle boligene samtidig eller bare en type om gangen? en type er vel absolutt lettest.
     -
     */
    } // få til sanntid-endringer mht endringer i innfeltene?



    public void visKontrakter()
    {
        /*
        - Burde være easymode. Lage en toString i kontraktliste som sender med en superlang String som kan skrives ut.

        */
    }



    public void slettBoligsøker()
    {

    } //  var det dette som vi skulle kunne slette, eller noe annet?

}
