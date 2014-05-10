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
    private JLabel minPris, maxPris, firmaLabel,tomtArealLabel, antEgtLabel,boligsøkerOverskrift,antEgtLabelFane2, utleierLabel, kontraktHeader,regpersonHeader,navnLabel;
    private JTextArea beskrivelse,feedbackFane1,feedbackFane3;
    private JMenuBar menybar = new JMenuBar();
    private JRadioButton utleier, boligsøker,persontabellRadioknapp,boligtabellRadioknapp;
    private JPanel panel1, bspanel, utpanel, panel2, bopanel, panel3, panel4, pepanel,tapanel,panel5,resultatPanel,velgBsPanel;
    private JComboBox boligtypeBox, byBox, romBox, etasjeBox, planBox, boligtypeBoxFane2, byBoxFane2, romBoxFane2, etasjeBoxFane2, planBoxFane2;
    private JCheckBox kjellerValg, heisValg, garasjeValg, badValg, kjøkkenValg, balkongValg, kjellerValgFane2, heisValgFane2, garasjeValgFane2, badValgFane2, kjøkkenValgFane2, balkongValgFane2;
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


    //private JScrollPane personTabellScroll,boligTabellScroll, kontraktHistorikkTabellScroll;
    //private JTextArea utskriftsområde;
    private fanelytter faneøre;
    //private Utvalgslytter lsm;


    public Gui() {
        super("Boligformidling for svaksynte");

        headerFont = new Font("Helvetica",Font.BOLD,30);

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
        kontrakter = new KontraktListe(boliger, boligsøkere);
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

        panel1 = new JPanel(new GridLayout(2, 2));  // FANE panel
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


        panel1.add(pepanel, BorderLayout.LINE_START);

        panel1.add(bspanel);
        panel1.add(utpanel);


        panel2.add(bopanel);



        c.gridy=0;
        c.gridx=0;
        c.gridheight=10;
        c.gridwidth=10;
        c.weightx=0.1;
        tapanel.setBackground(Color.RED);
        panel3.add(tapanel,c);





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


        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Boligtype: "), c);

        boligtypeBox = new JComboBox(boligtypeValg);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(boligtypeBox, c);
        boligtypeBox.addActionListener(new boligTypeLytter());


        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("By: "), c);

        byBox = new JComboBox(byvalg);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        byBox.addActionListener(new boligTypeLytter());
        bspanel.add(byBox, c);


        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Rom: "), c);

        romBox = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        romBox.addActionListener(new boligTypeLytter());
        bspanel.add(romBox, c);


        minPris = new JLabel("Min Pris: 0");
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
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(maxPris, c);


        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 100;
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

        antEgtLabel = new JLabel("Ant Etasjer: ");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        antEgtLabel.setVisible(false);
        bspanel.add(antEgtLabel, c);


        etasjeBox = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        etasjeBox.setVisible(false);
        bspanel.add(etasjeBox, c);

        garasjeValg = new JCheckBox("Garasje");
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        garasjeValg.setVisible(false);
        bspanel.add(garasjeValg, c);


        kjellerValg = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        kjellerValg.setVisible(false);
        bspanel.add(kjellerValg, c);

        heisValg = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        heisValg.setVisible(false);
        bspanel.add(heisValg, c);

        badValg = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        badValg.setVisible(false);
        bspanel.add(badValg, c);

        kjøkkenValg = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        kjøkkenValg.setVisible(false);
        bspanel.add(kjøkkenValg, c);

        balkongValg = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        balkongValg.setVisible(false);
        bspanel.add(balkongValg, c);


        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Plan: "), c);

        regPersonKnapp = new JButton("Registrer");
        regPersonKnapp.addActionListener(lytter);
        c.gridx = 2;
        c.gridy = 15;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 5, 5, 5);
        bspanel.add(regPersonKnapp, c);

        planBox = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        planBox.addActionListener(new boligTypeLytter());
        bspanel.add(planBox, c);

        feedbackFane1 = new JTextArea("feedback");
        feedbackFane1.setEditable(false);
        feedbackFane1.setBackground(Color.LIGHT_GRAY);
        feedbackFane1.setPreferredSize(new Dimension(20,20));


        panel1.add(feedbackFane1);


        // FANE NR 2, REGISTRER NY BOLIg *****************************************************************************

        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);


        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Adresse: "), c);

        adresseFane2 = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        bopanel.add(adresseFane2, c);


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
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Boligtype: "), c);

        boligtypeBoxFane2 = new JComboBox(boligtypeValg);
        c.gridx = 1;
        c.gridy = 2;


        boligtypeBoxFane2.addActionListener(new boligTypeLytter());
        bopanel.add(boligtypeBoxFane2, c);


        c.gridx = 0;
        c.gridy = 3;
        bopanel.add(new JLabel("Boareal"), c);

        boareal = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        bopanel.add(boareal, c);

        tomtArealLabel = new JLabel("TomtAreal:");
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        tomtArealLabel.setVisible(false);
        bopanel.add(tomtArealLabel, c);

        tomtAreal = new JTextField();
        c.gridx = 1;
        c.gridy = 4;
        tomtAreal.setVisible(false);
        bopanel.add(tomtAreal, c);


        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Utleiepris:"), c);

        pris = new JTextField();
        c.gridx = 1;
        c.gridy = 5;
        bopanel.add(pris, c);


        c.gridx = 0;
        c.gridy = 6;
        bopanel.add(new JLabel("Byggår"), c);

        byggår = new JTextField();
        c.gridx = 1;
        c.gridy = 6;
        bopanel.add(byggår, c);



        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Rom: "), c);

        romBoxFane2 = new JComboBox(romValg);
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(romBoxFane2, c);

        antEgtLabelFane2 = new JLabel("Ant Etasjer: ");
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        antEgtLabelFane2.setVisible(false);
        bopanel.add(antEgtLabelFane2, c);


        etasjeBoxFane2 = new JComboBox(etasjeValg);
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        etasjeBoxFane2.setVisible(false);
        bopanel.add(etasjeBoxFane2, c);

        c.gridx = 0;
        c.gridy = 9;
        bopanel.add(new JLabel("Hilket plan?"), c);

        planBoxFane2 = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(planBoxFane2, c);




        garasjeValgFane2 = new JCheckBox("Garasje");
        c.gridx = 0;
        c.gridy = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        garasjeValgFane2.setVisible(false);
        bopanel.add(garasjeValgFane2, c);


        kjellerValgFane2 = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        kjellerValgFane2.setVisible(false);
        bopanel.add(kjellerValgFane2, c);

        heisValgFane2 = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 12;
        c.fill = GridBagConstraints.HORIZONTAL;
        heisValgFane2.setVisible(false);
        bopanel.add(heisValgFane2, c);

        badValgFane2 = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 11;
        c.fill = GridBagConstraints.HORIZONTAL;
        badValgFane2.setVisible(false);
        bopanel.add(badValgFane2, c);

        kjøkkenValgFane2 = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.HORIZONTAL;
        kjøkkenValgFane2.setVisible(false);
        bopanel.add(kjøkkenValgFane2, c);

        balkongValgFane2 = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 12;
        c.fill = GridBagConstraints.HORIZONTAL;
        balkongValgFane2.setVisible(false);
        bopanel.add(balkongValgFane2, c);


        c.gridx = 0;
        c.gridy = 13;
        bopanel.add(new JLabel("Beskrivelse:"), c);

        beskrivelse = new JTextArea("Skriv da..", 5, 10);
        scroll = new JScrollPane(beskrivelse);
        c.gridx = 1;
        c.gridy = 13;
        c.gridheight =2;
        c.fill = GridBagConstraints.NONE;
        bopanel.add(scroll, c);

        c.gridx = 0;
        c.gridy = 15;
        c.gridheight = 1;
        bopanel.add(new JLabel("Velg bilde:"),c);

        bildesti = new JTextField();
        c.gridx = 1;
        c.gridy = 15;
        bildesti.setEditable(false);
        c.fill = GridBagConstraints.NONE;
        bopanel.add(bildesti, c);

        finnBildeKnapp = new JButton("Finn bilde");
        finnBildeKnapp.addActionListener(lytter);
        c.gridx = 2;
        c.gridy = 15;
        bopanel.add(finnBildeKnapp, c);

        velgUtleier = new JButton("Velg utleier");
        velgUtleier.addActionListener(lytter);
        c.gridx = 2;
        c.gridy = 16;
        bopanel.add(velgUtleier,c);


        c.gridx = 0;
        c.gridy = 16;
        bopanel.add(new JLabel("Utleier ID:"), c);

        utleierId = new JTextField();
        utleierId.setEditable(false);
        c.gridx = 1;
        c.gridy = 16;
        bopanel.add(utleierId, c);



        regBoligKnapp = new JButton("Registrer");
        regBoligKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 17;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 5, 5, 5);
        bopanel.add(regBoligKnapp, c);


        // FANE 3 - VIS TABELL   *********************************************************
        //RESETER
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;


        radioTabellLytter = new tabellTypeLytter();

        radioTabell = new ButtonGroup();
        persontabellRadioknapp = new JRadioButton("Vis personer",false);
        persontabellRadioknapp.addActionListener(radioTabellLytter);
        c.gridx = 10;
        c.gridy = 0;
        panel3.add(persontabellRadioknapp,c);


        boligtabellRadioknapp = new JRadioButton("Vis boliger:",false);
        boligtabellRadioknapp.addActionListener(radioTabellLytter);
        c.gridx = 10;
        c.gridy = 1;
        panel3.add(boligtabellRadioknapp,c);


        radioTabell.add(persontabellRadioknapp);
        radioTabell.add(boligtabellRadioknapp);
        slettPerson = new JButton("Slett person");
        slettPerson.addActionListener(lytter);
        c.gridx = 10;
        c.gridy = 2;
        panel3.add(slettPerson,c);

        slettBoligKnapp = new JButton("Slett bolig");
        slettBoligKnapp.addActionListener(lytter);
        c.gridx = 10;
        c.gridy = 3;
        panel3.add(slettBoligKnapp,c);


        feedbackFane3 = new JTextArea("feedbackfelt");
        //feedbackFane3.setPreferredSize(new Dimension(800,100));
        feedbackFane3.setBackground(Color.BLUE);
       // c.gridwidth=30;
        c.gridy=60;
        c.gridx=15;
        c.anchor=GridBagConstraints.PAGE_END;
        //c.ipadx=30;
        //c.gridy=30;
        c.weighty=1;
        //c.fill=GridBagConstraints
        panel3.add(feedbackFane3,c);




        //SLUTT FANE 3

        //FANE 4 - MATCHMAKING ************************************************************************************************************************************************************************
        //RESETER
        c.gridx = 0;
        c.gridy = 0;
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



        tapanel.setBackground(Color.BLUE);


        //Legger fanecontainer på vinduet med scroll, str er 80% todo: Christer endre den str!

        //Dimension heleSkjermen = new Dimension(bredde-20, høyde-20);
        fane.setPreferredSize(new Dimension(1380,1010));
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
                    revalidate();
                    break;
                case "Rekkehus":
                    togler();
                    garasjeValg.setVisible(true);
                    kjellerValg.setVisible(true);
                    etasjeBox.setVisible(true);
                    antEgtLabel.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
                    revalidate();
                    break;
                case "Leilighet":
                    togler();
                    heisValg.setVisible(true);
                    balkongValg.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
                    revalidate();
                    break;
                case "Hybel":
                    togler();
                    badValg.setVisible(true);
                    kjøkkenValg.setVisible(true);
                    boligtypeBox.setForeground(Color.BLACK);
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
                    revalidate();
                    break;
                case "Leilighet":
                    toglerFane2();
                    heisValgFane2.setVisible(true);
                    balkongValgFane2.setVisible(true);
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
                revalidate();


            } else if (boligsøker.isSelected()) {
                bspanel.setVisible(true);
                firma.setVisible(false);
                firmaLabel.setVisible(false);
                regUtleierKnapp.setVisible(false);
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
                tapanel.add(boligTabellScroll = new JScrollPane(boligTabellTabellen));
                revalidate();
                repaint();
            }
            else if(persontabellRadioknapp.isSelected()){
                clearPanel3();
                lagTabellen();
                tapanel.add(personTabellScroll = new JScrollPane(personTabell));
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
                }
            }
            else if(tabellmodell instanceof boligTabellFabrikk){
                if(!lsm.isSelectionEmpty()){
                    int valgtRad = lsm.getMaxSelectionIndex();
                    slettBoligId = (int)tabellmodell.getValueAt(valgtRad, 8);
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

        resultatTabell.
                setRowSorter(sorterer);
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
        public boolean isCellEditable(int rad, int kolonne)
        {
            return kolonne == 2;
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
        JOptionPane.showMessageDialog(null,"inni joinboligArray");
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
                return;
            }


            JOptionPane.showMessageDialog(null,"Om du ser denne har du ikke skrevet inn i alle feltene i boligsøker regging");

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
                feedbackFane1.setText("Ny utleier er registrert.  \n\n"+ny.toString()+ "\nUtleier ID: " + ny.getId());
                return;
            }
            JOptionPane.showMessageDialog(null,"om du ser denne har du ikke skrevet gyldige data i alle feltene i utleier!");
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

        try
        {
            areal = Integer.parseInt(arealString);
            år = Integer.parseInt(årString);
            upris = Integer.parseInt(utPrisString);
            tomtareal = Integer.parseInt(tAreal);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Du må skrive inn skikkelige verdier!");
            return;
        }



    // Comboboxer
        String valg = (String)boligtypeBoxFane2.getSelectedItem();
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
            case "Velg boligtype..": btype = 0;
                                break;
            default:            btype = 0;
                                break;
        }

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
            case 1: Enebolig nyEnebolig = new Enebolig(adr,byvalg, areal, rom, år, upris, utId, sti, antetasjer, garasje, kjeller, tomtareal);
                         boliger.leggTil(nyEnebolig);
                         break;
            case 2: Rekkehus nyttRekkehus = new Rekkehus(adr,byvalg, areal, rom, år, upris, utId, sti, antetasjer,garasje, kjeller, tomtareal);
                         boliger.leggTil(nyttRekkehus);
                         break;
            case 3: Leilighet nyLeilighet = new Leilighet(adr,byvalg, areal, rom, år, upris, utId, sti, plan, balkong, heis);
                         boliger.leggTil(nyLeilighet);
                         break;
            case 4: Hybel nyHybel = new Hybel(adr,byvalg, areal, rom, år, upris, utId, sti, badInt, kjøkkenInt);
                         boliger.leggTil(nyHybel);
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
