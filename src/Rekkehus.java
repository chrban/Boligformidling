import java.io.*;

/**
 * Created by mac on 02.04.14.
 */

    public class Rekkehus extends Bolig implements Serializable
    {
        private int etasjer;
        private int kjeller;
        private int parkering;
        private int tomtstørrelse;
        private int[] specArray;
        private static int id = 1000;

        public Rekkehus(){}

        public Rekkehus(String ad,int s, int b, int r, int by, int u ,String uid, String sti, int e, int p, int k, int t)
        {
            super(id++,ad,s, b, r, by, u, uid, sti);
            etasjer = e;
            parkering = p;
            kjeller = k;
            tomtstørrelse = t;
        }


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

        public String[] tilTabell()
        {
            String[] ut = new String[8];

            ut[0] = sted();
            ut[1] = getBoareal() + " m²";
            ut[2] = getUtleiepris() + " kr/m";
            ut[3] = getAdresse();
            ut[4] = Integer.toString(super.getRom());
            ut[5] = Integer.toString(parkering);
            ut[6] = Integer.toString(kjeller);
            ut[7] = super.getBildesti();

            return ut;
        }

        public Object[] tilMatchTabell()
        {
            Object[] ut = new Object[10];

            ut[0] = new Integer(0);
            ut[1] = sted();
            ut[2] = getBoareal() + " m²";
            ut[3] = getUtleiepris() + " kr/m";
            ut[4] = getAdresse();
            ut[5] = Integer.toString(super.getRom());
            ut[6] = Integer.toString(parkering);
            ut[7] = Integer.toString(kjeller);
            ut[8] = super.getBildesti();
            ut[9] = id;

            return ut;
        }

        public Object[] tilEnkelTabell()
        {
            Object[] ut = new Object[4];

            ut[0] = super.getId();
            ut[1] = sted();
            ut[2] = "Enebolig";
            ut[3] = getAdresse();

            return ut;
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
   /*
    public datatype[] specs()
    {
        returner array med spesifikasjoner om boligen(både fra super- og subklassen) for sammenlikning med boligsøkers krav.
    }
    */

}
