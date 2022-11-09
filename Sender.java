/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ২৯/১০/২২
 * Time: ১১:৩৪ PM
 * Email: joniyed.bhuiyan@gmail.com
 */


import java.io.*;


public class Sender {
    String inputString;
    String outputString;
    FileWriter fw;
    FileReader fr;


    Sender() throws FileNotFoundException, IOException {
        fr = new FileReader("labin.txt");
        fw = new FileWriter("labtemp.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        inputString = "";
        while (true) {
            int x = br.read();
            i++;
            if (x == -1) {
                applicationLayer(inputString);
                break;
            }
            char ch = (char) x;
            inputString = inputString + Character.toString(ch);
            if (i == 125) {
                applicationLayer(inputString);
                i = 0;
                inputString = "";
            }
        }
        System.out.println("Input String: ");
        System.out.println(inputString);
        fw.close();
    }


    void applicationLayer(String s) throws IOException {
        String mod_s = "A-H" + s;
        presentationLayer(mod_s);
    }

    void presentationLayer(String s) throws IOException {
        String mod_s = "P-H" + s;
        sessionLayer(mod_s);
    }

    void sessionLayer(String s) throws IOException {
        String mod_s = "S-H" + s;
        transportLayer(mod_s);
    }

    void transportLayer(String s) throws IOException {
        String mod_s = "T-H" + s;
        networkLayer(mod_s);
    }

    void networkLayer(String s) throws IOException {
        String mod_s = "N-H" + s;
        dataLinkLayer(mod_s);
    }

    void dataLinkLayer(String s) throws IOException {
        String mod_s = "D-H" + s + "D-T";
        physicalLayer(mod_s);
    }

    void physicalLayer(String s) throws IOException {
        String mod_s = "PH-H" + s;
        //System.out.println(mod_s.length());
        outputString = "";
        for (int i = 0; i < mod_s.length(); i++) {
            char c = mod_s.charAt(i);
            String sr = Integer.toBinaryString(c);
            int sr_len = sr.length();
            if (sr_len != 8) {
                for (int j = 0; j < 8 - sr_len; j++) {
                    sr = "0" + sr;
                    //System.out.println("here" + sr);
                }
            }

            outputString = outputString + sr;
        }
        //System.out.println(outputString.length());
        fw.write(outputString);
    }
}