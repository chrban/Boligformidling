import javax.swing.*;
import java.awt.*;
import java.io.*;
/*
Filen inneholder datafelt og metoder for klassen Rekkehus
Skrevet av: Kristoffer, Christer og Emil
Siste versjon:
 */

    public class Rekkehus extends Bolig implements Serializable
    {
        private int etasjer;
        private int kjeller;
        private int parkering;
        private int tomtstørrelse;
        private int[] specArray;

        public Rekkehus(int i,String ad,int s, int b, int r, int by, int u ,String uid, String sti, int e, int p, int k, int t, String be)
        {
            super(i,ad,s, b, r, by, u, uid, sti, be);
            etasjer = e;
            parkering = p;
            kjeller = k;
            tomtstørrelse = t;
        }

        //start of getMetoder
        public boolean getBooleanVerdiPark()
        {
            System.out.println("parkering"+parkering);
            if(parkering == 1)
                return true;

            return false;
        }

        public boolean getBooleanVerdiKjeller()
        {
            System.out.println("kjeller :" +kjeller);
            if(kjeller == 1)
                return true;

            return false;
        }
        public String sted()
        {
            String sted = "";

            switch (super.getSted()) {
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
                default:
                    sted = "Ukjent";
                    break;
            }
            return sted;
        }
        //Returnerer en Int-array med Rekkehus-objektets spesifikasjoner for matching
        public int[] getSpecArray(){
            specArray = new int[Konstanter.SPEC_LENGDE];
            specArray[1] = super.getSted();
            specArray[2] = super.getRom();
            specArray[3] = parkering;
            specArray[4] = etasjer;
            specArray[5] = kjeller;
            specArray[6] = Konstanter.URELEVANT;
            specArray[7] = Konstanter.URELEVANT;
            specArray[8] = Konstanter.URELEVANT;
            specArray[9] = Konstanter.URELEVANT;
            specArray[10] = super.getUtleiepris();
            return specArray;
        }
        //Returnerer en String-array for å sjekke om Rekkehus-objektet er likt andre Rekkehus-objekter
        public String[] getUnikArray()
        {
            String [] unik = new String[4];

            unik [0] = getEierID();
            unik [1] = getAdresse();
            unik [2] = Integer.toString(getBoareal());
            unik [3] = Integer.toString(getUtleiepris());

            return unik;
        }
        //end of getMetoder
        //Returnerer en Object-arary for å kunne vise informasjon om Rekkehus-objektet i GUI
        public Object[] tilTabell()
        {
            Object[] ut = new Object[9];

            ut[0] = sted();
            ut[1] = getBoareal() + " m²";
            ut[2] = getUtleiepris() + " kr/m";
            ut[3] = getAdresse();
            ut[4] = Integer.toString(super.getRom());
            ut[5] = getBooleanVerdiPark();
            ut[6] = getBooleanVerdiKjeller();
            ut[7] = super.getBildesti();
            ut[8] = super.getId();

            return ut;
        }
        //Returnerer en Object-array for å kunne vise informasjon om Rekkehus-objektet i matchingmakingfanen I GUI
        public Object[] tilMatchTabell()
        {
            Object[] ut = new Object[10];

            ut[0] = 0;
            ut[1] = sted();
            ut[2] = getBoareal() + " m²";
            ut[3] = getUtleiepris() + " kr/m";
            ut[4] = getAdresse();
            ut[5] = Integer.toString(super.getRom());
            ut[6] = Integer.toString(parkering);
            ut[7] = Integer.toString(kjeller);

            ImageIcon image = new ImageIcon(getBildesti());
            Image img = image.getImage();
            Image skalert = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            image = new ImageIcon(skalert);

            ut[8] = image;
            ut[9] = super.getId();

            return ut;
        }
        //Returnerer true eller false om Rekkehus-objektet er likt andre Rekkehus-objekter
        public boolean erLik(String[] andre)
        {
            String [] specs = getUnikArray();
            int lik = 0;

            for(int i = 0; i <= 3; i++)
                if(andre[i].equals(specs[i]))
                    lik++;

            if(lik == 4)
                return true;

            return false;
        }
}//end of class Rekkehus
