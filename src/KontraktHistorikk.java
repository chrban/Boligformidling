import java.io.*;

public class KontraktHistorikk {
    private File fil;
    public KontraktHistorikk(){
        fil = new File("D:\\BoligHistorikk.txt");
    }
    public void skrivTilTekstFil(String n){
        try{
            String content = n;

            if(!fil.exists())
                fil.createNewFile();

            FileWriter fw = new FileWriter(fil.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(content);
            bw.close();
            System.out.println();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public String lesFraTekstFil(){
        BufferedReader br = null;
        try{
            String current;
            String ut = "";
            br = new BufferedReader(new FileReader("D:\\BoligHistorikk.txt"));

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
    public void openTekstFil(){
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "D:\\BoligHistorikk.txt");
            pb.start();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
