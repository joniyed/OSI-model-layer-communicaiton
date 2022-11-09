/**
 * Created by IntelliJ IDEA.
 * User: joniyed
 * Date: ২৯/১০/২২
 * Time: ১১:৩৪ PM
 * Email: joniyed.bhuiyan@gmail.com
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reciever {
    String inputString;
    String outputString;
    FileWriter fw;
    FileReader fr;

    Reciever() throws IOException{
        fr = new FileReader("labtemp.txt");
        fw = new FileWriter("labout.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        inputString = "";
        while(true){
            int x = br.read();
            i++;
            if(x == -1){
                physicalLayer(inputString);
                break;
            }
            char ch = (char) x;
            inputString = inputString + Character.toString(ch);
            if(i == 1200)
            {
                physicalLayer(inputString);
                i = 0;
                inputString = "";
            }
        }
        fw.close();
    }


    void physicalLayer(String s) throws IOException{
        outputString = "";
        int i;
        for(i = 0 ; i < s.length();i+=8)
        {
            String temp = s.substring(i, i+8);
            int x = Integer.parseInt(temp, 2);
            char ch = (char) x;
            outputString = outputString + Character.toString(ch);
        }
        outputString = outputString.substring(4);
        datalinkLayer(outputString);
    }

    void datalinkLayer(String s) throws IOException{
        outputString = s.substring(3,s.length()-3);
        networkLayer(outputString);

    }

    void networkLayer(String s) throws IOException{
        System.out.println(s);
        outputString = s.substring(3);
        System.out.println(outputString);
        transportLayer(outputString);
    }

    void transportLayer(String s) throws IOException{
        outputString = s.substring(3);
        sessionLayer(outputString);
    }


    void sessionLayer(String s) throws IOException{
        outputString = s.substring(3);
        presentationLayer(outputString);
    }

    void presentationLayer(String s) throws IOException{
        outputString = s.substring(3);
        applicationLayer(outputString);
    }

    void applicationLayer(String s) throws IOException{
        outputString = s.substring(3);
        fw.write(outputString);
    }
}
