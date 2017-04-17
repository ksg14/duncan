import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class test {

    public static void main(String[] args) throws IOException 
    {
        //
        // Create an instance of File for data.txt file.
        //
        File file = new File("input.txt");
        File file1 = new File("alarmTiming.txt");

        FileWriter fw = new FileWriter(file1, true);
        BufferedWriter bw = new BufferedWriter(fw);


        try {
            //
            // Create a new Scanner object which will read the data from the 
            // file passed in. To check if there are more line to read from it
            // we check by calling the scanner.hasNextLine() method. We then
            // read line one by one till all line is read.
            //
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] tokens = line.split(",");
                bw.write(tokens[0]);
                bw.write("\n");
                //System.out.println(tokens[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bw.close();

    }
}