// // package notes;
// //
// // import java.util.Date;
// // import java.io.FileWriter;
// // import java.io.IOException;
// // import org.json.simple.JSONArray;
// // import org.json.simple.JSONObject;
// //
// // public class takeNotes {
// //   public static void writeUserNotes (String userNote) {
// //     Date sysDate = new Date ();
// //
// //     JSONObject note = new JSONObject();
// // 		note.put("Note", userNote);
// // 		note.put("Date", ""+sysDate.getTime());
// //
// // 		JSONArray notesArray = new JSONArray();
// // 		notesArray.add(note);
// //
// // 		try (FileWriter file = new FileWriter("/home/duncan/Documents/notes.txt")) {
// // 			file.write(notesArray.toJSONString());
// // 			System.out.println("Successfully Copied JSON Object to File...");
// // 			System.out.println("\nJSON Array: " + notesArray);
// //
// //     }
// //   }
// //   public static void getUserNotes () {
// //
// //   }
// //   public static void main(String [] args) {
// //     writeUserNotes ("Hey");
// //   }
// // }
package notes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class takeNotes {
    final static String notesFilePath = "/home/duncan/duncan_assets/test.json";
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);

        try (FileWriter file = new FileWriter(notesFilePath)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(obj);
    }

}
