import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.rmi.server.UID;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.awt.event.KeyEvent;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.lang.*;


/**
 * Created by mac on 02.04.14.
 */

public class Gui extends JFrame {
    private JTabbedPane fane = new JTabbedPane();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();
    private JButton regBoligKnapp, regPersonKnapp, regUtleierKnapp, finnBildeKnapp, oppdaterKontrakter;
    private JTextField fornavn, etternavn, adresse, adresseFane2, mail, firma, tlf, boareal, pris, byggår, tomtAreal, utleierId, bildesti;
    private JLabel minPris, maxPris, firmaLabel;
    private JTextArea beskrivelse;
    private JMenuBar menybar = new JMenuBar();
    private JRadioButton utleier, boligsøker,persontabell,boligtabell;
    private JPanel panel1, bspanel, utpanel, panel2, bopanel, panel3, panel4, pepanel,tapanel,panel5;
    //private JRadioButton utleier, boligsøker;
   // private JPanel panel1, bspanel, utpanel, panel2, bopanel, panel3, panel4, pepanel, panel5;
    private JComboBox boligtypeBox, byBox, romBox, etasjeBox, planBox, boligtypeBoxFane2, byBoxFane2, romBoxFane2, etasjeBoxFane2, planBoxFane2;
    private JCheckBox kjellerValg, heisValg, garasjeValg, badValg, kjøkkenValg, balkongValg, kjellerValgFane2, heisValgFane2, garasjeValgFane2, badValgFane2, kjøkkenValgFane2, balkongValgFane2;
    private JSlider minPrisSlider, maxPrisSlider;
    private String[] boligtypeValg = {"Velg boligtype..", "Enebolig", "Hybel", "Leilighet", "Rekkehus"};
    private String[] byvalg = {"Velg by..", "Oslo", "Bergen", "Stavanger", "Trondheim", "Kristiansand", "Tromsø"};
    private String[] romValg = {"Velg ant. rom..", "1", "2", "3", "4", "5", "6"};
    private String[] etasjeValg = {"Velg ant. etg..", "1", "2", "3"};
    private String[] planValg = {"Velg ant. plan", "1", "2", "3", "4", "5", "6", "7"};
    private String[] kontraktTabellKolonneNavn = {"Eier,","Leietaker","Startdato","Sluttdato"};
    private JTable personTabell, boligTabellTabellen, kontraktHistorikkTabell;
    private JScrollPane scroll, mainScroll;
    private PersonTypeLytter radioLytter;
    private tabellTypeLytter radioTabellLytter;
    private ButtonGroup radioPerson, radioTabell;
    private Border ramme = BorderFactory.createLineBorder(Color.BLACK);
    private UtleierListe utleiere;
    private BoligsøkerListe boligsøkere;
    private KontraktListe kontrakter;
    private knappLytter lytter;
    private menyLytter øre;
    private Boligliste boliger;
    private JMenuBar menylinje;
    private JMenu filmeny, rediger;
    private JMenuItem om, lagre, angre,tabell;
    private JScrollPane personTabellScroll;
    private JScrollPane boligTabellScroll;


    //private JScrollPane personTabellScroll,boligTabellScroll, kontraktHistorikkTabellScroll;
    //private JTextArea utskriftsområde;
    private fanelytter faneøre;
    //private Utvalgslytter lsm;


