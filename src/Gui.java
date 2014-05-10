import javafx.geometry.HorizontalDirection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.*;
import java.rmi.server.UID;
import java.util.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.DefaultRowSorter;
import javax.swing.text.Document;
import java.lang.*;
import javax.swing.RowSorter.*;


/**
 * Created by mac on 02.04.14.
 */

public class Gui extends JFrame {
    private JTabbedPane fane = new JTabbedPane();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private JButton regBoligKnapp, regPersonKnapp, regUtleierKnapp, finnBildeKnapp, oppdaterKontrakter, lagreKontrakt, velgUtleierKnapp, velgLeietakerKnapp,velgBoligKnapp,finnMatch, velgUtleier,sendMail,slettPerson,slettBoligKnapp;
    private JTextField fornavn, etternavn, adresse, adresseFane2, mail, firma, tlf, boareal, pris, byggår, tomtAreal, utleierId, bildesti,valgtUtleier, valgtLeietaker,valgtBolig, startDagFelt, startMånedFelt, startÅrFelt, sluttDagFelt, sluttMånedFelt, sluttårFelt;
    private JLabel boligtypeLabel, minPris, maxPris, firmaLabel,tomtArealLabel, antEgtLabel,boligsøkerOverskrift,antEgtLabelFane2, utleierLabel, kontraktHeader,regPersonHeader,navnLabel, re, planLabel, regBoligHeader, planLabelFane2, romLabelFane2;
    private JTextArea beskrivelse,feedbackFane1,feedbackFane3;
    private JMenuBar menybar = new JMenuBar();
    private JRadioButton utleier, boligsøker,persontabellRadioknapp,boligtabellRadioknapp;
    private JPanel panel1, bspanel, utpanel, panel2, bopanel, panel3, panel4, pepanel,tapanel,panel5,resultatPanel,velgBsPanel,bildepanel, btpanel;
    private JComboBox boligtypeBox, byBox, romBox, etasjeBox, planBox, boligtypeBoxFane2, byBoxFane2, romBoxFane2, etasjeBoxFane2, planBoxFane2;
    private JCheckBox kjellerValg, heisValg, garasjeValg, røykerValg, husdyrValg, badValg, kjøkkenValg, balkongValg, kjellerValgFane2, heisValgFane2, garasjeValgFane2, badValgFane2, kjøkkenValgFane2, balkongValgFane2;
    private JSlider minPrisSlider, maxPrisSlider;
    private String[] boligtypeValg = {"Velg boligtype..", "Enebolig", "Hybel", "Leilighet", "Rekkehus"};
    private String[] byvalg = {"Velg by..", "Oslo", "Bergen", "Stavanger", "Trondheim", "Kristiansand", "Tromsø"};
    private String[] romValg = {"Velg ant. rom..", "1", "2", "3", "4", "5", "6"};
    private String[] etasjeValg = {"Velg ant. etg..", "1", "2", "3"};
    private String[] planValg = {"Velg ant. plan", "1", "2", "3", "4", "5", "6", "7"};
    private String[] kontraktTabellKolonneNavn = {"Eier,","Leietaker","Startdato","Sluttdato"};
    private JTable personTabell, boligTabellTabellen, kontraktHistorikkTabell, utleierValgTabell, leietakerValgTabell,visBoligsøkereTabell,visBoligTabell,resultatTabell,boligSøkereForMatch,boligValgTabell;
    private JScrollPane scroll, mainScroll;
    private PersonTypeLytter radioLytter;
    private tabellTypeLytter radioTabellLytter;
    private ButtonGroup radioPerson, radioTabell;
    private Border ramme = BorderFactory.createLineBorder(Color.BLACK);
    private UtleierListe utleiere;
    private BoligsøkerListe boligsøkere;
    private KontraktListe kontrakter;
    private Mail epost;
    private KontraktHistorikk kontrakthistorie;
    private knappLytter lytter;
    private menyLytter øre;
    private Boligliste boliger;
    private JMenuBar menylinje;
    private JMenu filmeny, rediger,matching,kontraktHistorikk;
    private JMenuItem om, lagre, angre,tabell,oppdaterBoligsøkerTabell, visHistorikk;
    private JScrollPane personTabellScroll;
    private JScrollPane boligTabellScroll;
    private JFrame velgUtleierVindu, velgLeietakerVindu, velgBoligVindu;
    private Container kassa;
    private String valgtId, valgtBoligId, id, slettPersonFn,slettPersonEn;
    private int slettBoligId;
    private Font headerFont;
    private Color bakFarge, headerFarge,lyseSvart, comboboxFarge;


    //private JScrollPane personTabellScroll,boligTabellScroll, kontraktHistorikkTabellScroll;
    //private JTextArea utskriftsområde;
    private fanelytter faneøre;
    //private Utvalgslytter lsm;


    public Gui() {
        super("Boligformidling for svaksynte");

        headerFont = new Font("Arial",Font.BOLD,50);

        øre = new menyLytter();

        // temp, start filmeny
        filmeny = new JMenu("Fil");
        filmeny.setMnemonic('F');


        om = new JMenuItem("Om..");
        // om.setMnemonic('O');
        om.addActionListener(øre);


        lagre = new JMenuItem("Lagre");
        lagre.addActionListener(øre);

        tabell = new JMenuItem("Last inn tabell på nytt");//bate temp ass
        tabell.addActionListener(øre);

        //kontraktHistorikkTabell = new JTable(kontrakter.tilTabell(),kontraktTabellKolonneNavn);

        rediger = new JMenu("Rediger");

        angre = new JMenuItem("Angre");
        angre.addActionListener(øre);

        matching = new JMenu("Matching");
        oppdaterBoligsøkerTabell = new JMenuItem("Oppdater BS tabell");
        oppdaterBoligsøkerTabell.addActionListener(øre);

        kontraktHistorikk = new JMenu("Kontrakthistorikk");
        visHistorikk = new JMenuItem("Vis kontrakhistorikk");
        visHistorikk.addActionListener(øre);

        rediger.add(angre);
        filmeny.add(om);
        filmeny.add(lagre);
        rediger.add(tabell);
        matching.add(oppdaterBoligsøkerTabell);
        kontraktHistorikk.add(visHistorikk);


        menylinje = new JMenuBar();
        setJMenuBar(menylinje);
        menylinje.add(filmeny);
        menylinje.add(rediger);
        menylinje.add(matching);
        menylinje.add(kontraktHistorikk);


//temp, filmeny slutt
        boliger = new Boligliste();
        utleiere = new UtleierListe(boliger);
        boligsøkere = new BoligsøkerListe();
        kontrakter = new KontraktListe();
        epost = new Mail();
        kontrakthistorie = new KontraktHistorikk();
        lytter = new knappLytter();
        faneøre = new fanelytter();
        personTabellScroll= new JScrollPane(personTabell);
        boligTabellScroll=new JScrollPane(boligTabellTabellen);



        //alt dette bare for skjermstørrelsen hehehe

        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
//        int bredde = (int) (Math.round(skjerm.width * 0.80));
  //      int høyde = (int) (Math.round(skjerm.height * 0.80));
        //setSize(bredde, høyde);
        setLocationByPlatform(true);
        setSize(1400,1050);

        int bredde = getWidth();
        int høyde = getHeight();
        System.out.println("Skjermstr: " + bredde + "x" + høyde);

        //todo Finne en fin skjermstørrelse, er nå 80% av skjermstr.


        //Oppretter panelene

        panel1 = new JPanel(layout);  // FANE panel
        // panel1 = new JPanel(new BorderLayout());  // FANE panel
        panel2 = new JPanel(layout);  // FANE panel
        panel3 = new JPanel(layout);  // FANE panel
        panel4 = new JPanel(layout);  // FANE panel
        utpanel = new JPanel(layout); // Utleierpanel
        bspanel = new JPanel(layout); // Boligsøkerpanel
        bopanel = new JPanel(layout); // Boligpanel
        pepanel = new JPanel(layout); // PersonPanel
        tapanel = new JPanel(layout);//tabellpanel
        resultatPanel = new JPanel(layout);
        panel5 = new JPanel(layout);
        velgBsPanel = new JPanel(layout);
        bildepanel = new JPanel(new BorderLayout());
        btpanel = new JPanel(layout);



        //FARGER

        headerFarge = new Color(24,76,95);
        bakFarge = new Color(187,175,122);
        lyseSvart = new Color(38,38,38);
        comboboxFarge = new Color(163,123,69);






        panel1.setBackground(bakFarge);
        panel2.setBackground(bakFarge);
        bspanel.setBackground(bakFarge);
        pepanel.setBackground(bakFarge);
        pepanel.setForeground(lyseSvart);
        btpanel.setBackground(bakFarge);
        btpanel.setForeground(lyseSvart);
        bopanel.setBackground(bakFarge);





        regPersonHeader = new JLabel("Registrer en klient");
        regPersonHeader.setFont(headerFont);
        regPersonHeader.setForeground(headerFarge);
        c.gridx = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0,0,50,0);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(regPersonHeader,c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        panel1.add(pepanel,c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(30,150,0,0);
        c.weighty = -10;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,0,0,0);
        bspanel.setSize(bspanel.getPreferredSize());

        panel1.add(bspanel,c);
        c.insets = new Insets(50,0,0,0);
        panel1.add(utpanel,c);
       // bspanel.setPreferredSize(bspanel.getPreferredSize());

        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        c.insets = new Insets(0,20,0,0);
        c.weighty = -10;
        panel1.add(btpanel,c);


        regPersonKnapp = new JButton("Registrer");
        regPersonKnapp.addActionListener(lytter);
        c.gridx = 2;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(0, 0, 0, 0);
        regPersonKnapp.setVisible(false);
        panel1.add(regPersonKnapp, c);


        feedbackFane1 = new JTextArea("");
        feedbackFane1.setEditable(false);
        feedbackFane1.setBackground(bakFarge);
        feedbackFane1.setPreferredSize(new Dimension(200,20));
        c.gridx = 0;
        c.gridy = 4;

        panel1.add(feedbackFane1,c);





        panel2.add(bopanel);









        //til hit
        bopanel.setVisible(true);
        bspanel.setVisible(false); // endre tilbake til, false
        utpanel.setVisible(false);
        pepanel.setVisible(true);
        tapanel.setVisible(true);
        resultatPanel.setVisible(true);
        velgBsPanel.setVisible(true);
        //oppretter Fanene

        fane.addTab("Registrer Person", null, panel1, "Registrere ny boligsøker eller utleier");
        fane.addTab("Registrer bolig", null, panel2, "Registrere ny bolig");
        fane.addTab("Vis tabell", null, panel3, "Show tabell");
        fane.addTab("MatchMaking", null, panel4, "Tinde");
        fane.addTab("Kontrakter", null, new JScrollPane(panel5), "Registrer og se kontrakter");
        fane.setMnemonicAt(0, KeyEvent.VK_1);
        fane.setMnemonicAt(1, KeyEvent.VK_2);
        fane.setMnemonicAt(2, KeyEvent.VK_3);
        fane.setMnemonicAt(3, KeyEvent.VK_4);
        fane.setMnemonicAt(4, KeyEvent.VK_5);

        fane.addChangeListener(faneøre);






/*

        PPPPPPPPPPPPPPPPP   EEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR      SSSSSSSSSSSSSSS      OOOOOOOOO     NNNNNNNN        NNNNNNNNEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR
        P::::::::::::::::P  E::::::::::::::::::::ER::::::::::::::::R   SS:::::::::::::::S   OO:::::::::OO   N:::::::N       N::::::NE::::::::::::::::::::ER::::::::::::::::R
        P::::::PPPPPP:::::P E::::::::::::::::::::ER::::::RRRRRR:::::R S:::::SSSSSS::::::S OO:::::::::::::OO N::::::::N      N::::::NE::::::::::::::::::::ER::::::RRRRRR:::::R
        PP:::::P     P:::::PEE::::::EEEEEEEEE::::ERR:::::R     R:::::RS:::::S     SSSSSSSO:::::::OOO:::::::ON:::::::::N     N::::::NEE::::::EEEEEEEEE::::ERR:::::R     R:::::R
        P::::P     P:::::P  E:::::E       EEEEEE  R::::R     R:::::RS:::::S            O::::::O   O::::::ON::::::::::N    N::::::N  E:::::E       EEEEEE  R::::R     R:::::R
        P::::P     P:::::P  E:::::E               R::::R     R:::::RS:::::S            O:::::O     O:::::ON:::::::::::N   N::::::N  E:::::E               R::::R     R:::::R
        P::::PPPPPP:::::P   E::::::EEEEEEEEEE     R::::RRRRRR:::::R  S::::SSSS         O:::::O     O:::::ON:::::::N::::N  N::::::N  E::::::EEEEEEEEEE     R::::RRRRRR:::::R
        P:::::::::::::PP    E:::::::::::::::E     R:::::::::::::RR    SS::::::SSSSS    O:::::O     O:::::ON::::::N N::::N N::::::N  E:::::::::::::::E     R:::::::::::::RR
        P::::PPPPPPPPP      E:::::::::::::::E     R::::RRRRRR:::::R     SSS::::::::SS  O:::::O     O:::::ON::::::N  N::::N:::::::N  E:::::::::::::::E     R::::RRRRRR:::::R
        P::::P              E::::::EEEEEEEEEE     R::::R     R:::::R       SSSSSS::::S O:::::O     O:::::ON::::::N   N:::::::::::N  E::::::EEEEEEEEEE     R::::R     R:::::R
        P::::P              E:::::E               R::::R     R:::::R            S:::::SO:::::O     O:::::ON::::::N    N::::::::::N  E:::::E               R::::R     R:::::R
        P::::P              E:::::E       EEEEEE  R::::R     R:::::R            S:::::SO::::::O   O::::::ON::::::N     N:::::::::N  E:::::E       EEEEEE  R::::R     R:::::R
        PP::::::PP          EE::::::EEEEEEEE:::::ERR:::::R     R:::::RSSSSSSS     S:::::SO:::::::OOO:::::::ON::::::N      N::::::::NEE::::::EEEEEEEE:::::ERR:::::R     R:::::R
        P::::::::P          E::::::::::::::::::::ER::::::R     R:::::RS::::::SSSSSS:::::S OO:::::::::::::OO N::::::N       N:::::::NE::::::::::::::::::::ER::::::R     R:::::R
        P::::::::P          E::::::::::::::::::::ER::::::R     R:::::RS:::::::::::::::SS    OO:::::::::OO   N::::::N        N::::::NE::::::::::::::::::::ER::::::R     R:::::R
        PPPPPPPPPP          EEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR SSSSSSSSSSSSSSS        OOOOOOOOO     NNNNNNNN         NNNNNNNEEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR
*/









        // Reseter
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridheight = 1;
        c.gridwidth=0;
        c.weightx=0;



        //Inndatafelt
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        pepanel.add(new JLabel("Fornavn: "), c);





        //navnLabel  = new JLabel("NAVN");
        //navnLabel.setComponentPopupMenu();






        fornavn = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        fornavn.getDocument().addDocumentListener(documentListener);

        //c.ipadx = ;
        pepanel.add(fornavn, c);



        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        pepanel.add(new JLabel("Etternavn: "), c);

        etternavn = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 100;
            etternavn.getDocument().addDocumentListener(documentListener);



        pepanel.add(etternavn, c);


        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 0;
        c.anchor = GridBagConstraints.EAST;
        pepanel.add(new JLabel("Adresse: "), c);

        adresse = new JTextField();

        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        pepanel.add(adresse, c);

        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 0;
        pepanel.add(new JLabel("Mail: "), c);

        mail = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        pepanel.add(mail, c);

        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 0;
        pepanel.add(new JLabel("Telefon: "), c);

        tlf = new JTextField();
        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        pepanel.add(tlf, c);


        radioLytter = new PersonTypeLytter();
        radioPerson = new ButtonGroup();
        utleier = new JRadioButton("Utleier", false);

        utleier.addActionListener(radioLytter);
        utleier.setBackground(bakFarge);
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        pepanel.add(utleier, c);

        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;

        boligsøker = new JRadioButton("Boligsøker", false);
        boligsøker.addActionListener(radioLytter);
        boligsøker.setBackground(bakFarge);
        pepanel.add(boligsøker, c);

        radioPerson.add(utleier);
        radioPerson.add(boligsøker);


        firmaLabel = new JLabel("Firma: ");
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        firmaLabel.setVisible(false);
        pepanel.add(firmaLabel, c);

        firma = new JTextField();
        c.gridx = 1;
        c.gridy = 6;
        c.ipadx = 100;
        c.fill = GridBagConstraints.HORIZONTAL;
        firma.setVisible(false);
        pepanel.add(firma, c);

        regUtleierKnapp = new JButton("Registrer");
        regUtleierKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 15;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 5, 5, 5);
        regUtleierKnapp.setVisible(false);
        pepanel.add(regUtleierKnapp, c);


