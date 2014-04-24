import javax.swing.*;
import java.awt.*;

/**
 * Created by mac on 01.04.14.
 */
public class

        Main
{
    public static void main (String[]args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                Gui vindu = new Gui();
                vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vindu.setVisible(true);


            }
        });


        System.out.println("HELLOE PIKKK!");
    }
}