    public Gui() {
        super("Boligformidling for svaksynte");

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


        rediger.add(angre);
        filmeny.add(om);
        filmeny.add(lagre);
        rediger.add(tabell);


        menylinje = new JMenuBar();
        setJMenuBar(menylinje);
        menylinje.add(filmeny);
        menylinje.add(rediger);


//temp, filmeny slutt

        utleiere = new UtleierListe();
        boligsøkere = new BoligsøkerListe();
        boliger = new Boligliste();
        kontrakter = new KontraktListe();
        lytter = new knappLytter();
        faneøre = new fanelytter();
        personTabellScroll= new JScrollPane(personTabell);
        boligTabellScroll=new JScrollPane(boligTabellTabellen);



        //alt dette bare for skjermstørrelsen hehehe

        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        int bredde = (int) (Math.round(skjerm.width * 0.80));
        int høyde = (int) (Math.round(skjerm.height * 0.80));
        setSize(bredde, høyde);
        setLocationByPlatform(true);
        //todo Finne en fin skjermstørrelse, er nå 80% av skjermstr.
        System.out.println("Skjermstr: " + bredde + "x" + høyde);


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
        tapanel = new JPanel(new GridLayout(2,2));//tabellpanel

        panel5 = new JPanel(layout);
        //pepanel.setVisible(true);

      /*  panel1.setVisible(true);
            c.gridx = 8;
            c.gridy = 0;
            c.gridwidth = 2;
            c.gridheight = 9;
            c.insets = new Insets(5,5,5,5);
            c.anchor = GridBagConstraints.FIRST_LINE_START;
        panel1.add(bspanel, c);
        panel1.add(pepanel);
            c.gridheight = 1;

        panel1.add(utpanel, c);
        panel2.add(bopanel, c);

        */


        panel1.add(pepanel, BorderLayout.LINE_START);

        panel1.add(bspanel);
        panel1.add(utpanel);


        panel2.add(bopanel);
        panel3.add(tapanel);


//todo-Christer: sett min/maxpris label til å initie så den har verdiiii


        //til hit
        bopanel.setVisible(true);
        bspanel.setVisible(false); // endre tilbake til, false
        utpanel.setVisible(false);
        pepanel.setVisible(true);
        tapanel.setVisible(true);

        //oppretter Fanene

        fane.addTab("Registrer Person", null, panel1, "Registrere ny boligsøker eller utleier");
        fane.addTab("Registrer bolig", null, panel2, "Registrere ny bolig");
        fane.addTab("Vis tabell", null, panel3, "Show tabell");
        fane.addTab("MatchMaking", null, panel4, "Tinde");
        fane.addTab("Kontrakter", null, panel5, "Registrer og se kontrakter");
        fane.setMnemonicAt(0, KeyEvent.VK_1);
        fane.setMnemonicAt(1, KeyEvent.VK_2);
        fane.setMnemonicAt(2, KeyEvent.VK_3);
        fane.setMnemonicAt(3, KeyEvent.VK_4);
        fane.setMnemonicAt(4, KeyEvent.VK_5);

        fane.addChangeListener(faneøre);

        //todo- OSEN: Funker dette på windows?


        // Reseter
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridheight = 1;


        //Inndatafelt
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        pepanel.add(new JLabel("Fornavn: "), c);

        fornavn = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
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

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        bspanel.add(new JLabel("Ant Etasjer: "), c);


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
        c.gridx = 3;
        c.gridy = 15;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 5, 5, 5);
        bspanel.add(regPersonKnapp, c);

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
        bopanel.add(new JLabel("Boareal"), c);

        boareal = new JTextField();
        c.gridx = 1;
        c.gridy = 2;
        bopanel.add(boareal, c);

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
        bopanel.add(garasjeValgFane2, c);


        kjellerValgFane2 = new JCheckBox("Kjeller");
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(kjellerValgFane2, c);

        heisValgFane2 = new JCheckBox("Heis");
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(heisValgFane2, c);

        badValgFane2 = new JCheckBox("Eget Bad");
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(badValgFane2, c);

        kjøkkenValgFane2 = new JCheckBox("Eget kjøkken");
        c.gridx = 1;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(kjøkkenValgFane2, c);

        balkongValgFane2 = new JCheckBox("Balkong");
        c.gridx = 1;
        c.gridy = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(balkongValgFane2, c);