        //BOLISØKER PANEL (bspanel)

        c.ipadx= 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.insets = new Insets(5, 10, 10, 5);
        boligtypeLabel = new JLabel("Boligtype");
        bspanel.add(boligtypeLabel,c);

        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0;
        boligtypeBox = new JComboBox(boligtypeValg);
        boligtypeBox.setBackground(bakFarge);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        bspanel.add(boligtypeBox, c);
        boligtypeBox.addActionListener(new boligTypeLytter());


        c.gridx = 0;
        c.gridy = 1;

        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        bspanel.add(new JLabel("By: "), c);

        byBox = new JComboBox(byvalg);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        byBox.addActionListener(new boligTypeLytter());
        bspanel.add(byBox, c);


        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        bspanel.add(new JLabel("Rom: "), c);


        romBox = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        romBox.addActionListener(new boligTypeLytter());
        bspanel.add(romBox, c);


        minPris = new JLabel("Min Pris: 0");
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        bspanel.add(minPris, c);


        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth =2;
        c.ipadx = 100;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;


        minPrisSlider = new JSlider();
        minPrisSlider.setMinimum(0);
        minPrisSlider.setMaximum(50000);
        minPrisSlider.setValue(0);
        minPrisSlider.setMajorTickSpacing(10000);
        minPrisSlider.setMinorTickSpacing(1000);
        minPrisSlider.setPaintTicks(true);
        minPrisSlider.setPaintLabels(true);
        minPrisSlider.addChangeListener(new minPrisLytter());
        minPrisSlider.setSnapToTicks(true);
        bspanel.add(minPrisSlider, c);


        maxPris = new JLabel("Max Pris: ");
        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        bspanel.add(maxPris, c);


        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 100;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;

        maxPrisSlider = new JSlider();
        maxPrisSlider.setMinimum(0);
        maxPrisSlider.setMaximum(50000);
        maxPrisSlider.setValue(50000);
        maxPrisSlider.setMajorTickSpacing(10000);
        maxPrisSlider.setMinorTickSpacing(1000);
        maxPrisSlider.setPaintTicks(true);
        maxPrisSlider.setPaintLabels(true);
        maxPrisSlider.addChangeListener(new maxPrisLytter());
        maxPrisSlider.setSnapToTicks(true);
        bspanel.add(maxPrisSlider, c);


        røykerValg = new JCheckBox("Røker");
        c.ipadx = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        røykerValg.setVisible(true);
        bspanel.add(røykerValg, c);


        husdyrValg = new JCheckBox("Husdyr");
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        husdyrValg.setVisible(true);
        bspanel.add(husdyrValg, c);







        // BoligtypePanel

        antEgtLabel = new JLabel("Ant Etasjer: ");
        c.gridx = 0;
        c.ipadx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        antEgtLabel.setVisible(false);
        btpanel.add(antEgtLabel, c);


        etasjeBox = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        etasjeBox.setVisible(false);
        btpanel.add(etasjeBox, c);

        garasjeValg = new JCheckBox("Garasje");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        garasjeValg.setVisible(false);
        btpanel.add(garasjeValg, c);




        kjellerValg = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        kjellerValg.setVisible(false);
        btpanel.add(kjellerValg, c);





        heisValg = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        heisValg.setVisible(false);
        btpanel.add(heisValg, c);




        balkongValg = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        balkongValg.setVisible(false);
        btpanel.add(balkongValg, c);




        badValg = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        badValg.setVisible(false);
        btpanel.add(badValg, c);






        kjøkkenValg = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;;
        kjøkkenValg.setVisible(false);
        btpanel.add(kjøkkenValg, c);












        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        planLabel = new JLabel("Plan: ");
        btpanel.add(planLabel, c);
        planLabel.setVisible(false);




        planBox = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        planBox.addActionListener(new boligTypeLytter());
        btpanel.add(planBox, c);
        planBox.setVisible(false);



        /*
        BBBBBBBBBBBBBBBBB        OOOOOOOOO     LLLLLLLLLLL             IIIIIIIIII      GGGGGGGGGGGGGEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR
        B::::::::::::::::B     OO:::::::::OO   L:::::::::L             I::::::::I   GGG::::::::::::GE::::::::::::::::::::ER::::::::::::::::R
        B::::::BBBBBB:::::B  OO:::::::::::::OO L:::::::::L             I::::::::I GG:::::::::::::::GE::::::::::::::::::::ER::::::RRRRRR:::::R
        BB:::::B     B:::::BO:::::::OOO:::::::OLL:::::::LL             II::::::IIG:::::GGGGGGGG::::GEE::::::EEEEEEEEE::::ERR:::::R     R:::::R
        B::::B     B:::::BO::::::O   O::::::O  L:::::L                 I::::I G:::::G       GGGGGG  E:::::E       EEEEEE  R::::R     R:::::R
        B::::B     B:::::BO:::::O     O:::::O  L:::::L                 I::::IG:::::G                E:::::E               R::::R     R:::::R
        B::::BBBBBB:::::B O:::::O     O:::::O  L:::::L                 I::::IG:::::G                E::::::EEEEEEEEEE     R::::RRRRRR:::::R
        B:::::::::::::BB  O:::::O     O:::::O  L:::::L                 I::::IG:::::G    GGGGGGGGGG  E:::::::::::::::E     R:::::::::::::RR
        B::::BBBBBB:::::B O:::::O     O:::::O  L:::::L                 I::::IG:::::G    G::::::::G  E:::::::::::::::E     R::::RRRRRR:::::R
        B::::B     B:::::BO:::::O     O:::::O  L:::::L                 I::::IG:::::G    GGGGG::::G  E::::::EEEEEEEEEE     R::::R     R:::::R
        B::::B     B:::::BO:::::O     O:::::O  L:::::L                 I::::IG:::::G        G::::G  E:::::E               R::::R     R:::::R
        B::::B     B:::::BO::::::O   O::::::O  L:::::L         LLLLLL  I::::I G:::::G       G::::G  E:::::E       EEEEEE  R::::R     R:::::R
        BB:::::BBBBBB::::::BO:::::::OOO:::::::OLL:::::::LLLLLLLLL:::::LII::::::IIG:::::GGGGGGGG::::GEE::::::EEEEEEEE:::::ERR:::::R     R:::::R
        B:::::::::::::::::B  OO:::::::::::::OO L::::::::::::::::::::::LI::::::::I GG:::::::::::::::GE::::::::::::::::::::ER::::::R     R:::::R
        B::::::::::::::::B     OO:::::::::OO   L::::::::::::::::::::::LI::::::::I   GGG::::::GGG:::GE::::::::::::::::::::ER::::::R     R:::::R
        BBBBBBBBBBBBBBBBB        OOOOOOOOO     LLLLLLLLLLLLLLLLLLLLLLLLIIIIIIIIII      GGGGGG   GGGGEEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR

*/








        // FANE NR 2, REGISTRER NY BOLIg *****************************************************************************

