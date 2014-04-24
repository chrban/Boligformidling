import javax.swing.*;
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
    private JTextField tekst1, tekst2, tekst3;

    public Gui()
    {
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        setSize(skjerm);
        setLocationByPlatform(true);


        // FANA #1 - Utleier

        JPanel panel1 = new JPanel(layout);
        fane.addTab("Ny utleier", null, panel1, "Registrering");


        knapp1 = new JButton("Knapp 1");
            c.weightx = 0.0;
            c.gridx = 0;
            c.gridy = 0;
        panel1.add(knapp1, c);







        // FANA #2 - Boligsøker

        JPanel panel2 = new JPanel(layout);
        fane.addTab("Ny boligsøker", null, panel2, "registrerer boligsøker");





        add(fane);
    }

}
