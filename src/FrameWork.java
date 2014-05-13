import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Emil on 12.05.2014.
 */



public class FrameWork{
    private static JFrame frame;
    private static  JButton button;
    private static JLabel label;
    private static Listener listen;
    private static GridBagConstraints c = new GridBagConstraints();
    private static GridBagLayout layout = new GridBagLayout();

    public static void FrameWork(){
    }
    public static void showFrame(String e, String n){
        frame = new JFrame(e);
        button = new JButton("Ok");
        button.setSize(70,40);
        listen = new Listener();
        button.addActionListener(listen);
        label = new JLabel(n);


        JPanel j = new JPanel(layout);
        c.gridx=0;
        c.gridy=1;
        c.insets = new Insets(0,0,10,0);
        j.add(label,c);

        c.insets = new Insets(0,0,0,0);

        c.gridx=0;
        c.gridy=2;
        j.add(button,c);

        frame.add(j);



      //  frame.add(label, BorderLayout.CENTER);
      //  frame.add(j, BorderLayout.SOUTH);
        frame.addWindowListener(new WindowEventHandler());
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        



     }

    private static class WindowEventHandler extends WindowAdapter{
        public void windowShutDown(WindowEvent ev){
            frame.dispose();
            frame.notifyAll();
        }
    }
    private static class Listener implements ActionListener{
        public void actionPerformed(ActionEvent o){
            if(o.getSource() == button){

                frame.dispose();
                System.out.println("TRYKKA");
            }

        }
    }
}