        //RESETER
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);


        c.insets = new Insets(0, 0, 50, 0);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        regBoligHeader = new JLabel("Registrer bolig");
        regBoligHeader.setFont(headerFont);
        regBoligHeader.setForeground(headerFarge);
        bopanel.add(regBoligHeader, c);




        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 5);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Adresse: "), c);

        adresseFane2 = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(adresseFane2, c);


        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("By: "), c);

        byBoxFane2 = new JComboBox(byvalg);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        byBoxFane2.setBackground(comboboxFarge);
        byBoxFane2.setForeground(bakFarge);
        bopanel.add(byBoxFane2, c);

        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Boligtype: "), c);

        boligtypeBoxFane2 = new JComboBox(boligtypeValg);
        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        boligtypeBoxFane2.addActionListener(new boligTypeLytter());
        bopanel.add(boligtypeBoxFane2, c);


        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Boareal"), c);

        boareal = new JTextField(20);
        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(boareal, c);

        tomtArealLabel = new JLabel("Tomtareal:");
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        tomtArealLabel.setVisible(false);
        bopanel.add(tomtArealLabel, c);

        tomtAreal = new JTextField(20);
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        tomtAreal.setVisible(false);
        bopanel.add(tomtAreal, c);


        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Utleiepris:"), c);

        pris = new JTextField(20);
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(pris, c);


        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Byggår"), c);

        byggår = new JTextField(20);
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(byggår, c);



        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        romLabelFane2 = new JLabel("Rom: ");
        bopanel.add(romLabelFane2, c);
        romLabelFane2.setVisible(false);

        romBoxFane2 = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(romBoxFane2, c);
        romBoxFane2.setVisible(false);

        antEgtLabelFane2 = new JLabel("Ant Etasjer: ");
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        antEgtLabelFane2.setVisible(false);
        bopanel.add(antEgtLabelFane2, c);


        etasjeBoxFane2 = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        etasjeBoxFane2.setVisible(false);
        bopanel.add(etasjeBoxFane2, c);

        c.gridx = 0;
        c.gridy = 10;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        planLabelFane2 = new JLabel("Hilket plan?");
        bopanel.add(planLabelFane2, c);
        planLabelFane2.setVisible(false);


        planBoxFane2 = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(planBoxFane2, c);
        planBoxFane2.setVisible(false);




        garasjeValgFane2 = new JCheckBox("Garasje");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        garasjeValgFane2.setVisible(false);
        bopanel.add(garasjeValgFane2, c);


        kjellerValgFane2 = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        kjellerValgFane2.setVisible(false);
        bopanel.add(kjellerValgFane2, c);






        heisValgFane2 = new JCheckBox("Heis");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        heisValgFane2.setVisible(false);
        bopanel.add(heisValgFane2, c);

        balkongValgFane2 = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        balkongValgFane2.setVisible(false);
        bopanel.add(balkongValgFane2, c);






        badValgFane2 = new JCheckBox("Eget Bad");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        badValgFane2.setVisible(false);
        bopanel.add(badValgFane2, c);

        kjøkkenValgFane2 = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        kjøkkenValgFane2.setVisible(false);
        bopanel.add(kjøkkenValgFane2, c);




        c.gridx = 0;
        c.gridy = 14;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Beskrivelse:"), c);

        beskrivelse = new JTextArea("Skriv da..", 5, 10);
        scroll = new JScrollPane(beskrivelse);
        c.gridx = 1;
        c.gridy = 14;
        c.gridheight =2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(scroll, c);



        bildesti = new JTextField(20);
        c.gridx = 1;
        c.gridy = 16;
        bildesti.setEditable(false);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(bildesti, c);

        finnBildeKnapp = new JButton("Finn bilde");
        finnBildeKnapp.addActionListener(lytter);
        c.gridx = 2;
        c.gridy = 16;
        bopanel.add(finnBildeKnapp, c);

        velgUtleier = new JButton("Velg utleier");
        velgUtleier.addActionListener(lytter);
        c.gridx = 2;
        c.gridy = 17;
        bopanel.add(velgUtleier,c);


        c.gridx = 1;
        c.gridy = 17;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        bopanel.add(new JLabel("Utleier ID:"), c);

        utleierId = new JTextField(5);
        utleierId.setEditable(false);
        c.gridx = 1;
        c.gridy = 17;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(utleierId, c);



        regBoligKnapp = new JButton("Registrer");
        regBoligKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 18;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 5, 5, 5);
        bopanel.add(regBoligKnapp, c);





/*
        RRRRRRRRRRRRRRRRR   EEEEEEEEEEEEEEEEEEEEEE       GGGGGGGGGGGGGIIIIIIIIII   SSSSSSSSSSSSSSS TTTTTTTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR
        R::::::::::::::::R  E::::::::::::::::::::E    GGG::::::::::::GI::::::::I SS:::::::::::::::ST:::::::::::::::::::::TE::::::::::::::::::::ER::::::::::::::::R
        R::::::RRRRRR:::::R E::::::::::::::::::::E  GG:::::::::::::::GI::::::::IS:::::SSSSSS::::::ST:::::::::::::::::::::TE::::::::::::::::::::ER::::::RRRRRR:::::R
        RR:::::R     R:::::REE::::::EEEEEEEEE::::E G:::::GGGGGGGG::::GII::::::IIS:::::S     SSSSSSST:::::TT:::::::TT:::::TEE::::::EEEEEEEEE::::ERR:::::R     R:::::R
        R::::R     R:::::R  E:::::E       EEEEEEG:::::G       GGGGGG  I::::I  S:::::S            TTTTTT  T:::::T  TTTTTT  E:::::E       EEEEEE  R::::R     R:::::R
        R::::R     R:::::R  E:::::E            G:::::G                I::::I  S:::::S                    T:::::T          E:::::E               R::::R     R:::::R
        R::::RRRRRR:::::R   E::::::EEEEEEEEEE  G:::::G                I::::I   S::::SSSS                 T:::::T          E::::::EEEEEEEEEE     R::::RRRRRR:::::R
        R:::::::::::::RR    E:::::::::::::::E  G:::::G    GGGGGGGGGG  I::::I    SS::::::SSSSS            T:::::T          E:::::::::::::::E     R:::::::::::::RR
        R::::RRRRRR:::::R   E:::::::::::::::E  G:::::G    G::::::::G  I::::I      SSS::::::::SS          T:::::T          E:::::::::::::::E     R::::RRRRRR:::::R
        R::::R     R:::::R  E::::::EEEEEEEEEE  G:::::G    GGGGG::::G  I::::I         SSSSSS::::S         T:::::T          E::::::EEEEEEEEEE     R::::R     R:::::R
        R::::R     R:::::R  E:::::E            G:::::G        G::::G  I::::I              S:::::S        T:::::T          E:::::E               R::::R     R:::::R
        R::::R     R:::::R  E:::::E       EEEEEEG:::::G       G::::G  I::::I              S:::::S        T:::::T          E:::::E       EEEEEE  R::::R     R:::::R
        RR:::::R     R:::::REE::::::EEEEEEEE:::::E G:::::GGGGGGGG::::GII::::::IISSSSSSS     S:::::S      TT:::::::TT      EE::::::EEEEEEEE:::::ERR:::::R     R:::::R
        R::::::R     R:::::RE::::::::::::::::::::E  GG:::::::::::::::GI::::::::IS::::::SSSSSS:::::S      T:::::::::T      E::::::::::::::::::::ER::::::R     R:::::R
        R::::::R     R:::::RE::::::::::::::::::::E    GGG::::::GGG:::GI::::::::IS:::::::::::::::SS       T:::::::::T      E::::::::::::::::::::ER::::::R     R:::::R
        RRRRRRRR     RRRRRRREEEEEEEEEEEEEEEEEEEEEE       GGGGGG   GGGGIIIIIIIIII SSSSSSSSSSSSSSS         TTTTTTTTTTT      EEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR*/


        // FANE 3 - VIS TABELL   *********************************************************
        //RESETER

        //RESETER
        c.ipadx = 100;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.anchor=GridBagConstraints.NONE;
        c.weightx=0;
        c.weighty=0;









        c.gridx=0;
        c.gridy=1;
        c.gridheight=10;
        c.gridwidth=2;
        c.weightx=100;
        c.weighty=0.1;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;

        //c.insets=new Dimension()
        c.fill =GridBagConstraints.BOTH;
        //tapanel.setPreferredSize(new Dimension(100,100));
       // tapanel.setMinimumSize(tapanel.getSize());
        //tapanel.setAlignmentX(100);
        //tapanel.setAlignmentY(100);

        panel3.add(new JScrollPane(tapanel),c);
        c.fill = GridBagConstraints.NONE;


        radioTabellLytter = new tabellTypeLytter();
        radioTabell = new ButtonGroup();
        persontabellRadioknapp = new JRadioButton("Vis personer",false);
        persontabellRadioknapp.addActionListener(radioTabellLytter);
        //c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weightx=0.5;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx = 11;
        c.gridy = 1;
        panel3.add(persontabellRadioknapp,c);


        boligtabellRadioknapp = new JRadioButton("Vis boliger:",false);
        boligtabellRadioknapp.addActionListener(radioTabellLytter);
        c.gridx = 12;
        c.gridy = 1;
        panel3.add(boligtabellRadioknapp,c);


        radioTabell.add(persontabellRadioknapp);
        radioTabell.add(boligtabellRadioknapp);
        slettPerson = new JButton("Slett person");
        slettPerson.addActionListener(lytter);
        c.gridx = 11;
        c.gridy = 2;
        panel3.add(slettPerson,c);

        slettBoligKnapp = new JButton("Slett bolig");
        slettBoligKnapp.addActionListener(lytter);
        c.gridx = 12;
        c.gridy = 2;
        panel3.add(slettBoligKnapp,c);


        feedbackFane3 = new JTextArea("feedbackfelt");
        //feedbackFane3.setPreferredSize(new Dimension(800,100));
        feedbackFane3.setBackground(bakFarge);
        feedbackFane3.setSize(600,200);
       // c.gridwidth=30;
        c.gridx=0;
        c.gridy=15;
        c.gridwidth=10;

        c.anchor=GridBagConstraints.FIRST_LINE_START;
        //c.ipadx=30;
        //c.gridy=30;
        c.weighty=10;
        //c.fill=GridBagConstraints
        panel3.add(feedbackFane3,c);

        c.gridx = 11;
        c.gridy=3;
        c.gridheight=5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.weighty=100;
        panel3.add(bildepanel,c);
        bildepanel.setMaximumSize(bildepanel.getSize());
        bildepanel.setBackground(bakFarge);
        panel3.setBackground(bakFarge);
        tapanel.setBackground(bakFarge);


/*
MMMMMMMM               MMMMMMMM               AAA         TTTTTTTTTTTTTTTTTTTTTTT       CCCCCCCCCCCCCHHHHHHHHH     HHHHHHHHH
M:::::::M             M:::::::M              A:::A        T:::::::::::::::::::::T    CCC::::::::::::CH:::::::H     H:::::::H
M::::::::M           M::::::::M             A:::::A       T:::::::::::::::::::::T  CC:::::::::::::::CH:::::::H     H:::::::H
M:::::::::M         M:::::::::M            A:::::::A      T:::::TT:::::::TT:::::T C:::::CCCCCCCC::::CHH::::::H     H::::::HH
M::::::::::M       M::::::::::M           A:::::::::A     TTTTTT  T:::::T  TTTTTTC:::::C       CCCCCC  H:::::H     H:::::H
M:::::::::::M     M:::::::::::M          A:::::A:::::A            T:::::T       C:::::C                H:::::H     H:::::H
M:::::::M::::M   M::::M:::::::M         A:::::A A:::::A           T:::::T       C:::::C                H::::::HHHHH::::::H
M::::::M M::::M M::::M M::::::M        A:::::A   A:::::A          T:::::T       C:::::C                H:::::::::::::::::H
M::::::M  M::::M::::M  M::::::M       A:::::A     A:::::A         T:::::T       C:::::C                H:::::::::::::::::H
M::::::M   M:::::::M   M::::::M      A:::::AAAAAAAAA:::::A        T:::::T       C:::::C                H::::::HHHHH::::::H
M::::::M    M:::::M    M::::::M     A:::::::::::::::::::::A       T:::::T       C:::::C                H:::::H     H:::::H
M::::::M     MMMMM     M::::::M    A:::::AAAAAAAAAAAAA:::::A      T:::::T        C:::::C       CCCCCC  H:::::H     H:::::H
M::::::M               M::::::M   A:::::A             A:::::A   TT:::::::TT       C:::::CCCCCCCC::::CHH::::::H     H::::::HH
M::::::M               M::::::M  A:::::A               A:::::A  T:::::::::T        CC:::::::::::::::CH:::::::H     H:::::::H
M::::::M               M::::::M A:::::A                 A:::::A T:::::::::T          CCC::::::::::::CH:::::::H     H:::::::H
MMMMMMMM               MMMMMMMMAAAAAAA                   AAAAAAATTTTTTTTTTT             CCCCCCCCCCCCCHHHHHHHHH     HHHHHHHHH


 */

        //SLUTT FANE 3

        //FANE 4 - MATCHMAKING ************************************************************************************************************************************************************************
        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.weighty=0;
        c.weightx=0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;


        boligsøkerOverskrift = new JLabel("Boligsøkere");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        panel4.add(boligsøkerOverskrift,c);



        finnMatch = new JButton("Finn Match!");
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.ipady=30;

        finnMatch.addActionListener(lytter);
        panel4.add(finnMatch,c);
        velgBsPanel.setBackground(Color.RED);

        c.ipady=0;

        sendMail = new JButton("Send mail");
        c.gridx = 2;
        c.gridy = 15;
        sendMail.addActionListener(lytter);
        panel4.add(sendMail,c);

       // visBoligsøkere();


        c.gridx = 0;
        c.gridy = 4;
        panel4.add(velgBsPanel,c);




        c.gridx = 4;
        c.gridy = 0;
        panel4.add(new JLabel("Boliger, må endre til noe annet enn denne dumme tabellen her"),c);
      //  visMatch();

        c.gridx = 4;
        c.gridy = 4;
        panel4.add(resultatPanel,c);
        resultatPanel.setBackground(Color.BLUE);




