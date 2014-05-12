import java.io.*;
/*
Filen inneholder metoder for å skrive alle toString'ene til nyopprettet kontrakter til en fil og lese fra den.
Dette gjøres for at firma skal kunne ha en oversikt historikken over inngåtte kontrakter
Skrevet av: Emil, s198772
Sist oppdatert: 11/05/2014
 */
public class KontraktHistorikk {
    private File fil;
    public KontraktHistorikk(){
        fil = new File("BoligHistorikk.txt");
    }
    //Metoden skriver til tekststrenger som kommer i parameteret til fil, hvis ikke filen allerde eksisterer
    //oppretter metoden en slik fil.
    public void skrivTilTekstFil(String n){
        try{
            if(!fil.exists())
                fil.createNewFile();

            FileWriter fw = new FileWriter(fil.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(n);
            bw.close();
            System.out.println();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //Metoden leser innholde til filen og returnerer det i form av en streng
    public String lesFraTekstFil(){
        BufferedReader br = null;
        try{
            String current;
            String ut = "";
            br = new BufferedReader(new FileReader("BoligHistorikk.txt"));

            while((current = br.readLine()) != null )
                ut += current + "\n";
            return ut;

        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally {
            try{
                if (br != null)
                    br.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return null;

    }
    //Metoden blir ikke brukt siden den ikke fungerer for mac-brukere fordi mac ikke har programmet notepad.
    //velger å ha den her hvis den kan komme til nytte ved en videreutvikling
    public void openTekstFil(){
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "BoligHistorikk.txt");
            pb.start();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}//end of class KontraktHistorikk
