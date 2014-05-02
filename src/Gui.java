import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.SortedSet;
import java.awt.event.KeyEvent;
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
    private JButton regBoligKnapp,regPersonKnapp, regUtleierKnapp;
    private JTextField fornavn, etternavn, adresse,adresseFane2, mail, firma, tlf,boareal,pris,byggår,tomtAreal,utleierId;
    private JLabel minPris,maxPris;
    private JTextArea beskrivelse;
    private JMenuBar menybar = new JMenuBar();
    private JRadioButton utleier, boligsøker;
    private JPanel panel1, bspanel, utpanel, panel2,bopanel, panel3, panel4;
    private JComboBox boligtypeBox,byBox,romBox, etasjeBox,planBox,boligtypeBoxFane2,byBoxFane2,romBoxFane2, etasjeBoxFane2,planBoxFane2;
    private JCheckBox kjellerValg, heisValg, garasjeValg, badValg, kjøkkenValg, balkongValg,kjellerValgFane2, heisValgFane2, garasjeValgFane2, badValgFane2, kjøkkenValgFane2, balkongValgFane2;
    private JSlider minPrisSlider,maxPrisSlider;
    private String[] boligtypeValg = {"Enebolig","Hybel", "Leilighet","Rekkehus"};
    private String[] byvalg = {"Oslo","Bergen","Stavanger","Trondheim","Kristiansand","Tromsø"};
    private String[] romValg = {"1","2","3","4","5","6"};
    private String[] etasjeValg = {"1","2","3"};
    private String[] planValg = {"1","2","3","4","5","6","7"};
    private JTable tabell;
    private JScrollPane scroll,mainScroll;
    private PersonTypeLytter radioLytter;
    private ButtonGroup radioPerson;
    private  Border ramme = BorderFactory.createLineBorder(Color.BLACK);

    private UtleierListe utleiere;
    private BoligsøkerListe boligsøkere;
    private knappLytter lytter;
    private menyLytter øre;
    private Boligliste boliger;
    private JMenuBar menylinje;
    private JMenu filmeny,rediger;
    private JMenuItem om, lagre,angre;

    public Gui()
    {
        super("Boligformidling for svaksynte");

        filmeny = new JMenu("Fil");
        filmeny.setMnemonic('F');


        om = new JMenuItem("Om..");
       // om.setMnemonic('O');
        om.addActionListener(øre);


        lagre = new JMenuItem("Lagre");
        lagre.addActionListener(øre);


        rediger = new JMenu("Rediger");

        angre = new JMenuItem("Angre");
        angre.addActionListener(øre);


        rediger.add(angre);
        filmeny.add(om);
        filmeny.add(lagre);

        // add(filmeny);


        menylinje = new JMenuBar();
        setJMenuBar(menylinje);
        menylinje.add(filmeny);
        menylinje.add(rediger);


//menyslutt


        utleiere = new UtleierListe();
        boligsøkere = new BoligsøkerListe();
        boliger = new Boligliste();
        lytter = new knappLytter();

        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;


        //Oppretter panelene

        panel1 = new JPanel(layout);
        panel2 = new JPanel(layout);
        panel3 = new JPanel(layout);
        panel4 = new JPanel(layout);
        utpanel = new JPanel(layout);
        bspanel = new JPanel(layout);
        bopanel = new JPanel(layout);
        panel1.setVisible(true);
            c.gridx = 8;
            c.gridy = 0;
            c.gridwidth = 2;
            c.gridheight = 9;
            c.insets = new Insets(5,5,5,5);
            c.anchor = GridBagConstraints.FIRST_LINE_START;
        panel1.add(bspanel, c);
            c.gridheight = 1;

        panel1.add(utpanel, c);
        panel2.add(bopanel, c);

       // add(utpanel,BorderLayout.LINE_END);
        //add(bspanel,BorderLayout.LINE_END);



        //til hit
        bopanel.setVisible(true);
        bspanel.setVisible(false); // endre tilbake til, false
        utpanel.setVisible(false);

        //oppretter Fanene

        fane.addTab("Registrer Person",null,panel1 ,"Registrere ny boligsøker eller utleier");
        fane.addTab("Registrer bolig",null,panel2,"Registrere ny bolig");
        fane.addTab("Vis tabell",null,panel3,"Show tabell");
        fane.addTab("MatchMaking",null,panel4,"Tinde");
        fane.setMnemonicAt(0, KeyEvent.VK_1);
        fane.setMnemonicAt(1, KeyEvent.VK_2);
        fane.setMnemonicAt(2, KeyEvent.VK_3);
        fane.setMnemonicAt(3, KeyEvent.VK_4);
        //todo- OSEN: Funker dette på windows?




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

        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 0;
        panel1.add(new JLabel("Telefon: "), c);

        tlf = new JTextField();
        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(tlf, c);













        radioLytter = new PersonTypeLytter();
        radioPerson = new ButtonGroup();
        utleier = new JRadioButton("Utleier",false);

        utleier.addActionListener(radioLytter);
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(utleier,c);

        c.ipadx = 0;
        c.gridx = 1;
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

        regUtleierKnapp = new JButton("Registrer");
        regUtleierKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 15;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10,5,5,5);
        utpanel.add(regUtleierKnapp,c);


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



        minPris = new JLabel("");
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(minPris, c);



        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;


        minPrisSlider = new JSlider();
        minPrisSlider.setMinimum(0);
        minPrisSlider.setMaximum(50000);
        minPrisSlider.setValue(250);
        minPrisSlider.setMajorTickSpacing(10000);
        minPrisSlider.setMinorTickSpacing(1000);
        minPrisSlider.setPaintTicks(true);
        minPrisSlider.setPaintLabels(true);
        minPrisSlider.addChangeListener(new minPrisLytter());
        minPrisSlider.setSnapToTicks(true);


        bspanel.add(minPrisSlider, c);
        //todo koble riktige verdier

        maxPris = new JLabel("");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(maxPris, c);

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
        maxPrisSlider.setMinorTickSpacing(1000);
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

        balkongValg = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(balkongValg,c);


        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Plan: "), c);

        regPersonKnapp = new JButton("Registrer");
        regPersonKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 15;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10,5,5,5);
        bspanel.add(regPersonKnapp,c);

        planBox = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(planBox, c);


        // FANE NR 2, REGISTRER NY BOLIg *****************************************************************************

        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,0,0);



        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Adresse: "), c);

        adresseFane2 = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        bopanel.add(adresseFane2,c);


        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("By: "), c);

        byBoxFane2 = new JComboBox(byvalg);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(byBoxFane2, c);


        c.gridx = 0;
        c.gridy = 2;
        bopanel.add(new JLabel("Boareal"),c);

        boareal = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        bopanel.add(boareal,c);

        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Rom: "), c);

        romBoxFane2 = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(romBoxFane2, c);



        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Ant Etasjer: "), c);


        etasjeBoxFane2 = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(etasjeBoxFane2, c);


        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Boligtype: "), c);

        boligtypeBoxFane2 = new JComboBox(boligtypeValg);
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(boligtypeBoxFane2, c);




        garasjeValgFane2 = new JCheckBox("Garasje");
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(garasjeValgFane2,c);


        kjellerValgFane2 = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(kjellerValgFane2,c);

        heisValgFane2 = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(heisValgFane2,c);

        badValgFane2 = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(badValgFane2,c);

        kjøkkenValgFane2 = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(kjøkkenValgFane2,c);

        balkongValgFane2 = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(balkongValgFane2,c);

        c.gridx = 0;
        c.gridy = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Utleiepris:"),c);

        pris = new JTextField();
        c.gridx = 1;
        c.gridy = 9;
        bopanel.add(pris,c);


        c.gridx = 0;
        c.gridy = 10;
        bopanel.add(new JLabel("Byggår"),c);

        byggår = new JTextField();
        c.gridx = 1;
        c.gridy = 10;
        bopanel.add(byggår,c );


        c.gridx = 0;
        c.gridy = 11;
        bopanel.add(new JLabel("Beskrivelse:"),c);

        beskrivelse = new JTextArea("Skriv da..",5,10);
        JScrollPane scroll = new JScrollPane(beskrivelse);
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        bopanel.add(scroll,c);


        c.gridx = 0;
        c.gridy = 12;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("TomtArael:"),c);

        tomtAreal = new JTextField();
        c.gridx = 1;
        c.gridy = 12;
        bopanel.add(tomtAreal,c);

        c.gridx = 0;
        c.gridy = 13;
        bopanel.add(new JLabel("Hilket plan?"),c);


        planBoxFane2 = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 13;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(planBoxFane2, c);









        c.gridx = 0;
        c.gridy = 14;
        bopanel.add(new JLabel("Utleier ID:"),c);

        utleierId = new JTextField();
        c.gridx =  1;
        c.gridy = 14;
        bopanel.add(utleierId,c);





        regBoligKnapp = new JButton("Registrer");
        regBoligKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 15;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10,5,5,5);
        bopanel.add(regBoligKnapp,c);















        // FANE 3 - VIS TABELL   *********************************************************
        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = 1;










        fane.setBackground(Color.red);
        panel1.setBackground(Color.BLUE);





        fane.setPreferredSize(new Dimension(getSize()));
        add(new JScrollPane(fane), BorderLayout.PAGE_START);

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
    private class knappLytter implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == regBoligKnapp)
                regBolig();
            else if(e.getSource() == regPersonKnapp)
                regPerson();
            else if( e.getSource() == om )
                JOptionPane.showMessageDialog(null,"Dette er et program" );
            else if(e.getSource() == lagre)
                JOptionPane.showMessageDialog(null,"Save the rave" );
            else if(e.getSource() == regUtleierKnapp)
                regPerson();
        }
    }



    private class menyLytter implements ActionListener
    {
        public void actionPerformed(ActionEvent o)
        {
            if( o.getSource() == om )
            JOptionPane.showMessageDialog(null,"Dette er et program" );
           else if(o.getSource() == lagre)
            JOptionPane.showMessageDialog(null,"Save the rave" );
        }
    }







    private class minPrisLytter implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            minPris.setText("Min Pris: " + minPrisSlider.getValue());

        }
    }

    private class maxPrisLytter implements ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            maxPris.setText("Max Pris :" + maxPrisSlider.getValue());
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






    public void regPerson()
    {

        /*int blgtp, int b, int r, int
                  map, int mip, int p, int ae, int k, int mit, int mat, int h,
                  int blkng, int dbm, int dkm

                   0 Boligtype
    1 by
    2 rom
    3 minPris
    4 maxPris
    5 parkering
    6 antEtasjer
    7 kjeller
    8 minTomt
    9 maxTomt
    10 heis
    11 balkong
    12 delerBadMed
    13 delerKjøkkenMed*/

        if(boligsøker.isSelected())
        {
            int bt, by, rom, minPris, maxPris, park, antE, kjeller, minTomt, maxTomt, heis, balkong, dbm, dkm, plan;
            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String t = tlf.getText();
            String email = mail.getText();
            JOptionPane.showMessageDialog(null, "Fuck du må skrive inn ordentlig ting i feltene når du skal regge en boligsøker" + fnavn + enavn + ad + email);
            // Bestemm Boligtype
            String bType = (String) boligtypeBox.getSelectedItem();
            switch (bType)
            {
                case "Enebolig" : bt = 1;
                    break;
                case "Rekkehus" : bt = 2;
                    break;
                case "Leilighet" : bt = 3;
                    break;
                case "Hybel" : bt = 4;
                    break;
                default: bt = 0;
                    break;
            }

            // Bestem by "Oslo","Bergen","Stavanger","Trondheim","Kristiansand","Tromsø"};
            String byInn = (String) byBox.getSelectedItem();

            switch(byInn){
                case "Oslo":        by = 1;
                    break;
                case "Bergen":      by = 2;
                    break;
                case "Stavanger":   by = 3;
                    break;
                case "Trondheim":   by = 4;
                    break;
                case "Kristiansand":by = 5;
                    break;
                case "Tromsø":      by = 6;
                    break;
                default:            by = 0;
                    break;
            }

            rom = Integer.parseInt((String)romBox.getSelectedItem());

            minPris = (int) minPrisSlider.getValue();

            maxPris = (int) maxPrisSlider.getValue();

            antE = Integer.parseInt((String)etasjeBox.getSelectedItem());

            park = 0;
            heis = 0;
            balkong = 0;
            dbm = 0;
            dkm = 0;
            kjeller = 0;

            if(garasjeValg.isSelected())
                park = 1;
            if(heisValg.isSelected())
                heis = 1;
            if(kjellerValg.isSelected())
                kjeller = 0;
            if(badValg.isSelected())
                dbm = 1;
            if(kjøkkenValg.isSelected())
                dkm = 1;
            if(balkongValg.isSelected())
                balkong = 1;

            plan = Integer.parseInt((String)planBox.getSelectedItem());

            if( fnavn.equals("") || enavn.equals("") || ad.equals("") || email.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Fuck du må skrive inn ordentlig ting i feltene når du skal regge en boligsøker" + fnavn + enavn + ad + email);
                return;
                //todo Istedenfor joptpain, endrer vi farge på det feltet som mangler verdier.
            }
            JOptionPane.showMessageDialog(null, "Reg person, helt nederst i boligsøker");
            Boligsøker ny = new Boligsøker(fnavn, enavn, ad, t, email, bt, by, rom, maxPris, minPris, park, antE, kjeller, heis, balkong, dbm, dkm );
            boligsøkere.settInnNy(ny);
            return;
        }

        // regger utleier
        else if(utleier.isSelected())
        {
            JOptionPane.showMessageDialog(null, "du har helt klart valgt utleier nå");
            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String t = tlf.getText();
            String email = mail.getText();
            String firm = firma.getText();

            if( fnavn.equals("") || enavn.equals("") || ad.equals("") || email.equals("") || firm.equals("")){
                JOptionPane.showMessageDialog(null, "Fuck du må skrive inn ordentlig");
                return;
                //todo Istedenfor joptpain, endrer vi farge på det feltet som mangler verdier.
            }

            Utleier ny = new Utleier(fnavn, enavn, ad ,t ,email, firm);
            JOptionPane.showMessageDialog(null, "nederst på utleier, rett over add");
            utleiere.settInn(ny);
            return;
        }
        JOptionPane.showMessageDialog(null, "du må velge en av typene...");
    }


    public void regBolig()
    {
        JOptionPane.showMessageDialog(null, "Regbolig kjører");
        String adr = adresse.getText();
        int areal = Integer.parseInt(boareal.getText());
        int år = Integer.parseInt(byggår.getText());
        int upris = Integer.parseInt(pris.getText());

        // disse må knyttes til felter senere
        // todo christe rædd feltene
        int eid = 34;
        int tomtareal = 100;
        int plan = 3;
        int balko = -1;

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
        int rom = Integer.parseInt((String)romBox.getSelectedItem());
        int antetasjer = Integer.parseInt((String)etasjeBox.getSelectedItem());

        int kjeller = -1;
        int heis = -1;
        int garasje = -1;
        int badInt = -1;
        int kjøkkenInt = -1;
        int balkong = -1;

        if(kjellerValg.isSelected())
            kjeller = 1;
        if(heisValg.isSelected())
            heis = 1;
        if(garasjeValg.isSelected())
            garasje = 1;
        if(balkongValgFane2.isSelected())
            balkong = 1;
        if(badValg.isSelected())
            badInt = 1;
        if(kjøkkenValg.isSelected())
            kjøkkenInt = 1;

        Bolig ny = null;


        switch(btype){
            case 1: Enebolig nyEnebolig = new Enebolig(adr,byvalg, areal, rom, år, upris, eid, antetasjer, garasje, kjeller, tomtareal);
                         boliger.leggTil(nyEnebolig);
                         break;
            case 2: Rekkehus nyttRekkehus = new Rekkehus(adr,byvalg, areal, rom, år, upris, eid, antetasjer,garasje, kjeller, tomtareal);
                         boliger.leggTil(nyttRekkehus);
                         break;
            case 3: Leilighet nyLeilighet = new Leilighet(adr,byvalg, areal, rom, år, upris, eid, plan, balko, heis);
                         boliger.leggTil(nyLeilighet);
                         break;
            case 4: Hybel nyHybel = new Hybel(adr,byvalg, areal, rom, år, upris, eid, badInt, kjøkkenInt);
                         boliger.leggTil(nyHybel);
                         break;
        }
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




        *//*
        Int[] krav = en array;
        int[] specs = en annen array;

        if(krav[0] == 1)
        {
            SortedSet<Enebolig> eneboliger = boliger.getEneboliger();
            Iterator<Enebolig> iter = eneboliger.iterator();



        }*/
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