/*


KKKKKKKKK    KKKKKKK     OOOOOOOOO     NNNNNNNN        NNNNNNNNTTTTTTTTTTTTTTTTTTTTTTTRRRRRRRRRRRRRRRRR                  AAA               KKKKKKKKK    KKKKKKKTTTTTTTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR
K:::::::K    K:::::K   OO:::::::::OO   N:::::::N       N::::::NT:::::::::::::::::::::TR::::::::::::::::R                A:::A              K:::::::K    K:::::KT:::::::::::::::::::::TE::::::::::::::::::::ER::::::::::::::::R
K:::::::K    K:::::K OO:::::::::::::OO N::::::::N      N::::::NT:::::::::::::::::::::TR::::::RRRRRR:::::R              A:::::A             K:::::::K    K:::::KT:::::::::::::::::::::TE::::::::::::::::::::ER::::::RRRRRR:::::R
K:::::::K   K::::::KO:::::::OOO:::::::ON:::::::::N     N::::::NT:::::TT:::::::TT:::::TRR:::::R     R:::::R            A:::::::A            K:::::::K   K::::::KT:::::TT:::::::TT:::::TEE::::::EEEEEEEEE::::ERR:::::R     R:::::R
KK::::::K  K:::::KKKO::::::O   O::::::ON::::::::::N    N::::::NTTTTTT  T:::::T  TTTTTT  R::::R     R:::::R           A:::::::::A           KK::::::K  K:::::KKKTTTTTT  T:::::T  TTTTTT  E:::::E       EEEEEE  R::::R     R:::::R
  K:::::K K:::::K   O:::::O     O:::::ON:::::::::::N   N::::::N        T:::::T          R::::R     R:::::R          A:::::A:::::A            K:::::K K:::::K           T:::::T          E:::::E               R::::R     R:::::R
  K::::::K:::::K    O:::::O     O:::::ON:::::::N::::N  N::::::N        T:::::T          R::::RRRRRR:::::R          A:::::A A:::::A           K::::::K:::::K            T:::::T          E::::::EEEEEEEEEE     R::::RRRRRR:::::R
  K:::::::::::K     O:::::O     O:::::ON::::::N N::::N N::::::N        T:::::T          R:::::::::::::RR          A:::::A   A:::::A          K:::::::::::K             T:::::T          E:::::::::::::::E     R:::::::::::::RR
  K:::::::::::K     O:::::O     O:::::ON::::::N  N::::N:::::::N        T:::::T          R::::RRRRRR:::::R        A:::::A     A:::::A         K:::::::::::K             T:::::T          E:::::::::::::::E     R::::RRRRRR:::::R
  K::::::K:::::K    O:::::O     O:::::ON::::::N   N:::::::::::N        T:::::T          R::::R     R:::::R      A:::::AAAAAAAAA:::::A        K::::::K:::::K            T:::::T          E::::::EEEEEEEEEE     R::::R     R:::::R
  K:::::K K:::::K   O:::::O     O:::::ON::::::N    N::::::::::N        T:::::T          R::::R     R:::::R     A:::::::::::::::::::::A       K:::::K K:::::K           T:::::T          E:::::E               R::::R     R:::::R
KK::::::K  K:::::KKKO::::::O   O::::::ON::::::N     N:::::::::N        T:::::T          R::::R     R:::::R    A:::::AAAAAAAAAAAAA:::::A    KK::::::K  K:::::KKK        T:::::T          E:::::E       EEEEEE  R::::R     R:::::R
K:::::::K   K::::::KO:::::::OOO:::::::ON::::::N      N::::::::N      TT:::::::TT      RR:::::R     R:::::R   A:::::A             A:::::A   K:::::::K   K::::::K      TT:::::::TT      EE::::::EEEEEEEE:::::ERR:::::R     R:::::R
K:::::::K    K:::::K OO:::::::::::::OO N::::::N       N:::::::N      T:::::::::T      R::::::R     R:::::R  A:::::A               A:::::A  K:::::::K    K:::::K      T:::::::::T      E::::::::::::::::::::ER::::::R     R:::::R
K:::::::K    K:::::K   OO:::::::::OO   N::::::N        N::::::N      T:::::::::T      R::::::R     R:::::R A:::::A                 A:::::A K:::::::K    K:::::K      T:::::::::T      E::::::::::::::::::::ER::::::R     R:::::R
KKKKKKKKK    KKKKKKK     OOOOOOOOO     NNNNNNNN         NNNNNNN      TTTTTTTTTTT      RRRRRRRR     RRRRRRRAAAAAAA                   AAAAAAAKKKKKKKKK    KKKKKKK      TTTTTTTTTTT      EEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR








*/







        //Slutt fane 4
        // FANE 5 - KONTRAKTER ************************************************************************************************************************************************************************
        //RESETER

        Dimension dim = new Dimension(20,20);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;


        c.anchor = GridBagConstraints.CENTER;
        c.ipady=0;

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 50, 0);
        kontraktHeader = new JLabel("Opprett kontrakter");
        kontraktHeader.setFont(headerFont);
        panel5.add(kontraktHeader,c);




        c.gridwidth= 1;


        /*
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 5, 5);
        velgUtleierKnapp = new JButton("Velg en eier");
        velgUtleierKnapp.addActionListener(lytter);
        panel5.add(velgUtleierKnapp,c);



        c.gridx = 2;
        c.gridy = 2;
        valgtUtleier = new JTextField(10);
        valgtUtleier.setEditable(false);
        valgtUtleier.setText("Ingen utleier valgt");
        panel5.add(valgtUtleier, c);

        */

        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0,0,20,5);
        velgLeietakerKnapp = new JButton("Velg en Leietaker");
        velgLeietakerKnapp.addActionListener(lytter);
        velgLeietakerKnapp.setMargin(new Insets(0,0,0,0));
        velgLeietakerKnapp.setPreferredSize(dim);
        panel5.add(velgLeietakerKnapp,c);


        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(0,0,20,0);
        valgtLeietaker = new JTextField(3);
        valgtLeietaker.setEditable(false);
        valgtLeietaker.setText("Ingen leietaker valgt");
        panel5.add(valgtLeietaker,c);

        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(0,0,20,5);
        velgBoligKnapp = new JButton("Velg en Bolig");
        velgBoligKnapp.addActionListener(lytter);
        velgBoligKnapp.setMargin(new Insets(0,0,0,0));
        velgBoligKnapp.setPreferredSize(dim);
        panel5.add(velgBoligKnapp, c);
        velgBoligKnapp.setVisible(false);

        c.gridx = 2;
        c.gridy = 3;
        valgtBolig = new JTextField(10);
        valgtBolig.setEditable(false);
        valgtBolig.setText("Ingen bolig valgt");
        panel5.add(valgtBolig, c);
        valgtBolig.setVisible(false);



        c.gridx = 1;
        c.gridy = 4;
        utleierLabel = new JLabel("Utleier: ");
        panel5.add(utleierLabel,c);
        utleierLabel.setVisible(false);



        c.gridx = 2;
        c.gridy = 4;
        valgtUtleier = new JTextField(3);
        valgtUtleier.setEditable(false);
        valgtUtleier.setText("Ingen utleier valgt");
        panel5.add(valgtUtleier, c);
        valgtUtleier.setVisible(false);





        startDagFelt = new JTextField(3);
        startMånedFelt = new JTextField(3);
        startÅrFelt = new JTextField(3);
        sluttDagFelt = new JTextField(3);
        sluttMånedFelt = new JTextField(3);
        sluttårFelt = new JTextField(3);


        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 3;
        c.insets = new Insets(30, 0, 7, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(new JLabel("Kontrakten starter:"),c);


        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 5, 3);
        c.fill = GridBagConstraints.NONE;
        panel5.add(new JLabel("Dag(DD)"),c);

        c.gridx = 2;
        c.gridy = 6;
        panel5.add(startDagFelt,c);

        c.gridx = 1;
        c.gridy = 7;
        panel5.add(new JLabel("Måned(MM)"),c);

        c.gridx = 2;
        c.gridy = 7;
        panel5.add((startMånedFelt),c);

        c.gridx = 1;
        c.gridy = 8;
        panel5.add(new JLabel("År(ÅÅÅÅ)"),c);

        c.gridx = 2;
        c.gridy = 8;
        panel5.add((startÅrFelt),c);




        c.gridx = 2;
        c.gridy = 9;
        c.gridwidth = 3;
        c.insets = new Insets(30, 0, 7, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(new JLabel("Kontrakten Slutter"),c);


        c.insets = new Insets(0, 0, 5, 3);
        c.gridx = 1;
        c.gridy = 10;
        c.fill = GridBagConstraints.NONE;
        panel5.add(new JLabel("Dag(DD)"),c);

        c.gridx = 2;
        c.gridy = 10;
        panel5.add((sluttDagFelt),c);

        c.gridx = 1;
        c.gridy = 11;
        panel5.add(new JLabel("Måned(MM)"),c);

        c.gridx = 2;
        c.gridy = 11;
        panel5.add((sluttMånedFelt),c);

        c.gridx = 1;
        c.gridy = 12;
        panel5.add(new JLabel("År(ÅÅÅÅ)"),c);

        c.insets = new Insets(0, 3, 20, 3);
        c.gridx = 2;
        c.gridy = 12;
        panel5.add((sluttårFelt),c);


        c.insets = new Insets(0, 0, 5, 0);
        c.gridx = 3;
        c.gridy = 13;
        lagreKontrakt = new JButton("Lagre kontrakt");
        lagreKontrakt.addActionListener(lytter);
        lagreKontrakt.setMargin(new Insets(0,0,0,0));
        lagreKontrakt.setPreferredSize(dim);
        panel5.add(lagreKontrakt, c);


        c.gridx = 3;
        c.gridy = 14;
        oppdaterKontrakter = new JButton("Oppdater Register");
        oppdaterKontrakter.addActionListener(lytter);
        oppdaterKontrakter.setMargin(new Insets(0,0,0,0));
        oppdaterKontrakter.setPreferredSize(dim);
        panel5.add(oppdaterKontrakter, c);





        //Legger fanecontainer på vinduet med scroll, str er 80% todo: Christer endre den str!

        //Dimension heleSkjermen = new Dimension(bredde-20, høyde-20);
        fane.setPreferredSize(new Dimension(1380,1010));
        fane.setBackground(bakFarge);


        //add(new JScrollPane(fane), BorderLayout.PAGE_START);
        JScrollPane sp = new JScrollPane();
        getContentPane().add(sp);
        add(fane);
        //add(new JScrollPane(getContentPane()));





        lesFraFil();
    } // End GUI konstruktør


    // LYTTERE
    private class knappLytter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == regBoligKnapp)
                regBolig();
            else if (e.getSource() == regPersonKnapp)
                regPerson();
            else if (e.getSource() == regUtleierKnapp)
                regPerson();
            else if (e.getSource() == finnBildeKnapp)
                finnBilde();
            else if (e.getSource() == velgUtleier)
                visVelgUtleierVindu();
            else if(e.getSource() == oppdaterKontrakter)
                visKontrakter();
            else if(e.getSource() == velgUtleierKnapp)
                visVelgUtleierVindu();
            else if(e.getSource() == velgLeietakerKnapp)
                visVelgLeietakerVindu();
            else if(e.getSource() == velgBoligKnapp)
                visVelgBoligVindu();
            else if(e.getSource() == lagreKontrakt)
                mekkKontrakt();
            else if(e.getSource() == finnMatch ){

                // her henter jeg inn tabellverdin
                visMatch();
            }
            else if (e.getSource() == sendMail){
                sendEmail();
            }
            else if (e.getSource() == slettPerson){
                slettBoligsøker();
            }
            else if (e.getSource() == slettBoligKnapp){
                slettBolig();
            }

        }
    }

    private DocumentListener documentListener = new DocumentListener()
        {
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }

        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
            //valider();
        }

        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }

        private void printIt(DocumentEvent documentEvent) {
            DocumentEvent.EventType type = documentEvent.getType();
            String typeString = null;
            Document source = documentEvent.getDocument();
            int length = source.getLength();




            /*if (type.equals(DocumentEvent.EventType.CHANGE)) {
                typeString = "Change";
                if(length!=0);
                valider();
                    System.out.println("FORANDRET, altså dett vall");
            } else if (type.equals(DocumentEvent.EventType.INSERT)) {
                //byttFarge((JTextField)o);
                System.out.println("source: "+source);

            } else if (type.equals(DocumentEvent.EventType.REMOVE)) {


            }*/

          //  valider();


            System.out.println("Length: " + length);
        }
    };




    private class menyLytter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == om) {
                JOptionPane.showMessageDialog(null, boligsøkere.toString());
            } else if (e.getSource() == lagre) {
                System.out.println("Trykka på lagre, som kaller på skrivTilFil()");
                skrivTilFil();

            } else if (e.getSource() == angre) {
                System.out.println("du anger på at du tryka på angre");
                clearResultatPanel();
                visBoligsøkere();
                resultatPanel.add(new JScrollPane(boligSøkereForMatch));
                resultatPanel.add(new JScrollPane(resultatTabell));
                revalidate();
                repaint();
            }
            else if(e.getSource() ==tabell){
                System.out.println("Hente inn tabell på nytt");
                panel3.remove(personTabellScroll);
                panel3.remove(boligTabellScroll);
                lagTabellen();
                panel3.add(personTabellScroll = new JScrollPane(personTabell),BorderLayout.LINE_END);
                panel3.add(boligTabellScroll = new JScrollPane(boligTabellTabellen),BorderLayout.BEFORE_LINE_BEGINS);
            }
            else if(e.getSource()==oppdaterBoligsøkerTabell){
                visBoligsøkere();
                System.out.println("Lastet inn boligsøkertabellen på nytt");
            }
            else if(e.getSource() == visHistorikk){
                visKontraktFil();
            }


        }
    }

    //tabellSplittPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,personTabellScroll,boligTabellScroll);


    private class fanelytter implements ChangeListener{
        public void stateChanged(ChangeEvent e) {
           if(fane.getSelectedIndex()==2) {
                System.out.println("Trykka på fane; vis tabell!");
            }
            else if(fane.getSelectedIndex()==3){
               System.out.println("oppdaterer boligsøkerlista siden matchfane er velgt, dvs kaller på visBoligsøkere()");
               visBoligsøkere();
           }
            repaint();
        }
    }

