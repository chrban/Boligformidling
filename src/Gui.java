/*import javafx.geometry.HorizontalDirection;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;
*/
import javax.swing.*;
//import javax.swing.border.Border;
import java.awt.*;


import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
//import java.rmi.server.UID;
import java.util.*;
import java.util.List;
/*import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.MatteBorder;
*/
import javax.swing.event.*;
import javax.swing.table.*;
//import javax.swing.DefaultRowSorter;
//import javax.swing.text.Document;
import java.lang.*;
//import javax.swing.RowSorter.*;


/**
 * Created by mac on 02.04.14.
 */

public class Gui extends JFrame {
    private JTabbedPane fane = new JTabbedPane();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private JButton regBoligKnapp, regPersonKnapp, regUtleierKnapp, finnBildeKnapp, oppdaterKontrakter, lagreKontrakt, velgUtleierKnapp, velgLeietakerKnapp,velgBoligKnapp,finnMatch, velgUtleier,sendMail,slettPerson,slettBoligKnapp;
    private JTextField fornavn, etternavn, adresse, adresseFane2, mail, firma, tlf, boareal, pris, byggår, tomtAreal, utleierId, bildesti,valgtUtleier, valgtLeietaker,valgtBolig, startDagFelt, startMånedFelt, startÅrFelt, sluttDagFelt, sluttMånedFelt, sluttårFelt,søkefelt;
    private JLabel boligtypeLabel, minPris, maxPris, firmaLabel,tomtArealLabel, antEgtLabel,antEgtLabelFane2, utleierLabel, kontraktHeader,regPersonHeader,planLabel, regBoligHeader, planLabelFane2, romLabelFane2, registerHeader, startLabel,sluttLabel, visKontrakterLabel,matchHeader, mailLabel, mailStatusLabel;
    private JTextArea beskrivelse,feedbackFane1,feedbackFane3, kontraktInfoFelt,feedbackMail;
    private JRadioButton utleier, boligsøker,persontabellRadioknapp,boligtabellRadioknapp;
    private JPanel panel1, bspanel, utpanel, panel2, bopanel, panel3, panel4, pepanel,tapanel,panel5,okpanel,vkpanel,resultatPanel,velgBsPanel,bildepanel, btpanel, kontraktTabellPanel;
    private JComboBox boligtypeBox, byBox, romBox, etasjeBox, planBox, boligtypeBoxFane2, byBoxFane2, romBoxFane2, etasjeBoxFane2, planBoxFane2;
    private JCheckBox kjellerValg, heisValg, garasjeValg, røykerValg, husdyrValg, badValg, kjøkkenValg, balkongValg, kjellerValgFane2, heisValgFane2, garasjeValgFane2, badValgFane2, kjøkkenValgFane2, balkongValgFane2;
    private JSlider minPrisSlider, maxPrisSlider;
    private String[] boligtypeValg = {"Velg boligtype..", "Enebolig", "Hybel", "Leilighet", "Rekkehus"};
    private String[] byvalg = {"Velg by..", "Oslo", "Bergen", "Stavanger", "Trondheim", "Kristiansand", "Tromsø"};
    private String[] romValg = {"Velg ant. rom..", "1", "2", "3", "4", "5", "6"};
    private String[] etasjeValg = {"Velg ant. etg..", "1", "2", "3"};
    private String[] planValg = {"Velg ant. plan", "1", "2", "3", "4", "5", "6", "7"};
    private JTable personTabell, boligTabellTabellen, kontraktHistorikkTabell, utleierValgTabell, leietakerValgTabell,resultatTabell,boligSøkereForMatch;
    private JScrollPane scroll;
    private PersonTypeLytter radioLytter;
    private tabellTypeLytter radioTabellLytter;
    private ButtonGroup radioPerson, radioTabell;
    private UtleierListe utleiere;
    private BoligsøkerListe boligsøkere;
    private KontraktListe kontrakter;
    private Mail epost;
    private KontraktHistorikk kontrakthistorie;
    private idGenerator idMekking;
    private knappLytter lytter;
    private menyLytter øre;
    private Boligliste boliger;
    private JMenuBar menylinje;
    private JMenu filmeny, rediger, matching, kontrakt,register,hjelp,status;
    private JMenuItem om, lagre, angre, visHistorikk,klipput,kopier,liminn,instillinger,avslutt,printmatch,printperson,printbolig,printkontrakter,statistikk;
    private JScrollPane personTabellScroll;
    private JScrollPane boligTabellScroll;
    private JFrame velgUtleierVindu, velgLeietakerVindu, velgBoligVindu, visKontraktHistorikk;
    private String valgtId, valgtBoligId, id, slettPersonFn, slettPersonEn;
    private int slettBoligId;
    private Font headerFont,header2Font, header3Font,knappFont;
    private Color bakFarge, headerFarge,lyseSvart, comboboxFarge, tabellFarge;
    private fanelytter faneøre;


    public Gui() {
        super("BooleanFormidling");

        setExtendedState(JFrame.MAXIMIZED_BOTH); //Setter vinduet maksimert
        setLocationByPlatform(true);


        //Laster inn bilder
        ImageIcon skrivutIkon = sjekkPath("icon/print.png");
        ImageIcon personIkon = sjekkPath("icon/personadd.png");
        ImageIcon tabellIkon = sjekkPath("icon/tabell.png");
        ImageIcon kontraktIkon = sjekkPath("icon/kontrakt.png");
        ImageIcon boligIkon = sjekkPath("icon/addBoligIkon.png");
        ImageIcon matchIkon = sjekkPath("icon/match.png");
        ImageIcon slettpersonIkon = sjekkPath("icon/personSlettBig.png");
        ImageIcon slettboligIkon = sjekkPath("icon/boligSlettBig.png");
        ImageIcon mailIkoin = sjekkPath("icon/mail.png");
        ImageIcon finnmatchIkon = sjekkPath("icon/matchbolig.png");
        ImageIcon logo = sjekkPath("icon/logo_ikon.png");
        if (logo != null)
            setIconImage(logo.getImage());

        //Oppretter Menylinjen
        øre = new menyLytter();
        filmeny = new JMenu("Fil");
        filmeny.setMnemonic('F');
        om = new JMenuItem("Om..");
        om.addActionListener(øre);
        lagre = new JMenuItem(" Lagre");
        lagre.addActionListener(øre);
        ImageIcon lagreIkon= sjekkPath("icon/lagre.png");
        lagre.setIcon(lagreIkon);
        instillinger = new JMenuItem("Instillinger");
        avslutt = new JMenuItem("Avslutt");
        avslutt.addActionListener(øre);
        rediger = new JMenu("Rediger");
        hjelp = new JMenu("Hjelp");
        angre = new JMenuItem("Angre");
        angre.addActionListener(øre);
        klipput = new JMenuItem("Klipp ut");
        kopier = new JMenuItem("Kopier");
        liminn = new JMenuItem("Lim inn");
        matching = new JMenu("Matching");
        printmatch = new JMenuItem("Skriv ut Matchresultat");
        printmatch.addActionListener(øre);
        printmatch.setIcon(skrivutIkon);
        kontrakt = new JMenu("Kontrakt");
        visHistorikk = new JMenuItem("Vis kontrakhistorikk");
        visHistorikk.addActionListener(øre);
        printkontrakter = new JMenuItem("Skriv ut Kontrakter");
        printkontrakter.addActionListener(øre);
        printkontrakter.setIcon(skrivutIkon);
        register = new JMenu("Register");
        printbolig = new JMenuItem("skriv ut Boligregister");
        printbolig.addActionListener(øre);
        printbolig.setIcon(skrivutIkon);
        printperson = new JMenuItem("Skriv ut Personregister");
        printperson.addActionListener(øre);
        printperson.setIcon(skrivutIkon);
        status = new JMenu("Status");
        statistikk = new JMenuItem("Statistikk");
        statistikk.addActionListener(øre);

        rediger.add(angre);
        rediger.add(klipput);
        rediger.add(kopier);
        rediger.add(liminn);
        hjelp.add(om);
        filmeny.add(lagre);
        filmeny.add(instillinger);
        filmeny.add(avslutt);
        matching.add(printmatch);
        register.add(printbolig);
        register.add(printperson);
        kontrakt.add(visHistorikk);
        kontrakt.add(printkontrakter);
        status.add(statistikk);

        menylinje = new JMenuBar();
        setJMenuBar(menylinje);
        menylinje.add(filmeny);
        menylinje.add(rediger);
        menylinje.add(matching);
        menylinje.add(register);
        menylinje.add(kontrakt);
        menylinje.add(hjelp);
        menylinje.add(status);
        //Slutt menylinje



        //Datatruktur
        boliger = new Boligliste();
        utleiere = new UtleierListe(boliger);
        boligsøkere = new BoligsøkerListe();
        kontrakter = new KontraktListe();
        epost = new Mail();
        kontrakthistorie = new KontraktHistorikk();
        idMekking = new idGenerator();

        //Lyttere
        lytter = new knappLytter();
        faneøre = new fanelytter();
        personTabellScroll = new JScrollPane(personTabell);
        boligTabellScroll = new JScrollPane(boligTabellTabellen);


        //Oppretter paneler
        panel1 = new JPanel(layout);  // FANE panel
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
        bopanel.setVisible(true);
        bspanel.setVisible(false);
        utpanel.setVisible(false);
        pepanel.setVisible(true);
        tapanel.setVisible(true);
        resultatPanel.setVisible(true);
        velgBsPanel.setVisible(true);




        // Farger og Fonter
        headerFont = new Font("Arial",Font.BOLD,50);
        knappFont = new Font("Arial",Font.PLAIN,15);
        header2Font = new Font("Arial",Font.BOLD,20);
        header3Font = new Font("Arial",Font.BOLD,40);
        comboboxFarge = new Color(255,255,255);
        bakFarge = new Color(84,133,150);
        headerFarge= new Color(255,255,255);
        tabellFarge = new Color(190,218,218);

        //Fargelegger
        panel1.setBackground(bakFarge);
        panel2.setBackground(bakFarge);
        bspanel.setBackground(bakFarge);
        pepanel.setBackground(bakFarge);
        pepanel.setForeground(lyseSvart);
        btpanel.setBackground(bakFarge);
        btpanel.setForeground(lyseSvart);
        bopanel.setBackground(bakFarge);
        panel5.setBackground(bakFarge);

        //Oppretter faner
        fane.addTab("Registrer Person", personIkon, panel1, "Registrere ny boligsøker eller utleier");
        fane.addTab("Registrer bolig", boligIkon, panel2, "Registrere ny bolig");
        fane.addTab("Register", tabellIkon, panel3, "Vis register");
        fane.addTab("MatchMaking", matchIkon, panel4, "Finn bolig til registrerte boligsøkere");
        fane.addTab("Kontrakter", kontraktIkon, new JScrollPane(panel5), "Registrer og se kontrakter");
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
        //Start fane 1 - Registrer person


        regPersonHeader = new JLabel("Registrer en klient");
        regPersonHeader.setFont(headerFont);
        regPersonHeader.setForeground(headerFarge);
        c.gridx = 0;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(20, 0, 20, 0);
        c.anchor = GridBagConstraints.CENTER;
        panel1.add(regPersonHeader, c);

        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        panel1.add(pepanel, c);


        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0, 50, 0, 0);
        bspanel.setSize(bspanel.getPreferredSize());
        panel1.add(bspanel, c);

