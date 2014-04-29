import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
    private JRadioButton utleier, boligsøker;
    private JPanel panel1, bspanel, utpanel, panel2,bopanel, panel3, panel4;
    private JComboBox boligtypeBox,byBox,romBox, etasjeBox;
    private JCheckBox kjellerValg, heisValg, garasjeValg, badValg, kjøkkenValg;
    private JSlider minPrisSlider,maxPrisSlider;
    private String[] boligtypeValg = {"Enebolig","Hybel", "Leilighet","Rekkehus"};
    private String[] byvalg = {"Oslo","Bergen","Stavanger","Trondheim","Kristiansand","Tromsø"};
    private String[] romValg = {"1","2","3","4","5","6"};
    private String[] etasjeValg = {"1","2","3"};
    private JTable tabell;

    private PersonTypeLytter radioLytter;
    private ButtonGroup radioPerson;
    private  Border ramme = BorderFactory.createLineBorder(Color.BLACK);


    public Gui()
    {
        super("Boligformidling for svaksynte");

        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        setSize(bredde / 2, høyde / 2);
       // setLocationByPlatform(true);
        //todo Finne en fin skjermstørrelse



        //Oppretter panelene

        panel1 = new JPanel(layout);
        panel2 = new JPanel(layout);
        panel3 = new JPanel(layout);
        panel4 = new JPanel(layout);
        utpanel = new JPanel(layout);
        bspanel = new JPanel(layout);
        bopanel = new JPanel(layout);
        panel1.setVisible(true);
            c.gridx = 2;
            c.gridy = 0;
            c.gridwidth = 2;
            c.gridheight = 9;
            c.insets = new Insets(5,5,5,5);
            c.anchor = GridBagConstraints.FIRST_LINE_START;
        panel1.add(bspanel, c);
            c.gridheight = 1;

        panel1.add(utpanel, c);
        panel2.add(bopanel, c);
        bopanel.setVisible(true);
        bspanel.setVisible(false); // endre tilbake til, false
        utpanel.setVisible(false);

        //oppretter Fanene

        fane.addTab("Registrer Person",null,panel1,"Registrere ny boligsøker eller utleier");
        fane.addTab("Registrer nby bolig",null,panel2,"Registrere ny boligsøker eller utleier");
        fane.addTab("Vis tabell",null,panel3,"Registrere ny boligsøker eller utleier");
        fane.addTab("MatchMaking",null,panel4,"Registrere ny boligsøker eller utleier");





        // Reseter
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,0);
        c.gridheight = 1;



        //Inndatafelt
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        panel1.add(new JLabel("Fornavn: "), c);

        fornavn = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipadx = ;
        panel1.add(fornavn, c);



        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        panel1.add(new JLabel("Etternavn: "), c);

        etternavn = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 100;
        panel1.add(etternavn, c);


        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 0;
        panel1.add(new JLabel("Adresse: "), c);

        adresse = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(adresse, c);

        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 0;
        panel1.add(new JLabel("Mail: "), c);

        mail = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(mail, c);


        radioLytter = new PersonTypeLytter();
        radioPerson = new ButtonGroup();
        utleier = new JRadioButton("Utleier",false);

        utleier.addActionListener(radioLytter);
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(utleier,c);

        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;

        boligsøker = new JRadioButton("Boligsøker",false);
        boligsøker.addActionListener(radioLytter);
        panel1.add(boligsøker,c);

        radioPerson.add(utleier);
        radioPerson.add(boligsøker);



        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        utpanel.add(new JLabel("Firma: "), c);

        firma = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        utpanel.add(firma, c);



        //BOLISØKER PANEL (bspanel)


        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Boligtype: "), c);

        boligtypeBox = new JComboBox(boligtypeValg);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(boligtypeBox, c);


        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("By: "), c);

        byBox = new JComboBox(byvalg);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(byBox, c);



        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Rom: "), c);

        romBox = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(romBox, c);




        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("min Pris: "), c);

        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;


        minPrisSlider = new JSlider();
        minPrisSlider.setMinimum(0);
        minPrisSlider.setMaximum(50000);
        minPrisSlider.setValue(250);
        minPrisSlider.setMajorTickSpacing(10000);
        minPrisSlider.setMinorTickSpacing(5000);
        minPrisSlider.setPaintTicks(true);
        minPrisSlider.setPaintLabels(true);
        minPrisSlider.addChangeListener(new minPrisLytter());
        minPrisSlider.setSnapToTicks(true);


        bspanel.add(minPrisSlider, c);
        //todo koble riktige verdier


        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("max Pris: "), c);