//her er nfm
    private void togler() //Metode for å skjule alle BT-avhengige valg.
    {
        System.out.println("TOGLA!");
        garasjeValg.setVisible(false);
        kjellerValg.setVisible(false);
        etasjeBox.setVisible(false);
        heisValg.setVisible(false);
        balkongValg.setVisible(false);
        badValg.setVisible(false);
        kjøkkenValg.setVisible(false);
        antEgtLabel.setVisible(false);
        planLabel.setVisible(false);
        planBox.setVisible(false);
        revalidate();
    }
    private void toglerFane2(){
        System.out.println("Togla fane2 for faen");
        garasjeValgFane2.setVisible(false);
        kjellerValgFane2.setVisible(false);
        etasjeBoxFane2.setVisible(false);
        heisValgFane2.setVisible(false);
        balkongValgFane2.setVisible(false);
        badValgFane2.setVisible(false);
        kjøkkenValgFane2.setVisible(false);
        antEgtLabelFane2.setVisible(false);
        tomtAreal.setVisible(false);
        tomtArealLabel.setVisible(false);
        planLabelFane2.setVisible(false);
        planBoxFane2.setVisible(false);
        romLabelFane2.setVisible(false);
        romBoxFane2.setVisible(false);
        revalidate();
    }

    private class boligTypeLytter implements ActionListener { //Lytter som hører på BT-boksen og gjør som den sier!

        public void actionPerformed(ActionEvent e) {
            String typenTil = (String) boligtypeBox.getSelectedItem();
            String boligTypeFane2 = (String) boligtypeBoxFane2.getSelectedItem();


            if(romBox.getSelectedIndex()!=0)romBox.setForeground(Color.BLACK);
            if(byBox.getSelectedIndex()!=0) byBox.setForeground(Color.BLACK);
            if(romBox.getSelectedIndex()!=0)romBox.setForeground(Color.BLACK);
            if(etasjeBox.getSelectedIndex()!=0)etasjeBox.setForeground(Color.BLACK);
            if(planBox.getSelectedIndex()!=0)planBox.setForeground(Color.BLACK);


            switch (typenTil) {
                case "Enebolig":
                    togler();
                    garasjeValg.setVisible(true);
                    kjellerValg.setVisible(true);
                    etasjeBox.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
                    antEgtLabel.setVisible(true);
                    regPersonKnapp.setVisible(true);
                    revalidate();
                    break;
                case "Rekkehus":
                    togler();
                    garasjeValg.setVisible(true);
                    kjellerValg.setVisible(true);
                    etasjeBox.setVisible(true);
                    antEgtLabel.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
                    regPersonKnapp.setVisible(true);

                    revalidate();
                    break;
                case "Leilighet":
                    togler();
                    heisValg.setVisible(true);
                    balkongValg.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
                    regPersonKnapp.setVisible(true);
                    planLabel.setVisible(true);
                    planBox.setVisible(true);
                    revalidate();
                    break;
                case "Hybel":
                    togler();
                    badValg.setVisible(true);
                    kjøkkenValg.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
                    regPersonKnapp.setVisible(true);
                    revalidate();
                    break;
                default:
                    System.out.println("Default");
                    togler();
                    revalidate();
                    break;
            }
            switch (boligTypeFane2){
                case "Enebolig":
                    toglerFane2();
                    garasjeValgFane2.setVisible(true);
                    kjellerValgFane2.setVisible(true);
                    etasjeBoxFane2.setVisible(true);
                    antEgtLabelFane2.setVisible(true);
                    tomtAreal.setVisible(true);
                    tomtArealLabel.setVisible(true);
                    romLabelFane2.setVisible(true);
                    romBoxFane2.setVisible(true);
                    revalidate();
                    break;
                case "Rekkehus":
                    toglerFane2();
                    garasjeValgFane2.setVisible(true);
                    kjellerValgFane2.setVisible(true);
                    etasjeBoxFane2.setVisible(true);
                    antEgtLabelFane2.setVisible(true);
                    tomtAreal.setVisible(true);
                    tomtArealLabel.setVisible(true);
                    romLabelFane2.setVisible(true);
                    romBoxFane2.setVisible(true);
                    revalidate();
                    break;
                case "Leilighet":
                    toglerFane2();
                    heisValgFane2.setVisible(true);
                    balkongValgFane2.setVisible(true);
                    planLabelFane2.setVisible(true);
                    planBoxFane2.setVisible(true);
                    romLabelFane2.setVisible(true);
                    romBoxFane2.setVisible(true);
                    revalidate();
                    break;
                case "Hybel":
                    toglerFane2();
                    badValgFane2.setVisible(true);
                    kjøkkenValgFane2.setVisible(true);
                    revalidate();
                    break;
                default:
                    System.out.println("DefaultFane2");
                    toglerFane2();
                    revalidate();
                    break;

            }
        }
    }



    private class minPrisLytter implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            minPris.setText("Min Pris: " + minPrisSlider.getValue());

        }
    }

    private class maxPrisLytter implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            maxPris.setText("Max Pris :" + maxPrisSlider.getValue());
        }
    }

    private class PersonTypeLytter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (utleier.isSelected()) {

                firma.setVisible(true);
                firmaLabel.setVisible(true);
                regUtleierKnapp.setVisible(true);
                bspanel.setVisible(false);
                btpanel.setVisible(false);
                regPersonKnapp.setVisible(false);
                revalidate();


            } else if (boligsøker.isSelected()) {
                bspanel.setVisible(true);
                firma.setVisible(false);
                firmaLabel.setVisible(false);
                regUtleierKnapp.setVisible(false);
                btpanel.setVisible(true);
                revalidate();
            }
        }
    }
    private void clearPanel3()
    {
       tapanel.removeAll();
        revalidate();
        repaint();
    }


    //For fane 3, om det skal vises boligtabell  eller persontabell
    private class tabellTypeLytter implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(boligtabellRadioknapp.isSelected()){
                clearPanel3();
                lagBoligTabellen();
                c.gridx=0;
                c.gridy=0;
                c.anchor=GridBagConstraints.FIRST_LINE_START;
                c.fill =GridBagConstraints.BOTH;
                tapanel.add(boligTabellScroll = new JScrollPane(boligTabellTabellen),c);
                revalidate();
                repaint();
            }
            else if(persontabellRadioknapp.isSelected()){
                clearPanel3();
                lagTabellen();
                tapanel.add(personTabellScroll = new JScrollPane(personTabell),c);
                revalidate();
                repaint();
            }
        }
    }

    // UTVALGSLYTTER

    private class Utvalgslytter implements ListSelectionListener
    {
        private TableModel tabellmodell;

        public Utvalgslytter(TableModel m)
        {
            tabellmodell = m;
        }

        public void valueChanged(ListSelectionEvent e)
        {
            if(e.getValueIsAdjusting())
                return;

            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if(tabellmodell instanceof resultatTabellModell)
            {
                if(!lsm.isSelectionEmpty())
                {
                    System.out.println("Lytter til riktig vindu");
                    int valgtRad = lsm.getMinSelectionIndex();
                    valgtRad = resultatTabell.convertRowIndexToModel(valgtRad);
                    int id = (int) tabellmodell.getValueAt(valgtRad,9);
                    String stringId = Integer.toString(id);
                    
                    if(fane.getSelectedIndex() == 4) {
                        String uid = boliger.finnUtleier(boliger.finnBolig(stringId));
                        valgtUtleier.setText(uid);
                        valgtBolig.setText(stringId);
                        valgtUtleier.setVisible(true);
                        utleierLabel.setVisible(true);
                        velgBoligVindu.dispose();
                        return;
                    }
                    else if(fane.getSelectedIndex() == 3)
                    {
                        valgtBoligId = stringId;
                    }
                }
            }


            else if(tabellmodell instanceof utleierTabellModell) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMinSelectionIndex();
                    String id = (String) tabellmodell.getValueAt(valgtRad, 0);
                    System.out.println("nå har programmet fått med seg at du har valgt noe fra utleiertabbel");
                    utleierId.setText(id);

                    velgUtleierVindu.dispose();
                    return;
                }
            }

            else if(tabellmodell instanceof boligsøkerTabellModell)
            {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMinSelectionIndex();
                    id = (String) tabellmodell.getValueAt(valgtRad, 0);
                    System.out.println("nå har programmet fått med seg at du har valgt noe fra boligsøkertabbel");
                    valgtLeietaker.setText(id);

                    velgLeietakerVindu.dispose();
                    velgBoligKnapp.setVisible(true);
                    valgtBolig.setVisible(true);
                    return;
                }
            }
            else if(tabellmodell instanceof boligSøkerTabellModellForMatch)
            {
                if(!lsm.isSelectionEmpty()) {
                    System.out.println("inne i tabbelyttern nå! panel 4 altså matching");
                    int valgtRad = lsm.getMaxSelectionIndex();
                    valgtId = (String) tabellmodell.getValueAt(valgtRad,0);
                    System.out.println(valgtId);

                    return;
                }

            }
            else if(tabellmodell instanceof personTabellFabrikk){
                if(!lsm.isSelectionEmpty()){
                    int valgtRad = lsm.getMaxSelectionIndex();
                    slettPersonFn = (String)tabellmodell.getValueAt(valgtRad, 0);
                    slettPersonEn = (String)tabellmodell.getValueAt(valgtRad, 1);
                    visPersonInfo();

                }
            }
            else if(tabellmodell instanceof boligTabellFabrikk){
                if(!lsm.isSelectionEmpty()){
                    int valgtRad = lsm.getMaxSelectionIndex();
                    slettBoligId = (int)tabellmodell.getValueAt(valgtRad, 8);
                    System.out.println("B tabel ja");
                    visBoligInfo();
                }
            }




        }
    }







    // UTLEIERTABELLMODELL
    private class utleierTabellModell extends AbstractTableModel
    {
        String [] kolonnenavn = {"Id", "Fornavn","Etternavn", "Adresse", "Telefon", "eMail", "Firma"};
        String [][] celler = utleiere.tilTabellMedId();

        public int getRowCount() {
            return celler.length;
        }

        public int getColumnCount() {
            return celler[0].length;

        }

        public Object getValueAt(int rad, int kolonne) {
            return celler[rad][kolonne];
        }
        public String getColumnName(int kolonne)//for kolonnenavn
        {
            return kolonnenavn[kolonne];
        }
        public boolean isCellEditable(int rad, int kolonne)
        {
            return kolonne == 2;
        }
        public void setValueAt(String nyVerdi, int rad, int kolonne)
        {
            celler[rad][kolonne] = nyVerdi;
        }
    }



    private class boligsøkerTabellModell extends AbstractTableModel
    {
        String [] kolonnenavn = {"Id", "Fornavn","Etternavn", "Adresse", "Telefon", "eMail"};
        String [][] celler = boligsøkere.tilMatchTabll();

        public int getRowCount() {
            return celler.length;
        }

        public int getColumnCount() {
            return celler[0].length;

        }

        public Object getValueAt(int rad, int kolonne) {
            return celler[rad][kolonne];
        }
        public String getColumnName(int kolonne)//for kolonnenavn
        {
            return kolonnenavn[kolonne];
        }
        public boolean isCellEditable(int rad, int kolonne)
        {
            return kolonne == 2;
        }
        public void setValueAt(String nyVerdi, int rad, int kolonne)
        {
            celler[rad][kolonne] = nyVerdi;
        }
    }




