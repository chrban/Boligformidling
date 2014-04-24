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

    public Gui()
    {
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension skjerm = tools.getScreenSize();
        setSize(skjerm);
        setLocationByPlatform(true);


        // FANA #1 - Utleier

        JPanel panel1 = new JPanel(layout);
        fane.addTab("Ny utleier", null, panel1, "Registrering");


        // FANA #2 - Boligsøker

        JPanel panel2 = new JPanel(layout);
        fane.addTab("Ny boligsøker", null, panel2, "registrerer boligsøker");





        add(fane);
    }

}