        c.weighty = 0;
        c.insets = new Insets(50, 0, 0, 0);
        panel1.add(utpanel, c);

        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        c.insets = new Insets(0, 20, 0, 0);
        c.weighty = -10;
        panel1.add(btpanel, c);


        feedbackFane1 = new JTextArea("");
        feedbackFane1.setEditable(false);
        feedbackFane1.setBackground(bakFarge);
        feedbackFane1.setPreferredSize(new Dimension(200, 20));
        c.gridx = 0;
        c.gridy = 4;
        panel1.add(feedbackFane1, c);
        panel2.add(bopanel);



        // Reseter
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 5, 5);
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0;


        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        pepanel.add(new JLabel("Fornavn: "), c);



        fornavn = new JTextField(20);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        fornavn.setToolTipText("Skriv inn fornavn");
        fornavn.getDocument().addDocumentListener(documentListener);
        pepanel.add(fornavn, c);


        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        pepanel.add(new JLabel("Etternavn: "), c);

        etternavn = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        etternavn.getDocument().addDocumentListener(documentListener);
        etternavn.setToolTipText("Skriv inn etternavn");
        pepanel.add(etternavn, c);


        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        pepanel.add(new JLabel("Adresse: "), c);

        adresse = new JTextField(20);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        pepanel.add(adresse, c);


        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        pepanel.add(new JLabel("Mail: "), c);

        mail = new JTextField(20);
        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        pepanel.add(mail, c);


        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        pepanel.add(new JLabel("Telefon: "), c);

        tlf = new JTextField(20);
        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        pepanel.add(tlf, c);


        radioLytter = new PersonTypeLytter();
        radioPerson = new ButtonGroup();
        utleier = new JRadioButton("Utleier", false);

        utleier.addActionListener(radioLytter);
        utleier.setBackground(bakFarge);
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        pepanel.add(utleier, c);


        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
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

        regUtleierKnapp = new JButton("Registrer  ");
        regUtleierKnapp.addActionListener(lytter);
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 15;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(20, 0, 5, 5);
        regUtleierKnapp.setMargin(new Insets(0, 30, 0, 30));
        regUtleierKnapp.setVisible(false);
        regUtleierKnapp.setIcon(personIkon);
        regUtleierKnapp.setVerticalTextPosition(SwingConstants.CENTER);
        regUtleierKnapp.setHorizontalTextPosition(SwingConstants.LEFT);
        pepanel.add(regUtleierKnapp, c);



        //BOLISØKER PANEL (bspanel)

        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0;
        c.insets = new Insets(5, 10, 10, 5);
        boligtypeLabel = new JLabel("Boligtype");
        bspanel.add(boligtypeLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0;
        boligtypeBox = new JComboBox(boligtypeValg);
        boligtypeBox.setBackground(comboboxFarge);
        boligtypeBox.setPreferredSize(new Dimension(200, 20));
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
        byBox.setBackground(comboboxFarge);
        byBox.setPreferredSize(new Dimension(200, 20));
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
        romBox.setBackground(comboboxFarge);
        romBox.setPreferredSize(new Dimension(200, 20));
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
        c.gridwidth = 2;
        c.ipadx = 100;
        c.insets = new Insets(5, 0, 10, 5);

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
        minPrisSlider.setBackground(bakFarge);
        bspanel.add(minPrisSlider, c);

        c.insets = new Insets(5, 10, 10, 5);
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
        c.insets = new Insets(5, 0, 10, 5);
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
        maxPrisSlider.setBackground(bakFarge);
        bspanel.add(maxPrisSlider, c);

        c.insets = new Insets(5, 10, 10, 5);
        røykerValg = new JCheckBox("Røker");
        c.ipadx = 0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        røykerValg.setVisible(true);
        røykerValg.setBackground(bakFarge);
        bspanel.add(røykerValg, c);


        husdyrValg = new JCheckBox("Husdyr");
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        husdyrValg.setVisible(true);
        husdyrValg.setBackground(bakFarge);
        bspanel.add(husdyrValg, c);


        garasjeValg = new JCheckBox("Garasje");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        garasjeValg.setVisible(false);
        garasjeValg.setBackground(bakFarge);
        bspanel.add(garasjeValg, c);


        kjellerValg = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        kjellerValg.setVisible(false);
        kjellerValg.setBackground(bakFarge);
        bspanel.add(kjellerValg, c);


        heisValg = new JCheckBox("Heis");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        heisValg.setVisible(false);
        heisValg.setBackground(bakFarge);
        bspanel.add(heisValg, c);


        balkongValg = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        balkongValg.setVisible(false);
        balkongValg.setBackground(bakFarge);
        bspanel.add(balkongValg, c);


        badValg = new JCheckBox("Eget Bad");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        badValg.setVisible(false);
        badValg.setBackground(bakFarge);
        bspanel.add(badValg, c);


        kjøkkenValg = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        kjøkkenValg.setVisible(false);
        kjøkkenValg.setBackground(bakFarge);
        bspanel.add(kjøkkenValg, c);


        antEgtLabel = new JLabel("Ant Etasjer: ");
        c.gridx = 0;
        c.ipadx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        antEgtLabel.setVisible(false);
        bspanel.add(antEgtLabel, c);


        etasjeBox = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 8;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        etasjeBox.setVisible(false);
        etasjeBox.setBackground(comboboxFarge);
        bspanel.add(etasjeBox, c);


        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        planLabel = new JLabel("Plan: ");
        bspanel.add(planLabel, c);
        planLabel.setVisible(false);


        planBox = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        planBox.setBackground(comboboxFarge);
        planBox.addActionListener(new boligTypeLytter());
        bspanel.add(planBox, c);
        planBox.setVisible(false);


        regPersonKnapp = new JButton("Registrer  ");
        regPersonKnapp.addActionListener(lytter);
        c.gridx = 1;
        c.gridy = 9;

        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(15, 10, 0, 0);
        regPersonKnapp.setMargin(new Insets(0, 30, 0, 30));
        regPersonKnapp.setVisible(false);
        regPersonKnapp.setIcon(personIkon);
        regPersonKnapp.setVerticalTextPosition(SwingConstants.CENTER);
        regPersonKnapp.setHorizontalTextPosition(SwingConstants.LEFT);
        bspanel.add(regPersonKnapp, c);
        //End fane 1




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
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        regBoligHeader = new JLabel("Registrer bolig");
        regBoligHeader.setFont(headerFont);
        regBoligHeader.setForeground(headerFarge);
        bopanel.add(regBoligHeader, c);


        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 10);
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
        byBoxFane2.setForeground(lyseSvart);
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
        boligtypeBoxFane2.setBackground(comboboxFarge);
        boligtypeBoxFane2.setForeground(lyseSvart);
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
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        romBoxFane2.setBackground(comboboxFarge);
        romBoxFane2.setForeground(lyseSvart);
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
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        etasjeBoxFane2.setBackground(comboboxFarge);
        etasjeBoxFane2.setForeground(lyseSvart);
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
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        planBoxFane2.setBackground(comboboxFarge);
        planBoxFane2.setForeground(lyseSvart);
        bopanel.add(planBoxFane2, c);
        planBoxFane2.setVisible(false);


        garasjeValgFane2 = new JCheckBox("Garasje");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        garasjeValgFane2.setBackground(bakFarge);
        garasjeValgFane2.setForeground(lyseSvart);
        garasjeValgFane2.setVisible(false);
        bopanel.add(garasjeValgFane2, c);


        kjellerValgFane2 = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        kjellerValgFane2.setBackground(bakFarge);
        kjellerValgFane2.setForeground(lyseSvart);
        kjellerValgFane2.setVisible(false);
        bopanel.add(kjellerValgFane2, c);


        heisValgFane2 = new JCheckBox("Heis");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        heisValgFane2.setBackground(bakFarge);
        heisValgFane2.setForeground(lyseSvart);
        heisValgFane2.setVisible(false);
        bopanel.add(heisValgFane2, c);


        balkongValgFane2 = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        balkongValgFane2.setBackground(bakFarge);
        balkongValgFane2.setForeground(lyseSvart);
        balkongValgFane2.setVisible(false);
        bopanel.add(balkongValgFane2, c);


        badValgFane2 = new JCheckBox("Eget Bad");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        badValgFane2.setBackground(bakFarge);
        badValgFane2.setForeground(lyseSvart);
        badValgFane2.setVisible(false);
        bopanel.add(badValgFane2, c);

        kjøkkenValgFane2 = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        kjøkkenValgFane2.setBackground(bakFarge);
        kjøkkenValgFane2.setForeground(lyseSvart);
        kjøkkenValgFane2.setVisible(false);
        bopanel.add(kjøkkenValgFane2, c);


        c.gridx = 0;
        c.gridy = 14;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        bopanel.add(new JLabel("Beskrivelse:"), c);

        beskrivelse = new JTextArea("Beskrivelse av bolig..", 5, 20);
        scroll = new JScrollPane(beskrivelse);
        c.gridx = 1;
        c.gridy = 14;
        c.gridheight = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        beskrivelse.addMouseListener( new MouseAdapter() { //Klarerer infofelstet ved museklikk
            public void mouseClicked(MouseEvent e) {
                if(beskrivelse.getText().equals("Beskrivelse av bolig..")) {
                    beskrivelse.setText("");
                    revalidate();
                }}});
        bopanel.add(scroll, c);



        c.gridheight = 1;
        bildesti = new JTextField(20);
        c.gridx = 1;
        c.gridy = 16;
        bildesti.setEditable(false);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        bopanel.add(bildesti, c);

        finnBildeKnapp = new JButton("Finn bilde");
        finnBildeKnapp.addActionListener(lytter);
        finnBildeKnapp.setMargin(new Insets(0, 13, 0, 13));

        c.gridx = 2;
        c.gridy = 16;
        bopanel.add(finnBildeKnapp, c);

        velgUtleier = new JButton("Velg utleier");
        velgUtleier.addActionListener(lytter);
        velgUtleier.setMargin(new Insets(0, 8, 0, 8));
        c.gridx = 2;
        c.gridy = 17;
        bopanel.add(velgUtleier, c);


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


        c.ipady = 0;
        regBoligKnapp = new JButton("Registrer  ");
        regBoligKnapp.addActionListener(lytter);
        regBoligKnapp.setMargin(new Insets(0, 14, 0, 14));
        c.gridx = 2;
        c.gridy = 18;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 5, 10);
        regBoligKnapp.setIcon(boligIkon);
        regBoligKnapp.setVerticalTextPosition(SwingConstants.CENTER);
        regBoligKnapp.setHorizontalTextPosition(SwingConstants.LEFT);
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
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 0;
        //resatt


        søkefelt = new JTextField("Søk..",20);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        søkefelt.getDocument().addDocumentListener(documentListener);
        søkefelt.requestFocus(false);
        søkefelt.addMouseListener( new MouseAdapter() { //Klarerer søkefeltet ved museklikk
            public void mouseClicked(MouseEvent e) {
                if(søkefelt.getText().equals("Søk..")){
                    søkefelt.setText("");
                    revalidate();}}});
        panel3.add(søkefelt,c);


        registerHeader = new JLabel("Register");
        registerHeader.setFont(headerFont);
        registerHeader.setForeground(headerFarge);

        //c.insets = new Insets(50, 10, 50, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        panel3.add(registerHeader, c);
        c.insets = new Insets(0, 0, 0, 0);//reseter


        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 10;
        c.weighty = 40;
       // c.gridheight=2;
        c.insets = new Insets(5, 5, 5, 5);
        panel3.add(tapanel, c);


        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 0;
        //resatt



        radioTabellLytter = new tabellTypeLytter();
        radioTabell = new ButtonGroup();

        persontabellRadioknapp = new JRadioButton("Personregister", false);
        persontabellRadioknapp.addActionListener(radioTabellLytter);
        c.insets = new Insets(10,50,50,0);
        c.gridx = 1;
        c.gridy = 1;
        panel3.add(persontabellRadioknapp, c);


        boligtabellRadioknapp = new JRadioButton("Boligregister", false);
        boligtabellRadioknapp.addActionListener(radioTabellLytter);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(10,0,50,50);
        panel3.add(boligtabellRadioknapp, c);


        radioTabell.add(persontabellRadioknapp);
        radioTabell.add(boligtabellRadioknapp);
        slettPerson = new JButton("Slett person  ");
        slettPerson.addActionListener(lytter);
        c.gridx = 1;
        c.gridy = 2;
        c.ipady = 30;
        c.gridwidth=2;
        c.ipady = 30;
        c.insets = new Insets(0,50,0,30);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill=GridBagConstraints.HORIZONTAL;
        slettPerson.setVisible(false);
        slettPerson.setIcon(slettpersonIkon);
        slettPerson.setVerticalTextPosition(SwingConstants.CENTER);
        slettPerson.setHorizontalTextPosition(SwingConstants.LEFT);
        slettPerson.setFont(knappFont);
        panel3.add(slettPerson, c);

        slettBoligKnapp = new JButton("Slett bolig  ");
        slettBoligKnapp.addActionListener(lytter);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth=2;
        c.ipady = 30;
        c.insets = new Insets(0,50,0,50);
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill=GridBagConstraints.HORIZONTAL;
        slettBoligKnapp.setVisible(false);
        slettBoligKnapp.setIcon(slettboligIkon);
        slettBoligKnapp.setVerticalTextPosition(SwingConstants.CENTER);
        slettBoligKnapp.setHorizontalTextPosition(SwingConstants.LEFT);
        slettBoligKnapp.setFont(knappFont);
        panel3.add(slettBoligKnapp, c);

        //reset
        c.gridwidth=1;
        c.insets = new Insets(0,0,0,0);
        c.ipadx = 0;


        feedbackFane3 = new JTextArea("");
        feedbackFane3.setBackground(bakFarge);
        feedbackFane3.setSize(500, 200);
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 10;
        c.weighty = 5;
        c.insets = new Insets(2,40,40,0);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        panel3.add(feedbackFane3, c);

        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0;
        c.weighty = 0;
        //resatt

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth=2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor=GridBagConstraints.PAGE_END;
        panel3.add(bildepanel, c);



        bildepanel.setBackground(bakFarge);
        panel3.setBackground(bakFarge);
        tapanel.setBackground(bakFarge);
        //SLUTT FANE 3


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


        //FANE 4 - MATCHMAKING ************************************************************************************************************************************************************************

        visMatch();
        //RESETER GridBag påsan! c
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;

        matchHeader = new JLabel("MachMaking");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 0, 30, 0);
        matchHeader.setFont(headerFont);
        matchHeader.setForeground(headerFarge);
        panel4.add(matchHeader);

        finnMatch = new JButton("Finn Match!   ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.ipady = 30;
        c.insets = new Insets(10, 0, 10, 0);
        finnMatch.setIcon(finnmatchIkon);
        finnMatch.setVerticalTextPosition(SwingConstants.CENTER);
        finnMatch.setHorizontalTextPosition(SwingConstants.LEFT);
        c.anchor = GridBagConstraints.PAGE_END;
        finnMatch.addActionListener(lytter);
        panel4.add(finnMatch, c);


        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.ipady = 0;

        c.insets = new Insets(10, 60, 10, 0);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = -1;
        mailLabel = new JLabel("Mail:");
        mailLabel.setFont(headerFont);
        mailLabel.setForeground(headerFarge);
        panel4.add(mailLabel, c);

        feedbackMail = new JTextArea("", 15, 15);
        JScrollPane sspp = new JScrollPane(feedbackMail);
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 150;
        c.gridheight = 2;
        c.weighty = 0;
        c.insets = new Insets(0, 60, 0, 20);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.SOUTHWEST;
        feedbackMail.setBackground(tabellFarge);
        panel4.add(sspp, c);
        c.gridheight = 1;
        c.ipadx = 100;

        sendMail = new JButton("Send mail   ");
        c.gridx = 1;
        c.gridy = 3;
        c.ipady = 30;

        c.insets = new Insets(20, 60, 20, 40);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTH;
        sendMail.setMargin(new Insets(0,0,0,0));
        sendMail.addActionListener(lytter);
        sendMail.setIcon(mailIkoin);
        sendMail.setVerticalTextPosition(SwingConstants.CENTER);
        sendMail.setHorizontalTextPosition(SwingConstants.LEFT);
        panel4.add(sendMail, c);

        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(0, 60, 80, 20);
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 0;
        mailStatusLabel = new JLabel("");
        panel4.add(mailStatusLabel, c);

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.ipady = 0;

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 10;
        c.weighty = 40;
        c.insets = new Insets(0, 20, 5, 0);
        panel4.add(velgBsPanel, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 10;
        c.weighty = 40;
        c.insets = new Insets(5, 20, 20, 0);
        panel4.add(resultatPanel, c);
        panel4.setBackground(bakFarge);


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
        okpanel = new JPanel(layout);
        vkpanel = new JPanel(layout);
        kontraktTabellPanel = new JPanel(layout);
        kontraktTabellPanel.setBackground(tabellFarge);
        c.ipadx = 0;
        Dimension dim = new Dimension(20, 20);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;

        c.anchor = GridBagConstraints.CENTER;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 50, 0);
        kontraktHeader = new JLabel("Opprett kontrakt");
        kontraktHeader.setFont(header3Font);
        kontraktHeader.setForeground(headerFarge);
        okpanel.add(kontraktHeader,c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 20, 5);
        c.ipadx = 100;
        velgLeietakerKnapp = new JButton("Velg en Leietaker");
        velgLeietakerKnapp.addActionListener(lytter);
        velgLeietakerKnapp.setMargin(new Insets(0,5,0,5));
        velgLeietakerKnapp.setPreferredSize(dim);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(velgLeietakerKnapp, c);

        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 20, 0);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        valgtLeietaker = new JTextField(10);
        valgtLeietaker.setEditable(false);
        valgtLeietaker.setText("Ingen leietaker valgt");
        okpanel.add(valgtLeietaker, c);

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 20, 5);
        c.ipadx = 100;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        velgBoligKnapp = new JButton("Velg en Bolig");
        velgBoligKnapp.addActionListener(lytter);
        velgBoligKnapp.setMargin(new Insets(0,10,0,10));
        velgBoligKnapp.setPreferredSize(dim);
        okpanel.add(velgBoligKnapp, c);
        velgBoligKnapp.setVisible(false);

        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        valgtBolig = new JTextField(10);
        valgtBolig.setEditable(false);
        valgtBolig.setText("Ingen bolig valgt");
        okpanel.add(valgtBolig, c);
        valgtBolig.setVisible(false);

        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        utleierLabel = new JLabel("Utleier: ");
        okpanel.add(utleierLabel,c);
        utleierLabel.setVisible(false);

        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        valgtUtleier = new JTextField(10);
        valgtUtleier.setEditable(false);
        valgtUtleier.setText("Ingen utleier valgt");
        okpanel.add(valgtUtleier, c);
        valgtUtleier.setVisible(false);

        startDagFelt = new JTextField(10);
        startMånedFelt = new JTextField(10);
        startÅrFelt = new JTextField(10);
        sluttDagFelt = new JTextField(10);
        sluttMånedFelt = new JTextField(10);
        sluttårFelt = new JTextField(10);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.insets = new Insets(30,30, 15, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        startLabel = new JLabel("Kontrakten starter:");
        startLabel.setFont(header2Font);
        startLabel.setForeground(headerFarge);
        okpanel.add(startLabel,c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 5, 3);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(new JLabel("Dag(DD)"), c);

        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        okpanel.add(startDagFelt, c);

        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(new JLabel("Måned(MM)"), c);

        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        okpanel.add((startMånedFelt), c);

        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(new JLabel("År(ÅÅÅÅ)"), c);

        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        okpanel.add((startÅrFelt), c);

        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 2;
        c.insets = new Insets(30, 30, 15, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        sluttLabel = new JLabel("Kontrakten slutter");
        sluttLabel.setFont(header2Font);
        sluttLabel.setForeground(headerFarge);
        okpanel.add(sluttLabel,c);

        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 5, 3);
        c.gridx = 0;
        c.gridy = 10;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(new JLabel("Dag(DD)"), c);

        c.gridx = 1;
        c.gridy = 10;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        okpanel.add((sluttDagFelt), c);

        c.gridx = 0;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(new JLabel("Måned(MM)"), c);

        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        okpanel.add((sluttMånedFelt), c);

        c.insets = new Insets(0, 0, 20, 3);
        c.gridx = 0;
        c.gridy = 12;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        okpanel.add(new JLabel("År(ÅÅÅÅ)"), c);

        c.gridx = 1;
        c.gridy = 12;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        okpanel.add((sluttårFelt), c);

        c.insets = new Insets(0, 0, 5, 0);
        c.gridx = 1;
        c.gridy = 13;
        c.ipadx = 100;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        lagreKontrakt = new JButton("Lagre kontrakt");
        lagreKontrakt.addActionListener(lytter);
        lagreKontrakt.setMargin(new Insets(0, 0, 0, 0));
        lagreKontrakt.setPreferredSize(dim);
        okpanel.add(lagreKontrakt, c);

        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,100);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTH;
        okpanel.setBackground(bakFarge);
        vkpanel.setBackground(bakFarge);
        panel5.add(okpanel,c);

        visKontrakterLabel = new JLabel("Lagrede kontrakter");
        visKontrakterLabel.setFont(header3Font);
        visKontrakterLabel.setForeground(headerFarge);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,40,0);
        c.anchor = GridBagConstraints.CENTER;
        vkpanel.add(visKontrakterLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.CENTER;
        vkpanel.add(kontraktTabellPanel, c);

        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 100;
        c.gridwidth = 2;
        c.insets = new Insets(30,0,30,0);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        oppdaterKontrakter = new JButton("Oppdater Register");
        oppdaterKontrakter.addActionListener(lytter);
        oppdaterKontrakter.setMargin(new Insets(0,0,0,0));
        oppdaterKontrakter.setPreferredSize(dim);
        vkpanel.add(oppdaterKontrakter, c);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;

        kontraktInfoFelt = new JTextArea(10,30);
        kontraktInfoFelt.setBackground(tabellFarge);
        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0,0,10,0);
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        vkpanel.add(kontraktInfoFelt,c);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,80,0,0);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTH;
        panel5.add(vkpanel,c);
        //End fane 5



        fane.setPreferredSize(new Dimension(1380, 1010));
        fane.setBackground(bakFarge);
        JScrollPane sp = new JScrollPane();
        getContentPane().add(sp);
        add(fane);      //Legger faner til hovedvindu
        lesFraFil();    //Last inn data
        visKontrakter();
    } // End GUI konstruktør

    //----------------------------------------------------
    //---------------START OF REG-METODER-----------------
    //----------------------------------------------------

    public void regPerson() {


        if (boligsøker.isSelected()) {

            int bt, by, rom, minPris, maxPris, park, antE, kjeller,heis, balkong, dbm, dkm, plan;

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
            plan = 0;

            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String t = tlf.getText();
            String email = mail.getText();
            String bType = (String) boligtypeBox.getSelectedItem();
            String byInn = (String) byBox.getSelectedItem();

            try {
                if (romBox.isVisible())
                    rom = Integer.parseInt((String) romBox.getSelectedItem());

                minPris = minPrisSlider.getValue();

                maxPris = maxPrisSlider.getValue();

                if (etasjeBox.isVisible()) {
                    antE = Integer.parseInt((String) etasjeBox.getSelectedItem());
                }

                if (planBox.isVisible())
                    plan = Integer.parseInt((String) planBox.getSelectedItem());
            } catch (NumberFormatException nfe) {
                return;
            }


            switch (bType) {
                case "Enebolig":
                    bt = 1;
                    break;
                case "Rekkehus":
                    bt = 2;
                    break;
                case "Leilighet":
                    bt = 3;
                    break;
                case "Hybel":
                    bt = 4;
                    break;
                default:
                    bt = 0;
                    break;
            }

            switch (byInn) {
                case "Oslo":
                    by = 1;
                    break;
                case "Bergen":
                    by = 2;
                    break;
                case "Stavanger":
                    by = 3;
                    break;
                case "Trondheim":
                    by = 4;
                    break;
                case "Kristiansand":
                    by = 5;
                    break;
                case "Tromsø":
                    by = 6;
                    break;
                default:
                    by = 0;
                    break;
            }


            if (garasjeValg.isSelected())
                park = 1;
            if (heisValg.isSelected())
                heis = 1;
            if (kjellerValg.isSelected())
                kjeller = 0;
            if (badValg.isSelected())
                dbm = 1;
            if (kjøkkenValg.isSelected())
                dkm = 1;
            if (balkongValg.isSelected())
                balkong = 1;


            if (fnavn.equals("") || fnavn.length() < 2 || enavn.equals("") || enavn.length() < 2 || t.equals("") || t.length() < 2 || ad.length() < 2 || ad.equals("") || email.equals("") || email.length() < 2 || bt == 0 || by == 0 || rom == 0) {

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

            } else {
                String id = idMekking.setIdPåBoligsøker(enavn, fnavn);

                if (bt == 1 || bt == 2) //hvis enebolig
                {
                    if (antE != 0) {
                        Boligsøker ny = new Boligsøker(id, fnavn, enavn, ad, t, email, bt, by, rom, maxPris, minPris, park, antE, kjeller, heis, balkong, dbm, dkm);
                        boligsøkere.settInnNy(ny);
                        clearPersonFelt();
                        clearBSfelt();
                        return;
                    } else {
                        gyldigBox(etasjeBox);
                        return;
                    }
                }

                if (bt == 3 && plan == 0) {
                    feedbackFane1.setText("Velg ønsket etasje");
                    gyldigBox(planBox);
                    return;
                }

                Boligsøker ny = new Boligsøker(id, fnavn, enavn, ad, t, email, bt, by, rom, maxPris, minPris, park, antE, kjeller, heis, balkong, dbm, dkm);
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
        else if (utleier.isSelected()) {
            String fnavn = fornavn.getText();
            String enavn = etternavn.getText();
            String ad = adresse.getText();
            String t = tlf.getText();
            String email = mail.getText();
            String firm = firma.getText();


            if (fnavn.equals("") || fnavn.length() < 2 || enavn.equals("") || enavn.length() < 2 || ad.equals("") || ad.length() < 2 || t.equals("") || t.length() < 2 || email.equals("") || email.length() < 2 || firm.equals("") || firm.length() < 2) {
                gyldig(fornavn);
                gyldig(etternavn);
                gyldig(adresse);
                gyldig(tlf);
                gyldig(mail);
                gyldig(firma);
            } else {

                String id = idMekking.setIdPåUtleier(firm, enavn, fnavn);

                Utleier ny = new Utleier(id, fnavn, enavn, ad, t, email, firm);
                utleiere.settInn(ny);
                clearPersonFelt();
                clearBSfelt();
                feedbackFane1.setText("Utleier registrert med id: " + ny.getId());

                return;
            }
            feedbackFane1.setText("Du må skrive inn i alle feltene");
            return;

        }
        JOptionPane.showMessageDialog(null, "du må velge en av typene..."); //vil aldri intreffe
    }//end class regPerson

    public void regBolig() {

        String adr = adresseFane2.getText();
        String arealString = boareal.getText();
        String årString = byggår.getText();
        String utPrisString = pris.getText();
        String utId = utleierId.getText();
        String tAreal = tomtAreal.getText();
        String beskrivelseString = beskrivelse.getText();


        if (arealString.equals("") || adr.equals("") || årString.equals("") || utPrisString.equals("") || utId.equals("")) {
            gyldig(adresseFane2);
            gyldig(boareal);
            gyldig(byggår);
            gyldig(pris);
            gyldig(utleierId);
            return;
        }


        int areal;
        int år;
        int upris;
        int tomtareal = 0;
        String valg = (String)boligtypeBoxFane2.getSelectedItem();
        int btype;
        switch(valg){
            case "Enebolig":    btype = 1;
                break;
            case "Rekkehus":
                btype = 2;
                break;
            case "Leilighet":
                btype = 3;
                break;
            case "Hybel":
                btype = 4;
                break;
            case "Velg boligtype..":
                btype = 0;
                break;
            default:
                btype = 0;
                break;
        }
        try {
            areal = Integer.parseInt(arealString);
            år = Integer.parseInt(årString);
            upris = Integer.parseInt(utPrisString);
            if (btype == 1 || btype == 2) {
                try {
                    tomtareal = Integer.parseInt(tAreal);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Du må skrive inn skikkelige verdier!");
            return;
        }


        // Comboboxer
        String by = (String) byBoxFane2.getSelectedItem();
        int byvalg;
        switch (by) {
            case "Oslo":
                byvalg = 1;
                break;
            case "Bergen":
                byvalg = 2;
                break;
            case "Stavanger":
                byvalg = 3;
                break;
            case "Trondheim":
                byvalg = 4;
                break;
            case "Kristiansand":
                byvalg = 5;
                break;
            case "Tromsø":
                byvalg = 6;
                break;
            case "Velg by..":
                byvalg = 0;
                break;
            default:
                byvalg = 0;
                break;
        }
        int rom = 0;
        int antetasjer;
        int plan = 0;


        if (btype != 4)
            if (romBoxFane2.getSelectedIndex() == 0)
                gyldigBox(romBoxFane2);
            else
                rom = Integer.parseInt((String)romBoxFane2.getSelectedItem());


        if (etasjeBoxFane2.getSelectedItem().equals("Velg ant. etg.."))
            antetasjer = 0;
        else
            antetasjer = Integer.parseInt((String) etasjeBoxFane2.getSelectedItem());


        if (btype == 3)
            if (planBoxFane2.getSelectedIndex() == 0)
                gyldigBox(planBoxFane2);
            else plan = Integer.parseInt((String) planBoxFane2.getSelectedItem());


        int kjeller = -1;
        int heis = -1;
        int garasje = -1;
        int badInt = -1;
        int kjøkkenInt = -1;
        int balkong = -1;

        if (kjellerValgFane2.isSelected())
            kjeller = 1;
        if (heisValgFane2.isSelected())
            heis = 1;
        if (garasjeValgFane2.isSelected())
            garasje = 1;
        if (balkongValgFane2.isSelected())
            balkong = 1;
        if (badValgFane2.isSelected())
            badInt = 1;
        if (kjøkkenValgFane2.isSelected())
            kjøkkenInt = 1;

        String sti = null;
        try{sti = bildesti.getText();}
        catch(NullPointerException npe){

        }

        int id = 0;

        switch(btype){
            case 1:      id = idMekking.setIdPåBolig(1);
                Enebolig nyEnebolig = new Enebolig(id, adr,byvalg, areal, rom, år, upris, utId, sti, antetasjer, garasje, kjeller, tomtareal,beskrivelseString);
                if(boliger.leggTil(nyEnebolig)) {
                    JOptionPane.showMessageDialog(null, "Registrering vellykket!");
                    clearBoligfelt();
                }
                else
                    JOptionPane.showMessageDialog(null,"Ble ikke registrert!");
                break;
            case 2:      id = idMekking.setIdPåBolig(2);
                Rekkehus nyttRekkehus = new Rekkehus(id, adr,byvalg, areal, rom, år, upris, utId, sti, antetasjer,garasje, kjeller, tomtareal,beskrivelseString);
                if(boliger.leggTil(nyttRekkehus)) {
                    JOptionPane.showMessageDialog(null, "Registrering vellykket");
                    clearBoligfelt();

                }
                else
                    JOptionPane.showMessageDialog(null,"Ble ikke registrert!");
                break;
            case 3:      id = idMekking.setIdPåBolig(3);
                Leilighet nyLeilighet = new Leilighet(id, adr,byvalg, areal, rom, år, upris, utId, sti, plan, balkong, heis, beskrivelseString);
                if(boliger.leggTil(nyLeilighet)) {
                    JOptionPane.showMessageDialog(null, "Registrering vellykket");
                    clearBoligfelt();
                }
                else
                    JOptionPane.showMessageDialog(null,"Ble ikke registrert!");
                break;
            case 4:      id = idMekking.setIdPåBolig(4);
                Hybel nyHybel = new Hybel(id,adr,byvalg, areal, rom, år, upris, utId, sti, badInt, kjøkkenInt,beskrivelseString);
                if(boliger.leggTil(nyHybel)) {
                    JOptionPane.showMessageDialog(null, "Registrering vellykket");
                    clearBoligfelt();
                }
                else
                    JOptionPane.showMessageDialog(null,"Ble ikke registrert!");
                break;
        }
    }//end class regBolig

    public void finnBilde() {
        JFileChooser filvelger = new JFileChooser();
        filvelger.setCurrentDirectory(new File("."));
        int resultat = filvelger.showOpenDialog(this);

        if (resultat == JFileChooser.APPROVE_OPTION) {
            File f = filvelger.getSelectedFile();
            String filsti = f.getPath();
            bildesti.setText(filsti);
        }
    }

    public void slettBoligsøker() {
        String[] alternativer = {"Ja", "Nei"};
        if (slettPersonFn.equals("") || slettPersonEn.equals("")) {
            JOptionPane.showMessageDialog(null, "DUUUURT");
            return;
        }
        int svar = JOptionPane.showOptionDialog(null, "Sikker på at du vil slette: " + slettPersonFn + " " + slettPersonEn, "Slett person", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
                , null, alternativer, alternativer[0]);
        if (svar == JOptionPane.YES_OPTION) {
            String id = boligsøkere.finnBoligsøkerID(slettPersonFn, slettPersonEn);
            if (!id.equals("")) {
                Boligsøker slett = boligsøkere.getBoligsøker(id);
                if (boligsøkere.fjernSøker(slett)) {
                    kontrakter.fjernKontrakt(slett);
                    JOptionPane.showMessageDialog(null, "Personen er slettet");
                    slettPersonEn = "";
                    slettPersonFn = "";
                    lagTabellen();
                }
            } else {
                id = utleiere.finnID(slettPersonFn, slettPersonEn);
                Utleier woop = utleiere.getUtleier(id);
                if (!utleiere.harBoliger(woop)) {
                    utleiere.fjernUtleier(slettPersonFn, slettPersonEn);
                    JOptionPane.showMessageDialog(null, "Personen ble slettet");
                    lagTabellen();
                } else {
                    JOptionPane.showMessageDialog(null, "Personen har boliger registrert på seg!");
                }

            }
        }
    }

    public void slettBolig() {
        String[] alternativer = {"Ja", "Nei"};
        String slettDenne = Integer.toString(slettBoligId);
        if (slettDenne.equals("")) {
            JOptionPane.showMessageDialog(null, "Velg bolig");
            return;
        }
        Bolig slett = boliger.finnBolig(slettDenne);
        if (slett.getUtleid()) {
            JOptionPane.showMessageDialog(null, "Boligen er utleid, kan ikke slettes!");
            return;
        }
        int svar = JOptionPane.showOptionDialog(null, "Sikker på at du vil slette boligen med " + slett.getAdresse() + "?", "Slett bolig", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, alternativer, alternativer[0]);
        if (svar == JOptionPane.YES_OPTION) {
            if (boliger.slettBolig(slett)){

                JOptionPane.showMessageDialog(null, "Boligen ble slettet");
                lagBoligTabellen();
            }
            else
                JOptionPane.showMessageDialog(null, "DUUURT!");
        }

    }

    public void sendEmail() {

        Boligsøker send = boligsøkere.getBoligsøker(valgtId);
        if (send == null) {
            JOptionPane.showMessageDialog(null, "Velg en bolisøker");
            return;
        }
        String n = send.getFornavn() + " " + send.getEtternavn();
        //String epost = send.getEmail(); for å kunne sende eposten til boligsøkere

        Bolig sendTil = boliger.finnBolig(valgtBoligId);
        if (sendTil == null) {
            JOptionPane.showMessageDialog(null, "Velg en bolig");
            return;
        }
        JOptionPane.showMessageDialog(null, valgtBoligId);

        int stedInt = sendTil.getSted();
        String sted = "";
        switch (stedInt) {
            case 1:
                sted = "Oslo";
                break;
            case 2:
                sted = "Bergen";
                break;
            case 3:
                sted = "Stavanger";
                break;
            case 4:
                sted = "Trondheim";
                break;
            case 5:
                sted = "Kristiansand";
                break;
            case 6:
                sted = "Tromsø";
                break;

        }

        feedbackMail.setText("Hei " + send.getNavn() + ".\nVi har funnet en bolig som vi tror passer for deg: \nBolig: "
                + sendTil.getAdresse() + "\n" + sted + "\n" + sendTil.getUtleiepris() + "\nHvis denne passer for deg, send oss en tilbakemedlig\n Mvh. BoligFormidling.");

    }//end sendEmail

    public void sendEpost() {
        String til = JOptionPane.showInputDialog(null, "Skriv inn epostadresse");

        mailStatusLabel.setText("sender mail...");

        if(epost.sendMail(til, feedbackMail.getText()))
            mailStatusLabel.setText("Mail Sendt!");
        else
            mailStatusLabel.setText("Sending mislykket");

    }

    public void mekkKontrakt()
    {
        Boligsøker leietaker = boligsøkere.getBoligsøker(valgtLeietaker.getText());
        Bolig bolig = boliger.finnBolig(valgtBolig.getText());
        Utleier utleier = utleiere.getUtleier(valgtUtleier.getText());
        int sluttår, sluttmåned, sluttdag, startår, startmåned, startdag;

        try {
            sluttår = Integer.parseInt(sluttårFelt.getText());
            sluttmåned = Integer.parseInt(sluttMånedFelt.getText());
            sluttdag = Integer.parseInt(sluttDagFelt.getText());
            startår = Integer.parseInt(startÅrFelt.getText());
            startmåned = Integer.parseInt(startMånedFelt.getText());
            startdag = Integer.parseInt(startDagFelt.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Du må skrive inn ordentlige tall i datofeltene");
            return;
        }

        if (startdag <= 31 && startdag > 0 && startmåned <= 12 && startmåned > 0 && startår >= 2014 && startår <= 2020 &&
                sluttdag <= 31 && sluttdag > 0 && sluttmåned <= 12 && sluttmåned > 0 && sluttår >= 2014 && sluttår <= 2020) {
            startmåned = startmåned - 1;
            sluttmåned = sluttmåned - 1;
            Calendar start = new GregorianCalendar(startår, startmåned, startdag);
            Calendar slutt = new GregorianCalendar(sluttår, sluttmåned, sluttdag);
            start.setLenient(false);
            start.setLenient(false);
            try {
                Date dt = start.getTime();
                Date df = slutt.getTime();
                if (start.before(slutt)) {
                    String iden = idMekking.setIdPåKontrakt(utleier, leietaker, bolig);
                    Kontrakt ny = new Kontrakt(utleier, leietaker, bolig, start, slutt, iden);
                    if (kontrakter.leggTil(ny)) {
                        kontrakthistorie.skrivTilTekstFil(ny.toString());
                        JOptionPane.showMessageDialog(null, "Kontrakt lagret");
                        valgtLeietaker.setText(null); valgtBolig.setText(null); valgtUtleier.setText(null);
                        sluttårFelt.setText(null); sluttMånedFelt.setText(null); sluttDagFelt.setText(null); startÅrFelt.setText(null);
                        startMånedFelt.setText(null); startDagFelt.setText(null);
                        visKontrakter();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Disse datoene samsvarer ikke!");
                }
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, "En av datoene du skrev inn er ugyldig!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Du må skriver gyldige tall i datofeltene!");
        }

    }//end mekkontrakt


    //----------------------------------------------------
    //----------------END OF REG-METODER------------------
    //----------------------------------------------------


    //----------------------------------------------------
    //---------------START OF SØKEMETODER-----------------
    //----------------------------------------------------

    private void søkPersonregister(String v) {


        for (int rad = 0; rad <= personTabell.getRowCount() - 1; rad++) {
            for (int col = 0; col <= 1; col++) {
                String r = (personTabell.getValueAt(rad, col).toString());

                if (v.equalsIgnoreCase(r)) {
                    personTabell.scrollRectToVisible(personTabell.getCellRect(rad,0,true));
                    personTabell.setRowSelectionInterval(rad,rad);
                    for(int i=0; i<= personTabell.getColumnCount() -1 ;i++){
                        personTabell.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
                    }

                }
            }
        }
    }// end Personsøk

    private void søkBoligregister(String v){

        for(int rad = 0; rad <= boligTabellTabellen.getRowCount() -1; rad++){
            for(int col=0; col <= boligTabellTabellen.getColumnCount()-1;col++){

                String r = boligTabellTabellen.getValueAt(rad,col).toString();


                if(v.equalsIgnoreCase(r)){
                    boligTabellTabellen.scrollRectToVisible(personTabell.getCellRect(rad,0,true));
                    boligTabellTabellen.setRowSelectionInterval(rad,rad);
                    for(int i=0; i<= personTabell.getColumnCount() -1 ;i++){
                        boligTabellTabellen.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());

                    }

                }
            }

        }
    } // end Boligsøk

    //----------------------------------------------------
    //----------------END OF SØKEMETODER------------------
    //----------------------------------------------------


    //----------------------------------------------------
    //----------------START OF VIS-METODER----------------
    //----------------------------------------------------

    public void visKontraktHistorie() {
        visKontraktHistorikk = new JFrame("Kontrakt historikk");
        JTextArea ko = new JTextArea(30, 30);
        ko.setText(kontrakthistorie.lesFraTekstFil());
        ko.setEditable(false);
        JScrollPane scrolle = new JScrollPane(ko);
        visKontraktHistorikk.add(scrolle);
        visKontraktHistorikk.pack();
        visKontraktHistorikk.setVisible(true);
    }

    public void visVelgUtleierVindu() {
        velgUtleierVindu = new JFrame("Velg Eier");

        // utleierValgTabell = new JTable(utleiere.tilTabellMedId(),kolonnenavn);

        utleierTabellModell modell = new utleierTabellModell();
        utleierValgTabell = new SvartHvitRad(modell);


        utleierValgTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsm = utleierValgTabell.getSelectionModel();

        lsm.addListSelectionListener(new Utvalgslytter(modell));
        velgUtleierVindu.add(utleierValgTabell);
        velgUtleierVindu.pack();
        velgUtleierVindu.setVisible(true);
        velgUtleierVindu.setLocationRelativeTo(velgUtleier);


    }

    public void visVelgLeietakerVindu() {
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

    public void visVelgBoligVindu() {
        velgBoligVindu = new JFrame("Velg bolig");


        //sett utleierid, sånn at det matches på riktig boligsøker

        String leietakerId = valgtLeietaker.getText();
        if (leietakerId.equals("Ingen leietaker valgt")) {
            JOptionPane.showMessageDialog(null, "Ingen boligsøker valgt!");
            return;
        }
        valgtId = leietakerId;


        resultatTabellModell resultatModell = new resultatTabellModell();
        resultatTabell = new JTable(resultatModell);


        //sorter på matchkoefisient
        TableRowSorter<resultatTabellModell> sorterer = new TableRowSorter<>(resultatModell);
        List<TableRowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorterer.setSortKeys(sortKeys);
        resultatTabell.setRowSorter(sorterer);

        //valg
        resultatTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsm = resultatTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(resultatModell));
        resultatTabell.setBackground(tabellFarge);
        velgBoligVindu.add(new JScrollPane(resultatTabell));


        velgBoligVindu.pack();
        velgBoligVindu.setVisible(true);
        velgBoligVindu.setLocationRelativeTo(velgBoligKnapp);
    }// end visVelgBoligVindu

    public void visKontrakter(){
        kontraktTabellPanel.removeAll();

        kontraktTabellModell kmodell = new kontraktTabellModell();

        //kontraktHistorikkTabell = new JTable(kmodell);
        kontraktHistorikkTabell = new SvartHvitRad(kmodell);
        kontraktHistorikkTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsm = kontraktHistorikkTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(kmodell));

        TableColumn utleierkolonne = kontraktHistorikkTabell.getColumnModel().getColumn( 0);
        TableColumn leietakerkolonne = kontraktHistorikkTabell.getColumnModel().getColumn( 1);

        // utleierkolonne.setPreferredWidth(40);
        //leietakerkolonne.setPreferredWidth(40);
        // kontraktHistorikkTabell.setPreferredSize(new Dimension(150,100));


        JScrollPane kontraktScroll = new JScrollPane(kontraktHistorikkTabell);

        kontraktScroll.setPreferredSize(new Dimension(500,250));
        kontraktScroll.getViewport().setBackground(tabellFarge);
        // kontraktHistorikkTabell.setSize(new Dimension(200,100));



        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,0);

        kontraktTabellPanel.add(kontraktScroll, c);
        revalidate();

    }//end visKontrakter

    public void visMatch() {
        clearResultatPanel();
        resultatTabellModell resultatModell = new resultatTabellModell();
        // resultatTabell = new JTable(resultatModell);
        resultatTabell = new SvartHvitRad(resultatModell);

        TableRowSorter<resultatTabellModell> sorterer = new TableRowSorter<>(resultatModell);


        List<TableRowSorter.SortKey> sortKeys
                = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING
        ));

        resultatTabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel lsm = resultatTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(resultatModell));

        if (resultatModell.getValueAt(0, 0) != null) {
            sorterer.setSortKeys(sortKeys);
        }

        //resultatTabell.setRowSorter(sorterer);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 10;
        c.weighty = 40;
        c.insets = new Insets(0, 0, 0, 0);
        resultatTabell.setRowHeight(70);
        JScrollPane resultatscroll = new JScrollPane(resultatTabell);
        resultatscroll.getViewport().setBackground(tabellFarge);
        resultatPanel.add(resultatscroll, c);
        revalidate();
        repaint();


    }//end class visMatch

    public void visBoligsøkere() {
        clearVelgBsPanel();
        boligSøkerTabellModellForMatch boligSøkerTabellModellForMatch = new boligSøkerTabellModellForMatch();
        boligSøkereForMatch = new SvartHvitRad(boligSøkerTabellModellForMatch);
        boligSøkereForMatch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel lsm = boligSøkereForMatch.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(boligSøkerTabellModellForMatch));

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 10;
        c.weighty = 40;
        c.insets = new Insets(0, 0, 0, 0);
        JScrollPane boligsøkerscroll = new JScrollPane(boligSøkereForMatch);
        boligsøkerscroll.getViewport().setBackground(tabellFarge);
        velgBsPanel.add(boligsøkerscroll, c);
        revalidate();
        repaint();
    }//end class visBoligSøkere

    public void visPersonInfo(){
        try {
            String id = utleiere.finnID(slettPersonFn, slettPersonEn);
            if (id != null) {
                Utleier valgtUtleier = utleiere.getUtleier(id);
                feedbackFane3.setText(valgtUtleier.toString());
                clearBildePanel();

                ImageIcon image = sjekkPath("icon/bsperson.png");// new ImageIcon("icon/bsperson.png");
                Image img = image.getImage();
                Image skalert = img.getScaledInstance(350, 310, Image.SCALE_SMOOTH);
                image = new ImageIcon(skalert);
                JLabel label = new JLabel("", image, JLabel.CENTER);
                bildepanel.add(label, BorderLayout.CENTER);
            } else {
                String bs = boligsøkere.finnBoligsøkerID(slettPersonFn, slettPersonEn);
                Boligsøker valgtBoligsøker = boligsøkere.getBoligsøker(bs);
                feedbackFane3.setText(valgtBoligsøker.toString());
                clearBildePanel();
                ImageIcon image = sjekkPath("icon/bsperson.png");// new ImageIcon("icon/bsperson.png");
                Image img = image.getImage();
                Image skalert = img.getScaledInstance(350, 310, Image.SCALE_SMOOTH);
                image = new ImageIcon(skalert);
                JLabel label = new JLabel("", image, JLabel.CENTER);
                bildepanel.add(label, BorderLayout.CENTER);
            }

        } catch (NumberFormatException nfe) {
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
        Image skalert = img.getScaledInstance(350, 310, Image.SCALE_SMOOTH);
        image = new ImageIcon(skalert);


        JLabel label = new JLabel("", image, JLabel.CENTER);
        //prøv etterpå label.setBounds();
        bildepanel.add(label, BorderLayout.CENTER);

    }

    public void visKontraktFil() {
        visKontraktHistorie();
    }

    //----------------------------------------------------
    //----------------END OF VIS-METODER------------------
    //----------------------------------------------------

    //----------------------------------------------------
    //---------------START OF TABELLMETODER---------------
    //----------------------------------------------------

    private class utleierTabellModell extends AbstractTableModel {
        String[] kolonnenavn = {"Id", "Fornavn", "Etternavn", "Adresse", "Telefon", "eMail", "Firma"};
        String[][] celler = utleiere.tilTabellMedId();

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

        public boolean isCellEditable(int rad, int kolonne) {
            return kolonne == 2;
        }
    }

    private class boligsøkerTabellModell extends AbstractTableModel {
        String[] kolonnenavn = {"Id", "Fornavn", "Etternavn", "Adresse", "Telefon", "eMail", "lll"};
        String[][] celler = boligsøkere.tilMatchTabll();

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

    }

    private class resultatTabellModell extends AbstractTableModel {


        int[] kravene = boligsøkere.getKravPåId(valgtId);
        //int type = kravene[0];


        String[] kolonnenavn = getKolonneNavnForBoligtype(kravene);

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


        public Class getColumnClass(int k) {
            for(int i = 0;i<getRowCount();i++){
                Object value = getValueAt(i,k);
                if(value != null)
                    return value.getClass();
            }
            return Object.class;
        }
    }

    private class boligTabellFabrikk extends AbstractTableModel {


        String[] boligkolonnenavn = {"By", "Kvadrat", "Pris", "Adresse", "Rom", "Parkering", "Kjeller", "Bilde", "Id"};


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
        public Class getColumnClass( int k )
        {
            return getValueAt( 0, k ).getClass();
        }

    }

    private class boligSøkerTabellModellForMatch extends AbstractTableModel {
        String[] kolonnenavn = {"Id", "Fornavn", "Etternavn", "Adresse", "Email", "Telefon"};
        String[][] celler = boligsøkere.tilMatchTabll();

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


    }

    private class personTabellFabrikk extends AbstractTableModel {
        String[] kolonnenavn = {"Fornavn", "Etternavn", "Adrssse", "Mail", "Telefon", "Firma"};
        String[][] celler = joinPersonArray();

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

    }

    private class kontraktTabellModell extends AbstractTableModel
    {
        String[] kolonnenavn = {"Utleier", "Leietaker", "Startdato", "Sluttdato","ID"};

        Object[][] celler = kontrakter.tilTabell();

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
    }

    //----------------------------------------------------
    //----------------END OF TABELLMETODER----------------
    //----------------------------------------------------

    //----------------------------------------------------
    //---------------START OF LAGTABELLER-----------------
    //----------------------------------------------------

    public void lagTabellen()
    {

        personTabellFabrikk personTabellModell = new personTabellFabrikk();
        personTabell = new SvartHvitRad(personTabellModell);
        personTabell.setRowSelectionAllowed(true);
        JScrollPane personscroll = new JScrollPane(personTabell);
        personscroll.getViewport().setBackground(tabellFarge);
        ListSelectionModel lsm = personTabell.getSelectionModel();
        lsm.addListSelectionListener(new Utvalgslytter(personTabellModell));


    }

    public void lagBoligTabellen() {
        boligTabellFabrikk boligTabellModell = new boligTabellFabrikk();
        boligTabellTabellen = new SvartHvitRad(boligTabellModell);
        boligTabellTabellen.setBackground(bakFarge);
        JScrollPane boligscroll = new JScrollPane(boligTabellTabellen);
        boligscroll.getViewport().setBackground(tabellFarge);
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

    }

    private Object[][] joinBoligArray() {

        Object[][] første = boliger.eneboligerTilTabell();
        Object[][] andre = boliger.hyblerTilTabell();
        Object[][] tredje = boliger.leiligheterTilTabell();
        Object[][] fjerde = boliger.rekkehusTilTabell();
        String[][] dummy = {{"Tabellen", "er", "tom", "tom", "tom", "tom", "tom", "tom", "tom"}};

        Object[][] joina = new Object[første.length + andre.length + tredje.length + fjerde.length][9];
        int i = 0;
        while (i < første.length) {
            joina[i] = første[i];
            i++;
        }
        int j = 0;
        while (j < andre.length) {
            JOptionPane.showMessageDialog(null,Integer.toString(j));
            joina[i++] = andre[j];
            j++;
        }
        int k = 0;
        while (k < tredje.length) {
            joina[i++] = tredje[k];
            k++;
        }
        int l = 0;
        while (l < fjerde.length) {
            joina[i++] = fjerde[l];
            l++;
        }
        if (i == 0)
            joina = dummy;

        return joina;
    }

    public String[][] joinPersonArray() {
        String[][] første = boligsøkere.tilTabell();
        String[][] andre = utleiere.tilTabell();
        String[][] joina = new String[første.length + andre.length][6];
        String[][] dummy = {{"Tabellen", "er", "tom", "Tabellen", "er", "tom"}};


        int i = 0;
        while (i < første.length) {
            joina[i] = første[i];
            i++;
        }
        int j = 0;
        while (j < andre.length) {
            joina[i++] = andre[j];
            j++;
        }
        if (i == 0)
            joina = dummy;

        return joina;
    }

    //----------------------------------------------------
    //----------------END OF LAGTABLLER-------------------
    //----------------------------------------------------

    //----------------------------------------------------
    //---------------START OF CLEAR-METODER---------------
    //----------------------------------------------------

    private void clearResultatPanel() {
        resultatPanel.removeAll();
        revalidate();
        repaint();
    }

    private void clearVelgBsPanel() {
        velgBsPanel.removeAll();
        revalidate();
        repaint();

    }

    private void clearBSfelt() {
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
    }
    private void clearBoligfelt() {
        boligtypeBoxFane2.setSelectedIndex(0);
        byBoxFane2.setSelectedIndex(0);
        romBoxFane2.setSelectedIndex(0);
        boareal.setText("");
        tomtAreal.setText("");
        byggår.setText("");
        pris.setText("");
        utleierId.setText("");
        beskrivelse.setText("");
        etasjeBoxFane2.setSelectedIndex(0);
        planBoxFane2.setSelectedIndex(0);
        garasjeValgFane2.setSelected(false);
        kjellerValgFane2.setSelected(false);
        heisValgFane2.setSelected(false);
        balkongValgFane2.setSelected(false);
        kjøkkenValgFane2.setSelected(false);
        badValgFane2.setSelected(false);
        bildesti.setText("");

    }


    private void clearPersonFelt() {
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
    }
    private void clearKontraktfelt()
    {
        sluttårFelt.setText("");
        sluttMånedFelt.setText("");
        sluttDagFelt.setText("");
        startÅrFelt.setText("");
        startMånedFelt.setText("");
        startDagFelt.setText("");
    }

    private void clearPanel3() {
        tapanel.removeAll();
        revalidate();
        repaint();
    }

    private void clearBildePanel() {
        bildepanel.removeAll();
        revalidate();
    }

    //----------------------------------------------------
    //----------------END OF CLEAR-METODER----------------
    //----------------------------------------------------


    // ---------------------------------------------------
    //---------START OF CHANGELISTENER-METODER------------
    //----------------------------------------------------

    private class fanelytter implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            if (fane.getSelectedIndex() == 2) {
                if (persontabellRadioknapp.isSelected()) {
                    clearPanel3();
                    lagTabellen();
                    c.weightx = 100;
                    c.weighty = 100;
                    c.gridx = 0;
                    c.gridy = 0;
                    c.anchor = GridBagConstraints.FIRST_LINE_START;
                    c.fill = GridBagConstraints.BOTH;
                    /*personTabell.setBackground(tabellFarge);
                    tapanel.add(personTabellScroll = new JScrollPane(personTabell), c);
                    personTabellScroll.getViewport().setBackground(tabellFarge);
                    */
                    JScrollPane personscroll = new JScrollPane(personTabell);
                    personscroll.getViewport().setBackground(tabellFarge);
                    tapanel.add(personscroll, c);

                    slettPerson.setVisible(true);
                    slettBoligKnapp.setVisible(false);
                    revalidate();
                    repaint();
                    //gjøre no
                } else if (boligtabellRadioknapp.isSelected()) {
                    clearPanel3();
                    lagBoligTabellen();
                    c.anchor = GridBagConstraints.CENTER;
                    c.gridx = 0;
                    c.gridy = 0;
                    c.gridwidth = 1;
                    c.insets = new Insets(0, 0, 0, 0);
                    c.gridheight = 1;
                    c.gridwidth = 0;
                    c.weightx = 0;
                    c.weightx = 100;
                    c.weighty = 100;
                    c.gridx = 0;
                    c.gridy = 0;
                    c.anchor = GridBagConstraints.FIRST_LINE_START;
                    c.fill = GridBagConstraints.BOTH;


                    JScrollPane boligscroll = new JScrollPane(boligTabellTabellen);
                    tapanel.add(boligscroll, c);
                    boligscroll.getViewport().setBackground(tabellFarge);

                    slettBoligKnapp.setVisible(true);
                    slettPerson.setVisible(false);
                    revalidate();
                    repaint();
                    //vis bolig tabell
                } else {
                    lagBoligTabellen();
                    clearPanel3();
                    lagTabellen();
                    c.weightx = 100;
                    c.weighty = 100;
                    c.gridx = 0;
                    c.gridy = 0;
                    c.anchor = GridBagConstraints.FIRST_LINE_START;
                    c.fill = GridBagConstraints.BOTH;
                    personTabell.setBackground(tabellFarge);
                    tapanel.add(personTabellScroll = new JScrollPane(personTabell), c);
                    personTabellScroll.getViewport().setBackground(tabellFarge);
                    persontabellRadioknapp.setSelected(true);
                    slettPerson.setVisible(true);
                    slettBoligKnapp.setVisible(false);
                    revalidate();
                    repaint();
                }
            } else if (fane.getSelectedIndex() == 3) {
                visBoligsøkere();
            }

            repaint();
        }
    }//end class fanelytter

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

    //----------------------------------------------------
    //-----------END OF CHANGELISTENER-METODER------------
    //----------------------------------------------------


    //----------------------------------------------------
    //----------START OF ACTIONLISTENER-METODER-----------
    //----------------------------------------------------

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
            else if (e.getSource() == oppdaterKontrakter)
                visKontrakter();
            else if (e.getSource() == velgUtleierKnapp)
                visVelgUtleierVindu();
            else if (e.getSource() == velgLeietakerKnapp)
                visVelgLeietakerVindu();
            else if (e.getSource() == velgBoligKnapp)
                visVelgBoligVindu();
            else if (e.getSource() == lagreKontrakt)
                mekkKontrakt();
            else if (e.getSource() == finnMatch) {
                visMatch();
            } else if (e.getSource() == sendMail) {
                sendEpost();
            } else if (e.getSource() == slettPerson) {
                slettBoligsøker();
            } else if (e.getSource() == slettBoligKnapp) {
                slettBolig();
            }

        }
    }//end class knapplytter

    private class boligTypeLytter implements ActionListener { //Lytter som hører på BT-boksen og gjør som den sier!

        public void actionPerformed(ActionEvent e) {
            String typenTil = (String) boligtypeBox.getSelectedItem();
            String boligTypeFane2 = (String) boligtypeBoxFane2.getSelectedItem();


            if (romBox.getSelectedIndex() != 0) romBox.setForeground(Color.BLACK);
            if (byBox.getSelectedIndex() != 0) byBox.setForeground(Color.BLACK);
            if (romBox.getSelectedIndex() != 0) romBox.setForeground(Color.BLACK);
            if (etasjeBox.getSelectedIndex() != 0) etasjeBox.setForeground(Color.BLACK);
            if (planBox.getSelectedIndex() != 0) planBox.setForeground(Color.BLACK);


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
                    togler();
                    revalidate();
                    break;
            }
            switch (boligTypeFane2) {
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
                    toglerFane2();
                    revalidate();
                    break;

            }
        }
    }// end class boligTypeLytter

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

    private class tabellTypeLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (boligtabellRadioknapp.isSelected()) {
                clearPanel3();
                lagBoligTabellen();
                c.anchor = GridBagConstraints.CENTER;
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 1;
                c.insets = new Insets(0, 0, 0, 0);
                c.gridheight = 1;
                c.gridwidth = 0;
                c.weightx = 0;
                c.weightx=100;
                c.weighty=100;
                c.gridx=0;
                c.gridy=0;
                c.anchor = GridBagConstraints.FIRST_LINE_START;
                c.fill = GridBagConstraints.BOTH;
                JScrollPane boligscroll = new JScrollPane(boligTabellTabellen);
                tapanel.add(boligscroll, c);
                boligscroll.getViewport().setBackground(tabellFarge);
                slettBoligKnapp.setVisible(true);
                slettPerson.setVisible(false);
                revalidate();
                repaint();
            } else if (persontabellRadioknapp.isSelected()) {
                clearPanel3();
                lagTabellen();
                c.weightx=100;
                c.weighty=100;
                c.gridx=0;
                c.gridy=0;
                c.anchor = GridBagConstraints.FIRST_LINE_START;
                c.fill = GridBagConstraints.BOTH;
                JScrollPane personscroll = new JScrollPane(personTabell);
                personscroll.getViewport().setBackground(tabellFarge);
                tapanel.add(personscroll, c);
                slettPerson.setVisible(true);
                slettBoligKnapp.setVisible(false);
                revalidate();
                repaint();
            }
        }
    }// end class tabellTypeLytter

    private class menyLytter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == om) {
                JOptionPane.showMessageDialog(null, "BooleanFormidling - Versjon 1.0 \n® 2014\n av\n\nEmil Oppegård\nKristoffer Osen\nChrister Bang\n\n kontakt: booleanformidling@bang.is");
            } else if (e.getSource() == lagre) {
                skrivTilFil();
            } else if (e.getSource() == visHistorikk) {
                visKontraktFil();
            }
            else if(e.getSource() == printmatch){
                try {
                    resultatTabell.print();
                }
                catch(PrinterException pe){
                    JOptionPane.showMessageDialog(null,"Feil ved utskrift.");
                }
            }
            else if(e.getSource() == printkontrakter){
                try{
                    kontraktHistorikkTabell.print();
                }
                catch (PrinterException pe){
                    JOptionPane.showMessageDialog(null,"Feil ved utskrift.");
                }
            }
            else if(e.getSource()==printbolig) {
                try {
                    boligTabellTabellen.print();
                } catch (PrinterException pe) {
                    JOptionPane.showMessageDialog(null, "Feil ved utskrift.");
                }
            }
            else if(e.getSource()==printperson){
                try{
                    personTabell.print();
                }catch(PrinterException pe){
                    JOptionPane.showMessageDialog(null,"Feil ved utskrift.");
                }
            }
            else if(e.getSource()==statistikk){
                lagTabellen();
                lagBoligTabellen();
                JOptionPane.showMessageDialog(null,"Antall boliger i register:\t" + boligTabellTabellen.getRowCount() + "\nAntall registrerte personer:\t" + personTabell.getRowCount() );
            }
            else if(e.getSource()==avslutt){
                skrivTilFil();
                System.exit(0);

            }

        }
    }//end class menylytter
    //----------------------------------------------------
    //-----------END OF ACTIONLISTENER-METODER------------
    //----------------------------------------------------


    //----------------------------------------------------
    //------------START OF LISTENER-METODER---------------
    //----------------------------------------------------


    //----------------------------------------------------
    //-------------END OF LISTENER-METODER----------------
    //----------------------------------------------------

    private DocumentListener documentListener = new DocumentListener() {
        public void changedUpdate(DocumentEvent documentEvent) {
            søkPersonregister(søkefelt.getText());
            søkBoligregister(søkefelt.getText());
        }

        public void insertUpdate(DocumentEvent documentEvent) {
            søkPersonregister(søkefelt.getText());
            søkBoligregister(søkefelt.getText());
        }

        public void removeUpdate(DocumentEvent documentEvent) {
            søkPersonregister(søkefelt.getText());
            søkBoligregister(søkefelt.getText());
        }

    };

    private class Utvalgslytter implements ListSelectionListener {
        private TableModel tabellmodell;

        public Utvalgslytter(TableModel m) {
            tabellmodell = m;
        }

        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting())
                return;

            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (tabellmodell instanceof resultatTabellModell) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMinSelectionIndex();
                    valgtRad = resultatTabell.convertRowIndexToModel(valgtRad);
                    int id;
                    try{
                        id = (int) tabellmodell.getValueAt(valgtRad,9);
                    }catch(NullPointerException npe){
                        return;
                    }
                    String stringId = Integer.toString(id);

                    if (fane.getSelectedIndex() == 4) {
                        String uid = boliger.finnUtleier(boliger.finnBolig(stringId));
                        valgtUtleier.setText(uid);
                        valgtBolig.setText(stringId);
                        valgtUtleier.setVisible(true);
                        utleierLabel.setVisible(true);
                        velgBoligVindu.dispose();
                    }
                    else if(fane.getSelectedIndex() == 3)
                    {
                        valgtBoligId = stringId;
                        sendEmail();
                    }

                }
            } else if (tabellmodell instanceof utleierTabellModell) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMinSelectionIndex();
                    String id = (String) tabellmodell.getValueAt(valgtRad, 0);
                    utleierId.setText(id);

                    velgUtleierVindu.dispose();
                }
            } else if (tabellmodell instanceof boligsøkerTabellModell) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMinSelectionIndex();
                    id = (String) tabellmodell.getValueAt(valgtRad, 0);
                    valgtLeietaker.setText(id);

                    velgLeietakerVindu.dispose();
                    velgBoligKnapp.setVisible(true);
                    valgtBolig.setVisible(true);
                }
            } else if (tabellmodell instanceof boligSøkerTabellModellForMatch) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMaxSelectionIndex();
                    valgtId = (String) tabellmodell.getValueAt(valgtRad, 0);
                }

            } else if (tabellmodell instanceof personTabellFabrikk) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMaxSelectionIndex();
                    slettPersonFn = (String) tabellmodell.getValueAt(valgtRad, 0);
                    slettPersonEn = (String) tabellmodell.getValueAt(valgtRad, 1);
                    visPersonInfo();

                }
            } else if (tabellmodell instanceof boligTabellFabrikk) {
                if (!lsm.isSelectionEmpty()) {
                    int valgtRad = lsm.getMaxSelectionIndex();
                    slettBoligId = (int) tabellmodell.getValueAt(valgtRad, 8);
                    visBoligInfo();
                }
            }

            else if(tabellmodell instanceof kontraktTabellModell){
                if(!lsm.isSelectionEmpty()){
                    int valgtRad = lsm.getMaxSelectionIndex();
                    String kid = (String)tabellmodell.getValueAt(valgtRad,4);
                    Kontrakt k = kontrakter.finnKontrakt(kid);
                    skrivKontraktInfo(k);
                }
            }
        }
    }//end class Utvalgslytter

    private class HighlightRenderer extends DefaultTableCellRenderer{

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(row==table.getSelectedRow()){
                setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
            }
            return this;
        }
    }

    //----------------------------------------------------
    //-------------START OF TOGGLE-METODER----------------
    //----------------------------------------------------

    private void togler()
    {
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

    private void toglerFane2() {
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

    //----------------------------------------------------
    //--------------END OF TOGGLE-METODER-----------------
    //----------------------------------------------------

    //----------------------------------------------------
    //-------------START OF GYLDIG-METODER----------------
    //----------------------------------------------------

    private boolean gyldig(JTextField f) {
        if (f.getText().isEmpty() || f.getDocument().getLength() < 2 || f.getText().equals("")) {
            f.setBackground(Color.RED);
            return true;
        } else {
            f.setBackground(Color.WHITE);
            return false;
        }
    }

    private boolean gyldigBox(JComboBox f) {
        if (f.getSelectedIndex() == 0 && f.isVisible()) {
            f.setForeground(Color.RED);
            return true;
        } else
            f.setForeground(Color.BLACK);
        return false;

    }

    //----------------------------------------------------
    //--------------END OF GYLDIG-METODER-----------------
    //----------------------------------------------------


    //----------------------------------------------------
    //---------------START OF TABELL-METODER----------------
    //----------------------------------------------------

    public String[] getKolonneNavnForBoligtype(int[] krav) {
        String[] kolonnenavn1 = {"Matchresultat", "By", "Areal", "Pris/m", "Adresse", "Antall rom", "Parkering", "Kjeller", "Bilde", "id"};
        String[] kolonnenavn2 = {"Matchresultat", "By", "Areal", "Pris/m", "Adresse", "Antall rom", "Balkong", "Heis", "Bilde", "id"};
        String[] kolonnenavn3 = {"Matchresultat", "By", "Areal", "Pris/m", "Adresse", "Antall rom", "Deler bad med", "Deler kjøkken med", "Bilde", "id"};

        if (krav != null) {
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

    public class SvartHvitRad extends JTable{
        private boolean utskrift = false;


        public SvartHvitRad(TableModel modell)
        {
            super(modell);
        }
        public void print(Graphics g)
        {
            utskrift = true;
            try {
                super.print(g);
            }
            finally {
                utskrift = false;
            }
        }



        public Component prepareRenderer(
                TableCellRenderer rendrer, int rad, int kolonne)
        {
            Component c = super.prepareRenderer(rendrer, rad, kolonne);

            if(utskrift)
                c.setBackground(getBackground());
            else if (rad % 2 == 0 && !isCellSelected(rad, kolonne))
            {
                c.setBackground(Color.LIGHT_GRAY);
            }
            else if (isCellSelected(rad, kolonne))
            {
                c.setBackground(getSelectionBackground());
            }
            else
            {
                c.setBackground(getBackground());
            }
            return c;
        }
    }//end of class SvartHvitRad

    public void skrivKontraktInfo(Kontrakt k)
    {
        if(k == null) {
            return;
        }
        kontraktInfoFelt.setText(k.toString());
    }

    private ImageIcon sjekkPath(String s)
    {
        ImageIcon bilde;
        java.net.URL kilde = getClass().getResource(s);

        if (kilde != null)
            bilde = new ImageIcon(kilde);
        else {
            return null;
        }
        return bilde;


    }

    //----------------------------------------------------
    //----------------END OF TABELL-METODER-----------------
    //----------------------------------------------------


    //----------------------------------------------------
    //---------START OF LES TIL OG FRA - METODER----------
    //----------------------------------------------------

    public void lesFraFil() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("fildata.data"))) {

            utleiere = (UtleierListe) in.readObject();
            boligsøkere = (BoligsøkerListe) in.readObject();
            kontrakter = (KontraktListe) in.readObject();
            boliger = (Boligliste) in.readObject();
            idMekking = (idGenerator) in.readObject();
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Kunne ikke finne programmets klasse");
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Fant ikke fildata.data");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Feil med lesing fra fil");
        }
    }

    public void skrivTilFil() {
        try (ObjectOutputStream ut = new ObjectOutputStream(new FileOutputStream("fildata.data"))) {
            ut.writeObject(utleiere);
            ut.writeObject(boligsøkere);
            ut.writeObject(kontrakter);
            ut.writeObject(boliger);
            ut.writeObject(idMekking);
        } catch (NotSerializableException nse) {
            JOptionPane.showMessageDialog(null, "En av programmets klasser er ikke serialisert");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Feil med skriving til fil");
        }
    }

    //----------------------------------------------------
    //-----------END OF LES TIL OG FRA - METODER----------
    //----------------------------------------------------
}// END OF CLASS GUI