// HERFRA










public String[] getKolonneNavnForBoligtype(int[] krav)
{
    String[] kolonnenavn1 = {"Matchresultat", "By", "Areal", "Pris/m", "Adresse", "Antall rom", "Parkering", "Kjeller", "Bilde", "id"};
    String[] kolonnenavn2 = {"Matchresultat", "By", "Areal", "Pris/m", "Adresse", "Antall rom", "Balkong", "Heis", "Bilde", "id"};
    String[] kolonnenavn3 = {"Matchresultat", "By", "Areal", "Pris/m", "Adresse", "Antall rom", "Deler bad med", "Deler kjøkken med", "Bilde", "id"};

    if(krav != null) {
        switch (krav[0]) {
            case 1:
                return kolonnenavn1;

            case 2:
                return kolonnenavn1;

            case 3:
                return kolonnenavn2;

            case 4:
                return kolonnenavn3;

        }
    }
        return kolonnenavn1;
}


private class resultatTabellModell extends AbstractTableModel
{


    int[] kravene = boligsøkere.getKravPåId(valgtId);
    //int type = kravene[0];




    String [] kolonnenavn = getKolonneNavnForBoligtype(kravene);

    Object[][] celler = boliger.matchPåKrav(kravene);

    public int getRowCount() {
        return celler.length;
    }

    public int getColumnCount() {
        return celler[0].length;

    }

    public Object getValueAt(int rad, int kolonne) {
        return celler[rad][kolonne];
    }
    public String getColumnName(int kolonne)//for kolonnenavn
    {
        return kolonnenavn[kolonne];
    }
    public boolean isCellEditable(int rad, int kolonne)
    {
        return kolonne == 2;
    }
    public void setValueAt(String nyVerdi, int rad, int kolonne)
    {
        celler[rad][kolonne] = nyVerdi;
    }
    public Class getColumnClass( int k )
    {
       return getValueAt( 0, k ).getClass();
    }
}

    private void visMatch()
    {
        clearResultatPanel();
        resultatTabellModell resultatModell = new resultatTabellModell();
        resultatTabell = new JTable(resultatModell);

        TableRowSorter<resultatTabellModell> sorterer = new TableRowSorter<>( resultatModell );


        List<TableRowSorter.SortKey> sortKeys
                = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING
        ));

        resultatTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsm = resultatTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(resultatModell));



        sorterer.setSortKeys(sortKeys);

        resultatTabell.setRowSorter(sorterer);
        resultatPanel.add(new JScrollPane(resultatTabell));
        revalidate();
        repaint();



        /*
                        clearResultatPanel();
                visBoligsøkere();
                resultatPanel.add(new JScrollPane(boligSøkereForMatch));
                resultatPanel.add(new JScrollPane(resultatTabell));
                revalidate();
                repaint();
         */

    }
    private void clearResultatPanel()
    {
        resultatPanel.removeAll();
        revalidate();
        repaint();
    }
    private void clearVelgBsPanel(){
        velgBsPanel.removeAll();
        revalidate();
        repaint();

    }





    public void visBoligsøkere() {
        clearVelgBsPanel();


        boligSøkerTabellModellForMatch boligSøkerTabellModellForMatch = new boligSøkerTabellModellForMatch();
        boligSøkereForMatch = new JTable(boligSøkerTabellModellForMatch);
        boligSøkereForMatch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel lsm = boligSøkereForMatch.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(boligSøkerTabellModellForMatch));

        velgBsPanel.add(new JScrollPane(boligSøkereForMatch));



    }



    //Starter tabellmodell for visVoligSøkere
    private class boligSøkerTabellModellForMatch extends AbstractTableModel
    {
        String [] kolonnenavn = {"Id", "Fornavn","Etternavn", "Adresse", "Email", "Telefon"};
        String [][] celler = boligsøkere.tilMatchTabll();

        public int getRowCount() {
            return celler.length;
        }

        public int getColumnCount() {
            return celler[0].length;

        }

        public Object getValueAt(int rad, int kolonne) {
            return celler[rad][kolonne];
        }
        public String getColumnName(int kolonne)//for kolonnenavn
        {
            return kolonnenavn[kolonne];
        }
        public boolean isCellEditable(int rad, int kolonne)
        {
            return kolonne == 2;
        }
        public void setValueAt(String nyVerdi, int rad, int kolonne)
        {
            celler[rad][kolonne] = nyVerdi;
        }
    }
     ////SSLUTTER tabellmodell for visVoligSøkere

//TIL HIT






    private String[][] joinPersonArray() {
        String[][] første =  boligsøkere.tilTabell();
        String[][] andre =utleiere.tilTabell();
        String[][] joina = new String[første.length + andre.length][6];
        String[][] dummy = {{"Tabellen","er","tom","tom","tom","tom"}};


        int i = 0;
        while (i < første.length) {
            joina[i] = første[i];
            i++;
        }
        int j=0;
        while(j<andre.length){
            joina[i++]=andre[j];
            j++;
        }
        if(i==0)
            joina = dummy;

        return joina;
    }

    private class personTabellFabrikk extends AbstractTableModel {

      //  int  = boligsøkere.tellOpp();


        String[] kolonnenavn = {"Fornavn", "Etternavn", "Adrssse", "Mail", "Telefon","Firma"};

        String[][] celler = joinPersonArray();



      // THEM RULES for tabellen Altså tabellmodellen
        public int getRowCount() {
            return celler.length;
        }

        public int getColumnCount() {
            return celler[0].length;

        }


        public Object getValueAt(int rad, int kolonne) {
            return celler[rad][kolonne];
        }
        public String getColumnName(int kolonne)//for kolonnenavn
        {
            return kolonnenavn[kolonne];
        }
        public boolean isCellEditable(int rad, int kolonne)
        {
            return kolonne == 2;
        }
        public void setValueAt(String nyVerdi, int rad, int kolonne)
        {
            celler[rad][kolonne] = nyVerdi;
        }





    }    // end personTabellFabrikk


    private void lagTabellen() // legge til boligTabell her ogsÅ?
    {
        personTabellFabrikk personTabellModell = new personTabellFabrikk(); //lager modellen
        personTabell = new JTable(personTabellModell);
        ListSelectionModel lsm = personTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(personTabellModell));

    }
    private void lagBoligTabellen()
    {
        boligTabellFabrikk boligTabellModell = new boligTabellFabrikk();
        boligTabellTabellen = new JTable(boligTabellModell);
        ListSelectionModel lsm = boligTabellTabellen.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(boligTabellModell));


        //skjuler på en teit måte
        boligTabellTabellen.getColumn("Parkering").setMaxWidth(0);
        boligTabellTabellen.getColumn("Parkering").setMinWidth(0);
        boligTabellTabellen.getColumn("Parkering").setPreferredWidth(0);
        boligTabellTabellen.getColumn("Kjeller").setMaxWidth(0);
        boligTabellTabellen.getColumn("Kjeller").setMinWidth(0);
        boligTabellTabellen.getColumn("Kjeller").setPreferredWidth(0);
        boligTabellTabellen.getColumn("Bilde").setMaxWidth(0);
        boligTabellTabellen.getColumn("Bilde").setMinWidth(0);
        boligTabellTabellen.getColumn("Bilde").setPreferredWidth(0);
        boligTabellTabellen.getColumn("Id").setMaxWidth(0);
        boligTabellTabellen.getColumn("Id").setMinWidth(0);
        boligTabellTabellen.getColumn("Id").setPreferredWidth(0);

        boligTabellTabellen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }




    private class boligTabellFabrikk extends AbstractTableModel {


        String[] boligkolonnenavn = {"By","Kvadrat","Pris","Adresse","Rom","Parkering","Kjeller","Bilde","Id"};


        Object[][] boligceller = joinBoligArray();


        // THEM RULES for tabellen Altså tabellmodellen
        public int getRowCount() {
            return boligceller.length;
        }

        public int getColumnCount() {
            return boligceller[0].length;

        }


        public Object getValueAt(int rad, int kolonne) {
            return boligceller[rad][kolonne];
        }
        public String getColumnName(int kolonne)//for kolonnenavn
        {
            return boligkolonnenavn[kolonne];
        }



        public void setValueAt(String nyVerdi, int rad, int kolonne)
        {
            boligceller[rad][kolonne] = nyVerdi;
        }
        public Class getColumnClass( int k )
        {
            return getValueAt( 0, k ).getClass();
        }

    }    // end boligTabellFabrikk


    private Object[][] joinBoligArray() {

        Object[][] første = boliger.eneboligerTilTabell();
        Object[][] andre = boliger.hyblerTilTabell();
        Object[][] tredje = boliger.leiligheterTilTabell();
        Object[][] fjerde = boliger.rekkehusTilTabell();
        System.out.println("første "+første.length);

        Object[][] joina = new Object[første.length + andre.length + tredje.length + fjerde.length][9];
        int i = 0;
        while (i < første.length) {
            joina[i] = første[i];
            i++;
            System.out.println(i);
        }
        int j=0;
        while(j<andre.length){
            JOptionPane.showMessageDialog(null,j);
            joina[i++]=andre[j];
            j++;
        }
        int k=0;
        while(k<tredje.length){
            joina[i++]=tredje[k];
            k++;
        }
        int l=0;
        while(l<fjerde.length) {
            joina[i++] = fjerde[l];
            l++;
        }
        return joina;
    }




        //private TableModel tabellModell;
        //private personTabellFabrikk vindu;
