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


    public Gui()
    {
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        setSize(skjerm);
        setLocationByPlatform(true);


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

}