//todo - Koble sliders til TextField

        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;

        maxPrisSlider = new JSlider();
        maxPrisSlider.setMinimum(0);
        maxPrisSlider.setMaximum(50000);
        maxPrisSlider.setValue(250);
        maxPrisSlider.setMajorTickSpacing(10000);
        maxPrisSlider.setMinorTickSpacing(5000);
        maxPrisSlider.setPaintTicks(true);
        maxPrisSlider.setPaintLabels(true);
        maxPrisSlider.addChangeListener(new maxPrisLytter());
        maxPrisSlider.setSnapToTicks(true);
        bspanel.add(maxPrisSlider, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Ant Etasjer: "), c);


        etasjeBox = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(etasjeBox, c);

        garasjeValg = new JCheckBox("Garasje");
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(garasjeValg,c);


        kjellerValg = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(kjellerValg,c);

        heisValg = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(heisValg,c);

        badValg = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(badValg,c);

        kjøkkenValg = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(kjøkkenValg,c);

        // FANE NR 2, REGISTRER NY BOLIg *****************************************************************************



/*
        adresse = ad;
        sted = s;
        boareal = b;
        rom = r;
        byggAr = by;
        utleiepris = u;
        lagtUt = new Date();
        eierID = e;
  */

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Boligtype: "), c);

        boligtypeBox = new JComboBox(boligtypeValg);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(boligtypeBox, c);


        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("By: "), c);

        byBox = new JComboBox(byvalg);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(byBox, c);



        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Rom: "), c);

        romBox = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(romBox, c);










        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Ant Etasjer: "), c);


        etasjeBox = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(etasjeBox, c);

        garasjeValg = new JCheckBox("Garasje");
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(garasjeValg,c);


        kjellerValg = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(kjellerValg,c);

        heisValg = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(heisValg,c);

        badValg = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(badValg,c);

        kjøkkenValg = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(kjøkkenValg,c);

        // FANE 3 - VIS TABELL   *********************************************************












    add(fane, BorderLayout.PAGE_START);

/*

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.FIRST_LINE_END;

    panel1.add(bspanel, c);
    panel1.add(utpanel, c);

      //  add(bspanel, BorderLayout.LINE_START);
       // add(utpanel);
       */
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

    private class PersonTypeLytter implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(utleier.isSelected())
            {
                utpanel.setVisible(true);
                bspanel.setVisible(false);
            //    revalidate();
              //  repaint();
            }
            else if(boligsøker.isSelected())
            {
                bspanel.setVisible(true);
                utpanel.setVisible(false);
              //  revalidate();
             //   repaint();

            }


        }
    }






    public void regBoligsøker()
    {

        if(boligsøker.isSelected())
        {

        }
        else if(utleier.isSelected())
        {
            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String email = mail.getText();
            String firma = firma.getText();

            if( fnavn.equals("") || enavn.equals("") || ad.equals("") || email.equals("") || firma.equals("")){
                JOptionPane.showMessageDialog(null, "Fuck du må skrive inn ordentlig");
                return;
                //todo Istedenfor joptpain, endrer vi farge på det feltet som mangler verdier.
            }

            Utleier ny = new Utleier(String n, String a, String t, String e, String f))



        }
        JOptionPane.showMessageDialog(null, "du må velge en av typene person yo");

        String fnavn = fornavn.getText();
        String enavn = etternavn.getText();
        String ad = adresse.getText();
        String email = mail.getText();

        if( fnavn.equals("") || enavn.equals("") || ad.equals("") || email.equals("") ){

        }

    }



    public void regBolig()
    {
        String valg = (String)boligtypeBox.getSelectedItem();
        int btype = 0;
        switch(valg){
            case "Enebolig":    btype = 1;
                                break;
            case "Hybel":       btype = 2;
                                break;
            case "Leilighet":   btype = 3;
                                break;
            case "Rekkehus":    btype = 4;
                                break;
            default:            btype = 1;
                                break;
        }

        String by = (String)byBox.getSelectedItem();
        int byvalg = 0;
        switch(by){
            case "Oslo":        byvalg = 1;
                                break;
            case "Bergen":      byvalg = 2;
                                break;
            case "Stavanger":   byvalg = 3;
                                break;
            case "Trondheim":   byvalg = 4;
                                break;
            case "Kristiansand":byvalg = 5;
                                break;
            case "Tromsø":      byvalg = 6;
                                break;
            default:            byvalg = 1;
                                break;
        }
        int antrom = Integer.parseInt((String)romBox.getSelectedItem());
        int antetasjer = Integer.parseInt((String)etasjeBox.getSelectedItem());

        int kjellerInt = 0;
        int heisInt = 0;
        int garasjeInt = 0;
        int badInt = 0;
        int kjøkkenInt = 0;

        if(kjellerValg.isSelected())
            kjellerInt = 1;
        if(heisValg.isSelected())
            heisInt = 1;
        if(garasjeValg.isSelected())
            garasjeInt = 1;
        if(badValg.isSelected())
            badInt = 1;
        if(kjøkkenValg.isSelected())
            kjøkkenInt = 1;

        Bolig ny = null;

        switch(btype){
            case 1: ny = new Enebolig();
                         break;
            case 2: ny = new Hybel();
                         break;
            case 3: ny = new Leilighet();
                         break;
            case 4: ny = new Rekkehus();
                         break;
        }



    }


    public void regUtleier()
    {
        /*todo
        - Les inn til varz
        - opprett og legg i liste? lag liste først.
        - hvordan registrere boliger som er knyttet til utleieren?
        */
    }


    public void mekkKontrakt()
    {
        /*todo
        - Les inn identifiserende informasjon om søker og utleier fra gui
        - finn og returner begge objektene.
        - mekk en kontrakt med info fra begge.
        */
    }



    public void matchPåKrav()
    {
        /*todo
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
        /*todo
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