/*
        Utvalgslytter(TableModel m, personTabellFabrikk v)
        {
            tabellModell = m;
            vindu = v;
        }
*/


        /*
        public void valueChanged(ListSelectionEvent e)
        {
            String utskrift = "";
            if(e.getValueIsAdjusting()) //vent med handlig før valg er avsluttet
                return;


            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if(!lsm.isSelectionEmpty())
            {
                int valgtrad = lsm.getMinSelectionIndex();

                utskrift += "Navn: " + (String) tabellModell.getValueAt(valgtrad, 0 );
                utskrift += "EtterNavn: " + (String) tabellModell.getValueAt(valgtrad,1);
            }
            utskriftsområde.setText(utskrift);
           // vindu.visNavndata(navndata)


        }//end valuechange
    }


*/
    public void regPerson()
    {


        if(boligsøker.isSelected())
        {

            int bt, by, rom, minPris, maxPris, park, antE, kjeller, minTomt, maxTomt, heis, balkong, dbm, dkm, plan;

            park = 0;
            heis = 0;
            balkong = 0;
            dbm = 0;
            dkm = 0;
            kjeller = 0;
            antE = 0;
            rom = 0;
            minPris = 0;
            maxPris = 0;
            plan=0;

            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String t = tlf.getText();
            String email = mail.getText();
            String bType = (String) boligtypeBox.getSelectedItem();
            String byInn = (String) byBox.getSelectedItem();

            try{
                if(romBox.isVisible())
                rom = Integer.parseInt((String) romBox.getSelectedItem());

                minPris = (int) minPrisSlider.getValue();

                maxPris = (int) maxPrisSlider.getValue();

                if(etasjeBox.isVisible()){
                    System.out.println("Inni try iffen");
                antE = Integer.parseInt((String) etasjeBox.getSelectedItem()); //todo BUG, hvis du trykker registrer uten å ha valgt
                }

                if(planBox.isVisible())
                plan = Integer.parseInt((String)planBox.getSelectedItem());
            }
            catch(NumberFormatException nfe){
                System.out.println("NFE i trycatchen noe ikke er valgt. enten rom,plan,etg");

            }


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




            if( fnavn.equals("")||fnavn.length()<2|| enavn.equals("") ||enavn.length()<2|| t.equals("")||t.length()<2 || ad.length()<2 || ad.equals("") || email.equals("") || email.length()<2|| bt == 0 || by == 0 ||  plan == 0 || rom == 0)
            {

                gyldig(fornavn);
                gyldig(etternavn);
                gyldig(adresse);
                gyldig(tlf);
                gyldig(mail);
                gyldigBox(boligtypeBox);
                gyldigBox(byBox);
                gyldigBox(romBox);
                gyldigBox(etasjeBox);
                gyldigBox(romBox);
                gyldigBox(planBox);

            }
            else {
                String id = idGenerator(enavn,fnavn);

                    if(bt == 1 || bt == 2) //hvis enebolig
                    {
                        if(antE != 0)
                        {
                            JOptionPane.showMessageDialog(null,"1");
                            Boligsøker ny = new Boligsøker(id, fnavn, enavn, ad, t, email, bt, by, rom, maxPris, minPris, park, antE, kjeller, heis, balkong, dbm, dkm );
                            boligsøkere.settInnNy(ny);
                            clearPersonFelt();
                            clearBSfelt();
                            return;
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"2");
                            gyldigBox(etasjeBox);
                            return;
                        }
                    }


                JOptionPane.showMessageDialog(null,"3");
                Boligsøker ny = new Boligsøker(id, fnavn, enavn, ad, t, email, bt, by, rom, maxPris, minPris, park, antE, kjeller, heis, balkong, dbm, dkm );
                boligsøkere.settInnNy(ny);
                clearPersonFelt();
                clearBSfelt();
                feedbackFane1.setText("Ny boligsøker registrert med id: " + ny.getId());
                return;
            }


            feedbackFane1.setText("Du må skrive inn i alle feltene");

            return;

        }// end of: IF(BOLIGSØKER)


        // regger utleier
        else if(utleier.isSelected())
        {
            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String t = tlf.getText();
            String email = mail.getText();
            String firm = firma.getText();


            if( fnavn.equals("") || fnavn.length()<2 || enavn.equals("") ||enavn.length()<2|| ad.equals("")||ad.length()<2 || t.equals("")||t.length()<2 || email.equals("") ||email.length()<2|| firm.equals("")||firm.length()<2){
                gyldig(fornavn);
                gyldig(etternavn);
                gyldig(adresse);
                gyldig(tlf);
                gyldig(mail);
                gyldig(firma);
            }
            else {

                String id = idGenerator(firm,enavn,fnavn); // todo: Christer, fiks en fet måte yes, denne må også bulletproofes


                Utleier ny = new Utleier(id, fnavn, enavn, ad ,t ,email, firm);
                utleiere.settInn(ny);
                clearPersonFelt();
                clearBSfelt();
                feedbackFane1.setText("Utleier registrert med id: " + ny.getId());
               
                return;
            }
            feedbackFane1.setText("Du må skrive inn i alle feltene");
            return;

        }
        JOptionPane.showMessageDialog(null, "du må velge en av typene..."); //vil aldri kicke inn siden return hehe heehe
    }



    private boolean gyldig(JTextField f)
    {
        if( f.getText().isEmpty() || f.getDocument().getLength() < 2 || f.getText().equals("")) {
            f.setBackground(Color.RED);
            return true;
        }
        else{
            f.setBackground(Color.WHITE);
            return false;
       }
    }
    private void clearBSfelt()
    {
        boligtypeBox.setSelectedIndex(0);
        byBox.setSelectedIndex(0);
        romBox.setSelectedIndex(0);
        minPrisSlider.setValue(0);
        maxPrisSlider.setValue(50000);
        etasjeBox.setSelectedIndex(0);
        planBox.setSelectedIndex(0);
        garasjeValg.setSelected(false);
        kjellerValg.setSelected(false);
        heisValg.setSelected(false);
        balkongValg.setSelected(false);
        kjøkkenValg.setSelected(false);
        badValg.setSelected(false);
        feedbackFane1.setText("");
        System.out.println("Boligsøkerfelt er klarert");
    }


    private void clearPersonFelt()
    {
        fornavn.setText("");
        etternavn.setText("");
        adresse.setText("");
        tlf.setText("");
        mail.setText("");
        firma.setText("");
        fornavn.setBackground(Color.WHITE);
        etternavn.setBackground(Color.WHITE);
        adresse.setBackground(Color.WHITE);
        tlf.setBackground(Color.WHITE);
        mail.setBackground(Color.WHITE);
        firma.setBackground(Color.WHITE);
        System.out.println("Clear person feltene");
    }

    private boolean gyldigBox(JComboBox f)
    {
        if(f.getSelectedIndex()==0 && f.isVisible()) {
            f.setForeground(Color.RED);
            return true;
        }
        else
        f.setForeground(Color.BLACK);
        return false;

    }


    private String idGenerator(String f, String en, String fn) //fant masse gøyale måter å gjøre på. denne er kanskje litt for primitiv
    {
        String firma = f;
        String eNavn = en;
        String fNavn = fn;
        String id = "";

       try{
           id = f.substring(0,2).toUpperCase()+en.substring(0,2).toUpperCase()+fn.substring(0,2).toUpperCase();
       }
       catch (StringIndexOutOfBoundsException sioobe)
       {
           System.out.println();
       }


        JOptionPane.showMessageDialog(null,"Autogenrert ID: "+ id);

        return id;

    }

    private String idGenerator(String en, String fn) //fant masse gøyale måter å gjøre på. denne er kanskje litt for primitiv
    {

        String eNavn = en;
        String fNavn = fn;
        String id = "";
        //todo legge inn try; gir String out of bounds exeption
        try{id = en.substring(0,2).toUpperCase()+fn.substring(0,2).toUpperCase();}
        catch(StringIndexOutOfBoundsException sioobe){
            System.out.println("Får SIOOBE i idgenerator fordi navn id eller etternavn > 2 lang"); }

        JOptionPane.showMessageDialog(null,"Autogenrert ID: "+ id);

        return id;

    }


    public void regBolig()
    {
        JOptionPane.showMessageDialog(null, "Regbolig kjører");

        String adr = adresseFane2.getText();
        String arealString = boareal.getText();
        String årString = byggår.getText();
        String utPrisString = pris.getText();
        String utId = utleierId.getText();
        String tAreal = tomtAreal.getText();
        String beskrivelseString = beskrivelse.getText();



        if(utId.equals("Ingen utleier valgt"))
        {
            JOptionPane.showMessageDialog(null,"Du må velge en utleier!");
            return;
        }



        // innfelter
        if(arealString.equals("") || adr.equals("") || årString.equals("") || utPrisString.equals(""))
        {
            gyldig(adresseFane2);
            gyldig(boareal);
            gyldig(byggår);
            gyldig(pris);
            return;
        }


        int areal = 0;
        int år = 0;
        int upris = 0;
        int tomtareal = 0;
        String valg = (String)boligtypeBoxFane2.getSelectedItem();
        int btype = 0;
        switch(valg){
            case "Enebolig":    btype = 1;
                break;
            case "Rekkehus":       btype = 2;
                break;
            case "Leilighet":   btype = 3;
                break;
            case "Hybel":    btype = 4;
                break;
            case "Velg boligtype..": btype = 0;
                break;
            default:            btype = 0;
                break;
        }
        try
        {
            areal = Integer.parseInt(arealString);
            år = Integer.parseInt(årString);
            upris = Integer.parseInt(utPrisString);
            if( btype == 1 || btype == 4){
                try{
                    tomtareal = Integer.parseInt(tAreal);
                }catch(NumberFormatException nfe){
                    nfe.printStackTrace();
                }
        }
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Du må skrive inn skikkelige verdier!");
            return;
        }



    // Comboboxer
        String by = (String)byBoxFane2.getSelectedItem();
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
            case "Velg by..":   byvalg = 0;
                                break;
            default:            byvalg = 0;
                                break;
        }
        int rom;
        int antetasjer;
        int plan;


        if(romBoxFane2.getSelectedItem().equals("Velg ant. rom."))
            rom = 0;
        else
            rom = Integer.parseInt((String)romBoxFane2.getSelectedItem());


        if(etasjeBoxFane2.getSelectedItem().equals("Velg ant. etg.."))
            antetasjer = 0;
        else
            antetasjer = Integer.parseInt((String)etasjeBoxFane2.getSelectedItem());


        if(planBoxFane2.getSelectedItem().equals("velg antall etasjer"))
            plan = 0;
        else
            plan = Integer.parseInt((String)planBoxFane2.getSelectedItem());





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

        String sti = bildesti.getText();



        switch(btype){
            case 1: Enebolig nyEnebolig = new Enebolig(adr,byvalg, areal, rom, år, upris, utId, sti, antetasjer, garasje, kjeller, tomtareal,beskrivelseString);
                         if(boliger.leggTil(nyEnebolig))
                             JOptionPane.showMessageDialog(null,"Registrering vellykket!");
                         break;
            case 2: Rekkehus nyttRekkehus = new Rekkehus(adr,byvalg, areal, rom, år, upris, utId, sti, antetasjer,garasje, kjeller, tomtareal,beskrivelseString);
                         if(boliger.leggTil(nyttRekkehus))
                             JOptionPane.showMessageDialog(null, "Registrering vellykket");
                         break;
            case 3: Leilighet nyLeilighet = new Leilighet(adr,byvalg, areal, rom, år, upris, utId, sti, plan, balkong, heis, beskrivelseString);
                         if(boliger.leggTil(nyLeilighet))
                             JOptionPane.showMessageDialog(null,"Registrering vellykket");
                         break;
            case 4: Hybel nyHybel = new Hybel(adr,byvalg, areal, rom, år, upris, utId, sti, badInt, kjøkkenInt,beskrivelseString);
                         if(boliger.leggTil(nyHybel))
                             JOptionPane.showMessageDialog(null,"Registrering vellykket");
                         break;
        }
    }

    public void finnBilde()
    {
        JFileChooser filvelger = new JFileChooser();
        filvelger.setCurrentDirectory( new File( "." ) );
        int resultat = filvelger.showOpenDialog(this);

        if(resultat == JFileChooser.APPROVE_OPTION)
        {
            File f = filvelger.getSelectedFile();
            String filsti = f.getPath();
            bildesti.setText(filsti);
        }
    }



    public void mekkKontrakt()// DETTE ER JÆLA BRA KODE, men må kommentere den vekk til vi har innfelter.
    {
        Boligsøker leietaker = boligsøkere.getBoligsøker(valgtLeietaker.getText());
        Bolig bolig = boliger.finnBolig(valgtBolig.getText());
        Utleier utleier = utleiere.getUtleier(valgtUtleier.getText());
        int sluttår, sluttmåned, sluttdag, startår, startmåned,startdag;

        if(leietaker == null )  {
            System.out.println("leietaker var null");
            return;
        }
        if(bolig == null )  {
        System.out.println("boligen var null");
        return;
        }
        if(utleier == null ) {
        System.out.println("utleieren var null");
        return;
        }



        try{
            sluttår = Integer.parseInt((String) sluttårFelt.getText());
            sluttmåned = Integer.parseInt((String) sluttMånedFelt.getText());
            sluttdag = Integer.parseInt((String) sluttDagFelt.getText());
            startår = Integer.parseInt((String) startÅrFelt.getText());
            startmåned = Integer.parseInt((String) startMånedFelt.getText());
            startdag = Integer.parseInt((String) startDagFelt.getText());
        }

        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "Du må skrive inn ordentlige tall i datofeltene");
            return;
        }

        if(startdag <= 31   &&   startdag > 0   &&   startmåned <= 12   &&   startmåned > 0   &&   startår >=2014   &&  startår <= 2020 &&
           sluttdag <= 31   &&   sluttdag > 0   &&   sluttmåned <= 12   &&   sluttmåned > 0   &&   sluttår >=2014   &&  sluttår <= 2020)
        {
            Calendar start = new GregorianCalendar(startår, (startmåned-1), startdag);
            Calendar slutt = new GregorianCalendar(sluttår, (sluttmåned-1), sluttdag);

            if(start.before(slutt))
            {
                Kontrakt ny = new Kontrakt(utleier,leietaker, bolig, start, slutt );
                if(kontrakter.leggTil(ny)){
                    kontrakthistorie.skrivTilTekstFil(ny.toString());
                    JOptionPane.showMessageDialog(null, "Kontrakt lagret");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Disse datoene samsvarer ikke!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Du må skriver gyldige tall i datofeltene!");
        }

    }



    public void matchPåKrav()
    {
        /*
        - velge boligsøker:
            - ved hjelp av jtable
            - ved hjelp av navn eller id.

        - hente fram boligsøker sin kravarray.
        - sende denne med til boliglisten.
        - det returneres en string her som kan skrives ut i et felt.
        - hva vi gjør med bilder, vet jeg ikke enda altså. 


        */



    }

    private void visVelgUtleierVindu()
    {
        velgUtleierVindu = new JFrame("Velg Eier");

       // utleierValgTabell = new JTable(utleiere.tilTabellMedId(),kolonnenavn);

        utleierTabellModell modell = new utleierTabellModell();
        utleierValgTabell = new JTable(modell);


        utleierValgTabell.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        ListSelectionModel lsm = utleierValgTabell.getSelectionModel();

        lsm.addListSelectionListener(new Utvalgslytter(modell));
        velgUtleierVindu.add(utleierValgTabell);
        velgUtleierVindu.pack();
        velgUtleierVindu.setVisible(true);


    }


    private void visVelgLeietakerVindu()
    {
        velgLeietakerVindu = new JFrame("Velg Leietaker");


        boligsøkerTabellModell modell = new boligsøkerTabellModell();
        leietakerValgTabell = new JTable(modell);
        leietakerValgTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel lsm = leietakerValgTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(modell));

        velgLeietakerVindu.add(leietakerValgTabell);
        velgLeietakerVindu.pack();
        velgLeietakerVindu.setVisible(true);
        velgLeietakerVindu.setLocationRelativeTo(velgLeietakerKnapp);


    }
    public void visVelgBoligVindu()
    {
        velgBoligVindu = new JFrame("Velg bolig");


        //sett utleierid, sånn at det matches på riktig boligsøker

        String leietakerId = valgtLeietaker.getText();
        if(leietakerId.equals("")){
            JOptionPane.showMessageDialog(null, "Ingen boligsøker valgt!");
            return;
        }
        valgtId = leietakerId;





        resultatTabellModell resultatModell = new resultatTabellModell();
        resultatTabell = new JTable(resultatModell);




        //sorter på matchkoefisient
        TableRowSorter<resultatTabellModell> sorterer = new TableRowSorter<>( resultatModell );
        List<TableRowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorterer.setSortKeys(sortKeys);
        resultatTabell.setRowSorter(sorterer);

        //valg
        resultatTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsm = resultatTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(resultatModell));

        velgBoligVindu.add(new JScrollPane(resultatTabell));


        velgBoligVindu.pack();
        velgBoligVindu.setVisible(true);
        velgBoligVindu.setLocationRelativeTo(velgBoligKnapp);
    }
































    public void visKontrakter()
    {

        JOptionPane.showMessageDialog(null, "Viskontakter-metoden kjører");

        kontraktHistorikkTabell = new JTable(kontrakter.tilTabell(),kontraktTabellKolonneNavn);
        c.gridx = 1;
        c.gridy = 15;
        panel5.add(kontraktHistorikkTabell, c);
        /*
        - Burde være easymode. Lage en toString i kontraktliste som sender med en superlang String som kan skrives ut.

        */
        /*
        String ut = kontrakter.toString();
        utområde.append(ut);
        */
    }
    public void visPersonInfo()
    {
        System.out.println("gogo");

        try{
        String id = utleiere.finnID(slettPersonFn, slettPersonEn);
        if(!id.equals(null))
        {
            feedbackFane3.setText(id.toString());
            clearBildePanel();

        }
        else {
            String bs = boligsøkere.finnBoligsøkerID(slettPersonFn,slettPersonEn);
            feedbackFane3.setText(bs.toString());
            clearBildePanel();
        }
        clearBildePanel();
        }
        catch(NumberFormatException nfe) {
            System.out.println("number formatCare");
        }
    }



    public void visBoligInfo() {
        String id = Integer.toString(slettBoligId);



        Bolig valgtBolig = boliger.finnBolig(id);

        String ut = valgtBolig.toString();


        feedbackFane3.setText(ut);

        clearBildePanel();

        ImageIcon image = new ImageIcon(valgtBolig.getBildesti());
        Image img = image.getImage();
        Image skalert = img.getScaledInstance(330,310, Image.SCALE_SMOOTH);
        image = new ImageIcon(skalert);





        JLabel label = new JLabel("", image, JLabel.CENTER);
        //prøv etterpå label.setBounds();
        bildepanel.add(label, BorderLayout.CENTER);

    }
    private void clearBildePanel()
    {
        bildepanel.removeAll();
        revalidate();
    }



    public void slettBoligsøker()
    {
        String[] alternativer = {"Ja", "Nei"};
        if(slettPersonFn.equals("") || slettPersonEn.equals("")){
            JOptionPane.showMessageDialog(null, "DUUUURT");
            return;
        }
        int svar = JOptionPane.showOptionDialog(null,"Sikker på at du vil slette: " + slettPersonFn + " " + slettPersonEn, "Slett person",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
        , null, alternativer, alternativer[0]);
        if( svar == JOptionPane.YES_OPTION){
            String id = boligsøkere.finnBoligsøkerID(slettPersonFn, slettPersonEn);
            if(!id.equals("")) {
                Boligsøker slett = boligsøkere.getBoligsøker(id);
                if(boligsøkere.fjernSøker(slett)){
                    kontrakter.fjernKontrakt(slett);
                    JOptionPane.showMessageDialog(null, "Personen er slettet");
                    slettPersonEn = "";
                    slettPersonFn = "";
                }
            }
            else{
                id = utleiere.finnID(slettPersonFn, slettPersonEn);
                Utleier woop = utleiere.getUtleier(id);
                if(!utleiere.harBoliger(woop)){
                    utleiere.fjernUtleier(slettPersonFn,slettPersonEn);
                    JOptionPane.showMessageDialog(null,"Personen ble slettet");
                }else{
                    JOptionPane.showMessageDialog(null,"Personen har boliger registrert på seg!");
                }

            }
        }
    } //  var det dette som vi skulle kunne slette, eller noe annet?
    public void slettBolig(){
        String[] alternativer = {"Ja", "Nei"};
        String slettDenne = Integer.toString(slettBoligId);
        if(slettDenne.equals("")){
            JOptionPane.showMessageDialog(null,"Velg bolig");
            return;
        }
        Bolig slett = boliger.finnBolig(slettDenne);
        if(slett.getUtleid()){
            JOptionPane.showMessageDialog(null,"Boligen er utleid, kan ikke slettes!");
            return;
        }
        int svar = JOptionPane.showOptionDialog(null,"Sikker på at du vil slette boligen med " + slett.getAdresse() + "?","Slett bolig",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, alternativer, alternativer[0]);
        if( svar == JOptionPane.YES_OPTION){
            if(boliger.slettBolig(slett))
                JOptionPane.showMessageDialog(null,"Boligen ble slettet");
            else
                JOptionPane.showMessageDialog(null,"DUUURT!");
        }

    }

    public void lesFraFil(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("fildata.data"))){

            utleiere = (UtleierListe) in.readObject();
            boligsøkere = (BoligsøkerListe) in.readObject();
            kontrakter = (KontraktListe) in.readObject();
            boliger = (Boligliste) in.readObject();
        }
        catch( ClassNotFoundException cnfe ){
            JOptionPane.showMessageDialog(null,"Kunne ikke finne programmets klasse");
        }
        catch( FileNotFoundException fnfe ){
            JOptionPane.showMessageDialog(null, "Fant ikke fildata.data");
        }
        catch( IOException ioe ){
            JOptionPane.showMessageDialog(null, "Feil med lesing fra fil");
        }
    }
    public void skrivTilFil(){
        try( ObjectOutputStream ut = new ObjectOutputStream(new FileOutputStream("fildata.data"))){
            ut.writeObject(utleiere);
            ut.writeObject(boligsøkere);
            ut.writeObject(kontrakter);
            ut.writeObject(boliger);
            System.out.println("Skriver til fil, ");
        }
        catch( NotSerializableException nse ){
            JOptionPane.showMessageDialog(null, "En av programmets klasser er ikke serialisert");
        }
        catch( IOException ioe ){
            JOptionPane.showMessageDialog(null,"Feil med skriving til fil");
        }
    }
    public void sendEmail(){
        String til = JOptionPane.showInputDialog(null, "Skriv inn din epostadresse");
        Boligsøker send = boligsøkere.getBoligsøker(valgtId);
        if(send == null){
            JOptionPane.showMessageDialog(null, "Velg en bolisøker");
            return;
        }
        String n = send.getFornavn() + " " + send.getEtternavn();
        //String epost = send.getEmail(); for å kunne sende eposten til boligsøkere

        Bolig sendTil = boliger.finnBolig(valgtBoligId);
        if(sendTil == null){
            JOptionPane.showMessageDialog(null, "Velg en bolig");
            return;
        }
        JOptionPane.showMessageDialog(null, valgtBoligId);
        String adresse = sendTil.getAdresse();
        int stedInt = sendTil.getSted();
        String sted = "";
        switch (stedInt){
            case 1: sted = "Oslo";
                    break;
            case 2: sted = "Bergen";
                    break;
            case 3: sted = "Stavanger";
                    break;
            case 4: sted = "Trondheim";
                    break;
            case 5: sted = "Kristiansand";
                    break;
            case 6: sted = "Tromsø";
                    break;

        }
        int pris = sendTil.getUtleiepris();
        epost.sendMail(til,n,adresse, sted, pris);
    }
    public void visKontraktFil(){
        kontrakthistorie.openTekstFil();
    }

}
