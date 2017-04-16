// package nltk;

// import java.lang.Runtime;
// import java.io.IOException;
// import java.io.*;
// import java.lang.Object;
// import java.lang.*;

// public class NBClassifier {

//     public static String getLabel()
//     {
//         try{   

//             FileReader fr = new FileReader("./src/main/java/app/nltk/classificationLabel.txt");    
//             BufferedReader br = new BufferedReader(fr); 
//             String s;
//             int i = 0;
//             while((s = br.readLine()) != null) {
//                 return s;
//             } 
//         }catch(Exception e) {
//                 System.out.println(e); 
//             }
//         return s; 
//  }  
    
//   public static String labelGenerator (String query) {

//     try {
//          // create a new array of 2 strings
//          String[] cmdArray = new String[2];

//          // first argument is the program we want to open
//          cmdArray[0] = "python3";

//          // second argument is a txt file we want to open with notepad
//          cmdArray[1] = "nbClassifier.py";

//          //User Query
//          cmdArray[2] = query;
//          // create a process and execute cmdArray and correct environment
//          Process process = Runtime.getRuntime().exec(cmdArray, null);
//          process.waitFor ();

//          return getLabel();
         
//       } catch (Exception ex) {
//          ex.printStackTrace();
//       }
//       return getLabel();
//   }
// }
