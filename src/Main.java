import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by mac on 01.04.14.
 */
public class

        Main
{
    public static void main (String[]args)
    {

        final Gui vindu = new Gui();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                vindu.setVisible(true);
                vindu.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        vindu.skrivTilFil();
                        System.exit(0);
                    }
                });
            }
        });
    }
}



