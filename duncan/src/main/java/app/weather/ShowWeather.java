package weather;

import java.lang.Runtime;
import java.io.IOException;
import java.io.*;
import java.lang.Object;
import java.lang.*;

public class ShowWeather {

    public static String[] readWeather()
    {
        String str[] = new String [3];
        try{    
            FileReader fr = new FileReader("/home/mohit/Documents/IR/duncan/duncan/src/main/java/app/weather/weather.txt");    
            BufferedReader br = new BufferedReader(fr); 
            
            String s;
            int i = 0;
            while((s = br.readLine()) != null) {
                str[i] = s;
                i = i + 1;
            } 
        }catch(Exception e) {
                System.out.println(e); 
            }
        return str; 
 }  
    
  public static String[] showWeather () {
    try {
         // create a new array of 2 strings
         String[] cmdArray = new String[2];

         // first argument is the program we want to open
         cmdArray[0] = "python3";

         // second argument is a txt file we want to open with notepad
         cmdArray[1] = "weather.py";

         // print a message
         System.out.println("Displaying weather....");

         // create a process and execute cmdArray and correct environment
         Process process = Runtime.getRuntime().exec(cmdArray, null);
         process.waitFor ();
         
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return readWeather();
  }

}