        c.gridx = 0;
        c.gridy = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("Utleiepris:"), c);

        pris = new JTextField();
        c.gridx = 1;
        c.gridy = 9;
        bopanel.add(pris, c);


        c.gridx = 0;
        c.gridy = 10;
        bopanel.add(new JLabel("Byggår"), c);

        byggår = new JTextField();
        c.gridx = 1;
        c.gridy = 10;
        bopanel.add(byggår, c);


        c.gridx = 0;
        c.gridy = 11;
        bopanel.add(new JLabel("Beskrivelse:"), c);

        beskrivelse = new JTextArea("Skriv da..", 5, 10);
        scroll = new JScrollPane(beskrivelse);
        c.gridx = 1;
        c.gridy = 11;
        c.fill = GridBagConstraints.NONE;
        bopanel.add(scroll, c);


        c.gridx = 0;
        c.gridy = 12;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(new JLabel("TomtArael:"), c);

        tomtAreal = new JTextField();
        c.gridx = 1;
        c.gridy = 12;
        bopanel.add(tomtAreal, c);

        c.gridx = 0;
        c.gridy = 13;
        bopanel.add(new JLabel("Hilket plan?"), c);


        planBoxFane2 = new JComboBox(planValg);
        c.gridx = 1;
        c.gridy = 13;
        c.fill = GridBagConstraints.HORIZONTAL;
        bopanel.add(planBoxFane2, c);


        c.gridx = 0;
        c.gridy = 14;
        bopanel.add(new JLabel("Utleier ID:"), c);

        utleierId = new JTextField();
        c.gridx = 1;
        c.gridy = 14;
        bopanel.add(utleierId, c);

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

        regBoligKnapp = new JButton("Registrer");
        regBoligKnapp.addActionListener(lytter);
        c.gridx = 3;
        c.gridy = 16;
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
        persontabell = new JRadioButton("Vis personer",false);

        persontabell.addActionListener(radioTabellLytter);

        c.gridx = 0;
        c.gridy = 0;
        panel3.add(persontabell,c);

        boligtabell = new JRadioButton("Vis boliger:",false);
        boligtabell.addActionListener(radioTabellLytter);
        c.gridx = 0;
        c.gridy = 1;
        panel3.add(boligtabell,c);

        radioTabell.add(persontabell);
        radioTabell.add(boligtabell);





        //SLUTT FANE 3


        // fane 5?
        oppdaterKontrakter = new JButton("Oppdater Register");
        panel5.add(oppdaterKontrakter);






        //Legger fanecontainer på vinduet med scroll, str er 80% todo: Christer endre den str!
        fane.setPreferredSize(new Dimension(getSize()));
        add(new JScrollPane(fane), BorderLayout.PAGE_START);


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
        }
    }


    private class menyLytter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == om) {
                JOptionPane.showMessageDialog(null, boligsøkere.toString());
            } else if (e.getSource() == lagre) {

                System.out.println("Trykka på lagre");


            } else if (e.getSource() == angre) {
                System.out.println("du anger på at du tryka på angre");

            }
            else if(e.getSource() ==tabell){
                System.out.println("Hente inn tabell på nytt");
                panel3.remove(personTabellScroll);
                panel3.remove(boligTabellScroll);
                lagTabellen();
                panel3.add(personTabellScroll = new JScrollPane(personTabell),BorderLayout.LINE_END);
                panel3.add(boligTabellScroll = new JScrollPane(boligTabellTabellen),BorderLayout.BEFORE_LINE_BEGINS);
            }


        }
    }

    //tabellSplittPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,personTabellScroll,boligTabellScroll);


    private class fanelytter implements ChangeListener{
        public void stateChanged(ChangeEvent e) {
           if(fane.getSelectedIndex()==2) {
                System.out.println("Trykka på fane; vis tabell!");
/*
                panel3.remove(personTabellScroll);
                panel3.remove(boligTabellScroll);
                lagTabellen();
                lagBoligTabellen();
                panel3.add(personTabellScroll = new JScrollPane(personTabell));
                panel3.add(boligTabellScroll = new JScrollPane(boligTabell));
*/



            }
            System.out.println("byttet fane, derfor kjører jeg en repaint(), trengs egentlig ikke men..");
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
        revalidate();
    }


    private class boligTypeLytter implements ActionListener { //Lytter som hører på BT-boksen og gjør som den sier!

        public void actionPerformed(ActionEvent e) {
            String typenTil = (String) boligtypeBox.getSelectedItem();
            switch (typenTil) {
                case "Enebolig":
                    togler();
                    garasjeValg.setVisible(true);
                    kjellerValg.setVisible(true);
                    etasjeBox.setVisible(true);
                    revalidate();
                    break;
                case "Rekkehus":
                    togler();
                    System.out.println("Reke");
                    garasjeValg.setVisible(true);
                    kjellerValg.setVisible(true);
                    etasjeBox.setVisible(true);
                    revalidate();
                    break;
                case "Leilighet":
                    togler();
                    heisValg.setVisible(true);
                    balkongValg.setVisible(true);
                    revalidate();
                    break;
                case "Hybel":
                    togler();
                    badValg.setVisible(true);
                    kjøkkenValg.setVisible(true);
                    revalidate();
                    break;
                default:
                    System.out.println("Default");
                    togler();
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

    private class tabellTypeLytter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(boligtabell.isSelected()){

                System.out.println("togla boligtabell " + boligtabell.isSelected() + "perta er" + persontabell.isSelected());
            //   panel3.remove(personTabellScroll);
            //    panel3.remove(boligTabellScroll);
                clearPanel3();
                lagBoligTabellen();
                tapanel.add(boligTabellScroll = new JScrollPane(boligTabell));

                tapanel.add(boligTabellScroll = new JScrollPane(boligTabellTabellen));

                //repaint();
                revalidate();
                repaint();



            }
            else if(persontabell.isSelected()){
                System.out.println("Tolga persontabell " + persontabell.isSelected() + " boligtab er " + boligtabell.isSelected());
                //panel3.remove(personTabellScroll);
              //  panel3.remove(boligTabellScroll);
                clearPanel3();
                lagTabellen();
                tapanel.add(personTabellScroll = new JScrollPane(personTabell));
                //repaint();
                revalidate();
                repaint();

            }

        }
    }




    //todo Christer, Åpne fabrikken


    private String[][] joinPersonArray() {
        String[][] første =  boligsøkere.tilTabell();
        String[][] andre =utleiere.tilTabell();
        String[][] joina = new String[første.length + andre.length][6];


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

    }
    private void lagBoligTabellen()
    {
        boligTabellFabrikk boligTabellModell = new boligTabellFabrikk();
        boligTabellTabellen = new JTable(boligTabellModell);
    }




    private class boligTabellFabrikk extends AbstractTableModel {


        String[] boligkolonnenavn = {"By","Kvadrat","Pris","Adresse","Rom","Parkering","Kjeller","Bilde"};


        String[][] boligceller = joinBoligArray();


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


    }    // end boligTabellFabrikk


    private String[][] joinBoligArray() {
        String[][] første = boliger.eneboligerTilTabell();
        String[][] andre = boliger.hyblerTilTabell();
        String[][] tredje = boliger.leiligheterTilTabell();
        String[][] fjerde = boliger.rekkehusTilTabell();


        String[][] joina = new String[første.length + andre.length + tredje.length + fjerde.length][8];
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

            idGenerator(firm,enavn,fnavn); // todo: Christer, fiks en fet måte yes, denne må også bulletproofes
            Utleier ny = new Utleier(fnavn, enavn, ad ,t ,email, firm);
            JOptionPane.showMessageDialog(null, "nederst på utleier, rett over add");
            utleiere.settInn(ny);
            return;
        }
        JOptionPane.showMessageDialog(null, "du må velge en av typene...");
    }


    private String idGenerator(String f, String en, String fn) //fant masse gøyale måter å gjøre på. denne er kanskje litt for primitiv
    {
        String firma = f;
        String eNavn = en;
        String fNavn = fn;

       String id = f.substring(0,2).toUpperCase()+en.substring(0,2).toUpperCase()+fn.substring(0,2).toUpperCase();

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
        int areal = 0;
        int år = 0;
        int upris = 0;


        if(arealString.equals("") || adr.equals("") || årString.equals("") || utPrisString.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Du må fylle ut alle feltene");
            return;
        }



        try
        {
            areal = Integer.parseInt(arealString);
            år = Integer.parseInt(årString);
            upris = Integer.parseInt(utPrisString);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Du må skrive inn skikkelige verdier!");
        }



        // disse må knyttes til felter senere
        // todo christe rædd feltene
        int eid = 34;
        int tomtareal = 100;
        int plan = 3;
        int balko = -1;

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
        if(romBoxFane2.getSelectedItem().equals("Velg ant. rom."))
            rom = 0;
        else
            rom = Integer.parseInt((String)romBoxFane2.getSelectedItem());

        int antetasjer;
        if(etasjeBoxFane2.getSelectedItem().equals("Velg ant. etg.."))
            antetasjer = 0;
        else
            antetasjer = Integer.parseInt((String)etasjeBoxFane2.getSelectedItem());


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
            case 1: Enebolig nyEnebolig = new Enebolig(adr,byvalg, areal, rom, år, upris, eid, sti, antetasjer, garasje, kjeller, tomtareal);
                         boliger.leggTil(nyEnebolig);
                         break;
            case 2: Rekkehus nyttRekkehus = new Rekkehus(adr,byvalg, areal, rom, år, upris, eid, sti, antetasjer,garasje, kjeller, tomtareal);
                         boliger.leggTil(nyttRekkehus);
                         break;
            case 3: Leilighet nyLeilighet = new Leilighet(adr,byvalg, areal, rom, år, upris, eid, sti, plan, balko, heis);
                         boliger.leggTil(nyLeilighet);
                         break;
            case 4: Hybel nyHybel = new Hybel(adr,byvalg, areal, rom, år, upris, eid, sti, badInt, kjøkkenInt);
                         boliger.leggTil(nyHybel);
                         break;
        }
    }

    public void finnBilde()
    {
        JFileChooser filvelger = new JFileChooser();
        filvelger.setCurrentDirectory( new File( "." ) );
        int resultat = filvelger.showOpenDialog( this );

        if(resultat == JFileChooser.APPROVE_OPTION)
        {
            File f = filvelger.getSelectedFile();
            String filsti = f.getPath();
            bildesti.setText(filsti);
        }
    }



    public void mekkKontrakt()// DETTE ER JÆLA BRA KODE, men må kommentere den vekk til vi har innfelter.
    {/*
        Boligsøker leietaker;
        Utleier utleier;
        Bolig bolig;
        int sluttår, sluttmåned, sluttdag, startår, startmåned,startdag;

        try{
            sluttår = Integer.parseInt((String) sluttÅrFelt.getText());
            sluttmåned = Integer.parseInt((String) sluttMånedFelt.getText());
            sluttdag = Integer.parseInt((String) sluttDagFelt.getText());
            startår = Integer.parseInt((String) StarÅrFelt.getText());
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
            Date start = new Date(startår, startmåned, startdag);
            Date slutt = new Date(sluttår, sluttmåned, sluttdag);

            if(start.before(slutt))
            {
                Kontrakt ny = new Kontrakt(utleier,leietaker, bolig, start, slutt );
                kontrakter.leggTilNy(ny);
                JOptionPane.showMessageDialog(null,"Kontrakt lagret");
                return;
            }
            JOptionPane.showMessageDialog(null, "Disse datoene samsvarer ikke!");
            return;
        }
        JOptionPane.showMessageDialog(null,"Du må skriver gyldige tall i datofeltene!");
        */
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
        /*
        String ut = kontrakter.toString();
        utområde.append(ut);
        */
    }



    public void slettBoligsøker()
    {

    } //  var det dette som vi skulle kunne slette, eller noe annet?

}
